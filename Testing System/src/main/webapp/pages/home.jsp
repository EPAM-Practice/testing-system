<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="user" scope="session" type="com.epam.practice.testingsystem.data.dto.User"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-flat.css">
</head>
<body>
    <jsp:include page="header.jsp" />
    <div class="w3-container margin-top">
        <div class="w3-row-padding">
            <div class="w3-third">
                <c:choose>
                    <c:when test = "${user.role.id == 1}"><img src="img/avatar_student.png" class="w3-bar-item w3-circle w3-hide-small" style="width:135px"></c:when>
                    <c:when test = "${user.role.id == 2}"><img src="img/avatar_teacher.png" class="w3-bar-item w3-circle w3-hide-small" style="width:135px"></c:when>
                    <c:when test = "${user.role.id == 3}"><img src="img/avatar_admin.png" class="w3-bar-item w3-circle w3-hide-small" style="width:135px"></c:when>
                </c:choose>
            </div>
            <h1>Hello, ${user.name}!</h1>
        </div>
        <section>
            <p>Attempts:</p>
            <table class="w3-table w3-bordered">
                <tr>
                    <th>Test</th>
                    <th>Score</th>
                    <th>Time</th>
                </tr>
                <c:forEach var="attempt" items="${requestScope.attempts}">
                <tr>
                    <td>${attempt.test.name}</td>
                    <td>${attempt.score}</td>
                    <fmt:parseDate value="${attempt.dateTime}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDate" type="date"/>
                    <fmt:formatDate value="${parsedDate}" var="dateTime" type="both" dateStyle="medium" timeStyle="short"/>
                    <td>${dateTime}</td>
                </tr>
                </c:forEach>
            </table>
        </section>
    </div>
</body>
</html>
