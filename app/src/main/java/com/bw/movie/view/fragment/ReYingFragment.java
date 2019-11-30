package com.bw.movie.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.ReYingBean;
import com.bw.movie.presenter.ReYingPresenter;
import com.bw.movie.view.adapter.ReYingAdapter;
import com.bw.movie.view.adapter.ReYingRecycleAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/29/029<p>
 * <p>更改时间：2019/11/29/029<p>
 */
public class ReYingFragment extends BaseFragment<ReYingPresenter> implements Constraint.IGengDuoRYView {

    private XRecyclerView frecycler;
    private ReYingAdapter mReYingRecycleAdapter;

    @Override
    void initData() {
        //热映
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        frecycler.setLayoutManager(linearLayoutManager);

        mReYingRecycleAdapter = new ReYingAdapter();
        frecycler.setAdapter(mReYingRecycleAdapter);

        presenter.reYing(true);
    }

    @Override
    ReYingPresenter getPresenter() {
        return new ReYingPresenter();
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
    public void reyingSuccess(ReYingBean reYingBean) {
        if (reYingBean.status.equals("0000")) {
//            Toast.makeText(getContext(), reYingBean.message, Toast.LENGTH_SHORT).show();
            mReYingRecycleAdapter.addAdd(reYingBean.result);
            mReYingRecycleAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext(), reYingBean.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void reyingError(String s) {

    }
}
