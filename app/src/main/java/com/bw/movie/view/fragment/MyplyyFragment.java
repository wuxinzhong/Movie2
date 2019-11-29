package com.bw.movie.view.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.DaoSession;
import com.bw.movie.dao.UserDao;
import com.bw.movie.model.bean.MyYYPLBean;
import com.bw.movie.model.bean.User;
import com.bw.movie.presenter.MyyyplPresenter;
import com.bw.movie.view.activity.LoginActivity;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/19<p>
 * <p>更改时间：2019/11/19<p>
 */
public class MyplyyFragment extends BaseFragment<MyyyplPresenter> implements Constraint.MyyyplView {

    private UserDao mUserDao;
    private String sessionId;
    private int userId;
    private RecyclerView yyplrecycler;

    @Override
    public void yyplSuccess(MyYYPLBean yyplBean) {
        if (yyplBean.status.equals("9999")) {
            Toast.makeText(getContext(), yyplBean.message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        }else {

        }
    }

    @Override
    public void yyplError(String s) {
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

        presenter.YYPLP(userId,sessionId,"0","0",1,20);
    }

    @Override
    MyyyplPresenter getPresenter() {
        return new MyyyplPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.my_pl_yy_fragment;
    }

    @Override
    void initView(View view) {
        yyplrecycler=view.findViewById(R.id.yyplrecycler);
    }
}
