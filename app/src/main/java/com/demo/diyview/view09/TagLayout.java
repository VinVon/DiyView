package com.demo.diyview.view09;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 流式布局
 * Created by raytine on 2017/7/29.
 */

public class TagLayout extends ViewGroup {
    private BaseTagAdapter mBaseTagAdapter;
    private List<List<View>> mChildViews = new ArrayList<>();

    public TagLayout(Context context) {
        this(context,null);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mChildViews.clear();
        int width = MeasureSpec.getSize(widthMeasureSpec);
        //布局高度
        int height = getPaddingTop()+getPaddingBottom();
        //子布局的一行的宽度
        int lineWidth = getPaddingLeft()+getPaddingRight();
        // 子View高度不一致的情况下
        int maxHeight = 0;
        //测量子view
        int childCount = getChildCount();
        ArrayList<View> childViews = new ArrayList<>();
        for (int i = 0; i <childCount ; i++) {
            View childView = getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
            //margin值
            ViewGroup.MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();

            if (lineWidth+childView.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin>width){
                //换行
                height += maxHeight;
                lineWidth=childView.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
                mChildViews.add(childViews);
                childViews = new ArrayList<>();
                childViews.add(childView);
                if (i== (childCount-1)){
                    height+=childView.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;
                    mChildViews.add(childViews);
                }
            }else{
                maxHeight = Math.max(maxHeight,childView.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin);
                lineWidth+=childView.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
                childViews.add(childView);
                if (i== (childCount-1)){
                    height+=maxHeight;
                    mChildViews.add(childViews);
                }
            }
        }
        setMeasuredDimension(width,height);
    }

    /**
     * 摆放子view
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left,top = getPaddingTop(),right,bottom;
        // 子View高度不一致的情况下
        int maxHeight = 0;
       for (List<View> views :mChildViews){
           left = getPaddingLeft();
            for (View mView : views){
                ViewGroup.MarginLayoutParams layoutParams = (MarginLayoutParams) mView.getLayoutParams();
                left+=layoutParams.leftMargin;
                int childTop = top + layoutParams.topMargin;
                maxHeight = Math.max(maxHeight,mView.getMeasuredHeight()+ layoutParams.topMargin+ layoutParams.bottomMargin);
                right = mView.getMeasuredWidth()+left;
                bottom = childTop+mView.getMeasuredHeight();
                mView.layout(left,childTop,right,bottom);
                left += mView.getMeasuredWidth()+layoutParams.rightMargin;
            }
            top+=maxHeight;
       }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    public void setmBaseTagAdapter(BaseTagAdapter mBaseTagAdapter) {
        if (mBaseTagAdapter == null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        removeAllViews();
        this.mBaseTagAdapter = mBaseTagAdapter;
        int count = this.mBaseTagAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View view = this.mBaseTagAdapter.getView(i, this);
            addView(view);
        }

    }
}
