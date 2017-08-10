package com.demo.diyview.view15;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raytine on 2017/8/9.
 */

public class LockParentViews extends View {
    // 画笔
    private Paint mLinePaint;
    private Paint mPressedPaint;
    private Paint mErrorPaint;
    private Paint mNormalPaint;
    private Paint mArrowPaint;
    // 颜色
    private int mOuterPressedColor = 0xff8cbad8;
    private int mInnerPressedColor = 0xff0596f6;
    private int mOuterNormalColor = 0xffd9d9d9;
    private int mInnerNormalColor = 0xff929292;
    private int mOuterErrorColor = 0xff901032;
    private int mInnerErrorColor = 0xffea0945;

    private boolean isInit = false;
    //是否点中一个点
    private boolean isTouchPoint =false;
    private List<Point> mSelects = new ArrayList<>();
    private int mDotRadius = 0;
    private Point[][] arrays = new Point[3][3];

    private float mMoveingX ;
    private float mMoveingY ;
    private Object pressedPoint;
    private CallBack callBack;
    public LockParentViews(Context context) {
        this(context,null);
    }

    public LockParentViews(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LockParentViews(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInit){
            initDot();
            initPaint();
            isInit = true;
        }
        drawShow(canvas);
    }

    private void initDot() {
        int width = getWidth();
        int height = getHeight();
        int ofsetX = 0;
        int ofsetY = 0;
        //兼容横竖屏
        if (width<height){
            ofsetY = (height-width)/2;
            height = width;
        }else{
            ofsetX = (width-height)/2;
            width = height;
        }
        int squareWidth = width/3;
        //外圆的大小
        mDotRadius = width/12;
        arrays[0][0] = new Point(ofsetX+squareWidth/2,ofsetY+squareWidth/2,0);
        arrays[0][1] = new Point(ofsetX+squareWidth*3/2,ofsetY+squareWidth/2,1);
        arrays[0][2] = new Point(ofsetX+squareWidth*5/2,ofsetY+squareWidth/2,2);
        arrays[1][0] = new Point(ofsetX+squareWidth/2,ofsetY+squareWidth*3/2,3);
        arrays[1][1] = new Point(ofsetX+squareWidth*3/2,ofsetY+squareWidth*3/2,4);
        arrays[1][2] = new Point(ofsetX+squareWidth*5/2,ofsetY+squareWidth*3/2,5);
        arrays[2][0] = new Point(ofsetX+squareWidth/2,ofsetY+squareWidth*5/2,6);
        arrays[2][1] = new Point(ofsetX+squareWidth*3/2,ofsetY+squareWidth*5/2,7);
        arrays[2][2] = new Point(ofsetX+squareWidth*5/2,ofsetY+squareWidth*5/2,8);
    }

    private void initPaint() {
        // 线的画笔
        mLinePaint =new Paint();
        mLinePaint.setColor(mInnerPressedColor);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(mDotRadius/9);
        // 按下的画笔
        mPressedPaint =new Paint();
        mPressedPaint.setStyle(Paint.Style.STROKE);
        mPressedPaint.setAntiAlias(true);
        mPressedPaint.setStrokeWidth(mDotRadius/9);
        // 错误的画笔;
        mErrorPaint =new Paint();
        mErrorPaint.setStyle(Paint.Style.STROKE);
        mErrorPaint.setAntiAlias(true);
        mErrorPaint.setStrokeWidth(mDotRadius/9);
        // 默认的画笔
        mNormalPaint =new Paint();
        mNormalPaint.setStyle(Paint.Style.STROKE);
        mNormalPaint.setAntiAlias(true);
        mNormalPaint.setStrokeWidth(mDotRadius/9);
        // 箭头的画笔
        mArrowPaint =new Paint();
        mArrowPaint.setColor(mInnerPressedColor);
        mArrowPaint.setStyle(Paint.Style.FILL);
        mArrowPaint.setAntiAlias(true);
    }

    private void drawShow(Canvas canvas) {
        for (int i= 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {
                Point point = arrays[i][j];
                if (point.getSTATUS_MORMAL()){
                    mNormalPaint.setColor(mOuterNormalColor);
                    canvas.drawCircle(point.getCentX(),point.getCentY(),mDotRadius,mNormalPaint);
                    mNormalPaint.setColor(mInnerNormalColor);
                    canvas.drawCircle(point.getCentX(),point.getCentY(),mDotRadius/6,mNormalPaint);
                }
                if (point.getSTATUS_PRESSED()){
                    mNormalPaint.setColor(mOuterPressedColor);
                    canvas.drawCircle(point.getCentX(),point.getCentY(),mDotRadius,mNormalPaint);
                    mNormalPaint.setColor(mInnerPressedColor);
                    canvas.drawCircle(point.getCentX(),point.getCentY(),mDotRadius/6,mNormalPaint);
                }
                if (point.getSTATUS_ERROE()){
                    mNormalPaint.setColor(mOuterErrorColor);
                    canvas.drawCircle(point.getCentX(),point.getCentY(),mDotRadius,mNormalPaint);
                    mNormalPaint.setColor(mInnerErrorColor);
                    canvas.drawCircle(point.getCentX(),point.getCentY(),mDotRadius/6,mNormalPaint);
                }
            }
        }
        drawLine(canvas);
    }

