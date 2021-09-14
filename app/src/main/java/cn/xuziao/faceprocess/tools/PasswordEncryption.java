package cn.xuziao.faceprocess.tools;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author xuziao
 * @date 2021/9/10 19:30
 */

public class PasswordEncryption {
    public String md5Encryption(String password){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert md != null;
        md.update(password.getBytes());
        return new BigInteger(1, md.digest()).toString(16);
    }

    public String sha1Encryption(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert md != null;
        md.update(password.getBytes());
        return new BigInteger(1, md.digest()).toString(256);
    }
}
