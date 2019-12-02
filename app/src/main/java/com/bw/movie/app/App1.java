package com.bw.movie.app;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/*
 * @Auther:任德明(Administrator)
 * @Date:2019/10/14 0014
 * @Description:
 * multipart/form-data;charset=utf-8
 */
public class App1 extends Application {
    public static final String APP_ID  = "wxb3852e6a6b7d9516";

    // IWXAPI 是第三方app和微信通信的openApi接口
    public static IWXAPI api;


    public static int userId;
    public static String sessionId;
    public static Context sContext;

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        App1.userId = userId;
    }

    public static String getSessionId() {
        return sessionId;
    }

    public static void setSessionId(String sessionId) {
        App1.sessionId = sessionId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID , true);
        // 将应用的appId注册到微信
        api.registerApp(APP_ID );

        sContext = this;

        Fresco.initialize(this);
    }
}