    /**
     * 画线
     * @param canvas
     */
    private void drawLine(Canvas canvas) {
        if (mSelects.size()>1){
            Point lastPoint = mSelects.get(0);
            for (int i = 1; i <mSelects.size() ; i++) {
                Point point = mSelects.get(i);
                drawLine(lastPoint,point,canvas,mLinePaint);
                drawArrow(canvas,mArrowPaint,lastPoint,point, (float)
                        (mDotRadius/4),38);
                lastPoint = point;
            }
            boolean b = MathUtil.checkInRound(lastPoint.getCentX(), lastPoint.getCentY(),mDotRadius,mMoveingX, mMoveingY);

            if (!b && isTouchPoint){
                drawLine(lastPoint,new Point((int)mMoveingX,(int)mMoveingY,-1),canvas,mLinePaint);
            }
        }
    }

    private void drawLine(Point lastPoint, Point point, Canvas canvas, Paint mLinePaint) {
        canvas.drawLine(lastPoint.getCentX(),lastPoint.getCentY(),point.getCentX(),point.getCentY(),mLinePaint);
    }
    /**
     * 画箭头
     * arrowHeight 为箭头在线上长度
     */
    private void drawArrow(Canvas canvas,Paint paint,Point start,Point end,Float arrowHeight,int angle) {
        int d = MathUtil.distance((double) start.getCentX(), (double)start.getCentY(), (double)end.getCentX(), (double)end.getCentY());
        float sin_B =  ((float)(end.getCentX() - start.getCentX()) / d);
        float cos_B =  ((float)(end.getCentY() - start.getCentY()) / d);
        float tan_A = (float) Math.tan(Math.toRadians((double)angle));
        float h = (float) (d - (double)arrowHeight - mDotRadius * 1.1);
        float l = arrowHeight * tan_A;
        float a = l * sin_B;
        float b = l * cos_B;
        float x0 = h * sin_B;
        float y0 = h * cos_B;
        float x1 = start.getCentX() + (h + arrowHeight) * sin_B;
        float y1 = start.getCentY() + (h + arrowHeight) * cos_B;
        float x2 = start.getCentX() + x0 - b;
        float y2 = start.getCentY() + y0 + a;
        float x3 = start.getCentX() + x0 + b;
        float y3 = start.getCentY() + y0 - a;
        Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.close();
        canvas.drawPath(path, paint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mMoveingX = event.getX();
        mMoveingY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Point pressedPoint = getPressedPoint();
                if (pressedPoint !=null){
                    pressedPoint.setSTATUS_PRESSED();
                    isTouchPoint = true;
                    mSelects.add(pressedPoint);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isTouchPoint){
                    Point pressedPoints = getPressedPoint();
                    if (pressedPoints !=null){
                      if (!mSelects.contains(pressedPoints)){
                          pressedPoints.setSTATUS_PRESSED();
                          isTouchPoint = true;
                          mSelects.add(pressedPoints);
                      }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                isTouchPoint = false;
                String password = null;
                for (int i = 0; i < mSelects.size(); i++) {
                    password+=mSelects.get(i).getIndex();
                }
                callBack.callback(password);
                break;
        }
        invalidate();
        return true;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public Point getPressedPoint() {
        for (int i= 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {
                Point point = arrays[i][j];
                if (MathUtil.checkInRound(point.getCentX(),point.getCentY(),mDotRadius,mMoveingX,mMoveingY)){
                    return point;
                }

            }
        }
        return null;
    }

    /**
     * 清除点
     */
    public void clearPoint() {
        for (int i = 0; i < mSelects.size(); i++) {
            Point point = mSelects.get(i);
            point.setSTATUS_MORMAL();
        }
        mSelects.clear();
        invalidate();
    }

    /**
     * 显示错误点
     */
    public void showError() {
        for (int i = 0; i < mSelects.size(); i++) {
            Point point = mSelects.get(i);
            point.setSTATUS_ERROE();
        }
        invalidate();
        postDelayed(new Runnable() {
            @Override
            public void run() {
                clearPoint();
            }
        },3000);
    }

    public interface CallBack{
        public void callback(String password);
    }
}
