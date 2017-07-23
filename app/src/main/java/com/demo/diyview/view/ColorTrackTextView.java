package com.demo.diyview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;


import com.demo.diyview.R;


/**
 * 字体颜色变化
 * Created by 123 on 2017/7/23.
 */

public class ColorTrackTextView extends TextView {
    private int orginColor = Color.BLACK;
    private int changeColor = Color.RED;
    private Paint originPaint ;
    private Paint changePaint;
    public ColorTrackTextView(Context context) {
        this(context,null);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, null,0);
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
        String text = getText().toString();
        Rect rect = new Rect();
        changePaint.getTextBounds(text,0,text.length(),rect);
        int x = getWidth()/2- rect.width()/2;
        Paint.FontMetricsInt fontMetricsInt = changePaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom-fontMetricsInt.top)/2-fontMetricsInt.bottom;
        int baseLines = getHeight()/2+dy;
        canvas.drawText(text,x,baseLines,changePaint);
    }
}
