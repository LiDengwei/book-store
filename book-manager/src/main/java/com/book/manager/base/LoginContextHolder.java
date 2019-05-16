package com.book.manager.base;


import com.book.manager.domain.security.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * To change this template use File | Settings | File Templates.
 */
public class LoginContextHolder {

    private static Logger logger = LoggerFactory.getLogger(LoginContextHolder.class);

    private static final ThreadLocal<LoginContext> holder = new ThreadLocal<LoginContext>();

    public static void set(LoginContext context){
        if(context != null){
            holder.set(context);
        }
    }

    public static LoginContext getContext(){
        return holder.get();
    }

    public static void remove(){
        holder.remove();
    }

    public static boolean isLogin(){
        LoginContext context = getContext();
        if(context == null){
            return false;
        }
        return context.isLogin();
    }


    /**
     * 是否有异常
     * @return
     */
    public static boolean isError(){
        LoginContext context = getContext();
        if(context == null){
            return false;
        }
        return context.getEx()!=null;
    }

    public static void addLog(String mod,String log){
        LoginContext context = getContext();
        if(context != null){
            context.addLog(mod,log);
        }
        else{
            logger.info("["+mod+"]"+log);
        }
    }

    public static void error(String mod,String log,Exception e){
        LoginContext context = getContext();
        if(context != null){
            context.error(e);
        }
        else{
            logger.error("["+mod+"]"+log,e);
        }
    }




    public static void addLog(String log){
        LoginContext context = getContext();
        if(context != null){
            context.addLog("log",log);
        }
    }


    /**
     * 写context日志
     */
    public static void writeLog(){
        LoginContext context = getContext();
        if(context != null){
            context.writeLog();
        }
    }

    public static UserEntity getLoginUser(){
        LoginContext context = getContext();
        if(context == null || !context.isLogin()){
            return null;
        }
        return context.getLoginUser();
    }
}
