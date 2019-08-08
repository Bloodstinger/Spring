<%--
  Created by IntelliJ IDEA.
  User: bloodstinger
  Date: 03.07.19
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<div align="center">
    ${isValid}
    <form action="/admin/register" method="post">
        Email <input name="email" type="email" placeholder="Enter your email..."
                     value="${checkEmail}"> <br>
        Password <input name="password" type="password"><br>
        Repeat password <input name="repeatPassword" type="password"><br>
        Role <br><input type="radio" value="admin" name="role"> Admin<br>
        <input type="radio" value="user" name="role"> User<br>

        <button type="submit"> Register</button>
        <button type="submit" formaction="/admin/users" formmethod="get">Back to all users list</button>
    </form>
</div>
</body>
</html>
