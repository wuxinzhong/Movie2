package com.bw.movie.constant;


import com.bw.movie.model.bean.CHaiLeftBean;
import com.bw.movie.model.bean.CHaiRightBean;
import com.bw.movie.model.bean.CTuiJianBean;
import com.bw.movie.model.bean.CommentBean;
import com.bw.movie.model.bean.EmailBean;
import com.bw.movie.model.bean.GuanZhuDianYingBean;
import com.bw.movie.model.bean.GuanZhuYingYuanBean;
import com.bw.movie.model.bean.HomeBanner;
import com.bw.movie.model.bean.MovieTicketsBean;
import com.bw.movie.model.bean.MovieXqBean;
import com.bw.movie.model.bean.MovieXqYingpingBean;
import com.bw.movie.model.bean.MovieYuYueBean;
import com.bw.movie.model.bean.MyYPPLBean;
import com.bw.movie.model.bean.MyYYPLBean;
import com.bw.movie.model.bean.PaiQiBean;
import com.bw.movie.model.bean.PaiQiTimeBean;
import com.bw.movie.model.bean.PayBean;
import com.bw.movie.model.bean.PopularMovieBean;
import com.bw.movie.model.bean.ReYingBean;
import com.bw.movie.model.bean.RegisterBean;

import com.bw.movie.model.bean.SearchBean;
import com.bw.movie.model.bean.ShangYingBean;
import com.bw.movie.model.bean.XLLoginBean;
import com.bw.movie.model.bean.XiaoXiBean;
import com.bw.movie.model.bean.YYGuanZhuBean;
import com.bw.movie.model.bean.YYPingLunBean;
import com.bw.movie.model.bean.YYXiangQingBean;
import com.bw.movie.model.bean.YingYuanLieBiaoBean;
import com.bw.movie.model.bean.YuYueBean;
import com.bw.movie.model.bean.ZiLiaoBean;
import com.bw.movie.view.activity.YingYuanXiangQing;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * <p>文件描述：接口<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/5/005<p>
 * <p>更改时间：2019/11/5/005<p>
 */
public interface Constant {
    //注册
    @POST("movieApi/user/v2/register")
    @FormUrlEncoded
    Observable<RegisterBean> REGISTER(@Field("nickName") String nickName,
                                      @Field("pwd") String pwd,
                                      @Field("email") String email,
                                      @Field("code") String code);

    //获取邮箱验证码
    @POST("movieApi/user/v2/sendOutEmailCode")
    @FormUrlEncoded
    Observable<EmailBean> SENDOUTEMAILCODE(@Field("email") String email);

    //登录接口
    @FormUrlEncoded
    @POST("movieApi/user/v2/login")
    Observable<XLLoginBean> XL_LOGIN_BEAN(@Field("email") String email,
                                          @Field("pwd") String pwd);

    //banner
    @GET("movieApi/tool/v2/banner")
    Observable<HomeBanner> HOME_BANNER();

    //热门电影
    @GET("movieApi/movie/v2/findHotMovieList")
    Observable<PopularMovieBean> POPULAR_MOVIE_BEAN(@Query("page") int page,
                                                    @Query("count") int count);

    //正在热映
    @GET("movieApi/movie/v2/findReleaseMovieList")
    Observable<ReYingBean> RE_YING_BEAN(@Query("page") int page,
                                        @Query("count") int count);

    //根据关键字查询电影
    @GET("movieApi/movie/v2/findMovieByKeyword")
    Observable<SearchBean> SEARCH_BEAN(@Query("keyword") String keyword,
                                       @Query("page") int page,
                                       @Query("count") int count);


    //即将上映
    @GET("movieApi/movie/v2/findComingSoonMovieList")
    Observable<ShangYingBean> SHANG_YING_BEAN(@Header("sessionId") String sessionId,
                                              @Header("userId") int userId,
                                              @Query("page") int page,
                                              @Query("count") int count);

