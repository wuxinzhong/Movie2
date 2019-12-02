package com.bw.movie.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.view.adapter.MovieXqTabAdapter;
import com.bw.movie.view.fragment.BuyMovieFragment;
import com.bw.movie.view.fragment.BuyYeMovieFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class GouPiaoActivity extends AppCompatActivity {

    @BindView(R.id.xximg)
    ImageView xximg;
    @BindView(R.id.buy_tab_la)
    TabLayout buyTabLa;
    @BindView(R.id.buy_view_page)
    ViewPager buyViewPage;
    private Unbinder mBind;
    List<String> list = new ArrayList<>();
    List<Fragment> flist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gou_piao);
        mBind = ButterKnife.bind(this);

        flist.add(new BuyMovieFragment());
        flist.add(new BuyYeMovieFragment());

        list.add("待付款");
        list.add("已付款");

        for (int i = 0; i < list.size(); i++) {
            String title = list.get(i);

            TabLayout.Tab tab = buyTabLa.newTab();
            if (tab != null) {
                tab.setText(title);
                buyTabLa.addTab(tab);
            }
        }

        buyTabLa.setupWithViewPager(buyViewPage);
        MovieXqTabAdapter movieXqTabAdapter = new MovieXqTabAdapter(getSupportFragmentManager(), list, flist);
        movieXqTabAdapter.notifyDataSetChanged();
        buyViewPage.setAdapter(movieXqTabAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    @OnClick(R.id.xximg)
    public void onViewClicked() {
        finish();
    }
}
