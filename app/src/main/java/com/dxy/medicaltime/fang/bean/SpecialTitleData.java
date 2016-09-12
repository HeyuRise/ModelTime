package com.dxy.medicaltime.fang.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/26.
 * 专题标题页
 */
public class SpecialTitleData {

    /**
     * success : true
     * message : [{"pos":1,"name":"用药经验","id":1,"iconUrl":"http://res.dxycdn.com/cms/upload/asset/2016/06/23/B1466672277_1_1.jpg!"},{"pos":2,"name":"检验","id":2,"iconUrl":"http://res.dxycdn.com/cms/upload/asset/2016/06/23/A1466672128_1_1.jpg!"},{"pos":3,"name":"心血管","id":3,"iconUrl":"http://res.dxycdn.com/cms/upload/asset/2016/06/23/B1466672278_1_1.jpg!"},{"pos":4,"name":"肿瘤","id":4,"iconUrl":"http://res.dxycdn.com/cms/upload/asset/2016/06/23/B1466672279_1_1.jpg!"},{"pos":5,"name":"骨科","id":5,"iconUrl":"http://res.dxycdn.com/cms/upload/asset/2016/06/23/B1466672280_1_1.jpg!"},{"pos":6,"name":"神经","id":6,"iconUrl":"http://res.dxycdn.com/cms/upload/asset/2016/06/23/B1466672281_1_1.jpg!"},{"pos":7,"name":"呼吸","id":7,"iconUrl":"http://res.dxycdn.com/cms/upload/asset/2016/06/23/A1466672129_1_1.jpg!"},{"pos":8,"name":"消化","id":8,"iconUrl":"http://res.dxycdn.com/cms/upload/asset/2016/06/23/A1466672130_1_1.jpg!"},{"pos":9,"name":"普外","id":9,"iconUrl":"http://res.dxycdn.com/cms/upload/asset/2016/06/23/A1466672131_1_1.jpg!"},{"pos":10,"name":"内分泌","id":10,"iconUrl":"http://res.dxycdn.com/cms/upload/asset/2016/06/23/B1466672283_1_1.jpg!"},{"pos":11,"name":"妇产科","id":11,"iconUrl":"http://res.dxycdn.com/cms/upload/asset/2016/06/23/A1466672132_1_1.jpg!"},{"pos":12,"name":"儿科","id":12,"iconUrl":"http://res.dxycdn.com/cms/upload/asset/2016/06/23/A1466672133_1_1.jpg!"},{"pos":13,"name":"肾内","id":13,"iconUrl":"http://res.dxycdn.com/cms/upload/asset/2016/06/23/B1466672284_1_1.jpg!"},{"pos":14,"name":"影像","id":14,"iconUrl":"http://res.dxycdn.com/cms/upload/asset/2016/06/23/A1466672134_1_1.jpg!"},{"pos":15,"name":"精神","id":15,"iconUrl":"http://res.dxycdn.com/cms/upload/asset/2016/06/23/A1466672135_1_1.jpg!"},{"pos":16,"name":"急危重症","id":16,"iconUrl":"http://res.dxycdn.com/cms/upload/asset/2016/06/23/B1466672285_1_1.jpg!"},{"pos":17,"name":"医疗新闻","id":17,"iconUrl":"http://res.dxycdn.com/cms/upload/asset/2016/06/23/B1466672286_1_1.jpg!"}]
     * status : 0
     */

    private boolean success;
    private int status;
    /**
     * pos : 1
     * name : 用药经验
     * id : 1
     * iconUrl : http://res.dxycdn.com/cms/upload/asset/2016/06/23/B1466672277_1_1.jpg!
     */

    private List<MessageBean> message;

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

    public List<MessageBean> getMessage() {
        return message;
    }

    public void setMessage(List<MessageBean> message) {
        this.message = message;
    }

    public static class MessageBean {
        private int pos;
        private String name;
        private int id;
        private String iconUrl;

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

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

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }
    }
}
