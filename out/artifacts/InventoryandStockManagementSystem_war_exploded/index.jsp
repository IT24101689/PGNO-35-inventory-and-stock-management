<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #0f2027, #203a43, #2c5364);
            min-height: 100vh;
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            color: #fff;
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
            margin-top: 50px;
            text-align: center;
        }
        .dashboard-image {
            max-width: 300px;
            margin-bottom: 20px;
            border-radius: 12px;
        }
        footer {
            text-align: center;
            padding: 20px;
            color: #ccc;
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

<div class="container d-flex justify-content-center">
    <div class="container-card col-lg-8 col-md-10">
        <img class="dashboard-image" src=https://static.thenounproject.com/png/1851310-200.png
             alt="Order Illustration">
        <h2 class="mb-3">Welcome to the Order Dashboard</h2>
        <p>This is your central place to manage orders. Use the navbar to place, view, update, or cancel orders.</p>
    </div>
</div>

<footer>&copy; 2025 Inventory Management System</footer>

</body>
</html>




