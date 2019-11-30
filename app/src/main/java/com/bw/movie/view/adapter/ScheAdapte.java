package com.bw.movie.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.Schedule;

import java.util.List;

public class ScheAdapte extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Schedule.ResultBean> result;
    private View inflate;

    public ScheAdapte(Context context, List<Schedule.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        inflate = LayoutInflater.from(context).inflate(R.layout.recycler_room, viewGroup, false);
        return new MyView(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof MyView) {
            ((MyView) viewHolder).tv_name.setText(result.get(i).getScreeningHall());
            ((MyView) viewHolder).tv_k.setText(result.get(i).getBeginTime());
            ((MyView) viewHolder).tv_j.setText(result.get(i).getEndTime());
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onActi.onActi(result.get(i).getHallId());
            }
        });

    }
    class MyView extends RecyclerView.ViewHolder{

        TextView tv_name,tv_j,tv_k;

        public MyView(@NonNull View itemView) {
            super(itemView);
            tv_name = inflate.findViewById(R.id.tv_name);
            tv_k=inflate.findViewById(R.id.tv_k);
            tv_j=inflate.findViewById(R.id.tv_j);
        }
    }
    @Override
    public int getItemCount() {
        return result.size();
    }

    public OnActi onActi;

    public void setOnActi(OnActi onActi) {
        this.onActi = onActi;
    }

    //定义接口
    public interface OnActi{
        void onActi(int i);
    }

}
