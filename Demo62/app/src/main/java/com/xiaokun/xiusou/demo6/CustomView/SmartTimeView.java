package com.xiaokun.xiusou.demo6.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Calendar;

import static java.lang.Math.PI;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public class SmartTimeView extends SurfaceView implements SurfaceHolder.Callback, Runnable{

    private SurfaceHolder mHolder;
    private Canvas canvas;

    /**
     * 用于绘制的子线程
     */
    private Thread t;
    /**
     * 线程的控制开关
     */
    private boolean isRunning;
    private int mSize;

    public SmartTimeView(Context context) {
        this(context, null);
    }

    //表盘的背景颜色
    @ColorInt
    private static final int DIAL_BG = 0xFFcfe2f3;
    //表外圆环的颜色
    @ColorInt
    private static final int RING_BG = 0xFFd9d2e9;
    //字体颜色
    @ColorInt
    private static final int TEXT_COLOR = 0xFF141414;
    //时针和分针的颜色
    @ColorInt
    private static final int HOUR_MINUTE_COLOR = 0xFF5B5B5B;
    //秒钟的颜色
    @ColorInt
    private static final int SECOND_COLOR = 0xFFB55050;

    //表盘背景画笔
    private Paint outCirclePaint;
    //最外层圆环
    private Paint outRingPaint;
    //时间文本
    private Paint timeTextPaint;
    //时针,分针,秒针
    private Paint hourPaint, minutePaint, secondPaint;
    //中心圆
    private Paint inCirclePaint, inRedCirclePaint;
    //表盘最小大小
    private static final int MIN_SIZE = 300;

    private static final int HOUR_MINUTE_WIDTH = 30;

    private static final int SECOND_WIDTH = 8;



    //圆环的宽度
    private int ringPaintWidth = 10;

    public SmartTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
        //获取当前时间的实例
        calendar = Calendar.getInstance();

        outCirclePaint = new Paint();
        outCirclePaint.setColor(DIAL_BG);
//        outCirclePaint.setAntiAlias(true);//设置抗锯齿
        outCirclePaint.setStrokeWidth(10);

        outRingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outRingPaint.setColor(RING_BG);
        outRingPaint.setStrokeWidth(dp2px(ringPaintWidth));
        outRingPaint.setStyle(Paint.Style.STROKE);//设置style描边
        outRingPaint.setAntiAlias(true);
        //        添加阴影 0x80000000
        outRingPaint.setShadowLayer(4, 2, 2, 0x80000000);

        timeTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        timeTextPaint.setAntiAlias(true);
        timeTextPaint.setColor(TEXT_COLOR);

        hourPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        hourPaint.setAntiAlias(true);
        hourPaint.setColor(HOUR_MINUTE_COLOR);
        hourPaint.setStrokeWidth(HOUR_MINUTE_WIDTH);
        //设置为圆角
        hourPaint.setStrokeCap(Paint.Cap.ROUND);
        //        添加阴影
        hourPaint.setShadowLayer(4, 0, 0, 0x80000000);

        minutePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        minutePaint.setAntiAlias(true);
        minutePaint.setColor(HOUR_MINUTE_COLOR);
        minutePaint.setStrokeWidth(HOUR_MINUTE_WIDTH);
        //设置为圆角
        minutePaint.setStrokeCap(Paint.Cap.ROUND);
        //        添加阴影
        minutePaint.setShadowLayer(4, 0, 0, 0x80000000);

        secondPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        secondPaint.setAntiAlias(true);
        secondPaint.setColor(SECOND_COLOR);
        secondPaint.setStrokeWidth(SECOND_WIDTH);
        //设置为圆角
        secondPaint.setStrokeCap(Paint.Cap.ROUND);
        //        添加阴影
        secondPaint.setShadowLayer(4, 3, 0, 0x80000000);

        inCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        inCirclePaint.setAntiAlias(true);
        inCirclePaint.setColor(HOUR_MINUTE_COLOR);
        //        添加阴影
        inCirclePaint.setShadowLayer(5, 0, 0, 0x80000000);

        inRedCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        inRedCirclePaint.setAntiAlias(true);
        inRedCirclePaint.setColor(SECOND_COLOR);
        //        添加阴影
        inRedCirclePaint.setShadowLayer(5, 0, 0, 0x80000000);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning = true;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {
            draw();
        }
    }

    private Calendar calendar;

    private void draw() {
        try {
            canvas = mHolder.lockCanvas();
            canvas.drawColor(Color.parseColor("#ffffff"));
            if (canvas != null) {
                //draw something

                calendar = Calendar.getInstance();

                getTime();

                //将画布移到中央,应该是将坐标系的原点移至中心
                mSize = dp2px(300);
                canvas.translate(mSize / 2, mSize / 2);

                drawOutCircle(canvas);

//        drawOutRing(canvas);

                drawTickMarks(canvas);

                drawScale(canvas);

                drawHour(canvas);

                drawMinute(canvas);

                drawInCircle(canvas);
//
                drawSecond(canvas, second * DEGREE);
                drawInRedCircle(canvas);


                //每隔1秒重绘View,重绘会调用onDraw()方法
                postInvalidateDelayed(1000);

            }
        } catch (Exception e) {
        } finally {
            if (canvas != null) {
                mHolder.unlockCanvasAndPost(canvas);
            }
        }

    }

    private static final int DEGREE = 6;

    /**
     * 绘制刻度线
     *
     * @param canvas
     */
    private void drawTickMarks(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setStyle(Paint.Style.FILL);
        canvas.save();
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                paint.setColor(Color.parseColor("#666666"));
                paint.setStrokeWidth(6);
                canvas.drawLine(0, -mSize / 2 + dp2px(8), 0, -mSize / 2 + dp2px(23), paint);
            } else {
                paint.setColor(Color.parseColor("#928987"));
                paint.setStrokeWidth(3);
                canvas.drawLine(0, -mSize / 2 + dp2px(8), 0, -mSize / 2 + dp2px(20), paint);
            }
            canvas.rotate(6);
        }
        canvas.restore();
    }

    private void drawOutCircle(Canvas canvas) {
        canvas.save();
        canvas.drawCircle(0, 0, mSize / 2 - 4, outCirclePaint);
        Log.d("xiao", "表盘半径circle:" + (mSize / 2 - 4));
        canvas.restore();
    }


    /**
     * 画表盘最外层圆环
     *
     * @param canvas 画布
     */
    private void drawOutRing(Canvas canvas) {
        canvas.save();
        float radius = mSize / 2 - dp2px(ringPaintWidth + 6) / 2;
        Log.d("xiao", "外层圆环circle:" + radius);
        RectF rectF = new RectF(-radius, -radius, radius, radius);
        outRingPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(rectF, 0, 360, false, outRingPaint);
        canvas.restore();
    }


    /**
     * 画时间标志
     *
     * @param canvas 画布
     */
    private void drawScale(Canvas canvas) {
        int radius = mSize / 2 - dp2px(ringPaintWidth) / 2;
        // 刻度弧紧靠进度弧
        canvas.save();
        int textSize = 15;
        timeTextPaint.setTextSize(sp2px(textSize));

        float scaleWidth = timeTextPaint.measureText("12");
        canvas.drawText("12", -scaleWidth / 2, -radius + dp2px(23 + 10), timeTextPaint);

        scaleWidth = timeTextPaint.measureText("6");
        canvas.drawText("6", -scaleWidth / 2, radius - dp2px(23), timeTextPaint);

        scaleWidth = timeTextPaint.measureText("3");
        canvas.drawText("3", radius - dp2px(23) - scaleWidth, scaleWidth / 2, timeTextPaint);

        scaleWidth = timeTextPaint.measureText("9");
        canvas.drawText("9", -radius + dp2px(23), scaleWidth / 2, timeTextPaint);

        for (int i = 1; i < 13; i++) {
            if (i == 10 || i == 11 || i == 1) {
                canvas.drawText(i + "", (float) (Math.sin(30 * i * PI / 180) * (radius - dp2px
                                (23)) -
                                dp2px(5)), (float)
                                (-Math.cos(30 * i * PI / 180) * (radius - dp2px(23)) + dp2px(10)),
                        timeTextPaint);
            } else if (i == 5 || i == 7) {
                canvas.drawText(i + "", (float) (Math.sin(30 * i * PI / 180) * (radius - dp2px
                        (23)) - dp2px(5)), (float)
                        (-Math.cos(30 * i * PI / 180) * (radius - dp2px(23))), timeTextPaint);
            }
        }
        canvas.drawText(2 + "", (float) (Math.sin(30 * 2 * PI / 180) * (radius - dp2px
                (23)) - dp2px(7)), (float) (-Math.cos(30 * 2 * PI / 180) * (radius - dp2px(23)) +
                dp2px(5)), timeTextPaint);
        canvas.drawText(4 + "", (float) (Math.sin(30 * 4 * PI / 180) * (radius - dp2px
                (23)) - dp2px(8)), (float)
                (-Math.cos(30 * 4 * PI / 180) * (radius - dp2px(23)) + dp2px(5)), timeTextPaint);

        canvas.drawText(8 + "", (float) (Math.sin(30 * 8 * PI / 180) * (radius - dp2px
                (23)) - dp2px(2)), (float)
                (-Math.cos(30 * 8 * PI / 180) * (radius - dp2px(23)) + dp2px(5)), timeTextPaint);

        canvas.restore();
    }

    /**
     * 画时针
     *
     * @param canvas 画布
     */
    private void drawHour(Canvas canvas) {
        float length = (float) (mSize / 4);
        canvas.save();
        //这里没有算秒钟对时钟的影响
        float degree = hour * 5 * DEGREE + minute / 2f;//一小时30°
        canvas.rotate(degree, 0, 0);
        canvas.drawLine(0, 0, 0, -length, hourPaint);
        canvas.restore();
    }

    /**
     * 画分针
     *
     * @param canvas 画布
     */
    private void drawMinute(Canvas canvas) {
        float length = (float) (mSize / 3);
        canvas.save();
        float degree = minute * DEGREE + second / 10f;//一分钟6°
        canvas.rotate(degree, 0, 0);
        canvas.drawLine(0, 0, 0, -length, minutePaint);
        canvas.restore();
    }

    /**
     * 画中心黑圆
     *
     * @param canvas 画布
     */
    private void drawInCircle(Canvas canvas) {
        int radius = mSize / 20;
        canvas.save();
        canvas.drawCircle(0, 0, radius, inCirclePaint);
        canvas.restore();
    }

    /**
     * 红色中心圆
     *
     * @param canvas 画布
     */
    private void drawInRedCircle(Canvas canvas) {
        int radius = mSize / 40;
        canvas.save();
        canvas.drawCircle(0, 0, radius, inRedCirclePaint);
        canvas.restore();
    }

    /**
     * 画秒针
     *
     * @param canvas 画布
     */
    private void drawSecond(Canvas canvas, float degrees) {
        float length = (float) (mSize / 2);
        canvas.save();
        canvas.rotate(degrees);
        canvas.drawLine(0, length / 5, 0, -length * 6 / 7, secondPaint);
        canvas.restore();

            invalidate();

    }

    private int hour, minute, second;

    /**
     * 获取时分秒
     */
    private void getTime() {
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        System.out.println(hour + ":" + minute + ":" + second);
    }

    /**
     * 将 dp 转换为 px
     *
     * @param dp 需转换数
     * @return 返回转换结果
     */
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources()
                .getDisplayMetrics());
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources()
                .getDisplayMetrics());
    }

}
