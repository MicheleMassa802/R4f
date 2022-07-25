package com.server4.Servlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.server4.Controllers.BirthdayController;
import com.server4.Controllers.CalendarController;
import com.server4.Controllers.UserController;

public class RegisterServlet extends HttpServlet {
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // get inputs
        String username = request.getParameter("username2");
        String email = request.getParameter("email");
        String password = request.getParameter("password2");
        String firstname = request.getParameter("name");
        String lastname = request.getParameter("last-name");
        String birthday = request.getParameter("birthday");
        String ig = request.getParameter("ig");
        String twt = request.getParameter("twt");
        String dsc = request.getParameter("dsc");
        int notiType = Integer.parseInt(request.getParameter("noti-type"));

        // upload inputs (register user, bd and calendar)
        UserController userController = new UserController();
        userController.executeCreateUser(username, email, password, firstname, lastname, notiType);
        String userId = userController.userId;

        BirthdayController birthdayController = new BirthdayController();
        birthdayController.executeCreateSML(ig, twt, dsc);
        birthdayController.executeCreateBirthday(birthday, firstname, lastname);
        String userSmlId = birthdayController.smlId;
        String userBdId = birthdayController.bdId;

        

        if (userId == null || userBdId == null || userSmlId == null){
            // bad registration
            System.out.println("Please try registering again, there has been an error!");
            // stay in this page

        } else {
            // create calendar and return to index.jsp for user to now login
            CalendarController calendarController = new CalendarController(userId);
            calendarController.executeCreateCalendar();
            String userCalendarId = calendarController.calendarId;
            calendarController.setCalendarEventManager(userCalendarId);
            
            // link related calendar, bd and sml ids to account
            userController.firstLoginFollowUp(username, userBdId, userCalendarId, userSmlId);

            // alert that the user now needs to login and reset current page

            PrintWriter out = response.getWriter();
            out.println("<html><body onload=\"alert('Now that you have registered, you can proceed to login')\"></body></html>");
            // load index.jsp again for user to login (upon login, login servlet is called)
            response.sendRedirect("index.jsp");
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
