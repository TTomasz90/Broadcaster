------------------------------------------------------------------------------------------------

BROADCASTER	
Version 0.6.0 		
06/24/2019

------------------------------------------------------------------------------------------------
VERSION 0.6.0 changes
------------------------------------------------------------------------------------------------

-added support for multi-threading 
-added a timer thread to keep track of active question elapsed time.
-added an answer monitoring thread to keep track of incoming responses in real-time.

------------------------------------------------------------------------------------------------
ABOUT 
------------------------------------------------------------------------------------------------

The project I implemented is a student communication and assessment tool that Professors/Facilitators 
can use to assess student engagement and understanding of the presented material. Via the system called
Broadcaster faculty can send student remainders directly to the entire class from a specified set of contacts.
Broadcaster will integrate a contact list that can accept, delete and display available contacts. Users
will be easily able to compose new messages both in Email and SMS formats and broadcast them to all contacts on the contact list. During live lectures, the faculty will also be able to send multiple-choice
questions via SMS as a form of an interactive quiz to measure student understanding and progress based
on the number of correct answers. The faculty will be able to view the progress of students in real-time
to see how much time students are spending on the administered questions and how many responses were recorded
from the pool of students. The purpose of the project is to create a learning tool that will aid the faculty
in student assessment and engage students to actively participate in a live class. The program will also introduce 
a Gui companion program called Ranker for viewing results from the administered Quiz. It provides the ability to sort participant scores based on correctness and display best and worst-performing individuals. It also incorporates database connectivity to store and retrieve past quiz information and student grades.

------------------------------------------------------------------------------------------------
INSTALL
------------------------------------------------------------------------------------------------

Broadcaster for since version 0.3.0 now comes with an additional server application to support the new quiz functions. That application is a node.js server. And also the
Ranker companion application for Visualizing the Quiz grades.

To run in Broadcaster directory run 

	>mvn clean compile

followed by 

	>mvn -q exec:java


***NEW FOR VERSION 0.5.0 RANKER REQUIRES MySQL DATABASE CONNECTION

Before using the database features of Ranker you must have a MySql server running.
Create a new database and name it "broadcaster" all lowercase.
Create a user with access to that database with the following credentials and permissions:

USERNAME: admin
PASSWORD: pass

You can now use all features of Broadcaster and Ranker application.


------------------------------------------------------------------------------------------------
USAGE NOTES FOR NEW FEATURES
------------------------------------------------------------------------------------------------

Menu:
1. Broadcast SMS     -> broadcast SMS to contacts (currently requires Twilio credentials) migrating to sever
2. Broadcast Email
3. Add Contact
4. Delete Contact
5. View Contact List
6. Begin Question
7. End Question
8. Current Question Status 
9. Exit


The Begin Quiz option lets you input a question and expected answer and
send it to all contacts in the contact list. You can reply multiple times on your phone.
(This is to allow for testing multiple responses with a limited number of phones, 
however, doing this will alter results in Ranker, therefore, responding once per question is advised. In the final version responses from the same phone number will be overwritten)

While the Quiz is in progress you can turn on the logging function using option "8"
then you reply on your phone responses will be registered on the client-side.

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
-added message generation upon choosing a broadcast type (no longer hardcoded)
-added a management system for contacts.

Version 0.3.0
-added support for sending quizzes via SMS.
-added a new feature that grades student responses.
-added a new feature that displays question statistics based on student responses received.

Version 0.4.0
-added support for grading and recording CUMULATIVE student Quiz progress.
-added an objecFile.dat as a medium for storing student responses.
-created "Ranker" a GUI companion app for visualizing and filtering student results.

Version 0.5.0
-added MySQL database connectivity.
-added the ability to Add quiz sessions to the database.
-added ability to Query database by first name, last name, quiz, grade.
-updated "Ranker" GUI interface to support those features.
