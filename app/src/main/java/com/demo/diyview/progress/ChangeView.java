package com.demo.diyview.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.demo.diyview.R;



/**
 * Created by raytine on 2017/7/25.
 */

public class ChangeView extends View {
    private int rectangularColor = getResources().getColor(R.color.burlywood);
    private int triangleColor  = getResources().getColor(R.color.lightskyblue);
    private int cricularColor  = getResources().getColor(R.color.lime);
    private Type type = Type.TRIANGLE;
    private Paint paint;



    public enum Type{
        RECTANGLULAR,
        TRIANGLE,
        CIRCULAR
    }
    private int width ;
    private int height ;
    public ChangeView(Context context) {
        this(context,null);
    }

    public ChangeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ChangeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ChangeView);
        rectangularColor = typedArray.getColor(R.styleable.ChangeView_rectangularColor,rectangularColor);
        triangleColor = typedArray.getColor(R.styleable.ChangeView_triangleColor,triangleColor);
        cricularColor = typedArray.getColor(R.styleable.ChangeView_circularColor,cricularColor);
        typedArray.recycle();
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width =  MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width,height);

    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        if (type == Type.RECTANGLULAR){//画矩形
            setPaintColor(rectangularColor);
            Rect rect = new Rect(0,0,width,height);
            canvas.drawRect(rect,paint);

        }else if(type == Type.TRIANGLE){
            setPaintColor(triangleColor);
            Path path = new Path();
            path.moveTo(getWidth()/2,0);
            path.lineTo(0, (float) (getHeight()/2*Math.sqrt(3)));
            path.lineTo(getWidth(),(float) (getHeight()/2*Math.sqrt(3)));
            path.lineTo(getWidth()/2,0);
            canvas.drawPath(path,paint);
            Log.e("-----view",getWidth() +" :"+width);
        }else{
            setPaintColor(cricularColor);
            canvas.drawCircle(getWidth()/2,getHeight()/2,width/2,paint);
        }
    }
    private  void setPaintColor(int color){
        paint.setColor(color);
    }

    public void setType(Type type) {
        this.type = type;
        invalidate();
    }

}
