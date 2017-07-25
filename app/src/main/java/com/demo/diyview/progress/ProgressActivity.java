package com.demo.diyview.progress;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import com.demo.diyview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 进度条
 * Created by raytine on 2017/7/25.
 */

public class ProgressActivity extends AppCompatActivity {

    @BindView(R.id.view)
    PregressView view;
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_activity);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn)
    public void onViewClicked() {
        view.setStepMax(6000);
        ValueAnimator animator = ObjectAnimator.ofFloat(0,6000);
        animator.setDuration(3000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                view.setStepCurrent((int) animatedValue);
            }
        });
        animator.start();
    }
}
