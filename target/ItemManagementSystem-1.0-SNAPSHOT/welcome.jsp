<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Item Management System</title>
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

<h1>Welcome to the Item Management System</h1>
<p>Your one-stop solution for managing items in the inventory.</p>

<div class="container">
    <a href="itemList.jsp" class="button">View Items</a>
    <a href="itemAdd.jsp" class="button">Add New Item</a>
</div>

</body>
</html>
