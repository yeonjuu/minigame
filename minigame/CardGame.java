package com.example.minigame;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.LogRecord;

public class CardGame extends AppCompatActivity {
    ImageView[] card=new ImageView[16];
    ImageView[] cover=new ImageView[16];

    Integer[] cardID={R.id.card1,R.id.card2,R.id.card3,R.id.card4,
            R.id.card5,R.id.card6,R.id.card7,R.id.card8,
            R.id.card9, R.id.card10,R.id.card11,R.id.card12,
            R.id.card13, R.id.card14,R.id.card15,R.id.card16};

    Integer[] coverID={R.id.cover1,R.id.cover2,R.id.cover3,R.id.cover4,
            R.id.cover5,R.id.cover6,R.id.cover7,R.id.cover8,
            R.id.cover9, R.id.cover10,R.id.cover11,R.id.cover12,
            R.id.cover13, R.id.cover14,R.id.cover15,R.id.cover16};

    Chronometer chrono;
    TextView sscore,myScore;
    Button gameStart,ok;
    LinearLayout cardLayout;
    int count, num1, num2, nnum1, nnum2,score;
    View gameOver;
    String nick;
    myDBHelper myHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_game);


        chrono=findViewById(R.id.chronometer);
        Random r = new Random();
        gameStart=findViewById(R.id.gameStart);
        cardLayout=findViewById(R.id.cardLayout);
        ok=findViewById(R.id.ok);
        sscore=findViewById(R.id.score);

        Integer a[]=new Integer[card.length];

        Intent getIntent = getIntent();
        nick = getIntent.getStringExtra("NickName");

        myHelper = new myDBHelper(this);

        for(int i=0;i<card.length;i++){
            a[i]=r.nextInt(card.length);
            for(int j=0; j<i;j++){
                if(a[i]==a[j]){
                    i--;
                }
            }
        }

        for(int i=0;i<card.length;i++){
            card[i]=(ImageView)findViewById(cardID[i]);
        }

        for(int i=0;i<cover.length;i++){
            cover[i]=(ImageView)findViewById(coverID[i]);
        }

        for(int i=0;i<2;i++){
            card[a[i]].setImageResource(R.drawable.bbonana);
        }
        for(int i=2;i<4;i++){
            card[a[i]].setImageResource(R.drawable.boddo);
        }
        for(int i=4;i<6;i++){
            card[a[i]].setImageResource(R.drawable.ddubora);
        }
        for(int i=6;i<8;i++){
            card[a[i]].setImageResource(R.drawable.naddubi);
        }
        for(int i=8;i<10;i++){
            card[a[i]].setImageResource(R.drawable.boradori);
        }
        for(int i=10;i<12;i++){
            card[a[i]].setImageResource(R.drawable.ddubi);
        }
        for(int i=12;i<14;i++){
            card[a[i]].setImageResource(R.drawable.nana);
        }
        for(int i=14;i<16;i++){
            card[a[i]].setImageResource(R.drawable.bbo);
        }


        for(int i=0;i<cover.length;i++){
            cover[i]=(ImageView) findViewById(coverID[i]);
        }


        gameStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
                chrono.setTextColor(Color.BLUE);
                gameStart.setClickable(false);


                count=0;
                score=0;
                for(int i=0;i<16;i++){
                    cover[i].setVisibility(View.VISIBLE);
                    card[i].setVisibility(View.INVISIBLE);
                }

                for (int i = 0; i < 16; i++) {
                    cover[i].setOnClickListener(myClick);
                }

                for(int i=0;i<16;i++){
                    cover[i].setVisibility(View.INVISIBLE);
                    card[i].setVisibility(View.VISIBLE);
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        for(int i=0;i<16;i++){
                            cover[i].setVisibility(View.VISIBLE);
                            card[i].setVisibility(View.INVISIBLE);
                        }
                    }
                }, 5000);

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(count>=2){
                            nnum1=Arrays.asList(a).indexOf(num1);
                            nnum2=Arrays.asList(a).indexOf(num2);
                            //sscore.setText(String.valueOf(nnum1)+" , "+String.valueOf(nnum2));
                            if((nnum1==0&&nnum2==1)||(nnum1==1&&nnum2==0)){
                                score++;
                                sscore.setText("점수: "+score);
                                count=0;
                            }
                            else if((nnum1==2&&nnum2==3)||(nnum1==3&&nnum2==2)){
                                score++;
                                sscore.setText("점수: "+score);
                                count=0;
                            }
                            else if((nnum1==4&&nnum2==5)||(nnum1==5&&nnum2==4)){
                                score++;
                                sscore.setText("점수: "+score);
                                count=0;
                            }
                            else if((nnum1==6&&nnum2==7)||(nnum1==7&&nnum2==6)){
                                score++;
                                sscore.setText("점수: "+score);
                                count=0;
                            }
                            else if((nnum1==8&&nnum2==9)||(nnum1==9&&nnum2==8)){
                                score++;
                                sscore.setText("점수: "+score);
                                count=0;
                            }
                            else if((nnum1==10&&nnum2==11)||(nnum1==11&&nnum2==10)){
                                score++;
                                sscore.setText("점수: "+score);
                                count=0;
                            }
                            else if((nnum1==12&&nnum2==13)||(nnum1==13&&nnum2==12)){
                                score++;
                                sscore.setText("점수: "+score);
                                count=0;
                            }
                            else if((nnum1==14&&nnum2==15)||(nnum1==15&&nnum2==14)){
                                score++;
                                sscore.setText("점수: "+score);
                                count=0;
                            }
                            else if((nnum1==16&&nnum2==17)||(nnum1==17&&nnum2==16)){
                                score++;
                                sscore.setText("점수: "+score);
                                count=0;
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"틀렸습니다!",Toast.LENGTH_SHORT).show();
                                cover[num1].setVisibility(View.VISIBLE);
                                cover[num2].setVisibility(View.VISIBLE);
                                card[num1].setVisibility(View.INVISIBLE);
                                card[num2].setVisibility(View.INVISIBLE);
                                count=0;
                            }
                            if (score==8){
                                chrono.stop();
                                chrono.setTextColor(Color.RED);

                                long current = SystemClock.elapsedRealtime()-chrono.getBase();
                                int time = (int) ((current-5000)/1000);
                                Toast.makeText(getApplicationContext(),"게임 결과: "+String.valueOf(time)+"초",Toast.LENGTH_SHORT).show();

                                //TODO
                                myHelper.update(nick,"boradori",time);

                                gameOver = (View) View.inflate(CardGame.this, R.layout.game_over,null);
                                AlertDialog.Builder dlg = new AlertDialog.Builder(CardGame.this);

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
                                                Intent restart = new Intent(getApplicationContext(),CardGame.class);
                                                finish();
                                                startActivity(restart);
                                            }
                                        });
                                dlg.show();
                            }
                        }
                        else if(count<2){
                            Toast.makeText(getApplicationContext(),"카드 2개 선택해주세요!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    View.OnClickListener myClick=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.cover1:
                    if (count < 2) {
                        if (count == 0) {
                            num1 = 0;
                        }
                        if (count == 1){
                            num2 = 0;
                        }
                        count++;
                        cover[0].setVisibility(View.INVISIBLE);
                        card[0].setVisibility(View.VISIBLE);
                    }
                    else if(count>=2){
                        Toast.makeText(getApplicationContext(),"!카드는 2개만!",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.cover2:
                    if (count < 2) {
                        if (count == 0) {
                            num1 = 1;
                        }
                        if (count == 1){
                            num2 = 1;
                        }
                        count++;
                        cover[1].setVisibility(View.INVISIBLE);
                        card[1].setVisibility(View.VISIBLE);
                    }
                    else if(count>=2){
                        Toast.makeText(getApplicationContext(),"!카드는 2개만!",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.cover3:
                    if (count < 2) {
                        if (count == 0) {
                            num1 = 2;
                        }
                        if (count == 1){
                            num2 = 2;
                        }
                        count++;
                        cover[2].setVisibility(View.INVISIBLE);
                        card[2].setVisibility(View.VISIBLE);
                    }
                    else if(count>=2){
                        Toast.makeText(getApplicationContext(),"!카드는 2개만!",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.cover4:
                    if (count < 2) {
                        if (count == 0) {
                            num1 = 3;
                        }
                        if (count == 1){
                            num2 = 3;
                        }
                        count++;
                        cover[3].setVisibility(View.INVISIBLE);
                        card[3].setVisibility(View.VISIBLE);
                    }
                    else if(count>=2){
                        Toast.makeText(getApplicationContext(),"!카드는 2개만!",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.cover5:
                    if (count < 2) {
                        if (count == 0) {
                            num1 = 4;
                        }
                        if (count == 1){
                            num2 = 4;
                        }
                        count++;
                        cover[4].setVisibility(View.INVISIBLE);
                        card[4].setVisibility(View.VISIBLE);
                    }
                    else if(count>=2){
                        Toast.makeText(getApplicationContext(),"!카드는 2개만!",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.cover6:
                    if (count < 2) {
                        if (count == 0) {
                            num1 = 5;
                        }
                        if (count == 1){
                            num2 = 5;
                        }
                        count++;
                        cover[5].setVisibility(View.INVISIBLE);
                        card[5].setVisibility(View.VISIBLE);
                    }
                    else if(count>=2){
                        Toast.makeText(getApplicationContext(),"!카드는 2개만!",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.cover7:
                    if (count < 2) {
                        if (count == 0) {
                            num1 = 6;
                        }
                        if (count == 1){
                            num2 = 6;
                        }
                        count++;
                        cover[6].setVisibility(View.INVISIBLE);
                        card[6].setVisibility(View.VISIBLE);
                    }
                    else if(count>=2){
                        Toast.makeText(getApplicationContext(),"!카드는 2개만!",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.cover8:
                    if (count < 2) {
                        if (count == 0) {
                            num1 = 7;
                        }
                        if (count == 1){
                            num2 = 7;
                        }
                        count++;
                        cover[7].setVisibility(View.INVISIBLE);
                        card[7].setVisibility(View.VISIBLE);
                    }
                    else if(count>=2){
                        Toast.makeText(getApplicationContext(),"!카드는 2개만!",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.cover9:
                    if (count < 2) {
                        if (count == 0) {
                            num1 = 8;
                        }
                        if (count == 1){
                            num2 = 8;
                        }
                        count++;
                        cover[8].setVisibility(View.INVISIBLE);
                        card[8].setVisibility(View.VISIBLE);
                    }
                    else if(count>=2){
                        Toast.makeText(getApplicationContext(),"!카드는 2개만!",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.cover10:
                    if (count < 2) {
                        if (count == 0) {
                            num1 = 9;
                        }
                        if (count == 1){
                            num2 = 9;
                        }
                        count++;
                        cover[9].setVisibility(View.INVISIBLE);
                        card[9].setVisibility(View.VISIBLE);
                    }
                    else if(count>=2){
                        Toast.makeText(getApplicationContext(),"!카드는 2개만!",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.cover11:
                    if (count < 2) {
                        if (count == 0) {
                            num1 = 10;
                        }
                        if (count == 1){
                            num2 = 10;
                        }
                        count++;
                        cover[10].setVisibility(View.INVISIBLE);
                        card[10].setVisibility(View.VISIBLE);
                    }
                    else if(count>=2){
                        Toast.makeText(getApplicationContext(),"!카드는 2개만!",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.cover12:
                    if (count < 2) {
                        if (count == 0) {
                            num1 = 11;
                        }
                        if (count == 1){
                            num2 = 11;
                        }
                        count++;
                        cover[11].setVisibility(View.INVISIBLE);
                        card[11].setVisibility(View.VISIBLE);
                    }
                    else if(count>=2){
                        Toast.makeText(getApplicationContext(),"!카드는 2개만!",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.cover13:
                    if (count < 2) {
                        if (count == 0) {
                            num1 = 12;
                        }
                        if (count == 1){
                            num2 = 12;
                        }
                        count++;
                        cover[12].setVisibility(View.INVISIBLE);
                        card[12].setVisibility(View.VISIBLE);
                    }
                    else if(count>=2){
                        Toast.makeText(getApplicationContext(),"!카드는 2개만!",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.cover14:
                    if (count < 2) {
                        if (count == 0) {
                            num1 = 13;
                        }
                        if (count == 1){
                            num2 = 13;
                        }
                        count++;
                        cover[13].setVisibility(View.INVISIBLE);
                        card[13].setVisibility(View.VISIBLE);
                    }
                    else if(count>=2){
                        Toast.makeText(getApplicationContext(),"!카드는 2개만!",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.cover15:
                    if (count < 2) {
                        if (count == 0) {
                            num1 = 14;
                        }
                        if (count == 1){
                            num2 = 14;
                        }
                        count++;
                        cover[14].setVisibility(View.INVISIBLE);
                        card[14].setVisibility(View.VISIBLE);
                    }
                    else if(count>=2){
                        Toast.makeText(getApplicationContext(),"!카드는 2개만!",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.cover16:
                    if (count < 2) {
                        if (count == 0) {
                            num1 = 15;
                        }
                        if (count == 1){
                            num2 = 15;
                        }
                        count++;
                        cover[15].setVisibility(View.INVISIBLE);
                        card[15].setVisibility(View.VISIBLE);
                    }
                    else if(count>=2){
                        Toast.makeText(getApplicationContext(),"!카드는 2개만!",Toast.LENGTH_SHORT).show();
                    }
                    break;

            }
        }

    };

}