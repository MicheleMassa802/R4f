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
	
	<%
		// catch session variables
		String username = (String)session.getAttribute("username");
		String userId = (String)session.getAttribute("userId");
		String userCalId = (String)session.getAttribute("calendarId");
		int monthToDisplay;


		// create year calendar Hashmap for page to use
		// use the user and cal ids to get the calendar
		// setup needed controllers
		BirthdayController birthdayController = new BirthdayController();
		CalendarController calendarController = new CalendarController(userId);
		HashMap<String, ArrayList<String>> yearCalendar = calendarController.getCalFromId(userCalId).getYearCal();


		// to determine the month to show, check if a value has been given to the month-to-display attribute
		// if month-to-display attribute has been set, use that, else, use defaultMonth from session
		String monthSelected = request.getParameter("month-to-display");
		if (monthSelected == null){
			// show calendar with default month
			monthToDisplay = (Integer)session.getAttribute("defaultMonth");
		} else {
			monthToDisplay = Integer.parseInt(monthSelected);
		}
		String displayMonthStr = UseCaseDateFormatter.convertIntToMonth(monthToDisplay);
	%>

	<!-- Function (declared outside of service method) for fetching the ordered month calendar hashmap and printing it out (by filtering the year calendar) -->
	<%!
		public void getMonthCalendar(JspWriter out, int monthToDisplay, HashMap<String, ArrayList<String>> yearCalendar) throws Exception {
			
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

				if (yearCalendar.containsKey(calDay)){
					// only print if date exists within year calendar
					out.println("<div class='date-cell'>");
					out.println("	<div class='date-text'>" + calDay.charAt(0) + calDay.charAt(1) + ":</div>");
					out.println("	<div class='date-content'>");
					getDateBds(out, calDay, yearCalendar);
					out.println("	</div>");
					out.println("</div>");
				}
				
			}
		}
	%>

	<!-- Function (declared outside of service method) for fetching the bd information corresponding to a specific date printing it out (date is dd-mm)-->
	<%!
		public void getDateBds(JspWriter out, String dateToDisplay, HashMap<String, ArrayList<String>> yearCalendar) throws Exception {
			
			// create a bd controller to fetch bdId information
			BirthdayController bdController = new BirthdayController();

			// using the calendar, get the bd id list corresponding to the given date
        	ArrayList<String> bdIdList = yearCalendar.get(dateToDisplay);
			out.println("<ul>");
			if (bdIdList.isEmpty()){
				// print default message
				out.println("	<li> There are no birthdays for the date you have selected!</li>");
			} else {
				for (String bdId : bdIdList){
				out.println("	<li>" + bdController.getBdFromID(bdId).toString() + "</li>");
				}
			}
			out.println("</ul>");
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
			<!-- Selector for months (showing current as default) -->
			<form class="month-form" action="calendar.jsp" method="GET">
				<div class="month-text">Current Month Displayed:</div>
				<div class="month-value"> <%=displayMonthStr%> </div>
				<div class="select-month-text"> Select Month To View:</div>
				<div class="month-selector">
					<select name="month-to-display" id="month-to-display">
						<option value="1"> January </option>
						<option value="2"> February </option>
						<option value="3"> March </option>
						<option value="4"> April </option>
						<option value="5"> May </option>
						<option value="6"> June </option>
						<option value="7"> July </option>
						<option value="8"> August </option>
						<option value="9"> September </option>
						<option value="10"> October </option>
						<option value="11"> November </option>
						<option value="12"> December </option>
					</select>
				</div>
				<div class="select-month-button">
					<button type="submit"> View Month </button>
				</div>
			</form>

			<div class="page-content-wrapper">
				<!-- The actual calendar display being 9x4 to display 31 dates in 36 possible slots -->
				<div class="calendar-wrapper">
					<!-- use jsp to create the needed date cell divs for the available days of the month (through the created function) -->
					<%getMonthCalendar(out, monthToDisplay, yearCalendar);	%>
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

<script src="navBarMover.js"></script>
</html>
