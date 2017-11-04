package com.demo.diyview.docDemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by raytine on 2017/10/13.
 */

public class SlideTab extends HorizontalScrollView {
    private Context mContext;
    private Paint paint;
    private int textSize = 5;
    private int textColor = Color.RED;
    private int paddingLeft = sp2px(15);
    private String[] strs = null;
    private int current;//滑动的距离
    private GestureDetector gestureDetector;
    //宽度
    private int mWidth;

    //选中条件的下标
    public volatile int mIndex = 0;
    //当前页面条目的位置
    public volatile int mPosition = 0;
    private  int ss =0;
    //翻页次数
    private int count = 0;
    private boolean isRight = false;//是否右移
        private textOclickListener mTextOnclickListener;
    public SlideTab(Context context) {
        this(context, null);
    }

    public SlideTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    public void setmTextOnclickListener(textOclickListener mTextOnclickListener) {
        this.mTextOnclickListener = mTextOnclickListener;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (strs.length >= 4) {
            mWidth = MeasureSpec.getSize(widthMeasureSpec) / 4;
        } else {
            mWidth = MeasureSpec.getSize(widthMeasureSpec) / strs.length;
        }
        Log.e("TAG", "onMeasure" + mWidth);
        LinearLayout childAt = (LinearLayout) getChildAt(0);
        for (int i = 0; i < strs.length; i++) {
            TextView tv = new TextView(mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.width = mWidth;
            tv.setLayoutParams(layoutParams);
            tv.setGravity(Gravity.CENTER);

            tv.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                   downX = (int) event.getX();
                    return false;
                }
            });
            tv.setText(strs[i]);
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(sp2px(textSize));
            final int finalI = i;
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTextOnclickListener.click(v, finalI);
                }
            });
            childAt.addView(tv);
        }
        setmCurrentColor(mIndex);
    }

    public void setStrs(String[] strs) {
        this.strs = strs;

        invalidate();
    }
    //滑动转化字体颜色

    int downX = 0;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            downX = (int) ev.getX();
                return true;
        }
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            int downX3 = (int) ev.getX();
            return true;
//            if(3 >mIndex){
//                return true;
//            }
//            if (mPosition == 0 && (downX3 - downX) < 0) {
//                return true;
//            }
//            if (mPosition == 3 && (downX3 - downX) > 0) {
//                return true;
//            }
//            if (mPosition == 1 || mPosition == 2) {
//                return true;
//            }
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            int downX2 = (int) ev.getX();
            current = getScrollX();
            if ((strs.length - 4) < mIndex) {//最后4个条目出现的情况不能右移
                int cus = downX2 - downX;
                if (mIndex == (strs.length - 1) && cus < 0) {

                }else{
                    juedgMove(cus);
                    setmCurrentColor(mIndex);
                }
                Log.e("TAG1", "滑动的当前下标" + mIndex + "==count" + count + "==mPosition" + mPosition+"downX2"+(downX2-downX));
                return true;
            } else if (3 > mIndex) {//前4个条目出现的情况
                int cus = downX2 - downX;
                if (mIndex == 0 && cus > 0) {

                }else{
                    juedgMove(cus);
                    setmCurrentColor(mIndex);
                }
                Log.e("TAG2", "滑动的当前下标" + mIndex + "==count" + count + "==mPosition" + mPosition+"downX2"+(downX2-downX));
                return true;
            } else if ((strs.length - 4) == mIndex) {
                int cus = downX2 - downX;
                juedgMove(cus);
                setmCurrentColor(mIndex);
                Log.e("TAG3", "滑动的当前下标" + mIndex + "==count" + count + "==mPosition" + mPosition+"downX2"+(downX2-downX));
                scrollTo(4 * mWidth * count, 0);
            } else if (3 == mIndex && (downX2 - downX) > 0) {
                int cus = downX2 - downX;
                juedgMove(cus);
                setmCurrentColor(mIndex);
                Log.e("TAG4", "滑动的当前下标" + mIndex + "==count" + count + "==mPosition" + mPosition+"downX2"+(downX2-downX));
            } else {
                int cus = downX2 - downX;
                 ss = mPosition;
                Log.e("TAG5", "滑动" + mIndex + "==cus" + cus + "==mPosition" + mPosition+"==downX2"+(downX2-downX));
                if (true) {
                    //关闭菜单
                    if (cus < 0) {
                        mIndex += 1;
                        mPosition += 1;
                        if (mPosition > 3) {
                            mPosition = 0;
                            count += 1;
                        }
                        int i = strs.length / 4;
                        int a = strs.length % 4;
                        if (a > 0) {
                            i++;
                        }
                        if (count >= i) {
                            count = i - 1;
                        }
                        if (mIndex >= (strs.length - 1)) {
                            mIndex = strs.length - 1;
                        }

                    } else {
                        mIndex -= 1;
                        mPosition -= 1;
                        if (mPosition < 0) {
                            mPosition = 3;
                            count -= 1;
                        }
                        if (count < 0) {
                            count = 0;
                        }
                        if (mIndex <= 0) {
                            mIndex = 0;
                        }
                    }
                    Log.e("TAG", "滑动的当前下标" + mIndex + "==count" + count + "==mPosition" + mPosition);
                    if (ss == 3 && mPosition == 0 && cus < 0 || mPosition == 3 && ss == 0 && cus > 0) {

                        scrollTo(4 * mWidth * count, 0);
                    }
                    setmCurrentColor(mIndex);
                } else {
                    //打开菜单
//                openMenu();
                }
            }

            return true;
        }
        return super.onTouchEvent(ev);
    }

    private void juedgMove(int cus) {
        if (cus < 0) {
            cus = Math.abs(cus);
            if (cus > mWidth / 2) {
                mIndex += 1;
                mPosition += 1;
                if (mPosition > 3) {
                    mPosition = 0;
                    count += 1;
                }
                int i = strs.length / 4;
                int a = strs.length % 4;
                if (a > 0) {
                    i++;
                }
                if (count >= i) {
                    count = i - 1;
                }

                if (mIndex >= (strs.length - 1)) {
                    mIndex = strs.length - 1;
                }
            }
        } else {
            if (cus > mWidth / 2) {
                mIndex -= 1;
                mPosition -= 1;
                if (mPosition < 0) {
                    mPosition = 3;
                    count -= 1;
                }
                if (count < 0) {
                    count = 0;
                }
                if (mIndex <= 0) {
                    mIndex = 0;
                }
            }
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
//        Log.e("TAG","onScrollChanged"+l+"=="+t+"=="+oldl+"==="+oldt);

        if (l - oldl > 0) {
            isRight = true;
            Log.e("TAG", "右移");
        } else {
            isRight = false;
            Log.e("TAG", "左移");
        }


    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sp, getResources().getDisplayMetrics());
    }

    public void setmCurrentColor(int positon) {
        ViewGroup childAt = (ViewGroup) getChildAt(0);
        for (int i = 0; i < childAt.getChildCount(); i++) {
            TextView childAt1 = (TextView) childAt.getChildAt(i);
            childAt1.setTextColor(Color.BLACK);
        }
        TextView childAt1 = (TextView) childAt.getChildAt(positon);
        childAt1.setTextColor(Color.RED);
        Toast.makeText(mContext,childAt1.getText().toString(),Toast.LENGTH_SHORT).show();
        mIndex = positon;
        mPosition = positon%4;
        ss = mPosition;
        invalidate();
    }

    public interface textOclickListener{
        public void click(View view,int position);
    }
}
