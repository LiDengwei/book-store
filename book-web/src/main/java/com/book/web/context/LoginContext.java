package com.book.web.context;



import com.book.util.DateUtil;
import com.book.util.Log.LogFormat;

public class LoginContext {
    private String ip;
//    private User user;
    private String language;
    private Exception ex;
    private StringBuilder logerBuilder = new StringBuilder();//日志
    private Short logType;//日志类型

//    public LoginContext(User user){
//        this.user = user;
//    }
//    public boolean isLogin(){
//        return user == null ? false : true;
//    }


    public enum LogTypeEnum{
        WEB(1),TASK(2);
        short value;
        public short getValue() {
            return value;
        }
        LogTypeEnum(int i) {
            this.value = (short)i;
        }
    }

    /**
     * 添加日志条目
     * @param oper
     * @param log
     */
    public void addLog(String oper,String log){
        String userName =  "noLogin";
        long pid = Thread.currentThread().getId();
        logerBuilder.append(DateUtil.getCurrenyDateStr()+"("+pid+") ["+userName+"@"+ip+"] --"+oper+"-- "+log+"\n");
    }

    /**
     * 写context日志
     */
    public void writeLog(){
        LogFormat.printLog(logerBuilder.toString());
    }

    public void error(Exception ex){
        if(ex!=null){
            this.ex = ex;
            StringBuilder ne = new StringBuilder();
            ne.append(ex.getMessage());
            for(StackTraceElement st: ex.getStackTrace()){
                ne.append(st.toString()+"\n");
            }
            addLog("Has Error",ne.toString());
        }

    }


//    public User getLoginUser(){
//        return user;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public Exception getEx() {
        return ex;
    }

    public void setEx(Exception ex) {
        this.ex = ex;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public StringBuilder getLogerBuilder() {
        return logerBuilder;
    }

    public void setLogerBuilder(StringBuilder logerBuilder) {
        this.logerBuilder = logerBuilder;
    }

    public Short getLogType() {
        return logType;
    }

    public void setLogType(Short logType) {
        this.logType = logType;
    }
}
