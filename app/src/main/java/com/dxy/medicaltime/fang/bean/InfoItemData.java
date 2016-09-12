package com.dxy.medicaltime.fang.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/25.
 * 资讯页listView中的每一项数据
 */
public class InfoItemData {



    private boolean success;
    private String impresionId;

    private MessageBean message;
    private int status;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getImpresionId() {
        return impresionId;
    }

    public void setImpresionId(String impresionId) {
        this.impresionId = impresionId;
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

        private ArrayList<AdListBean> adList;

        private ArrayList<ListBean> list;

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

        public ArrayList<AdListBean> getAdList() {
            return adList;
        }

        public void setAdList(ArrayList<AdListBean> adList) {
            this.adList = adList;
        }

        public ArrayList<ListBean> getList() {
            return list;
        }

        public void setList(ArrayList<ListBean> list) {
            this.list = list;
        }

        public static class AdListBean {
            private String date;
            private int adPos;
            private int tagId;
            private String adImg;
            private boolean fromAsms;
            private int departId;
            private String outLink;
            private int id;
            private String title;
            private int adSort;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getAdPos() {
                return adPos;
            }

            public void setAdPos(int adPos) {
                this.adPos = adPos;
            }

            public int getTagId() {
                return tagId;
            }

            public void setTagId(int tagId) {
                this.tagId = tagId;
            }

            public String getAdImg() {
                return adImg;
            }

            public void setAdImg(String adImg) {
                this.adImg = adImg;
            }

            public boolean isFromAsms() {
                return fromAsms;
            }

            public void setFromAsms(boolean fromAsms) {
                this.fromAsms = fromAsms;
            }

            public int getDepartId() {
                return departId;
            }

            public void setDepartId(int departId) {
                this.departId = departId;
            }

            public String getOutLink() {
                return outLink;
            }

            public void setOutLink(String outLink) {
                this.outLink = outLink;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getAdSort() {
                return adSort;
            }

            public void setAdSort(int adSort) {
                this.adSort = adSort;
            }
        }

        public static class ListBean {
            private int numOfLiked;
            private String author;
            private String appImg;
            private String imgpath;
            private int comrowcount;
            private String description;
            private String firstImg;
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
