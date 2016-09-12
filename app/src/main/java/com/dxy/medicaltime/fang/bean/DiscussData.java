package com.dxy.medicaltime.fang.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/30.
 * 评论数据
 */
public class DiscussData {


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
            private String head;
            private int bury;
            private String created;
            private int digg;
            private int id;
            private String body;
            private String username;

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }

            public int getBury() {
                return bury;
            }

            public void setBury(int bury) {
                this.bury = bury;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public int getDigg() {
                return digg;
            }

            public void setDigg(int digg) {
                this.digg = digg;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
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
