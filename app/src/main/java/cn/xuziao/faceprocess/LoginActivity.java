package cn.xuziao.faceprocess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import cn.xuziao.faceprocess.tools.ReturnInfo;
import cn.xuziao.faceprocess.web.WebBaseHandle;


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

        EditText edit_username = findViewById(R.id.edit_username);
        EditText edit_password = findViewById(R.id.edit_password);
        Button btn = findViewById(R.id.button_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        WebBaseHandle webBaseHandle = new WebBaseHandle();
                        ReturnInfo returnInfo = webBaseHandle.login(String.valueOf(edit_username.getText()), String.valueOf(edit_password.getText()));
                        if (returnInfo.getCode() == 0){
                            startActivity(new Intent(LoginActivity.this, UserActivity.class));
                        } else {
                            LoginActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this, returnInfo.getStatus(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }


}