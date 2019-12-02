package com.bw.movie.presenter;

import android.widget.Toast;

import com.bw.movie.app.App1;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.SeenMovieBean;
import com.bw.movie.model.http.HttpUtils;
import com.bw.movie.view.interfaces.IBaseView;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @作者 :于鹏
 * @时间 :2019/12/2 10:57
 * @功能:
 */
public class Presenter extends BasePresenter<Constraint.doSeenMovie> {
    protected IBaseView iBaseVIew;

    public void doSeenMovie(Map<String, Object> map) {
        HttpUtils.getInstance().getConstant()
                .onSeenMovie(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SeenMovieBean>() {
                    @Override
                    public void accept(SeenMovieBean seenMovieBean) throws Exception {
                        if ("0000".equals(seenMovieBean.status)) {
                            if (iBaseVIew != null) {
                                getView().doSeenMovieSuccess(seenMovieBean);
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }

}
