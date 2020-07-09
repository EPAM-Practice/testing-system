package com.epam.practice.testingsystem.servlets.security;

import com.epam.practice.testingsystem.data.dao.DAOFactory;
import com.epam.practice.testingsystem.data.dto.User;
import com.epam.practice.testingsystem.data.dto.UserRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String passwordConfirm = req.getParameter("password_confirm");

        if (!password.equals(passwordConfirm)) {
            req.setAttribute("err", "Passwords don't match");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/register.jsp");
            requestDispatcher.forward(req, resp);
            return;
        }

        try {
            UserRole role = DAOFactory.getUserRoleDAO().find(1);
            User user = User.builder()
                    .name(login)
                    .passwordHash(HashCalculator.getPasswordHash(password))
                    .role(role)
                    .build();
            DAOFactory.getUserDAO().add(user);
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            String redirPath = "/home";
            resp.sendRedirect(super.getServletContext().getContextPath() + redirPath);
        }
        catch (RuntimeException e) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/register.jsp");
            req.setAttribute("err", "Cannot register with specified credentials. Try again with another username.");
            requestDispatcher.forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            String redirPath = "/home";
            resp.sendRedirect(super.getServletContext().getContextPath() + redirPath);
        }
        else {
            req.setAttribute("err", "");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/register.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
