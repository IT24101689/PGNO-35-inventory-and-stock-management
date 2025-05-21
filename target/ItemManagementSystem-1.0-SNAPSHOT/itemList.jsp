<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Item List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            text-align: center;
            padding: 50px;
        }
        h1 {
            color: #4CAF50;
        }
        table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        .button {
            padding: 10px 20px;
            font-size: 16px;
            color: white;
            background-color: #4CAF50;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }
        .button:hover {
            background-color: #45a049;
        }
        .container {
            margin-top: 50px;
        }
    </style>
</head>
<body>

<h1>Item List</h1>
<form action="ItemServlet" method="get">
    <input type="text" name="searchQuery" placeholder="Search items" />
    <input type="submit" value="Search" />
</form>

<table>
    <thead>
    <tr><th>Name</th><th>Description</th><th>Price</th><th>Quantity</th><th>Action</th></tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${items}">
        <tr>
            <td>${item.name}</td>
            <td>${item.description}</td>
            <td>${item.price}</td>
            <td>${item.quantity}</td>
            <td>
                <a href="itemEdit.jsp?id=${item.id}" class="button">Edit</a>
                <form action="ItemServlet" method="post" style="display:inline;">
                    <input type="hidden" name="itemId" value="${item.id}" />
                    <input type="submit" name="action" value="delete" class="button" />
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="container">
    <a href="itemAdd.jsp" class="button">Add New Item</a>
</div>

</body>
</html>
