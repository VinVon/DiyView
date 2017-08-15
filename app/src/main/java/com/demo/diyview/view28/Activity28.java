package com.demo.diyview.view28;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.demo.diyview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 123 on 2017/8/13.
 */

public class Activity28 extends AppCompatActivity {
    @BindView(R.id.loading_view)
    LoadingView loadingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity28_main);
        ButterKnife.bind(this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingView.dispear();
            }
        },5000);
    }
}
