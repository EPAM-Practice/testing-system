<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Test</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-flat.css">
</head>
<body class="w3-container">
<h1>${requestScope.test.name}</h1>
    <c:if test="${requestScope.expired}">
        <div class="w3-panel w3-flat-pomegranate">
            <h3>Error!</h3>
            <p>Cannot start solving an expired test.</p>
        </div>
    </c:if>
    <c:if test="${requestScope.require_confirm}">
        <div class="w3-panel w3-flat-peter-river">
            <h3>You are about to begin solving the test.</h3>
            <p>Do you want to continue?</p>
        </div>
        <a href="?id=${param["id"]}&confirm=1" class="w3-button w3-flat-nephritis w3-round-xxlarge">Continue</a>
    </c:if>
</body>
</html>
