package com.dxy.medicaltime.fang.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/25.
 * 资讯标题栏数据
 */
public class TitleData {


    private boolean success;
    private int status;

    private ArrayList<MessageBean> message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<MessageBean> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<MessageBean> message) {
        this.message = message;
    }

    public static class MessageBean {
        private String name;
        private int id;
        private int numOfSubscibe;
        private int status;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNumOfSubscibe() {
            return numOfSubscibe;
        }

        public void setNumOfSubscibe(int numOfSubscibe) {
            this.numOfSubscibe = numOfSubscibe;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
