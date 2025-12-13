//player
//役割： 自キャラの位置・見た目・当たり判定などを管理する
//プレイヤーは「描画」「移動」「当たり判定」のみ
//ゲーム全体のルール（勝敗やスコア）はGame側で管理する



package com.example.harotekugame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;

//プレイヤー情報を記述する
public class Player {
    public float x;
    public float y;
    public float hankei = 5; //自身の当たり判定の大きさ

    //コンストラクタ
    public  Player(float x,float y,Bitmap bitmap){
        this.x = x;
        this.y = y;
    }

    //移動処理
    public  void moveP(float newX,float newY){
        this.x = newX;
        this.y = newY;
    }

    //当たり判定：計算:Hurdleのほうで当たり判定あるため必要ない
//    public  boolean hits(Hurdle hurdle){
//        float dx = x - hurdle.x;
//        float dy = y - hurdle.y;
//        float distance = (float) Math.sqrt(dx*dx + dy*dy);
//        return  distance < (hankei + hurdle.hankei);
//    }

}
