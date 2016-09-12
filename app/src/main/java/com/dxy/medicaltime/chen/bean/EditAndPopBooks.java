package com.dxy.medicaltime.chen.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/26.
 */
public class EditAndPopBooks {
    List<NewBook>books;

    public EditAndPopBooks(List<NewBook> books) {
        this.books = books;
    }

    public List<NewBook> getBooks() {
        return books;
    }

    public void setBooks(List<NewBook> books) {
        this.books = books;
    }
}
