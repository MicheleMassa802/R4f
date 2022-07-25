<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Birthday Survey Page</title>
	<link href="https://fonts.googleapis.com/css2?family=Kdam+Thmor+Pro&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/surveyStyles.css">
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
		<div class="survey-header">
			<h1>Birthday Survey</h1>
		</div>

		<div class="survey-form">
			<!-- GET allows us to get the submitted answers in the link, and upon submission, user is sent to results page -->
			<form action="submit-survey" name="survey" method="get">
				<div class="questions-container">
					<div class="question">
						<label> 
							What is the user ID of the person inviting you to fill this survey?
							<div class="survey-answer-box">
								<input type="text" name="user-id" id="q1" required>
								<!-- the type=text allows us to submit strings, use type "password" to blur the field being entered -->  
							</div>
						</label>
					</div>

					<div class="question">
						<label> 
							What is the calendar ID of the person inviting you to fill this survey?
							<div class="survey-answer-box">
								<input type="text" name="cal-id" id="q2" required>  
							</div>
						</label>
					</div>

					<div class="question">
						<label> 
							What is your name?
							<div class="survey-answer-box">
								<input type="text" name="name" id="q3" required>
							</div>
						</label>
					</div>

					<div class="question">
						<label> 
							What is your last name? 
							<div class="survey-answer-box">
								<input type="text" name="lastname" id="q4" required>
							</div>
						</label>
					</div>

					<div class="question">
						<label> 
							What is your date of birth? [yyyy=mm-dd]
							<div class="survey-answer-box">
								<input type="date" name="bd" id="q5" required>
							</div>	
						</label>
					</div>

					<div class="question">
						<label> 
							What is your instagram username?
							<div class="survey-answer-box">
								<input type="text" name="ig" id="q6">
							</div>
						</label>
					</div>

					<div class="question">
						<label> 
							What is your twitter username?
							<div class="survey-answer-box">
								<input type="text" name="twt" id="q7">
							</div>
						</label>
					</div>

					<div class="question">
						<label> 
							What is your discord username?
							<div class="survey-answer-box">
								<input type="text" name="dsc" id="q8">
							</div>
						</label>
					</div>

				</div>

				<div class="submit-reset-buttons">
					<div class="reset-button">
						<button type="reset"> Reset </button> <!-- the type=reset allows us to reset all fields to blank -->
					</div>
					<div class="submit-button">
						<button type="submit" onclick="submitFunc()"> Submit </button>  <!-- the type=submit allows us to submit by clicking ENTER -->
					</div>
					
				</div>
			</form>
		</div>
	</div>

	
	<!-- Footer Image -->
	<footer>
		<div class="footer-center-img">
			<img src="Images/footer-img.png">
		</div>
	</footer>

</body>

<!-- Submission function js -->
<script src="survey.js"></script>
</html>
