<%--
  Created by IntelliJ IDEA.
  User: bloodstinger
  Date: 05.07.19
  Time: 1:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Starting page</title>
</head>
<body>
<div align="center">
    Enter your email and password.
    <form action="users" method="post">
        Email <input name="email" type="email">
        Password <input name="password" type="password">
        <button type="submit"> Sign in</button>
    </form>
    <form action="register" method="get">
        <button type="submit"> Register</button>
    </form>
</div>
</body>
</html>
