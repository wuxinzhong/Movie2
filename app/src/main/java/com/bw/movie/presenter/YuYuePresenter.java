package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.CTuiJianBean;
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
public class YuYuePresenter extends BasePresenter<Constraint.YuYueView> {
    public  void YuYueP(int userId,String sessionId){
        HttpUtils.getInstance().getConstant()
                .FINDUSERRESERVE(userId,sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<YuYueBean>() {
                    @Override
                    public void accept(YuYueBean tuiJianBean) throws Exception {
                        getView().yuyueSuccess(tuiJianBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().yuyueError(throwable.getMessage());
                    }
                });
    }
}
