package com.example.harotekugame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


//クリア画面の処理
public class Clearset extends AppCompatActivity {

    //変巣作成
    Button btn;//ボタン用の変数

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //部品の特定
        setContentView(R.layout.clear);
        btn = findViewById(R.id.btnC);

        //ゲームパートからの受取り
        Intent intent = getIntent();
        String chapter = intent.getStringExtra("chapterID");
        CalkSelect(chapter);

    }

    private  void CalkSelect(String id){
        switch (id){

            case "clear1"://ステージ１クリア後の処理→talk２に向かう
                btn.setOnClickListener(v -> {
                    Intent intent = new Intent(Clearset.this,Talkset.class);
                    intent.putExtra("chapterID","talk2");
                    startActivity(intent);
                });break;

            case "clear2"://ステージ２クリア後の処理→talk3に向かう
                btn.setOnClickListener(v -> {
                    Intent intent = new Intent(Clearset.this,Talkset.class);
                    intent.putExtra("chapterID","talk3");
                    startActivity(intent);
                });break;

            case "clear3"://ステージ3クリア後の処理→talkEDに向かう
                btn.setOnClickListener(v -> {
                    Intent intent = new Intent(Clearset.this,Talkset.class);
                    intent.putExtra("chapterID","talkED");
                    startActivity(intent);
                });break;

            case "clearEX"://ステージEXクリア後の処理→talkEXEDに向かう
                btn.setOnClickListener(v -> {
                    Intent intent = new Intent(Clearset.this,Talkset.class);
                    intent.putExtra("chapterID","talkEXED");
                    startActivity(intent);
                });break;
        }

    }

}
