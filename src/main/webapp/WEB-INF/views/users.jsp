<%--
  Created by IntelliJ IDEA.
  User: bloodstinger
  Date: 05.07.19
  Time: 1:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of all users #godmode</title>
</head>
<body>
<div align="center">
    ${isValid}
    <h2> List of all users</h2>
    <a href="/admin/register"> Add new user.</a> <br>
    <a href="/admin/items">List of all items</a> <br>
    <form action="/logout" method="get">
    @PostMapping("/login")
    @GetMapping("/login")
    public String logingPost(@AuthenticationPrincipal User user) {

        <button type="submit">Logout</button>
    </form><br>
    <form action="/admin/users" method="get">
        <button type="submit">Refresh</button>
    </form>
    <table border="1">
        <tr>Email</tr>
        <tr> Password</tr>
        <tr> Role</tr>
        <c:forEach var="user" items="${allUsers}">
            <tr>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td>
                    <form action="/admin/userDelete" method="post">
                        <button name="delete" type="submit" value="${user.id}"> Delete</button>
                    </form>
                    <form action="/admin/userEdit" method="get">
                        <input type="hidden" name="id" value="${user.id}">
                        <button type="submit"> Edit</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
