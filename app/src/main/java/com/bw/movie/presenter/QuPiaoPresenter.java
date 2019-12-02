package com.bw.movie.presenter;

import com.bw.movie.model.bean.QuPiao;
import com.bw.movie.model.bean.Seat;
import com.bw.movie.model.core.DataCall;
import com.bw.movie.model.core.IRequset;
import com.bw.movie.model.util.NetHttp;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Header;
import retrofit2.http.Query;

public class QuPiaoPresenter {
    DataCall myCall;

    public QuPiaoPresenter(DataCall myCall) {
        this.myCall = myCall;
    }
    public void getModel(int userId,  String sessionId, int recordId){
        IRequset iRequset = NetHttp.getNetHttp().getRetrofit().create(IRequset.class);
        iRequset.findExchangeCode(userId,sessionId,recordId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<QuPiao>() {
            @Override
            public void accept(QuPiao quPiao) throws Exception {
                if (quPiao.getStatus().equals("0000")){
                    myCall.onSuccess(quPiao.getResult());
                }else {
                    myCall.onFalse(quPiao.getMessage());
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
