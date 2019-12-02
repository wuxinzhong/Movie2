package com.bw.movie.constraint;


import com.bw.movie.model.bean.CHaiLeftBean;
import com.bw.movie.model.bean.CHaiRightBean;
import com.bw.movie.model.bean.CTuiJianBean;
import com.bw.movie.model.bean.CommentBean;
import com.bw.movie.model.bean.EmailBean;
import com.bw.movie.model.bean.GuanZhuDianYingBean;
import com.bw.movie.model.bean.GuanZhuYingYuanBean;
import com.bw.movie.model.bean.HomeBanner;
import com.bw.movie.model.bean.MovieXqBean;
import com.bw.movie.model.bean.MovieXqYingpingBean;
import com.bw.movie.model.bean.MovieYuYueBean;
import com.bw.movie.model.bean.MyYPPLBean;
import com.bw.movie.model.bean.MyYYPLBean;
import com.bw.movie.model.bean.PaiQiBean;
import com.bw.movie.model.bean.PaiQiTimeBean;
import com.bw.movie.model.bean.PopularMovieBean;
import com.bw.movie.model.bean.ReYingBean;
import com.bw.movie.model.bean.RegisterBean;

import com.bw.movie.model.bean.SearchBean;
import com.bw.movie.model.bean.ShangYingBean;
import com.bw.movie.model.bean.TICketBean;
import com.bw.movie.model.bean.XLLoginBean;

import com.bw.movie.model.bean.XiaoXiBean;
import com.bw.movie.model.bean.YYGuanZhuBean;
import com.bw.movie.model.bean.YYPingLunBean;
import com.bw.movie.model.bean.YYXiangQingBean;
import com.bw.movie.model.bean.YingYuanLieBiaoBean;
import com.bw.movie.model.bean.YuYueBean;
import com.bw.movie.model.bean.ZiLiaoBean;
import com.bw.movie.view.interfaces.IBaseView;

/**
 * <p>文件描述：契约类<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/5/005<p>
 * <p>更改时间：2019/11/5/005<p>
 */
public interface Constraint {

    //注册
    interface RegisterView extends IBaseView {
        void registerSuccess(RegisterBean registerBean);

        void registerError(String s);

        void emailSuccess(EmailBean emailBean);

        void emailError(String s);
    }

    //登录
    interface ILoginView extends IBaseView {
        void loginSuccess(XLLoginBean xlLoginBean);

        void loginError(String s);
    }

    //首页电影展示
    interface IHomeMovie extends IBaseView {
        //banner
        void bannerSuccess(HomeBanner homeBanner);

        void bannerError(String s);

        //热映
        void reyingSuccess(ReYingBean reYingBean);

        void reyingError(String s);

        //上映
        void shangyingSuccess(ShangYingBean shangYingBean);

        void shangyingError(String s);

        //预约
        void yuyueSuccess(MovieYuYueBean movieYuYueBean);

        void yuyueError(String s);

        //热门
        void popularSuccess(PopularMovieBean popularMovieBean);

        void popularError(String s);
    }

    //更多
    interface IGengDuoRYView extends IBaseView {
        //热映
        void reyingSuccess(ReYingBean reYingBean);

        void reyingError(String s);
    }

    //更多
    interface IGengDuoSYView extends IBaseView {
        //上映
        void shangyingSuccess(ShangYingBean shangYingBean);

        void shangyingError(String s);

        //预约
        void yuyueSuccess(MovieYuYueBean movieYuYueBean);

        void yuyueError(String s);
    }

    //更多
    interface IGengDuoRMView extends IBaseView {
        //热门
        void popularSuccess(PopularMovieBean popularMovieBean);

        void popularError(String s);
    }

    //电影详情
    interface IMovieXqView extends IBaseView {
        void movieXQSuccess(MovieXqBean movieXqBean);

        void movieXQError(String s);
    }

    //影评
    interface IMovieXqYpView extends IBaseView {
        void movieXqYpSuccess(MovieXqYingpingBean movieXqYingpingBean);

        void movieXqYpError(String s);
    }

    //搜索
    interface ISearchView extends IBaseView {
        //搜索
        void searchSuccess(SearchBean searchBean);

        void searchError(String s);
    }

    //附近和推荐
    interface TuiJianView extends IBaseView {
        void tuijianSuccess(CTuiJianBean cTuiJianBean);

        void tuijianError(String s);
    }

    //chaileft
    interface CHaiView extends IBaseView {
        void leftSuccess(CHaiLeftBean leftBean);

        void leftError(String s);

        void rightSuccess(CHaiRightBean rightBean);

        void rightError(String s);
    }

    //影院列表
    interface YingYuanLieBiao extends IBaseView {
        void liebiaoSuccess(YingYuanLieBiaoBean lieBiao);

        void liebiaoError(String s);
    }

    //影院详情
    interface YYXiangQingView extends IBaseView {
        void xiangqingSuccess(YYXiangQingBean xiangQingBean);

        void xiangqingError(String s);

        void guanzhuSuccess(YYGuanZhuBean guanZhuBean);

        void guanzhuError(String s);

        void quguanSuccess(YYGuanZhuBean guanZhuBean);

        void quguanError(String s);
    }

    //影院评论
    interface YYPingJiaView extends IBaseView {
        void pinglunSuccess(YYPingLunBean yyPingLunBean);

        void pinglunError(String s);

        //影院评论点赞
        void dianzanSuccess(YYGuanZhuBean guanZhuBean);

        void dianzanError(String s);
    }

    //用户消息
    interface XiaoXiView extends IBaseView {
        void xiaoxiSuccess(XiaoXiBean xiaoXiBean);

        void xiaoxiError(String s);
    }

    //排期时间
    interface PaiQiTimeView extends IBaseView {
        void paiqitimeSuccess(PaiQiTimeBean paiQiTimeBean);

        void paiqitimeError(String s);
    }

    //排期
    interface PaiQiView extends IBaseView {
        void paiqiSuccess(PaiQiBean paiQiBean);

        void paiqiError(String s);
    }

    //个人资料
    interface ZiLiaoView extends IBaseView {
        void ziliaoSuccess(ZiLiaoBean ziLiaoBean);

        void ziliaoError(String s);

        void shengqiSuccess(YYGuanZhuBean guanZhuBean);

        void shengqiError(String s);
    }

    //我的关注..影院
    interface GuanZhuYingYuanView extends IBaseView {
        void gzyySuccess(GuanZhuYingYuanBean gzyybean);

        void gzyyError(String s);
    }

    //我的关注..电影
    interface GuanZhuDianYingView extends IBaseView {
        void gzdySuccess(GuanZhuDianYingBean gzdyBean);

        void gzdyError(String s);
    }

    //我的预约
    interface YuYueView extends IBaseView {
        void yuyueSuccess(YuYueBean yuYueBean);

        void yuyueError(String s);
    }

    //意见反馈
    interface FanKuiView extends IBaseView {
        void fankuiSuccess(YYGuanZhuBean fankui);

        void fankuiError(String s);
    }

    //我的影片评论
    interface MyypplView extends IBaseView {
        void ypplSuccess(MyYPPLBean ypplBean);

        void ypplError(String s);
    }

    //我的影院评论
    interface MyyyplView extends IBaseView {
        void yyplSuccess(MyYYPLBean yyplBean);

        void yyplError(String s);
    }

    //写影评
    interface IMovieCommentView extends IBaseView {
        void commentSuccess(CommentBean commentBean);

        void commentError(String s);
    }

    //购票记录查询
    interface IDoView extends IBaseView {
        void doViewSuccess(TICketBean tiCketBean);

        void doViewError(String s);
    }
}
