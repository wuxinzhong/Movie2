package com.bw.movie.model.http;

import android.util.Log;

import com.bw.movie.constant.Constant;
import com.bw.movie.model.bean.MovieTicketsBean;
import com.bw.movie.model.bean.PayBean;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**

 * function:
 */
public class OkHttpUtils<B> {

    private final OkHttpClient mOkHttpClient;
    private final Retrofit mRetrofit;
    private final Constant mIApi;
    private static OkHttpUtils mOkHttpUtils = null;

    private OkHttpUtils() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl("http://172.17.8.100/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mIApi = mRetrofit.create(Constant.class);
    }

    public static OkHttpUtils getInstance() {
        if (mOkHttpUtils == null) {
            return new OkHttpUtils();
        }
        return mOkHttpUtils;
    }


    //购票下单
    public void buyMovieTickets(final IOkCallBack iOkCallBack, Class<B> bean, int userId, String sessionId, int scheduleId, String seat, String sign) {
        Observable<MovieTicketsBean> buyMovieTickets = mIApi.buyMovieTickets(userId, sessionId, scheduleId, seat, sign);
        buyMovieTickets.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieTicketsBean>() {

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieTicketsBean movieTicketsBean) {
                        iOkCallBack.callSuccess(movieTicketsBean);
                    }
                });
    }

    //支付
    public void pay(final IOkCallBack iOkCallBack, Class<B> bean, int userId, String sessionId, int payType, String orderId) {
        Observable<PayBean> pay = mIApi.pay(userId, sessionId, payType, orderId);
        pay.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PayBean>() {

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PayBean payBean) {
                        iOkCallBack.callSuccess(payBean);
                    }
                });
    }



    public interface IOkCallBack<B> {
        void callSuccess(B bean);

        void callError(String msg);
    }
}
