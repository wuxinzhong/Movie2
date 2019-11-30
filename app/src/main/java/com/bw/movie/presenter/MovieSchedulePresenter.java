package com.bw.movie.presenter;


import com.bw.movie.model.bean.MovieScheduleBean;
import com.bw.movie.model.bean.MovieTicketsBean;
import com.bw.movie.model.bean.PayBean;
import com.bw.movie.model.bean.SeatInfoBean;
import com.bw.movie.model.core.IContractView;
import com.bw.movie.model.okhttp.OkHttpUtils;

/**

 * function:
 */
public class MovieSchedulePresenter extends BasePresenter<IContractView.IMovieScheduleView> {

    //购票下单
    public void buyMovieTickets(int userId,String sessionId,int scheduleId,String seat,String sign){
        com.bw.movie.model.okhttp.OkHttpUtils.getInstance().buyMovieTickets(new com.bw.movie.model.okhttp.OkHttpUtils.IOkCallBack<MovieTicketsBean>() {
            @Override
            public void callSuccess(MovieTicketsBean bean) {
                getView().buyMovieTicketsSuccess(bean);
            }

            @Override
            public void callError(String msg) {

            }
        }, MovieTicketsBean.class,userId,sessionId,scheduleId,seat,sign);
    }

    //支付
    public void pay(int userId,String sessionId,int payType,String orderId){
        com.bw.movie.model.okhttp.OkHttpUtils.getInstance().pay(new com.bw.movie.model.okhttp.OkHttpUtils.IOkCallBack<PayBean>() {
            @Override
            public void callSuccess(PayBean bean) {
                getView().paySuccess(bean);
            }

            @Override
            public void callError(String msg) {

            }
        }, PayBean.class,userId,sessionId,payType,orderId);
    }

}
