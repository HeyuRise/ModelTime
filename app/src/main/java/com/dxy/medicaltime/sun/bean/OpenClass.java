package com.dxy.medicaltime.sun.bean;

import java.util.List;

/**
 * Created by dell-pc on 2016/7/25.
 */
public class OpenClass {

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
        private String name;

        private List<ColumnsBean> columns;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ColumnsBean> getColumns() {
            return columns;
        }

        public void setColumns(List<ColumnsBean> columns) {
            this.columns = columns;
        }

        public static class ColumnsBean {
            private String name;
            private String id;
            private String type;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
