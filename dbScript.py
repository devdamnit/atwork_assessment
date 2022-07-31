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
        
        userTableQ = "CREATE TABLE users (" 
        userTableQ += "id BIGINT,"
        userTableQ += "firstName varchar(255) NOT NULL," 
        userTableQ += "lastName varchar(255) NOT NULL,"
        userTableQ += "annualSalary BIGINT NOT NULL," 
        userTableQ += "dateOfBirth varchar(11) NOT NULL,"
        userTableQ += "email varchar(255) NOT NULL,"
        userTableQ += "gender varchar(7) NOT NULL," 
        userTableQ += "mobilePhone varchar(11) NOT NULL," 
        userTableQ += "userType varchar(11) NOT NULL," 
        userTableQ += "PRIMARY KEY (id))"

        addrTableQ = "CREATE TABLE userAddresses (" 
        addrTableQ += "id BIGINT,"
        addrTableQ += "userId BIGINT NOT NULL,"
        addrTableQ += "addrLn1 varchar(255) NOT NULL,"
        addrTableQ += "addrLn2 varchar(255),"
        addrTableQ += "addrName varchar(255) NOT NULL,"
        addrTableQ += "addrType varchar(9) NOT NULL,"
        addrTableQ += "city varchar(255) NOT NULL,"
        addrTableQ += "stateCode varchar(255) NOT NULL,"
        addrTableQ += "postalCode varchar(255) NOT NULL,"
        addrTableQ += "country varchar(255) NOT NULL,"
        addrTableQ += "PRIMARY KEY (id),"
        addrTableQ += "FOREIGN KEY (userId) REFERENCES users(id))"

        summTableQ = "CREATE TABLE userSummaries (" 
        summTableQ += "dateOfBirth varchar(11)," 
        summTableQ += "firstName varchar(255)," 
        summTableQ += "id BIGINT," 
        summTableQ += "lastName varchar(255)," 
        summTableQ += "FOREIGN KEY (id) REFERENCES users(id))" 

        cursor.execute(userTableQ)
        cursor.execute(addrTableQ)
        cursor.execute(summTableQ)

except Error as e:
            print("Error while connecting to MySQL", e)