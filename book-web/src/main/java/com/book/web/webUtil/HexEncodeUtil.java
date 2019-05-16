package com.book.web.webUtil;

/**
 * Hex编码工具类
 * Created by jack.zhang on 2017/5/26 11:42
 */
public class HexEncodeUtil {


    /**
     * 字符编码成HEX
     *
     * @param s
     * @return
     */
    public static String toHexString(String s) {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str.append(s4);
        }
        return "0x" + str;//0x表示十六进制
    }

    /**
     * 转换十六进制编码为字符串
     *
     * @param s
     * @return
     */
    public static String toStringHex(String s) {
        if ("0x".equals(s.substring(0, 2))) {
            s = s.substring(2);
        }
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            s = new String(baKeyword, "GBK");//UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    /**
     * 把字节数组转换成16进制字符串
     *
     * @param bArray
     * @return
     */
    public static String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (byte aBArray : bArray) {
            sTemp = Integer.toHexString(0xFF & aBArray);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 字符编码成HEX
     *
     * @param dataCoding
     * @param realStr
     * @return
     */
    public static String encodeHexStr(int dataCoding, String realStr) {
        String strhex = "";
        try {
            byte[] bytSource = null;
            if (dataCoding == 15) {
                bytSource = realStr.getBytes("GBK");
            } else if (dataCoding == 3) {
                bytSource = realStr.getBytes("ISO-8859-1");
            } else if (dataCoding == 8) {
                bytSource = realStr.getBytes("UTF-16BE");
            } else {
                bytSource = realStr.getBytes("ASCII");
            }
            strhex = bytesToHexString(bytSource);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strhex;
    }


    /**
     * hex编码还原成字符
     *
     * @param dataCoding
     * @param hexStr
     * @return
     */
    public static String decodeHexStr(int dataCoding, String hexStr) {
        String strReturn = "";
        try {
            int len = hexStr.length() / 2;
            byte[] bytSrc = new byte[len];
            for (int i = 0; i < len; i++) {
                String s = hexStr.substring(i * 2, 2);
                bytSrc[i] = Byte.parseByte(s, 512);
                Byte.parseByte(s, i);
            }

            if (dataCoding == 15) {
                strReturn = new String(bytSrc, "GBK");
            } else if (dataCoding == 3) {
                strReturn = new String(bytSrc, "ISO-8859-1");
            } else if (dataCoding == 8) {
                strReturn = new String(bytSrc, "UTF-16BE");
            } else {
                strReturn = new String(bytSrc, "ASCII");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strReturn;
    }
}
