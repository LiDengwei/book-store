package com.book.web;

public class BaseController {


    /**
     * 获取异常详细信息
     * @param ex
     * @author daniel 2018/9/7
     * @return
     */
    public String getErrorInfo(Exception ex){
        String error = "";
        StackTraceElement ste =ex.getStackTrace()[0];
        error = ",异常信息："+ex.getMessage()+",异常类："+ste.getClassName()+",异常方法："+ste.getMethodName()+",异常行数："+ste.getLineNumber();
        return error;
    }
}
