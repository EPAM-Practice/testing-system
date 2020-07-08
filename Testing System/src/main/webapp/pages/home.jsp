<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="session" type="com.epam.practice.testingsystem.data.dto.User"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-flat.css">
</head>
<body>
    <jsp:include page="header.jsp" />
    <h1>Hello, ${user.name}!</h1>
    <section class="tests">
        <div class="container">
            <p> Пройденные тесты:</p>
            <table>
                <tr>
                    <td>Тест</td>
                    <td>Процент выполнения</td>
                </tr>
                <tr>
                    <td>Тест 3</td>
                    <td>66%</td>
                </tr>
                <tr>
                    <td>Тест 4</td>
                    <td>87%</td>
                </tr>
            </table>
        </div>
    </section>
</body>
</html>
