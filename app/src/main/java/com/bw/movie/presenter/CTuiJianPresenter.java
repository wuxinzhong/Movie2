package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.CTuiJianBean;
import com.bw.movie.model.bean.HomeBanner;
import com.bw.movie.model.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * date:19/11/8
 * author:张自磊(lenovo)
 * function:
 */
public class CTuiJianPresenter extends BasePresenter<Constraint.TuiJianView> {
    public void TuiJian(int userId,String sessionId,int page,int count){

        HttpUtils.getInstance().getConstant()
                .FINDRECOMMENDCINEMAS(userId,sessionId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CTuiJianBean>() {
                    @Override
                    public void accept(CTuiJianBean tuiJianBean) throws Exception {
                        getView().tuijianSuccess(tuiJianBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().tuijianError(throwable.getMessage());
                    }
                });

    }
}
