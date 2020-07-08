<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="view_groups" scope="request" type="java.util.List"/>
<html>
<head>
    <title>Available tests</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-flat.css">
</head>
<body>
    <jsp:include page="header/header.jsp" />
    <div class="w3-container">
        <h2>Available tests</h2>
        <c:forEach var="view_group" items="${view_groups}">
            <div class="w3-card-4" style="width:75%;">
                <header class="w3-container w3-flat-wisteria">
                    <h2>${view_group.group}</h2>
                </header>

                <div class="w3-container">
                    <ul class="w3-ul w3-hoverable">
                        <c:forEach var="view_test" items="${view_group.tests}">
                            <li>
                                <h3><a href="<c:url value="/solve?id=${view_test.test.id}" />">${view_test.test.name}</a></h3>
                                <c:if test="${view_test.deadline != null}">
                                    <fmt:parseDate value="${view_test.deadline}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>
                                    <i>until <fmt:formatDate value="${parsedDate}" pattern="dd.MM.yyyy" /></i>
                                </c:if>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>
