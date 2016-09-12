package com.dxy.medicaltime.sun.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by dell-pc on 2016/7/29.
 */
@Entity
public class Question {
    @Id
    private String url;
    private String right;
    private String answered;

    @Generated(hash = 1868476517)
    public Question() {
    }

    @Generated(hash = 274429018)
    public Question(String url, String right, String answered) {
        this.url = url;
        this.right = right;
        this.answered = answered;
    }

    public String getAnswered() {
        return answered;
    }

    public void setAnswered(String answered) {
        this.answered = answered;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
