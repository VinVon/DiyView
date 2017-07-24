package com.demo.diyview.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.demo.diyview.adapter.BaseMenuAdapter;

/**
 * Created by 123 on 2017/7/23.
 */

public class ListDataScreenView extends LinearLayout {
    private LinearLayout mMenuTabView;
    private Context mContext;

    private FrameLayout mMenuMiddleView;
    //阴影部分
    private View mShadowView;
    private int mShadowColor = Color.parseColor("#88999999");
    //菜单
    private FrameLayout mMenuContainerView;
    //菜单的adapter
    private BaseMenuAdapter baseMenuAdapter;
    public ListDataScreenView(Context context) {
        this(context,null);
    }

    public ListDataScreenView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ListDataScreenView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int containerHeight = (int) (height*75f/100);
        ViewGroup.LayoutParams layoutParams = mMenuContainerView.getLayoutParams();
        layoutParams.height  = containerHeight;
        mMenuContainerView.setLayoutParams(layoutParams);
    }

    private void initLayout() {
        setOrientation(VERTICAL);
        mMenuTabView = new LinearLayout(mContext);
        mMenuTabView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(mMenuTabView);
        mMenuMiddleView = new FrameLayout(mContext);
        mMenuMiddleView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mMenuMiddleView);
        mShadowView = new View(mContext);
        mShadowView.setBackgroundColor(mShadowColor);
        mMenuMiddleView.addView(mShadowView);
        //创建菜单，存放菜单内容
        mMenuContainerView = new FrameLayout(mContext);
        mMenuContainerView.setVisibility(GONE);
        mMenuMiddleView.addView(mMenuContainerView);
    }

    public  void  setAdapter(BaseMenuAdapter adapter){
        this.baseMenuAdapter = adapter;
        //获取多少条item
       int count =  baseMenuAdapter.getCount();
        for (int i = 0; i <count ; i++) {
            //获取tab
            View tabView = baseMenuAdapter.getTabView(i,mMenuTabView);
            mMenuTabView.addView(tabView);
            LinearLayout.LayoutParams layoutParams = (LayoutParams) tabView.getLayoutParams();
            layoutParams.weight = 1;
            tabView.setLayoutParams(layoutParams);
            //获取菜单的内容
            View menuView = baseMenuAdapter.getMenuView(i,mMenuContainerView);
            mMenuContainerView.addView(menuView);
        }
    };
}
