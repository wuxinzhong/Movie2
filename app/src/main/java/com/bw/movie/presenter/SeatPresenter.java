package com.bw.movie.presenter;

import com.bw.movie.model.bean.Seat;
import com.bw.movie.model.core.DataCall;
import com.bw.movie.model.core.IRequset;
import com.bw.movie.model.util.NetHttp;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SeatPresenter {
    DataCall myCall;

    public SeatPresenter(DataCall myCall) {
        this.myCall = myCall;
    }
    public void getModel(int hallId){
        IRequset iRequset = NetHttp.getNetHttp().getRetrofit().create(IRequset.class);
        iRequset.findSeatInfo(hallId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Seat>() {
            @Override
            public void accept(Seat seat) throws Exception {
                if (seat.getStatus().equals("0000")) {
                    myCall.onSuccess(seat.getResult());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }
}
