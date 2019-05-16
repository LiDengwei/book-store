package com.book.manager.util;

/**
 * Created by yangyibo on 17/2/7.
 */

import java.io.IOException;
import java.security.MessageDigest;

/**
 * MD5加密工具
 *
 */
public class MD5Util {

    private static final String SALT = "tamboo";

    public static String encode(String password) {
        password = password + SALT;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }




        private static final String UNIT = " 万千佰拾亿千佰拾万千佰拾元角分";
        private static final String DIGIT = "零壹贰叁肆伍陆柒捌玖";
        private static final double MAX_VALUE = 9999999999999.99D;
        public static String change(double v) {
            if (v < 0 || v > MAX_VALUE){
                return "参数非法!";
            }
            long l = Math.round(v * 100);
            if (l == 0){
                return "零元整";
            }
            String strValue = l + "";
            // i用来控制数
            int i = 0;
            // j用来控制单位
            int j = UNIT.length() - strValue.length();
            String rs = "";
            boolean isZero = false;
            for (; i < strValue.length(); i++, j++) {
                char ch = strValue.charAt(i);
                if (ch == '0') {
                    isZero = true;
                    if (UNIT.charAt(j) == '亿' || UNIT.charAt(j) == '万' || UNIT.charAt(j) == '元') {
                        rs = rs + UNIT.charAt(j);
                        isZero = false;
                    }
                } else {
                    if (isZero) {
                        rs = rs + "零";
                        isZero = false;
                    }
                    rs = rs + DIGIT.charAt(ch - '0') + UNIT.charAt(j);
                }
            }
            if (!rs.endsWith("分")) {
                rs = rs + "整";
            }
            rs = rs.replaceAll("亿万", "亿");
            return rs;
        }



    public static void main(String[] args) throws IOException {
        System.out.println(Thread.currentThread().getName()) ;

        System.out.println(MD5Util.change(1111111111111.000));
    }
}