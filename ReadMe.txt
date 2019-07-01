------------------------------------------------------------------------------------------------

BROADCASTER	
Version 0.6.0 		
06/24/2019

------------------------------------------------------------------------------------------------
VERSION 0.6.0 changes
------------------------------------------------------------------------------------------------

-added support for multi-threading 
-added a timer thread to keep track of active question elapsed time.
-added answer monitoring thread to keep track of incoming responses in real-time.

------------------------------------------------------------------------------------------------
INSTALL
------------------------------------------------------------------------------------------------

Broadcaster for since version 0.3.0 now comes with a additional server application to 
support the new quiz functions. That application is a node.js server. And also the
Ranker companion application for Visualizing the Quiz grades.

*** MAKE SURE TO INPUT YOUR ID AND TOKEN FOR TWILLIO API IN THE CREDENTIALS.TXT FILE
YOU MUST CREATE A TWILLIO ACCOUNT FOR THOSE.

IN THE PROCESS OF UPLOADING SERVER TO HEROKU QUIZ PORTION NOT WORKING
SENDING MESSAGES AND EMAILLS WILL WORK 

You should see this message if everything is working:

SERVER listening on localhost port  8080
CONNECTED to ngrok tunnel https://broadcaster.ngrok.io

Leave the server running and open another command line window this time in the 
Broadcaster and Ranker Client folder.
I packaged the Broadcaster and Ranker java client application as a jar executable.
In your command line run:

	$ java -jar Broadcaster.jar

and to run Ranker use:

	$ java -jar Ranker.jar


***NEW FOR VERSION 0.5.0 RANKER REQUIRES MySQL DATABASE CONNECTION

Befor using the database features of Ranker you must have an MySql server running.
Create a new Database and name it "broadcaster" all lowercase.
Create a user with access to that database with the following credentials and all permissions:

USERNAME: cs622admin
PASSWORD: cs622

Then run 

	$ java -jar CreateDatabase.jar

If everything runs correctly you should see "Data successfully created" message

You can now use all features of Brodcaster and Ranker application.


------------------------------------------------------------------------------------------------
USAGE NOTES FOR NEW FEATURES
------------------------------------------------------------------------------------------------

Menu:
1. Broadcast SMS
2. Broadcast Email
3. Add Contact
4. Delete Contact
5. View Contact List
6. Begin Question
7. End Question
8. Current Question Status <==
9. Exit


The Begin Quiz option lets you input a question and expected answer and
send it to all contacts in the contact list. You can reply multiple times on your 
phone.
(This is to allow for testing multiple responses with a limited number of phones
however doing this will alter results in Ranker therefore responding once per question 
is advised. In the final version responses from the same phone number will be overwritten)

While the Quiz is in progress you can turn on the logging function using option "8"
then you reply on your phone responses will be registered on the client side.

Once you are done quizzing your contacts you can open up Ranker companion app 
to visualize and manipulate results from the Quiz session as well as use database features.

-------------------------------------------------------------------------------------------------
DEPENDENCIES
------------------------------------------------------------------------------------------------
CLIENT BROADCASTER

-twilio 7.37.4
-javax.mail 1.6.2
-junit 4.12
-okhttp3 4.0.0-alpha02
-json 20180813

CLIENT RANKER

-mysql-connector-java-8.0.16
-JavaFX

SERVER

 "body-parser": "^1.19.0",
 "express": "^4.17.0",
 "ngrok": "^3.1.1",
 "twilio": "^3.31.0"


------------------------------------------------------------------------------------------------
VERSION CHANGES
------------------------------------------------------------------------------------------------

Version 0.1.0 
- The application currently supports sending Sms and email to a single user 
  with all the fields hardcoded.
  
Version 0.2.0
-added support for sending SMS and Email to multiple users.
-added message generation upon choosing a broadcast type (no longer hard coded)
-added a management system for contacts.

Version 0.3.0
-added support for sending quizzes via SMS.
-added a new feature that grades student responses.
-added a new feature that displays question statistics based on student responses received.

Version 0.4.0
-added support for grading and recording CUMULATIVE student Quiz progress.
-added a objecFile.dat as a medium for storing student responses.
-created "Ranker" a GUI companion app for visualizing and filtering student results.

Version 0.5.0
-added MySQL database connectivity.
-added ability to Add quiz sessions to the database.
-added ability to Query database by first name, last name, quiz, grade.
-updated "Ranker" GUI interface to support those features.

