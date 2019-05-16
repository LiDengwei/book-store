package com.book.util;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * Created by OWk on 2016/2/20.
 */
public class RandomUtil {

    private static char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


    /**
     * 生产一定长度的随机数字字符串
     * @param bound
     * @param size
     * @return
     */
    public static String randomeNumStr(int bound,int size){
        SecureRandom random = new SecureRandom();
        return (random.nextInt(bound)+"00000000").substring(0,size);
    }

    /**
     * 生产一定长度的随机数字字符串
     * @param len
     * @return
     */
    public static String randomeNumStr(int len){
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<len;i++){
            stringBuilder.append(random.nextInt(9));
        }
        return stringBuilder.toString();
    }



    /**
     * 生产一定长度的随机字母数字字符串
     * @param size
     * @return
     */
    public static String randomeStr(int size){
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<size;i++){
            stringBuilder.append(codeSequence[random.nextInt(codeSequence.length)]);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        RandomUtil.randomeStr(10);
    }

    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
    }


    /**
     * 随机数字
     * @param num
     * @return
     */
    public static int randomNumber(Integer num){
        SecureRandom random = new SecureRandom();
        return num==null ? random.nextInt():random.nextInt(num);
    }


    /**
     * 随机Double数字
     * @return
     */
    public static Double randomDouble(){
        SecureRandom random = new SecureRandom();
        return random.nextDouble();
    }


}
