<!-- java imports + session to catch from welcome page-->
<%@page import="com.server4.Controllers.BirthdayController, com.server4.Controllers.CalendarController, com.server4.Helpers.UseCaseDateFormatter"%>
<%@page import="java.util.ArrayList, java.util.HashMap, java.time.LocalDate"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Home Page</title>
	<link href="https://fonts.googleapis.com/css2?family=Kdam+Thmor+Pro&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/mainPageStyles.css">
	<link rel="stylesheet" href="css/globalStyles.css">
</head>
<body>
	<!-- catch session variables and setup the needed information -->
	<%
		String username = (String)session.getAttribute("username");
		String userId = (String)session.getAttribute("userId");
		String userBdId = (String)session.getAttribute("bdId");
		String userCalId = (String)session.getAttribute("calendarId");

		// setup needed controllers
        BirthdayController birthdayController = new BirthdayController();
        CalendarController calendarController = new CalendarController(userId);
        HashMap<String, ArrayList<String>> calendar = calendarController.getCalFromId(userCalId).getYearCal();
        
        // get bd info for the day to display upon hover
        String ddmmDate = UseCaseDateFormatter.formatYearlyToMonthly(LocalDate.now().toString());
        ArrayList<String> bdIds = calendar.get(ddmmDate);
        ArrayList<String> bdInfo = new ArrayList<>();
        for(String bdId: bdIds){
            // fetch bdId corresponding info and build the array to return with all the info setup
            bdInfo.add(birthdayController.getBdFromID(bdId).toString());
        }
	%>

	<!-- Header Image -->
    <header>
		<div class="header-center-img">
			<img src="Images/header-img.png">
		</div>
	</header>

    <div class="container">
        <!-- Navigation Bar (4 links + name) -->
        <div class="nav-wrapper">
            <!-- left column division of nav bar -->
            <div class="left-side">
                <div class="nav-link-wrapper active-nav-link">
                    <a href="#" onclick="movePageOnClick('mainPage.jsp')">Home</a>
                </div>
                <div class="nav-link-wrapper">
                    <a href="#" onclick="movePageOnClick('calendar.jsp')">Your Calendar</a>
                </div>
				<div class="nav-link-wrapper">
                    <a href="#" onclick="movePageOnClick('account.jsp')">Your Account</a>
                </div>
            </div>
            <!-- right column division of nav bar -->
            <div class="right-side">
				<div class="nav-link-wrapper">
                    <a href="#" onclick="movePageOnClick('about.jsp')">About Us</a>
                </div>
                <div class="name-text">
                    <div>Arael_00</div>
                </div>
            </div>
        </div>

		<div class="inner-container">
			<div class="index-header">
				<h1>Welcome to your home page in R4F <%=username%>, where everything is at your reach</h1>
			</div>
	
			<div class="buttons-content-wrapper"> <!-- 4 main grid buttons -->  
	
				<div class="hover-content-wrapper">
					<div class="hover-img-background" style="background-image:url(Images/bg/calendar.png)"></div>
					<div class="logo-wrapper">
						<a href="calendar.jsp">	
							<img src="Images/logos/calendarLogo.png" width="400" height="200">  <!-- Link to another page -->
						</a>
					</div>
				</div>
	
				<div class="hover-content-wrapper">
					<div class="hover-img-background" style="background-image:url(Images/bg/survey.png)"></div>
					<div class="logo-wrapper">
						<img src="Images/logos/shareSurveyLogo.png" width="400" height="200">  <!-- Information (survey link and reference Id) appears upon hover -->
					</div>
					<div class="survey-subtitle">
						The survey link to share is: <br>
						Still on the works <br>
						For now, you can have your friends download the application and use your reference IDs when filling the survey through the command line...<br>
						Calendar ID: <%=userCalId%> <br>
						User ID: <%=userId%> <br>
					</div>
				</div>
	
				<div class="hover-content-wrapper">
					<div class="hover-img-background" style="background-image:url(Images/bg/birthday.png)"></div>
					<div class="logo-wrapper">
						<img src="Images/logos/birthdaysLogo.png" width="400" height="200">  <!-- Information (bd details) appears upon hover -->
					</div>
					<div class="bd-subtitle">
						Birthday Details: <br>
						<%
							if (bdInfo.isEmpty()){
								out.println("There are no birthdays registered in your calendar for today :)");
							} else {
								out.println("<ul>");
								for (String bd : bdInfo){
									out.println("<li> " + bd + "</li>");
								}
								out.println("</ul>");
							}


						%>
					</div>

				</div>

				<div class="hover-content-wrapper">
					<div class="hover-img-background" style="background-image:url(Images/bg/logout.png)"></div>
					<div class="logo-wrapper">
						<a href="welcome.html">
							<img src="Images/logos/logoutLogo.png" width="400" height="200">  <!-- Link to another page -->
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Footer Image -->
	<footer>
		<div class="footer-center-img">
			<img src="Images/footer-img.png">
		</div>
	</footer>
</body>

<!-- js code starts here -->
<script>
    const contentItems = document.querySelectorAll('.hover-content-wrapper');  // select all images
    contentItems.forEach(contentItem => {
        // for loop over each image (content item)
        contentItem.addEventListener('mouseover', () => {
            // every time you hover over one of the images, perform the following
            // access first child node in content-item-wrapper = content-img-background, and add img-darken css
            contentItem.childNodes[1].classList.add('img-darken'); 
            contentItem.childNodes[1].classList.remove('img-lighten');  
        })

        contentItem.addEventListener('mouseout', () => {
            // every time you hover out of one of the images, perform the following
            // access first child node in content-item-wrapper = content-img-background, and add img-lighten css
            contentItem.childNodes[1].classList.add('img-lighten');
            contentItem.childNodes[1].classList.remove('img-darken'); 
        })

    })

</script>

<script src="treeManager.js"></script>

</html>
