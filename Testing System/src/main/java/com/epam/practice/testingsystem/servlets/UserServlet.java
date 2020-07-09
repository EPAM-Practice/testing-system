package com.epam.practice.testingsystem.servlets;

import com.epam.practice.testingsystem.data.dao.DAOFactory;
import com.epam.practice.testingsystem.data.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            resp.sendRedirect("login");
            return;
        }

        User user = (User) userObj;
        int roleId = user.getRole().getId();
        if (roleId < 3) {
            resp.sendRedirect("home");
            return;
        }

        List<User> users = DAOFactory.getUserDAO().findAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("pages/users.jsp").forward(req, resp);
    }
}
