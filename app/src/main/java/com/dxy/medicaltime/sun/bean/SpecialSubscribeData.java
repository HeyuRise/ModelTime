package com.dxy.medicaltime.sun.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2016/7/30.
 * 专题订阅数据模型
 */
@Entity
public class SpecialSubscribeData  {

    @Id
   private long cardId;
    private String cardTitle;
    private String img;

    @Generated(hash = 357872908)
    public SpecialSubscribeData() {
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCardTitle() {
        return this.cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public long getCardId() {
        return this.cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    @Generated(hash = 870619623)
    public SpecialSubscribeData(long cardId, String cardTitle, String img) {
        this.cardId = cardId;
        this.cardTitle = cardTitle;
        this.img = img;
    }
}
