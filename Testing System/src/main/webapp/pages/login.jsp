<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="login_error" scope="request" type="java.lang.Boolean" />
<html>
<head>
    <title>Вход</title>
    <link rel="stylesheet" type="text/css" href="pages/style.css" />
</head>
<body>
    <div class="container">
        <h1>Log in</h1>
        <hr>

        <form action="<c:url value="/login" />" method="post">
            <label for="login"><b>Username</b></label>
            <input type="text" placeholder="Enter username" name="login" id="login" required>

            <label for="password"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" id="password" required>

            <button type="submit" class="registerbtn">Log in</button>
        </form>
    </div>

    <c:if test="${login_error}">
        <div class="container error">
            <p>Cannot log in with specified credentials. Check username and password and try again.</p>
        </div>
    </c:if>

    <div class="container signin">
        <p>Do not have an account? <a href="<c:url value="/register" />">Register</a>.</p>
    </div>
</body>
</html>
