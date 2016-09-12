package com.dxy.medicaltime.chen.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/25.
 */
public class RecommendHeadView {
        List<Pic>items;

    public RecommendHeadView() {
    }

    public RecommendHeadView(List<Pic> items) {
        this.items = items;
    }

    public List<Pic> getItems() {
        return items;
    }

    public void setItems(List<Pic> items) {
        this.items = items;
    }
}

