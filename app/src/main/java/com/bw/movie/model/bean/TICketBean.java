package com.bw.movie.model.bean;

import java.util.List;

public class TICketBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {

        public int amount;
        public long createTime;
        public int id;
        public String imageUrl;
        public String movieName;
        public String orderId;
        public double price;
    }
}
