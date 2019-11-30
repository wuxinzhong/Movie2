package com.bw.movie.presenter;

import com.bw.movie.model.bean.Schedule;
import com.bw.movie.model.core.DataCall;
import com.bw.movie.model.core.IRequset;
import com.bw.movie.model.util.NetHttp;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SchePresenter {
    DataCall myCall;

    public SchePresenter(DataCall myCall) {
        this.myCall = myCall;
    }
    public void getModel(int movieId,int cinemaId){
        IRequset iRequset = NetHttp.getNetHttp().getRetrofit().create(IRequset.class);
        iRequset.findMovieSchedule(movieId,cinemaId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Schedule>() {
            @Override
            public void accept(Schedule schedule) throws Exception {
                if (schedule.getStatus().equals("0000")) {
                    myCall.onSuccess(schedule.getResult());
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
