package com.epam.practice.testingsystem.servlets;

import com.epam.practice.testingsystem.data.dao.DAOFactory;
import com.epam.practice.testingsystem.data.dto.Attempt;
import com.epam.practice.testingsystem.data.dto.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            resp.sendRedirect(super.getServletContext().getContextPath() + "/login");
        }
        else {
            User user = (User) userObj;
            List<Attempt> attempts = DAOFactory.getAttemptDAO().findAllByUser(user.getId());
            req.setAttribute("attempts", attempts);
            String jspPath = "pages/home.jsp";
            req.getRequestDispatcher(jspPath).forward(req, resp);
        }
    }
}
