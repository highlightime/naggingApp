package org.techtowm.nagging.ui.main;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABSE_VERSION=1;
    public DBHelper(Context context){
        super(context, "alarmdb",null,DATABSE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String memoSQL="Create table tb_alarm "
                +"(_id integer primary key autoincrement,"
                +"title)";
        db.execSQL(memoSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion==DATABSE_VERSION){
            db.execSQL("drop table tb_alarm");
            onCreate(db);
        }
    }
}
