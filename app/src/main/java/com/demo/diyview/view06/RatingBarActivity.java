package com.demo.diyview.view06;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.demo.diyview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by raytine on 2017/7/26.
 */

public class RatingBarActivity extends AppCompatActivity {
    @BindView(R.id.ratingView)
    RatingBar ratingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ratingbar_activity);
        ButterKnife.bind(this);
    }
}
