package com.book.util;


import io.goeasy.GoEasy;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class GoEasyPublish {

    public static final String SECRET_KEY = "5fd46b20ef0a4f47" ;
    private static final String REST_KEY = "PR-80d608b59f44465f9a5df73d6fd5ac66" ;
    private static final String CLIENT_KEY = "PC-d1c8f6d8ba974add91826af380309d5e" ;
    private static final String GLOAB_SERVICE = "http://rest-singapore.goeasy.io/publish" ;

    private static GoEasy goEasy = new GoEasy(GLOAB_SERVICE,REST_KEY) ;
    public static GoEasy getInstance(){
        if(goEasy == null){
            goEasy = new GoEasy(GLOAB_SERVICE,REST_KEY) ;
            return goEasy ;
        }
        return goEasy ;
    }

    // 发送消息
    public static boolean sendMessage(String message){
        try{
            String userName = message.split(":")[1] ;
            goEasy.publish("message_remind_"+userName ,message) ;
        }catch(Exception e){
            return false ;
        }
        return true ;
    }

    // 后台重启提醒推送
    public static void restartTip(String message){
        goEasy.publish("manager_restart",message);
    }
    // 转出发送提示消息
    public static boolean sendInfo(String message){
        try{
            goEasy.publish("manager_orders" ,message) ;
        }catch(Exception e){
            return false ;
        }
        return true ;
    }
    // 提现订单提示消息
    public static boolean sendOrderInfo(String message){
        try{
            goEasy.publish("manager_drawAuditing" ,message) ;
        }catch(Exception e){
            return false ;
        }
        return true ;
    }
    // 通过服务器秘钥生成一次性登录密码
    public static String goEasyOTP(String secretKey){
        try {
            String otp = "000" + System.currentTimeMillis();
            SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            byte[] otpBytes = otp.getBytes();
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedOTP = cipher.doFinal(otpBytes);
            otp = new BASE64Encoder().encode(encryptedOTP);
            return otp;
        } catch (Exception e) {
            throw new IllegalStateException("Failed to generate GoEasy-OTP.", e);
        }
    }

    public static void main (String [] str){
        //GoEasyPublish.sendMessage("MANAGER_RECORD:admin");
//        GoEasyPublish.restartTip("MANAGER_RESTART");
        GoEasyPublish.sendOrderInfo("manager_drawAuditing");

    }

}
