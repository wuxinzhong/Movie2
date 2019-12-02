package com.bw.movie.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.UserDao;
import com.bw.movie.model.bean.MovieCard;
import com.bw.movie.model.bean.QuPiao;
import com.bw.movie.model.bean.User;
import com.bw.movie.model.core.DataCall;
import com.bw.movie.presenter.QuPiaoPresenter;
import com.bw.movie.view.activity.DianYingPiaoActivity;

import java.text.SimpleDateFormat;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<MovieCard.ResultBean> result;
    private View inflate;

    public CardAdapter(Context context, List<MovieCard.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        inflate = LayoutInflater.from(context).inflate(R.layout.item_moviecard, viewGroup, false);
        return new MyView(inflate);
    }
    class MyView extends RecyclerView.ViewHolder {

       TextView tv_name,tv_seat,tv_add,tv_ting_name,tv_time,tv_start,tv_stop;
        Button btn;
        public MyView(@NonNull View itemView) {
            super(itemView);
            tv_name = inflate.findViewById(R.id.tv_name);
            tv_seat=inflate.findViewById(R.id.tv_seat);
            tv_add=inflate.findViewById(R.id.tv_add);
            tv_ting_name=inflate.findViewById(R.id.tv_ting_name);
            tv_time=inflate.findViewById(R.id.tv_time);
            tv_start=inflate.findViewById(R.id.tv_start);
            tv_stop=inflate.findViewById(R.id.tv_stop);
            btn= inflate.findViewById(R.id.btn);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyView){
            ((MyView) viewHolder).tv_name.setText(result.get(i).getMovieName());
            String[] split = result.get(i).getSeat().split("-");
            ((MyView) viewHolder).tv_seat.setText(split[0]+"排"+split[1]+"座");
            ((MyView) viewHolder).tv_add.setText(result.get(i).getCinemaName());
            ((MyView) viewHolder).tv_ting_name.setText(result.get(i).getScreeningHall());
            SimpleDateFormat format=new SimpleDateFormat("MM"+"月"+"dd"+"日上映");
            String format1 = format.format(result.get(i).getCreateTime());
            ((MyView) viewHolder).tv_time.setText(format1);
            ((MyView) viewHolder).tv_start.setText(result.get(i).getBeginTime());
            ((MyView) viewHolder).tv_stop.setText(result.get(i).getEndTime());
            ((MyView) viewHolder).btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserDao userDao = DaoMaster.newDevSession(context, UserDao.TABLENAME).getUserDao();
                    List<User> users = userDao.loadAll();
                    int userId = users.get(users.size() - 1).getUserId();
                    String sessionId = users.get(users.size() - 1).getSessionId();
                    Log.i("xxx", "onClick: "+userId+"  "+sessionId+"   "+result.get(i).getId());
                    QuPiaoPresenter quPiaoPresenter = new QuPiaoPresenter(new MyCall());
                    quPiaoPresenter.getModel(userId,sessionId,result.get(i).getId());
                }
            });
        }
    }
    class MyCall implements DataCall{

        private PopupWindow popupWindow;
        private View inflate;
        private ImageView iv;

        @Override
        public void onSuccess(Object o) {
          QuPiao.ResultBean result=(QuPiao.ResultBean) o;
            Log.i("xxx", "onSuccess: "+result.getExchangeCode());
            inflate = LayoutInflater.from(context).inflate(R.layout.pop, null);
            popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            popupWindow.setContentView(inflate);
            iv = inflate.findViewById(R.id.iv);
            Glide.with(context).load(result.getExchangeCode()).into(this.iv);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setOutsideTouchable(true);
            View rootview = LayoutInflater.from(context).inflate(R.layout.activity_main, null);
            popupWindow.showAtLocation(rootview,Gravity.BOTTOM,0,0);
            inflate.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
            inflate.findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
        }

        @Override
        public void onFalse(String msg) {
            Log.i("xxx", "onFalse: "+msg);
        }
    }
    @Override
    public int getItemCount() {
        return result.size();
    }
}
