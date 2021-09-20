package cn.xuziao.faceprocess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import cn.xuziao.faceprocess.tools.JudgeEmail;
import cn.xuziao.faceprocess.tools.ReturnInfo;
import cn.xuziao.faceprocess.web.SendIdentifyingCode;
import cn.xuziao.faceprocess.web.WebBaseHandle;

public class resignActivity extends Activity {

    private String TAG = "resignActivity";
    private String keyword;
    private final Object object = new Object();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resign);

        Button code_resign = findViewById(R.id.get_code_resign);
        Button resign_resign = findViewById(R.id.resign_resign);
        EditText username_edittext_resign = findViewById(R.id.username_edittext_resign);
        EditText password_edittext_resign = findViewById(R.id.password_edittext_resign);
        EditText repassword_edittext_resign = findViewById(R.id.repassword_edittext_resign);
        EditText email_edittext_resign = findViewById(R.id.email_edittext_resign);
        EditText code_edittext_resign = findViewById(R.id.code_edittext_resign);




        code_resign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = String.valueOf(email_edittext_resign.getText());
                JudgeEmail judgeEmail = new JudgeEmail(email);
                if (judgeEmail.judge()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int mes = 0;
                            for (int i = 0; i < 5; i++) {
                                mes += new Random().nextInt(10) * Math.pow(10, i);
                            }
                            keyword = String.valueOf(mes);
                            SendIdentifyingCode sendIdentifyingCode = new SendIdentifyingCode();
                            String info = sendIdentifyingCode.send(email, keyword);
                        }
                    }).start();
                    code_resign.setText("已发送，请等待！");
                    code_resign.setEnabled(false);
//                    Toast.makeText(resignActivity.this, "验证码已发送，30秒后可再尝试发送", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(resignActivity.this, "输入邮箱格式不正确", Toast.LENGTH_SHORT).show();
                }
            }
        });


        resign_resign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(password_edittext_resign.getText()).equals(String.valueOf(repassword_edittext_resign.getText()))){
                    if (String.valueOf(code_edittext_resign.getText()).equals(keyword)) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                WebBaseHandle webBaseHandle = new WebBaseHandle();
                                ReturnInfo returnInfo = webBaseHandle.resign(
                                        String.valueOf(username_edittext_resign.getText()),
                                        String.valueOf(password_edittext_resign.getText()),
                                        String.valueOf(email_edittext_resign.getText())
                                );
                                Log.d(TAG, returnInfo.getStatus());
                                if (returnInfo.getCode() == 0) {
                                    startActivity(new Intent(resignActivity.this, UserActivity.class));
                                }
                            }
                        }).start();
                    } else {
                        Toast.makeText(resignActivity.this, "验证码输入错误", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(resignActivity.this, "两次密码输入不同", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}