    //预约
    @FormUrlEncoded
    @POST("movieApi/movie/v2/verify/reserve")
    Observable<MovieYuYueBean> MOVIE_YU_YUE_BEAN(@Header("sessionId") String sessionId,
                                                 @Header("userId") int userId,
                                                 @Field("movieId") int movieId);

    //电影详情
    @GET("movieApi/movie/v2/findMoviesDetail")
    Observable<MovieXqBean> MOVIE_XQ_BEAN(@Query("movieId") int movieId);

    //电影详情影评
    @GET("movieApi/movie/v2/findAllMovieComment")
    Observable<MovieXqYingpingBean> MOVIE_XQ_YINGPING_BEAN(@Query("movieId") int movieId,
                                                           @Query("page") int page,
                                                           @Query("count") int count);


    //影院推荐
    @GET("movieApi/cinema/v1/findRecommendCinemas")
    Observable<CTuiJianBean> FINDRECOMMENDCINEMAS(@Header("userId") int userId,
                                                  @Header("sessionId") String sessionId,
                                                  @Query("page") int page,
                                                  @Query("count") int count);

    //影院附近
    @GET("movieApi/cinema/v1/findNearbyCinemas")
    Observable<CTuiJianBean> FINDNEARBYCINEMAS(@Header("userId") int userId,
                                               @Header("sessionId") String sessionId,
                                               @Query("longitude") String longitude,
                                               @Query("latitude") String latitude,
                                               @Query("page") int page,
                                               @Query("count") int count);

    //影院海淀
    @GET("movieApi/cinema/v2/findCinemaByRegion")
    Observable<CHaiRightBean> FINDCINEMABYREGION(@Query("regionId") int regionId);

    //影院附近
    @GET("movieApi/tool/v2/findRegionList")
    Observable<CHaiLeftBean> FINDREGIONLIST();

    //影院列表
    @GET("movieApi/cinema/v2/findCinemaScheduleList")
    Observable<YingYuanLieBiaoBean> FINDCINEMASCHEDULELIST(@Query("cinemaId") int cinemaId,
                                                           @Query("page") int page,
                                                           @Query("count") int count);

    //影院详情
    @GET("movieApi/cinema/v1/findCinemaInfo")
    Observable<YYXiangQingBean> FINDCINEMAINFO(@Query("cinemaId") int cinemaId);

    //关注影院
    @GET("movieApi/cinema/v1/verify/followCinema")
    Observable<YYGuanZhuBean> FOLLOWCINEMA(@Header("userId") int userId,
                                           @Header("sessionId") String sessionId,
                                           @Query("cinemaId") int cinemaId);

    //取关影院
    @GET("movieApi/cinema/v1/verify/cancelFollowCinema")
    Observable<YYGuanZhuBean> CANCELFOLLOWCINEMA(@Header("userId") int userId,
                                                 @Header("sessionId") String sessionId,
                                                 @Query("cinemaId") int cinemaId);

    //评论影院
    @POST("movieApi/cinema/v1/verify/cinemaComment")
    @FormUrlEncoded
    Observable<YYGuanZhuBean> CINEMACOMMENT(@Header("userId") int userId,
                                            @Header("sessionid") String sessionId,
                                            @Field("cinemaId") int cinemaId,
                                            @Field("commentContent") String commentContent);

    //影院评论点赞
    @POST("movieApi/cinema/v1/verify/cinemaCommentGreat")
    @FormUrlEncoded
    Observable<YYGuanZhuBean> CINEMACOMMENTGREAT(@Header("userId") int userId,
                                                 @Header("sessionid") String sessionId,
                                                 @Field("commentId") int commentId);

    //影院评论列表
    @GET("movieApi/cinema/v1/findAllCinemaComment")
    Observable<YYPingLunBean> FINDALLCINEMACOMMENT(@Header("userId") int userId,
                                                   @Header("sessionId") String sessionId,
                                                   @Query("page") int page,
                                                   @Query("count") int count,
                                                   @Query("cinemaId") int cinemaId);

