package com.dxy.medicaltime.sun.bean;

import java.util.List;

/**
 * Created by dell-pc on 2016/7/29.
 */
public class Most {

    private boolean success;
    private MessageBean message;
    private int status;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class MessageBean {
        private int total;
        private int limit;
        private int pge;

        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getPge() {
            return pge;
        }

        public void setPge(int pge) {
            this.pge = pge;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int completeNum;
            private String userPhoto;
            private int questionNum;
            private int rightNum;
            private String username;

            public int getCompleteNum() {
                return completeNum;
            }

            public void setCompleteNum(int completeNum) {
                this.completeNum = completeNum;
            }

            public String getUserPhoto() {
                return userPhoto;
            }

            public void setUserPhoto(String userPhoto) {
                this.userPhoto = userPhoto;
            }

            public int getQuestionNum() {
                return questionNum;
            }

            public void setQuestionNum(int questionNum) {
                this.questionNum = questionNum;
            }

            public int getRightNum() {
                return rightNum;
            }

            public void setRightNum(int rightNum) {
                this.rightNum = rightNum;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
