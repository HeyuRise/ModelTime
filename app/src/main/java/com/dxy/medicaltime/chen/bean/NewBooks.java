package com.dxy.medicaltime.chen.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/25.
 */
public class NewBooks {
    String success;

    List<NewBook>items;

    public NewBooks() {
    }

    public NewBooks(String success, List<NewBook> items) {
        this.success = success;
        this.items = items;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<NewBook> getItems() {
        return items;
    }

    public void setItems(List<NewBook> items) {
        this.items = items;
    }
}
