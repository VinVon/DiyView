package com.demo.diyview.view11;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by raytine on 2017/7/31.
 */

public class TouchViewGroup extends LinearLayout {

    public TouchViewGroup(Context context) {
        super(context);
    }

    public TouchViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            childAt.layout(0,0,childAt.getMeasuredWidth(),childAt.getMeasuredHeight());
        }
    }

    /**
     * 事件分发
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("TAG","group--dispatch"+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 事件拦截
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("TAG","group--Intercept"+ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 事件触摸
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("TAG","group--ontouch->"+event.getAction());
        return super.onTouchEvent(event);
    }
}
