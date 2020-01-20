<%--
  Created by IntelliJ IDEA.
  User: bloodstinger
  Date: 17.07.19
  Time: 1:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Checkout</title>
</head>
<body>
<div align="center">
    <h2> List of all items in cart</h2>
    <form method="post" action="/user/checkout">
        <button type="submit">Confirm</button>
    </form>
    Total : ${totalPrice}
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <c:forEach var="item" items="${allItems}">
        <tr>
            <td>${item.getName()}</td>
            <td>${item.getDescription()}</td>
            <td>${item.getPrice()}</td>
        </tr>
        </c:forEach>
        </tr>
    </table>
</div>
</body>
</html>
