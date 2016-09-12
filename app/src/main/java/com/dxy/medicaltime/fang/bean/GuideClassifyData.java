package com.dxy.medicaltime.fang.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 * 指南分类数据
 */
public class GuideClassifyData  {

    /**
     * success : true
     * impresionId : 220b716a-eaf7-4052-bf2b-02ee30deda83
     * message : [{"children":[{"children":[{"name":"医疗新闻","id":560,"numOfSubscibe":189481,"status":0},{"name":"丁香公开课","id":574,"numOfSubscibe":140507,"status":1},{"name":"会议","id":570,"numOfSubscibe":172145,"status":0}],"name":"医生必备","id":576,"numOfSubscibe":0,"status":0},{"children":[{"name":"心血管","id":290,"numOfSubscibe":185398,"status":0},{"name":"呼吸","id":295,"numOfSubscibe":162126,"status":0},{"name":"消化","id":291,"numOfSubscibe":160478,"status":0},{"name":"内分泌","id":292,"numOfSubscibe":149804,"status":0},{"name":"肾内","id":549,"numOfSubscibe":117069,"status":0},{"name":"血液","id":569,"numOfSubscibe":100606,"status":0}],"name":"内科","id":577,"numOfSubscibe":0,"status":0},{"children":[{"name":"骨科","id":322,"numOfSubscibe":109294,"status":0},{"name":"泌尿","id":551,"numOfSubscibe":81680,"status":0},{"name":"普外","id":556,"numOfSubscibe":96771,"status":0},{"name":"神外","id":559,"numOfSubscibe":75863,"status":0},{"name":"胸外","id":561,"numOfSubscibe":67724,"status":0}],"name":"外科","id":578,"numOfSubscibe":0,"status":0},{"children":[{"name":"妇产科","id":548,"numOfSubscibe":95044,"status":0},{"name":"儿科","id":550,"numOfSubscibe":99225,"status":0}],"name":"妇科 &amp; 儿科","id":579,"numOfSubscibe":0,"status":0},{"children":[{"name":"神经","id":536,"numOfSubscibe":128991,"status":0},{"name":"肿瘤","id":273,"numOfSubscibe":118130,"status":0},{"name":"急危重症","id":565,"numOfSubscibe":119753,"status":0},{"name":"感染","id":299,"numOfSubscibe":102774,"status":0},{"name":"风湿免疫","id":294,"numOfSubscibe":87340,"status":0},{"name":"麻醉","id":553,"numOfSubscibe":56903,"status":0},{"name":"眼科","id":557,"numOfSubscibe":54995,"status":0},{"name":"整形","id":558,"numOfSubscibe":51827,"status":0},{"name":"皮肤","id":562,"numOfSubscibe":74274,"status":0},{"name":"精神","id":563,"numOfSubscibe":56832,"status":0},{"name":"康复","id":567,"numOfSubscibe":62159,"status":0},{"name":"耳鼻喉","id":564,"numOfSubscibe":51660,"status":0}],"name":"其他临床专科","id":580,"numOfSubscibe":0,"status":0},{"children":[{"name":"影像","id":552,"numOfSubscibe":120729,"status":0},{"name":"药品汇","id":543,"numOfSubscibe":176107,"status":0},{"name":"口腔","id":566,"numOfSubscibe":47790,"status":0},{"name":"超声","id":568,"numOfSubscibe":67059,"status":0},{"name":"论文基金","id":541,"numOfSubscibe":84867,"status":0},{"name":"丁香六度","id":542,"numOfSubscibe":59386,"status":0},{"name":"健康互联","id":544,"numOfSubscibe":58730,"status":0},{"name":"调查派","id":554,"numOfSubscibe":49554,"status":0},{"name":"医疗器械","id":571,"numOfSubscibe":40126,"status":0},{"name":"检验","id":573,"numOfSubscibe":51703,"status":0},{"name":"护理","id":575,"numOfSubscibe":19935,"status":0}],"name":"其他","id":581,"numOfSubscibe":0,"status":0}],"name":"所有专科","id":267,"numOfSubscibe":0,"status":0}]
     * status : 0
     */

