package com.epam.practice.testingsystem.servlets;

import com.epam.practice.testingsystem.data.dao.DAOFactory;
import com.epam.practice.testingsystem.data.dto.Attempt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/attempts")
public class AttemptServlet extends HttpServlet {
    private void doGetItem(int id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Attempt attempt = DAOFactory.getAttemptDAO().find(id);
        req.setAttribute("attempt", attempt);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/attempt.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null)
            doGetItem(Integer.parseInt(id), req, resp);
    }
}
