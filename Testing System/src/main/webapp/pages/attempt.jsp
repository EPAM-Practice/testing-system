<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Attempt #${requestScope.attempt.id}</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-flat.css">
</head>
<body>
    <div class="w3-modal-content w3-card-4">
        <c:if test="${requestScope.attempt == null}">
        <header class="w3-container w3-flat-pomegranate">
            <h2>Attempt not found</h2>
            <a href="tests" class="w3-button w3-display-topright">&times;</a>
        </header>
        <div class="w3-container">
            <p>Could not find attempt with such id</p>
        </div>
        </c:if>
        <c:if test="${requestScope.attempt != null}">
        <header class="w3-container w3-flat-wisteria">
            <h2>Attempt #${requestScope.attempt.id}</h2>
            <a href="tests" class="w3-button w3-display-topright">&times;</a>
        </header>
        <div class="w3-container">
            <table>
                <tr>
                    <th class="w3-left-align">Test</th>
                    <td class="w3-padding-large">${requestScope.attempt.test.name}</td>
                </tr>
                <tr>
                    <th class="w3-left-align">User</th>
                    <td class="w3-padding-large">${requestScope.attempt.user.name}</td>
                </tr>
                <tr>
                    <th class="w3-left-align">Time</th>
                    <td class="w3-padding-large">${requestScope.attempt.dateTime}</td>
                </tr>
                <tr>
                    <th class="w3-left-align">Score</th>
                    <td class="w3-padding-large">${requestScope.attempt.score}</td>
                </tr>
            </table>
        </div>
        </c:if>
    </div>
</body>
</html>
