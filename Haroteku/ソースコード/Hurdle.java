package com.example.harotekugame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Hurdle {
    public float x, y;
    public float hankei;//当たり判定用の変数
    public float speed;//落下速度

    //コンストラクタ
    public Hurdle(float x, float y, float hankei,float speed) {
        this.x = x;
        this.y = y;
        this.hankei = hankei;
        this.speed = speed;
    }

    //下に落ちる処理
    public void update() {
        y = y + speed;
    }

    //弾そのものの描写
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.RED);//弾の色（画像に変えたいところだが）
        canvas.drawCircle(x, y, hankei, paint);
    }

    //当たり判定
    public boolean collidesWith(Player player) {
        float dx = x - player.x;
        float dy = y - player.y;
        double distance = Math.sqrt(dx*dx + dy*dy);
        return distance < (hankei + player.hankei);
    }

    //画面外の処理
    public  boolean isOff(int outh){
        return  y - hankei > outh;
    }
}

