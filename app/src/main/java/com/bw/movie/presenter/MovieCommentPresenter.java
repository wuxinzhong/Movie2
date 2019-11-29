package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.CommentBean;
import com.bw.movie.model.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/20/020<p>
 * <p>更改时间：2019/11/20/020<p>
 */
public class MovieCommentPresenter extends BasePresenter<Constraint.IMovieCommentView> {
    
    public void comment(String sessionId, int userId, int movieId, String commentContent, String score){
        HttpUtils.getInstance().getConstant().COMMENT_BEAN(sessionId, userId, movieId, commentContent, score)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CommentBean>() {
                    @Override
                    public void accept(CommentBean commentBean) throws Exception {
                        getView().commentSuccess(commentBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().commentError(throwable.getMessage());
                    }
                });
    }

}
