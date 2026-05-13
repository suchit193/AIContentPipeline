package com.contentflow.model;

public class ContentItem {

    private int id;

    private int workspaceId;

    private String platform;

    private String brandName;

    private String brandDetails;

    private String targetAudience;

    private String tone;

    private String contentGoal;

    private String generatedContent;

    private String generatedImageUrl;

    // =====================
    // GETTERS AND SETTERS
    // =====================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(int workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandDetails() {
        return brandDetails;
    }

    public void setBrandDetails(String brandDetails) {
        this.brandDetails = brandDetails;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public String getTone() {
        return tone;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }

    public String getContentGoal() {
        return contentGoal;
    }

    public void setContentGoal(String contentGoal) {
        this.contentGoal = contentGoal;
    }

    public String getGeneratedContent() {
        return generatedContent;
    }

    public void setGeneratedContent(
            String generatedContent) {

        this.generatedContent =
                generatedContent;
    }

    public String getGeneratedImageUrl() {
        return generatedImageUrl;
    }

    public void setGeneratedImageUrl(
            String generatedImageUrl) {

        this.generatedImageUrl =
                generatedImageUrl;
    }
}