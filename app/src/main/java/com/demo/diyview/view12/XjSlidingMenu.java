package com.demo.diyview.view12;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

/**
 * Created by xws on 2017/8/1.
 */

public class XjSlidingMenu extends HorizontalScrollView {
    private int distance = 62;
    private int menuWidth;
    private int dip2px(int i) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,i,getResources().getDisplayMetrics());
    }

    public XjSlidingMenu(Context context) {
        this(context,null);
    }

    public XjSlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public XjSlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        distance = dip2px(62);
        menuWidth = getScreenWidth()-distance;
    }

    /**
     * 布局解析完毕调用
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ViewGroup childAt = (ViewGroup) getChildAt(0);
        if (childAt.getChildCount() !=2){
            throw  new RuntimeException("只能放置2个子View");
        }
        /**
         * 菜单页
         */
        View menuView = childAt.getChildAt(0);
        ViewGroup.LayoutParams layoutParams = menuView.getLayoutParams();
        layoutParams.width = menuWidth;
        menuView.setLayoutParams(layoutParams);
        /**
         * 内容页
         */
        View contentView = childAt.getChildAt(1);
        ViewGroup.LayoutParams layoutParam = contentView.getLayoutParams();
        layoutParam.width = getScreenWidth();
        contentView.setLayoutParams(layoutParam);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        scrollTo(menuWidth,0);
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    private int getScreenWidth(){
        WindowManager wm = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}
