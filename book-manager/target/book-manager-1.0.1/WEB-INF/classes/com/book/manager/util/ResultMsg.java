package com.book.manager.util;

import com.book.exceptions.APIException;

import java.io.Serializable;

/**
 * Created by datangwallet
 */
public class ResultMsg implements Serializable{

    private String status=FALI;
    private String resultCode;
    private String msg;
    private Object data;
    private String errorcode;


    //status类型
    public static final String SUCCESS = "success";
    public static final String FALI = "failed";

    public void success(){
        this.status = SUCCESS;
    }

    public boolean isSuccess(){
        return status.equals(SUCCESS);
    }

    public void fail(){
        this.status = FALI;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultCode(APIException.ErrorCode resultCode) {
        this.resultCode = resultCode.name();
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }
}
