package com.bw.movie.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.bw.movie.R;
import com.bw.movie.model.bean.Seat;

import java.util.List;

/**
 *  * <p>文件描述：<p>
 *  * <p>作者：zheng<p>
 *  * <p>创建时间：2019/10/25<p>
 *  * <p>更改时间：2019/10/25<p>
 *  * <p>版本号：1<p>
 *  *
 *  
 */
public class MovieSeatAdapter extends RecyclerView.Adapter<MovieSeatAdapter.MovieVIewHolder> {
    List<Seat.ResultBean> result;
    View inflate;
    Boolean b=false;
    int n=0;
    public MovieSeatAdapter(List<Seat.ResultBean> result) {
        this.result = result;
    }

    @NonNull
    @Override
    public MovieVIewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_seat_item, viewGroup, false);
        return new MovieVIewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieVIewHolder movieVIewHolder, final int i) {
        String row = result.get(i).getRow();
        String seat1 = result.get(i).getSeat();
        String seat = row + "-" + seat1;
        if (movieVIewHolder instanceof MovieVIewHolder){
            if (result.get(i).getStatus()==1){
                movieVIewHolder.cheBox.setChecked(false);
            }else {
                movieVIewHolder.cheBox.setBackgroundColor(R.drawable.myy_shape);
            }
        }
        movieVIewHolder.cheBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (result.get(i).getStatus()==1){
                   if (movieVIewHolder.cheBox.isChecked()){
                       n++;
                   }else {
                       b=movieVIewHolder.cheBox.isChecked();
                       n--;
                   }

               }
                onActi.onActi1(n,seat);
            }
        });

    }


    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MovieVIewHolder extends RecyclerView.ViewHolder {
        CheckBox cheBox;
        public MovieVIewHolder(@NonNull View itemView) {
            super(itemView);
            cheBox  = inflate.findViewById(R.id.che_box);
        }
    }
    public OnActi1 onActi;

    public void setOnActi(OnActi1 onActi) {
        this.onActi = onActi;
    }

    //定义接口
    public interface OnActi1{
        void onActi1(int i,String str);
    }
}
