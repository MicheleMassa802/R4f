<%
    // increase session month and redirect to calendar page
    int displayMonth = (Integer)session.getAttribute("displayMonth");
    if (displayMonth == 12){
        // cant increase any more
        session.setAttribute("displayMonth", displayMonth);
    } else {
        // keep going up
        session.setAttribute("displayMonth", displayMonth++);
    }
    System.out.println("Changing to month: " + displayMonth);
    response.sendRedirect("calendar.jsp");
%>