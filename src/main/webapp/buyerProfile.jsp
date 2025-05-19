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

<div class="container mt-5 pt-3">
    <div class="row d-flex justify-content-center my-3">
        <div class="col-12 col-md-6 d-flex justify-content-between">
            <h4>Profile</h4>
            <c:choose>
                <c:when test="${sessionScope.role == 'admin'}">
                    <a href="adminDashboard" class="btn btn-primary">Back to Dashboard</a>
                </c:when>
                <c:otherwise>
                    <a href="buyerDashboard.jsp" class="btn btn-primary">Back to Dashboard</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="row d-flex justify-content-center">
        <div class="col-12 col-md-6 mb-3">
            <div class="card">
                <div class="card-header">
                    <span><i class="bi bi-plus-circle"></i></span> Update Profile
                </div>
                <div class="card-body">

                    <form action="profile" method="post">
                        <div class="mb-3">
                            <label for="name" class="form-label">User Name</label>
                            <input type="text" class="form-control" id="name" value="${user.name}"
                                   readonly>
                            <input type="hidden" name="name" value="${user.name}"/>
                            <input type="hidden" name="userRole" value="buyer"/>
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label">User Password</label>
                            <input type="password" name="password" class="form-control" id="password"
                                   value="${user.password}" required>
                        </div>

                        <button type="submit" class="btn btn-primary">Update</button>
                    </form>

                </div>
            </div>

            <div class="card mt-3">
                <div class="card-header">
                    <span><i class="bi bi-plus-circle"></i></span> Delete Profile
                </div>
                <div class="card-body">

                    <button type="button" class="btn btn-danger btn-sm"
                            data-bs-toggle="modal" data-bs-target="#deleteModal">Delete Profile
                    </button>

                </div>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Delete User</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p class="fw-bold">Are you sure you want to delete this profile ?</p>
                    <p class="text-danger">This action cannot be undone.</p>
                </div>
                <div class="modal-footer">
                    <form action="deleteProfile" method="post">

                        <input type="hidden" name="name" value="${user.name}"/>
                        <input type="hidden" name="userRole" value="buyer"/>

                        <button type="submit" class="btn btn-danger"><i class="bi bi-trash3"></i> Delete</button>
                    </form>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
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