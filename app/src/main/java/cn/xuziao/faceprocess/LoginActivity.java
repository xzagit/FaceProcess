package cn.xuziao.faceprocess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

import cn.xuziao.faceprocess.dao.TestConnection;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String TAG = "LoginActivity";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textView = findViewById(R.id.login_resign);
        textView.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, resignActivity.class);
            startActivity(intent);
        });

    }
}