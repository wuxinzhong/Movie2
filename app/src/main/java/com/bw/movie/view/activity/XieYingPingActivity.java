package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.DaoSession;
import com.bw.movie.dao.UserDao;
import com.bw.movie.model.bean.CommentBean;
import com.bw.movie.model.bean.User;
import com.bw.movie.presenter.MovieCommentPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XieYingPingActivity extends BaseActivity<MovieCommentPresenter> implements Constraint.IMovieCommentView {

    @BindView(R.id.movie_xie_yp_back)
    ImageView movieXieYpBack;
    @BindView(R.id.movie_xie_yp_name)
    TextView movieXieYpName;
    @BindView(R.id.movie_xie_yp_bar)
    RatingBar movieXieYpBar;
    @BindView(R.id.movie_xie_yp_edit)
    EditText movieXieYpEdit;
    @BindView(R.id.movie_xie_yp_btn)
    Button movieXieYpBtn;
    private String mXieYp;
    private UserDao mUserDao;
    private String sessionId;
    private int userId;
    private int mMovieId;
    private String mName;


    @Override
    void initData() {

    }

    @Override
    MovieCommentPresenter getPresenter() {
        return new MovieCommentPresenter();
    }

    @Override
    void initListener() {

        Intent intent = getIntent();
        mMovieId = intent.getIntExtra("movieId", 0);
        mName = intent.getStringExtra("name");

        movieXieYpName.setText(mName);

        DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        mUserDao = daoSession.getUserDao();

        List<User> users = mUserDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            sessionId = users.get(i).getSessionId();
            userId = users.get(i).getUserId();
        }
    }

    @Override
    int initLayout() {
        return R.layout.activity_xie_ying_ping;
    }

    @OnClick({R.id.movie_xie_yp_back, R.id.movie_xie_yp_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.movie_xie_yp_back:
                finish();
                break;
            case R.id.movie_xie_yp_btn:
                if (submit()) {

                    float rating = movieXieYpBar.getRating();

                    presenter.comment(sessionId, userId, mMovieId, mXieYp, String.valueOf(rating));
                }
                break;
        }
    }

    @Override
    public void commentSuccess(CommentBean commentBean) {
        if (commentBean.status.equals("0000")) {
            finish();
        } else if (commentBean.status.equals("9999")) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            Toast.makeText(this, commentBean.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void commentError(String s) {

    }

    private boolean submit() {
        // validate
        mXieYp = movieXieYpEdit.getText().toString().trim();
        if (TextUtils.isEmpty(mXieYp)) {
            Toast.makeText(this, "请输入评论", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
