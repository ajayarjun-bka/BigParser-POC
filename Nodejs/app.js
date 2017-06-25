var BpData = require('./modules/bpData');

//Example program to use BpData module to login and Query a grid from BigParser

BpData.fetchData("arjun.bka@gmail.com", "AjayArjun", {
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


BpData.fetchHeader("arjun.bka@gmail.com", "AjayArjun",'57a33a99e4b019ed65d2b00d',(errorMessage, response) => {
                if (errorMessage) {
                    console.log(errorMessage);
                } else {
                    console.log(JSON.stringify(response,undefined,2));
                }
            });