<!-- java imports + session to catch from welcome page-->
<%@page import="com.server4.Controllers.BirthdayController, com.server4.Controllers.UserController, com.server4.Helpers.UseCaseDateFormatter"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Your R4F Account</title>
    <link href="https://fonts.googleapis.com/css2?family=Kdam+Thmor+Pro&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/accountStyles.css">
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
        UserController userController = new UserController();
        BirthdayController birthdayController = new BirthdayController();

        ArrayList<String> userData = userController.getUserFromId(userId);
        String bd = birthdayController.getBdFromID(userBdId).getBirthdayString();
        userData.add(bd);
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
                <div class="nav-link-wrapper">
                    <a href="#" onclick="movePageOnClick('mainPage.jsp')">Home</a>
                </div>
                <div class="nav-link-wrapper">
                    <a href="#" onclick="movePageOnClick('calendar.jsp')">Your Calendar</a>
                </div>
				<div class="nav-link-wrapper active-nav-link">
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

		<div class="user-info-content">
			<h1>Your Account Details:</h1>
			<ul>
				<li>Username:</li> <div class="account-details-info"><%=userData.get(1)%> </div>
				<li>Your User ID:</li> <div class="account-details-info"><%=userData.get(0)%> </div>
				<li>Email:</li> <div class="account-details-info"><%=userData.get(9)%> </div>
				<li>Name:</li> <div class="account-details-info"><%=userData.get(3)%> </div>
				<li>Last Name:</li> <div class="account-details-info"><%=userData.get(4)%> </div>
				<li>Birthday:</li> <div class="account-details-info"><%=userData.get(10)%> </div>
			</ul>
		</div>
	</div>

    <!-- Footer Image -->
	<footer>
		<div class="footer-center-img">
			<img src="Images/footer-img.png">
		</div>
	</footer>
</body>

<script src="treeManager.js"></script>
</html>
