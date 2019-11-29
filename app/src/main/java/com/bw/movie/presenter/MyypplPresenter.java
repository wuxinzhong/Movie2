package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.MyYPPLBean;
import com.bw.movie.model.bean.YYGuanZhuBean;
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
public class MyypplPresenter extends BasePresenter<Constraint.MyypplView> {
    public  void YPPLP(int userId,String sessionId,int page,int count){
        HttpUtils.getInstance().getConstant()
                .FINDMYMOVIECOMMENTLIST(userId,sessionId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MyYPPLBean>() {
                    @Override
                    public void accept(MyYPPLBean tuiJianBean) throws Exception {
                        getView().ypplSuccess(tuiJianBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().ypplError(throwable.getMessage());
                    }
                });
    }
}
