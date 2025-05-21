<%@ page import="java.io.*, org.example.Order, org.example.OrderQueue" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>View Orders</title>
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
            <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="placeOrder.jsp">Place Order</a></li>
            <li class="nav-item"><a class="nav-link active" href="#">View Orders</a></li>
            <li class="nav-item"><a class="nav-link" href="updateOrder.jsp">Update</a></li>
            <li class="nav-item"><a class="nav-link" href="deleteOrder.jsp">Cancel</a></li>
        </ul>
    </div>
</nav>

<div class="container d-flex justify-content-center">
    <div class="container-card col-lg-10 col-md-12">
        <h2 class="mb-4">All Orders</h2>

        <%
            String filePath = "C:\\Users\\User\\IdeaProjects\\OrderManagement\\orders.txt";
            OrderQueue queue = new OrderQueue();
            boolean fileError = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Order o = Order.fromCSV(line);
                    if (o != null) queue.enqueue(o);
                }
                queue.sortByStatus(); // Apply Bubble Sort
            } catch (Exception e) {
                fileError = true;
            }

            Order[] orders = queue.toArray();
        %>

        <% if (fileError) { %>
        <div class="alert alert-danger">
            Unable to read order file. Please check the file path or permissions.
        </div>
        <% } else if (orders.length == 0) { %>
        <div class="alert alert-info">No orders found.</div>
        <% } else { %>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Order ID</th>
                <th>Product ID</th>
                <th>Quantity</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <% for (Order o : orders) { %>
            <tr>
                <td><%= o.getOrderId() %></td>
                <td><%= o.getProductId() %></td>
                <td><%= o.getQuantity() %></td>
                <td><%= o.getStatus() %></td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <% } %>
    </div>
</div>

<footer>&copy; 2025 Inventory Management System</footer>

</body>
</html>