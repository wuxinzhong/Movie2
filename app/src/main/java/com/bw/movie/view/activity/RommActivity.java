package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.DaoSession;
import com.bw.movie.dao.UserDao;
import com.bw.movie.jiami.MD5Utils;
import com.bw.movie.model.bean.MovieScheduleBean;
import com.bw.movie.model.bean.MovieTicketsBean;
import com.bw.movie.model.bean.PayBean;
import com.bw.movie.model.bean.Schedule;
import com.bw.movie.model.bean.Seat;
import com.bw.movie.model.bean.SeatInfoBean;
import com.bw.movie.model.bean.User;
import com.bw.movie.model.core.DataCall;
import com.bw.movie.model.core.IContractView;
import com.bw.movie.presenter.MovieSchedulePresenter;
import com.bw.movie.presenter.SchePresenter;
import com.bw.movie.presenter.SeatPresenter;
import com.bw.movie.view.adapter.MovieSeatAdapter;
import com.bw.movie.view.adapter.ScheAdapte;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class RommActivity extends BaseActivity<MovieSchedulePresenter> implements IContractView.IMovieScheduleView {
    @BindView(R.id.room_back)
    ImageView mRoomBack;
    @BindView(R.id.room_name)
    TextView mRoomName;
    @BindView(R.id.layout)
    LinearLayout mLayout;
    @BindView(R.id.room_VideoPlayer)
    JCVideoPlayerStandard mRoomVideoPlayer;
    @BindView(R.id.room_movieSeat)
    RecyclerView mRoomMovieSeat;
    @BindView(R.id.real)
    RelativeLayout mReal;
    @BindView(R.id.room_time)
    TextView mRoomTime;
    @BindView(R.id.room_recycler)
    RecyclerView mRoomRecycler;
    @BindView(R.id.radio_wx)
    RadioButton mRadioWx;
    @BindView(R.id.radio_zzfb)
    RadioButton mRadioZzfb;
    @BindView(R.id.liner_lay)
    LinearLayout mLinerLay;
    @BindView(R.id.btn_purchaseOrder)
    Button mBtnPurchaseOrder;
    @BindView(R.id.room_btn)
    Button mRoomBtn;
    private ImageView roomBack;
    private TextView roomName;
    private LinearLayout layout;
    private JCVideoPlayerStandard roomVideoPlayer;
    private RecyclerView roomMovieSeat;
    private RelativeLayout real;
    private TextView roomTime;
    private RecyclerView roomRecycler;
    private Button btnPurchaseOrder;
    private Button roomBtn;
    private RadioButton radioZzfb;
    private LinearLayout linerLay;
    private RadioButton radioWx;
    private String string;
    private long sum;
    private double fare = 0.1;
    private double zf;
    private CheckBox wxzf;
    private String orderId;
    private SchePresenter schePresenter;
    private int n = 0;
    private UserDao mUserDao;
    private int userId;
    private int id;
    private String sessionId;
    private int tid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_romm);
        ButterKnife.bind(this);
        roomBack = findViewById(R.id.room_back);
        roomName = findViewById(R.id.room_name);
        layout = findViewById(R.id.layout);
        roomVideoPlayer = findViewById(R.id.room_VideoPlayer);
        roomMovieSeat = findViewById(R.id.room_movieSeat);
        real = findViewById(R.id.real);
        roomTime = findViewById(R.id.room_time);
        roomRecycler = findViewById(R.id.room_recycler);
        btnPurchaseOrder = findViewById(R.id.btn_purchaseOrder);
        roomBtn = findViewById(R.id.room_btn);
        radioZzfb = findViewById(R.id.radio_zzfb);
        linerLay = findViewById(R.id.liner_lay);
        radioWx = findViewById(R.id.radio_wx);
        radioWx = findViewById(R.id.radio_wx);
        DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        mUserDao = daoSession.getUserDao();
        roomBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        tid = intent.getIntExtra("tid", 0);
        int yid = intent.getIntExtra("yid", 0);
        String video = intent.getStringExtra("video");
        String iv = intent.getStringExtra("iv");
        roomVideoPlayer.setUp(video, roomVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
        Glide.with(RommActivity.this).load(iv).into(roomVideoPlayer.thumbImageView);
        schePresenter = new SchePresenter(new myCall());
        schePresenter.getModel(yid, tid);
        btnPurchaseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linerLay.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    void initData() {

    }

    @Override
    MovieSchedulePresenter getPresenter() {
        return new MovieSchedulePresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.activity_romm;
    }

    @Override
    public void movieScheduleSuccess(MovieScheduleBean movieScheduleBean) {

    }

    @Override
    public void buyMovieTicketsSuccess(MovieTicketsBean movieTicketsBean) {
        if (movieTicketsBean.getStatus().equals("0000")){
            Toast.makeText(this,movieTicketsBean.getMessage(), Toast.LENGTH_SHORT).show();
            String orderId = movieTicketsBean.getOrderId();
            Log.e("orderId",orderId);
            presenter.pay(userId,sessionId,1,orderId);
        }else {
            Toast.makeText(this,movieTicketsBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void Message(User userBean){
        userId = userBean.getUserId();
        sessionId = userBean.getSessionId();

    }

    @Override
    public void seatInfoSuccess(SeatInfoBean seatInfoBean) {

    }

    @Override
    public void paySuccess(PayBean payBean) {
        if (payBean.getStatus().equals("0000")){
            IWXAPI wxapi = WXAPIFactory.createWXAPI(RommActivity.this, null);
            wxapi.registerApp("wxb3852e6a6b7d9516");
            PayReq payReq = new PayReq();
            payReq.appId = "wxb3852e6a6b7d9516";
            payReq.partnerId = payBean.getPartnerId();
            payReq.prepayId = payBean.getPrepayId();
            payReq.nonceStr = payBean.getNonceStr();
            payReq.timeStamp = payBean.getTimeStamp()+"";
            payReq.packageValue = payBean.getPackageValue();
            payReq.sign = payBean.getSign();
            payReq.extData = "app data"; // optional
            wxapi.sendReq(payReq);
            RommActivity.this.finish();
        }else {
            Toast.makeText(this,payBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void movieScheduleError(String msg) {

    }

    class myCall implements DataCall {
        @Override
        public void onSuccess(Object o) {
            List<Schedule.ResultBean> result = (List<Schedule.ResultBean>) o;
            ScheAdapte scheAdapte = new ScheAdapte(RommActivity.this, result);
            scheAdapte.setOnActi(new ScheAdapte.OnActi() {
                @Override
                public void onActi(int i) {
                    SeatPresenter seatPresenter = new SeatPresenter(new SeatCall());
                    seatPresenter.getModel(i);
                }
            });
            roomRecycler.setAdapter(scheAdapte);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RommActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            roomRecycler.setLayoutManager(linearLayoutManager);
        }

        @Override
        public void onFalse(String msg) {

        }
    }

    class SeatCall implements DataCall {
        @Override
        public void onSuccess(Object o) {
            List<Seat.ResultBean> result = (List<Seat.ResultBean>) o;
            Log.i("xxx", "onSuccess: " + result.get(0).getRow());
            MovieSeatAdapter movieSeatAdapter = new MovieSeatAdapter(result);
            roomMovieSeat.setAdapter(movieSeatAdapter);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(RommActivity.this, 6);
            roomMovieSeat.setLayoutManager(gridLayoutManager);
            movieSeatAdapter.setOnActi(new ScheAdapte.OnActi() {
                @Override
                public void onActi(int i) {
                    n = i;
                    if (n != 0) {
                        btnPurchaseOrder.setVisibility(View.VISIBLE);
                        btnPurchaseOrder.setText("订" + n + "张票");
                        roomBtn.setVisibility(View.GONE);
                    } else {
                        linerLay.setVisibility(View.GONE);
                        btnPurchaseOrder.setVisibility(View.GONE);
                        roomBtn.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        @Override
        public void onFalse(String msg) {

        }
    }

    @OnClick({R.id.radio_wx, R.id.radio_zzfb, R.id.room_btn, R.id.btn_purchaseOrder})
    public void onClick(View view) {
        switch (view.getId()) {
            //微信
            case R.id.radio_wx:
                radioWx.setChecked(false);
                String s = userId+""+ tid + "movie";
                Log.e("MessageUserId",userId+"");
                Log.e("MessageUserId",id+"");
                Log.e("MyMessageSss",s);
                String sign = MD5Utils.MD5(s);
                Log.e("MyMessageSsign",sign);
                presenter.buyMovieTickets(userId,sessionId,tid,string,sign);
                break;
            //支付宝
            case R.id.radio_zzfb:
                radioZzfb.setChecked(false);
                break;
            case R.id.room_btn:
                break;
            //立即支付
            case R.id.btn_purchaseOrder:
                real.setVisibility(View.GONE);
                linerLay.setVisibility(View.VISIBLE);
                break;
            default:break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        roomVideoPlayer.releaseAllVideos();
    }
}
