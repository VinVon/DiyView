package com.demo.diyview.view22;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.demo.diyview.R;

/**
 * Created by 123 on 2017/7/29.
 */

public class circleView extends View {
    private Paint mPaint;

    public circleView(Context context) {
        super(context);
        mPaint =  new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLUE);
    }

    public circleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public circleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth()/2,getHeight()/2,20,mPaint);
    }
    public void getLeftCitcleView(int c){
        mPaint.setColor(c);
        invalidate();
    }
    public void getMiddleCitcleView(int c){
        mPaint.setColor(c);
        invalidate();
    }
    public void getRightCitcleView(int c){
        mPaint.setColor(c);
        invalidate();
    }
    public void changeColor(int color){
        mPaint.setColor(color);
        invalidate();
    };
}
