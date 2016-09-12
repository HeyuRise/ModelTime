package com.dxy.medicaltime.sun.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2016/7/29.
 * 数据库收藏数据
 */
@Entity
public class CollectionData  {

    @Id
    private long id;
    private String title;
    private String url;
    private String img;
    private String time;

    @Generated(hash = 1584283732)
    public CollectionData() {
    }

    @Generated(hash = 902799682)
    public CollectionData(long id, String title, String url, String img, String time) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.img = img;
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
