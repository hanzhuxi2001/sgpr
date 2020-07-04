##import urllib.request
##response = urllib.request.urlopen('file:///Users/hd/Downloads/bootstrap-with-springboot-master/data1.html')
##result = response.read().decode('utf-8')
##print(result)  
import csv
from urllib.request import urlopen
from bs4 import BeautifulSoup
html=urlopen("file:///Users/hd/Downloads/bootstrap-with-springboot-master/data1.html")
bsObj=BeautifulSoup(html.read())
print("====================")
title=bsObj.findAll({"tr","_ngcontent-uii-c3"})
with open("test.csv","w") as csvfile: 
    writer = csv.writer(csvfile)
    ####写入Csv文件中
    #先写入columns_name
    writer.writerow(["School","Year","Phase","Availiability","Registration","Sucess Rate"])
    if title != None:
        for j in title:
            #写入多行用writerows
            #writer.writerows([j.get_text()])
            #print(j)
            tds=j.findAll({"td","_ngcontent-uii-c3"})
            my_list = []
            for td in tds:
                print(td.get_text())
                my_list.append(td.get_text());
            writer.writerows([my_list])

