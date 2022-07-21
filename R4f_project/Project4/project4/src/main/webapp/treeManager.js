// The class connected to all the webpages along the main tree of operation actions, in charge of using the MainPage.TreeManager.java class to move the user along the
// R4F website while keeping all needed entities active, quering for backend info to display, and moving the user through the html pages

// From the very beginning, show the welcome page
//window.location.href = "welcome.html";


/**
 * The function in charge of moving the user from page to page through the nav bar and other buttons
 * @param {The .html link to move the user to (a string surrounded with '')} htmlLink 
 */
function movePageOnClick(jspLink){
    window.location.href = jspLink;
}



var TreeManagerClass = Java.type("main.java.MainPage.TreeManager");  // class representing the TreeManager java object
TreeManagerClass.TreeManager();  // constructor
var loggedIn = false;  // the variable representing whether the user is logged in or not


/**
 * Function in charge of calling the login procedure
 */
function loginFunc() {
    var username = document.getElementById("username").value;
    var pswd = document.getElementById("password").value;
	var pswd = document.getElementById("password").value;
	var firstTime = document.getElementById("first-time").value == 1;

    TreeManagerClass.logUserIn(username, pswd, firstTime);
    loggedIn = true;
    
    if(loggedIn){
        window.location.href = "index.html";
    } else {
        alert("Information entered could not be validated, please try again.\nMake sure all fields have been filled!")
    }
}


/**
 * Function in charge of calling the registering procedure
 */
function regFunc(){
    var username = document.getElementById("username2").value;
    var email = document.getElementById("email").value;
    var pswd = document.getElementById("password2").value;
    var name = document.getElementById("name").value;
    var lName = document.getElementById("last-name").value;
    var bd = document.getElementById("birthday").value;
    var ig = document.getElementById("ig").value;
    var twt = document.getElementById("twt").value;
    var dsc = document.getElementById("dsc").value;
    var notiType = document.getElementById("noti-type").value;
    console.log("Registering...");
    TreeManagerClass.registerUser(username, email, pswd, name, lName, bd, ig, twt, dsc, parseInt(notiType));
    
    // move onto same welcome page for user to log in
}

