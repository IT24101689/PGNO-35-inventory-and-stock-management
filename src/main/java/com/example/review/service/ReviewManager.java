package com.example.review.service;

import com.example.review.model.Review;
import com.example.review.model.VerifiedReview;
import com.example.review.model.PublicReview;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Abstraction - ReviewManager handles the complex operations
public class ReviewManager {
    private static final String REVIEWS_FILE = "reviews.txt";
    private List<Review> reviews;
    private static int nextId = 1;

    public ReviewManager() {
        this.reviews = new ArrayList<>();
        loadReviews();
    }

    // Create operation
    public Review addReview(Review review) {
        if (review.getId() == 0) {
            review.setId(getNextId());
        }
        reviews.add(review);
        saveReviews();
        return review;
    }

    // Read operations
    public List<Review> getAllReviews() {
        return new ArrayList<>(reviews);
    }

    public Review getReviewById(int id) {
        return reviews.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Review> getReviewsByReviewer(String reviewer) {
        return reviews.stream()
                .filter(r -> r.getReviewer().equals(reviewer))
                .collect(Collectors.toList());
    }

    public List<Review> getReviewsByProductOrVendor(String productOrVendor) {
        return reviews.stream()
                .filter(r -> r.getProductOrVendor().equals(productOrVendor))
                .collect(Collectors.toList());
    }

    // Update operation
    public boolean updateReview(Review updatedReview) {
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getId() == updatedReview.getId()) {
                reviews.set(i, updatedReview);
                saveReviews();
                return true;
            }
        }
        return false;
    }

    // Delete operation
    public boolean deleteReview(int id) {
        boolean removed = reviews.removeIf(r -> r.getId() == id);
        if (removed) {
            saveReviews();
        }
        return removed;
    }

    // Verify a review (for VerifiedReview)
    public boolean verifyReview(int id, String verifiedBy) {
        Review review = getReviewById(id);
        if (review instanceof VerifiedReview) {
            VerifiedReview verifiedReview = (VerifiedReview) review;
            verifiedReview.setVerified(true);
            verifiedReview.setVerifiedBy(verifiedBy);
            verifiedReview.setVerificationDate(LocalDateTime.now().toString());
            saveReviews();
            return true;
        }
        return false;
    }

    // Moderate a review (for PublicReview)
    public boolean moderateReview(int id, boolean approved, String moderationNotes) {
        Review review = getReviewById(id);
        if (review instanceof PublicReview) {
            PublicReview publicReview = (PublicReview) review;
            publicReview.setModerated(true);
            publicReview.setApproved(approved);
            publicReview.setModerationNotes(moderationNotes);
            saveReviews();
            return true;
        }
        return false;
    }

    // File operations
    private void loadReviews() {
        try {
            if (!Files.exists(Paths.get(REVIEWS_FILE))) {
                Files.createFile(Paths.get(REVIEWS_FILE));
                return;
            }

            List<String> lines = Files.readAllLines(Paths.get(REVIEWS_FILE));
            for (String line : lines) {
                if (line.trim().isEmpty()) continue;
                
                String[] parts = line.split(",");
                if (parts.length < 7) continue;
                
                String type = parts[6];
                Review review;
                
                switch (type) {
                    case "VERIFIED":
                        review = VerifiedReview.fromFileString(line);
                        break;
                    case "PUBLIC":
                        review = PublicReview.fromFileString(line);
                        break;
                    default:
                        review = Review.fromFileString(line);
                }
                
                reviews.add(review);
                if (review.getId() >= nextId) {
                    nextId = review.getId() + 1;
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading reviews: " + e.getMessage());
        }
    }

    private void saveReviews() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REVIEWS_FILE))) {
            for (Review review : reviews) {
                writer.write(review.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving reviews: " + e.getMessage());
        }
    }

    private int getNextId() {
        return nextId++;
    }
}
