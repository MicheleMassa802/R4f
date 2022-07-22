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
					<button> &lt; </button>
				</div>
				<div class="month-text">Current Month</div>
				<div class="forward-button">
					<button> &gt; </button>
				</div>
			</div>

			<div class="page-content-wrapper">
				<!-- The actual calendar display being 9x4 to display 31 dates in 36 possible slots -->
				<div class="calendar-wrapper">
					<!-- The following are the divs for all 36 calendar cell dates (only filled up to 31) -->
					<div class="date-cell">
						<div class="date-text">1:</div>
						<div class="date-content">Content for a date cell (Comma separated Bd ids displayed here)</div>
					</div>

					<div class="date-cell">
						<div class="date-text">2:</div>
						<div class="date-content">Content for a date cell</div>
					</div>
					
					<div class="date-cell">
						<div class="date-text">3:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">4:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">5:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">6:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">7:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">8:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">9:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">10:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">11:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">12:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">13:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">14:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">15:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">16:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">17:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">18:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">19:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">20:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">21:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">22:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">23:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">24:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">25:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">26:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">27:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">28:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">29:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">30:</div>
						<div class="date-content">Content for a date cell</div>
					</div>

					<div class="date-cell">
						<div class="date-text">31:</div>
						<div class="date-content">Content for a date cell</div>
					</div>
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