package com.dxy.medicaltime.fang.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/29.
 * 第二个专题详情页的数据
 */
public class SecondSpecialDetailData {


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
            private int numOfLiked;
            private String author;
            private String appImg;
            private String imgpath;
            private int comrowcount;
            private String description;
            private String firstImg;
            private String voteid;
            private String shortTitle;
            private String title;
            private boolean appTitlePic;
            private String url;
            private boolean appTop;
            private int numOfShared;
            private String articleDate;
            private String resultSource;
            private int id;
            private int numOfHits;

            public int getNumOfLiked() {
                return numOfLiked;
            }

            public void setNumOfLiked(int numOfLiked) {
                this.numOfLiked = numOfLiked;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getAppImg() {
                return appImg;
            }

            public void setAppImg(String appImg) {
                this.appImg = appImg;
            }

            public String getImgpath() {
                return imgpath;
            }

            public void setImgpath(String imgpath) {
                this.imgpath = imgpath;
            }

            public int getComrowcount() {
                return comrowcount;
            }

            public void setComrowcount(int comrowcount) {
                this.comrowcount = comrowcount;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getFirstImg() {
                return firstImg;
            }

            public void setFirstImg(String firstImg) {
                this.firstImg = firstImg;
            }

            public String getVoteid() {
                return voteid;
            }

            public void setVoteid(String voteid) {
                this.voteid = voteid;
            }

            public String getShortTitle() {
                return shortTitle;
            }

            public void setShortTitle(String shortTitle) {
                this.shortTitle = shortTitle;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public boolean isAppTitlePic() {
                return appTitlePic;
            }

            public void setAppTitlePic(boolean appTitlePic) {
                this.appTitlePic = appTitlePic;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isAppTop() {
                return appTop;
            }

            public void setAppTop(boolean appTop) {
                this.appTop = appTop;
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

            public void setArticleDate(String articleDate) {
                this.articleDate = articleDate;
            }

            public String getResultSource() {
                return resultSource;
            }

            public void setResultSource(String resultSource) {
                this.resultSource = resultSource;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getNumOfHits() {
                return numOfHits;
            }

            public void setNumOfHits(int numOfHits) {
                this.numOfHits = numOfHits;
            }
        }
    }
}
