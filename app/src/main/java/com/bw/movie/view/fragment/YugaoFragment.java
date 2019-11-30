package com.bw.movie.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.MovieXqBean;
import com.bw.movie.presenter.MovieXQPresenter;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/13/013<p>
 * <p>更改时间：2019/11/13/013<p>
 */
public class YugaoFragment extends BaseFragment<MovieXQPresenter> implements Constraint.IMovieXqView {

    private int mMovieId;
    private JCVideoPlayerStandard videoplayer1;
    private JCVideoPlayerStandard videoplayer2;
    private JCVideoPlayerStandard videoplayer3;

    @Override
    void initData() {

        Intent intent = getActivity().getIntent();
        mMovieId = intent.getIntExtra("movieId", 0);

        presenter.movieXQ(mMovieId);
    }


    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }


    @Override
    MovieXQPresenter getPresenter() {
        return new MovieXQPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            videoplayer1.releaseAllVideos();
            videoplayer2.releaseAllVideos();
            videoplayer3.releaseAllVideos();
        } catch (Exception e) {
        }
    }

    @Override
    int initLayout() {
        return R.layout.movie_xq_yugao_fragment_layout;
    }

    void initView(View view) {
        videoplayer1 = (JCVideoPlayerStandard) view.findViewById(R.id.videoplayer1);
        videoplayer2 = (JCVideoPlayerStandard) view.findViewById(R.id.videoplayer2);
        videoplayer3 = (JCVideoPlayerStandard) view.findViewById(R.id.videoplayer3);
    }

    @Override
    public void movieXQSuccess(MovieXqBean movieXqBean) {

        videoplayer1.TOOL_BAR_EXIST = false;

        videoplayer1.setUp(movieXqBean.result.shortFilmList.get(0).videoUrl, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, movieXqBean.result.name);

        Glide.with(getContext()).load(movieXqBean.result.shortFilmList.get(0).imageUrl).into(videoplayer1.thumbImageView);

//        videoplayer1.widthRatio = 4;//播放比例
//        videoplayer1.heightRatio = 3;

        videoplayer2.TOOL_BAR_EXIST = false;

        videoplayer2.setUp(movieXqBean.result.shortFilmList.get(1).videoUrl, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, movieXqBean.result.name);

        Glide.with(getContext()).load(movieXqBean.result.shortFilmList.get(1).imageUrl).into(videoplayer2.thumbImageView);

//        videoplayer2.widthRatio = 4;//播放比例
//        videoplayer2.heightRatio = 3;

        videoplayer3.TOOL_BAR_EXIST = false;

        videoplayer3.setUp(movieXqBean.result.shortFilmList.get(2).videoUrl, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, movieXqBean.result.name);

        Glide.with(getContext()).load(movieXqBean.result.shortFilmList.get(2).imageUrl).into(videoplayer3.thumbImageView);

//        videoplayer3.widthRatio = 4;//播放比例
//        videoplayer3.heightRatio = 3;


    }


    @Override
    public void movieXQError(String s) {

    }

}
