package com.bw.movie.view.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.DaoSession;
import com.bw.movie.dao.UserDao;
import com.bw.movie.model.bean.MyYPPLBean;
import com.bw.movie.model.bean.User;
import com.bw.movie.presenter.MyypplPresenter;
import com.bw.movie.view.activity.LoginActivity;
import com.bw.movie.view.adapter.MyypplAdapter;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/19<p>
 * <p>更改时间：2019/11/19<p>
 */
public class MyplypFragment extends BaseFragment<MyypplPresenter> implements Constraint.MyypplView {

    private UserDao mUserDao;
    private String sessionId;
    private int userId;
    private RecyclerView ypplrecycler;
    private MyypplAdapter myypplAdapter;

    @Override
    public void ypplSuccess(MyYPPLBean ypplBean) {
        if (ypplBean.status.equals("9999")) {
            Toast.makeText(getContext(), ypplBean.message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        }else {
            myypplAdapter.ypAddAll(ypplBean.result);
            myypplAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void ypplError(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    void initData() {
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), UserDao.TABLENAME);
        mUserDao = daoSession.getUserDao();

        List<User> users = mUserDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            sessionId = users.get(i).getSessionId();
            userId = users.get(i).getUserId();
        }

        presenter.YPPLP(userId,sessionId,1,20);

        myypplAdapter = new MyypplAdapter();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ypplrecycler.setLayoutManager(linearLayoutManager);
        ypplrecycler.setAdapter(myypplAdapter);
        myypplAdapter.notifyDataSetChanged();
    }

    @Override
    MyypplPresenter getPresenter() {
        return new MyypplPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.my_pl_yp_fragment;
    }

    @Override
    void initView(View view) {
        ypplrecycler=view.findViewById(R.id.ypplrecycler);
    }
}
