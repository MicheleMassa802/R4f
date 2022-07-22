package com.server4.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.server4.Controllers.UserController;

public class LoginServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // check inputs for login using TreeManager
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserController userController = new UserController();
        userController.executeLoginUser(username, password, false);

        String userId = userController.userId;

        if (userId == null){
            // bad login
            System.out.println("Please try logging in again!");
            // stay in this page

        } else {
            // startup TomCat session
            HttpSession session = request.getSession();
            session.setAttribute("username", userController.username);
            session.setAttribute("userId", userId);
            session.setAttribute("bdId", userController.userBdId);
            session.setAttribute("calendarId", userController.userCalendarId);
            // move to next page passing information through session
            System.out.println("Redirecting to main!");
            response.sendRedirect("main");
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
