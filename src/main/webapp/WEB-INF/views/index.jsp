<%--
  Created by IntelliJ IDEA.
  User: bloodstinger
  Date: 04.07.19
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<div align="center">
    Sup bruh.<br>
    Enter your email and password.<br>
    ${isValid}
    <form action="/admin/users" method="post">
        Email <input name="email" type="email" value="${email}"><br>
        Password <input name="password" type="password"><br>
        <button type="submit"> Sign in</button>
    </form>
</div>
</body>
</html>
