# Import the module into the main app
from module import BpData

# Example program to use BpData module to login and Query a grid from BigParser

# Function Call to fetch header of the specified grid
fetchedData = BpData.fetchHeader("arjun.bka@gmail.com", "AjayArjun", "57a33a99e4b019ed65d2b00d");
print(fetchedData)

# Function Call to fetch rows from the specified grid based on the filtering parameters
fetchedData = BpData.fetchData("arjun.bka@gmail.com", "AjayArjun",
                               {'gridId': '57a33a99e4b019ed65d2b00d', 'rowCount': '50',
                                'keywords': ['Wine Bar'],
                                'selectColumnsStoreName': [0, 3]});
print(fetchedData)
