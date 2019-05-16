package com.book.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

public class XMLConfig {
	private static final Log log = LogFactory.getLog(XMLConfig.class);
	private static String excludeFile = "exclude.xml";
	private static String xml;
	private static XMLConfig instance;
	public static XMLConfig getInstance(){
		if (instance == null) {
			synchronized (XMLConfig.class) {
				if (instance == null) {
					instance = new XMLConfig();
				}
			}
		}
		return instance;
	}
	XMLConfig() {
		log.debug("init xml config file..");
	    InputStream inputStream = RequestUtils.class.getClassLoader().getResourceAsStream(excludeFile);
	    try{
	    	BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
	    	xml = new String();
	    	String l = null;
	    	while ((l = reader.readLine())!=null){
	    		xml+=l;
	    	}
	    }catch(Exception e){
	    	log.info("Read exclude xml file error, message is "+e.getMessage());
	    }
	}
	public void init(){
		log.info("==  XMLConfig init start.");
	}	
	@SuppressWarnings("unchecked")
	public String getAuthExcludes() {
		StringBuffer buffer = new StringBuffer();
		try{
			Document d = DocumentHelper.parseText(xml);
			Element root = d.getRootElement();
			Element auth = root.element("auth");
			Iterator<Element> it = auth.elementIterator();
			while(it.hasNext()){
				Element e = it.next();
				buffer.append(e.getText());
				buffer.append(",");
			}
			if(buffer.toString().length()>0){
				return buffer.substring(0,buffer.length()-1).toString();
			}else
				return null;
		}catch(Exception e){
			log.error("get excludes tag error. message is "+e.getMessage());
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public String getRateLimitExcludes() {
		StringBuffer buffer = new StringBuffer();
		try{
			Document d = DocumentHelper.parseText(xml);
			Element root = d.getRootElement();
			Element auth = root.element("rate_limit");
			Iterator<Element> it = auth.elementIterator();
			while(it.hasNext()){
				Element e = it.next();
				buffer.append(e.getText());
				buffer.append(",");
			}
			if(buffer.toString().length()>0){
				return buffer.substring(0,buffer.length()-1).toString();
			}else
				return null;
		}catch(Exception e){
			log.error("get excludes tag error. message is "+e.getMessage());
			return null;
		}
	}
    public String getUnlockLimits() {
        StringBuffer buffer = new StringBuffer();
        try{
            Document d = DocumentHelper.parseText(xml);
            Element root = d.getRootElement();
            Element auth = root.element("unlock");

			@SuppressWarnings("unchecked")
            Iterator<Element> it = auth.elementIterator();
            while(it.hasNext()){
                Element e = it.next();
                buffer.append(e.getText());
                buffer.append(",");
            }
            if(buffer.toString().length()>0){
                return buffer.substring(0,buffer.length()-1).toString();
            }else
                return null;
        }catch(Exception e){
            log.error("get excludes tag error. message is "+e.getMessage());
            return null;
        }
    }
	public static void main(String[] args){
		XMLConfig config = XMLConfig.getInstance();
		System.out.println(config.getAuthExcludes());
		System.out.println(config.getRateLimitExcludes());
		System.out.println(config.getUnlockLimits());
	}
}
