package com.example.harotekugame;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class Stageset extends AppCompatActivity {


    //変数作成
    private Game game;
    FrameLayout gameContainer;
    private SeekBar seekBar;
    private Drawable aikon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //親レイアウトの作成：
        //これを変更してステージ変更とする
        FrameLayout oya = new FrameLayout(this);
        setContentView(oya);

        //ゲーム領域表示
        gameContainer = new FrameLayout(this);
        oya.addView(gameContainer, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        //シークバー生成:シークバーの設定
        seekBar = new SeekBar(this);
        seekBar.setMax(100);
        seekBar.setProgress(50);
        aikon = getResources().getDrawable(R.drawable.game2);//画像：幅74高さ100
        seekBar.setThumb(aikon);
        //シークバーの場所
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM;
        params.bottomMargin = (int)(100 * getResources().getDisplayMetrics().density + 0.5f);
        seekBar.setLayoutParams(params);
        oya.addView(seekBar); //前面に表示


        //シークバー動かしてる処理
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(game != null){
                    //シークバーとプレイヤーの当たり判定の高さを合わせる
                    int seekBarHeight = seekBar.getHeight();
                    game.setPlayertoSeekber(progress,seekBarHeight);
                    float x = game.getWidth() * (progress/100f);
                    //game.setOnClearA(x);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });


        //chapter受け取りの記述:ステージ
        Intent intent = getIntent();
        String chapter = intent.getStringExtra("chapterID");
        stageSelect(chapter);

    }


    //ステージの振り分け：キャプチャーごとのシーン対応
    private void stageSelect (String id){

        switch (id) {
                case "stage1":
                    game = new Game(this,60,1);
                    gameContainer.addView(game);
                    //クリアに反応
                    game.setOnClearA(() ->{
                        Intent intent = new Intent(Stageset.this, Clearset.class);
                        intent.putExtra("chapterID", "clear1");
                        startActivity(intent);
                        finish();
                    });
                    //失敗に反応
                    game.setOnMissA(() ->{
                        Intent intent = new Intent(Stageset.this, Over.class);
                        startActivity(intent);
                        finish();
                    });
                break;

                case "stage2":
                    game = new Game(this,60,2);
                    gameContainer.addView(game);
                    //クリアに反応
                    game.setOnClearA(() -> {
                        Intent intent = new Intent(Stageset.this, Clearset.class);
                        intent.putExtra("chapterID", "clear2");
                        startActivity(intent);
                        finish();
                    });
                    //失敗に反応
                    game.setOnMissA(() ->{
                        Intent intent = new Intent(Stageset.this, Over.class);
                        startActivity(intent);
                        finish();
                    });
                    break;

                case "stage3":
                    game = new Game(this,60,3);
                    gameContainer.addView(game);
                    //クリアに反応
                    game.setOnClearA(() -> {
                        Intent intent = new Intent(Stageset.this, Clearset.class);
                        intent.putExtra("chapterID", "clear3");
                        startActivity(intent);
                        finish();
                    });
                    //失敗に反応
                    game.setOnMissA(() ->{
                        Intent intent = new Intent(Stageset.this, Over.class);
                        startActivity(intent);
                        finish();
                    });
                    break;

                case "stageEX":
                    game = new Game(this,150,99);
                    gameContainer.addView(game);
                    //クリアに反応
                    game.setOnClearA(() -> {
                        Intent intent = new Intent(Stageset.this, Clearset.class);
                        intent.putExtra("chapterID", "clearEX");
                        startActivity(intent);
                        finish();
                    });
                    //失敗に反応
                    game.setOnMissA(() ->{
                        Intent intent = new Intent(Stageset.this, Over2.class);
                        startActivity(intent);
                        finish();
                    });
                    break;
            }
    }
}