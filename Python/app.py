from module import BpData

'''Example program to use BpData module to login and Query a grid from BigParser'''
bpData = BpData();
fetchedData = bpData.fetchHeader("arjun.bka@gmail.com", "AjayArjun", "57a33a99e4b019ed65d2b00d");
print(fetchedData)

fetchedData = bpData.fetchData("arjun.bka@gmail.com", "AjayArjun", {'gridId': '57a33a99e4b019ed65d2b00d', 'rowCount': '50',
                                                           'keywords': ['Wine Bar'],
                                                           'selectColumnsStoreName': ['0', '3']});
print(fetchedData)
