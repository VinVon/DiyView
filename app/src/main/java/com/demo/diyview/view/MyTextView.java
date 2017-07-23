package com.demo.diyview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;


import com.demo.diyview.R;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by raytine on 2017/7/14.
 */

public class MyTextView extends LinearLayout {
    private String text;
    private int textSize = 15;
    private int textColor = Color.RED;
    private Paint paint;
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
        text =  typedArray.getString(R.styleable.MyTextView_mtext);
        textColor =  typedArray.getColor(R.styleable.MyTextView_mtextColor,textColor);
        textSize =  typedArray.getDimensionPixelSize(R.styleable.MyTextView_mtextSize,sp2px(textSize));
        typedArray.recycle();
        paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        setWillNotDraw(false);
    }

    private int sp2px(int sp) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,getResources().getDisplayMetrics());
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
        //1 确定的值
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //不确定的值
        if (mode_width == MeasureSpec.AT_MOST){
            Rect rect = new Rect();
            paint.getTextBounds(text,0,text.length(),rect);
            width = rect.width()+getPaddingLeft()+getPaddingRight();
        }
        if (mode_height == MeasureSpec.AT_MOST){
            Rect rect = new Rect();
            paint.getTextBounds(text,0,text.length(),rect);
            height = rect.height()+getPaddingTop()+getPaddingBottom();
        }
        setMeasuredDimension(width,height);
    }

    /**
     * 绘制方法
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        //基线 baselines
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom-fontMetricsInt.top)/2-fontMetricsInt.bottom; //基线到高度一半的距离
        int baseLines = getHeight()/2 + dy;
        canvas.drawText(text,getPaddingLeft(),baseLines,paint);
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
                Log.e("-----------","手指移动");
                break;
        }
        invalidate();
        return true;
    }
    public void setOnCliCkhaha(OnClickListener li){
        li.OnClick(this);

    }
    private String randomText()
    {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set)
        {
            sb.append("" + i);
        }

        return sb.toString();
    }
    public interface OnClickListener {
        public void OnClick(View v);
    }
}
