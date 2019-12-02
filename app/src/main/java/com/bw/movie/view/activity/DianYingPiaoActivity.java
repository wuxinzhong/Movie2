package com.bw.movie.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.UserDao;
import com.bw.movie.model.bean.MovieCard;
import com.bw.movie.model.bean.User;
import com.bw.movie.model.core.DataCall;
import com.bw.movie.presenter.CardPresenter;
import com.bw.movie.view.adapter.CardAdapter;

import java.util.List;

public class DianYingPiaoActivity extends AppCompatActivity {

    private RecyclerView recy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_ying_piao);
        recy = findViewById(R.id.recy);
        ImageView iv = findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        UserDao userDao = DaoMaster.newDevSession(this, UserDao.TABLENAME).getUserDao();
        List<User> users = userDao.loadAll();
        int userId = users.get(users.size() - 1).getUserId();
        String sessionId = users.get(users.size() - 1).getSessionId();
        CardPresenter cardPresenter = new CardPresenter(new MyCall());
        cardPresenter.getModel(userId,sessionId);
    }
    class MyCall implements DataCall{
        @Override
        public void onSuccess(Object o) {
            List<MovieCard.ResultBean> result=(List<MovieCard.ResultBean>) o;
            Log.i("xxx", "onSuccess: "+result.get(0).getBeginTime());
            CardAdapter cardAdapter = new CardAdapter(DianYingPiaoActivity.this, result);
            recy.setAdapter(cardAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DianYingPiaoActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recy.setLayoutManager(linearLayoutManager);
        }

        @Override
        public void onFalse(String msg) {

        }
    }
}
