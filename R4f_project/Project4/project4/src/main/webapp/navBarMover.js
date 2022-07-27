// The class connected to all the webpages in order to share teh movePageOnClick function

/**
 * The function in charge of moving the user from page to page through the nav bar and other buttons
 * @param {The .html link to move the user to (a string surrounded with '')} htmlLink 
 */
function movePageOnClick(jspLink){
    window.location.href = jspLink;
}