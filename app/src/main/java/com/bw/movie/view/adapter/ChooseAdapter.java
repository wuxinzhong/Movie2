package com.bw.movie.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.Choose;
import com.bw.movie.view.activity.RommActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ChooseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Choose.ResultBean> result;
    String video1;
    String s;
    int id;
    private View inflate;

    public ChooseAdapter(Context context, List<Choose.ResultBean> result, String s, String video1, int id) {
        this.context = context;
        this.result = result;
        this.video1 = video1;
        this.s = s;
        this.id = id;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        inflate = LayoutInflater.from(context).inflate(R.layout.item_zjone, viewGroup, false);
        return new MyView(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof MyView) {
            ((MyView) viewHolder).tv_name.setText(result.get(i).getName());
            ((MyView) viewHolder).tv_ping.setText(result.get(i).getAddress());
            Uri parse = Uri.parse(result.get(i).getLogo());
            ((MyView) viewHolder).iv.setImageURI(parse);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RommActivity.class);
                intent.putExtra("tid",result.get(i).getCinemaId());
                intent.putExtra("yid",id);
                intent.putExtra("video",video1);
                intent.putExtra("iv",s);
                context.startActivity(intent);
            }
        });
    }
    class MyView extends RecyclerView.ViewHolder {

        TextView tv_ping;
        TextView tv_name;
        SimpleDraweeView iv;
        public MyView(@NonNull View itemView) {
            super(itemView);
            iv = inflate.findViewById(R.id.iv);
            tv_name = inflate.findViewById(R.id.text_zjone1);
            tv_ping = inflate.findViewById(R.id.text_zjone2);
        }
    }
    @Override
    public int getItemCount() {
        return result.size();
    }
}
