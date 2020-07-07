<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="session" type="com.epam.practice.testingsystem.data.dto.User"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Hello, ${user.name}!</h1>
    <a href="<c:url value="/logout"/>">Logout</a>
</body>
</html>
