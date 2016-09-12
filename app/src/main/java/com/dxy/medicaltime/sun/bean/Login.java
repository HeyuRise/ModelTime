package com.dxy.medicaltime.sun.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by dell-pc on 2016/7/29.
 */
@Entity
public class Login {
    @Id
    private String name;
    private String url;

    @Generated(hash = 1827378950)
    public Login() {
    }

    @Generated(hash = 1566087701)
    public Login(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
