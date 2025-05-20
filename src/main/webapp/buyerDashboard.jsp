<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Supplier Dashboard</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #00bcd4, #008cba);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background: #fff;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            max-width: 1000px;
            width: 100%;
            text-align: center;
            animation: fadeIn 1s ease-in-out;
        }

        h1 {
            font-size: 28px;
            font-weight: 500;
            color: #333;
            margin-bottom: 10px;
        }

        .table-container {
            margin-top: 20px;
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #00bcd4;
            color: white;
            font-weight: bold;
        }

        .button {
            display: inline-block;
            text-decoration: none;
            padding: 10px 15px;
            background-color: #00bcd4;
            color: white;
            border-radius: 5px;
            font-weight: 500;
            margin-top: 15px;
            transition: background-color 0.3s ease;
        }

        .button:hover {
            background-color: #008cba;
        }

        select {
            padding: 10px;
            margin-top: 15px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
        }

        .alert {
            padding: 10px;
            margin-bottom: 10px;
            text-align: left;
            border-left: 6px solid;
        }

        .expired {
            background-color: #ffdddd;
            border-color: #f44336;
        }

        .soon-expire {
            background-color: #fff3cd;
            border-color: #ffc107;
        }

        tr.expired-row {
            background-color: #ffe6e6;
        }

        tr.soon-expire-row {
            background-color: #fffbe6;
        }

        #itemCount {
            margin-top: 10px;
            color: #555;
            font-size: 15px;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: scale(0.9);
            }
            to {
                opacity: 1;
                transform: scale(1);
            }
        }
    </style>
    <script>
        function filterItems() {
            var selectedCategory = document.getElementById("categoryFilter").value;
            var rows = document.querySelectorAll("#itemsTable tbody tr");
            let count = 0;

            rows.forEach(row => {
                var category = row.getAttribute("data-category");
                if (selectedCategory === "all" || category === selectedCategory) {
                    row.style.display = "";
                    count++;
                } else {
                    row.style.display = "none";
                }
            });

            document.getElementById("itemCount").textContent = "Showing " + count + " item(s)";
        }
    </script>
</head>
<body>

<div class="container">
    <h1>Welcome, ${sessionScope.user.name}!</h1>

    <a href="profile?userRole=buyer&name=${sessionScope.user.name}" class="button">Profile</a>

    <a href="logout" class="button">Logout</a>
</div>

<jsp:include page="includes/alert.jsp"/>
</body>
</html>
