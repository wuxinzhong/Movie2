package com.bw.movie.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.DaoSession;
import com.bw.movie.dao.UserDao;
import com.bw.movie.model.bean.SeenMovieBean;
import com.bw.movie.model.bean.User;
import com.bw.movie.presenter.BasePresenter;
import com.bw.movie.presenter.PaiQiPresenter;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.presenter.YingYuanLieBiaoPresenter;
import com.bw.movie.view.adapter.SeenAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieActivity extends BaseActivity<Presenter> implements Constraint.doSeenMovie {

    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.rect_list_view)
    RecyclerView rectListView;
    @BindView(R.id.linear_view)
    LinearLayout linearView;
    private UserDao mUserDao;
    private int userId;
    private String sessionId;
    private Map<String, Object> map;

    @Override
    protected int initLayout() {
        return R.layout.activity_movie;
    }
    @Override
    void initData() {

    }

    @Override
    Presenter getPresenter() {
        return new Presenter();
    }


    @Override
    void initListener() {
        DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        mUserDao = daoSession.getUserDao();
        map = new HashMap<>();
        List<User> users = mUserDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            sessionId = users.get(i).getSessionId();
            userId = users.get(i).getUserId();
        }
        map.put("userId", userId);
        map.put("sessionId", sessionId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        detailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SharedPreferences sp = getSharedPreferences("feil", Context.MODE_PRIVATE);
        String sessionId = sp.getString("sessionId", null);
        int userId = sp.getInt("userId", 0);


    }

    @Override
    public void doSeenMovieSuccess(SeenMovieBean seenMovieBean) {
        presenter.doSeenMovie(map);
        SeenMovieBean sss= (SeenMovieBean) seenMovieBean;
        List<SeenMovieBean.ResultBean> result = sss.result;
        if (result.size()!=0) {
            rectListView.setVisibility(View.VISIBLE);
            linearView.setVisibility(View.GONE);
            SeenAdapter seenAdapter=new SeenAdapter(result);
            rectListView.setLayoutManager(new LinearLayoutManager(this));
            rectListView.setAdapter(seenAdapter);
            seenAdapter.setDoView(new SeenAdapter.DoView() {
                @Override
                public void onCurress(int id) {
                    Intent intent=new Intent("com.bawei.RevieMovie");
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            });
        }else{
            rectListView.setVisibility(View.GONE);
            linearView.setVisibility(View.VISIBLE);
        }

    }
    @Override
    public void doSeenMovieError(String s) {

    }


//    @Override
//    public void onLogCurress(Object obj) {
//        SeenMovieBean seenMovieBean= (SeenMovieBean) obj;
//        List<SeenMovieBean.ResultBean> result = seenMovieBean.result;
//        if (result.size()!=0) {
//            rectListView.setVisibility(View.VISIBLE);
//            linearView.setVisibility(View.GONE);
//            SeenAdapter seenAdapter=new SeenAdapter(result);
//            rectListView.setLayoutManager(new LinearLayoutManager(this));
//            rectListView.setAdapter(seenAdapter);
//            seenAdapter.setDoView(new SeenAdapter.DoView() {
//                @Override
//                public void onCurress(int id) {
//                    Intent intent=new Intent("com.bawei.RevieMovie");
//                    intent.putExtra("id",id);
//                    startActivity(intent);
//                }
//            });
//        }else{
//            rectListView.setVisibility(View.GONE);
//            linearView.setVisibility(View.VISIBLE);
//        }
//
//    }


}
