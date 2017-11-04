package com.demo.diyview.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.demo.diyview.adapter.BaseMenuAdapter;

/**
 * Created by 123 on 2017/7/23.
 */

public class ListDataScreenView extends LinearLayout implements View.OnClickListener{
    private LinearLayout mMenuTabView;
    private Context mContext;

    private FrameLayout mMenuMiddleView;
    //阴影部分
    private View mShadowView;
    private int mShadowColor = 0x88888888;
    //菜单
    private FrameLayout mMenuContainerView;
    //菜单的adapter
    private BaseMenuAdapter baseMenuAdapter;
    //内容菜单的高度
    private int mMenuContainerHeight = 0 ;
    private boolean mAnimatorExecute =false ;
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
        Log.e("TAG","mMenuContainerHeight = "+mMenuContainerHeight);
        if (mMenuContainerHeight == 0 && height>0){
            mMenuContainerHeight = (int) (height*75f/100);
            ViewGroup.LayoutParams layoutParams = mMenuContainerView.getLayoutParams();
            layoutParams.height  = mMenuContainerHeight;
            mMenuContainerView.setLayoutParams(layoutParams);
            mMenuContainerView.setTranslationY(-mMenuContainerHeight);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
        mShadowView.setAlpha(0);
        mShadowView.setVisibility(GONE);
        mShadowView.setOnClickListener(this);
        mMenuMiddleView.addView(mShadowView);
        //创建菜单，存放菜单内容
        mMenuContainerView = new FrameLayout(mContext);
        mMenuContainerView.setVisibility(GONE);
        mMenuContainerView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
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
            layoutParams.height = dip2px(45);
            layoutParams.weight = 1;
            tabView.setLayoutParams(layoutParams);
            //设置点击事件
            setTabClick(tabView,i);
            //获取菜单的内容
            final View menuView = baseMenuAdapter.getMenuView(i,mMenuContainerView);
            menuView.setVisibility(GONE);
            mMenuContainerView.addView(menuView);
        }

    }

    /**
     * 设置tab的点击事件
     * @param tabView
     * @param position
     */
    private int mCurrentPOSiton= -1;

    private void setTabClick(final View tabView, final int position) {
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //菜单栏没打开
                if (mCurrentPOSiton == -1) {
                    openMenu(position, tabView);
                }
                else{
                   if (mCurrentPOSiton == position){
                       colseMenu();

                   }else {
                       //切换显示
                       View childAt = mMenuContainerView.getChildAt(mCurrentPOSiton);
                       childAt.setVisibility(GONE);
                       baseMenuAdapter.setMenuClose(mMenuTabView.getChildAt(mCurrentPOSiton));
                       mCurrentPOSiton = position;
                       View childAts = mMenuContainerView.getChildAt(position);
                       childAts.setVisibility(VISIBLE);
                       baseMenuAdapter.setMenuOpen(mMenuTabView.getChildAt(mCurrentPOSiton));
                   }
                }
            }
        });
    }
    /**
     * g关闭菜单
     */
    private void colseMenu() {
        if (mAnimatorExecute){
            return;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(mMenuContainerView,"translationY",0,-mMenuContainerHeight);
        animator.setDuration(350);
        animator.start();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mShadowView,"alpha",1,0);
        animator1.setDuration(350);

        animator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                View childAt = mMenuContainerView.getChildAt(mCurrentPOSiton);
                childAt.setVisibility(GONE);
                mShadowView.setVisibility(GONE);
                mCurrentPOSiton = -1;
                mAnimatorExecute =false;
            }

            @Override
            public void onAnimationStart(Animator animation) {
                mAnimatorExecute =true;
                baseMenuAdapter.setMenuClose(mMenuTabView.getChildAt(mCurrentPOSiton));
            }
        });
        animator1.start();
    }



    /**
     * 打开菜单
     * @param position
     * @param tabView
     */
    private void openMenu(int position, final View tabView) {
        if (mAnimatorExecute){
            return;
        }
        mShadowView.setVisibility(VISIBLE);
        mMenuContainerView.setVisibility(VISIBLE);
        View childAt = mMenuContainerView.getChildAt(position);
        childAt.setVisibility(VISIBLE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mMenuContainerView,"translationY",-mMenuContainerHeight,0);
        animator.setDuration(350);
        animator.start();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mShadowView,"alpha",0,1);
        animator1.setDuration(350);

        animator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mAnimatorExecute = false;
            }

            @Override
            public void onAnimationStart(Animator animation) {
                mAnimatorExecute = true;
                baseMenuAdapter.setMenuOpen(tabView);
            }
        });
        animator1.start();
        mCurrentPOSiton = position;
    }


    private int dip2px(int dr){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dr,getResources().getDisplayMetrics());
    }

    @Override
    public void onClick(View v) {
        if (mCurrentPOSiton !=-1){
        colseMenu();}
    }
}
