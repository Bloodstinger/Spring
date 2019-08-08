<%--
  Created by IntelliJ IDEA.
  User: bloodstinger
  Date: 11.07.19
  Time: 1:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit item</title>
</head>
<body>
<h4> Edit product</h4> <br>
ID : ${id}
<form action="/admin/itemEdit" method="post">
    Name <input type="text" name="name" value="${name}"> <br>
    Description <input type="text" name="description" value="${description}"/><br>
    Price <input type="number" step="0.01" name="price" value="${price}">
    <input type="hidden" name="id" value="${id}">
    <input type="submit" value="Confirm"> <br>
    <input type="submit" formaction="/admin/items" formmethod="get" value="Back to all items">
</form>
</body>
</html>
