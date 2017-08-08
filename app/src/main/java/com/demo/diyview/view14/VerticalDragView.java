package com.demo.diyview.view14;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;

/**
 * Created by raytine on 2017/8/4.
 */

public class VerticalDragView extends FrameLayout {
    private ViewDragHelper viewDragHelper;
    private View view1, mDragListView;
    private boolean menuIsOPen = false;
    public VerticalDragView(@NonNull Context context) {
        super(context);


    }

    public VerticalDragView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        viewDragHelper = ViewDragHelper.create(this,1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {

                return mDragListView == child;
            }



            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                if (top > view1.getHeight()){
                    top = view1.getHeight();
                }
                if (top<0){
                    top = 0;
                }
                return top;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {

               if (mDragListView.getTop() > view1.getHeight()/2){
                   viewDragHelper.settleCapturedViewAt(0,view1.getHeight());
                   menuIsOPen = true;
               }else{
                   viewDragHelper.settleCapturedViewAt(0,0);
                   menuIsOPen = false;
               }
               invalidate();
            }
        });
    }

    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)){
            invalidate();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        view1 = getChildAt(0);
        mDragListView = getChildAt(1);
    }

    public VerticalDragView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private  float downY;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (menuIsOPen){
            return  true;
        }
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("TAG",ev.getAction()+"");
                downY = ev.getY();
                viewDragHelper.processTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = ev.getY();
                Log.e("Tag","da"+moveY);
                if ((moveY-downY)>0 && !canChildScrollUp()){//向下滑动
                    return  true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }
    public boolean canChildScrollUp() {

        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mDragListView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mDragListView;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mDragListView, -1) || mDragListView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mDragListView, -1);
        }
    }
}
