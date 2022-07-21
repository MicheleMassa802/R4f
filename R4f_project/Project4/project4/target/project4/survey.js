// The page in charge of managing the MainPage.SurveyManager.java class in order to feed and execute the correct steps to conclude a survey submission

function submitFunc(){
    // get values
    var q1 = document.getElementById("q1").value;
    var q2 = document.getElementById("q2").value;
    var q3 = document.getElementById("q3").value;
    var q4 = document.getElementById("q4").value;
    var q5 = document.getElementById("q5").value;
    var q6 = document.getElementById("q6").value;
    var q7 = document.getElementById("q7").value;
    var q8 = document.getElementById("q8").value;

    // create birthday object with the given answers
    var bdControllerClass = Java.type("main.java.Controllers.BirthdayController");
    bdControllerClass.bdController();
    bdControllerClass.executeCreateSML(q6, q7, q8);
    bdControllerClass.executeCreateBirthday(q5, q3, q4);

    // create calendar object from ids provided
    var calendarControllerClass = Java.type("main.java.Controllers.CalendarController");
    calendarControllerClass.calendarController(q1);
    calendarControllerClass.setCalendarEventManager(q2);

    // add bdid to calendar
    calendarControllerClass.modifyCalendarEvent(true, q5, bdControllerClass.bdId);
}