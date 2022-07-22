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
				<h1>Welcome to your home page in R4F, where everything is at your reach</h1>
			</div>
	
			<div class="buttons-content-wrapper"> <!-- 4 main grid buttons -->  
	
				<div class="hover-content-wrapper">
					<div class="hover-img-background" style="background-image:url(Images/bg/calendar.png)"></div>
					<div class="logo-wrapper">
						<a href="calendar.html">	
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
						file:///C:/Users/miche/Documents/IndependentCoding/R4f/R4f_project/src/main/java/Web/survey.html <br>
						Have your friends download the application and use your reference IDs when filling the survey <br>
						Calendar ID: calId <br>
						User ID: userId <br>
					</div>
				</div>
	
				<div class="hover-content-wrapper">
					<div class="hover-img-background" style="background-image:url(Images/bg/birthday.png)"></div>
					<div class="logo-wrapper">
						<img src="Images/logos/birthdaysLogo.png" width="400" height="200">  <!-- Information (bd details) appears upon hover -->
					</div>
					<div class="bd-subtitle">
						Here would go birhtday details:
						<ul>
							<li>Name + Lastname + BdDate</li>
							<li>Name + Lastname + BdDate</li>
							<li>Name + Lastname + BdDate</li>
							<li>Name + Lastname + BdDate</li>
							<li>Name + Lastname + BdDate</li>
						</ul>
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
