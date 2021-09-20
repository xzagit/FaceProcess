package cn.xuziao.faceprocess.web;

import android.util.Log;

import java.io.IOException;
import java.util.Objects;

import cn.xuziao.faceprocess.tools.GetReturnInfo;
import cn.xuziao.faceprocess.tools.PasswordEncryption;
import cn.xuziao.faceprocess.tools.ReturnInfo;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebBaseHandle {
    private static final String TAG = "WebBaseHandle";
    String baseUrl = "https://faceprocess.xuziao.cn:8081/";
    final OkHttpClient client = new OkHttpClient();
    PasswordEncryption passwordEncryption = new PasswordEncryption();

    public ReturnInfo login(String username, String password){
        password = passwordEncryption.md5Encryption(password);
        String url = baseUrl+"login/"+username+"/"+password+"/";
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return GetReturnInfo.get(Objects.requireNonNull(response.body()).string());

        } catch (IOException e) {
            e.printStackTrace();
            return ReturnInfo.OTHERS;
        }
    }

    public ReturnInfo resign(String username, String password, String email){
        password = passwordEncryption.md5Encryption(password);
        String url = baseUrl+"resign/"+username+"/"+password+"/"+email+"/";
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return GetReturnInfo.get(Objects.requireNonNull(response.body()).string());

        } catch (IOException e) {
            e.printStackTrace();
            return ReturnInfo.OTHERS;
        }
    }
}
