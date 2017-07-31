package com.demo.diyview.view23;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * 贝塞尔曲线
 * Created by 123 on 2017/7/30.
 */

public class MessageBubbleView extends View {
    //p1拖拽圆
    private Paint mPaint1;
    private  int tagRaduis = 10;
    //p2固定圆
    private PointF p1,p2;
    private int distanceMax =  7;
    private int gudingRadius;
    private int distanceMin  = 3;
    private double distance ;
    private Object berserPath;

    public MessageBubbleView(Context context) {
        this(context,null);
    }

    public MessageBubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MessageBubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        tagRaduis =  dip2px(tagRaduis);
        distanceMax =  dip2px(distanceMax);
        distanceMin =  dip2px(distanceMin);
        mPaint1 =new Paint();
        mPaint1.setAntiAlias(true);
        mPaint1.setColor(Color.BLUE);
        mPaint1.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (p1 == null && p2 == null) {
            return;
        }
        canvas.drawCircle(p1.x, p1.y, tagRaduis, mPaint1);

        Path path = getBerserPath();
        if (path !=null) {
            canvas.drawCircle(p2.x, p2.y, gudingRadius, mPaint1);
            canvas.drawPath(path,mPaint1);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float dx1 = event.getX();
                float dy1 = event.getY();
                initPoint(dx1, dy1);
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = event.getX();
                float dy = event.getY();
                upPoint(dx, dy);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }
    //拖拽的原
    private void upPoint(float dx, float dy) {
        p1.x = dx;
        p1.y = dy;
    }

    private void initPoint(float dx1, float dy1) {
        p1 = new PointF(dx1,dy1);
        p2 = new PointF(dx1,dy1);
    }


    private int dip2px(int dip){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dip,getResources().getDisplayMetrics());
    }

    public double getdistance() {
        return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
    }

    /**
     * 求贝塞尔曲线
     * @return
     */
    public Path getBerserPath() {
        distance = getdistance();
        gudingRadius = (int) (distanceMax - distance / 14);
        if (gudingRadius < distanceMin){
            return null;
        }
        Path path = new Path();
        double tanA = Math.atan((p1.y-p2.y)/(p1.x-p2.x));
        PointF px0 = new PointF((float) (p2.x+gudingRadius*Math.sin(tanA)),p2.y-(float) (gudingRadius*Math.cos(tanA)));
        PointF px1 = new PointF((float) (p1.x+tagRaduis*Math.sin(tanA)),p1.y-(float) (tagRaduis*Math.cos(tanA)));
        PointF px2 = new PointF((float) (p1.x-tagRaduis*Math.sin(tanA)),p1.y+(float) (tagRaduis*Math.cos(tanA)));
        PointF px3 = new PointF((float) (p2.x-gudingRadius*Math.sin(tanA)),p2.y+(float) (gudingRadius*Math.cos(tanA)));
        path.moveTo(px0.x,px0.y);
        PointF pointF = getcontrolPoint();
        path.quadTo(pointF.x,pointF.y,px1.x,px1.y);
        path.lineTo(px2.x,px2.y);
        path.quadTo(pointF.x,pointF.y,px3.x,px3.y);
        path.close();
        return path;
    }

    public PointF getcontrolPoint() {

        return new PointF((p1.x+p2.x)/2,(p1.y+p2.y)/2);
    }
}
