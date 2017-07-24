package com.demo.diyview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;

import com.demo.diyview.adapter.ListScreenAdapter;
import com.demo.diyview.view.ColorTrackTextView;
import com.demo.diyview.view.ListDataScreenView;
import com.demo.diyview.view.MyTextView;
import com.demo.diyview.view.View01;


public class MainActivity extends AppCompatActivity {
    private View01 myTextView;
    private MyTextView mmyTextView;
    private ColorTrackTextView colorTrackTextView;
    private ListDataScreenView listDataScreenView;
    private ListScreenAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initMyTextView();
//        initView01();//仿QQ计步器View
        initView();
    }

    private void initView() {
        //字体变色
        colorTrackTextView = (ColorTrackTextView) findViewById(R.id.colorTextView);
        //常见多条目属性菜单
//        listDataScreenView = (ListDataScreenView) this.findViewById(R.id.view111);
//        listDataScreenView.setAdapter(new ListScreenAdapter(this));
    }

    private void initMyTextView() {
        mmyTextView = (MyTextView) this.findViewById(R.id.myTextView);

    }

    private void initView01() {
        myTextView = (View01) findViewById(R.id.test);
        myTextView.setmStepMax(4000);
        //属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 3000);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                myTextView.setmCurrentStep((int)animatedValue);
            }
        });
        valueAnimator.start();
    }
}
