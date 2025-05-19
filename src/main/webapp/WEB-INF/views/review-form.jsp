<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${isNew ? 'Create' : 'Edit'} Review</title>
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
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], 
        input[type="number"], 
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
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            border: none;
            cursor: pointer;
            font-size: 16px;
            margin-right: 10px;
        }
        .btn-cancel {
            background-color: #f44336;
        }
        .error {
            color: red;
            font-size: 14px;
            margin-top: 5px;
        }
        .rating-container {
            display: flex;
            flex-direction: row-reverse;
            justify-content: flex-end;
        }
        .rating-container input {
            display: none;
        }
        .rating-container label {
            cursor: pointer;
            width: 30px;
            height: 30px;
            margin-right: 5px;
            position: relative;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .rating-container label:before {
            content: "★";
            font-size: 30px;
            color: #ddd;
        }
        .rating-container input:checked ~ label:before,
        .rating-container label:hover:before,
        .rating-container label:hover ~ label:before {
            color: #FFD700;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>${isNew ? 'Create' : 'Edit'} Review</h1>
        
        <form action="${isNew ? pageContext.request.contextPath.concat('/reviews') : pageContext.request.contextPath.concat('/reviews/').concat(review.id)}" method="post">
            <input type="hidden" name="id" value="${review.id}" />
            
            <c:if test="${isNew}">
                <input type="hidden" name="reviewType" value="${isVerified ? 'VERIFIED' : (isPublic ? 'PUBLIC' : 'STANDARD')}" />
            </c:if>
            
            <div class="form-group">
                <label for="reviewer">Reviewer Name:</label>
                <input type="text" id="reviewer" name="reviewer" value="${review.reviewer}" required />
            </div>
            
            <div class="form-group">
                <label for="productOrVendor">Product/Vendor Name:</label>
                <input type="text" id="productOrVendor" name="productOrVendor" value="${review.productOrVendor}" required />
            </div>
            
            <div class="form-group">
                <label>Rating:</label>
                <div class="rating-container">
                    <input type="radio" id="star5" name="rating" value="5" ${review.rating == 5 ? 'checked' : ''} />
                    <label for="star5" title="5 stars"></label>
                    <input type="radio" id="star4" name="rating" value="4" ${review.rating == 4 ? 'checked' : ''} />
                    <label for="star4" title="4 stars"></label>
                    <input type="radio" id="star3" name="rating" value="3" ${review.rating == 3 ? 'checked' : ''} />
                    <label for="star3" title="3 stars"></label>
                    <input type="radio" id="star2" name="rating" value="2" ${review.rating == 2 ? 'checked' : ''} />
                    <label for="star2" title="2 stars"></label>
                    <input type="radio" id="star1" name="rating" value="1" ${review.rating == 1 ? 'checked' : ''} />
                    <label for="star1" title="1 star"></label>
                </div>
            </div>
            
            <div class="form-group">
                <label for="comment">Comment:</label>
                <textarea id="comment" name="comment" required>${review.comment}</textarea>
            </div>
            
            <div class="form-group">
                <button type="submit" class="btn">${isNew ? 'Create' : 'Update'} Review</button>
                <a href="${pageContext.request.contextPath}/reviews" class="btn btn-cancel">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>
