package com.bw.movie.view.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.MyYPPLBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/20<p>
 * <p>更改时间：2019/11/20<p>
 */
public class MyypplAdapter extends RecyclerView.Adapter<MyypplAdapter.YPPLViewHolder> {

    List<MyYPPLBean.ResultBean> list = new ArrayList<>();

    public void ypAddAll(List<MyYPPLBean.ResultBean> li) {
        if (li != null) {
            list.addAll(li);
        }
    }

    @NonNull
    @Override
    public YPPLViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_yp_pl_adapter, viewGroup, false);
        return new YPPLViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull YPPLViewHolder ypplViewHolder, int i) {
        Uri uri = Uri.parse(list.get(i).imageUrl);
        ypplViewHolder.ypplimg.setImageURI(uri);
        ypplViewHolder.ypplname.setText(list.get(i).movieName);
        ypplViewHolder.yppldy.setText("导演:" + list.get(i).director);
        ypplViewHolder.ypplyy.setText("演员:" + list.get(i).starring);
        ypplViewHolder.ypplpf.setText("评分:" + list.get(i).movieScore);
        ypplViewHolder.ypplpl.setText(list.get(i).myCommentContent);

        Date date = new Date(list.get(i).commentTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        ypplViewHolder.yppltime.setText(simpleDateFormat.format(date));

        ypplViewHolder.ypplrb.setRating(list.get(i).myCommentScore);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class YPPLViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ypplimg;
        private final TextView ypplname, yppldy, ypplyy, ypplpf, yppltime, ypplpl;
        private final RatingBar ypplrb;

        public YPPLViewHolder(@NonNull View itemView) {
            super(itemView);
            ypplimg = itemView.findViewById(R.id.ypplimg);
            ypplname = itemView.findViewById(R.id.ypplname);
            yppldy = itemView.findViewById(R.id.yppldy);
            ypplyy = itemView.findViewById(R.id.ypplyy);
            ypplpf = itemView.findViewById(R.id.ypplpf);
            ypplpl = itemView.findViewById(R.id.ypplpl);
            ypplrb = itemView.findViewById(R.id.ypplrb);
            yppltime = itemView.findViewById(R.id.yppltime);
        }
    }
}
