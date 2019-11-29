package com.bw.movie.model.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/19<p>
 * <p>更改时间：2019/11/19<p>
 */
public class MyYPPLBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * commentTime : 1574162336000
         * director : 托尼·班克罗夫特
         * imageUrl : http://172.17.8.100/images/movie/stills/sqmxtzdwbg/sqmxtzdwbg1.jpg
         * movieId : 6
         * movieName : 神奇马戏团之动物饼干
         * movieScore : 0
         * myCommentContent : 真好
         * myCommentScore : 0
         * starring : 艾米莉·布朗特,约翰·卡拉辛斯基,西尔维斯特·史泰龙
         */

        public long commentTime;
        public String director;
        public String imageUrl;
        public int movieId;
        public String movieName;
        public int movieScore;
        public String myCommentContent;
        public int myCommentScore;
        public String starring;
    }
}
