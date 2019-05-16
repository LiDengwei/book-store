package com.book.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc : 服务返回信息
 * @dae : 2016/12/15
 */
public class ServiceResultMsg implements Serializable {
    private String status;
    private String resultCode;
    private String msg;
    private String msgKey;
    private Object data;
    private Map mapData;



    //status类型
    public static final String SUCCESS = "success";
    public static final String FALI = "failed";

    public void success(){
        this.status = SUCCESS;
    }

    public boolean resultIsSuccess(){
        return status.equals(SUCCESS);
    }

    public void fail(){
        this.status = FALI;
    }

    public void putData(String key,Object value){
        if(mapData==null){
            mapData = new HashMap<String,Object>();
        }
        mapData.put(key,value);
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

    public Map getMapData() {
        return mapData;
    }

    public void setMapData(Map mapData) {
        this.mapData = mapData;
    }

    public String getMsgKey() {
        return msgKey;
    }

    public void setMsgKey(String msgKey) {
        this.msgKey = msgKey;
    }
}
