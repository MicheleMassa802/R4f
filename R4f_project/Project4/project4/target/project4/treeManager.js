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