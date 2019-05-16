package com.book.util;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by OWk on 2015/11/13.
 */

public class Config {
    private static final Logger logger = Logger.getLogger(Config.class);
    Properties config = new Properties();
    private static Config instance = new Config();
    private static String xml;
    private static Map<String,Object> sysParameter = new ConcurrentHashMap<String,Object>();
    private static Long time = 0L;
    private static String parameterPath = Config.getValue("parameter_path");

    public Config() {
        Class var1 = Config.class;
        synchronized(Config.class) {
            this.loadProperties();
        }
    }

    private void loadProperties() {
        String[] fileNames = new String[]{"config.properties"};
        String[] var2 = fileNames;
        int var3 = fileNames.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String fileName = var2[var4];
            InputStream in = this.getClass().getResourceAsStream("/" + fileName);

            try {
                if(in != null) {
                    this.config.load(in);
                }
            } catch (IOException var8) {
                var8.printStackTrace();
            }
        }

    }

    public static String copyToString(InputStream in, String charset) throws IOException {
        StringBuilder out = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(in, charset);
        char[] buffer = new char[4096];
        boolean bytesRead = true;

        int bytesRead1;
        while((bytesRead1 = reader.read(buffer)) != -1) {
            out.append(buffer, 0, bytesRead1);
        }

        return out.toString();
    }


    /**
     * 获取配置文件的值
     * @param key
     * @return
     */
    public static String getValue(String key){
        return Config.getInstance().getProperty(key);
    }

    /**
     * 获取配置文件的值
     * @param key
     * @return
     */
    public static Object getSysParameter(String key){
        if((System.currentTimeMillis()-time)>60000){
            instance.loadSysPrammeter();
        }
        return sysParameter.get(key);
    }

    /**
     * 获取配置文件的值
     * @return
     */
    public static Map getSysParameter(){
        if((System.currentTimeMillis()-time)>60000){
            loadSysPrammeter();
        }
        return sysParameter;
    }


    private static  void loadSysPrammeter() {
        if(StringUtil.isEmpty(parameterPath)){
            String fileName = "parameter";
            parameterPath = Config.class.getResource("/" + fileName).getPath();
        }
        logger.info("parameterPath==="+parameterPath);
        sysParameter = new ConcurrentHashMap<String,Object>();
        List<String> list = FileUtil.readDirNameList(parameterPath);
        for(String s:list){
            String dirPath = parameterPath+ File.separator+s+File.separator;
            List<String> filelist = FileUtil.readDirNameList(dirPath);

            Map paramMap = new ConcurrentHashMap<String,Object>();

            for(String f:filelist){
                String filePath = dirPath+f;
                System.out.println(filePath);
                String content = FileUtil.readFile(filePath);
                System.out.println(content);
                Object object = JSON.parse(content);
                if(f.indexOf("update.json")>=0){
                    paramMap.putAll((Map)object);
                }
                else if(f.indexOf("data.json")>=0){
                    paramMap.putAll((Map)object);
                }
                else{
                    String lan = f.substring(6,f.indexOf("."));
                    paramMap.put(lan,object);
                }
            }
            sysParameter.put(s,paramMap);
        }
        time = System.currentTimeMillis();

    }

    public static Config getInstance() {
        return instance;
    }

    public String getProperty(String key) {
        return this.config.getProperty(key);
    }


}

