
const rest = require('./rest');


var BpData = {

// Function to login and fetch authId for future calls

    login: function (username, password, callback) {
        let uri = "https://www.bigparser.com/APIServices/api/common/login";
        data = {
            'emailId': username,
            'password': password
        }

        headers = {
            'content-type': 'application/json'
        }

        rest.post(uri, headers, data,  (errorMessage, response) => {
            if (errorMessage) {
                console.log(errorMessage);
            } else {
                callback(response.result.authId);
            }
        });
    },


//Function to fetch Data from grid. gridId is required. Additional parameters can be specified as JSON object. 
//Refer API documentation for format of JSON
    
    fetchData: function (username, password, data,callback) {
        this.login(username, password, (key) => {
            authID = key;
            headers = {
                authId: authID
            };
            let uri = "https://www.bigparser.com/APIServices/api/query/table?startIndex=0&endIndex=50";
            rest.post(uri,headers, data,callback);
        });
    },

//Function to fetch header of a grid. gridId should be specified
    
    fetchHeader: function (username, password, gridId,callback) {
        this.login(username, password, (key) => {
            authID = key;
            headers = {
                authId: authID
            };
            let uri = `https://www.bigparser.com/APIServices/api/grid/headers?gridId=${gridId}`;
            rest.get(uri, headers,callback);
        });
    }
}

module.exports = BpData;
