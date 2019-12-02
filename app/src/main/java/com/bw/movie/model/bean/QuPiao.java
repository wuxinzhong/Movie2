package com.bw.movie.model.bean;


public class QuPiao {

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {


        private String exchangeCode;
        private int id;
        private int recordId;
        private int status;

        public String getExchangeCode() {
            return exchangeCode;
        }

        public void setExchangeCode(String exchangeCode) {
            this.exchangeCode = exchangeCode;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
