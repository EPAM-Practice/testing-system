<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="users" scope="request" type="java.util.List"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-flat.css">
</head>
<body>
<jsp:include page="/pages/header.jsp" />
<div class="w3-container">
    <h1>Users</h1>
    <ul class="w3-ul w3-card-4">
    <c:forEach var="user" items="${users}">
        <li class="w3-bar">
            <c:choose>
                <c:when test = "${user.role.id == 1}"><img src="img/avatar_student.png" class="w3-bar-item w3-circle w3-hide-small" style="width:85px"></c:when>
                <c:when test = "${user.role.id == 2}"><img src="img/avatar_teacher.png" class="w3-bar-item w3-circle w3-hide-small" style="width:85px"></c:when>
                <c:when test = "${user.role.id == 3}"><img src="img/avatar_admin.png" class="w3-bar-item w3-circle w3-hide-small" style="width:85px"></c:when>
            </c:choose>

            <div class="w3-bar-item">
                <span class="w3-xlarge">${user.name}</span><br>
                <span>${user.role.name}</span>
            </div>
        </li>
    </c:forEach>
    </ul>
</div>
</body>
</html>
