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
				<li>Username:</li>
				<li>Your User ID:</li>
				<li>Email:</li>
				<li>Name:</li>
				<li>Last Name:</li>
				<li>Birthday:</li>
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