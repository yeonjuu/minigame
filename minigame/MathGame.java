package com.example.minigame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MathGame extends AppCompatActivity {

    myDBHelper myHelper;
    TextView edit, assignment,tvScore, tv1 , tv2;
    Button start, submit, del, sign;
    String oper, input;
    String[] operArr = {"+", "-", "x"};
    Button[] btnNum = new Button[10];
    Integer[] btnID = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9};
    ImageView[] heart = new ImageView[3];
    int num1, num2, answer, score, count;
    Integer[] imageID = {R.id.heart1, R.id.heart2, R.id.heart3};
    View gameOver;
    String nick;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_game);

        edit = (TextView) findViewById(R.id.edit);
        assignment = (TextView) findViewById(R.id.assignment);
        start = (Button) findViewById(R.id.start);
        submit = (Button) findViewById(R.id.submit);
        tvScore = (TextView) findViewById(R.id.tvScore);
        del = (Button) findViewById(R.id.del);
        sign = (Button) findViewById(R.id.sign);
        tv1 =(TextView) findViewById(R.id.tv1);
        tv2 =(TextView) findViewById(R.id.tv2);

        Intent getIntent = getIntent();
        nick = getIntent.getStringExtra("NickName");

        myHelper = new myDBHelper(this);


        count = heart.length;
        score = 0;
        for (int i = 0; i < heart.length; i++) {
            heart[i] = (ImageView) findViewById(imageID[i]);
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showQuiz();

                for (int i = 0; i < heart.length; i++) {
                    heart[i].setVisibility(View.VISIBLE);
                }
                tvScore.setVisibility(View.VISIBLE);
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
                start.setVisibility(View.INVISIBLE);


                for (int i = 0; i < btnNum.length; i++) {
                    btnNum[i] = (Button) findViewById(btnID[i]);
                }

                for (int i = 0; i < btnNum.length; i++) {
                    final int index = i;
                    btnNum[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            input = edit.getText().toString() + btnNum[index].getText().toString();
                            edit.setText(input);
                        }
                    });
                }
            }
        });

        del.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit.getText().toString().length() != 0) {
                    input = edit.getText().toString().substring(0, edit.length() - 1);
                    edit.setText(input);
                }else{
                    Toast.makeText(getApplicationContext(),"입력값 없음",Toast.LENGTH_SHORT).show();
                }
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit.getText() == ""){
                    edit.setText("-");
                }else{
                    Toast.makeText(getApplication(),"마이너스 불가", Toast.LENGTH_SHORT).show();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit.getText() != "") {
                    if (answer == Integer.parseInt(edit.getText().toString())) {
                        score += 1;
                        //Toast.makeText(getApplication(), "정답", Toast.LENGTH_LONG).show();
                        showQuiz();
                    } else {
                        if(count!=1){
                            count -= 1;
                            heart[count].setVisibility(View.INVISIBLE);
                            //Toast.makeText(getApplicationContext(), "답: " + answer + " 오답", Toast.LENGTH_SHORT).show();
                            showQuiz();
                        }else{
                            heart[count-1].setVisibility(View.INVISIBLE);
                            //TODO
                            Toast.makeText(getApplicationContext(),"게임 결과: "+score,Toast.LENGTH_SHORT).show();
                            myHelper.update(nick,"ddubi",score);

                            gameOver = (View) View.inflate(MathGame.this, R.layout.game_over,null);
                            AlertDialog.Builder dlg = new AlertDialog.Builder(MathGame.this);

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
                                            Intent restart = new Intent(getApplicationContext(),MathGame.class);
                                            finish();
                                            startActivity(restart);
                                        }
                                    });
                            dlg.show();
                        }
                    }
                    tvScore.setText(String.valueOf(score));
                    edit.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "입력값 없음", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    void showQuiz(){
        Random random = new Random();
        num1 = random.nextInt(20)+1;
        num2 = random.nextInt(20)+1;
        oper = operArr[random.nextInt(3)];

        switch(oper){
            case "+" :
                answer = num1 + num2; break;
            case "-" :
                answer = num1 - num2; break;
            case "x" :
                answer = num1 * num2; break;
        }
        assignment.setText(num1 + oper + num2);
    }
}
