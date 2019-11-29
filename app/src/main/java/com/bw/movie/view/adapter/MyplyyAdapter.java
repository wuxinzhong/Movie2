package com.bw.movie.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/23<p>
 * <p>更改时间：2019/11/23<p>
 */
public class MyplyyAdapter extends RecyclerView.Adapter<MyplyyAdapter.YYPLViewHolder> {

    @NonNull
    @Override
    public YYPLViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull YYPLViewHolder yyplViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class YYPLViewHolder extends RecyclerView.ViewHolder {

        public YYPLViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
