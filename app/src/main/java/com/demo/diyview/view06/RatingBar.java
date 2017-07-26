package com.demo.diyview.view06;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.demo.diyview.R;

import java.lang.ref.Reference;

/**
 * Created by raytine on 2017/7/26.
 */

public class RatingBar extends View {
    private int mSelectorimg;
    private int mUnSelectorimg;
    private int mStartCount;

    private Paint mPaint;
    private Bitmap biemap ;
    private Bitmap biemap0 ;

    private int selectorCount = 0;
    public RatingBar(Context context) {
        this(context, null);
    }

    public RatingBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ratingBarView);
        mSelectorimg = typedArray.getResourceId(R.styleable.ratingBarView_rtSelector, mSelectorimg);
        mUnSelectorimg = typedArray.getResourceId(R.styleable.ratingBarView_rtUnSelector, mUnSelectorimg);
        mStartCount = typedArray.getInt(R.styleable.ratingBarView_rtStartCount, mStartCount);
        typedArray.recycle();
        mPaint = new Paint();
        biemap = BitmapFactory.decodeResource(getResources(), mUnSelectorimg);
        biemap0 = BitmapFactory.decodeResource(getResources(), mSelectorimg);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = biemap.getHeight()+getPaddingTop()+getPaddingBottom();
        int width = mStartCount*(biemap.getWidth()+getPaddingRight()+getPaddingLeft());
        Log.e("---onmearsea","getpaddingLeft :"+getPaddingLeft()+"getpaddingRight :"+getPaddingRight());
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mStartCount; i++) {
            int dx;
         if (i<selectorCount){
             dx = biemap0.getWidth()*i + getPaddingLeft()*(i+1) + getPaddingRight()*(i);
             canvas.drawBitmap(biemap0,dx, getPaddingTop(), mPaint);
         }else{
             dx = biemap.getWidth()*i + getPaddingLeft()*(i+1) + getPaddingRight()*(i);
             canvas.drawBitmap(biemap,dx, getPaddingTop(), mPaint);
         }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

            case MotionEvent.ACTION_MOVE:

            case MotionEvent.ACTION_UP:
               int Count = (int)(event.getX()/getWidth()*5)+1;
                if (Count<0){
                    Count = 0;
                }
                if (Count>mStartCount){
                    Count = mStartCount;
                }
                if (Count != selectorCount){
                    selectorCount = Count;
                    invalidate();
                    Log.e("----","draw");
                }
        }
        return true;
    }


}
