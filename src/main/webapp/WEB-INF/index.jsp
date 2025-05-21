<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;700&display=swap" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #0f2027, #203a43, #2c5364);
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        .navbar {
            background-color: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
        }

        .container-card {
            background-color: #ffffff;
            color: #333;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.3);
            margin: 50px auto;
            text-align: center;
            max-width: 600px;
        }

        .dashboard-image {
            max-width: 300px;
            margin-bottom: 20px;
            border-radius: 12px;
        }

        .footer {
            text-align: center;
            padding: 20px;
            color: #ccc;
            margin-top: auto;
        }

        .button {
            display: inline-block;
            padding: 14px 28px;
            background: linear-gradient(90deg, #00b4d8, #0077b6);
            color: white;
            font-size: 17px;
            font-weight: 500;
            text-decoration: none;
            border-radius: 10px;
            margin-top: 25px;
            transition: all 0.3s ease;
        }

        .button:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(0, 183, 255, 0.3);
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark px-4">
    <a class="navbar-brand" href="#">Order System</a>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav ms-auto">
            <li class="nav-item"><a class="nav-link" href="placeOrder.jsp">Place Order</a></li>
            <li class="nav-item"><a class="nav-link" href="viewOrders.jsp">View Orders</a></li>
            <li class="nav-item"><a class="nav-link" href="updateOrder.jsp">Update</a></li>
            <li class="nav-item"><a class="nav-link" href="deleteOrder.jsp">Cancel</a></li>
        </ul>
    </div>
</nav>

<div class="container-card">
    <img class="dashboard-image" src="https://static.thenounproject.com/png/1851310-200.png" alt="Order Illustration">
    <h2 class="mb-3">Welcome to the Order Dashboard</h2>
    <p>This is your central place to manage orders. Use the navbar to place, view, update, or cancel orders.</p>
</div>

<footer class="footer">
    &copy; 2025 Inventory Management System
</footer>

</body>
</html>



