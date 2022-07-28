package com.server4.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;

import com.server4.Controllers.BirthdayController;
import com.server4.Controllers.CalendarController;

public class SubmitSurveyServlet extends HttpServlet {
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {        
        
        // get inputs for survey answers
        String userId = request.getParameter("user-id");
        String calendarId = request.getParameter("cal-id");
        String firstname = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String birthday = request.getParameter("bd");
        String ig = request.getParameter("ig");
        String twt = request.getParameter("twt");
        String dsc = request.getParameter("dsc");

        // create the visitor controllers
        BirthdayController visitorBdController = new BirthdayController();
        CalendarController visitorCalController = new CalendarController(userId);

        // create visitor's bd object to add to calendar
        visitorBdController.executeCreateSML(ig, twt, dsc);
        visitorBdController.executeCreateBirthday(birthday, firstname, lastname);

        // get the date in ddmm format for this year and add bd object to referenced calendar
        String dateArray[] = birthday.split("-");
        String currYear = LocalDate.now().toString().split("-")[0];
        String yearDate = currYear + "-" + dateArray[1] + "-" + dateArray[2];

        visitorCalController.setCalendarEventManager(calendarId);
        visitorCalController.modifyCalendarEvent(true, yearDate, visitorBdController.bdId);

        // load index.jsp (send back to welcome menu)
        response.sendRedirect("index.jsp");
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
