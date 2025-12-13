package com.example.harotekugame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Over2 extends AppCompatActivity {

    //変数生成
    private Button btnE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //部品の特定
        setContentView(R.layout.over2);
        btnE = findViewById(R.id.btn02);

        //ボタンを押したときの処理（EX）
        btnE.setOnClickListener(v -> {
            Intent intent = new Intent(Over2.this,MainActivity2.class);
            startActivity(intent);
            finish();
        });


    }

}
