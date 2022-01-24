package com.example.minigame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Avatar extends AppCompatActivity {

    myDBHelper myHelper;
    ImageView bbo, nana, ddubi, boradori;
    Button btnNick, btnAva;
    EditText nickname;
    String myAvatar, myNickName;
    LinearLayout avaLayout;
    TextView tvChk;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avatar);

        bbo = (ImageView) findViewById(R.id.bbo);
        nana = (ImageView) findViewById(R.id.nana);
        ddubi = (ImageView) findViewById(R.id.ddubi);
        boradori = (ImageView) findViewById(R.id.boradori);

        btnNick = (Button) findViewById(R.id.btnNick);
        btnAva = (Button) findViewById(R.id.btnAva);

        nickname = (EditText) findViewById(R.id.nickname);

        avaLayout = (LinearLayout) findViewById(R.id.avaLayout);

        myHelper = new myDBHelper(this);

        tvChk = (TextView) findViewById(R.id.tvChk);

        tvChk.setTextColor(Color.parseColor("#FF0000"));
        tvChk.setText("버튼을 눌러 닉네임을 확인해주세요");

        btnNick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                //닉네임중복확인
                myNickName = nickname.getText().toString();

                boolean nickChk = false;
                sqlDB = myHelper.getReadableDatabase();

                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM recordTBL;",null);

                while(cursor.moveToNext()){
                    if(cursor.getString(0).equals(myNickName)){
                        Toast.makeText(getApplicationContext(),"기존 캐릭터로 설정",Toast.LENGTH_SHORT).show();
                        nickChk = true;
                        break;
                    }
                }
                cursor.close();

                if(nickChk == false){
                    tvChk.setText("캐릭터를 선택해주세요");
                    avaLayout.setVisibility(View.VISIBLE);
                }else if(nickChk == true){
                    Cursor ava;
                    ava = sqlDB.rawQuery("SELECT * FROM recordTBL WHERE nickName ='"+myNickName+"';",null);
                    ava.moveToFirst();
                    myAvatar = ava.getString(1);
                    ava.close();

                    //Toast.makeText(getApplicationContext(),myAvatar,Toast.LENGTH_SHORT).show();

                    //myHelper.insert(myNickName,myAvatar);
                    Intent intent = new Intent(getApplicationContext(), Main.class);
                    intent.putExtra("NickName", myNickName);
                    intent.putExtra("Avatar", myAvatar);
                    startActivity(intent);
                }
                sqlDB.close();
            }
        });

        bbo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "뽀를 선택했습니다", Toast.LENGTH_SHORT).show();
                myAvatar = "bbo";

            }
        });
        nana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "나나를  선택했습니다", Toast.LENGTH_SHORT).show();
                myAvatar = "nana";

            }
        });
        ddubi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "뚜비를  선택했습니다", Toast.LENGTH_SHORT).show();
                    myAvatar = "ddubi";

            }
        });
        boradori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "보라돌이를  선택했습니다", Toast.LENGTH_SHORT).show();
                myAvatar = "boradori";

            }
        });

        btnAva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myAvatar.length() == 0) {
                    Toast.makeText(getApplicationContext(), "캐릭터를 선택해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(getApplicationContext(),myNickName+", "+myAvatar,Toast.LENGTH_SHORT).show();
                    myHelper.insert(myNickName,myAvatar);
                    Intent intent = new Intent(getApplicationContext(), Main.class);
                    intent.putExtra("NickName", myNickName);
                    intent.putExtra("Avatar", myAvatar);
                    startActivity(intent);
                }
            }
        });
    }

}

