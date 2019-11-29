package com.bw.movie.model.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/19<p>
 * <p>更改时间：2019/11/19<p>
 */
public class MyYYPLBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * address : 前门大街大栅栏街36号
         * cinemaId : 2
         * cinemaName : 大观楼电影院
         * commentTime : 1574162483000
         * distance : 0
         * logo : http://172.17.8.100/images/movie/logo/dgl.jpg
         * myCommentContent : 真好
         */

        public String address;
        public int cinemaId;
        public String cinemaName;
        public long commentTime;
        public int distance;
        public String logo;
        public String myCommentContent;
    }
}
