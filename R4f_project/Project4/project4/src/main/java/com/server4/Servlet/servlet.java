package com.server4.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api")
public class servlet extends HttpServlet {
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // to get input from forms do:
        // request.getParameter(<nameOfHtmlElementDiv>) and parse them if they are ints, or take them as strings
        // to take to another page, use: response.redirect("file.jsp");
        try {
            out.println("<html>");
            out.println("<h1>HOWDY all from servlet</h1>");
            out.println("<h1> Servlet NewServlet at " + request.getContextPath() + "</h1>");
            String user = request.getParameter("user");
            out.println("<h2> Welcome " + user + "</h2>");
            out.println("</html>");
        } catch (Exception e) {
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