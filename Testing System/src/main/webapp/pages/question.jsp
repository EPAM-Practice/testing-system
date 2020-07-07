<%--
  Created by IntelliJ IDEA.
  User: bozhk
  Date: 07.07.2020
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Question</title>
</head>
<body>
<div class="container">
    <p>Текст вопроса</p>
    <form>
        <p><b>Какое у вас состояние разума?</b></p>
        <p><input name="dzen" type="radio" value="nedzen"> Не дзен</p>
        <p><input name="dzen" type="radio" value="dzen"> Дзен</p>
        <p><input name="dzen" type="radio" value="pdzen" checked> Полный дзен</p>
        <p><input type="submit" value="Выбрать"></p>
    </form>
</div>
<div class="container">
    <p>Пройдено:</p> <p>(здесь процесс прохождения)</p>
</div>
<div class="container">
    <button><a href="/test_score">Завершить</a></button>
</div>
</body>
</html>
