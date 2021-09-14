package cn.xuziao.faceprocess.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

    private final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, Constants.NAME, null, Constants.VERSION);
        Log.d(TAG, "DatabaseHelper: 调用到构造方法");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: 数据库被创建了！");
        String sql = "create table userInfo (username varchar primary key, password varchar," +
                " email varchar, verificationCode varchar, isnow integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
