package Rates;

import java.util.Date;

public class Review {
    private String userName;
    private String topic_id;
    private String review_id;
    private String review_type;
    private String fileUpload;
    private String reviewText;
    private String date;
    private boolean anonymous;

    /**
     * Review object that holds information gathered from the reviewPanel
     *
     * Holds username, main text, date and anonymous state of the user created review
     *
     */
    public Review() {
    }

    public String getReview_type() {
        return review_type;
    }

    public void setReview_type(String review_type) {
        this.review_type = review_type;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getReview_id() {
        return review_id;
    }

    public void setReview_id(String review_id) {
        this.review_id = review_id;
    }

    public String getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(String fileUpload) {
        this.fileUpload = fileUpload;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public String getUserName() {
        return userName;
    }

    public String getReviewText() { return reviewText; }

    public String getDate() {
        return date;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    @Override
    public String toString() {
        return "Review ID : " + getReview_id() + " || Review Type : " + getReview_type() + " || Review Date : " + getDate();
    }
}
