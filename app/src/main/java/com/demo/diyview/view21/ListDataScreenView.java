package com.demo.diyview.view21;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * 多条目属性菜单
 * Created by raytine on 2017/10/11.
 */

public class ListDataScreenView extends LinearLayout {
    //头部tab
    private LinearLayout mMenuTabView;
    private Context mContext;
    private FrameLayout mMiddleDataView;
    //阴影区域
    private View mShadowView;
    //菜单区域
    private FrameLayout mMenuContainerView;
    private int  mShadowColor = Color.parseColor("#999999");
    public ListDataScreenView(Context context) {
        this(context,null);
    }

    public ListDataScreenView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ListDataScreenView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initLayout();
    }

    private void initLayout() {
        //创建头部
        mMenuTabView = new LinearLayout(mContext);
        mMenuTabView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(mMenuTabView);
        //frameLayout 存菜单和阴影部分
        mMiddleDataView = new FrameLayout(mContext);
        mMiddleDataView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(mMiddleDataView);
        mShadowView = new View(mContext);
        mShadowView.setBackgroundColor(mShadowColor);
        mMiddleDataView.addView(mShadowView);
        //创建菜单，存放内容
        mMenuContainerView = new FrameLayout(mContext);
        mMiddleDataView.addView(mMenuContainerView);
    }

}
