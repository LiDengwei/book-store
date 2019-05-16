package com.book.util;


import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OWk on 2016/3/20.
 */
public class FileUtil {
    private static final Logger logger = Logger.getLogger(FileUtil.class);
    public static void bakFile(String filePath,String backPath){
        String content = readFile(filePath);

        content = "\n"+ DateUtil.getCurrenyDateStr()+" "+filePath+" BAK\n"+content;
        File file = new File(backPath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readDirNameList(String path){
        logger.info("path==="+path);
        File file = new File(path);
        List<String> list = new ArrayList<String>();
        for(File f:file.listFiles()){
            list.add(f.getName());
        }
        return list;
    }

    public static void createDirIfNotExist(String path){

        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    public static long getFileLastUpdate(String filePath){
        File file = new File(filePath);
        if(!file.exists()){
            return 0;
        }
        return file.lastModified();
    }

    public static void writeFile(String filePath,String content)throws IOException {
        String path = filePath;
        File file = new File(path);

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false),"utf-8"));
        bufferedWriter.write(content);
        bufferedWriter.close();
    }

    public static void writeFileWithAppend(String filePath,String content)throws IOException {
        String path = filePath;
        File file = new File(path);

        FileWriter fileWriter = new FileWriter(file,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(content);
        bufferedWriter.close();
    }


    public static boolean isExists(String filePath){
        File file = new File(filePath);
        return file.exists();
    }

    public static String readFile(String filePath) {
        String path = filePath;
        File file = new File(path);
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        try {
            while((line=bufferedReader.readLine())!=null){
                if(stringBuilder.length()>0){
                    stringBuilder.append("\n");
                }
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            return new String(stringBuilder.toString().getBytes(),"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        return stringBuilder.toString();
    }


}
