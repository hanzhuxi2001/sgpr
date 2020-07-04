import requests
r = requests.get("https://snowballdata.com/schools/2017/5")
print (r.content)