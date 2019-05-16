package com.book.manager.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * Created by $_Mai on 2018/5/22.
 */
@Controller
@RequestMapping(value = "/public",produces = "application/json;charset=utf-8")
public class PublicEnumValueController {
    private static final Log logger = LogFactory.getLog(PublicEnumValueController.class) ;

    /**
     * 获取枚举值
     * @param classPackage 包名称，多个包使用 ; 间隔
     * @param flag true时下拉框需要全部 false不需要全部
     * @param state 包类型 0:manager包实体 1:service 类实体
     */
    @RequestMapping(value = "/enumValue",method = {RequestMethod.POST})
    @ResponseBody
    public JSONObject enumValue(String classPackage,boolean flag,Integer state){
        JSONObject jsonObject = new JSONObject() ;
        String basePackage = null ;
        if(state.equals(1)){
            basePackage = "com.book.service.model." ;
        }else{
            basePackage = "com.book.manager.domain.security." ;
        }
        try {
            String[] classes = classPackage.split(";") ;
            for(String className : classes){
                Class clazz = Class.forName(basePackage+className) ;
                Enum[] enums = (Enum[]) clazz.getEnumConstants(); // 通过枚举构造函数，获取枚举对象
                String[] keyNames = className.split("\\$") ;
                if(!Objects.isNull(enums)){
                    JSONArray jsonArray = new JSONArray() ;
                    JSONObject keyObject = new JSONObject() ;
                    if(flag){
                        keyObject.put("id","selected") ;
                        keyObject.put("text","全部") ;
                        jsonArray.add(keyObject) ;
                    }
                    for(Enum enumValue : enums){
                        String value = enumValue.toString() ;
                        String[] toString = value.split(":") ;
                        JSONObject valueObject = new JSONObject() ;
                        valueObject.put("id",toString[0]) ;
                        valueObject.put("text",toString[1]) ;
                        jsonArray.add(valueObject) ;
                    }
                    jsonObject.put(keyNames[1],jsonArray) ;
                }
            }
        }catch (Exception e){
            logger.info("服务器异常:" + e.getMessage());
        }

        return jsonObject ;
    }

    public static void main(String[] args) {
        PublicEnumValueController publicEnumValueController = new PublicEnumValueController() ;
        String packageName = "user.Transaction$CallbackEnum" ;
        JSONObject jsonObject = publicEnumValueController.enumValue(packageName,true,1) ;
        System.out.println(jsonObject);
    }

}
