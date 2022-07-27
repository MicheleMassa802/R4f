package com.server4.Servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IncreaseMonthServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {        
        
        // get session's current displayMonth attribute and update it, then show updated calendar.jsp
        HttpSession session = request.getSession();
        int displayMonth = (Integer)session.getAttribute("displayMonth");

        if (displayMonth == 12){
            // cant increase any more
            session.setAttribute("displayMonth", displayMonth);
        } else {
            // keep going up
            session.setAttribute("displayMonth", displayMonth++);
        }
        System.out.println("Changing to month: " + displayMonth);
        RequestDispatcher rd = request.getRequestDispatcher("calendar.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
}
