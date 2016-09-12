package com.dxy.medicaltime.chen.bean;

/**
 * Created by Administrator on 2016/7/27.
 */
public class TheOneBookContent {
    String preview;
    String previewFileId;
    String pushMd5;
    String originalPrice;
    String author;
    String purchase;
    String length;
    String description;
    String currentPrice;
    String title;
    String appleId;
    String cover;
    String previewMd5;
    String iosPrice;
    String pushFileId;
    String id;

    public TheOneBookContent() {
    }

    public TheOneBookContent(String preview, String previewFileId, String pushMd5, String purchase, String originalPrice, String author, String length, String description, String currentPrice, String title, String appleId, String cover, String iosPrice, String previewMd5, String pushFileId, String id) {
        this.preview = preview;
        this.previewFileId = previewFileId;
        this.pushMd5 = pushMd5;
        this.purchase = purchase;
        this.originalPrice = originalPrice;
        this.author = author;
        this.length = length;
        this.description = description;

        this.currentPrice = currentPrice;
        this.title = title;
        this.appleId = appleId;
        this.cover = cover;
        this.iosPrice = iosPrice;
        this.previewMd5 = previewMd5;
        this.pushFileId = pushFileId;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPushFileId() {
        return pushFileId;
    }

    public void setPushFileId(String pushFileId) {
        this.pushFileId = pushFileId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getPreviewFileId() {
        return previewFileId;
    }

    public void setPreviewFileId(String previewFileId) {
        this.previewFileId = previewFileId;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getPushMd5() {
        return pushMd5;
    }

    public void setPushMd5(String pushMd5) {
        this.pushMd5 = pushMd5;
    }

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getAppleId() {
        return appleId;
    }

    public void setAppleId(String appleId) {
        this.appleId = appleId;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPreviewMd5() {
        return previewMd5;
    }

    public void setPreviewMd5(String previewMd5) {
        this.previewMd5 = previewMd5;
    }

    public String getIosPrice() {
        return iosPrice;
    }

    public void setIosPrice(String iosPrice) {
        this.iosPrice = iosPrice;
    }
}
