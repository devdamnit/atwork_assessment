import mysql.connector as msql
from mysql.connector import Error

try:
    conn = msql.connect(host="sql5.freesqldatabase.com", database="sql5509456", user="sql5509456", password="VBeHFA4cgC")
    if conn.is_connected():
        cursor = conn.cursor()
        cursor.execute("select database();")
        record = cursor.fetchone()
        print("You're connected to database: ", record)

        cursor.execute('DROP TABLE IF EXISTS users;')
        cursor.execute('DROP TABLE IF EXISTS userAddresses;')
        cursor.execute('DROP TABLE IF EXISTS userSummaries;')
        print('Creating tables....')
        
        cursor.execute("CREATE TABLE users(id BIGINT PRIMARY KEY, firstName varchar(255), lastName varchar(255), annualSalary BIGINT, dateOfBirth varchar(11), email varchar(255), gender varchar(7), mobilePhone varchar(11), userType varchar(11))")
        cursor.execute("CREATE TABLE userAddressess(id BIGINT PRIMARY KEY, userId BIGINT , )")

except Error as e:
            print("Error while connecting to MySQL", e)