package com.example.minigame;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {

    TextView myNick;
    ImageView myAvatar;
    Button nanaGame, bboGame, boradoriGame, ddubiGame, btnRank, btnInit;
    String avatar,nick;
    myDBHelper myHelper;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        myAvatar = (ImageView) findViewById(R.id.myAvatar);
        myNick = (TextView) findViewById(R.id.myNick);

        nanaGame = (Button) findViewById(R.id.nanaGame);
        bboGame = (Button) findViewById(R.id.bboGame);
        boradoriGame = (Button) findViewById(R.id.boradoriGame);
        ddubiGame = (Button) findViewById(R.id.ddubiGame);
        btnRank = (Button) findViewById(R.id.btnRank);
        btnInit = (Button) findViewById(R.id.btnInit);

        Intent firstIntent = getIntent();
        avatar = firstIntent.getStringExtra("Avatar");
        nick = firstIntent.getStringExtra("NickName");

        myNick.setText(nick);
        myNick.setTextColor(Color.parseColor("#0000FF"));

        switch (avatar){
            case "bbo" :
                myAvatar.setImageResource(R.drawable.bbo);
                break;
            case "nana" :
                myAvatar.setImageResource(R.drawable.nana);
                break;
            case "ddubi" :
                myAvatar.setImageResource(R.drawable.ddubi);
                break;
            case "boradori" :
                myAvatar.setImageResource(R.drawable.boradori);
                break;
        }

        nanaGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nanaIntent = new Intent(getApplicationContext(),RSPGame.class);
                nanaIntent.putExtra("NickName",nick);
                startActivity(nanaIntent);
            }
        });

        ddubiGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ddubiIntent = new Intent(getApplicationContext(),MathGame.class);
                ddubiIntent.putExtra("NickName",nick);
                startActivity(ddubiIntent);
            }
        });

        boradoriGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent boradoriIntent = new Intent(getApplicationContext(),CardGame.class);
                boradoriIntent.putExtra("NickName",nick);
                startActivity(boradoriIntent);
            }
        });

        bboGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bboIntent = new Intent(getApplicationContext(),CookieGame.class);
                bboIntent.putExtra("NickName",nick);
                startActivity(bboIntent);
            }
        });

        btnRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rankIntent = new Intent(getApplicationContext(),Record.class);
                rankIntent.putExtra("NickName",nick);
                startActivity(rankIntent);
            }
        });
        if(nick.equals("admin")){
            btnInit.setVisibility(View.VISIBLE);
            myHelper = new myDBHelper(this);
            btnInit.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    sqlDB = myHelper.getWritableDatabase();
                    myHelper.onUpgrade(sqlDB,1,2);
                    sqlDB.close();
                    Toast.makeText(getApplicationContext(),"DB초기화",Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }

    }
    long pressedTime;
    @Override
    public void onBackPressed() {
        if ( pressedTime == 0 ) {
            Toast.makeText(Main.this, " 한 번 더 누르면 종료됩니다." , Toast.LENGTH_SHORT).show();
            pressedTime = System.currentTimeMillis();
        }
        else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);

            if ( seconds > 1000 ) {
                Toast.makeText(Main.this, " 한 번 더 누르면 종료됩니다." , Toast.LENGTH_LONG).show();
                pressedTime = 0 ;
            }
            else {
                super.onBackPressed();
                finish(); // app 종료 시키기
            }
        }

    }
}
