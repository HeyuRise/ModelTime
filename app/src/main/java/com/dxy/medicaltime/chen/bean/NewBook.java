package com.dxy.medicaltime.chen.bean;

/**
 * Created by Administrator on 2016/7/25.
 */
public class NewBook {
    String id;
    String cover;
    String title;
    String author;
    String summary;
    String preview;
    String originalPrice;
    String appleId;
    String iosPrice;
    String previewFileId;
    String currentPrice;
    String pushFileId;

    public NewBook() {
    }

    public NewBook(String pushFileId, String currentPrice, String previewFileId, String iosPrice, String originalPrice, String preview, String cover, String id, String title, String author, String summary, String appleId) {
        this.pushFileId = pushFileId;
        this.currentPrice = currentPrice;
        this.previewFileId = previewFileId;
        this.iosPrice = iosPrice;
        this.originalPrice = originalPrice;
        this.preview = preview;
        this.cover = cover;
        this.id = id;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.appleId = appleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getAppleId() {
        return appleId;
    }

    public void setAppleId(String appleId) {
        this.appleId = appleId;
    }

    public String getIosPrice() {
        return iosPrice;
    }

    public void setIosPrice(String iosPrice) {
        this.iosPrice = iosPrice;
    }

    public String getPreviewFileId() {
        return previewFileId;
    }

    public void setPreviewFileId(String previewFileId) {
        this.previewFileId = previewFileId;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getPushFileId() {
        return pushFileId;
    }

    public void setPushFileId(String pushFileId) {
        this.pushFileId = pushFileId;
    }
}
