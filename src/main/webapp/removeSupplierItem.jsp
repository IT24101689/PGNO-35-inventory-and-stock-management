<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*, java.util.*, javax.servlet.*, javax.servlet.http.*" %>

<%
  // Check if the supplier is logged in
  HttpSession session1 = request.getSession(false);
  String supplierUsername = (session1 != null) ? (String) session1.getAttribute("supplierUsername") : null;

  if (supplierUsername == null) {
    response.sendRedirect("supplierLogin.jsp");
    return;
  }
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Supplier Inventory - Remove Item</title>
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
      margin-bottom: 10px;
    }

    .welcome {
      font-size: 18px;
      color: #666;
      margin-bottom: 20px;
    }

    p {
      font-size: 16px;
      color: #444;
      margin-bottom: 25px;
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
      margin: 10px;
      border: none; /* <- This removes the black border */
      cursor: pointer;
      transition: all 0.3s ease;
    }


    .button:hover {
      transform: translateY(-2px);
      box-shadow: 0 8px 20px rgba(0, 183, 255, 0.3);
    }

    .footer {
      margin-top: 30px;
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
    }
  </style>
</head>
<body>

<div class="container">
  <div class="hero">
    <i class="bi bi-box-seam"></i>
    <h1>Remove Item</h1>
    <p class="welcome">Welcome, <strong><%= supplierUsername %></strong>!</p>
  </div>

  <p>Are you sure you want to remove the last added item from your inventory?</p>

  <form action="RemoveSupplierItemServlet" method="post">
    <button type="submit" class="button">Yes, Remove Last Added Item</button>
  </form>

  <div class="footer">
    <p><a href="supplierDashboard.jsp">Back to Supplier Dashboard</a></p>
  </div>
</div>

</body>
</html>
