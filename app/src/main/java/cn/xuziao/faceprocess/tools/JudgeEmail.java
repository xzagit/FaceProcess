package cn.xuziao.faceprocess.tools;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JudgeEmail {
    String email;
    public JudgeEmail(String email) {
        this.email = email;
    }
    public boolean judge() {
        if (Objects.equals(email, "") || email == null){
            return false;
        }
        String pat = "^[a-z0-9A-Z]+[- |a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$";
        Pattern pattern = Pattern.compile(pat);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
