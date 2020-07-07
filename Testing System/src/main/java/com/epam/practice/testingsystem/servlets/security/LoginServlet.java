package com.epam.practice.testingsystem.servlets.security;

import com.epam.practice.testingsystem.data.dao.DAOFactory;
import com.epam.practice.testingsystem.data.dto.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            String redirPath = "/home";
            resp.sendRedirect(super.getServletContext().getContextPath() + redirPath);
        }
        else {
            req.setAttribute("login_error", false);
            req.getRequestDispatcher("pages/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        session.setAttribute("user", null);
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String passwordHash = HashCalculator.getPasswordHash(password);

        User user = DAOFactory.getUserDAO().findUserByCred(login, passwordHash);
        if (user != null) {
            session.setAttribute("user", user);
            String redirPath = "/home";
            resp.sendRedirect(super.getServletContext().getContextPath() + redirPath);
        }
        else {
            req.setAttribute("login_error", true);
            req.getRequestDispatcher("pages/login.jsp").forward(req, resp);
        }
    }
}
