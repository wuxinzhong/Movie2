package com.bw.movie.model.core;

import com.bw.movie.model.bean.MovieScheduleBean;
import com.bw.movie.model.bean.MovieTicketsBean;
import com.bw.movie.model.bean.PayBean;
import com.bw.movie.model.bean.SeatInfoBean;
import com.bw.movie.view.interfaces.IBaseView;


public interface IContractView {

    //根据电影ID和影院ID查询电影排期列表
    interface IMovieScheduleView extends IBaseView {
        //查看电影排期
        void movieScheduleSuccess(MovieScheduleBean movieScheduleBean);
        //下单
        void buyMovieTicketsSuccess(MovieTicketsBean movieTicketsBean);
        //选座
        void seatInfoSuccess(SeatInfoBean seatInfoBean);
        //支付
        void paySuccess(PayBean payBean);
        void movieScheduleError(String msg);
    }


}
