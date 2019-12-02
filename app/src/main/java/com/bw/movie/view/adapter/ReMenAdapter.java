package com.bw.movie.view.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.model.bean.PopularMovieBean;
import com.bw.movie.view.activity.AllGouPiaoActivity;
import com.bw.movie.view.activity.MovieDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/7/007<p>
 * <p>更改时间：2019/11/7/007<p>
 */
public class ReMenAdapter extends RecyclerView.Adapter<ReMenAdapter.PopularViewHolder> {

    private List<PopularMovieBean.ResultBean> mList = new ArrayList<>();

    public void addAdd(List<PopularMovieBean.ResultBean> mList1) {
        if (mList1 != null)
            this.mList.addAll(mList1);
    }

    @NonNull
    @Override
    public ReMenAdapter.PopularViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_movie_layout, viewGroup, false);
        PopularViewHolder popularViewHolder = new PopularViewHolder(view);
        return popularViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReMenAdapter.PopularViewHolder popularViewHolder, int i) {
        popularViewHolder.search_movie_name.setText(mList.get(i).name);
        popularViewHolder.search_movie_dy.setText("导演 : " + mList.get(i).director);
        popularViewHolder.search_movie_yy.setText("主演 : " + mList.get(i).starring);
        popularViewHolder.search_movie_pf.setText(String.valueOf(mList.get(i).score));
        Glide.with(popularViewHolder.itemView.getContext())
                .load(mList.get(i).imageUrl)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(popularViewHolder.search_movie_img);

        popularViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(popularViewHolder.itemView.getContext(), MovieDetailsActivity.class);
                intent.putExtra("movieId", mList.get(i).movieId);
                intent.putExtra("name", mList.get(i).name);
                popularViewHolder.itemView.getContext().startActivity(intent);
            }
        });

        popularViewHolder.search_movie_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(popularViewHolder.itemView.getContext(), MovieDetailsActivity.class);
                intent.putExtra("movieId", mList.get(i).movieId);
                popularViewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder {

        private final ImageView search_movie_img;
        private final TextView search_movie_name;
        private final TextView search_movie_dy;
        private final TextView search_movie_yy;
        private final TextView search_movie_pf;
        private final Button search_movie_shop;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            search_movie_img = itemView.findViewById(R.id.search_movie_img);
            search_movie_name = itemView.findViewById(R.id.search_movie_name);
            search_movie_dy = itemView.findViewById(R.id.search_movie_dy);
            search_movie_yy = itemView.findViewById(R.id.search_movie_yy);
            search_movie_pf = itemView.findViewById(R.id.search_movie_pf);
            search_movie_shop = itemView.findViewById(R.id.search_movie_shop);
        }
    }
}
