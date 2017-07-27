package com.demo.diyview.view07;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.demo.diyview.R;

/**
 * 字母索引列表
 * Created by raytine on 2017/7/27.
 */

public class LetterSideBar extends View {
    private Paint mPaint;
    private int mTextSize = 15;
    private int mTextColor = getResources().getColor(R.color.gray);
    private String[] list = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int mCurrentPosition ;
    private int singleHeight;
    private TextOnclick textOnclick;

    public void setTextOnclick(TextOnclick textOnclick) {
        this.textOnclick = textOnclick;
    }

    public LetterSideBar(Context context) {
        this(context,null);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(sp2px(mTextSize));
        mPaint.setColor(mTextColor);
    }
    private float sp2px(int sp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int a = (int) mPaint.measureText("A");
        int width = getPaddingLeft()+getPaddingRight()+a;
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

             singleHeight = (getHeight()-getPaddingTop()-getPaddingBottom())/list.length;
        for (int i = 0; i <list.length ; i++) {
            int dy = singleHeight*i+singleHeight/2+getPaddingTop();
            Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
            int dys =(fontMetricsInt.bottom-fontMetricsInt.top)/2 -fontMetricsInt.bottom;
            int baseLines = dy+dys;
            int posx = (int) ((getWidth()-mPaint.measureText(list[i]))/2);
            if (i == mCurrentPosition){
                mPaint.setColor(getResources().getColor(R.color.colorPrimary));
                canvas.drawText(list[i],posx,baseLines,mPaint);
            }else {
                mPaint.setColor(getResources().getColor(R.color.gray));
                canvas.drawText(list[i],posx,baseLines,mPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventY = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                if (mCurrentPosition != (int) (eventY/singleHeight)){
                    mCurrentPosition = (int) (eventY/singleHeight);
                    if (mCurrentPosition<0){
                        mCurrentPosition = 0;
                    }
                    if (mCurrentPosition>list.length-1){
                        mCurrentPosition = list.length-1;
                    }
                    Log.e("TAG","ACTION_DOWN");
                    this.textOnclick.onclick(list[mCurrentPosition],true);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:

                    if (mCurrentPosition != (int) (eventY/singleHeight)){
                        mCurrentPosition = (int) (eventY/singleHeight);
                        if (mCurrentPosition<0){
                            mCurrentPosition = 0;
                        }
                        if (mCurrentPosition>list.length-1){
                            mCurrentPosition = list.length-1;
                        }
                        Log.e("TAG","ACTION_MOVE");
                        this.textOnclick.onclick(list[mCurrentPosition],true);
                        invalidate();
                    }
                break;
            case MotionEvent.ACTION_UP:
                if (textOnclick!=null){
                    this.textOnclick.onclick(list[mCurrentPosition],false);
                }
                break;
        }
        return true;
    }
    public interface TextOnclick{
        public void onclick(String st,boolean istouch);
    }
}
