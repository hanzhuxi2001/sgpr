##import urllib.request
##response = urllib.request.urlopen('file:///Users/hd/Downloads/bootstrap-with-springboot-master/data1.html')
##result = response.read().decode('utf-8')
##print(result)  
import csv
from urllib.request import urlopen
from bs4 import BeautifulSoup
import requests
#read from http
#r = requests.get("http:///Users/hd/Downloads/spring/bootstrap-with-springboot-master/data/moePhase1.html")
##r = urlopen("/Users/hd/Downloads/spring/bootstrap-with-springboot-master/data/moePhase1.html")
#bsObj=BeautifulSoup(r.text,'html.parser')
#print (r.content)
html=open("/Users/hd/Documents/phase2a-1.html")
bsObj=BeautifulSoup(html.read(),'html.parser')
print("====================")
#print (bsObj)

title=bsObj.findAll({"div","moe-ballot-card m-b:m  "})
with open("moe20202a1.csv","w") as csvfile: 
    writer = csv.writer(csvfile)
    ####写入Csv文件中
    #先写入columns_name
    writer.writerow(["School","Year","Phase","Availiability","Registration","Sucess Rate"])
    if title != None:
        for j in title:
            #写入多行用writerows
            #writer.writerows([j.get_text()])
            #print(j)
            school=j.find("h3")
            if school != None:
                print(school.get_text())
                tds=j.findAll('p', attrs={'class' : 'info-data'})
                if len(tds)==2: 
                    my_list = []
                    c="100%";
                    my_list.append(school.get_text());
                    my_list.append("2020");
                    my_list.append("Phase 1");
                    for td in tds:
                        my_list.append(td.get_text())
                    if (int(my_list[4]))>(int(my_list[3])):
                        c="{0:.0f}%".format(int(my_list[3])/int(my_list[4]) * 100)
                    my_list.append(c)                
                    print(my_list)
                    writer.writerows([my_list])

