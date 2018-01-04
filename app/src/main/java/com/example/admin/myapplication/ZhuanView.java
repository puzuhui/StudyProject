package com.example.admin.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Admin on 2018/1/3.
 * 作者：蒲祖辉
 * 电话：15823903420
 * 公司：铭轩科技
 */

public class ZhuanView extends View {
    Paint paint,paint1,tpaint;
    Canvas canvas;

    public ZhuanView(Context context) {
        super(context);
    }

    public ZhuanView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);//设置抗锯齿
        paint.setDither(true);//设置防抖动
        paint.setStrokeWidth(5);//设置线条粗细
        paint.setStyle(Paint.Style.STROKE);

        paint1 = new Paint();
        paint1.setColor(Color.RED);
        paint1.setAntiAlias(true);//设置抗锯齿
        paint1.setDither(true);//设置防抖动
        paint1.setStrokeWidth(5);//设置线条粗细

        tpaint = new Paint();
        tpaint.setColor(Color.BLACK);
        tpaint.setStyle(Paint.Style.FILL);
        tpaint.setAntiAlias(true);//设置抗锯齿
        tpaint.setDither(true);//设置防抖动
        tpaint.setStrokeWidth(1);//设置线条粗细
        tpaint.setTextSize(dp2px(16));
    }

    public ZhuanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    /**
     *  UNSPECIFIED	父容器没有对当前View有任何限制，当前View可以任意取尺寸
        EXACTLY	当前的尺寸就是当前View应该取的尺寸
        AT_MOST	当前尺寸是当前View能取的最大尺寸
     *
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);//测量模式
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);//测量尺寸
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);

        width = widthsize / 2 ;
        hight = heightsize / 2 ;
    }

    int width,hight;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //canvas.drawText("中国",width, hight+15,tpaint);
        canvas.drawLine(width, hight, width, hight - width, paint);
        canvas.drawLine(width, hight, width, hight + width, paint);

        //绘制刻度
        for (int i = 0; i < 60; i++) {
            int line = 0;
            if(i % 5 == 0){
                line = 30;
                paint.setColor(Color.BLACK);
            }else {
                line = 20;
                paint.setColor(Color.RED);
            }

            canvas.drawLine(width, hight+width-line, width, hight + width, paint);
            canvas.rotate(6,width, hight);//旋转
        }
        canvas.restore();

        //绘制数字
        for (int i = 0; i < 60; i++) {
            int line = 0;
            if(i % 5 == 0){
                String text = ((i / 5)  == 0 ? 12 : (i / 5) )+"";
                Rect rect = new Rect();
                tpaint.getTextBounds(text,0,text.length(),rect);

                line = 50;
                paint.setColor(Color.BLACK);
                canvas.drawText(text,width, hight- width +line,tpaint);
                canvas.rotate(30,width, hight);//旋转
            }
        }
        canvas.restore();

        canvas.drawCircle(width,hight,width,paint);
        canvas.drawCircle(width,hight,10,paint1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    //将dp转化为px
    public int dp2px(int dp){
        //获取手机密度
        float density = getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);//实现四舍五入
    }

}
