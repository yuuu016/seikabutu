package com.example.harotekugame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class HurdleBig extends Hurdle{

    public HurdleBig(float x,float y){
        super(x,y,110,15);
    }

    //落下スピードを上げる

    @Override
    public void update() {
        y += speed * 0.8;//速度は0.8倍
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLUE);
        canvas.drawCircle(x,y,hankei,paint);
    }
}
