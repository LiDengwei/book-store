package com.book.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.DefaultAttribute;

import java.util.*;

/**
 * Created by Lynn
 * Created Date  2014/8/27 16:58
 */
public class XmlHelper {

    private static final String ELEMENTTEXT = "ELEMENTTEXT";

    /**
     * @description 将xml字符串转换成map
     * @param xml
     * @return Map
     */
    public static Map readStringXmlAsMap(String xml) {
        Map map = new HashMap();
        Document doc = null;

        try {
            // 将字符串转为XML
            doc = DocumentHelper.parseText(xml);
            // 获取根节点
            Element rootElt = doc.getRootElement();
            // 拿到根节点的名称

            // 获取根节点下的子节点head
            int eleSize = rootElt.elements().size();
            String name = rootElt.getName();
            if(eleSize!=0){
                List list = parseElementList(rootElt);
                map.put(name,list);
            }
            else{
                String value = rootElt.getTextTrim();
                map.put(name, value);
            }



        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * @description 将xml字符串转换成map
     * @param xml
     * @return Map
     */
    public static Map readStringXmlAsOneMap(String xml) {
        Map map = new HashMap();
        Document doc = null;

        try {
            // 将字符串转为XML
            doc = DocumentHelper.parseText(xml);
            // 获取根节点
            Element rootElt = doc.getRootElement();
            // 拿到根节点的名称

            // 获取根节点下的子节点head
            int eleSize = rootElt.elements().size();
            String name = rootElt.getName();
            if(eleSize!=0){
                return parseElementMap(rootElt);
            }
            else{
                String value = rootElt.getTextTrim();
                map.put(name, value);
            }



        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * @description 将xml字符串转换成map
     * @param map
     * @return String
     */
    public static String writeMapAsXml(Map map) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<xml>");
        for(Object key:map.keySet()){
            stringBuilder.append("<");
            stringBuilder.append(key.toString());
            stringBuilder.append(">");

            stringBuilder.append(map.get(key));

            stringBuilder.append("</");
            stringBuilder.append(key.toString());
            stringBuilder.append(">\n");
        }
        stringBuilder.append("</xml>");
        return stringBuilder.toString();
    }


    public static Map parseElementMap(Element element){
        //获取根节点下的子节点body
        Map<String,Object>  map = new HashMap<String,Object>();
        Iterator iterss = element.elementIterator();
        // 遍历body节点
        while (iterss.hasNext()) {
            Element recordEless = (Element) iterss.next();
            // 拿到body节点下的子节点result值
            int eleSize = recordEless.elements().size();
//            Map  map  =new HashMap();
            String name = recordEless.getName();
            if(eleSize==0){
                String elementText = recordEless.getText();
                map.put(name, elementText);
//                map.put(ELEMENTTEXT, elementText);

//                List<DefaultAttribute> attrList = recordEless.attributes();
//
//                for(DefaultAttribute at:attrList){
//                    map.put(at.getName(),at.getValue());
//                }
            }
//            else{
//                List newList = parseElementList(recordEless);
//                map.put(name,newList);
//            }
//            list.add(map);
        }
        return map;
    }

    public static List parseElementList(Element element){
        //获取根节点下的子节点body
        List  list = new ArrayList();
        Iterator iterss = element.elementIterator();
        // 遍历body节点
        while (iterss.hasNext()) {
            Element recordEless = (Element) iterss.next();
            // 拿到body节点下的子节点result值
            int eleSize = recordEless.elements().size();
            Map  map  =new HashMap();
            String name = recordEless.getName();
            if(eleSize==0){
                String elementText = recordEless.getText();
                map.put(name, elementText);
                map.put(ELEMENTTEXT, elementText);

                List<DefaultAttribute> attrList = recordEless.attributes();

                for(DefaultAttribute at:attrList){
                    map.put(at.getName(),at.getValue());
                }
            }
            else{
                List newList = parseElementList(recordEless);
                map.put(name,newList);
            }
            list.add(map);
        }
        return list;
    }

}
