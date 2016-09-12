package com.dxy.medicaltime.chen.bean;

/**
 * Created by Administrator on 2016/7/25.
 */
public class Pic {
        String image;
        String subjectId;
        String count;
        String title;

    public Pic() {
    }

    public Pic(String image, String subjectId, String title, String count) {
        this.image = image;
        this.subjectId = subjectId;
        this.title = title;
        this.count = count;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
}
