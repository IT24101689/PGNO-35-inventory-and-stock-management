package com.example.review.model;

// Inheritance - PublicReview extends Review
public class PublicReview extends Review {
    private boolean moderated;
    private boolean approved;
    private String moderationNotes;

    public PublicReview() {
        super();
        this.moderated = false;
        this.approved = false;
    }

    public PublicReview(int id, String reviewer, int rating, String comment, 
                       String productOrVendor, String type) {
        super(id, reviewer, rating, comment, productOrVendor, type);
        this.moderated = false;
        this.approved = false;
    }

    // Additional getters and setters
    public boolean isModerated() {
        return moderated;
    }

    public void setModerated(boolean moderated) {
        this.moderated = moderated;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getModerationNotes() {
        return moderationNotes;
    }

    public void setModerationNotes(String moderationNotes) {
        this.moderationNotes = moderationNotes;
    }

    // Polymorphism - overriding the display method
    @Override
    public String display() {
        if (!moderated || !approved) {
            return "[PENDING MODERATION] Review #" + getId();
        }
        
        String baseDisplay = super.display();
        return baseDisplay + "\n[PUBLIC]";
    }

    // Admin view with moderation details
    public String displayForAdmin() {
        String baseDisplay = super.display();
        String status = moderated ? (approved ? "APPROVED" : "REJECTED") : "PENDING";
        return baseDisplay + "\nStatus: " + status + 
               (moderationNotes != null ? "\nModeration Notes: " + moderationNotes : "");
    }

    // Override toFileString to include moderation details
    @Override
    public String toFileString() {
        return super.toFileString() + "," + moderated + "," + approved + "," + 
               (moderationNotes != null ? moderationNotes.replace(",", ";;") : "");
    }

    // Static method to create from file string
    public static PublicReview fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length < 10) {
            throw new IllegalArgumentException("Invalid public review data format");
        }
        
        Review baseReview = Review.fromFileString(parts[0] + "," + parts[1] + "," + 
                                                parts[2] + "," + parts[3] + "," + 
                                                parts[4] + "," + parts[5] + "," + parts[6]);
        
        PublicReview publicReview = new PublicReview();
        publicReview.setId(baseReview.getId());
        publicReview.setReviewer(baseReview.getReviewer());
        publicReview.setRating(baseReview.getRating());
        publicReview.setComment(baseReview.getComment());
        publicReview.setDateSubmitted(baseReview.getDateSubmitted());
        publicReview.setProductOrVendor(baseReview.getProductOrVendor());
        publicReview.setType(baseReview.getType());
        
        publicReview.setModerated(Boolean.parseBoolean(parts[7]));
        publicReview.setApproved(Boolean.parseBoolean(parts[8]));
        
        if (parts.length > 9) {
            publicReview.setModerationNotes(parts[9].replace(";;", ","));
        }
        
        return publicReview;
    }
}
