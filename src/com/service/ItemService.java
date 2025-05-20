package com.service;

import com.model.Item;
import java.io.*;
import java.util.*;

public class ItemService {

    private static final String FILE_NAME = "items.txt";

    // Add new item
    public static void addItem(Item item) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(item.getId() + "," + item.getName() + "," + item.getDescription() + "," + item.getPrice() + "," + item.getQuantity());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update item
    public static void updateItem(Item updatedItem) {
        List<Item> items = getAllItems();
        for (Item item : items) {
            if (item.getId().equals(updatedItem.getId())) {
                item.setName(updatedItem.getName());
                item.setDescription(updatedItem.getDescription());
                item.setPrice(updatedItem.getPrice());
                item.setQuantity(updatedItem.getQuantity());
            }
        }
        saveItemsToFile(items);
    }

    // Delete item
    public static void deleteItem(String itemId) {
        List<Item> items = getAllItems();
        items.removeIf(item -> item.getId().equals(itemId));
        saveItemsToFile(items);
    }

    // Get all items
    public static List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                items.add(new Item(data[0], data[1], data[2], Double.parseDouble(data[3]), Integer.parseInt(data[4])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    // Search items by name
    public static List<Item> searchItems(String searchQuery) {
        List<Item> items = getAllItems();
        List<Item> searchResults = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
                searchResults.add(item);
            }
        }
        return searchResults;
    }

    // Save items list back to file
    private static void saveItemsToFile(List<Item> items) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Item item : items) {
                writer.write(item.getId() + "," + item.getName() + "," + item.getDescription() + "," + item.getPrice() + "," + item.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
