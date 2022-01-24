package com.example.minigame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class CookieGame extends AppCompatActivity {

    Button start, btnLeft, btnRight;
    ImageView bbo;
    ImageView[] smile = new ImageView[6];
    Integer[] cookId = {R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5, R.id.c6};
    int[] cookies ;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cookie_game);

        bbo = (ImageView) findViewById(R.id.bbo);
        start = (Button) findViewById(R.id.start);
        btnLeft = (Button) findViewById(R.id.btnLeft);
        btnRight = (Button) findViewById(R.id.btnRight);

        for (int i = 0; i < cookId.length; i++) {
            smile[i] = (ImageView) findViewById(cookId[i]);
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start.setVisibility(View.INVISIBLE);
                bbo.setVisibility(View.VISIBLE);
                //TODO
                //쿠키들 떨어지기 및 쿠키마다 다르게
                //1. 점수 게임
                for(int i = 0; i<num; i++){
                    smile[cookies[i]].setVisibility(View.VISIBLE);
                }
            }
        });

        //TODO
        //앵글밖으로 나가지 못하게 , 시작할때 좌측하단으로 이동하는 이유

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }

}
