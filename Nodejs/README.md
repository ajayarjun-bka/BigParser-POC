# Nodejs Module for BigParser

This module comprises of `bpData.js` and `rest.js`. Copy the modules folder into the root directory of your app and then include this line of code into `app.js` to import the module into your code.
```javascript 
var BpData = require('./modules/bpData');
``` 
Refer `app.js` for example and read below to learn how to use the methods.
#### Dependencies:
`request` module has to be installed for this module to work. Please add `request`, version `^2.81.0` in package.json under `dependencies`

**Availalble Methods:**
```javascript
 fetchHeader(emailId, password, gridId, callback)
```
```javascript
 fetchData(emailId, password, data, callback)
```
## Description of the Methods:


#### fetchHeader

```javascript
 fetchHeader(emailId, password, gridId, callback)
```
*Fetches rows from the specified grid.*

**Arguments**

*Required:*
 
   `emailId` - emailId/username of your account
   
   `password` - password to login into BigParser account
   
   `gridId` - Id of the grid to be queried
   
   `callback` - callback function to handle the response
      
**Sample Call & Response:**

  ```javascript
  BpData.fetchHeader("abc@xyz.com", "password",'57a33a99e4b019ed65d2b00d',(errorMessage, response) => {
                if (errorMessage) {
                    console.log(errorMessage);
                } else {
                    console.log(JSON.stringify(response,undefined,2));
                }
            });
 ``` 
**Success Response:**

   **Code:** 200
   
   **Content:**  
   ```javascript
    {
    "gridType": null,
    "category": null,
    "sample": true,
    "tableName": "T_57a33a99e4b019ed65d2b00c",
    "gridId": "57a33a99e4b019ed65d2b00d",
    "displayName": "Restaurants.grid",
    "gridExtension": null,
    "gridDescription": null,
    "columns": [{
        "columnName": "Name ",
        "dataType": "String",
        "columnStoreName": "0"
    }, {
        "columnName": "Open Table URL",
        "dataType": "String",
        "columnStoreName": "25"
    }],
    "dictionary": null,
    "fileType": "trial",
    "shareId": null,
    "allowCopy": true,
    "allowShare": true,
    "shareDescription": null,
    "sortKeys": [],
    "s3ImageInfo": null,
    "owner": true
}
   ```
   **Error Response:**
   
   **Code:** *Appropriate Error code will be displayed*
   
   **Content:** *Error message will be displayed in the response*
##   
#### fetchData
```java
 fetchData(emailId, password, data, callback)
```
*Fetches rows from the specified grid.Parameters to query the grid are passed as a part of the post request*

**Arguments**

*Required:*
 
   `emailId` - emailId/username of your account
   
   `password` - password to login into BigParser account
   
   `callback` - callback function to handle the response
   
   `data` - comprises the options to query the grid in the form of JSON object.
   
  
   ***List of allowed options in JSON object***
   
* `gridId` - *required* - gridId on which you like to perform search

* `rowCount`	- *required* - No of rows to fetch. If value not provided then default is 10

* `selectColumnsStoreName` - *optional* - array of column store name which you like to fetch, by default all columns will be fetched

	**sample format**

```javascript
   "selectColumnsStoreName": [0, 3]
```
*columnIds start with zero*

* `tags`-	*optional* - array of column level filters. 

	**sample format**

```javascript
      "tags": [{
          "columnValue": "Hello World",
          "columnStoreName": "1"
          },	{
          "columnValue": "Hello Alexa",
          "columnStoreName": "4"
          }]
```
* `keywords`- *optional* - array of global level keywords.

	**sample format**
 
 ```javascript
		"keywords": ["Bear Story","spectre"]
 ```

* `sortFields` - *optional* - array of sort fields
    
	 **sample format**
 ```javascript
		"sortKeys": [{"columnStoreName": "1","ascending": true}]
 ```

**Sample Call & Response:**

  ```javascript
BpData.fetchData("abc@xyz.com", "password", {
    'gridId': '57a33a99e4b019ed65d2b00d',
    'rowCount': '2',
    'keywords': ['Wine Bar'],
    'selectColumnsStoreName': [0,3]
},(errorMessage, response) => {
                if (errorMessage) {
                    console.log(errorMessage);
                } else {
                    console.log(JSON.stringify(response,undefined,2));
                }
            });
 ``` 
**Success Response:**

   **Code:** 200
   
   **Content:**  
   ```javascript
   {
    "count": 5,
    "rows": [{
        "data": ["The Tasting Room Wine Bars & Shop", "+1 703-435-3553"],
        "successful": true
    }, {
        "data": ["Vinifera Wine Bar & Bistro", "+1 703-234-3550"],
        "successful": true
    }, {
        "data": ["Barcelona Wine Bar Reston", "+1 703-689-0700"],
        "successful": true
    }],
    "keywords": [{
        "keyWord": "Wine Bar",
        "count": "5"
    }],
    "otherGrids": [{
        "gridId": "57a33a99e4b019ed65d2b00d",
        "name": "Reston Restaurants",
        "viewId": ""
    }]
}
```
  **Error Response:**
   
   **Code:** *Appropriate Error code will be displayed*
   
   **Content:** *Error message will be displayed in the response*
##
