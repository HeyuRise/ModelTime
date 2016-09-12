package com.dxy.medicaltime.sun.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by dell-pc on 2016/7/27.
 */
@Entity
public class SectionHeyu {
    @Id
    private long id;
    private String name;

    @Generated(hash = 1145619357)
    public SectionHeyu() {
    }

    @Generated(hash = 1767030165)
    public SectionHeyu(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
