/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.ocr.ui.crop;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.baidu.ocr.ui.util.DimensionUtil;

/**
 * 作者:胡涛
 * 日期:2019-7-23
 * 时间:19:01
 * 功能:提示框
 */
public class FrameHintView extends View {

    private int screenWidth, screenHeight;
    private int width, height;

    public FrameHintView(Context context) {
        super(context);
        init();
    }

    public FrameHintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FrameHintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private Paint paint;

    private void init() {
        paint = new Paint();
        //用于防止边缘的锯齿
        paint.setAntiAlias(true);
        //设置颜色
        paint.setColor(Color.WHITE);
        //设置样式为空心矩形
        paint.setStyle(Paint.Style.STROKE);
        //设置空心矩形边框的宽度
        paint.setStrokeWidth(5f);
        //设置透明度
        paint.setAlpha(1000);

        screenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
        screenHeight = getContext().getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, width, height , paint);
    }

    private void drawLine(Canvas canvas, float x, float y, int dx, int dy) {
        canvas.drawLine(x, y, x + dx, y + dy, paint);
    }

}
