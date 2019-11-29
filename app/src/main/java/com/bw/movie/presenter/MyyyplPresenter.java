package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.MyYPPLBean;
import com.bw.movie.model.bean.MyYYPLBean;
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
public class MyyyplPresenter extends BasePresenter<Constraint.MyyyplView> {
    public  void YYPLP(int userId,String sessionId,String longitude,String latitude,int page,int count){
        HttpUtils.getInstance().getConstant()
                .FINDMYCINEMACOMMENTLIST(userId,sessionId,longitude,latitude,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MyYYPLBean>() {
                    @Override
                    public void accept(MyYYPLBean tuiJianBean) throws Exception {
                        getView().yyplSuccess(tuiJianBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().yyplError(throwable.getMessage());
                    }
                });
    }
}
