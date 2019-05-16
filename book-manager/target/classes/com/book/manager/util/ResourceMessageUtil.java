package com.book.manager.util;

import com.book.common.Constant;
import com.book.manager.base.LoginContextHolder;
import com.book.util.XmlHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by daniel on 2017/12/27.
 */
public class ResourceMessageUtil {

    private static Map<String,String> cnMap = new ConcurrentHashMap<String,String>();
    private static Map<String,String> enMap = new ConcurrentHashMap<String,String>();
    private static Map<String,String> cnTwMap = new ConcurrentHashMap<String,String>();
    private static final Logger logger = LoggerFactory.getLogger(ResourceMessageUtil.class);


    static{
        try{
            String resourcePath = ResourceMessageUtil.class.getResource("/i18n").getPath();
//            String resourcePath ="";
//            logger.info("resourcePath====="+resourcePath);

            String cnContent = "";
            String enContent = "";
            String cn_twContent = "";
            //加载CN的内容
            InputStream inputStream=ResourceMessageUtil.class.getClassLoader().getResourceAsStream("i18n/cn.xml");
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
                cnContent = new String();
                String str = null;
                while ((str = reader.readLine())!=null){
                    cnContent+=str;
                }
            }catch(Exception e){
                logger.info("Read exclude cn.xml file error, message is "+e.getMessage());
            }

            cnMap = new ConcurrentHashMap<String,String>();
            Map<String,String> resMap = XmlHelper.readStringXmlAsOneMap(cnContent);
            cnMap.putAll(resMap);
            Map cnMapNew = new ConcurrentHashMap<String,String>();
            for(Map.Entry<String,String> entry:cnMap.entrySet()){
                cnMapNew.put(entry.getKey().toLowerCase(),entry.getValue());
            }
            cnMap = new ConcurrentHashMap<String,String>();
            cnMap.putAll(cnMapNew);

            //加载EN的内容
            InputStream inputStreamEn=ResourceMessageUtil.class.getClassLoader().getResourceAsStream("i18n/en.xml");
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStreamEn,"utf-8"));
                enContent = new String();
                String str = null;
                while ((str = reader.readLine())!=null){
                    enContent+=str;
                }
            }catch(Exception e){
                logger.info("Read exclude en.xml file error, message is "+e.getMessage());
            }

            enMap = new ConcurrentHashMap<String,String>();
            resMap = XmlHelper.readStringXmlAsOneMap(enContent);
            enMap.putAll(resMap);
            Map enMapNew = new ConcurrentHashMap<String,String>();
            for(Map.Entry<String,String> entry:enMap.entrySet()){
                enMapNew.put(entry.getKey().toLowerCase(),entry.getValue());
            }
            enMap = new ConcurrentHashMap<String,String>();
            enMap.putAll(enMapNew);

            //加载CN_TW的内容
            //加载EN的内容
            InputStream inputStreamCnTw=ResourceMessageUtil.class.getClassLoader().getResourceAsStream("i18n/cn_tw.xml");
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStreamCnTw,"utf-8"));
                cn_twContent = new String();
                String str = null;
                while ((str = reader.readLine())!=null){
                    cn_twContent+=str;
                }
            }catch(Exception e){
                logger.info("Read exclude cn_tw.xml file error, message is "+e.getMessage());
            }

            cnTwMap = new ConcurrentHashMap<String,String>();
            resMap = XmlHelper.readStringXmlAsOneMap(cn_twContent);
            cnTwMap.putAll(resMap);
            Map cnTwMapNew = new ConcurrentHashMap<String,String>();
            for(Map.Entry<String,String> entry:cnTwMap.entrySet()){
                cnTwMapNew.put(entry.getKey().toLowerCase(),entry.getValue());
            }
            cnTwMap = new ConcurrentHashMap<String,String>();
            cnTwMap.putAll(cnTwMapNew);


            LogFormat.writeLog("context_log", "国际化资源文件加载成功 en:" + enMap.size() + " cn:" + cnMap.size()+ " cn_tw:" + cnTwMap.size());
        }
        catch (Exception e){
            LogFormat.error("context_log","new ResourceMessageServiceImpl","error","国际化资源文件加载失败",e,null);
        }



    }

    public static String getMessage(String language,String key) {
//        String language = null;
//        if(LoginContextHolder.getContext()!=null){
//            language = LoginContextHolder.getContext().getLanguage();
//        }
        if(null == language){
            language=Constant.CN;
        }
        String msg = null;
        if(key!=null){
            key= key.toLowerCase();
        }
        if(Constant.EN.equals(language)){
            msg = enMap.get(key);
        }
        else if(Constant.CN.equals(language)){
            msg = cnMap.get(key);
        }
        else{
            msg = cnMap.get(key);
            LoginContextHolder.getContext().addLog("getMessage", "language not found,return en,language:" + language);
        }

        if(msg==null){
            if(key.length()>100){
                msg = getMessage(language,"caozuoshibai_snd");
            }else{
                msg = key;
            }
            LoginContextHolder.getContext().addLog("getMessage", "key not found,return key,key:"+key);
        }
        return msg;
    }

    public static void main(String[] args) {
        System.out.println(getMessage("EN","operate_more"));
    }

}
