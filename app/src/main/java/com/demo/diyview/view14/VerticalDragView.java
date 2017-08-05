package com.demo.diyview.view14;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by raytine on 2017/8/4.
 */

public class VerticalDragView extends FrameLayout {
    private ViewDragHelper viewDragHelper;
    private View view1,view2;
    public VerticalDragView(@NonNull Context context) {
        super(context);


    }

    public VerticalDragView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        viewDragHelper = ViewDragHelper.create(this,1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {

                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {

                return left;

            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        view1 = getChildAt(0);
        view2 = getChildAt(1);
    }

    public VerticalDragView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }
}
