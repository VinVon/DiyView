package com.demo.diyview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import com.demo.diyview.R;

/**
 * Created by raytine on 2017/7/14.
 */

public class MyTextView extends View {
    private String text;
    private int textSize = 15;
    private int textColor = Color.RED;
    public MyTextView(Context context) {
        this(context,null);
    }
    //布局中使用
    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    //布局文件中使用style，则调用该函数
    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        text =  typedArray.getString(R.styleable.MyTextView_text);
        textColor =  typedArray.getColor(R.styleable.MyTextView_textColor,textColor);
        textSize =  typedArray.getDimensionPixelSize(R.styleable.MyTextView_textSize,textSize);
        typedArray.recycle();
    }

    /**
     * 测量大小方法
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //指定控件的宽高
        //获取宽高的模式
        int mode_width = MeasureSpec.getMode(widthMeasureSpec);
        int mode_height = MeasureSpec.getMode(heightMeasureSpec);

    }

    /**
     * 绘制方法
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * 处理触摸事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("-----------","手指按下");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("-----------","手指移动");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("-----------","手指离开");
                break;
        }
        return super.onTouchEvent(event);
    }
}
