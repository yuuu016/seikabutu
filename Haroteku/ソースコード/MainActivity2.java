package com.example.harotekugame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//クリア後のEXモード搭載したスタート画面
public class MainActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start2);

        //ボタンの読み取り
        Button btnS1 = findViewById(R.id.btnStart1);
        Button btnS2 = findViewById(R.id.btnStart2);

        //スタート画面からトーク画面に移動する
        btnS1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this,Talkset.class);
            intent.putExtra("chapterID", "talk1"); //会話パート１への指示
            startActivity(intent);
        });

        //スタート画面からエクストラモードに移動する
        btnS2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this,Talkset.class);
            intent.putExtra("chapterID", "talkEXOP");//EXモードに向かう指示
            startActivity(intent);
        });

    }
}
