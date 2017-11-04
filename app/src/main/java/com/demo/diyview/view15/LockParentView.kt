package com.demo.diyview.view15

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

/**9宫格自定义控件
 * Created by raytine on 2017/8/8.
 */
class LockParentView : View{
    private var callBack :CallBack? = null
    // 二维数组初始化，int[3][3]
    private var mPoints: Array<Array<Point?>> = Array(3) { Array<Point?>(3, { null }) }
    // 画笔
    private var mLinePaint: Paint? = null
    private var mPressedPaint: Paint? = null
    private var mErrorPaint: Paint? = null
    private var mNormalPaint: Paint? = null
    private var mArrowPaint: Paint? = null
    // 颜色
    private val mOuterPressedColor = 0xff8cbad8.toInt()
    private val mInnerPressedColor = 0xff0596f6.toInt()
    private val mOuterNormalColor = 0xffd9d9d9.toInt()
    private val mInnerNormalColor = 0xff929292.toInt()
    private val mOuterErrorColor = 0xff901032.toInt()
    private val mInnerErrorColor = 0xffea0945.toInt()
    private var mIsInit = false;
    private var mIsTouchPoint = false;
    private var mSelectPoints = ArrayList<Point>()
    //外圆的半径
    private  var  mDotRadius = 0;
    constructor(context: Context):super(context)
    constructor(context: Context,attrs: AttributeSet):super(context,attrs)
    constructor(context: Context,attrs: AttributeSet,defStyleAttr : Int):super(context,attrs,defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        if (!mIsInit){
            initDot();
            initPaint();
            mIsInit = true;
        }
        drawShow(canvas)
    }
    var mMovingX:Float = 0f
    var mMovingY:Float = 0f

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mMovingX = event!!.x
        mMovingY = event.y
        when(event!!.action){
            MotionEvent.ACTION_DOWN ->{
                var point = point
                if (point != null){
                    mIsTouchPoint = true
                    mSelectPoints.add(point)
                    // 点设置为已经选中
                    point.setStatusPressed()
                }

            }
            MotionEvent.ACTION_MOVE ->{
                if (mIsTouchPoint){
                    //按下的时候一定在一个点上
                    var point = point
                    if (point != null){
                        if (!mSelectPoints.contains(point)){
                            mSelectPoints.add(point)
                            // 点设置为已经选中
                            point.setStatusPressed()
                        }
                    }
                }
            }
            MotionEvent.ACTION_UP ->{
                mIsTouchPoint = false
                var password = "";
                for (point in mSelectPoints){
                    password+=point.index
                }
                callBack!!.callback(password)
            }
        }
        invalidate()
        return true
    }
   public fun setCallBack(callBack:CallBack){

        this.callBack = callBack
    }
    public fun clearPoint(){
        for (point in mSelectPoints){
            point.setStatusNormal()
        }
        mSelectPoints.clear()
        invalidate()
    }
    public fun showError(){
        for (selectPoint in mSelectPoints) {
            selectPoint.setStatusError()

        }

        postDelayed({
            clearPoint()
            invalidate()
        }, 1000)
    }
    /**
     * 获取按下的点
     * @return 当前按下的点
     */
    private val point: Point?
        get() {
            for (i in mPoints.indices) {
                for (j in 0..mPoints[i].size - 1) {
                    val point = mPoints[i][j]
                    if (point != null) {
                        if (MathUtil.checkInRound(point.centerX.toFloat(), point.centerY.toFloat(), mDotRadius.toFloat(), mMovingX, mMovingY)) {
                            return point
                        }
                    }
                }
            }
            return null
        }
    /**
     * 绘制九宫格
     */
    private fun drawShow(canvas: Canvas?) {
        for (i in 0..2){
            for (point in mPoints[i]){
                if (point!!.getStatusNormal()){
                    mNormalPaint!!.color = mOuterNormalColor
                    canvas!!.drawCircle(point!!.centerX.toFloat(),point.centerY.toFloat(), mDotRadius.toFloat(),mNormalPaint)
                    mNormalPaint!!.color = mInnerNormalColor
                    canvas!!.drawCircle(point!!.centerX.toFloat(),point.centerY.toFloat(), mDotRadius/6.toFloat(),mNormalPaint)
                }
                if (point!!.getStatusPressed()){
                    mPressedPaint!!.color = mOuterPressedColor
                    canvas!!.drawCircle(point!!.centerX.toFloat(),point.centerY.toFloat(), mDotRadius.toFloat(),mPressedPaint)
                    mPressedPaint!!.color = mInnerPressedColor
                    canvas!!.drawCircle(point!!.centerX.toFloat(),point.centerY.toFloat(), mDotRadius/6.toFloat(),mPressedPaint)
                }
                if (point!!.getStatusError()){
                    mErrorPaint!!.color = mOuterErrorColor
                    canvas!!.drawCircle(point!!.centerX.toFloat(),point.centerY.toFloat(), mDotRadius.toFloat(),mErrorPaint)
                    mErrorPaint!!.color = mInnerErrorColor
                    canvas!!.drawCircle(point!!.centerX.toFloat(),point.centerY.toFloat(), mDotRadius/6.toFloat(),mErrorPaint)
                }
            }
        }
        drawLine(canvas)
    }

    private fun drawLine(canvas: Canvas?) {
        if (mSelectPoints.size >= 1){
            //连2点之间的线和箭头
            var  lastPoint = mSelectPoints[0]
            for (index in 1..mSelectPoints.size-1){
                drawLine(lastPoint,mSelectPoints[index], canvas!!, mLinePaint!!)
                drawArrow(canvas, mArrowPaint!!,lastPoint,mSelectPoints[index],(mDotRadius/4).toFloat(),38)
                lastPoint = mSelectPoints[index]
            }
           var isInnerPoint =  MathUtil.checkInRound(lastPoint.centerX.toFloat(), lastPoint.centerY.toFloat(), mDotRadius.toFloat()/6, mMovingX, mMovingY)
            if (!isInnerPoint && mIsTouchPoint){
            drawLine(lastPoint, Point(mMovingX.toInt(),mMovingY.toInt(),-1), canvas!!, mLinePaint!!)}
        }

    }

    /**
     * 画线
     */
    private fun drawLine(start: Point, end: Point, canvas: Canvas, paint: Paint) {
        val d = MathUtil.distance(start.centerX.toDouble(), start.centerY.toDouble(), end.centerX.toDouble(), end.centerY.toDouble())
        var dx = (end.centerX - start.centerX)
        var dy = (end.centerY - start.centerY)
        var mRadius= (mDotRadius/ 6.0)
        val rx = (dx /d*mRadius).toFloat()
        val ry = (dy /d*mRadius).toFloat()
        canvas.drawLine(start.centerX + rx, start.centerY + ry, end.centerX - rx, end.centerY - ry, paint)
    }
    /**
     * 画箭头
     */
    private fun drawArrow(canvas: Canvas, paint: Paint, start: Point, end: Point, arrowHeight: Float, angle: Int) {
        val d = MathUtil.distance(start.centerX.toDouble(), start.centerY.toDouble(), end.centerX.toDouble(), end.centerY.toDouble())
        val sin_B = ((end.centerX - start.centerX) / d).toFloat()
        val cos_B = ((end.centerY - start.centerY) / d).toFloat()
        val tan_A = Math.tan(Math.toRadians(angle.toDouble())).toFloat()
        val h = (d - arrowHeight.toDouble() - mDotRadius * 1.1).toFloat()
        val l = arrowHeight * tan_A
        val a = l * sin_B
        val b = l * cos_B
        val x0 = h * sin_B
        val y0 = h * cos_B
        val x1 = start.centerX + (h + arrowHeight) * sin_B
        val y1 = start.centerY + (h + arrowHeight) * cos_B
        val x2 = start.centerX + x0 - b
        val y2 = start.centerY.toFloat() + y0 + a
        val x3 = start.centerX.toFloat() + x0 + b
        val y3 = start.centerY + y0 - a
        val path = Path()
        path.moveTo(x1, y1)
        path.lineTo(x2, y2)
        path.lineTo(x3, y3)
        path.close()
        canvas.drawPath(path, paint)
    }


    /**
     * 3种状态的画笔,线的画笔,箭头画笔
     */
    private fun initPaint() {
        // 线的画笔
        mLinePaint = Paint()
        mLinePaint!!.color = mInnerPressedColor
        mLinePaint!!.style = Paint.Style.STROKE
        mLinePaint!!.isAntiAlias = true
        mLinePaint!!.strokeWidth = (mDotRadius / 9).toFloat()
        // 按下的画笔
        mPressedPaint = Paint()
        mPressedPaint!!.style = Paint.Style.STROKE
        mPressedPaint!!.isAntiAlias = true
        mPressedPaint!!.strokeWidth = (mDotRadius / 6).toFloat()
        // 错误的画笔
        mErrorPaint = Paint()
        mErrorPaint!!.style = Paint.Style.STROKE
        mErrorPaint!!.isAntiAlias = true
        mErrorPaint!!.strokeWidth = (mDotRadius / 6).toFloat()
        // 默认的画笔
        mNormalPaint = Paint()
        mNormalPaint!!.style = Paint.Style.STROKE
        mNormalPaint!!.isAntiAlias = true
        mNormalPaint!!.strokeWidth = (mDotRadius / 9).toFloat()
        // 箭头的画笔
        mArrowPaint = Paint()
        mArrowPaint!!.color = mInnerPressedColor
        mArrowPaint!!.style = Paint.Style.FILL
        mArrowPaint!!.isAntiAlias = true
    }

    private fun initDot() {
        var width  = this.width
        var height = this.height
        var ofsetX = 0 ;
        var ofsetY  = 0;
        //兼容横竖屏
        if (width <height){
            //竖屏
            ofsetY = (height -width)/2
            height = width;
        }else{
            //横屏
            ofsetX = (width - height)/2
            width = height
        }
        var squareWidth = width/3
        //外圆的大小
        mDotRadius = width/12

        mPoints[0][0] = LockParentView.Point(ofsetX+squareWidth/2,ofsetY+squareWidth/2,0);
        mPoints[0][1] = LockParentView.Point(ofsetX+squareWidth*3/2,ofsetY+squareWidth/2,1);
        mPoints[0][2] = LockParentView.Point(ofsetX+squareWidth*5/2,ofsetY+squareWidth/2,2);
        mPoints[1][0] = LockParentView.Point(ofsetX+squareWidth/2,ofsetY+squareWidth*3/2,3);
        mPoints[1][1] = LockParentView.Point(ofsetX+squareWidth*3/2,ofsetY+squareWidth*3/2,4);
        mPoints[1][2] = LockParentView.Point(ofsetX+squareWidth*5/2,ofsetY+squareWidth*3/2,5);
        mPoints[2][0] = LockParentView.Point(ofsetX+squareWidth/2,ofsetY+squareWidth*5/2,6);
        mPoints[2][1] = LockParentView.Point(ofsetX+squareWidth*3/2,ofsetY+squareWidth*5/2,7);
        mPoints[2][2] = LockParentView.Point(ofsetX+squareWidth*5/2,ofsetY+squareWidth*5/2,8);
    }
    class Point(var centerX:Int,var centerY:Int,var index:Int){
        private val STATUS_MORMAL = 1;
        private val STATUS_PRESSED = 2;
        private val STATUS_ERROE = 3

        private var status = STATUS_MORMAL

        fun setStatusNormal() {
            status = STATUS_MORMAL
        }
        fun setStatusPressed() {
            status = STATUS_PRESSED
        }
        fun setStatusError() {
            status = STATUS_ERROE
        }
        fun getStatusNormal():Boolean{
            return status == STATUS_MORMAL
        }
        fun getStatusPressed():Boolean{
            return status == STATUS_PRESSED
        }
        fun getStatusError():Boolean{
            return status == STATUS_ERROE
        }
    }
   public interface CallBack{
      fun callback(password:String)
    }
}