<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Review Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 1000px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
        }
        .message {
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 4px;
            background-color: #d4edda;
            color: #155724;
        }
        .error {
            background-color: #f8d7da;
            color: #721c24;
        }
        .review-list {
            width: 100%;
            border-collapse: collapse;
        }
        .review-list th, .review-list td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        .review-list th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        .review-list tr:hover {
            background-color: #f5f5f5;
        }
        .actions {
            display: flex;
            gap: 10px;
        }
        .btn {
            display: inline-block;
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            border: none;
            cursor: pointer;
            font-size: 14px;
        }
        .btn-view {
            background-color: #2196F3;
        }
        .btn-edit {
            background-color: #FFC107;
            color: #333;
        }
        .btn-delete {
            background-color: #F44336;
        }
        .btn-new {
            margin-bottom: 20px;
        }
        .star-rating {
            color: #FFD700;
        }
        .badge {
            display: inline-block;
            padding: 3px 7px;
            border-radius: 3px;
            font-size: 12px;
            font-weight: bold;
        }
        .badge-verified {
            background-color: #28a745;
            color: white;
        }
        .badge-public {
            background-color: #17a2b8;
            color: white;
        }
        .badge-standard {
            background-color: #6c757d;
            color: white;
        }
        .badge-pending {
            background-color: #ffc107;
            color: #333;
        }
        .badge-approved {
            background-color: #28a745;
            color: white;
        }
        .badge-rejected {
            background-color: #dc3545;
            color: white;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Review Management</h1>
        
        <c:if test="${not empty message}">
            <div class="message">${message}</div>
        </c:if>
        
        <c:if test="${not empty error}">
            <div class="message error">${error}</div>
        </c:if>
        
        <div>
            <a href="${pageContext.request.contextPath}/reviews/new" class="btn btn-new">New Standard Review</a>
            <a href="${pageContext.request.contextPath}/reviews/new/verified" class="btn btn-new">New Verified Review</a>
            <a href="${pageContext.request.contextPath}/reviews/new/public" class="btn btn-new">New Public Review</a>
        </div>
        
        <table class="review-list">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Type</th>
                    <th>Reviewer</th>
                    <th>Rating</th>
                    <th>Product/Vendor</th>
                    <th>Date</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${reviews}" var="review">
                    <tr>
                        <td>${review.id}</td>
                        <td>
                            <c:choose>
                                <c:when test="${review['class'].simpleName == 'VerifiedReview'}">
                                    <span class="badge badge-verified">VERIFIED</span>
                                </c:when>
                                <c:when test="${review['class'].simpleName == 'PublicReview'}">
                                    <span class="badge badge-public">PUBLIC</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-standard">STANDARD</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${review.reviewer}</td>
                        <td>
                            <div class="star-rating">
                                <c:forEach begin="1" end="${review.rating}">★</c:forEach>
                                <c:forEach begin="${review.rating + 1}" end="5">☆</c:forEach>
                            </div>
                        </td>
                        <td>${review.productOrVendor}</td>
                        <td>${review.formattedDate}</td>
                        <td>
                            <c:choose>
                                <c:when test="${review['class'].simpleName == 'VerifiedReview'}">
                                    <c:choose>
                                        <c:when test="${review.verified}">
                                            <span class="badge badge-approved">VERIFIED</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge badge-pending">UNVERIFIED</span>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:when test="${review['class'].simpleName == 'PublicReview'}">
                                    <c:choose>
                                        <c:when test="${!review.moderated}">
                                            <span class="badge badge-pending">PENDING</span>
                                        </c:when>
                                        <c:when test="${review.approved}">
                                            <span class="badge badge-approved">APPROVED</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge badge-rejected">REJECTED</span>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <span>-</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="actions">
                            <a href="${pageContext.request.contextPath}/reviews/${review.id}" class="btn btn-view">View</a>
                            <a href="${pageContext.request.contextPath}/reviews/${review.id}/edit" class="btn btn-edit">Edit</a>
                            <a href="${pageContext.request.contextPath}/reviews/${review.id}/delete" class="btn btn-delete" onclick="return confirm('Are you sure you want to delete this review?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
