package com.example.harotekugame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Talkset extends AppCompatActivity {

    //変数作成
    private String[] dialog;//本文を入れる
    private int i = 0;//ダイアログカウントカウント用
    private Button btn;//ボタン用
    private TextView txt;//テキスト用
    private TextView title;//タイトル用


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //部品の特定
        setContentView(R.layout.talk);
        btn = findViewById(R.id.btnNext);
        txt = findViewById(R.id.txtText);
        title = findViewById(R.id.txtTitle);

        //chapter受け取りの記述：会話パート
        Intent intent = getIntent();
        String chapter = intent.getStringExtra("chapterID");
        talkSelect(chapter);

    }


    //トーク画面の振り分け：キャプチャーごとのシーン対応
private void talkSelect(String id){
        switch (id){
            case "talk1"://オープニング会話パート（そのあとステージ1に向かう）
                title.setText("タイトル：\nこんにちはハローポリテク");
                dialog = getResources().getStringArray(R.array.talk1);
                btn.setOnClickListener(v -> {
                    if(i < dialog.length){
                        txt.setText(dialog[i]);
                        i++;
                    }else {
                        Intent intent = new Intent(Talkset.this,Stageset.class);
                        intent.putExtra("chapterID", "stage1");
                        startActivity(intent);
                    }
                });break;

            case "talk2"://ステージ1後の会話パート（そのままステージ2へ向かう）
                title.setText("タイトル：\n就職活動始めるその前に");
                dialog = getResources().getStringArray(R.array.talk2);
                btn.setOnClickListener(v -> {
                    if (i < dialog.length) {
                        txt.setText(dialog[i]);
                        i++;
                    } else {
                        Intent intent = new Intent(Talkset.this, Stageset.class);
                        intent.putExtra("chapterID", "stage2"); //ステージ2への指示
                        startActivity(intent);
                    }
                });break;

            case "talk3"://ステージ２後の会話パート（そのままステージ3へ向かう）
                title.setText("タイトル：\n本格的な就職活動！");
                dialog = getResources().getStringArray(R.array.talk3);
                btn.setOnClickListener(v -> {
                    if (i < dialog.length) {
                        txt.setText(dialog[i]);
                        i++;
                    } else {
                        Intent intent = new Intent(Talkset.this, Stageset.class);
                        intent.putExtra("chapterID", "stage3"); //ステージ3への指示
                        startActivity(intent);
                    }
                });break;

            case "talkED"://ステージ３クリア後の会話パートエンディング
                title.setText("タイトル：\n就職おめでとう！");
                dialog = getResources().getStringArray(R.array.talkED);
                btn.setOnClickListener(v -> {
                    if (i < dialog.length) {
                        txt.setText(dialog[i]);
                        i++;
                    } else {
                        Intent intent = new Intent(Talkset.this, MainActivity2.class);
                        startActivity(intent);
                    }
                });break;

            case "talkEXOP"://エキストラモード起動後の会話パート
                title.setText("タイトル：\n全部一気にやってもいい？");
                dialog = getResources().getStringArray(R.array.talkEXOP);
                btn.setOnClickListener(v -> {
                    if (i < dialog.length) {
                        txt.setText(dialog[i]);
                        i++;
                    } else {
                        Intent intent = new Intent(Talkset.this, Stageset.class);
                        intent.putExtra("chapterID", "stageEX"); //ステージEXへの指示
                        startActivity(intent);
                    }
                });break;
            case "talkEXED"://エキストラモードクリア後の会話パート
                title.setText("タイトル：\n駆け抜けていく");
                dialog = getResources().getStringArray(R.array.talkEXED);
                btn.setOnClickListener(v -> {
                    if (i < dialog.length) {
                        txt.setText(dialog[i]);
                        i++;
                    } else {
                        Intent intent = new Intent(Talkset.this, MainActivity.class);
                        startActivity(intent);
                    }
                });break;
        }

}

}
