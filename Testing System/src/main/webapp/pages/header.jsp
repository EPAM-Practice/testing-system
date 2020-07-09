<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="w3-bar w3-flat-midnight-blue">
    <a href="${pageContext.request.contextPath}" class="w3-bar-item w3-button w3-padding-16">Testing System</a>
    <c:if test="${sessionScope.user == null}">
    <a href="register" class="w3-bar-item w3-button w3-padding-16 w3-right w3-blue">Register</a>
    <a href="login" class="w3-bar-item w3-button w3-padding-16 w3-right w3-blue">Login</a>
    </c:if>
    <c:if test="${sessionScope.user != null}">
    <a href="tests" class="w3-bar-item w3-button w3-padding-16">Tests</a>
    <c:if test="${sessionScope.user.role.id >= 2}"><%--some links for role "teacher" and higher--%></c:if>
    <c:if test="${sessionScope.user.role.id >= 3}">
    <a href="users" class="w3-bar-item w3-button w3-padding-16">Users</a>
    </c:if>
    <a href="logout" class="w3-bar-item w3-button w3-padding-16 w3-right w3-gray">Logout</a>
    <a href="home" class="w3-bar-item w3-button w3-padding-16 w3-right">${sessionScope.user.name}</a>
    </c:if>
</div>
