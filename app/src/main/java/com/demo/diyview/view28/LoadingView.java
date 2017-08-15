package com.demo.diyview.view28;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.LinearInterpolator;

import com.demo.diyview.R;

/**
 * Created by 123 on 2017/8/13.
 */

public class LoadingView extends View{
    private boolean initColors = false;
    private int[] colors ;
    private Paint mPaint,mPaintBackground;
    //大圆半径
    private int mRadius;
    //小圆圆半径
    private int mSmallRadius;
    private int mCentX,mCentY;
    //度数
    private float singal;

    int mSplashColor = getResources().getColor(R.color.azure);
    private animationState mRotationState;
    //对角线
    private float degree ;
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
        }
        if (mRotationState == null){
            mRotationState = new RotationState();
        }
        mRotationState.draw(canvas);
    }

    private void initParams() {
        colors = getResources().getIntArray(R.array.colorsss);
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaintBackground = new Paint();
        mPaintBackground.setDither(true);
        mPaintBackground.setAntiAlias(true);
        mPaintBackground.setStyle(Paint.Style.STROKE);
        mPaintBackground.setColor(mSplashColor);
        mCentX = getMeasuredWidth()/2;
        mCentY = getMeasuredHeight()/2;
        mRadius = mCentX/2;
        mSmallRadius = mRadius/8;
        degree = (float) Math.sqrt(mCentX*mCentX+mCentY*mCentY);
    }

    /**
     * 消失
     */
    public void dispear() {
        if (mRotationState instanceof RotationState){
            ((RotationState) mRotationState).cancelAnimator();

        }
        mRotationState = new MegreState();
    }


    private abstract class animationState{
        public abstract void draw(Canvas canvas);
    }

    /**
     * 旋转动画
     */
    private class  RotationState extends animationState{
        private ValueAnimator animator;
        public RotationState() {

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
        }

        @Override
        public void draw(Canvas canvas) {
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
        public void cancelAnimator() {
            animator.cancel();
            animator = null;
        }
    }
    /**
     * 聚合动画
     */
    private float currentDistance;
    private class  MegreState extends animationState{
        private ValueAnimator animator;
        public MegreState() {

            animator = ValueAnimator.ofFloat(mRadius,0);
            animator.setDuration(2000);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    currentDistance = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRotationState = new ExpandState();
                }
            });
            animator.setInterpolator(new AnticipateInterpolator(3f));
            animator.start();
        }

        @Override
        public void draw(Canvas canvas) {
            //画6个圆
            // 绘制六个小圆 坐标
            canvas.drawColor(mSplashColor);
            float preAngle = (float) (2 * Math.PI / colors.length);
            for (int i = 0; i < colors.length; i++) {
                mPaint.setColor(colors[i]);
                // 初始角度 + 当前旋转的角度
                double angle = i * preAngle + singal;
                float cx = (float) (mCentX + (currentDistance) * Math.cos(angle));
                float cy = (float) (mCentY + (currentDistance) * Math.sin(angle));
                canvas.drawCircle(cx, cy, mSmallRadius, mPaint);
            }
        }
    }
    /**
     * 展开动画
     */
    private float currentDistances;
    private class  ExpandState extends animationState{
        private ValueAnimator animator;
        public ExpandState() {
            animator = ValueAnimator.ofFloat(0,degree);
            animator.setDuration(2000);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    currentDistances = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            animator.setInterpolator(new AccelerateInterpolator());
            animator.start();
        }

        @Override
        public void draw(Canvas canvas) {
            //画6个圆
            // 绘制六个小圆 坐标
            if (currentDistances > 0) {
                float stokeWidth = degree - currentDistances;
                mPaintBackground.setStrokeWidth(stokeWidth);
                float dRiaus = currentDistances+stokeWidth/2;
                canvas.drawCircle(mCentX,mCentY,dRiaus,mPaintBackground);
            } else {
                canvas.drawColor(mSplashColor);
            }

        }
    }
}
