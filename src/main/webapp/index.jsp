<<<<<<< HEAD
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
=======
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventory Management System</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&family=Poppins:wght@300;500;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(to right, #0077b6, #00b4d8);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #ffffff;
            padding: 50px 30px;
            border-radius: 20px;
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 100%;
            text-align: center;
            animation: fadeIn 1s ease-in-out;
        }

        .hero i {
            font-size: 60px;
            color: #00b4d8;
            margin-bottom: 20px;
        }

        h1 {
            font-size: 28px;
            color: #222;
            font-weight: 600;
            margin-bottom: 20px;
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

        .footer {
            margin-top: 35px;
            font-size: 16px;
        }

        .footer a {
            color: #0077b6;
            text-decoration: none;
            font-weight: 500;
        }

        .footer a:hover {
            text-decoration: underline;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: scale(0.95); }
            to { opacity: 1; transform: scale(1); }
>>>>>>> f50d5077a154828bda602ab84c66e409f47d533a
        }
    </style>
</head>
<body>

<<<<<<< HEAD
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




=======
<div class="container">
    <div class="hero">
        <i class="bi bi-archive"></i>
    </div>
    <h1>Welcome to <br><strong>Inventory Management System (PGNO: 35)</strong></h1>

    <a href="loginSelection.jsp" class="button">Login Selection</a>

</div>

<jsp:include page="includes/alert.jsp"/>
</body>
</html>
>>>>>>> f50d5077a154828bda602ab84c66e409f47d533a
