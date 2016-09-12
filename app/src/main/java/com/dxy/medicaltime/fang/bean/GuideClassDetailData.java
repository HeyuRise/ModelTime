package com.dxy.medicaltime.fang.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 * 指南分类详情数据
 */
public class GuideClassDetailData {

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
        private int pages;
        private int limit;
        private int pge;

        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
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
            private String author;
            private String imgpath;
            private String description;
            private String title;
            private String url;
            private int numOfShared;
            private int id;
            private String articleDate;

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }


            public String getImgpath() {
                return imgpath;
            }

            public void setImgpath(String imgpath) {
                this.imgpath = imgpath;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }


            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }


            public int getNumOfShared() {
                return numOfShared;
            }

            public void setNumOfShared(int numOfShared) {
                this.numOfShared = numOfShared;
            }

            public String getArticleDate() {
                return articleDate;
            }

            public void setArticleDate(String articleDate){
                this.articleDate =articleDate;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

        }
    }
}
