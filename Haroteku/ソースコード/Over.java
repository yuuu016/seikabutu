package com.example.harotekugame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Over extends AppCompatActivity {

    //変数生成
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //部品の特定
        setContentView(R.layout.over);
        btn = findViewById(R.id.btnO);

        //ボタンを押したときの処理（ノーマル）
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(Over.this,MainActivity.class);
            startActivity(intent);
            finish();
            });
        }
    }



