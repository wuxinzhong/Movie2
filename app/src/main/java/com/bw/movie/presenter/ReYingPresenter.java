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
public class ReYingPresenter extends BasePresenter<Constraint.IGengDuoRYView> {

    private int page;

    //正在热映
    public void reYing(boolean isRefresh) {
        if (isRefresh)
            page = 1;
        else
            page++;

        HttpUtils.getInstance().getConstant().RE_YING_BEAN(page, 7)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReYingBean>() {
                    @Override
                    public void accept(ReYingBean reYingBean) throws Exception {
                        getView().reyingSuccess(reYingBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().reyingError(throwable.getMessage());
                    }
                });
    }
}
