package com.demo.diyview.view08;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.demo.diyview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * view 的绘制流程
 * Created by raytine on 2017/7/27.
 */

public class View08Activity extends AppCompatActivity {
    @BindView(R.id.view)
    TextView view;

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("TAG","onResume"+view.getMeasuredHeight()+"");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_08_activity);
        ButterKnife.bind(this);
        Log.e("TAG","onCreate"+view.getMeasuredHeight()+"");

        view.post(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG","run"+view.getMeasuredHeight()+"");
            }
        });
    }
}
