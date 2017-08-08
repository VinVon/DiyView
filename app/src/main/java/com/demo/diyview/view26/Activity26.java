package com.demo.diyview.view26;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.demo.diyview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by raytine on 2017/8/7.
 */

public class Activity26 extends AppCompatActivity {
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.re_re)
    LoveLayout reRe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity26_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn})
    public void OnClick(View view) {
        for (int i = 0; i < 10; i++) {
            reRe.addImageView();
        }
    }
}
