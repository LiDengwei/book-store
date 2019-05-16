package com.book.common;

import com.book.exceptions.APIException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jfb
 */
public class ResultMsg implements Serializable{

    private String status=FALI;
    private String resultCode="";
    private String msg;
    private Object data;
    private Map mapData;
    private String errorcode;
    private String state=FALI;


    //status类型
    public static final String SUCCESS = "success";
    public static final String FALI = "failed";

    public enum ResultCodeType{
        //支付
        PAYMENT("101");
        String value;

        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
        ResultCodeType(String i) {
            this.value = i;
        }

    }

    public void success(){
        this.status = SUCCESS;
        this.state = SUCCESS;
    }

    public void setStateSuccess(){
        this.state = SUCCESS;
    }

    public void setStateFailed(){
        this.state = FALI;
    }

    public boolean isSuccess(){
        return status.equals(SUCCESS);
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


    public void setResultCode(APIException.ErrorCode code) {
        this.resultCode = code.name();
    }



    public Map getMapData() {
        return mapData;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public void setMapData(Map mapData) {
        this.mapData = mapData;
    }
}
