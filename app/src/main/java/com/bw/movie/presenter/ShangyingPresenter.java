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
public class ShangyingPresenter extends BasePresenter<Constraint.IGengDuoSYView> {

    private int page;
    //即将上映
    public void shangYing(boolean isRefresh,String sessionId, int userId) {
        if (isRefresh)
            page = 1;
        else
            page++;

        HttpUtils.getInstance().getConstant().SHANG_YING_BEAN(sessionId,userId,page, 7)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShangYingBean>() {
                    @Override
                    public void accept(ShangYingBean shangYingBean) throws Exception {
                        getView().shangyingSuccess(shangYingBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().shangyingError(throwable.getMessage());
                    }
                });
    }

    //预约
    public void yuYue(String sessionId, int userId, int movieId){
        HttpUtils.getInstance().getConstant().MOVIE_YU_YUE_BEAN(sessionId, userId, movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieYuYueBean>() {
                    @Override
                    public void accept(MovieYuYueBean movieYuYueBean) throws Exception {
                        getView().yuyueSuccess(movieYuYueBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().yuyueError(throwable.getMessage());
                    }
                });
    }

}
