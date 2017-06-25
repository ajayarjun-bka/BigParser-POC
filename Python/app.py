from module import BpData

'''Example program to use BpData module to login and Query a grid from BigParser'''

fetchedData = BpData.fetchHeader("abc@xyz.com", "password", "57a33a99e4b019ed65d2b00d");
print(fetchedData)

fetchedData = BpData.fetchData("abc@xyz.com", "password", {'gridId': '57a33a99e4b019ed65d2b00d', 'rowCount': '50',
                                                           'keywords': ['Wine Bar'],
                                                           'selectColumnsStoreName': ['0', '3']});
print(fetchedData)
