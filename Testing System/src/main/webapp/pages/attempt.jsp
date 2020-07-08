<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="attempt" scope="request" type="com.epam.practice.testingsystem.data.dto.Attempt" />
<html>
<head>
    <title>Attempt #${attempt.id}</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-flat.css">
</head>
<body>
    <div class="w3-modal-content w3-card-4">
        <header class="w3-container w3-flat-wisteria">
            <h2>Attempt #${attempt.id}</h2>
            <a href="tests" class="w3-button w3-display-topright">&times;</a>
        </header>
        <div class="w3-container">
            <table>
                <tr>
                    <th class="w3-left-align">Test</th>
                    <td class="w3-padding-large">${attempt.test.name}</td>
                </tr>
                <tr>
                    <th class="w3-left-align">User</th>
                    <td class="w3-padding-large">${attempt.user.name}</td>
                </tr>
                <tr>
                    <th class="w3-left-align">Time</th>
                    <td class="w3-padding-large">${attempt.dateTime}</td>
                </tr>
                <tr>
                    <th class="w3-left-align">Score</th>
                    <td class="w3-padding-large">${attempt.score}</td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>
