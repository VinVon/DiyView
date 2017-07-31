package com.demo.diyview.view22;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.renderscript.Sampler;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;

import com.demo.diyview.R;

/**
 * Created by 123 on 2017/7/29.
 */

public class LoadingView extends RelativeLayout {
    private circleView mcircle1,mcircle2,mcircle3;
    private float leftDistance = dip2px(-40);
    private float rightDistance = dip2px(40);
    private int mFingerMoveDistance = (int) dip2px(20);
    private int leftColor = getResources().getColor(R.color.green),middleColor = getResources().getColor(R.color.red),rightColor =  getResources().getColor(R.color.blue);
    private  int count = 0;
    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mcircle1 = new circleView(getContext());
        mcircle1.changeColor(leftColor);
        addView(mcircle1);
        mcircle3 = new circleView(getContext());
        mcircle3.changeColor(rightColor);
        addView(mcircle3);
        mcircle2 = new circleView(getContext());
        mcircle2.changeColor(middleColor);
        addView(mcircle2);
        post(new Runnable() {
            @Override
            public void run() {
                open();
            }
        });
    }



    private void open() {
        ObjectAnimator animator = (ObjectAnimator) ObjectAnimator.ofFloat(mcircle1, "translationX",0, -mFingerMoveDistance);
        ObjectAnimator animator1 = (ObjectAnimator) ObjectAnimator.ofFloat(mcircle3, "translationX", 0,mFingerMoveDistance);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(1000);
        animSet.setInterpolator(new DecelerateInterpolator());
        //两个动画同时执行
        animSet.playTogether(animator, animator1);
//        animSet.play(animator).with(animator1);
        animSet.start();
        animSet.addListener(new AnimatorListenerAdapter(){
            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e("TAG","open");
               close();
            }
        } );

    }

    private void close() {
        ObjectAnimator animator3 = (ObjectAnimator) ObjectAnimator.ofFloat(mcircle1, "translationX", -mFingerMoveDistance,0);
        ObjectAnimator animator4 = (ObjectAnimator) ObjectAnimator.ofFloat(mcircle3, "translationX",mFingerMoveDistance, 0);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(2000);
        animSet.setInterpolator(new AccelerateInterpolator());
        //两个动画同时执行
        animSet.playTogether(animator3, animator4);
//        animSet.play(animator3).with(animator4);
        animSet.start();
        animSet.addListener(  new AnimatorListenerAdapter(){
            @Override
            public void onAnimationEnd(Animator animation) {
                int rightColor = mcircle3.getmColor();
                int leftColor = mcircle1.getmColor();
                int middleColor = mcircle2.getmColor();
                    mcircle1.changeColor(rightColor);
                    mcircle2.changeColor(leftColor);
                    mcircle3.changeColor(middleColor );
                open();
            }
        } );


    }

    public float dip2px(int dip){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dip,getResources().getDisplayMetrics());
    }
}
