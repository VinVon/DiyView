package com.demo.diyview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.demo.diyview.R;

/**
 * QQStepView
 * Created by 123 on 2017/7/22.
 */

public class View01 extends View {
    private int  outerColor = Color.RED;
    private int  ineerColor = Color.BLUE;
    private int  cycleWidth  =   20;
    private int  textSize    =   20;
    private int  textColor   = Color.BLUE;
    private Paint mOutPaint ,mIneerPaint,mTextPaint;
    //总共步数
    private  int mStepMax  = 0;
    //当前步数
    private  int mCurrentStep  = 0;

    public View01(Context context) {
        super(context);
    }

    public View01(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public View01(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.QQStepView);
        outerColor = typedArray.getColor(R.styleable.QQStepView_outerColor,outerColor);
        ineerColor = typedArray.getColor(R.styleable.QQStepView_innerColor,ineerColor);
        cycleWidth = typedArray.getDimensionPixelSize(R.styleable.QQStepView_cycleWithd,cycleWidth);
        textSize = typedArray.getDimensionPixelSize(R.styleable.QQStepView_stepSize,textSize);
        textColor = typedArray.getColor(R.styleable.QQStepView_stepColor,textColor);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //定义大小
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width>height?height:width,width>height?height:width);
        mOutPaint = new Paint();
        mOutPaint.setAntiAlias(true);
        mOutPaint.setStrokeWidth(cycleWidth);
        mOutPaint.setColor(outerColor);
        mOutPaint.setStrokeCap(Paint.Cap.ROUND);
        mOutPaint.setStyle(Paint.Style.STROKE);

        mIneerPaint = new Paint();
        mIneerPaint.setAntiAlias(true);
        mIneerPaint.setStrokeWidth(cycleWidth);
        mIneerPaint.setColor(ineerColor);
        mIneerPaint.setStrokeCap(Paint.Cap.ROUND);
        mIneerPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(textColor);
        mTextPaint.setTextSize(textSize);
    }

    // 3.画外弧,画内弧,画文字
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //3.画外弧
        RectF mRextF =  new RectF(cycleWidth/2,cycleWidth/2,getWidth()-cycleWidth/2,getWidth()-cycleWidth/2);
        canvas.drawArc(mRextF,135,270,false,mOutPaint);
        //3.画内弧 画百分比
        float sweepAngle =(float)mCurrentStep/mStepMax;
        canvas.drawArc(mRextF,135,sweepAngle*270,false,mIneerPaint);
        //3.画文字
        String  steptext = mCurrentStep+"";
        Rect textBound = new Rect();
        mTextPaint.getTextBounds(steptext,0,steptext.length(),textBound);
        int dx = getWidth()/2-textBound.width()/2;
        //基线
        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        int dy =(fontMetricsInt.bottom-fontMetricsInt.top)/2+fontMetricsInt.bottom;
        int baseLines = getHeight()/2+dy;
         canvas.drawText(steptext,dx,baseLines,mTextPaint);
    }
    //其他，添加计步器动画等等

    public synchronized  void setmStepMax(int mStepMax) {
        this.mStepMax = mStepMax;
    }

    public synchronized  void setmCurrentStep(int mCurrentStep) {
        this.mCurrentStep = mCurrentStep;
        //不断绘制
        invalidate();
    }
}
