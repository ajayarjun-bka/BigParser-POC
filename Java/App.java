package base;

import base.BpData;
/**
 * Sample program to show how to use BpData.java to fetch data from BigParser
 * @author Ajay Arjun
 * @version 1.0
 */
public class App {

	public static void main(String args[]) {
		String result=null;
		// Function Call to fetch rows from the specified grid based on the filtering parameters
		System.out.println("Request 1");
		result=BpData.fetchData("abc@xyz.com", "password","{\"gridId\":\"57a33a99e4b019ed65d2b00d\",\"keywords\": [\"Wine Bar\"],\"selectColumnsStoreName\": [0,3],\"rowCount\":\"3\"}");
		System.out.println(result);
		// Function Call to fetch header of the specified grid
		System.out.println("Request 2");
		result=BpData.fetchHeader("abc@xyz.com", "password","57a33a99e4b019ed65d2b00d");
		System.out.println(result);
	}
}
