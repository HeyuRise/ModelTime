package com.dxy.medicaltime.sun.bean;

import java.util.List;

/**
 * Created by dell-pc on 2016/7/28.
 */
public class QuestionItem {
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
            private int articleId;
            private int voteId;
            private String title;

            public int getArticleId() {
                return articleId;
            }

            public void setArticleId(int articleId) {
                this.articleId = articleId;
            }

            public int getVoteId() {
                return voteId;
            }

            public void setVoteId(int voteId) {
                this.voteId = voteId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
