package com.example.review.model;

// Inheritance - VerifiedReview extends Review
public class VerifiedReview extends Review {
    private boolean verified;
    private String verificationDate;
    private String verifiedBy;

    public VerifiedReview() {
        super();
        this.verified = false;
    }

    public VerifiedReview(int id, String reviewer, int rating, String comment, 
                         String productOrVendor, String type) {
        super(id, reviewer, rating, comment, productOrVendor, type);
        this.verified = false;
    }

    // Additional getters and setters
    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(String verificationDate) {
        this.verificationDate = verificationDate;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    // Polymorphism - overriding the display method
    @Override
    public String display() {
        String baseDisplay = super.display();
        if (verified) {
            return baseDisplay + "\n[VERIFIED by " + verifiedBy + " on " + verificationDate + "]";
        }
        return baseDisplay;
    }

    // Override toFileString to include verification details
    @Override
    public String toFileString() {
        return super.toFileString() + "," + verified + "," + 
               (verificationDate != null ? verificationDate : "") + "," + 
               (verifiedBy != null ? verifiedBy : "");
    }

    // Static method to create from file string
    public static VerifiedReview fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length < 10) {
            throw new IllegalArgumentException("Invalid verified review data format");
        }
        
        Review baseReview = Review.fromFileString(parts[0] + "," + parts[1] + "," + 
                                                parts[2] + "," + parts[3] + "," + 
                                                parts[4] + "," + parts[5] + "," + parts[6]);
        
        VerifiedReview verifiedReview = new VerifiedReview();
        verifiedReview.setId(baseReview.getId());
        verifiedReview.setReviewer(baseReview.getReviewer());
        verifiedReview.setRating(baseReview.getRating());
        verifiedReview.setComment(baseReview.getComment());
        verifiedReview.setDateSubmitted(baseReview.getDateSubmitted());
        verifiedReview.setProductOrVendor(baseReview.getProductOrVendor());
        verifiedReview.setType(baseReview.getType());
        
        verifiedReview.setVerified(Boolean.parseBoolean(parts[7]));
        verifiedReview.setVerificationDate(parts[8]);
        verifiedReview.setVerifiedBy(parts[9]);
        
        return verifiedReview;
    }
}
