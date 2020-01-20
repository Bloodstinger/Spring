<%--
  Created by IntelliJ IDEA.
  User: bloodstinger
  Date: 11.07.19
  Time: 2:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<div align="center">
    ${isValid}
    <form action="/admin/userEdit" method="post">
        Email <input name="email" type="email" placeholder="Enter your email..."
                     value="${email}"><br>
        New password <input name="password" type="password"> <br>
        Repeat new password <input name="repeatPassword" type="password"><br>
        Role <br><input type="radio" value="ROLE_ADMIN" name="role"> Admin<br>
        <input type="radio" value="ROLE_USER" name="role"> User<br>
        <input type="hidden" name="id" value="<%= request.getParameter("id")%>">
        <button type="submit"> Submit</button>
        <button type="submit" formaction="/admin/users" formmethod="get"> Back to users list</button>
    </form>
</div>
</body>
</html>
