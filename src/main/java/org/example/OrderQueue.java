package org.example;

import java.util.LinkedList;

public class OrderQueue {
    private LinkedList<Order> queue;

    public OrderQueue() {
        queue = new LinkedList<>();
    }

    public void enqueue(Order order) {
        queue.addLast(order);
    }

    public Order dequeue() {
        return queue.pollFirst();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void sortByStatus() {
        int n = queue.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Order o1 = queue.get(j);
                Order o2 = queue.get(j + 1);
                if (statusPriority(o1.getStatus()) > statusPriority(o2.getStatus())) {
                    queue.set(j, o2);
                    queue.set(j + 1, o1);
                }
            }
        }
    }

    public Order[] toArray() {
        return queue.toArray(new Order[0]);
    }

    private int statusPriority(String status) {
        if (status == null) {
            return 5;
        }
        switch (status) {
            case "Pending":
                return 1;
            case "Shipped":
                return 2;
            case "Delivered":
                return 3;
            case "Canceled":
                return 4;
            default:
                return 5;
        }
    }
}



