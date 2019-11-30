package com.bw.movie.presenter;


import com.bw.movie.model.bean.Choose;
import com.bw.movie.model.core.DataCall;
import com.bw.movie.model.core.IRequset;
import com.bw.movie.model.util.NetHttp;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ChoosePresenter {
    DataCall myCall;

    public ChoosePresenter(DataCall myCall) {
        this.myCall = myCall;
    }
    public void getModel(int movieId,int regionId,int page,int count){
        NetHttp.getNetHttp().getRetrofit().create(IRequset.class).findCinemasInfoByRegion(movieId,regionId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Choose>() {
                    @Override
                    public void accept(Choose choose) throws Exception {
                        if (choose.getStatus().equals("0000")) {
                            myCall.onSuccess(choose.getResult());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }
}
