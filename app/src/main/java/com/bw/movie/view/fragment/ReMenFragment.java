package com.bw.movie.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.PopularMovieBean;
import com.bw.movie.model.bean.ReYingBean;
import com.bw.movie.presenter.ReYingPresenter;
import com.bw.movie.presenter.RemenPresenter;
import com.bw.movie.view.activity.MovieDetailsActivity;
import com.bw.movie.view.adapter.PopularRecycleAdapter;
import com.bw.movie.view.adapter.ReMenAdapter;
import com.bw.movie.view.adapter.ReYingRecycleAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/29/029<p>
 * <p>更改时间：2019/11/29/029<p>
 */
public class ReMenFragment extends BaseFragment<RemenPresenter> implements Constraint.IGengDuoRMView {

    private XRecyclerView frecycler;
    private ReMenAdapter mPopularRecycleAdapter;

    @Override
    void initData() {
        //热门电影
        LinearLayoutManager pop = new LinearLayoutManager(getContext());
        pop.setOrientation(LinearLayoutManager.VERTICAL);
        frecycler.setLayoutManager(pop);

        mPopularRecycleAdapter = new ReMenAdapter();
        frecycler.setAdapter(mPopularRecycleAdapter);

        presenter.popular(true);
    }

    @Override
    RemenPresenter getPresenter() {
        return new RemenPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.cfujin_fragment;
    }

    void initView(View view) {
        frecycler = (XRecyclerView) view.findViewById(R.id.frecycler);
    }

    @Override
    public void popularSuccess(PopularMovieBean popularMovieBean) {
        if (popularMovieBean.status.equals("0000")) {
//            Toast.makeText(getContext(), popularMovieBean.message, Toast.LENGTH_SHORT).show();
            mPopularRecycleAdapter.addAdd(popularMovieBean.result);
            mPopularRecycleAdapter.notifyDataSetChanged();

        } else {
            Toast.makeText(getContext(), popularMovieBean.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void popularError(String s) {

    }
}
