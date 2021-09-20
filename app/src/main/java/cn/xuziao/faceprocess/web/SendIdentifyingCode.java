package cn.xuziao.faceprocess.web;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SendIdentifyingCode {
    public String send(String email, String keyword) {
        String url = "https://faceprocess.xuziao.cn:8081/sendCode/"+email+"/"+keyword+"/";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
            return "验证码发送错误";
        }
    }
}
