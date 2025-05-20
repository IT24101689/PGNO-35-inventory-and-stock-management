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
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"/>
    <link rel="stylesheet" href="css/dataTables.bootstrap5.min.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <title>Inventory Management</title>
</head>

<body>

<jsp:include page="includes/adminNavbar.jsp"/>
<jsp:include page="includes/adminSidebar.jsp"/>

<main class="mt-5 pt-3">

    <div class="container-fluid">
        <div class="row my-3">
            <div class="col-md-12 d-flex justify-content-between">
                <h4>Users</h4>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12 mb-3">
                <div class="card">
                    <div class="card-header">
                        <span><i class="bi bi-table me-2"></i></span> Buyers Table
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table id="example" class="table table-striped data-table" style="width: 100%">
                                <thead>
                                <tr>
                                    <th>Buyer Username</th>
                                    <th>Buyer Password</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:choose>
                                    <c:when test="${empty buyers}">
                                        <tr>
                                            <td colspan="8" style="text-align: center;">No Buyers found</td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="buyer" items="${buyers}">
                                            <tr>
                                                <td data-label="Ticket ID">${buyer.name}</td>
                                                <td data-label="Subject">${buyer.password}</td>
                                                <td>
                                                    <a href="profile?userRole=buyer&name=${buyer.name}"
                                                       class="btn btn-primary btn-sm me-2">Edit</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>Buyer Username</th>
                                    <th>Buyer Password</th>
                                    <th>Actions</th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12 mb-3">
                <div class="card">
                    <div class="card-header">
                        <span><i class="bi bi-table me-2"></i></span> Supplier Table
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table id="example" class="table table-striped data-table" style="width: 100%">
                                <thead>
                                <tr>
                                    <th>Supplier Username</th>
                                    <th>Supplier Password</th>
                                    <th>Company Name</th>
                                    <th>Item Category</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:choose>
                                    <c:when test="${empty suppliers}">
                                        <tr>
                                            <td colspan="8" style="text-align: center;">No Supplier found</td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="supplier" items="${suppliers}">
                                            <tr>
                                                <td data-label="Ticket ID">${supplier.name}</td>
                                                <td data-label="Subject">${supplier.password}</td>
                                                <td data-label="Subject">${supplier.companyName}</td>
                                                <td data-label="Subject">${supplier.category}</td>
                                                <td>
                                                    <a href="profile?userRole=supplier&name=${supplier.name}"
                                                       class="btn btn-primary btn-sm me-2">Edit</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>Supplier Username</th>
                                    <th>Supplier Password</th>
                                    <th>Company Name</th>
                                    <th>Item Category</th>
                                    <th>Actions</th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>

<!-- Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Delete User</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p class="fw-bold">Are you sure you want to delete this user ? <span class="text-primary"><i
                        class="bi bi-person-x-fill"></i> UserID: 03</span></p>
                <p class="text-danger">This action cannot be undone.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger"><i class="bi bi-trash3"></i> Delete</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script src="./js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.0.2/dist/chart.min.js"></script>
<script src="./js/jquery-3.5.1.js"></script>
<script src="./js/jquery.dataTables.min.js"></script>
<script src="./js/dataTables.bootstrap5.min.js"></script>
<script src="./js/script.js"></script>

<jsp:include page="includes/alert.jsp"/>
</body>

</html>