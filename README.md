# R4f
R4f is a website that works as a birthday tracker for people who are close to you, making use of a Java backend deployed through a Tomcat server, MySQL database, HTML and CSS front end. 

&nbsp;
&nbsp;
&nbsp;

# Key Features:
- User Authentication.
- User and respective calendar profiles.
- Birthday management through your calendar.
- Email-based notification system on birthday dates registered on your calendar.
- Birthday surveys to share with your friends such that they can fill your calendar for you with their respective birthday info.
- Daily preview for birthdays.
- Birthday message recommendation system to send to people.

&nbsp;
&nbsp;
&nbsp;

# Instructions for accessing the application:

## CMDLine Version:

If you are anything like me and appreciate some good old fashioned command line action [so if you are preferably used to coding and compiling projects], then you can go ahead and clone the repo, in there follow the directories in the following way: R4F_project -> Project4 -> project4.

This project4 folder is the one that contains the src folder where the actual application code resides, so you can use your IDE of preference to open the folder, navigate into: src -> main -> java -> com -> server4 -> MainPage, and once in the MainPage package, open up the Main.java file, run this file in order to experience R4F through command line prompts, in a very raw fashion.

## UI-based Version:

If your want to look at the website and the much prettier UI this brings, then you can deploy the app using a Tomcat server the way I have been doing it during the development of this project.

I'll provide the steps for this TomCat deployment using VSCode, but I'm sure other IDEs will have comparable steps:

Prep: open the project by opening the folder project4, which can be found by navigating your cloned location following: R4F_project -> Project4 -> project4.

1. Install the Community Server Connectors extension on VSCode, this will allow you to download the Tomcat Server where the webapp will be deployed.
2. Under the servers tab that will appear on your left sidebar, click the + button to add/download a server, click yes, and look for "apache-tomcat-9.0.41" (this is the one I've used and tested, so it will work, but similar ones may work too).
3. Once installed, right click on the tomcat server icon under the servers tab on your left navigation bar, and start up the server.
4. Since this is a maven project, now its time to package it up and create the .war file to deploy project4. So go over to the MAVEN tab on your left navigation bar in VSCode, go into: project4 Maven Webapp -> Lifecycle, and under the options, click the run arrow for the "clean" option, and then run the "package" option too.
5. Now a target folder will have been created within your PROJECT4 folder (the folder on which the project is opened), along with other folders such as the src and .vscode folders which were already there.
6. Go into this target folder, and right click on the subfolder with the name "project4", then select the option "Debug on Server" -> then select the installed tomcat server -> and then "no" when it asks you to modify optional server parameters.
7. Lastly, once the project is running on the server, right click the installed server under the servers window, select the "server actions" option -> "show in browser" -> "http://localhost:8080/project4/".
8. After this, a new tab should be opened in your preferred web browser, where R4F will be fully running with its beautiful UI. From here you have access to all the R4F features, so enjoy if you were able to make it through my terrible instruction process...

&nbsp;
&nbsp;
&nbsp;

# Using the R4F website

Ever since Heroku got rid of their free plan for hosting websites, I've had to take down the published build of this project, and trying to get it setup someplace else is quite the nightmare given the tech-stack and structure of this project as you might understand as its server THROUGH JSP (lol).

So it is not in my plans to re-deploy this project publicly, if you want to experience it, I'd recommend doing so through the instructions above to deploy it through your own Tomcat server.

&nbsp;
&nbsp;
&nbsp;

# TechStack:

- This application is obviously divided into its front end and its back end.
- On the front end side, everything has been made from the ground up through HTML5 and CSS pages served through JSP.
- For the back end, I've gone with a simple (no framework) Java project connected to a MySQL database.

&nbsp;
&nbsp;
&nbsp;

# Architecture:

Throughout the project I tried to stick as much as I could to clean architecture principles from Robert C. Marting and his book "Clean Architecture: A Craftsman's Guide to Software Structure and Design" 
&nbsp;
&nbsp;
&nbsp;

# Important Notes:

Note, all of the files in this project have been fully developed by me, Michele Massa, if you are interested in talking to me about coding, why I did something the way I did, or want to use a file, please ask for permission by shooting an email at: michelemassa802@gmail.com. It takes no effort and is a little nicer than just stealing the code. 

All of the files under this project are copyrighted under copyright (c) 2022 Michele Massa. So please, ask for permission if you want to use my files.

Aside from copying files, I am also not going to be open to collaboration in the project since it's more of a solo development thing that I really wanted to experience, if you have any recommendations as far as making it better however, or want to hire me to code something for you, once again shoot me an email at: michelemassa802@gmail.com.

&nbsp;
&nbsp;
&nbsp;

# THANK YOU

Just making it all the way down here is special enough to me, even if you end up not using R4F, I wanted to thank you for visiting my project page, it really makes the 200+ hours spent between learning new coding languages, learning about database usage, learning about website making and server usage, and obviously, coding the actual thing, really worth it.

This has been Michele, I hope you have a great day!
