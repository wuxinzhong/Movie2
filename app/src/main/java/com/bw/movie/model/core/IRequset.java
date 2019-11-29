package com.bw.movie.model.core;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRequset {

//    @GET("tool/v2/banner")
//    Observable<Ban> banner();
//
//    @GET("movie/v2/findHotMovieList")
//    Observable<Hot> findHotMovieList(@Query("page") int page, @Query("count") int count);
//
//    @GET("movie/v2/findReleaseMovieList")
//    Observable<Movie> findReleaseMovieList(@Query("page") int page, @Query("count") int count);
//
//    @GET("movie/v2/findComingSoonMovieList")
//    Observable<Im> findComingSoonMovieList(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);
//
//    //登录接口
//    @FormUrlEncoded
//    @POST("user/v2/login")
//    Observable<Bean<Beanlogin>> login(@Field("email") String email, @Field("pwd") String pwd);
//
//    //验证码接口
//    @FormUrlEncoded
//    @POST("user/v2/sendOutEmailCode")
//    Observable<Bean> yzm(@Field("email") String email);
//
//    //注册接口
//    @FormUrlEncoded
//    @POST("user/v2/register")
//    Observable<Bean> zc(@Field("nickName") String nickName, @Field("pwd") String pwd, @Field("email") String email, @Field("code") String code);
//
//
//
//    //推荐影院接口
//    @GET("cinema/v1/findRecommendCinemas")
//    Observable<Bean<List<Beantj>>> tj(@Query("page") int page, @Query("count") int count);
//
//    //附近影院接口
//    @GET("cinema/v1/findNearbyCinemas")
//    Observable<Bean<List<Beantj>>> tjtwo(@Query("longitude") String longitude, @Query("latitude") String latitude, @Query("page") int page, @Query("count") int count);
//
//    //查询电影详情
//    @GET("movie/v2/findMoviesDetail")
//    Observable<Xiang> findMoviesDetail(@Query("movieId") int movieId, @Header("userId") int userId, @Header("sessionId") String sessionId);
//
//    //根据电影的id查询电影评论
//    @GET("movie/v2/findAllMovieComment")
//    Observable<YingPing> findAllMovieComment(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("movieId") int movieId, @Query("page") int page, @Query("count") int count);
//
//    //影院详情接口
//    @GET("cinema/v1/findCinemaInfo")
//    Observable<Bean<Beanxx>> xx(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("cinemaId") int cinemaId);
//
//    //评论列表接口
//    @GET("cinema/v1/findAllCinemaComment")
//    Observable<Bean<List<BeanComment>>> commentlist(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("cinemaId") int cinemaId, @Query("page") int page, @Query("count") int count);
//
//    //评论点赞接口
//    @FormUrlEncoded
//    @POST("cinema/v1/verify/cinemaCommentGreat")
//    Observable<Bean> dianzan(@Header("userId") int userId, @Header("sessionId") String sessionId, @Field("commentId") int commentId);
//
//    //查询区域接口
//    @GET("tool/v2/findRegionList")
//    Observable<Bean<List<Beancity>>> cityw();
//
//    //海淀区接口
//    @GET("cinema/v2/findCinemaByRegion")
//    Observable<Bean<List<Beantj>>> address(@Query("regionId") int regionId);
//
//    //根据电影id,区域id 查询播放影院信息
//    @GET("movie/v2/findCinemasInfoByRegion")
//    Observable<Choose> findCinemasInfoByRegion(@Query("movieId") int movieId, @Query("regionId") int regionId, @Query("page") int page, @Query("count") int count);
//
//    //根据影厅id 查询座位信息
//    @GET("movie/v2/findSeatInfo")
//    Observable<Seat> findSeatInfo(@Query("hallId") int hallId);
//
//    //根据电影ID和影院ID查询电影排期列表
//    @GET("movie/v2/findMovieSchedule")
//    Observable<Schedule> findMovieSchedule(@Query("movieId") int movieId, @Query("cinemaId") int cinemaId);
//
//    //查询电影排期
//    @GET("tool/v2/findDateList")
//    Observable<Bean> pqshang();
//
//    //查询下载的电影排期 (里)
//    @GET("cinema/v2/findCinemaScheduleList")
//    Observable<Bean<List<PqBean>>> pqxia(@Query("cinemaId") int cinemaId, @Query("page") int page, @Query("count") int count);
//
//    //我的预约接口
//    @GET("user/v2/verify/findUserReserve")
//    Observable<Bean<List<BeanYuYue>>> yuyue(@Header("userId") int userId, @Header("sessionId") String sessionId);
//
//    //我的关注成功的影院接口
//    @GET("user/v2/verify/findUserFollowCinemaList")
//    Observable<Bean<List<BeanGZRight>>> guanzhu(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);
//
//    //我的关注成功的电影接口
//    @GET("user/v2/verify/findUserFollowMovieList")
//    Observable<Bean<List<PqBean>>> guanzhuleft(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);
//
//    //意见反馈
//    @FormUrlEncoded
//    @POST("tool/v1/verify/recordFeedBack")
//    Observable<Bean> fankui(@Header("userId") int userId, @Header("sessionId") String sessionId, @Field("content") String content);
//
//    //模糊查询
//    @GET("movie/v2/findMovieByKeyword")
//    Observable<Bean<List<BeanScheduling>>> mohu (@Query("keyword") String keyword, @Query("page") int page, @Query("count") int count);

//    //购票记录
//    @GET("user/v2/verify/findUserBuyTicketRecord")
//    Observable<>  findUserBuyTicketRecord(@Header("userId")int userId,@Header("sessionId")String sessionId);


}
