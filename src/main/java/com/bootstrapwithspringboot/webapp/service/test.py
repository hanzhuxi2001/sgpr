
#!/usr/bin/python3
import csv
from urllib.request import urlopen
from bs4 import BeautifulSoup

url='file:///Users/hd/Downloads/bootstrap-with-springboot-master/data/'
for n in range(2010, 2011):
    for x in range(6, 7):
        curUrl=url+str(n)+str(x)+'.html'
        print(curUrl)
        curFileName=str(n)+str(x)
        print(curFileName)
        html=urlopen(curUrl)
        bsObj=BeautifulSoup(html.read())
        print("====================")
        title=bsObj.findAll({"tr","_ngcontent-uii-c3"})
        with open(curFileName,"w") as csvfile: 
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


