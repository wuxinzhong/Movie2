package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.YYGuanZhuBean;
import com.bw.movie.model.bean.YuYueBean;
import com.bw.movie.model.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/19<p>
 * <p>更改时间：2019/11/19<p>
 */
public class FanKuiPresenter extends BasePresenter<Constraint.FanKuiView> {
    public  void FanKuiP(int userId,String sessionId,String content){
        HttpUtils.getInstance().getConstant()
                .RECORDFRRDBACK(userId,sessionId,content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<YYGuanZhuBean>() {
                    @Override
                    public void accept(YYGuanZhuBean tuiJianBean) throws Exception {
                        getView().fankuiSuccess(tuiJianBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().fankuiError(throwable.getMessage());
                    }
                });
    }
}
