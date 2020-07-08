package com.epam.practice.testingsystem.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(super.getServletContext().getContextPath() + "/login");
        }
        else {
            String jspPath = "pages/home.jsp";
            req.getRequestDispatcher(jspPath).forward(req, resp);
        }
    }
}
