package com.demo.diyview.view11;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.demo.diyview.R;
import com.demo.diyview.view10.TouchView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by raytine on 2017/7/31.
 */

public class TouchViewGroupActivity extends AppCompatActivity {

    @BindView(R.id.touchview)
    TouchView touchview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touchviewgroup_activity);
        ButterKnife.bind(this);
        touchview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("TAG", "view---ontouchlistener" + event.getAction());
                return false;
            }
        });
//        touchview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("TAG", "view---onclicklistener");
//            }
//        });
    }

}
