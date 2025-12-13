package com.example.harotekugame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class HurdleSmall extends Hurdle {

    public HurdleSmall(float x,float y){
        super(x,y,40,15);
    }

    //落下スピードを少し変える


    @Override
    public void update() {
        y += speed * 1.5;
    }

    //見た目の変化
    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        canvas.drawCircle(x,y,hankei,paint);
    }
}