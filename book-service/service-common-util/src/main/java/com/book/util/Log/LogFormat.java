package com.book.util.Log;

import com.alibaba.fastjson.JSON;
import com.book.util.RealIpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class LogFormat {
    static short LOG_SERV_PORT = (short) 10000;
    static String LOG_SERV_HOST = null;
    static String DEFAULT_MODULE = "test";
    static boolean debug = false;

    static final Logger logger = LoggerFactory.getLogger(LogFormat.class);

    static {
//        LOG_SERV_HOST = Config.getInstance().getProperty("logserver.host");
//        String _port = Config.getInstance().getProperty("logserver.port");
//        debug = Boolean.valueOf(Config.getValue("logserver.debug"));
        LOG_SERV_HOST = "r4";
        LOG_SERV_HOST = "54.169.220.225";
        String _port = "10000";
        LOG_SERV_PORT = Short.parseShort(_port);
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
        if(debug){
            logger.info(modName+"-->"+str.toString());
            return;
        }
        LogServ.log(modName, str.toString(), LOG_SERV_HOST, LOG_SERV_PORT);
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
        if(debug){
            logger.info(modName+"-->"+str.toString());
            return;
        }
        LogServ.log(modName, str.toString(), LOG_SERV_HOST, LOG_SERV_PORT);
    }

    /**
     * @param modName 模块名称，代表日志文件夹
     * @param opName  操作名称
     * @param oper    操作
     * @param info    日志内容
     */
    public static void log(String modName, String opName, String oper, String info,Object[] objArray) {

        StringBuilder s = new StringBuilder();
        if(info!=null){
            s.append(info);
        }
        //打印更多内容
        if(objArray!=null) {
            for (Object o : objArray) {
                if (o instanceof String) {
                    s.append(o);
                } else if (o instanceof Number) {
                    s.append(o);
                } else if (o instanceof BigDecimal) {
                    s.append(o);
                } else {
                    s.append(JSON.toJSONString(o));
                }
                s.append("\n");
            }
        }
        log(modName,opName,oper,s.toString());
    }


    /**
     * @param modName
     * @param info
     */
    public static void writeLog(String modName, String info) {
        if(debug){
            logger.info(modName+"-->"+info);
            return;
        }
        LogServ.log(modName, info, LOG_SERV_HOST, LOG_SERV_PORT);
    }

    /**
     * @param opName
     * @param oper
     * @param info
     */
    public static void log(String opName, String oper, String info) {
        StringBuilder str = new StringBuilder(512);
        str.append(opName).append(" [").append(oper).append("] ").append(info.replaceAll("\\r|\\n", ""));
        if(debug){
            logger.info(DEFAULT_MODULE+"-->"+str.toString());
            return;
        }
        LogServ.log(DEFAULT_MODULE, str.toString(), LOG_SERV_HOST, LOG_SERV_PORT);
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
        s.append(ne.getMessage());
        for(StackTraceElement st: ne.getStackTrace()){
            s.append(st.toString()+"\n");
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


    public static void printLog(String log){
            logger.info(log);
    }

}
