package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.HomeBanner;
import com.bw.movie.model.bean.MovieYuYueBean;
import com.bw.movie.model.bean.PopularMovieBean;
import com.bw.movie.model.bean.ReYingBean;
import com.bw.movie.model.bean.ShangYingBean;
import com.bw.movie.model.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/7/007<p>
 * <p>更改时间：2019/11/7/007<p>
 */
public class RemenPresenter extends BasePresenter<Constraint.IGengDuoRMView> {
    private int page;

    //热门电影
    public void popular(boolean isRefresh) {
        if (isRefresh)
            page = 1;
        else
            page++;

        HttpUtils.getInstance().getConstant().POPULAR_MOVIE_BEAN(page, 7)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PopularMovieBean>() {
                    @Override
                    public void accept(PopularMovieBean popularMovieBean) throws Exception {
                        getView().popularSuccess(popularMovieBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().popularError(throwable.getMessage());
                    }
                });
    }


}
