<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SHOP</title>
</head>
<body>
<div align="center">
    ${loginMessage}
    <form action='<spring:url value="/signin"/>' method="post">
        <table>
            <tr>
                <td>Логин</td>
                <td><input type="text" name="login"></td>
            </tr>
            <tr>
                <td>Пароль</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Войти"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>