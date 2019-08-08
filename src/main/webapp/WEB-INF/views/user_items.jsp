<%--
  Created by IntelliJ IDEA.
  User: bloodstinger
  Date: 16.07.19
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of all items</title>

</head>
<body>
<div align="center">
    <h2> List of all items</h2>
    Items in shopping cart :
    ${count}
    <form action="/logout" method="post">
        <button type="submit">Logout</button>
        <button formmethod="get" formaction="/user/checkout" type="submit">Checkout</button>
    </form>
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
            <td>
                <form action="/user/buy" method="post">
                    <input type="hidden" name="id" value="${item.getId()}">
                    <button type="submit">Buy</button>
                </form>
            </td>
        </tr>
        </c:forEach>
        </tr>
    </table>
</div>
</body>
</html>
