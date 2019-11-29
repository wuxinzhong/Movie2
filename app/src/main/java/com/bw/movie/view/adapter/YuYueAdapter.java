package com.bw.movie.view.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.YuYueBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/19<p>
 * <p>更改时间：2019/11/19<p>
 */
public class YuYueAdapter extends RecyclerView.Adapter<YuYueAdapter.YuYueViewHodle> {

    private List<YuYueBean.ResultBean> list=new ArrayList<>();

    public void onAddAll(List<YuYueBean.ResultBean> list1){
        if (list1 != null) {
            list.addAll(list1);
        }
    }

    @NonNull
    @Override
    public YuYueViewHodle onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.yuyue_adapter, viewGroup, false);
        return new YuYueViewHodle(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull YuYueViewHodle yuYueViewHodle, int i) {
        Uri uri=Uri.parse(list.get(i).imageUrl);
        yuYueViewHodle.yuyueimg.setImageURI(uri);

        yuYueViewHodle.yuyuename.setText(list.get(i).name);
        Date date=new Date(list.get(i).releaseTime);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM-dd");
        yuYueViewHodle.sy.setText(simpleDateFormat.format(date)+"上映");
        yuYueViewHodle.xk.setText(list.get(i).wantSeeNum+"人想看");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class YuYueViewHodle extends RecyclerView.ViewHolder {

        private final ImageView yuyueimg;
        private final TextView yuyuename,sy,xk;

        public YuYueViewHodle(@NonNull View itemView) {
            super(itemView);
            yuyueimg = itemView.findViewById(R.id.yuyueimg);
            yuyuename = itemView.findViewById(R.id.yuyuename);
            sy = itemView.findViewById(R.id.yuyuesy);
            xk = itemView.findViewById(R.id.yuyuexk);
        }
    }
}
