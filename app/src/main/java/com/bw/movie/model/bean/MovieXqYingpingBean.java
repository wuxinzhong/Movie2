package com.bw.movie.model.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/15/015<p>
 * <p>更改时间：2019/11/15/015<p>
 */
public class MovieXqYingpingBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {

        public String commentContent;
        public String commentHeadPic;
        public int commentId;
        public long commentTime;
        public int commentUserId;
        public String commentUserName;
        public int greatNum;
        public int isGreat;
        public int replyNum;
        public double score;
        public List<?> replyHeadPic;
    }
}
