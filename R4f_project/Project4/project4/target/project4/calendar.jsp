<!-- java imports + session to catch from welcome page-->
<%@page import="com.server4.Controllers.BirthdayController, com.server4.Controllers.CalendarController, com.server4.Helpers.UseCaseDateFormatter"%>
<%@page import="java.util.ArrayList, java.util.HashMap, java.time.LocalDate"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Your Birthday Calendar</title>
	<link href="https://fonts.googleapis.com/css2?family=Kdam+Thmor+Pro&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/calendarStyles.css">
	<link rel="stylesheet" href="css/globalStyles.css">
</head>
<body>
	<!-- Function (declared outside of service method) for getting fetching the ordered month calendar hashmap and printing it out -->
	<%!
		public void getMonthCalendar(JspWriter out, int monthToDisplay, String userId, String calId) throws Exception {
			// use the user and cal ids to get the calendar
			// setup needed controllers
        	BirthdayController birthdayController = new BirthdayController();
        	CalendarController calendarController = new CalendarController(userId);
        	HashMap<String, ArrayList<String>> yearCalendar = calendarController.getCalFromId(calId).getYearCal();

			// get the month double digit string
			String monthNumberStr;
			if (monthToDisplay < 10){
				monthNumberStr = "0" + Integer.toString(monthToDisplay);
			} else {
				monthNumberStr = Integer.toString(monthToDisplay);
			}

			// print calendar in order for specific month
			String calDay;
			ArrayList<String> idList;
			int i;
			for (i = 1; i < 32; i++){
				if (i < 10){
					calDay = "0" + Integer.toString(i) + "-" + monthNumberStr;
				} else {
					calDay = Integer.toString(i) + "-" + monthNumberStr;
				}
				// get or default from yearCalendar hashmap the list of ids corresponding to the date
				idList = yearCalendar.getOrDefault(calDay, new ArrayList<>());
				out.println("<div class='date-cell'>");
				out.println("	<div class='date-text'>" + calDay.charAt(0) + calDay.charAt(1) + ":</div>");
				out.println("	<div class='date-content'>" + yearCalendar.get(calDay).toString() + "</div>");
				out.println("</div>");
			}
		}
	%>

	<!-- catch session variables (needed info setup through function) -->
	<%
		String username = (String)session.getAttribute("username");
		String userId = (String)session.getAttribute("userId");
		String userCalId = (String)session.getAttribute("calendarId");
		int displayMonth = (Integer)session.getAttribute("displayMonth");
		String displayMonthStr = UseCaseDateFormatter.convertIntToMonth(displayMonth);
		System.out.println("This month: " + displayMonth);
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
                <div class="nav-link-wrapper active-nav-link">
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

			<h1>Calendar View:</h1>
			Hover over a date on this calendar view to show the details of people with birthdays on that date.
			<!-- Like a nav bar to switch between months -->
			<div class="month-scroller-bar">
				<div class="back-button">
					<button onclick="window.location.href='decreaseMonth.jsp'"> &lt; </button>
				</div>
				<div class="month-text"><%=displayMonthStr%></div>
				<div class="forward-button">
					<button onclick="window.location.href='inc-month'"> &gt; </button>
				</div>
			</div>

			<div class="page-content-wrapper">
				<!-- The actual calendar display being 9x4 to display 31 dates in 36 possible slots -->
				<div class="calendar-wrapper">
					<!-- use jsp to create the needed date cell divs for the available days of the month (through the created function) -->
					<%getMonthCalendar(out, displayMonth, userId, userCalId);%>
				</div>
				<!-- Area displaying text containning the basic birthday info for the bds of the date being hovered in the calendar -->
				<div class="date-bd-displayer-wrapper">
					<h2>Date View:</h2>
					<ul>
						<li>Name, LastName, IG, Twitter, Discord</li>
						<li>Name, LastName, IG, Twitter, Discord</li>
						<li>Name, LastName, IG, Twitter, Discord</li>
						<li>Name, LastName, IG, Twitter, Discord</li>
						<li>Name, LastName, IG, Twitter, Discord</li>
						<li>Name, LastName, IG, Twitter, Discord</li>
						<li>Name, LastName, IG, Twitter, Discord</li>
						<li>Name, LastName, IG, Twitter, Discord</li>
					</ul>
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

<script src="treeManager.js"></script>
</html>
