package com.demo.diyview.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.demo.diyview.R;
import com.demo.diyview.view15.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义折线图
 * Created by raytine on 2017/8/15.
 */

public class TestView extends View {
    //y轴的数据
    private String[] ints={"0.0","4.0","8.0","12.0","16.0","20.0","24.0","28.0","32.0","36.0","40.0","44.0"};
    //距离x的距离
    private int indistanceX = 30;
    private int mHeight,mWidth;
    //线的画笔
    private Paint  linePaint,dataPaint;
    //参考区域的画笔
    private Paint  warmPaint;
    //参数点的画笔
    private Paint  pointPaint;
    private Paint  pointLinePaint;
    //参数点
    private List<Bean> beans ;
    private List<Point> points = new ArrayList<>();
    public TestView(Context context) {
        this(context,null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        indistanceX =  dipXpx(indistanceX);
        initPaint();
    }

    private void initPaint() {
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setDither(true);
        linePaint.setStrokeWidth(dipXpx(2));
        linePaint.setColor(getResources().getColor(R.color.gray));
        dataPaint = new Paint();
        dataPaint.setAntiAlias(true);
        dataPaint.setDither(true);
        dataPaint.setTextSize(dipXpx(10));
        dataPaint.setColor(getResources().getColor(R.color.gray));
        warmPaint = new Paint();
        warmPaint.setAntiAlias(true);
        warmPaint.setDither(true);
        warmPaint.setColor(getResources().getColor(R.color.chartreuse));
        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setStrokeWidth(dipXpx(5));
        pointPaint.setDither(true);
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setColor(getResources().getColor(R.color.red));
        pointLinePaint = new Paint();
        pointLinePaint.setAntiAlias(true);
        pointLinePaint.setDither(true);
        pointLinePaint.setStrokeWidth(dipXpx(2));
        pointLinePaint.setStyle(Paint.Style.STROKE);
        pointLinePaint.setColor(getResources().getColor(R.color.red));
    }

    private int dipXpx(int i) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,i,getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画x.y轴
        canvas.drawLine(indistanceX,mHeight-indistanceX,indistanceX,indistanceX,linePaint);
        canvas.drawLine(indistanceX,mHeight-indistanceX,mWidth,mHeight-indistanceX,linePaint);
        //画y轴数据
        int yLenght = mHeight-2*indistanceX;
        int dy = yLenght/ints.length;
        for (int i = 0; i < ints.length; i++) {
            //每个数据的高度
            int pointHeight = (mHeight-indistanceX)-dy*i;
            String text = ints[i];
            Rect bounds = new Rect();
            dataPaint.getTextBounds(text,0,text.length(),bounds);
            Paint.FontMetrics fontMetrics = dataPaint.getFontMetrics();
            int dx = indistanceX/2 - bounds.width()/2;
//            int baselines = (int) ((fontMetrics.bottom-fontMetrics.top)/2+fontMetrics.bottom+pointHeight);
            canvas.drawText(text,dx,pointHeight,dataPaint);
        }
        //画参考区域
        RectF rect = new RectF(indistanceX+linePaint.getStrokeWidth()/2,(mHeight-indistanceX)-(dy*3/2),mWidth,(mHeight-indistanceX)-dy);
        canvas.drawRect(rect ,warmPaint);
        //画日期相对应的点 x轴每个数据间距也采用dy
        int dx =  (int) ((indistanceX+linePaint.getStrokeWidth()/2));

        if (beans != null && beans.size()>0){
            if (points.size()!=0 && points!= null){
                points.clear();
            }
            for (int i = 0; i < beans.size(); i++) {
                Bean bean = beans.get(i);
                String text = bean.getDate();
                int point = (int) bean.getPoint();
                String ptext = point+"";
                Rect bounds = new Rect();
                dataPaint.getTextBounds(text,0,text.length(),bounds);
                Paint.FontMetrics fontMetrics = dataPaint.getFontMetrics();
                int baselines = (int) ((mHeight-indistanceX)+(fontMetrics.bottom-fontMetrics.top)/2+fontMetrics.bottom);
                dx += dy;
                canvas.drawText(text,dx,baselines,dataPaint);
                dx+=bounds.width();
                //画点
                canvas.drawCircle(dx-bounds.width()/2,mHeight-indistanceX-dy/4*point,dipXpx(5),pointPaint);
                //连接点
                Point point1 = new Point(dx-bounds.width()/2,mHeight-indistanceX-dy/4*point,i);
                points.add(point1);
            }
            //将点连起来
            Path path = new Path();
            path.moveTo(points.get(0).getCentX(),points.get(0).getCentY());
            for (int i = 1; i < points.size(); i++) {
                Point point = points.get(i);
                path.lineTo(point.getCentX(),point.getCentY());

            }
            canvas.drawPath(path,pointLinePaint);
        }


    }

    public void addList(List<Bean> been) {
        beans = been;
        invalidate();
    }
}
