package com.bw.movie.presenter;

import com.bw.movie.model.bean.MovieCard;
import com.bw.movie.model.bean.Seat;
import com.bw.movie.model.core.DataCall;
import com.bw.movie.model.core.IRequset;
import com.bw.movie.model.util.NetHttp;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CardPresenter {
    DataCall myCall;

    public CardPresenter(DataCall myCall) {
        this.myCall = myCall;
    }
    public void getModel(int userId,String sessionId){
        IRequset iRequset = NetHttp.getNetHttp().getRetrofit().create(IRequset.class);
        iRequset.findMyMovieTicket(userId,sessionId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<MovieCard>() {
            @Override
            public void accept(MovieCard movieCard) throws Exception {
                if (movieCard.getStatus().equals("0000")) {
                    myCall.onSuccess(movieCard.getResult());
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
