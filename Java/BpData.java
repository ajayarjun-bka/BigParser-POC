package base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * BpData This class implements the methods needed to connect to BigParser's API
 * to authenticate and fetch the required data
 * 
 * @author Ajay Arjun
 * @version 1.0
 */

final public class BpData {
	/**
	 * This method makes generic post calls
	 * 
	 * @param endPoint
	 *            Target Url
	 * @param headers
	 *            Headers to be passed for the current request
	 * @param data
	 *            Body of the post request
	 * @return String returns the response as JSON Object
	 */

	private static String post(String endpoint, Map<String, String> headers, String data) {
		StringBuilder response = new StringBuilder();
		try {
			URL uri = new URL(endpoint);
			HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
			for (String key : headers.keySet()) {
				conn.setRequestProperty(key, headers.get(key));
			}
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			OutputStream output = conn.getOutputStream();
			output.write(data.getBytes());
			output.flush();
			output.close();
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException(
						String.format("Error Code:%s\nMessage%s", conn.getResponseCode(), conn.getResponseMessage()));
			} else {
				String temp;
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((temp = br.readLine()) != null) {
					response.append(temp);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return response.toString();
	}

	/**
	 * This method makes generic get calls
	 * 
	 * @param endPoint
	 *            Target Url
	 * @param headers
	 *            Headers to be passed for the current request
	 * @return String returns the response as JSON Object
	 */

	private static String get(String endpoint, Map<String, String> headers) {
		StringBuilder response = new StringBuilder();
		try {
			URL uri = new URL(endpoint);
			HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
			for (String key : headers.keySet()) {
				conn.setRequestProperty(key, headers.get(key));
			}
			conn.setRequestMethod("GET");

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException(
						String.format("Error Code:%s\nMessage%s", conn.getResponseCode(), conn.getResponseMessage()));
			} else {
				String temp;
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((temp = br.readLine()) != null) {
					response.append(temp);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return response.toString();
	}

	/**
	 * This method performs the task of login into BigParser account and fetch
	 * authId for future calls
	 * 
	 * @param emailId
	 *            emailId/username of your account
	 * @param password
	 *            password to login into BigParser account
	 * @return String returns the response as JSON Object
	 */
	private static String login(String emailId, String password) {
		String uri = "https://www.bigparser.com/APIServices/api/common/login";
		Map<String, String> header = new HashMap<String, String>();
		header.put("content-type", "application/json");
		String body = String.format("{\"emailId\":\"%s\",\"password\":\"%s\"}", emailId, password);
		String response = BpData.post(uri, header, body);
		String authId = null;
		if (response.length() > 0) {
			try {
				int start = response.indexOf("\"authId\"") + 10;
				int end = response.indexOf("\",\"subscriptionInfo\"");
				authId = response.substring(start, end);
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}
		}
		return authId;
	}

	/**
	 * Fetches rows from the specified grid. Parameters to query the grid are
	 * passed as a part of the post request
	 * 
	 * @param emailId
	 *            emailId/username of your account
	 * @param password
	 *            password to login into BigParser account
	 * @param data
	 *            comprises the options to query the grid in the form of JSON
	 *            object.
	 * @return String returns the response as JSON in string format
	 */
	public static String fetchData(String emailId, String password, String data) {
		String uri = "https://www.bigparser.com/APIServices/api/query/table?startIndex=0&endIndex=50";
		String authId = BpData.login(emailId, password);
		if (authId != null) {
			Map<String, String> header = new HashMap<String, String>();
			header.put("Content-Type", "application/json");
			header.put("authId", authId);
			String response = BpData.post(uri, header, data);
			return response;
		}
		return "check your request";
	}

	/**
	 * Function to fetch header of a grid. gridId is required.
	 *
	 * @param emailId
	 *            emailId/username of your account
	 * @param password
	 *            password to login into BigParser account
	 * @param gridId
	 *            gridId of the required grid
	 * @return String returns the response as JSON in string format
	 */

	public static String fetchHeader(String emailId, String password, String gridId) {
		String uri = String.format("https://www.bigparser.com/APIServices/api/grid/headers?gridId=%s", gridId);
		String authId = BpData.login(emailId, password);
		if (authId != null) {
			Map<String, String> header = new HashMap<String, String>();
			header.put("Content-Type", "application/json");
			header.put("authId", authId);
			String response = BpData.get(uri, header);
			return response;
		}
		return "check your request";
	}
}
