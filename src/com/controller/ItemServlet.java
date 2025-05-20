package com.controller;

import com.model.Item;
import com.service.ItemService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;

public class ItemServlet extends HttpServlet {

    // Handle POST requests (Add, Update, Delete)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String name = request.getParameter("itemName");
            String description = request.getParameter("itemDescription");
            double price = Double.parseDouble(request.getParameter("itemPrice"));
            int quantity = Integer.parseInt(request.getParameter("itemQuantity"));

            Item item = new Item(name, description, price, quantity);
            ItemService.addItem(item);
            response.sendRedirect("itemList.jsp");
        } else if ("update".equals(action)) {
            String itemId = request.getParameter("itemId");
            String name = request.getParameter("itemName");
            String description = request.getParameter("itemDescription");
            double price = Double.parseDouble(request.getParameter("itemPrice"));
            int quantity = Integer.parseInt(request.getParameter("itemQuantity"));

            Item item = new Item(itemId, name, description, price, quantity);
            ItemService.updateItem(item);
            response.sendRedirect("itemList.jsp");
        } else if ("delete".equals(action)) {
            String itemId = request.getParameter("itemId");
            ItemService.deleteItem(itemId);
            response.sendRedirect("itemList.jsp");
        }
    }

    // Handle GET requests (Display items or search)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");
        List<Item> items;
        if (searchQuery != null && !searchQuery.isEmpty()) {
            items = ItemService.searchItems(searchQuery);
        } else {
            items = ItemService.getAllItems();
        }
        request.setAttribute("items", items);
        RequestDispatcher dispatcher = request.getRequestDispatcher("itemList.jsp");
        dispatcher.forward(request, response);
    }
}
