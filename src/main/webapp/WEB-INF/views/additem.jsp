<%--
  Created by IntelliJ IDEA.
  User: bloodstinger
  Date: 04.07.19
  Time: 2:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Item</title>
</head>
<body>
<H2>${isValid}</H2>
<form action="/admin/additem" method="post">
    Name <input type="text" name="name"> <br>
    Description <input type="text" name="description"><br>
    Price <input type="number" step="0.01" name="price" value="0"><br>
    <input type="submit" value="Add new item "> <br>
    <input type="submit" formaction="/admin/items" formmethod="get" value="List of all items">
</form>
</body>
</html>
