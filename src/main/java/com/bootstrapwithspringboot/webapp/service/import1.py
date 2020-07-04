#!/usr/local/bin/python3.7
import csv
import mysql.connector
import sys

def csv_lines_skip_first(csvPath):
    csv_data = csv.reader(open(csvPath, 'r'))
    next(csv_data)
    return csv_data

db = mysql.connector.connect(host='localhost', user='user', passwd='pass', db='db')
cursor = db.cursor()

# orders
for row in csv_lines_skip_first('data/orders.csv'):
    try:
        user_id = int(user_names_to_id[row[0].strip().lower()])
        insertRow = (user_id, row[1])
        print (insertRow)
        cursor.execute(
            "INSERT INTO orders "
            "(user_id, order_details) "
            "VALUES"
            "(%s, %s)",
            insertRow
        )
        db.commit()
        # sys.exit()
    except KeyError as e:
        print("Order add skipped. missing key. " + repr(e))
    except Exception as e:
        print("Order add skipped. Details:" + repr(e))
        sys.exit()


db.commit()
cursor.close()

print("Done")