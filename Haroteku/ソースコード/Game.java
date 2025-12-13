package com.example.harotekugame;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.Switch;


import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Game extends View {

    //変数作成:表示系
    private Paint paint = new Paint();
    private Paint txtTime = new Paint();

    //変数作成：プレイヤーと弾幕
    private Player player;
    private int hp;
    private ArrayList<Hurdle> hurdles = new ArrayList<>();
    private Random random = new Random();
    private long hurdleTime = 0;//弾幕生成タイマー
    private int hurdleType = 0;//弾の種類わけ（ステージごと)

    //変数作成：その他ゲームの処理用
    private long starttime = 0;//ゲーム開始時間
    private boolean isclear = false;//クリア判定
    private int hit = 0;//被弾数カウンター
    private int clearTimeSec = 0;
    private boolean isLoop = true;//ループ処理用


    //クリアか失敗の判定を送る処理
    public OnClearA onCleara;//自作データ型：アクテビティにクリア判定する
    public OnMissA onMissa;//自作データ型：アクテビティに失敗判定する

    //成功
    public interface OnClearA {
        void onClear();
    }
    public void setOnClearA(OnClearA listener) {
        this.onCleara = listener;
    }

    //失敗
    public interface OnMissA {
        void onMiss();
    }
    public void setOnMissA(OnMissA listener2) {
        this.onMissa = listener2;
    }


    //コンストラクタ
    //引数はcontextのほか:クリア時間、弾幕のタイプ（両方ともint）
    public Game(Context context, int clearTimeSec, int hurdleType) {
        super(context);
        this.clearTimeSec = clearTimeSec;
        this.hurdleType = hurdleType;
        player = new Player(500, 500, null);//ここ見直すこと

        //時間の処理と表示：初期化など
        starttime = System.currentTimeMillis();
        hurdleTime = starttime;
        txtTime.setColor(Color.BLACK);
        txtTime.setTextSize(60);
    }

    //シークバーの場所がプレイヤーの場所になるようにする
    public void setPlayertoSeekber(int progress, int seekBerHeight) {
        float x = getWidth() * (progress / 100f);
        float y = getHeight() - seekBerHeight - player.hankei - 20;
        player.moveP(x, y);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        long now = System.currentTimeMillis();//現在時刻を入れる

        //弾の生成
        if (now - hurdleTime > 500) {
            float startX = random.nextInt(getWidth());
            float startX2 = random.nextInt(getWidth());
            float startX3 = random.nextInt(getWidth());

            //弾幕セレクト：ステージによって弾幕を使い分ける
            switch (hurdleType) {
                case 1://ステージ1
                    hurdles.add(new Hurdle(startX, 0, 60, 15));
                    break;
                case 2://ステージ2
                    hurdles.add(new Hurdle(startX, 0, 60, 15));
                    hurdles.add(new HurdleSmall(startX3, 0));
                    break;

                case 3://ステージ3
                    hurdles.add(new Hurdle(startX, 0, 60, 15));
                    hurdles.add(new HurdleBig(startX2, 0));
                    break;

                case 99://エクストラステージ
                    hurdles.add(new Hurdle(startX, 0, 60, 15));
                    hurdles.add(new HurdleBig(startX2, 0));
                    hurdles.add(new HurdleSmall(startX3, 0));
                    break;
            }
            hurdleTime = now;
        }

        //弾の描写＋移動＋当たりカウント
        Iterator<Hurdle> it = hurdles.iterator();
        while (it.hasNext()) {
            Hurdle h = it.next();
            h.update();
            h.draw(canvas, paint);

            //画面外に出た弾は消える
            if (h.isOff(getHeight())) {
                it.remove();
            }

            //ヒットカウント
            if (h.collidesWith(player)) {
                hit++;
                it.remove();
            }

        }

        //経過時間表示など
        int elapsedSec = (int) ((now - starttime) / 1000);
        int t = clearTimeSec - elapsedSec;
        canvas.drawText("クリアまで：" + t, 50, 50, txtTime);

        //スコア表示
        paint.setColor(Color.BLACK);
        paint.setTextSize(60);
        if(hurdleType == 99){
            hp = 10 - hit;
            canvas.drawText("就労意欲:" + hp, 50, 100, paint);
        }else {
            hp = 5 -hit;
            canvas.drawText("就労意欲:" + hp, 50, 100, paint);
        }

        //クリア判定
        if (!isclear && elapsedSec >= clearTimeSec) {
            isclear = true;
            isLoop = false;
            if (onCleara != null) {
                onCleara.onClear();//アクテビティに通知
            }
        }
        //失敗判定
        if(!isclear && hp <= 0) {
            isLoop = false;
            onMissa.onMiss();//アクティビティに通知
            if (hurdleType == 99) {
                onMissa.onMiss();//アクティビティに通知
            }
        }


        //リロードする
        if(isLoop == true){
            invalidate();
        }


    }
}