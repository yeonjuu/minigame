package com.example.minigame;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class myDBHelper extends SQLiteOpenHelper {
    public myDBHelper(Context context){
        super(context, "recordDB", null,1);
    }
    //테이블 생성
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE recordTBL (nickName CHAR(20) PRIMARY KEY, " +
                        "avatar CHAR(20), nana INTEGER, ddubi INTEGER, boradori INTEGER, bbo INTEGER);");
    }
    //테이블 업데이트
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS recordTBL");
        onCreate(db);
    }
    //데이터 입력
    public void update(String nickName,String game,int score){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE recordTBL SET '"+game+"'="+score+" WHERE nickName='"+nickName+"';");
        db.close();
    }

    public void insert(String nickName, String avatar){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO recordTBL(nickName,avatar) VALUES ('"+nickName+"','"+avatar+"');");
        db.close();
    }

    //테이블 조회
    public String nanaGetResult(){
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        //Cursor cursor = db.rawQuery("SELECT DENSE_RANK() OVER(ORDER BY nana DESC) AS 'Rank', nickName , nana FROM recordTBL;", null);
        Cursor cursor = db.rawQuery("SELECT nickName , nana FROM recordTBL ORDER BY nana DESC;", null);
        while (cursor.moveToNext()) {
            String rank = String.valueOf(cursor.getPosition()+1);
            result += rank +"\t\t\t"+ cursor.getString(0) +"\t\t\t" + cursor.getInt(1) + "\r\n";
        }
        return result;
    }


    public String ddubiGetResult(){
        SQLiteDatabase db = getReadableDatabase(); String result = "";
        Cursor cursor = db.rawQuery("SELECT nickName , ddubi FROM recordTBL ORDER BY ddubi DESC;", null);
        while (cursor.moveToNext()) {
            String rank = String.valueOf(cursor.getPosition()+1);
            result += rank +"\t\t\t"+ cursor.getString(0) +"\t\t\t" + cursor.getInt(1) + "\r\n";
        }
        return result;
    }
    public String boradoriGetResult(){
        SQLiteDatabase db = getReadableDatabase(); String result = "";
        Cursor cursor = db.rawQuery("SELECT nickName , boradori FROM recordTBL ORDER BY boradori;", null);
        while (cursor.moveToNext()) {
            String rank = String.valueOf(cursor.getPosition()+1);
            result += rank +"\t\t\t"+ cursor.getString(0) +"\t\t\t" + cursor.getInt(1) + "\r\n";
        }
        return result;
    }
    public String bboGetResult(){
        SQLiteDatabase db = getReadableDatabase(); String result = "";
        Cursor cursor = db.rawQuery("SELECT nickName , bbo FROM recordTBL ORDER BY bbo DESC;", null);
        while (cursor.moveToNext()) {
            String rank = String.valueOf(cursor.getPosition()+1);
            result += rank +"\t\t\t"+ cursor.getString(0) +"\t\t\t" + cursor.getInt(1) + "\r\n";
        }
        return result;
    }
    public String myRank(String nick) {
        String myRank = "";
        return myRank;
    }

}