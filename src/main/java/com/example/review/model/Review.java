package com.example.review.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Base Review class demonstrating encapsulation
public class Review implements Serializable {
    // Private fields - encapsulation
    private int id;
    private String reviewer;
    private int rating;
    private String comment;
    private LocalDateTime dateSubmitted;
    private String productOrVendor;
    private String type;

    // Constructor
    public Review() {
        this.dateSubmitted = LocalDateTime.now();
    }

    public Review(int id, String reviewer, int rating, String comment, String productOrVendor, String type) {
        this.id = id;
        this.reviewer = reviewer;
        this.rating = rating;
        this.comment = comment;
        this.dateSubmitted = LocalDateTime.now();
        this.productOrVendor = productOrVendor;
        this.type = type;
    }

    // Getters and setters - encapsulation
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            this.rating = rating;
        } else {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(LocalDateTime dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public String getProductOrVendor() {
        return productOrVendor;
    }

    public void setProductOrVendor(String productOrVendor) {
        this.productOrVendor = productOrVendor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Polymorphic method to be overridden by subclasses
    public String display() {
        return "Review #" + id + " - " + rating + "/5 stars by " + reviewer + 
               " on " + getFormattedDate() + "\nComment: " + comment;
    }

    // Helper method
    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateSubmitted.format(formatter);
    }

    // For storage in text file
    public String toFileString() {
        return id + "," + reviewer + "," + rating + "," + comment + "," + 
               dateSubmitted + "," + productOrVendor + "," + type;
    }

    // For creating from text file
    public static Review fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length < 7) {
            throw new IllegalArgumentException("Invalid review data format");
        }
        
        Review review = new Review();
        review.setId(Integer.parseInt(parts[0]));
        review.setReviewer(parts[1]);
        review.setRating(Integer.parseInt(parts[2]));
        review.setComment(parts[3]);
        review.setDateSubmitted(LocalDateTime.parse(parts[4]));
        review.setProductOrVendor(parts[5]);
        review.setType(parts[6]);
        
        return review;
    }
}
