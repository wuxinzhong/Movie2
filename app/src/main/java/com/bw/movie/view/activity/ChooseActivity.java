package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.Choose;
import com.bw.movie.model.core.DataCall;
import com.bw.movie.presenter.ChoosePresenter;
import com.bw.movie.view.adapter.ChooseAdapter;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class ChooseActivity extends AppCompatActivity {

    private JCVideoPlayerStandard video;
    private TextView tv_dao;
    private TextView tv_ping;
    private TextView tv_name;
    private TextView tv_time;
    private RecyclerView recy;
    private Intent intent;
    private ChoosePresenter choosePresenter;
    private String video1;
    private String iv;
    private ImageView iv_back;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        video = findViewById(R.id.video);
        tv_time = findViewById(R.id.tv_time);
        iv_back = findViewById(R.id.iv_back);
        tv_name = findViewById(R.id.tv_name);
        tv_ping = findViewById(R.id.tv_ping);
        tv_dao = findViewById(R.id.tv_dao);
        recy = findViewById(R.id.recy);
        intent = getIntent();
        iv = intent.getStringExtra("iv");
        video1 = intent.getStringExtra("video");
        String name = intent.getStringExtra("name");
        String time = intent.getStringExtra("time");
        String dao = intent.getStringExtra("dao");
        int fen = intent.getIntExtra("fen",0);
        tv_dao.setText(dao);
        tv_name.setText(name);
        tv_ping.setText(fen+"分");
        tv_time.setText(time);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //video.setUp(video1,video.SCREEN_LAYOUT_NORMAL,name);
        video.setUp(video1,video.SCREEN_LAYOUT_NORMAL,name);
        Glide.with(ChooseActivity.this).load(iv).into(video.thumbImageView);
        id = intent.getIntExtra("id", 0);
        choosePresenter = new ChoosePresenter(new myCall());
        choosePresenter.getModel(id,1,1,10);
    }
    class myCall implements DataCall {
        @Override
        public void onSuccess(Object o) {
            List<Choose.ResultBean> result=(List<Choose.ResultBean>) o;
            ChooseAdapter chooseAdapter = new ChooseAdapter(ChooseActivity.this, result,video1,iv,id);
            recy.setAdapter(chooseAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChooseActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recy.setLayoutManager(linearLayoutManager);
        }

        @Override
        public void onFalse(String msg) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        choosePresenter=null;
        video.releaseAllVideos();
    }
}
