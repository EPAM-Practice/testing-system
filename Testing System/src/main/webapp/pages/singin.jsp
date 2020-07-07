<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" type="text/css" href="pages/style.css" />
</head>
<body>
<form method="post">
    <div class="container">
        <h1>Sing in</h1>
        <p>Please fill in this form to sing in account.</p>
        <hr>

        <label for="username"><b>Username</b></label>
        <input type="text" placeholder="Enter uername" name="username" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>

        <button type="submit" class="singinbtn"><a href="/profile_student">Sing in</a></button>
    </div>

</form>
</body>
</html>
