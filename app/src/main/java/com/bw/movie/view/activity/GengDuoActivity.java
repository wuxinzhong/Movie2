package com.bw.movie.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.view.adapter.MovieXqTabAdapter;
import com.bw.movie.view.fragment.ReMenFragment;
import com.bw.movie.view.fragment.ReYingFragment;
import com.bw.movie.view.fragment.ShangYingFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class GengDuoActivity extends AppCompatActivity {

    @BindView(R.id.gengduo_back)
    ImageView gengduoBack;
    @BindView(R.id.gengduo_sou)
    EditText gengduoSou;
    @BindView(R.id.gengduo_tab)
    TabLayout gengduoTab;
    @BindView(R.id.gengduo_viewpager)
    ViewPager gengduoViewpager;
    private Unbinder mBind;
    private ArrayList<String> mTitle = new ArrayList<>();
    private ArrayList<Fragment> mList = new ArrayList<>();
    private ReYingFragment mReYingFragment;
    private ShangYingFragment mShangYingFragment;
    private ReMenFragment mReMenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geng_duo);
        mBind = ButterKnife.bind(this);

        mTitle.add("正在热映");
        mTitle.add("即将上映");
        mTitle.add("热门电影");

        mReYingFragment = new ReYingFragment();
        mShangYingFragment = new ShangYingFragment();
        mReMenFragment = new ReMenFragment();

        mList.add(mReYingFragment);
        mList.add(mShangYingFragment);
        mList.add(mReMenFragment);

        gengduoTab.setupWithViewPager(gengduoViewpager);

        MovieXqTabAdapter movieXqTabAdapter = new MovieXqTabAdapter(getSupportFragmentManager(), mTitle, mList);
        gengduoViewpager.setAdapter(movieXqTabAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    @OnClick(R.id.gengduo_back)
    public void onViewClicked() {
        finish();
    }
}
