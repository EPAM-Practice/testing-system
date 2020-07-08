<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="err" scope="request" type="java.lang.String" />
<html>
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" type="text/css" href="pages/style.css" />
</head>
<body>
    <div class="container">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <form action="<c:url value="/register" />" method="post">
            <label for="login"><b>Username</b></label>
            <input type="text" placeholder="Enter username" name="login" id="login" required>

            <label for="password"><b>Password</b></label>
            <input type="password" placeholder="Enter password" name="password" id="password" required>

            <label for="password_confirm"><b>Repeat Password</b></label>
            <input type="password" placeholder="Repeat Password" name="password_confirm" id="password_confirm" required>

            <button type="submit" class="registerbtn">Register</button>
        </form>
        <hr>
    </div>

    <c:if test="${err!=\"\"}">
        <div class="container error">
            <p>${err}</p>
        </div>
    </c:if>

    <div class="container signin">
        <p>Already have an account? <a href="<c:url value="/login" />">Login</a>.</p>
    </div>
</body>
</html>
