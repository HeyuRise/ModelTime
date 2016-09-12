package com.dxy.medicaltime.sun.bean;

import java.util.List;

/**
 * Created by dell-pc on 2016/7/28.
 */
public class Answer {

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
        private boolean answered;
        private String subject;
        private boolean dingdang;
        private int rewardNum;
        private int articleId;
        private String standardAnswer;
        private boolean answerRight;
        private String resolution;
        private String voteTime;
        private String userAnswer;
        private int dingDangNum;
        private ExtendReadBean extendRead;
        private int id;
        private int accum;
        private List<VoteItemsBean> voteItems;

        public boolean isAnswered() {
            return answered;
        }

        public void setAnswered(boolean answered) {
            this.answered = answered;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public boolean isDingdang() {
            return dingdang;
        }

        public void setDingdang(boolean dingdang) {
            this.dingdang = dingdang;
        }

        public int getRewardNum() {
            return rewardNum;
        }

        public void setRewardNum(int rewardNum) {
            this.rewardNum = rewardNum;
        }

        public int getArticleId() {
            return articleId;
        }

        public void setArticleId(int articleId) {
            this.articleId = articleId;
        }

        public String getStandardAnswer() {
            return standardAnswer;
        }

        public void setStandardAnswer(String standardAnswer) {
            this.standardAnswer = standardAnswer;
        }

        public boolean isAnswerRight() {
            return answerRight;
        }

        public void setAnswerRight(boolean answerRight) {
            this.answerRight = answerRight;
        }

        public String getResolution() {
            return resolution;
        }

        public void setResolution(String resolution) {
            this.resolution = resolution;
        }

        public String getVoteTime() {
            return voteTime;
        }

        public void setVoteTime(String voteTime) {
            this.voteTime = voteTime;
        }

        public String getUserAnswer() {
            return userAnswer;
        }

        public void setUserAnswer(String userAnswer) {
            this.userAnswer = userAnswer;
        }

        public int getDingDangNum() {
            return dingDangNum;
        }

        public void setDingDangNum(int dingDangNum) {
            this.dingDangNum = dingDangNum;
        }

        public ExtendReadBean getExtendRead() {
            return extendRead;
        }

        public void setExtendRead(ExtendReadBean extendRead) {
            this.extendRead = extendRead;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAccum() {
            return accum;
        }

        public void setAccum(int accum) {
            this.accum = accum;
        }

        public List<VoteItemsBean> getVoteItems() {
            return voteItems;
        }

        public void setVoteItems(List<VoteItemsBean> voteItems) {
            this.voteItems = voteItems;
        }

        public static class ExtendReadBean {
            private String imgpath;
            private int numOfShared;
            private String articleDate;
            private int id;
            private String title;
            private String url;

            public String getImgpath() {
                return imgpath;
            }

            public void setImgpath(String imgpath) {
                this.imgpath = imgpath;
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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class VoteItemsBean {
            private String index;
            private int id;
            private String option;

            public String getIndex() {
                return index;
            }

            public void setIndex(String index) {
                this.index = index;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOption() {
                return option;
            }

            public void setOption(String option) {
                this.option = option;
            }
        }
    }
}
