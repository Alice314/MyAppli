package com.example.fg.myappli;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fg on 2016/8/5.
 */

public class CircleView extends View {
    
    private Paint mOuterLinePaint;//饼状图外圈线条画笔
    private Paint mPiePaint;//饼状图画笔
    private Paint mInnerPiant;//内圆画笔

    private final float R = Utils.dp2px(getContext(),100);//饼状图半径
    private final float FiveDegree = 5;//这是写死了的
    private final float r = (float) (R * Math.tan(Math.toRadians(FiveDegree))
            / (1 + Math.tan(Math.toRadians(FiveDegree))));//大圆内小圆的半径
    private final float START_ANGLE = 30;
    private final float OUTER_LINE_WIDTH = 3;
    private RectF rectF ;
    private RectF rr;
    private List<Integer>mColorList;
    private float[]mPieSweep;
    private float xLeft;
    private float yLeft;
    private float xRight;
    private float yRight;
    private double[]data;
    private String[]descriptions;


    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mOuterLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOuterLinePaint.setColor(Color.GRAY);
        mOuterLinePaint.setStyle(Paint.Style.STROKE);
        mOuterLinePaint.setStrokeWidth(OUTER_LINE_WIDTH);
        
         mPiePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
         mPiePaint.setStyle(Paint.Style.FILL);

        mInnerPiant = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerPiant.setColor(Color.WHITE);
        mInnerPiant.setStyle(Paint.Style.FILL);

        mColorList = new ArrayList<>();
        mColorList.add(com.example.fg.myappli.R.color.abc_color_highlight_material);
        mColorList.add(com.example.fg.myappli.R.color.abc_input_method_navigation_guard);
        mColorList.add(com.example.fg.myappli.R.color.abc_tint_btn_checkable);

        mPieSweep = new float[]{30,120,45,165};
    }

    public void setData(double[] data) {
        this.data = data;
        for (int i = 0; i < data.length;i++){
            mPieSweep[i]= (float) (( data[i]-5)*3.6);
            Log.d("TAG",data[i] + "");
        }
        invalidate();
    }

   /* public void setDescriptions(String[] descriptions) {
        this.descriptions = descriptions;
        invalidate();
    }*/
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(200,200);
        }else if (widthSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(200,heightSpecSize);
        }else if (heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,200);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float pieStart = START_ANGLE;
        int i;
        for (i = 0;i < 3;i++){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mPiePaint.setColor(getResources().getColor(mColorList.get(i),null));
            }
            float pieSweep = mPieSweep[i];
            initLittleCircle(pieSweep,pieStart);
            if (rectF == null){
                rectF = new RectF(getMeasuredWidth() / 2 - R, getMeasuredHeight() / 2 - R,
                        getMeasuredWidth() / 2 + R, getMeasuredHeight() / 2 + R);
            }
            canvas.drawArc(rectF,pieStart,pieSweep,true,mOuterLinePaint);
            canvas.drawArc(rectF, pieStart, pieSweep, true, mPiePaint);
                drawSmallRect(mPiePaint,canvas,i * 120);

            canvas.drawCircle(xLeft, yLeft, r, mPiePaint);
            canvas.drawCircle(xRight,yRight,r,mPiePaint);
            if (rr == null){
                rr = new RectF(getMeasuredWidth() / 2 - R + r, getMeasuredHeight() / 2 - R + r,
                        getMeasuredWidth() / 2 + R - r, getMeasuredHeight() / 2 + R - r);
            }
            canvas.drawArc(rr, pieSweep + pieStart, FiveDegree, true, mPiePaint);
            canvas.drawArc(rr,pieStart-5,FiveDegree,true,mPiePaint);
            pieStart = pieSweep + 13 + pieStart;
        }
        drawInnerCircle(canvas);
    }

    private void drawSmallRect(Paint mPiePaint,Canvas canvas,int distance) {
        RectF details  = null;
        if (details == null){
            details = new RectF(getMeasuredWidth() / 2 +distance, 3*getMeasuredHeight() / 4,
                    getMeasuredWidth() / 2 + 100 +distance, 3*getMeasuredHeight() / 4 + 80);
        }
        canvas.drawRect(details,mPiePaint);

    }

    private void drawInnerCircle(Canvas canvas) {
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,Utils.dp2px(getContext(),40),mInnerPiant);

    }

    public void initLittleCircle(float pieSweep,float pieStart){
        float little_circle_coordinate_left = 360 - (pieSweep + pieStart);//左边小圆坐标的角度
        float little_circle_coordinate_right = (little_circle_coordinate_left + pieSweep);//右边小圆坐标的角度
        xLeft = (float) (getMeasuredWidth() / 2 + (R - r) * Math.cos(Math.toRadians(little_circle_coordinate_left)));
        xRight = (float) (getMeasuredWidth() / 2 + (R - r) * Math.cos(Math.toRadians(little_circle_coordinate_right)));

        yLeft = (float) (getMeasuredHeight() / 2 - (R - r) * Math.sin(Math.toRadians(little_circle_coordinate_left)));
        yRight = (float) (getMeasuredHeight() / 2 - (R - r) * Math.sin(Math.toRadians(little_circle_coordinate_right)));
    }
}


