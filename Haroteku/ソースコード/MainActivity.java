package com.example.harotekugame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.start1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //ボタンの読み取り
        Button btnS1 = findViewById(R.id.btnStart1);

        //スタート画面からトーク画面に移動する
        btnS1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,Talkset.class);
            intent.putExtra("chapterID", "talk1"); //会話パート１への指示
            startActivity(intent);
        });

    }
}