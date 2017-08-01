package com.demo.diyview.view12;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.demo.diyview.R;

/**
 * Created by raytine on 2017/8/1.
 */

public class Activity12 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity12_main);
        getWindowManager();
    }
}
