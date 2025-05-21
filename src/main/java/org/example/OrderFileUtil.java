package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderFileUtil {
    private static final String FILE_PATH = "C:\\Users\\User\\IdeaProjects\\OrderManagement\\orders.txt";

    public static List<Order> readOrders() {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Order order = Order.fromCSV(line);
                if (order != null) {
                    orders.add(order);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static void writeOrders(List<Order> orders) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Order order : orders) {
                writer.write(order.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateOrderId() {
        List<Order> orders = readOrders();
        int max = 0;
        for (Order order : orders) {
            String id = order.getOrderId().replace("ORD", "");
            try {
                int num = Integer.parseInt(id);
                if (num > max) {
                    max = num;
                }
            } catch (NumberFormatException ignored) {}
        }
        return String.format("ORD%03d", max + 1);
    }
}