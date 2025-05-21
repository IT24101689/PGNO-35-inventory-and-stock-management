<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cancel Order</title>
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
        footer {
            text-align: center;
            padding: 20px;
            color: #ccc;
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="container d-flex justify-content-center">
    <div class="container-card col-lg-6 col-md-8">
        <h2 class="mb-4">Cancel an Order</h2>
        <form method="post" action="orderServlet">
            <input type="hidden" name="action" value="delete" />
            <div class="mb-3">
                <input type="text" class="form-control" name="orderId" placeholder="Order ID to cancel" required>
            </div>
            <button type="submit" class="btn btn-danger">Cancel Order</button>
        </form>
    </div>
</div>

<footer>&copy; 2025 Inventory Management System</footer>
</body>
</html>
