<%--
  Created by IntelliJ IDEA.
  User: bloodstinger
  Date: 17.07.19
  Time: 1:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <center><h2>Total Price: ${totalPrice} </h2></center>
    <center><h2>Confirm Order:</h2></center>
    <form action="/user/confirmation" method="get">
        Email <input name="email" type="email" value="${email}" ><br>
        Address <input name="address" type="text" value="${address}"><br>
        <input type="hidden" name="totalPrice" value="${totalPrice}">
        <button type="submit"> Get Confirm Code </button><br>
    </form>
    <center><h2>Confirm Code:</h2></center><br>
    ${success}
    <form action="/user/confirmation" method="post">
        Code <input name="code" type="text" ><br>
        <button type="submit"> Confirm Code </button><br>
    </form>
</center>
</body>
</html>