<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Review Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 800px;
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
        .review-detail {
            margin-bottom: 30px;
        }
        .review-detail h2 {
            margin-bottom: 15px;
            color: #444;
            border-bottom: 1px solid #eee;
            padding-bottom: 10px;
        }
        .review-detail p {
            margin: 10px 0;
            line-height: 1.5;
        }
        .review-detail .label {
            font-weight: bold;
            display: inline-block;
            width: 150px;
        }
        .star-rating {
            color: #FFD700;
            font-size: 24px;
            margin: 10px 0;
        }
        .badge {
            display: inline-block;
            padding: 5px 10px;
            border-radius: 3px;
            font-size: 14px;
            font-weight: bold;
            margin-right: 10px;
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
            margin-right: 10px;
        }
        .btn-edit {
            background-color: #FFC107;
            color: #333;
        }
        .btn-delete {
            background-color: #F44336;
        }
        .btn-back {
            background-color: #2196F3;
        }
        .actions {
            margin-top: 20px;
            display: flex;
            gap: 10px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], 
        textarea, 
        select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
        }
        textarea {
            height: 100px;
            resize: vertical;
        }
        .moderation-form,
        .verification-form {
            margin-top: 30px;
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 5px;
            border: 1px solid #ddd;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Review Details</h1>
        
        <c:if test="${not empty message}">
            <div class="message">${message}</div>
        </c:if>
        
        <c:if test="${not empty error}">
            <div class="message error">${error}</div>
        </c:if>
        
        <div class="review-detail">
            <h2>
                <c:choose>
                    <c:when test="${isVerified}">
                        <span class="badge badge-verified">VERIFIED REVIEW</span>
                    </c:when>
                    <c:when test="${isPublic}">
                        <span class="badge badge-public">PUBLIC REVIEW</span>
                    </c:when>
                    <c:otherwise>
                        <span class="badge badge-standard">STANDARD REVIEW</span>
                    </c:otherwise>
                </c:choose>
                
                <c:if test="${isVerified}">
                    <c:choose>
                        <c:when test="${review.verified}">
                            <span class="badge badge-approved">VERIFIED</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge badge-pending">UNVERIFIED</span>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                
                <c:if test="${isPublic}">
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
                </c:if>
            </h2>
            
            <p><span class="label">ID:</span> ${review.id}</p>
            <p><span class="label">Reviewer:</span> ${review.reviewer}</p>
            <p><span class="label">Product/Vendor:</span> ${review.productOrVendor}</p>
            <p><span class="label">Rating:</span> 
                <span class="star-rating">
                    <c:forEach begin="1" end="${review.rating}">★</c:forEach>
                    <c:forEach begin="${review.rating + 1}" end="5">☆</c:forEach>
                </span>
            </p>
            <p><span class="label">Date Submitted:</span> ${review.formattedDate}</p>
            <p><span class="label">Comment:</span></p>
            <p style="margin-left: 20px; white-space: pre-line;">${review.comment}</p>
            
            <c:if test="${isVerified && review.verified}">
                <p><span class="label">Verified By:</span> ${review.verifiedBy}</p>
                <p><span class="label">Verification Date:</span> ${review.verificationDate}</p>
            </c:if>
            
            <c:if test="${isPublic && review.moderated}">
                <p><span class="label">Status:</span> ${review.approved ? 'Approved' : 'Rejected'}</p>
                <c:if test="${not empty review.moderationNotes}">
                    <p><span class="label">Moderation Notes:</span></p>
                    <p style="margin-left: 20px; white-space: pre-line;">${review.moderationNotes}</p>
                </c:if>
            </c:if>
        </div>
        
        <!-- Verification Form for VerifiedReview -->
        <c:if test="${isVerified && !review.verified}">
            <div class="verification-form">
                <h3>Verify This Review</h3>
                <form action="${pageContext.request.contextPath}/reviews/${review.id}/verify" method="get">
                    <div class="form-group">
                        <label for="verifiedBy">Verified By:</label>
                        <input type="text" id="verifiedBy" name="verifiedBy" required />
                    </div>
                    <button type="submit" class="btn">Verify Review</button>
                </form>
            </div>
        </c:if>
        
        <!-- Moderation Form for PublicReview -->
        <c:if test="${isPublic && !review.moderated}">
            <div class="moderation-form">
                <h3>Moderate This Review</h3>
                <form action="${pageContext.request.contextPath}/reviews/${review.id}/moderate" method="post">
                    <div class="form-group">
                        <label>Decision:</label>
                        <div>
                            <label style="display: inline-block; margin-right: 20px;">
                                <input type="radio" name="approved" value="true" checked /> Approve
                            </label>
                            <label style="display: inline-block;">
                                <input type="radio" name="approved" value="false" /> Reject
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="moderationNotes">Moderation Notes:</label>
                        <textarea id="moderationNotes" name="moderationNotes"></textarea>
                    </div>
                    <button type="submit" class="btn">Submit Moderation</button>
                </form>
            </div>
        </c:if>
        
        <div class="actions">
            <a href="${pageContext.request.contextPath}/reviews" class="btn btn-back">Back to List</a>
            <a href="${pageContext.request.contextPath}/reviews/${review.id}/edit" class="btn btn-edit">Edit</a>
            <a href="${pageContext.request.contextPath}/reviews/${review.id}/delete" class="btn btn-delete" onclick="return confirm('Are you sure you want to delete this review?')">Delete</a>
        </div>
    </div>
</body>
</html>
