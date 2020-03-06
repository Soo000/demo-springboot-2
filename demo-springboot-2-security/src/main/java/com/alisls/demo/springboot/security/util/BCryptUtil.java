package com.alisls.demo.springboot.security.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptUtil {

    /**
     * 生成密码
     *
     * @param password
     */
    public static String getEncodePassword(String password) {
        String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashpw;
    }

    public static boolean checkPassword(String srcPwd, String enPwd) {
        return BCrypt.checkpw(srcPwd, enPwd);
    }

    public static void main(String[] args) {
        String srcPwd = "soosky";
        String enPwd = getEncodePassword(srcPwd);
        System.out.println(enPwd);

        boolean isMatchered = checkPassword(srcPwd, enPwd);
        System.out.println(isMatchered);
    }

}
