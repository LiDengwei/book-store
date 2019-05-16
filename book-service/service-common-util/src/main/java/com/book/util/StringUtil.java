package com.book.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by OWk on 2015/9/5.
 */
public class StringUtil {

    public static boolean isEmpty(String str){
        return str==null || str.trim().equals("");
    }

    public static boolean isEmpty(Object o){
        if(o==null){
            return true;
        }
        if(o instanceof  String){
            return  o.equals("");
        }
        return false;
    }

    public static int intVal(String s){
        if(s==null){
            return 0;
        }
        return Integer.parseInt(s);
    }


    /**
     * 截取字符串
     * @param content
     * @param len
     * @return
     */
    public static String subString(String content,int len,boolean isEnd){
        if(isEmpty(content)){
            return "";
        }
        else{
            if(content.length()<len){
                return content;
            }
            else{
                return content.substring(isEnd ? content.length()-len:0);
            }
        }
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    public static boolean isNotEmpty(Object obj){
        return obj==null ? false:!(isEmpty(obj));
    }

    public static Long[] splitLong(String arrayStr,String spx){
        String[] array = arrayStr.split(spx);
        if(array!=null){
            Long[] longArray = new Long[array.length];
            for(int i=0;i<longArray.length;i++){
                longArray[i] = Long.parseLong(array[i]);
            }
            return longArray;
        }
        return null;
    }

    public static Short[] splitShort(String arrayStr,String spx){
        String[] array = arrayStr.split(spx);
        if(array!=null){
            Short[] shortArray = new Short[array.length];
            for(int i=0;i<shortArray.length;i++){
                shortArray[i] = Short.parseShort(array[i]);
            }
            return shortArray;
        }
        return null;
    }

    public static String toFilrstUpCase(String str){
        return str.substring(0,1).toUpperCase()+str.substring(1);
    }

    public static String toFilrstLowerCase(String str){
        return str.substring(0,1).toLowerCase()+str.substring(1);
    }


    public static String hideUserName(String username){
        if(isEmpty(username)){
            return "";
        }
        int len = username.length();
        String newName = "";
        if(len>=11){
            newName = username.substring(0,3)+"***"+username.substring(username.length()-4);
        }
        else{
            newName = username.substring(0,len-4)+"***";
        }
        return newName;
    }

    public static String hideMobile(String mobile){
        if(mobile!=null && mobile.length()>6){
            String newMobile = mobile.substring(0,3)+"***"+mobile.substring(mobile.length()-4);
            return newMobile;
        }
        return null;
    }



    /**
     * 是否是double类型
     * @param value
     * @return
     */
    public static boolean isDouble(String value){
        try{
            double b = Double.parseDouble(value);
            if (b==0){
                return false;
            }
        }catch(Exception e){
            return false;
        }
        return true;
    }

    /**
     * 是否是Long类型
     * @param value
     * @return
     */
    public static boolean isLong(String value){
        try{
            new Long(value);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    /**
     * 判断是否为数字  只能判断字符串是否全是数字  不支持小数和负数
     *
     * @param str 传入的字符串
     * @return 是数字返回true, 否则返回false
     */
    public static boolean isNumeric(String str){
        if (str == null)return false;
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否为数字  用正则表达式 支持小数和负数
     *
     * @param str 传入的字符串
     * @return 是数字返回true, 否则返回false
     */
    public static boolean isNumerType(String str){
        return str.matches("-?[0-9]+.*[0-9]*");
    }

    /**
     *  判断字符串是否为数字 包括整数 浮点
     * @param str 要判断的字符串
     * @return 是数字返回true
     */
    public static Boolean isNumber(String str){
        if(str!=null){
            try{
                new BigDecimal(str);
            }
            catch (Exception e){
                return false;
            }
            return true;
        }
        return false;
    }

    public static double[] getPersent(int value){
        if (value==1){
            double[] num={
                    13,13.1,13.2,13.3,13.4,13.5,13.6,13.7,13.8,13.9,
                    15,
                    14,14.1,14.2,14.3,14.4,14.5,14.6,14.7,14.8,14.9,
                    14,14.1,14.2,14.3,14.4,14.5,14.6,14.7,14.8,14.9,
                    13,13.1,13.2,13.3,13.4,13.5,13.6,13.7,13.8,13.9,
                    15,
                    15,
                    15,
                    13,13.1,13.2,13.3,13.4,13.5,13.6,13.7,13.8,13.9,
                    13,13.1,13.2,13.3,13.4,13.5,13.6,13.7,13.8,13.9,
                    13,13.1,13.2,13.3,13.4,13.5,13.6,13.7,13.8,13.9,
                    14,14.1,14.2,14.3,14.4,14.5,14.6,14.7,14.8,14.9,
                    13,13.1,13.2,13.3,13.4,13.5,13.6,13.7,13.8,13.9,
                    13,13.1,13.2,13.3,13.4,13.5,13.6,13.7,13.8,13.9,
                    14,14.1,14.2,14.3,14.4,14.5,14.6,14.7,14.8,14.9,
                    13,13.1,13.2,13.3,13.4,13.5,13.6,13.7,13.8,13.9,
                    14,14.1,14.2,14.3,14.4,14.5,14.6,14.7,14.8,14.9,
                    14,14.1,14.2,14.3,14.4,14.5,14.6,14.7,14.8,14.9,
                    14,14.1,14.2,14.3,14.4,14.5,14.6,14.7,14.8,14.9,
                    14,14.1,14.2,14.3,14.4,14.5,14.6,14.7,14.8,14.9
            };
            return num;
        }else if (value==2){
            double[] num={
                    9,9.1,9.2,9.3,9.4,9.5,9.6,9.7,9.8,9.9,
                    11,
                    10,10.1,10.2,10.3,10.4,10.5,10.6,10.7,10.8,10.9,
                    10,10.1,10.2,10.3,10.4,10.5,10.6,10.7,10.8,10.9,
                    9,9.1,9.2,9.3,9.4,9.5,9.6,9.7,9.8,9.9,
                    11,
                    11,
                    11,
                    9,9.1,9.2,9.3,9.4,9.5,9.6,9.7,9.8,9.9,
                    9,9.1,9.2,9.3,9.4,9.5,9.6,9.7,9.8,9.9,
                    9,9.1,9.2,9.3,9.4,9.5,9.6,9.7,9.8,9.9,
                    10,10.1,10.2,10.3,10.4,10.5,10.6,10.7,10.8,10.9,
                    9,9.1,9.2,9.3,9.4,9.5,9.6,9.7,9.8,9.9,
                    9,9.1,9.2,9.3,9.4,9.5,9.6,9.7,9.8,9.9,
                    10,10.1,10.2,10.3,10.4,10.5,10.6,10.7,10.8,10.9,
                    9,9.1,9.2,9.3,9.4,9.5,9.6,9.7,9.8,9.9,
                    10,10.1,10.2,10.3,10.4,10.5,10.6,10.7,10.8,10.9,
                    10,10.1,10.2,10.3,10.4,10.5,10.6,10.7,10.8,10.9,
                    10,10.1,10.2,10.3,10.4,10.5,10.6,10.7,10.8,10.9,
                    10,10.1,10.2,10.3,10.4,10.5,10.6,10.7,10.8,10.9
            };
            return num;
        }else {
            return null;
        }
    }

    public static String hideZerro(String str){
        if(str!=null){
            return str.replaceAll("[0]+$","");
        }
        return str;
    }

    public static long parseLong(String str) {
        if (str != null) {
            try {
                return Long.parseLong(str);
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }

    public static boolean isOctNumberRex(String str){
        String validate = "\\d+";
        return str.matches(validate);
    }
    public static boolean isHexNumberRex(String str){
        String validate = "(?i)[0-9a-f]+";
        return str.matches(validate);
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     * @param version1 版本1
     * @param version2 版本2
     * @return
     */
    public static int compareVersion(String version1, String version2)  {
        try {
            if (version1 == null || version2 == null) {
                throw new Exception("compareVersion error:illegal params.");
            }
            String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
            String[] versionArray2 = version2.split("\\.");
            int idx = 0;
            int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
            int diff = 0;
            while (idx < minLength
                    && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                    && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
                ++idx;
            }
            //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
            diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
            return diff;
        } catch (Exception e) {
            return 1;
        }
    }


    /**
     * 查询符合的手机号码
     * @param str
     */
    public static String checkCellphone(String str){
        // 将给定的正则表达式编译到模式中
        Pattern pattern = Pattern.compile("((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}");
        // 创建匹配给定输入与此模式的匹配器。
        Matcher matcher = pattern.matcher(str);
        //查找字符串中是否有符合的子字符串
        String result="";
        while(matcher.find()){
            //查找到符合的即输出
            result= matcher.group();
        }
        return result;
    }

    public static int getSubNum(String a,String b){
        int num=0;
        String str=a;
        int index=a.indexOf(b);
        while(index!=-1){
            num++;
            str=str.substring(index+b.length()-1);
            index=str.indexOf(b);
        }
        return num;
    }

    public static String handleEmoji(String str){
        if(StringUtil.isNotEmpty(str)){
            if (str.indexOf("span>") > 0) {
                if(StringUtil.isNotEmpty(str)){
                    int num = StringUtil.getSubNum(str,"span>");
                    System.out.println("nickName num："+ num);
                    if(num >0){
                        for(int i=0 ;i<num;i++){
                            if(str.indexOf("span>") > 0){
                                String nickNameSource = str;
                                str=nickNameSource.substring(0,nickNameSource.indexOf("<span"))+nickNameSource.substring(nickNameSource.indexOf("span>")+5);
                            }
                        }
                    }
                }
            }
        }
        return str;
    }



    /**
     * 随机指定范围内N个不重复的数
     * 最简单最基本的方法
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n 随机数个数
     */
    public static int[] randomCommon(int min, int max, int n){
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while(count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        return result;
    }

    /**
     * 给用户名加密 增加*
     * @param userName 用户名
     * @return
     */
    public  static String getSecretUserName(String userName) {
        if (userName.length() > 2) {
            String pre = userName.substring(0, 2);//用户名前2个字符
            String back = userName.substring(userName.length() - 2, userName.length());//用户名后2个字符
            userName = pre + "****" + back;
        }
        return userName;
    }
}
