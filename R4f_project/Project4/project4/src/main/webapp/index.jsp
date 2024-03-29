<!-- java imports-->
<%@page import="com.server4.MainPage.MainScheduler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Welcome Page</title>
	<link href="https://fonts.googleapis.com/css2?family=Kdam+Thmor+Pro&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/indexStyles.css">
	<link rel="stylesheet" href="css/globalStyles.css">
</head>
<body>
	<!-- Set Scheduler upon launch of page on server -->
	<%-- <% 
		Still figuring out how to trigger scheduler only once
		MainScheduler.setScheduler();
	%> --%>

	<!-- Header Image -->
    <header>
		<div class="header-center-img">
			<img src="Images/header-img.png">
		</div>
	</header>

	<div class="container">
		<div class="welcome-header">
			<h1>Welcome to R4F, your best friend for remembering the special days!</h1>
		</div>

		<div class="three-column-wrapper">

			<!-- Left side buttons -->
			<div class="buttons-content-wrapper"> <!-- 3 main left grid buttons -->  

				<div class="button-content-wrapper">
					<div class="button-img-background" style="background-image:url(Images/miniBg/login.png)"></div>

					<div class="img-text-wrapper">
						<div class="logo-wrapper">
							<img id="button-1" src="Images/miniLogos/loginLogo.png"> 
						</div>
					</div>
				</div>

				<div class="button-content-wrapper">
					<div class="button-img-background" style="background-image:url(Images/miniBg/register.png)"></div>

					<div class="img-text-wrapper">
						<div class="logo-wrapper">
							<img id="button-2" src="Images/miniLogos/registerLogo.png"> 
						</div>
					</div>
				</div>

				<div class="button-content-wrapper">
					<div class="button-img-background" style="background-image:url(Images/miniBg/survey.png)"></div>

					<div class="img-text-wrapper">
						<div class="logo-wrapper">
							<a href="survey.jsp">
								<img src="Images/miniLogos/fillSurveyLogo.png">
							</a>  
						</div>
					</div>
				</div>

			</div>

			<div class="forms-content-wrapper">
				<!-- Login -->
				<div class="left-form-content-wrapper">
					<form action="submit-login" name="login" method="get">
						<div class="left-questions-container">
							
							<div class="question">
								<label> 
									Username:
									<div class="survey-answer-box">
										<input type="text" name="username" id="username" required>
									</div> 
								</label>
							</div>

							<div class="question">
								<label> 
									Password:
									<div class="survey-answer-box">
										<input type="password" name="password" id="password" required>
									</div> 
								</label>
							</div>
							<!--
							<div class="question">
								<label> 
									Is this your first time ever logging in? [check if true]
									<div class="survey-answer-box">
										False <input type="range" name="first-time" id="first-time" min="0" max="1" value="true" required> True
									</div>
								</label>
							</div>	
							-->						
						</div>

						<div class="submit-login-button">
							<button type="submit"> Submit Login Form </button>
						</div>
					</form>
					
				</div>

				<!-- Register -->
				<div class="right-form-content-wrapper">
					<form action="submit-reg" name="register" method="get">
						<div class="right-questions-container">
							<div class="question">
								<label> 
									Username:
									<div class="survey-answer-box">
										<input type="text" name="username2" id="username2" required> 
									</div> 
								</label>
							</div>

							<div class="question">
								<label> 
									Email:
									<div class="survey-answer-box">
										<input type="email" name="email" id="email" required>
									</div>
								</label>
							</div>

							<div class="question">
								<label> 
									Password:
									<div class="survey-answer-box">
										<input type="password" name="password2" id="password2" required>
									</div>	
								</label>
							</div>

							<div class="question">
								<label> 
									First Name:
									<div class="survey-answer-box">
										<input type="text" name="name" id="name" required>
									</div> 
								</label>
							</div>

							<div class="question">
								<label> 
									Last Name:
									<div class="survey-answer-box">
										<input type="text" name="last-name" id="last-name" required>
									</div>  
								</label>
							</div>

							<div class="question">
								<label> 
									Birthday:
									<div class="survey-answer-box">
										<input type="date" name="birthday" id="birthday" required>
									</div>  
								</label>
							</div>

							<div class="question">

								<label> 
									Instagram @:
									<div class="survey-answer-box">
										<input type="text" name="ig" id="ig" value="none">
									</div>
								</label>

								<label> 
									Twitter @:
									<div class="survey-answer-box">
										<input type="text" name="twt" id="twt" value="none">
									</div>  
								</label>

								<label> 
									Discord Username:
									<div class="survey-answer-box">
										<input type="text" name="dsc" id="dsc" value="none">
									</div>
								</label>
							</div>

							<div class="question">
								<label> 
									How many times would you like to be notified of a birthday?
									<div class="survey-answer-box">
										<input type="number" name="noti-type" id="noti-type" min="1" max="3" required>
									</div> 
								</label>
							</div>
							
						</div>

						<div class="submit-reg-button">
							<button type="submit"> Submit Registration Form </button>
						</div>
					</form>
					
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
	
	// button effects
    const contentItems = document.querySelectorAll('.button-content-wrapper');  // select all images
	
    contentItems.forEach(contentItem => {
        // for loop over each image (content item)
        contentItem.addEventListener('mouseover', () => {
            // every time you hover over one of the images, perform the following
            // access first child node in button-content-wrapper = button-img-background, and add img-darken css
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
	
	
	// appearing of forms
	var button1 = document.getElementById('button-1'); // login
	var button2 = document.getElementById('button-2'); // sign-up
	var loginContent = document.getElementsByClassName('left-form-content-wrapper');  // login from
	var regContent = document.getElementsByClassName('right-form-content-wrapper');  // registration from

	button1.onclick = function(){
		loginContent[0].style.opacity=100;
		regContent[0].style.opacity=0;
	
	}
	button2.onclick = function(){
		regContent[0].style.opacity=100;
		loginContent[0].style.opacity=0;
	}
</script>

</html>