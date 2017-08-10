package com.demo.diyview.view17;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.demo.diyview.R;

/**
 * Created by raytine on 2017/8/10.
 */

public class Activity17 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity17_main);
        StatusBarUtil.setStatusBarColor(this,R.color.colorAccent);
    }
}
