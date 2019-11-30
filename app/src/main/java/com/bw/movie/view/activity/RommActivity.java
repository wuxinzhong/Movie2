package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.Schedule;
import com.bw.movie.model.bean.Seat;
import com.bw.movie.model.core.DataCall;
import com.bw.movie.presenter.SchePresenter;
import com.bw.movie.presenter.SeatPresenter;
import com.bw.movie.view.adapter.MovieSeatAdapter;
import com.bw.movie.view.adapter.ScheAdapte;

import java.util.List;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class RommActivity extends AppCompatActivity {
    private ImageView roomBack;
    private TextView roomName;
    private LinearLayout layout;
    private JCVideoPlayerStandard roomVideoPlayer;
    private RecyclerView roomMovieSeat;
    private RelativeLayout real;
    private TextView roomTime;
    private  RecyclerView roomRecycler;
    private Button btnPurchaseOrder;
    private Button roomBtn;
    private RadioButton radioZzfb;
    private LinearLayout linerLay;
    private RadioButton radioWx;
    private String string;
    private long sum;
    private double fare = 0.1;
    private double zf;
    private CheckBox wxzf;
    private String orderId;
    private SchePresenter schePresenter;
    private int n=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_romm);
        roomBack = findViewById(R.id.room_back);
        roomName = findViewById(R.id.room_name);
        layout = findViewById(R.id.layout);
        roomVideoPlayer = findViewById(R.id.room_VideoPlayer);
        roomMovieSeat = findViewById(R.id.room_movieSeat);
        real = findViewById(R.id.real);
        roomTime = findViewById(R.id.room_time);
        roomRecycler = findViewById(R.id.room_recycler);
        btnPurchaseOrder = findViewById(R.id.btn_purchaseOrder);
        roomBtn = findViewById(R.id.room_btn);
        radioZzfb = findViewById(R.id.radio_zzfb);
        linerLay = findViewById(R.id.liner_lay);
        radioWx = findViewById(R.id.radio_wx);
        radioWx = findViewById(R.id.radio_wx);
        roomBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        int tid = intent.getIntExtra("tid", 0);
        int yid = intent.getIntExtra("yid", 0);
        String video = intent.getStringExtra("video");
        String iv = intent.getStringExtra("iv");
        roomVideoPlayer.setUp(video,roomVideoPlayer.SCREEN_LAYOUT_NORMAL,"");
        Glide.with(RommActivity.this).load(iv).into(roomVideoPlayer.thumbImageView);
        schePresenter = new SchePresenter(new myCall());
        schePresenter.getModel(yid,tid);
        btnPurchaseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linerLay.setVisibility(View.VISIBLE);
            }
        });
    }
    class myCall implements DataCall {
        @Override
        public void onSuccess(Object o) {
            List<Schedule.ResultBean> result=(List<Schedule.ResultBean>) o;
            ScheAdapte scheAdapte = new ScheAdapte(RommActivity.this, result);
            scheAdapte.setOnActi(new ScheAdapte.OnActi() {
                @Override
                public void onActi(int i) {
                    SeatPresenter seatPresenter = new SeatPresenter(new SeatCall());
                    seatPresenter.getModel(i);
                }
            });
            roomRecycler.setAdapter(scheAdapte);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RommActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            roomRecycler.setLayoutManager(linearLayoutManager);
        }

        @Override
        public void onFalse(String msg) {

        }
    }
    class SeatCall implements DataCall{
        @Override
        public void onSuccess(Object o) {
            List<Seat.ResultBean> result=(List<Seat.ResultBean>) o;
            Log.i("xxx", "onSuccess: "+ result.get(0).getRow());
            MovieSeatAdapter movieSeatAdapter = new MovieSeatAdapter( result);
            roomMovieSeat.setAdapter(movieSeatAdapter);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(RommActivity.this, 6);
            roomMovieSeat.setLayoutManager(gridLayoutManager);
            movieSeatAdapter.setOnActi(new ScheAdapte.OnActi() {
                @Override
                public void onActi(int i) {
                    n=i;
                    if (n!=0){
                        btnPurchaseOrder.setVisibility(View.VISIBLE);
                        btnPurchaseOrder.setText("订"+n+"张票");
                        roomBtn.setVisibility(View.GONE);
                    }else {
                        linerLay.setVisibility(View.GONE);
                        btnPurchaseOrder.setVisibility(View.GONE);
                        roomBtn.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        @Override
        public void onFalse(String msg) {

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        roomVideoPlayer.releaseAllVideos();
    }
}
