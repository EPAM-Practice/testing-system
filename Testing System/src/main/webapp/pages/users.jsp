<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="users" scope="request" type="java.util.List"/>
<jsp:useBean id="roles" scope="request" type="java.util.List"/>
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
                <c:when test = "${user.role.id == 1}"><img src="img/avatar_student.png" class="w3-bar-item w3-circle w3-hide-small" style="width:135px"></c:when>
                <c:when test = "${user.role.id == 2}"><img src="img/avatar_teacher.png" class="w3-bar-item w3-circle w3-hide-small" style="width:135px"></c:when>
                <c:when test = "${user.role.id == 3}"><img src="img/avatar_admin.png" class="w3-bar-item w3-circle w3-hide-small" style="width:135px"></c:when>
            </c:choose>

            <div>
                <span class="w3-xlarge">${user.name}</span><br>
                <span>${user.role.name}</span>
                <form method="post">
                    <input type="hidden" name="user_id" value="${user.id}">
                    <div>
                        <div class="w3-third">
                            <select class="w3-select w3-border" name="user_role">
                            <c:forEach var="role" items="${roles}">
                                <option value="${role.id}" <c:if test="${user.role.id == role.id}">selected</c:if>>${role.name}</option>
                            </c:forEach>
                            </select>
                        </div>
                        <input type="submit" value="Change role" class="w3-button w3-flat-peter-river w3-round-xxlarge w3-margin-left">
                    </div>
                </form>
            </div>
        </li>
    </c:forEach>
    </ul>
</div>
</body>
</html>
