# atwork_assessment
This is an assessment for the intern position at Atwork Systems

# Requirements
Jdk 11 or above

# Assumptions
	- userId property in UserAddress is a foreign key referencing to its User.
	- id property in UserSummary is a foreign key referencing to its User.

# TO RUN 
To run the code, go to the Employee directory and enter "./mvnw spring-boot:run" in terminal.
Maven requires JAVA_HOME env variable to be referenced to a jdk and not a jre.

# Left to do
	- Error class was not implemented properly.
	- PUT /users/{userId} endpoint updates the user, but the response is still bad request
	- The required responses are not received on successes/failures but db is updated as expected

# The database is hosted by freesqldatabase.com 
	db host: sql5.freesqldatabase.com
	db name: sql5509844
	db username: sql5509844
	db password: k6wpMdmTXa

	The credentials can be used to access it using phpmyadmin

# Notes
	- The database is hosted for free and can handle atmost 20 connections at a time.
	- The database will be cleared when you access it for the first time. 
	- The python script to create tables in database is included but is of NO USE since
		the java application makes the tables itself.
