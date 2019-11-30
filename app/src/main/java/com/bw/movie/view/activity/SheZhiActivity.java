package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.bw.movie.R;
public class SheZhiActivity extends AppCompatActivity {


    private ImageView iv;
    private ImageView iv_update;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_zhi);
        initView();

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SheZhiActivity.this, LoginActivity.class));
            }
        });
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.iv);
        iv_update = (ImageView) findViewById(R.id.iv_update);
        back = (Button) findViewById(R.id.back);
    }
}
