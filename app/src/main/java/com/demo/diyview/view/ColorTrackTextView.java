package com.demo.diyview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import com.demo.diyview.R;


/**
 * 字体颜色变化
 * Created by 123 on 2017/7/23.
 */

public class ColorTrackTextView extends android.support.v7.widget.AppCompatTextView {
    private int orginColor = Color.BLACK;
    private int changeColor = Color.RED;
    private Paint originPaint ;
    private Paint changePaint;
    private float mCurrentProgress = 0.5f;
    public ColorTrackTextView(Context context) {
        this(context,null);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackView);
        orginColor =  typedArray.getColor(R.styleable.ColorTrackView_originColor,orginColor);
        changeColor =  typedArray.getColor(R.styleable.ColorTrackView_changeColor,changeColor);
        initPaint();
        typedArray.recycle();

    }

    private void initPaint() {
        originPaint = new Paint();
        originPaint.setAntiAlias(true);
        originPaint.setColor(orginColor);
        originPaint.setTextSize(getTextSize());
        changePaint = new Paint();
        changePaint.setAntiAlias(true);
        changePaint.setColor(changeColor);
        changePaint.setTextSize(getTextSize());
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.save();
        //根据当前进度算出需要画不变色长度的文字
        int left  = (int) (mCurrentProgress*getWidth());
        canvas.clipRect(0,0,left,getHeight());
        String text = getText().toString();
        Rect rect = new Rect();
        originPaint.getTextBounds(text,0,text.length(),rect);
        int x = getWidth()/2- rect.width()/2;
        Paint.FontMetricsInt fontMetricsInt = originPaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom-fontMetricsInt.top)/2-fontMetricsInt.bottom;
        int baseLines = getHeight()/2+dy;
        canvas.drawText(text,x,baseLines,originPaint);
        canvas.restore();
        //变色文字长度
        canvas.save();
        canvas.clipRect(left,0,getWidth(),getHeight());
        canvas.drawText(text,x,baseLines,changePaint);
        canvas.restore();
    }
}
