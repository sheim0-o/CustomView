package com.example.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleProgressBar extends View {
    public CircleProgressBar(Context context) { super(context); }
    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) { super(context, attrs); }
    public CircleProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }
    private void init(@Nullable AttributeSet attrs){
    }

    private float currentValue = 0;
    private float maxValue = 100;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Integer drkOrange = Color.rgb(242,73,39);
        Integer lightOrange = Color.rgb(255,170,128);
        Integer lightGrey = Color.rgb(90,90,90);
        Integer darkGrey = Color.rgb(40,40,40);

        Paint backCircle = new Paint();
        backCircle.setStyle(Paint.Style.FILL);
        backCircle.setColor(lightGrey);

        Paint progressBar = new Paint();
        progressBar.setStyle(Paint.Style.STROKE);
        progressBar.setStrokeWidth(25F);
        progressBar.setShader(new LinearGradient(0, 400, 400, getHeight(), drkOrange, lightOrange, Shader.TileMode.MIRROR));
        progressBar.setStrokeCap(Paint.Cap.ROUND);

        Paint text = new Paint();
        text.setStyle(Paint.Style.FILL);
        text.setColor(Color.WHITE);
        text.setTextSize(90);

        canvas.drawCircle(getWidth()/2f, getHeight()/2f, getWidth()/2f, backCircle);

        float toAngle = 360f/maxValue*currentValue;
        if(toAngle > 360)
            toAngle %= 360;
        canvas.drawArc(
                35f,
                35f,
                getWidth()-35f,
                getHeight()-35f,
                -90f,
                toAngle,
                false,
                progressBar
        );

        backCircle.setShader(new LinearGradient(0, 100, 400, getHeight(), darkGrey, lightGrey, Shader.TileMode.MIRROR));
        canvas.drawCircle(getWidth()/2f, getHeight()/2f, getWidth()/2.5f, backCircle);

        progressBar.setColor(darkGrey);
        progressBar.setShader(null);
        progressBar.setStrokeWidth(50f);
        canvas.drawArc(
                35f,
                35f,
                getWidth()-35f,
                getHeight()-35f,
                toAngle-90f,
                0.1f,
                false,
                progressBar
        );

        canvas.drawText(currentValue+"°C", getWidth()/2f-(getWidth()/4f), getHeight()/2f, text);
        text.setTextSize(40);
        canvas.drawText("temperature", getWidth()/2f-(getWidth()/5f), getHeight()/2f+50, text);
    }

    void setValue(float value){
        this.currentValue = value;
        invalidate();
    }

    void setMaxValue(float value){
        this.maxValue = value;
    }
}