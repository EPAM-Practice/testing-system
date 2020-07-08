<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>My super project!</title>
</head>
<body>
<!-- header -->
<div>
    <h1>Super app!</h1>
</div>
<a href="<c:url value="/login" />">Login</a>
<a href="<c:url value="/register" />">Register</a>

</body>
</html>