    private boolean success;
    private String impresionId;
    private int status;
    /**
     * children : [{"children":[{"name":"医疗新闻","id":560,"numOfSubscibe":189481,"status":0},{"name":"丁香公开课","id":574,"numOfSubscibe":140507,"status":1},{"name":"会议","id":570,"numOfSubscibe":172145,"status":0}],"name":"医生必备","id":576,"numOfSubscibe":0,"status":0},{"children":[{"name":"心血管","id":290,"numOfSubscibe":185398,"status":0},{"name":"呼吸","id":295,"numOfSubscibe":162126,"status":0},{"name":"消化","id":291,"numOfSubscibe":160478,"status":0},{"name":"内分泌","id":292,"numOfSubscibe":149804,"status":0},{"name":"肾内","id":549,"numOfSubscibe":117069,"status":0},{"name":"血液","id":569,"numOfSubscibe":100606,"status":0}],"name":"内科","id":577,"numOfSubscibe":0,"status":0},{"children":[{"name":"骨科","id":322,"numOfSubscibe":109294,"status":0},{"name":"泌尿","id":551,"numOfSubscibe":81680,"status":0},{"name":"普外","id":556,"numOfSubscibe":96771,"status":0},{"name":"神外","id":559,"numOfSubscibe":75863,"status":0},{"name":"胸外","id":561,"numOfSubscibe":67724,"status":0}],"name":"外科","id":578,"numOfSubscibe":0,"status":0},{"children":[{"name":"妇产科","id":548,"numOfSubscibe":95044,"status":0},{"name":"儿科","id":550,"numOfSubscibe":99225,"status":0}],"name":"妇科 &amp; 儿科","id":579,"numOfSubscibe":0,"status":0},{"children":[{"name":"神经","id":536,"numOfSubscibe":128991,"status":0},{"name":"肿瘤","id":273,"numOfSubscibe":118130,"status":0},{"name":"急危重症","id":565,"numOfSubscibe":119753,"status":0},{"name":"感染","id":299,"numOfSubscibe":102774,"status":0},{"name":"风湿免疫","id":294,"numOfSubscibe":87340,"status":0},{"name":"麻醉","id":553,"numOfSubscibe":56903,"status":0},{"name":"眼科","id":557,"numOfSubscibe":54995,"status":0},{"name":"整形","id":558,"numOfSubscibe":51827,"status":0},{"name":"皮肤","id":562,"numOfSubscibe":74274,"status":0},{"name":"精神","id":563,"numOfSubscibe":56832,"status":0},{"name":"康复","id":567,"numOfSubscibe":62159,"status":0},{"name":"耳鼻喉","id":564,"numOfSubscibe":51660,"status":0}],"name":"其他临床专科","id":580,"numOfSubscibe":0,"status":0},{"children":[{"name":"影像","id":552,"numOfSubscibe":120729,"status":0},{"name":"药品汇","id":543,"numOfSubscibe":176107,"status":0},{"name":"口腔","id":566,"numOfSubscibe":47790,"status":0},{"name":"超声","id":568,"numOfSubscibe":67059,"status":0},{"name":"论文基金","id":541,"numOfSubscibe":84867,"status":0},{"name":"丁香六度","id":542,"numOfSubscibe":59386,"status":0},{"name":"健康互联","id":544,"numOfSubscibe":58730,"status":0},{"name":"调查派","id":554,"numOfSubscibe":49554,"status":0},{"name":"医疗器械","id":571,"numOfSubscibe":40126,"status":0},{"name":"检验","id":573,"numOfSubscibe":51703,"status":0},{"name":"护理","id":575,"numOfSubscibe":19935,"status":0}],"name":"其他","id":581,"numOfSubscibe":0,"status":0}]
     * name : 所有专科
     * id : 267
     * numOfSubscibe : 0
     * status : 0
     */

    private List<MessageBean> message;

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
        private String name;
        private int id;
        private int numOfSubscibe;
        private int status;
        /**
         * children : [{"name":"医疗新闻","id":560,"numOfSubscibe":189481,"status":0},{"name":"丁香公开课","id":574,"numOfSubscibe":140507,"status":1},{"name":"会议","id":570,"numOfSubscibe":172145,"status":0}]
         * name : 医生必备
         * id : 576
         * numOfSubscibe : 0
         * status : 0
         */

        private List<ChildrenBean> children;

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

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            private String name;
            private int id;
            private int numOfSubscibe;
            private int status;
            /**
             * name : 医疗新闻
             * id : 560
             * numOfSubscibe : 189481
             * status : 0
             */

            private List<ChildrenBean1> children;

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

            public List<ChildrenBean1> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean1> children) {
                this.children = children;
            }

            public static class ChildrenBean1 {
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
    }
}