    //用户消息
    @GET("movieApi/tool/v1/verify/findAllSysMsgList")
    Observable<XiaoXiBean> FINDALLSYSMSGLIST(@Header("userId") int userId,
                                             @Header("sessionId") String sessionId,
                                             @Query("page") int page,
                                             @Query("count") int count);

    //电影排期时间
    @GET("movieApi/tool/v2/findDateList")
    Observable<PaiQiTimeBean> FINDDATELIST();

    //电影排期
    @GET("movieApi/cinema/v2/findCinemaScheduleList")
    Observable<PaiQiBean> PAIQI(@Query("cinemaId") int cinemaId,
                                @Query("page") int page,
                                @Query("count") int count);

    //根据userId和sessionId查询个人信息
    @GET("movieApi/user/v1/verify/getUserInfoByUserId")
    Observable<ZiLiaoBean> ZILIAOOBSERVALE(@Header("userId") int userId,
                                           @Header("sessionId") String sessionId);

    //修改用户生日
    @POST("movieApi/user/v2/verify/updateUserBirthday")
    Observable<YYGuanZhuBean> XIUGAISHENGRI(@Header("userId") int userId,
                                            @Header("sessionId") String sessionId,
                                            @Query("birthday") String birthday);

    //我的关注..影院
    @GET("movieApi/user/v2/verify/findUserFollowCinemaList")
    Observable<GuanZhuYingYuanBean> GUANZHUYINGYUAN(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                    @Query("page") int page, @Query("count") int count);

    //我的关注..电影
    @GET("movieApi/user/v2/verify/findUserFollowMovieList")
    Observable<GuanZhuDianYingBean> GUANZHUDIANYING(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                                    @Query("page") int page, @Query("count") int count);

    //我的预约
    @GET("movieApi/user/v2/verify/findUserReserve")
    Observable<YuYueBean> FINDUSERRESERVE(@Header("userId") int userId, @Header("sessionId") String sessionId);

    //我的反馈
    @POST("movieApi/tool/v1/verify/recordFeedBack")
    @FormUrlEncoded
    Observable<YYGuanZhuBean> RECORDFRRDBACK(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                             @Field("content") String content);

    //我的电影评论
    @GET("movieApi/user/v2/verify/findMyMovieCommentList")
    Observable<MyYPPLBean> FINDMYMOVIECOMMENTLIST(@Header("userId") int userId,
                                                  @Header("sessionId") String sessionId,
                                                  @Query("page") int page,
                                                  @Query("count") int count);

    //我的影院评论
    @GET("movieApi/user/v2/verify/findMyCinemaCommentList")
    Observable<MyYYPLBean> FINDMYCINEMACOMMENTLIST(@Header("userId") int userId,
                                                   @Header("sessionId") String sessionId,
                                                   @Query("longitude") String longitude,
                                                   @Query("latitude") String latitude,
                                                   @Query("page") int page,
                                                   @Query("count") int count);

    //写评论
    @FormUrlEncoded
    @POST("movieApi/movie/v1/verify/movieComment")
    Observable<CommentBean> COMMENT_BEAN(@Header("sessionId") String sessionId,
                                         @Header("userId") int userId,
                                         @Field("movieId") int movieId,
                                         @Field("commentContent") String commentContent,
                                         @Field("score") String score);

    //购票下单
    @FormUrlEncoded
    @POST("movieApi/movie/v2/verify/buyMovieTickets")
    Observable<MovieTicketsBean> buyMovieTickets(@Header("userId") int userId,
                                                 @Header("sessionId") String sessionId,
                                                 @Field("scheduleId") int scheduleId,
                                                 @Field("seat") String seat,
                                                 @Field("sign") String sign);

    //支付
    @FormUrlEncoded
    @POST("movieApi/movie/v2/verify/pay")
    Observable<PayBean> pay(@Header("userId") int userId,
                            @Header("sessionId") String sessionId,
                            @Field("payType") int payType,
                            @Field("orderId") String orderId);
}
