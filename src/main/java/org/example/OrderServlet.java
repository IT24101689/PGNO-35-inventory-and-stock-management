package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/orderServlet")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        List<Order> orders = OrderFileUtil.readOrders();

        switch (action) {
            case "add":
                String productId = request.getParameter("productId");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                String status = request.getParameter("status");

                String newOrderId = generateNextOrderId(orders);
                Order newOrder = new Order(newOrderId, productId, quantity, status);
                orders.add(newOrder);
                break;

            case "update":
                String updateOrderId = request.getParameter("orderId");
                String newStatus = request.getParameter("status");

                for (Order o : orders) {
                    if (o.getOrderId().equals(updateOrderId)) {
                        o.setStatus(newStatus);
                        break;
                    }
                }
                break;

            case "delete":
                String deleteOrderId = request.getParameter("orderId");
                orders.removeIf(o -> o.getOrderId().equals(deleteOrderId));
                break;

            default:
                break;
        }

        OrderQueue queue = new OrderQueue();
        for (Order o : orders) {
            queue.enqueue(o);
        }
        queue.sortByStatus();

        orders = Arrays.asList(queue.toArray());
        OrderFileUtil.writeOrders(orders);

        response.sendRedirect("viewOrders.jsp");
    }

    private String generateNextOrderId(List<Order> orders) {
        int maxId = 0;

        for (Order order : orders) {
            String idStr = order.getOrderId().replaceAll("[^0-9]", "");
            if (!idStr.isEmpty()) {
                int idNum = Integer.parseInt(idStr);
                if (idNum > maxId) {
                    maxId = idNum;
                }
            }
        }

        int nextId = maxId + 1;
        return String.format("ORD%03d", nextId);
    }
}
