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

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.DaoSession;
import com.bw.movie.dao.UserDao;
import com.bw.movie.model.bean.MovieYuYueBean;
import com.bw.movie.model.bean.ReYingBean;
import com.bw.movie.model.bean.ShangYingBean;
import com.bw.movie.model.bean.User;
import com.bw.movie.presenter.ReYingPresenter;
import com.bw.movie.presenter.ShangyingPresenter;
import com.bw.movie.view.activity.LoginActivity;
import com.bw.movie.view.adapter.ReYingRecycleAdapter;
import com.bw.movie.view.adapter.ShangYingRecycleAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/29/029<p>
 * <p>更改时间：2019/11/29/029<p>
 */
public class ShangYingFragment extends BaseFragment<ShangyingPresenter> implements Constraint.IGengDuoSYView {

    private XRecyclerView frecycler;
    private UserDao mUserDao;
    private String sessionId;
    private int userId;
    private ShangYingRecycleAdapter mShangYingRecycleAdapter;

    @Override
    void initData() {
        //即将上映
        LinearLayoutManager shangying = new LinearLayoutManager(getContext());
        shangying.setOrientation(LinearLayoutManager.VERTICAL);
        frecycler.setLayoutManager(shangying);

        mShangYingRecycleAdapter = new ShangYingRecycleAdapter();
        frecycler.setAdapter(mShangYingRecycleAdapter);

        mShangYingRecycleAdapter.setYuYue(new ShangYingRecycleAdapter.YuYue() {
            @Override
            public void movieYy(int mId) {
                presenter.yuYue(sessionId, userId, mId);
            }
        });

        if (sessionId == null && userId == 0) {
            presenter.shangYing(true, "", 0);

        } else {
            presenter.shangYing(true, sessionId, userId);
        }
    }

    @Override
    ShangyingPresenter getPresenter() {
        return new ShangyingPresenter();
    }

    @Override
    void initListener() {
        DaoSession daoSession = DaoMaster.newDevSession(getActivity(), UserDao.TABLENAME);
        mUserDao = daoSession.getUserDao();

        List<User> users = mUserDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            sessionId = users.get(i).getSessionId();
            userId = users.get(i).getUserId();
        }
    }

    @Override
    int initLayout() {
        return R.layout.cfujin_fragment;
    }

    void initView(View view) {
        frecycler = (XRecyclerView) view.findViewById(R.id.frecycler);
    }


    @Override
    public void shangyingSuccess(ShangYingBean shangYingBean) {
        if (shangYingBean.status.equals("0000")) {
//            Toast.makeText(getContext(), shangYingBean.message, Toast.LENGTH_SHORT).show();
            mShangYingRecycleAdapter.addAdd(shangYingBean.result);
            mShangYingRecycleAdapter.notifyDataSetChanged();

        } else {
            Toast.makeText(getContext(), shangYingBean.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void shangyingError(String s) {

    }

    @Override
    public void yuyueSuccess(MovieYuYueBean movieYuYueBean) {
        if (movieYuYueBean.status.equals("0000")) {
            Toast.makeText(getActivity(), movieYuYueBean.message, Toast.LENGTH_SHORT).show();
        } else if (movieYuYueBean.status.equals("9999")){

            startActivity(new Intent(getActivity(), LoginActivity.class));

            Toast.makeText(getActivity(), movieYuYueBean.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void yuyueError(String s) {

    }
}
