package com.example.minigame;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class RSPGame extends AppCompatActivity {

    myDBHelper myHelper;
    TextView tvRound,tvNanaScore ,tvMyScore,result;
    ImageView nana, rock,scissors,paper;
    int myScore,nanaScore, round;
    View gameOver;
    SQLiteDatabase sqlDB;
    String nick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rsp_game);

        tvRound = (TextView) findViewById(R.id.tvRound);
        tvNanaScore = (TextView) findViewById(R.id.tvNanaScore);
        tvMyScore = (TextView) findViewById(R.id.tvMyScore);
        result = (TextView) findViewById(R.id.result);

        nana = (ImageView) findViewById(R.id.nana);
        rock = (ImageView) findViewById(R.id.rock);
        scissors = (ImageView) findViewById(R.id.scissors);
        paper = (ImageView) findViewById(R.id.paper);

        myScore = 0;
        nanaScore = 0;
        round = 0;

        myHelper = new myDBHelper(this);

        Intent myInfo = getIntent();
        nick = myInfo.getStringExtra("NickName");

        rock.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                RSPGame(0);
            }
        });

        scissors.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                RSPGame(1);
            }
        });

        paper.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                RSPGame(2);
            }
        });

    }
    public void RSPGame(int num) {
        tvRound.setText(String.valueOf(round+1));
        Random rand = new Random();
        int img = rand.nextInt(3);
        switch (img) {
            case 0:
                nana.setImageResource(R.drawable.nanarock);
                break;
            case 1:
                nana.setImageResource(R.drawable.nanascissors);
                break;
            case 2:
                nana.setImageResource(R.drawable.nanapaper);
                break;
        }
        if (num == img) {
            result.setText("DRAW");
            result.setTextColor(Color.parseColor("#00FF00"));
        } else if ((num == 0 && img == 1) || (num == 1 && img == 2) || (num == 2 && img == 0)) {
            result.setText("WIN!");
            result.setTextColor(Color.parseColor("#FF0000"));
            myScore++;
        }else if((num==0 && img==2)||(num==1 && img==0)||(num==2 && img==1)){
            result.setText("LOSE");
            result.setTextColor(Color.parseColor("#0000FF"));
            nanaScore++;
        }
        tvNanaScore.setText(String.valueOf(nanaScore));
        tvMyScore.setText(String.valueOf(myScore));
        round++;
        if(round==10){
            //TODO
            //게임 결과 기록 추가하기
            Toast.makeText(getApplicationContext(),"게임 결과 :  "+myScore,Toast.LENGTH_SHORT).show();
            myHelper.update(nick,"nana",myScore);

            gameOver = (View) View.inflate(RSPGame.this, R.layout.game_over,null);
            AlertDialog.Builder dlg = new AlertDialog.Builder(RSPGame.this);
            dlg.setView(gameOver);
            dlg.setPositiveButton("게임 종료",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
            dlg.setNegativeButton("다시 시작",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent restart = new Intent(getApplicationContext(),RSPGame.class);
                            finish();
                            startActivity(restart);
                        }
                    });
            dlg.show();
        }
    }
}
