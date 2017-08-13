package com.demo.diyview.view28;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.demo.diyview.R;

/**
 * Created by 123 on 2017/8/13.
 */

public class LoadingView extends View{
    private boolean initColors = false;
    private int[] colors ;
    private Paint mPaint;
    //大圆半径
    private int mRadius;
    //小圆圆半径
    private int mSmallRadius;
    private int mCentX,mCentY;
    //度数
    private float singal;
    private ValueAnimator animator;
    int mSplashColor = getResources().getColor(R.color.peachpuff);
    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        if (!initColors){
            initColors = true;
            initParams();
            drawRotation(canvas);
        }
        float preAngle = (float) (2 * Math.PI / colors.length);
        for (int i = 0; i < colors.length; i++) {
            mPaint.setColor(colors[i]);
            // 初始角度 + 当前旋转的角度
            double angle = i * preAngle + singal;
            float cx = (float) (mCentX + mRadius * Math.cos(angle));
            float cy = (float) (mCentY + mRadius * Math.sin(angle));
            canvas.drawCircle(cx, cy, mSmallRadius, mPaint);
        }
    }

    private void initParams() {
        colors = getResources().getIntArray(R.array.colorsss);
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mCentX = getMeasuredWidth()/2;
        mCentY = getMeasuredHeight()/2;
        mRadius = mCentX/2;
        mSmallRadius = mRadius/8;
    }

    private void drawRotation(Canvas canvas) {
        animator = ValueAnimator.ofFloat(0, 2 * (float) Math.PI);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                 singal = (float) animation.getAnimatedValue();
                 invalidate();
            }
        });
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(-1);
        animator.start();
        //画6个圆
        // 绘制六个小圆 坐标
        canvas.drawColor(mSplashColor);
        float preAngle = (float) (2 * Math.PI / colors.length);
        for (int i = 0; i < colors.length; i++) {
            mPaint.setColor(colors[i]);
            // 初始角度 + 当前旋转的角度
            double angle = i * preAngle + singal;
            float cx = (float) (mCentX + mRadius * Math.cos(angle));
            float cy = (float) (mCentY + mRadius * Math.sin(angle));
            canvas.drawCircle(cx, cy, mSmallRadius, mPaint);
        }

    }
}
