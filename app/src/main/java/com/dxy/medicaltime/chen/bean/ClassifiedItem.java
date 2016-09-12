package com.dxy.medicaltime.chen.bean;

/**
 * Created by Administrator on 2016/7/26.
 */
public class ClassifiedItem {
    String image;
    String name;
    String count;
    String subjectId;

    public ClassifiedItem(String count, String image, String name, String subjectId) {
        this.count = count;
        this.image = image;
        this.name = name;
        this.subjectId = subjectId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
