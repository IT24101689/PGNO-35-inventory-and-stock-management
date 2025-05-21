<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Item</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            text-align: center;
            padding: 50px;
        }
        h1 {
            color: #4CAF50;
        }
        input, textarea {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .button {
            padding: 10px 20px;
            font-size: 16px;
            color: white;
            background-color: #4CAF50;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<h1>Add Item</h1>
<form action="ItemServlet" method="post">
    <input type="hidden" name="action" value="add" />
    <input type="text" name="itemName" placeholder="Item Name" required />
    <textarea name="itemDescription" placeholder="Item Description" required></textarea>
    <input type="number" name="itemPrice" placeholder="Price" required />
    <input type="number" name="itemQuantity" placeholder="Quantity" required />
    <input type="submit" value="Add Item" class="button" />
</form>

</body>
</html>
