package com.dxy.medicaltime.chen.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/26.
 */
public class Classified {
    List<ClassifiedItem>items;

    public Classified(List<ClassifiedItem> items) {
        this.items = items;
    }

    public List<ClassifiedItem> getItems() {
        return items;
    }

    public void setItems(List<ClassifiedItem> items) {
        this.items = items;
    }
}
