<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="cur_question" scope="request" type="com.epam.practice.testingsystem.data.dto.Question" />
<%
    int cur_question_number = (Integer) session.getAttribute("cur_question_number");
    int total = ((List<?>) session.getAttribute("questions")).size();
    int percent = Math.round(100 * cur_question_number / (float)total);
    pageContext.setAttribute("percent", percent);
    pageContext.setAttribute("total", total);
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-flat.css">
</head>
<body>
    <div class="w3-container w3-flat-wisteria">
        <h2>${sessionScope.test.name}</h2>
        <p>Question ${sessionScope.cur_question_number + 1} / ${total}</p>
    </div>
    <div class="w3-container">
        <h2>${cur_question.question}</h2>
        <form method="post">
        <c:forEach var="answer" items="${cur_question.answers}">
            <p>
                <input class="w3-check" type="checkbox" name="${answer.id}" id="${answer.id}">
                <label for="${answer.id}">${answer.answer}</label>
            </p>
        </c:forEach>
            <hr>
            <input type="submit" value="Submit" class="w3-button w3-flat-nephritis w3-round-xxlarge">
            <a href="?stop=1" class="w3-button w3-flat-pomegranate w3-round-xxlarge">Finish</a>
        </form>
        <div class="w3-light-grey w3-round">
            <div class="w3-container w3-round w3-blue" style="width:${percent}%">${percent}%</div>
        </div>
    </div>
</body>
</html>
