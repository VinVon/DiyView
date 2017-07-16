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
import android.view.MotionEvent;
import android.view.View;


import com.demo.diyview.R;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by raytine on 2017/7/14.
 */

public class MyTextView extends View {
    private String text;
    private int textSize = 15;
    private int textColor = Color.RED;
    private Rect rect;
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
        text =  typedArray.getString(R.styleable.MyTextView_text);
        textColor =  typedArray.getColor(R.styleable.MyTextView_textColor,textColor);
        textSize =  typedArray.getDimensionPixelSize(R.styleable.MyTextView_textSize,textSize);
        typedArray.recycle();
        paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        rect = new Rect();
        paint.getTextBounds(text,0,text.length(),rect);
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
        int size_width = MeasureSpec.getSize(widthMeasureSpec);
        int size_height = MeasureSpec.getSize(heightMeasureSpec);
        if (mode_width == MeasureSpec.AT_MOST && mode_height == MeasureSpec.AT_MOST){
                 setMeasuredDimension(getWidth(),getHeight());
                }else if(mode_width == MeasureSpec.AT_MOST){
                 setMeasuredDimension(getWidth(),size_height);
                }else if(mode_height == MeasureSpec.AT_MOST){
                 setMeasuredDimension(size_width,getHeight());
        }

    }

    /**
     * 绘制方法
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawRect(0,0,getWidth(),getHeight(),paint);
        canvas.save();
        //然后再调用父类的构造方法完成绘制
        super.onDraw(canvas);
        //取出画布的状态属性
        canvas.restore();
        paint.setColor(Color.RED);

        canvas.drawText(text,getWidth()/2-rect.width()/2,getHeight()/2+rect.height()/2,paint);

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
