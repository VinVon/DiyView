package com.demo.diyview.view23;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.demo.diyview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 123 on 2017/7/30.
 */

public class MeaasgeActivity extends AppCompatActivity {
    @BindView(R.id.view)
    MessageBubbleView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_activity);
        ButterKnife.bind(this);
    }
}
