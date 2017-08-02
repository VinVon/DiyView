package com.demo.diyview.view12;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.demo.diyview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by raytine on 2017/8/1.
 */

public class Activity12 extends AppCompatActivity {
    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.headView)
    ImageView headView;
    @BindView(R.id.sliding)
    XjSlidingMenu sliding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity12_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Activity12.this, "点击头像", Toast.LENGTH_SHORT).show();
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sliding.openMenu();
            }
        });
    }
}
