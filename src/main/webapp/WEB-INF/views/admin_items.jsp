<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of all items</title>
</head>
<body>
<div align="center">
    <h2> List of all items</h2>
    <a href="/admin/additem">Add new item</a> <br>
    <form>
    <input formmethod="get" formaction="/admin/users" type="submit" value="Back to all users">
    </form>
    <form action="/signout" method="post">
        <button type="submit">Logout</button>
    </form>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <c:forEach var="item" items="${allItems}">
        <tr>
            <td>${item.name}</td>
            <td>${item.description}</td>
            <td>${item.price}</td>
            <td>
                <form action="/admin/itemEdit" method="get">
                    <input type="hidden" name="id" value="${item.id}">
                    <button type="submit"> Edit</button>
                </form>
            </td>
            <td>
                <form action="/admin/itemDelete" method="post">
                    <button name="delete" type="submit" value="${item.id}">Delete</button>
                </form>
            </td>
        </tr>
        </c:forEach>
        </tr>
    </table>
</div>
</body>
</html>
