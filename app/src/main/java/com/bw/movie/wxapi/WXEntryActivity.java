package com.bw.movie.wxapi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;


import com.bw.movie.app.XLApp;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;


import org.greenrobot.eventbus.EventBus;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "WXEntryActivity";
    private static final int RETURN_MSG_TYPE_LOGIN = 1; //登录
    private static final int RETURN_MSG_TYPE_SHARE = 2; //分享
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        //这句没有写,是不能执行回调的方法的
        XLApp.mWxApi.handleIntent(getIntent(), this);
    }



    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq baseReq) {

    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp baseResp) {
        Log.i(TAG, "onResp:------>");
        Log.i(TAG, "error_code:---->" + baseResp.errCode);
        int type = baseResp.getType(); //类型：分享还是登录
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                //用户拒绝授权
                //ToastUtils.showToast(mContext, "拒绝授权微信登录");
                Toast.makeText(mContext, "拒绝授权微信登录", Toast.LENGTH_SHORT).show();
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //用户取消
                String message = "";
                if (type == RETURN_MSG_TYPE_LOGIN) {
                    message = "取消了微信登录";
                } else if (type == RETURN_MSG_TYPE_SHARE) {
                    message = "取消了微信分享";
                }
                //ToastUtils.showToast(mContext, message);
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                break;
            case BaseResp.ErrCode.ERR_OK:
                //用户同意
                if (type == RETURN_MSG_TYPE_LOGIN) {
                    //用户换取access_token的code，仅在ErrCode为0时有效
                    String code = ((SendAuth.Resp) baseResp).code;
                    String state = ((SendAuth.Resp) baseResp).state;
                    Log.i("aaaa", "code:------>" + code);
                    EventBus.getDefault().postSticky(code);
                    //这里拿到了这个code，去做2次网络请求获取access_token和用户个人信息
//                     WXLoginUtils().getWXLoginResult(code, this);

                } else if (type == RETURN_MSG_TYPE_SHARE) {
                    // ToastUtils.showToast(mContext, "微信分享成功");
                    Toast.makeText(mContext, "微信分享成功", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
