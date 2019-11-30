package com.bw.movie.model.util;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetHttp {
    private static NetHttp netHttp=null;
    private Retrofit retrofit;

    private NetHttp(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient build = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        retrofit = new Retrofit.Builder().client(build).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://172.17.8.100/movieApi/").build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static NetHttp getNetHttp(){
        if (netHttp == null) {
            synchronized (NetHttp.class){
                if (netHttp == null) {
                    netHttp=new NetHttp();
                }
            }
        }return netHttp;
    }
}
