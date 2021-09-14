package cn.xuziao.faceprocess.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import cn.xuziao.faceprocess.tools.ReturnInfo;

public class UserInfoDAO {

    Context context;

    public UserInfoDAO(Context context) {
        this.context = context;
    }

    /**执行逻辑：isnow表示当前要注册的账号，如果该字段为Constants.IS_NOW
     * 表示当前账号正在被操作，所以在加入一条记录的时候要先把其余的Constants.IS_NOW
     * 改为Constants.NOT_NOW
     * @param username 用户名
     * @param password 密码
     * @param email 邮件
     * @return 执行状态
     */
    public ReturnInfo addInfo(String username, String password, String email) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        try {
            String sql1 = "update userinfo set isnow = ? where isnow = ?";
            db.execSQL(sql1, new Object[]{Constants.NOT_NOW, Constants.IS_NOW});
            String sql2 = "insert into userInfo(username, password, " +
                    "email, isnow) values (?, ?, ?, ?)";
            db.execSQL(sql2, new Object[]{username, password, email, Constants.IS_NOW});
        } catch (SQLiteConstraintException sqLiteConstraintException){
            String sql3 = "update userinfo set password = ?, email = ?, isnow = ? where username = ?";
            db.execSQL(sql3, new Object[]{password, email, Constants.IS_NOW, username});
            db.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            db.close();
            return ReturnInfo.OTHERS;
        }
        db.close();
        return ReturnInfo.OK;
    }

    public ReturnInfo addVerificationCode(String username ,String verificationCode) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        try {
            String sql = "update userinfo set verificationCode = ? where username = ?";
            db.execSQL(sql, new Object[]{verificationCode, username});
            db.close();
        } catch (Exception e) {
            db.close();
            return ReturnInfo.OTHERS;
        } return ReturnInfo.OK;
    }

    public String getVerificationCode(String username) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String verificationCode;
        Cursor cursor;
        cursor = db.rawQuery("select verificationCode from userinfo where username=?", new String[]{username});
        cursor.moveToFirst();
        verificationCode = cursor.getString(cursor.getColumnIndex("verificationCode"));
        cursor.close();
        db.close();
        return verificationCode;
    }
    public ReturnInfo delTable() {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        try {
            String sql = "delete from userInfo where isnow != ?";
            db.execSQL(sql, new Object[]{Constants.NOT_NOW});
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnInfo.OTHERS;
        }
        return ReturnInfo.OK;
    }

}













