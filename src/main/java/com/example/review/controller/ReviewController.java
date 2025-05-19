package com.example.review.controller;

import com.example.review.model.Review;
import com.example.review.model.VerifiedReview;
import com.example.review.model.PublicReview;
import com.example.review.service.ReviewManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    
    private final ReviewManager reviewManager;
    
    public ReviewController() {
        this.reviewManager = new ReviewManager();
    }
    
    // Display all reviews
    @GetMapping
    public String getAllReviews(Model model) {
        List<Review> reviews = reviewManager.getAllReviews();
        model.addAttribute("reviews", reviews);
        return "review-list";
    }
    
    // Display form to create a new review
    @GetMapping("/new")
    public String showNewReviewForm(Model model) {
        model.addAttribute("review", new Review());
        model.addAttribute("isNew", true);
        return "review-form";
    }
    
    // Display form to create a new verified review
    @GetMapping("/new/verified")
    public String showNewVerifiedReviewForm(Model model) {
        model.addAttribute("review", new VerifiedReview());
        model.addAttribute("isNew", true);
        model.addAttribute("isVerified", true);
        return "review-form";
    }
    
    // Display form to create a new public review
    @GetMapping("/new/public")
    public String showNewPublicReviewForm(Model model) {
        model.addAttribute("review", new PublicReview());
        model.addAttribute("isNew", true);
        model.addAttribute("isPublic", true);
        return "review-form";
    }
    
    // Create a new review
    @PostMapping
    public String createReview(@ModelAttribute Review review, 
                              @RequestParam("reviewType") String reviewType,
                              RedirectAttributes redirectAttributes) {
        
        Review newReview;
        
        switch (reviewType) {
            case "VERIFIED":
                VerifiedReview verifiedReview = new VerifiedReview();
                copyReviewProperties(review, verifiedReview);
                verifiedReview.setType("VERIFIED");
                newReview = verifiedReview;
                break;
            case "PUBLIC":
                PublicReview publicReview = new PublicReview();
                copyReviewProperties(review, publicReview);
                publicReview.setType("PUBLIC");
                newReview = publicReview;
                break;
            default:
                review.setType("STANDARD");
                newReview = review;
        }
        
        reviewManager.addReview(newReview);
        redirectAttributes.addFlashAttribute("message", "Review created successfully!");
        return "redirect:/reviews";
    }
    
    // Display a specific review
    @GetMapping("/{id}")
    public String getReviewById(@PathVariable int id, Model model) {
        Review review = reviewManager.getReviewById(id);
        if (review == null) {
            return "redirect:/reviews";
        }
        model.addAttribute("review", review);
        
        if (review instanceof VerifiedReview) {
            model.addAttribute("isVerified", true);
        } else if (review instanceof PublicReview) {
            model.addAttribute("isPublic", true);
        }
        
        return "review-detail";
    }
    
    // Display form to edit a review
    @GetMapping("/{id}/edit")
    public String showEditReviewForm(@PathVariable int id, Model model) {
        Review review = reviewManager.getReviewById(id);
        if (review == null) {
            return "redirect:/reviews";
        }
        model.addAttribute("review", review);
        model.addAttribute("isNew", false);
        
        if (review instanceof VerifiedReview) {
            model.addAttribute("isVerified", true);
        } else if (review instanceof PublicReview) {
            model.addAttribute("isPublic", true);
        }
        
        return "review-form";
    }
    
    // Update a review
    @PostMapping("/{id}")
    public String updateReview(@PathVariable int id, 
                              @ModelAttribute Review review,
                              RedirectAttributes redirectAttributes) {
        
        Review existingReview = reviewManager.getReviewById(id);
        if (existingReview == null) {
            return "redirect:/reviews";
        }
        
        // Preserve the type and ID
        review.setId(id);
        review.setType(existingReview.getType());
        
        // Handle specific review types
        if (existingReview instanceof VerifiedReview && review instanceof VerifiedReview) {
            VerifiedReview verifiedReview = (VerifiedReview) review;
            VerifiedReview existingVerifiedReview = (VerifiedReview) existingReview;
            verifiedReview.setVerified(existingVerifiedReview.isVerified());
            verifiedReview.setVerifiedBy(existingVerifiedReview.getVerifiedBy());
            verifiedReview.setVerificationDate(existingVerifiedReview.getVerificationDate());
        } else if (existingReview instanceof PublicReview && review instanceof PublicReview) {
            PublicReview publicReview = (PublicReview) review;
            PublicReview existingPublicReview = (PublicReview) existingReview;
            publicReview.setModerated(existingPublicReview.isModerated());
            publicReview.setApproved(existingPublicReview.isApproved());
            publicReview.setModerationNotes(existingPublicReview.getModerationNotes());
        }
        
        reviewManager.updateReview(review);
        redirectAttributes.addFlashAttribute("message", "Review updated successfully!");
        return "redirect:/reviews";
    }
    
    // Delete a review
    @GetMapping("/{id}/delete")
    public String deleteReview(@PathVariable int id, RedirectAttributes redirectAttributes) {
        boolean deleted = reviewManager.deleteReview(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("message", "Review deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to delete review!");
        }
        return "redirect:/reviews";
    }
    
    // Verify a review
    @GetMapping("/{id}/verify")
    public String verifyReview(@PathVariable int id, 
                              @RequestParam String verifiedBy,
                              RedirectAttributes redirectAttributes) {
        
        boolean verified = reviewManager.verifyReview(id, verifiedBy);
        if (verified) {
            redirectAttributes.addFlashAttribute("message", "Review verified successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to verify review!");
        }
        return "redirect:/reviews/" + id;
    }
    
    // Moderate a review
    @PostMapping("/{id}/moderate")
    public String moderateReview(@PathVariable int id,
                                @RequestParam boolean approved,
                                @RequestParam String moderationNotes,
                                RedirectAttributes redirectAttributes) {
        
        boolean moderated = reviewManager.moderateReview(id, approved, moderationNotes);
        if (moderated) {
            redirectAttributes.addFlashAttribute("message", "Review moderated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to moderate review!");
        }
        return "redirect:/reviews/" + id;
    }
    
    // Helper method to copy properties
    private void copyReviewProperties(Review source, Review target) {
        target.setReviewer(source.getReviewer());
        target.setRating(source.getRating());
        target.setComment(source.getComment());
        target.setProductOrVendor(source.getProductOrVendor());
    }
}
