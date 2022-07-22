package com.server4.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.server4.Controllers.BirthdayController;
import com.server4.Controllers.CalendarController;
import com.server4.Controllers.UserController;
import com.server4.Helpers.UseCaseDateFormatter;

public class MainPageServlet extends HttpServlet{
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // once login sends a request to the main servlet, use all the
        // passed session information to modify the page
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        String userId = (String) session.getAttribute("userId");
        String userBdId = (String) session.getAttribute("bdId");
        String userCalId = (String) session.getAttribute("calendarId");
        
        // setup needed controllers
        BirthdayController birthdayController = new BirthdayController();
        CalendarController calendarController = new CalendarController(userId);
        HashMap<String, ArrayList<String>> calendar = calendarController.getCalFromId(userCalId).getYearCal();
        
        String ddmmDate = UseCaseDateFormatter.formatYearlyToMonthly(LocalDate.now().toString());
        ArrayList<String> bdIds = calendar.get(ddmmDate);
        ArrayList<String> bdInfo = new ArrayList<>();
        for(String bdId: bdIds){
            // fetch bdId corresponding info and build the array to return with all the info setup
            bdInfo.add(birthdayController.getBdFromID(bdId).toString());
        }

        response.sendRedirect("mainPage.jsp");
        // get bd info for the day to display upon hover
        // bd info stored in bdInfo, iterate over it to show it in the .jsp list of mainPage.jsp

        // use cal and user ids to display when sharing survey



        
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
