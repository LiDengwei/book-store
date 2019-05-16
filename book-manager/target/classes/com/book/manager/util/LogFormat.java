package com.book.manager.util;


import com.book.manager.base.LoginContext;
import com.book.manager.base.LoginContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class LogFormat {
    static short LOG_SERV_PORT = (short) 10000;
    static String LOG_SERV_HOST = null;
    static String DEFAULT_MODULE = "test";
    static boolean debug = false;

    static final Logger logger = LoggerFactory.getLogger("contextLogger");
    static final Logger taskLogger = LoggerFactory.getLogger("taskLogger");
    static final Logger errorLogger = LoggerFactory.getLogger("errorLogger");


    public static void printLog(String log){
        if(LoginContextHolder.getContext()==null){
            logger.info(log);
        }
        else if(LoginContextHolder.getContext().getLogType()== LoginContext.LogTypeEnum.WEB.getValue()){
            logger.info(log);
        } else if(LoginContextHolder.getContext().getLogType()==LoginContext.LogTypeEnum.TASK.getValue()){
            taskLogger.info(log);
        }
        if(LoginContextHolder.getContext().getEx()!=null){
            errorLogger.error(log,LoginContextHolder.getContext().getEx());
        }
    }


    /**
     * @param modName
     * @param opName
     * @param oper
     * @param info
     */
    public static void log(String modName, String opName, String oper, String info) {
        StringBuilder str = new StringBuilder(512);
        str.append(opName).append(" [")
                .append(oper).append("] ").append(info.replaceAll("\\r|\\n", ""));
        printLog(modName + "-->" + str.toString());
    }


    /**
     * @param modName
     * @param info
     */
    public static void writeLog(String modName, String info) {
        printLog(modName + "-->" + info);
    }

    /**
     * @param modName
     * @param info
     */
    public static void writeTaskLog(String modName, String info) {
        printLog(modName + "-->" + info);
    }


    /**
     * @param modName
     * @param opName
     * @param oper
     * @param info
     */
    private static void logError(String modName, String opName, String oper, String info) {
        StringBuilder str = new StringBuilder(512);
        str.append(opName).append(" [")
                .append(oper).append("] ").append(info);
        printLog(modName + "-->" + str.toString());
    }


    /**
     * @param opName
     * @param oper
     * @param info
     */
    public static void log(String opName, String oper, String info) {
        StringBuilder str = new StringBuilder(512);
        str.append(opName).append(" [").append(oper).append("] ").append(info.replaceAll("\\r|\\n", ""));
        printLog(DEFAULT_MODULE + "-->" + str.toString());
    }




//    private static void log(String modName, String opName, String oper, String ip, String info,HttpServletRequest request) {
//        try {
//            log(modName, opName, oper + " @" + ip, info);
//        }catch (Exception e){
//            logger.error("LogFormat:"+e.getMessage(),e);
//        }
//    }

    /**
     * @param modName 模块名称，代表日志文件夹
     * @param opName  操作名称
     * @param oper    操作
     * @param info    日志内容
     * @param request http请求 可以为空
     */
    public static void log(String modName, String opName, String oper, String info,HttpServletRequest request) {
        try {
            if(request!=null){
                String ip = RealIpUtils.getClientIP(request);
                log(modName, opName, oper + " @" + ip, info);
            }
            else{
                log(modName, opName, oper, info);
            }
        }catch (Exception e){
            logger.error("LogFormat:"+e.getMessage(),e);
        }
    }


    public static void error(String modName, String opName, String oper, String info,Exception ne,HttpServletRequest request){
        StringBuilder s = new StringBuilder();
        if(ne!=null){
            s.append(ne.getMessage());
            for(StackTraceElement st: ne.getStackTrace()){
                s.append(st.toString()+"\n");
            }
        }
        String ip = "***";
        if(request!=null) {
            ip = RealIpUtils.getClientIP(request);
        }
        try {
            logError(modName + "_error", opName, oper + " @" + ip, info + "\n" + s.toString());
        }catch (Exception e){
            logger.error("LogFormat:"+e.getMessage(),e);
        }
    }

//    public static void log(String modName, String opName, String oper, String ip, String params, String res,HttpServletRequest request) {
//        try{
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("params",params);
//            jsonObject.put("res",res);
//            log(modName, opName, oper + " @" + ip, jsonObject.toString());
//        }catch (Exception e){
//            logger.error("LogFormat:"+e.getMessage(),e);
//        }
//    }


}
