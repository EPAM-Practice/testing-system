package com.epam.practice.testingsystem.servlets;

import com.epam.practice.testingsystem.data.dao.DAOFactory;
import com.epam.practice.testingsystem.data.dto.Answer;
import com.epam.practice.testingsystem.data.dto.Attempt;
import com.epam.practice.testingsystem.data.dto.Question;
import com.epam.practice.testingsystem.data.dto.Test;
import com.epam.practice.testingsystem.data.dto.UniversityGroup;
import com.epam.practice.testingsystem.data.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class SolveServlet extends HttpServlet {
    private void doGetStart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object userObj = session.getAttribute("user");
        User user = (User) userObj;
        int testId = Integer.parseInt(req.getParameter("id"));
        Test test = DAOFactory.getTestDAO().find(testId);

        if (user.getRole().isCheckDeadlines()) {
            UniversityGroup group = DAOFactory.getUserDAO().findAssignedUniversityGroups(user).get(0);
            LocalDate date = LocalDate.now();
            LocalDate deadline = DAOFactory.getDeadlineDAO().getDeadline(test, group).getDeadline();
            req.setAttribute("test", test);
            if (date.compareTo(deadline) > 0)
                req.setAttribute("expired", true);
            else
                req.setAttribute("require_confirm", true);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/solve-prepare.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    private void doGetConfirm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int testId = Integer.parseInt(req.getParameter("id"));
        Test test = DAOFactory.getTestDAO().find(testId);

        List<Question> questions = DAOFactory.getQuestionDAO().findAllByTest(test);
        session.setAttribute("test", test);
        session.setAttribute("questions", questions);
        session.setAttribute("cur_question_number", 0);
        session.setAttribute("answers", new ArrayList<List<String>>());

        resp.sendRedirect(super.getServletContext().getContextPath() + "/solve");
    }

    @SuppressWarnings("unchecked")
    private void doGetQuestion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Question> questions = (List<Question>) session.getAttribute("questions");
        int curQuestionNumber = (Integer) session.getAttribute("cur_question_number");
        Question question = questions.get(curQuestionNumber);
        req.setAttribute("cur_question", question);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/solve.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            resp.sendRedirect(super.getServletContext().getContextPath() + "/login");
            return;
        }

        String confirm = req.getParameter("confirm");

        if (Objects.equals(confirm, "1"))
            doGetConfirm(req, resp);
        else if (session.getAttribute("test") == null)
            doGetStart(req, resp);
        else
            doGetQuestion(req, resp);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int curQuestionNumber = (Integer) session.getAttribute("cur_question_number");
        List<?> questions = (List<?>) session.getAttribute("questions");
        List<List<String>> answers = (List<List<String>>) session.getAttribute("answers");

        Enumeration<String> parameterNames = req.getParameterNames();
        List<String> curAnswers = new ArrayList<>();
        while (parameterNames.hasMoreElements()) {
            String x = parameterNames.nextElement();
            if (Objects.equals(req.getParameter(x), "on")) {
                curAnswers.add(x);
            }
        }
        answers.add(curAnswers);
        session.setAttribute("answers", answers);

        if (curQuestionNumber + 1 == questions.size())
            check(req, resp);
        else {
            session.setAttribute("cur_question_number", curQuestionNumber + 1);
            doGetQuestion(req, resp);
        }
    }

    @SuppressWarnings("unchecked")
    private void check(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        List<Question> questions = (List<Question>) session.getAttribute("questions");
        List<List<String>> answers = (List<List<String>>) session.getAttribute("answers");
        Test test = (Test) session.getAttribute("test");
        User user = (User) session.getAttribute("user");

        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            Set<Integer> answerSet = questions
                    .get(i)
                    .getAnswers()
                    .stream()
                    .filter(Answer::getIsCorrect)
                    .map(Answer::getId)
                    .collect(Collectors.toSet());
            Set<Integer> userAnswersSet = answers
                    .get(i)
                    .stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());

            if (answerSet.containsAll(userAnswersSet)
                    && userAnswersSet.containsAll(answerSet))
                score += questions.get(i).getScore();
        }

        Attempt attempt = Attempt
                .builder()
                .test(test)
                .dateTime(LocalTime.now())
                .score(score)
                .user(user)
                .build();
        int attemptId = DAOFactory.getAttemptDAO().add(attempt);

        resp.sendRedirect(String.format("%s/attempts?id=%d", getServletContext().getContextPath(), attemptId));
    }
}
