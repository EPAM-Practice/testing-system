package com.epam.practice.testingsystem.servlets;

import com.epam.practice.testingsystem.data.dao.DAOFactory;
import com.epam.practice.testingsystem.data.dto.Deadline;
import com.epam.practice.testingsystem.data.dto.Test;
import com.epam.practice.testingsystem.data.dto.UniversityGroup;
import com.epam.practice.testingsystem.data.dto.User;
import com.epam.practice.testingsystem.servlets.pojo.ViewGroup;
import com.epam.practice.testingsystem.servlets.pojo.ViewTest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            resp.sendRedirect(super.getServletContext().getContextPath() + "/login");
            return;
        }

        User user = (User) userObj;
        List<ViewGroup> viewGroups;
        if (user.getRole().getId() == 3)
            viewGroups = getViewGroupsAdmin();
        else {
            List<UniversityGroup> groups = DAOFactory.getUserDAO().findAssignedUniversityGroups(user);
            viewGroups = getViewGroups(groups);
        }

        req.setAttribute("view_groups", viewGroups);
        req.getRequestDispatcher("pages/test_list.jsp").forward(req, resp);
    }

    private List<ViewGroup> getViewGroups(List<UniversityGroup> groups) {
        List<ViewGroup> viewGroups = new ArrayList<>();
        groups.forEach(group -> {
            List<ViewTest> tests = new ArrayList<>();
            List<Deadline> deadlines = DAOFactory.getDeadlineDAO().getDeadlinesByUniversityGroup(group);
            deadlines.forEach(deadline -> {
                LocalDate date = deadline.getDeadline();
                Test test = deadline.getTest();
                ViewTest viewTest = ViewTest
                        .builder()
                        .deadline(date)
                        .test(test)
                        .build();
                tests.add(viewTest);
            });
            ViewGroup viewGroup = ViewGroup
                    .builder()
                    .group(group.getName())
                    .tests(tests)
                    .build();
            viewGroups.add(viewGroup);
        });
        return viewGroups;
    }

    private List<ViewGroup> getViewGroupsAdmin() {
        List<ViewGroup> viewGroups = new ArrayList<>();
        List<ViewTest> tests = DAOFactory
                .getTestDAO()
                .findAll()
                .stream()
                .map(test -> ViewTest.builder()
                                .test(test)
                                .build())
                .collect(Collectors.toList());
        ViewGroup general = ViewGroup
                .builder()
                .group("General")
                .tests(tests)
                .build();
        viewGroups.add(general);
        return viewGroups;
    }
}
