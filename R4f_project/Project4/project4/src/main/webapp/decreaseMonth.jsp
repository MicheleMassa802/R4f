<%
    // decrease session month and redirect to calendar page
    int displayMonth = (Integer)session.getAttribute("displayMonth");
    if (displayMonth == 1){
        // cant decrease any more
        session.setAttribute("displayMonth", displayMonth);
    } else {
        // keep going down
        session.setAttribute("displayMonth", displayMonth--);
    }
    System.out.println("Changing to month: " + displayMonth);
%>

<button onclick="window.location.href='calendar.jsp'">