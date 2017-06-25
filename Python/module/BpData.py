import requests
import json
import sys

'''
BpData This class implements the methods needed to connect to BigParser's
API to authenticate and fetch the required data
@author Ajay Arjun
@version 1.0
'''

'''
This method makes generic post calls
@param endPoint 
        Target URI
@param headers
        Headers to be passed for the current request
@param data
        Body of the post request
@return String returns the response as JSON Object
'''


def post(uri, headers, data):
    try:
        response = requests.post(uri, data=json.dumps(data), headers=headers)
        response.raise_for_status()
        if response.status_code == 200:
            responseData = json.loads(response.text)
            return responseData
    except requests.exceptions.HTTPError as err:
        print(err.response.text)
        sys.exit(1)
    except requests.exceptions.Timeout as err:
        print(err.response.text)
        sys.exit(1)
    except requests.exceptions.ConnectionError as err:
        print(err.response.text)
        sys.exit(1)


'''
This method makes generic get calls
@param endPoint 
        Target URI
@param headers
        Headers to be passed for the current request
@return String returns the response as JSON Object
'''


def get(uri, headers):
    try:
        response = requests.get(uri, headers=headers)
        response.raise_for_status()
        if response.status_code == 200:
            responseData = json.loads(response.text)
            return responseData
    except requests.exceptions.HTTPError as err:
        print(err.response.text)
        sys.exit(1)
    except requests.exceptions.Timeout as err:
        print(err.response.text)
        sys.exit(1)
    except requests.exceptions.ConnectionError as err:
        print(err.response.text)
        sys.exit(1)


'''
This method performs the task of login into BigParser account and fetch authId for future calls
@param emailId
    emailId/username of your account
@param password
    password to login into BigParser account
@return String returns the response as JSON Object
'''


def login(username, password):
    uri = "https://www.bigparser.com/APIServices/api/common/login"
    data = {'emailId': '' + username + '', 'password': '' + password + ''}
    headers = {'content-type': 'application/json'}
    response = post(uri, headers, data)
    authId = response["authId"]
    return authId


'''
Fetches rows from the specified grid. Parameters to query the grid are passed as a part of the post request
@param emailId
    emailId/username of your account
@param password
    password to login into BigParser account
@param gridId
    gridId of the required grid
@return String returns the response as JSON in string format
'''


def fetchHeader(username, password, gridId):
    authId = login(username, password)
    uri = "https://www.bigparser.com/APIServices/api/grid/headers?gridId=" + gridId
    headers = {'content-type': 'application/json', 'authId': '' + authId + ''}
    response = get(uri, headers)
    return response


'''
Fetches rows from the specified grid. Parameters to query the grid are passed as a part of the post request
@param emailId
    emailId/username of your account
@param password
    password to login into BigParser account
@param data
    comprises the options to query the grid in the form of JSON object.
@return String returns the response as JSON in string format
'''


def fetchData(username, password, data):
    authId = login(username, password)
    uri = "https://www.bigparser.com/APIServices/api/query/table?startIndex=0&endIndex=50"
    headers = {'content-type': 'application/json', 'authId': '' + authId + ''}
    data = data
    response = post(uri, headers, data)
    return response
