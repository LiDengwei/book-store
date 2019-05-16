package com.book.manager.base;

import com.alibaba.fastjson.JSONObject;
import com.book.manager.api.service.ManagerLogService;
import com.book.manager.api.service.RoleService;
import com.book.manager.api.service.UserEntityService;
import com.book.manager.controller.WebContextUtils;
import com.book.manager.domain.security.ManagerLogEntity;
import com.book.manager.domain.security.UserEntity;
import com.book.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/** 
 * TODO(Controller基类).
 * @ClassName: ParentController
 * @author Johnny_L_Q
 */
public class ParentController {

    private static String TEMPLATE_PRE = "<!DOCTYPE html><html><head><meta charset='UTF-8' /><meta name='MobileOptimized' content='240' /><meta name='viewport' content='width=device-width,initial-scale=1.0,minimum-scale=1.0' /><meta name='format-detection' content='telephone=no' /><title></title></head><body>";
    private static String TEMPLATE_SUB = "</body></html>";

    @Autowired
    private ManagerLogService managerLogService;
    @Autowired
    private UserEntityService userService;

    /**
     * 添加管理日志
     * @param username 用户名
     * @param info 日志信息
     * @param logType 日志类型
     */
    public void addManagerLog(String username,String info,Integer logType){
        try{
            String manager = null;
            UserEntity userEntity = LoginContextHolder.getLoginUser();
            if (Objects.isNull(userEntity)) {
                UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                manager = userDetails.getUsername();
            } else {
                manager = userEntity.getUserName();
            }
            ManagerLogEntity managerLog = new ManagerLogEntity();
            managerLog.setManager(manager);
            managerLog.setUsername(username);
            managerLog.setInfo(info);
            managerLog.setLogType(logType);
            managerLog.setCreateAt(new Date());
            managerLog.setUpdateAt(new Date());
            managerLogService.insert(managerLog);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * 
     * TODO(打印).
     * @param response
     * @param result
     */
    public void println(HttpServletResponse response , Object result){
        response.setContentType("text/Xml;charset=gbk");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(result.toString());
        } catch (IOException ex1) {
            ex1.printStackTrace();
        } finally {
            out.close();
        }
    }
    
    
    /**
     * 
     * TODO(将查询的List结果集封装在Json对象中并且返回).
     * @param objects 所查出来的实体集合
     * @param count 结果集总和
     * @return
     * @author Johnny_L_Q
     */
    public Map<String, Object> getGridDatas(List<?> objects , Integer count){
        Map<String, Object> result = new HashMap<String, Object>();
        /*JSONObject jsonobj = new JSONObject();
        response.setContentType("text/Xml;charset=UTF-8");
        PrintWriter out = null;
        try {
            jsonobj.put("Rows", objects);
            jsonobj.put("Total", count);
            System.out.println("json" + result);
            out = response.getWriter();
            out.println(jsonobj.toString());
        } catch (Exception ex1) {
            ex1.printStackTrace();
        } finally {
            out.close();
        }*/
        result.put("Rows", objects);
        result.put("Total", count);
        return result;
    }

    /**
     *
     * TODO(将查询的List结果集封装在Json对象中并且返回).
     * @param objects 所查出来的实体集合
     * @param totalCount 结果集总和
     * @return
     * @author Johnny_L_Q
     */
    public String getGridData(List<?> objects , Integer totalCount){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("Rows", objects);
        jsonObject.put("Total", totalCount);
        return jsonObject.toJSONString();
    }

    /**
     *
     * TODO(将查询的List结果集封装在Json对象中并且返回).
     * @param objects 所查出来的实体集合
     * @param totalCount 结果集总和
     * @return
     * @author Johnny_L_Q
     */
    public String getGridData(List<?> objects , Integer totalCount,BigDecimal totalAmount){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("totalAmount", totalAmount);
        jsonObject.put("Rows", objects);
        jsonObject.put("Total", totalCount);
        return jsonObject.toJSONString();
    }
    
    /**
     * 
     * TODO(公共方法，用户获取页面传递过来的各个属性值).
     * @param request
     * @param defaultSortName
     * @return 获取参数后的实体
     * @author Johnny_L_Q
     */
    public Map<String,Object> getParameters(HttpServletRequest request , String defaultSortName){
        Map<String,Object> map = new HashMap<String, Object>();
        /**
         * 当前页数
         */
        String page = request.getParameter("page");
        /**
         * 页面数据条数
         */
        String pageSize = request.getParameter("pagesize");
        /**
         * 排序字段
         */
        String sortname = request.getParameter("sortname");
        /**
         * 排序方式
         */
        String sortorder = request.getParameter("sortorder");
        //地市描述
        String regionCode = request.getParameter("regionCode");
        map.put("regionCode", regionCode);
        String orderBy = "";
        
        if(!StringUtil.isEmpty(sortname)){
            orderBy = sortname;
        }
        if(!StringUtil.isEmpty(sortorder)){
            orderBy += " " + sortorder;
        }
        if(!StringUtil.isEmpty(orderBy)){
            map.put("orderBy", orderBy);
        }
        if(!StringUtil.isEmpty(page)){
            map.put("pageIndex", (Integer.parseInt(page)-1) * Integer.parseInt(pageSize));
        }else{
            map.put("pageIndex", 0);
        }
        if(!StringUtil.isEmpty(pageSize)){
            map.put("pageSize", Integer.parseInt(pageSize));
        }else{
            map.put("pageSize", 20);
        }
        //如果没有排序请求就默认按contentId排序
        if (!map.containsKey("orderBy")) {
            map.put("orderBy" , defaultSortName);
        }
        return map;
    }
    
    
    /**
     * 更新html文件到七牛服务器
     * @param contentShow   富文本内容
     * @param key           功能类别
     * @param oldFileName   要删除的原文件
     * @return 文件路径
     */
    protected String updateContentShowUrl(String contentShow, String key, String oldFileName){
        contentShow = TEMPLATE_PRE + contentShow + TEMPLATE_SUB;
        String fileName = key + "_" + System.currentTimeMillis() + ".html";
//        FileWriter fw = null;
//        File file = null;
//        try {
//            //创建要上传的文件
//            file = new File(fileName);
//            fw = new FileWriter(file);
//            fw.write(contentShow);
//            fw.flush();
//            //上传到七牛服务器
//            QiniuUtil.upload(file, fileName);
//            //删除以前的文件
//            if(oldFileName != null && !"".equals(oldFileName)){
//                QiniuUtil.delete(new String[]{oldFileName});
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            try{
//                if(fw != null){
//                    fw.close();
//                }
//                //删除本地文件
//                file.delete();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return fileName;
    }

    /**
     * 前台日期格式转换为Date格式
     * 日期格式：yyyy-MM-dd,第二个参数为是否为空  true代表可以为空
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 判断用户是否为共享者角色
     */
    protected JSONObject judgeUserRole(){
        JSONObject jsonObject = new JSONObject() ;
        String userName = null;
        UserEntity userEntity = LoginContextHolder.getLoginUser();
        if (Objects.isNull(userEntity)) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userName = userDetails.getUsername();
        } else {
            userName = userEntity.getUserName();
        }
        RoleService roleService = WebContextUtils.getBean("roleService") ;
        Integer roleId = roleService.judgeUserRole(userName) ;//根据登录的用户名获取登录角色的权限信息
        if(roleId == 3){
            jsonObject.put("status",true) ;
            jsonObject.put("userName",userName) ;
        }else{
            jsonObject.put("status",false) ;
        }
        return jsonObject ;
    }


    /**
     * 时间转换成字符串
     */
    public static String changeDate(Date date){
        if(!Objects.isNull(date)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
            return simpleDateFormat.format(date) ;
        }
        else{
            return "" ;
        }
    }

    public UserEntity getUserLanguages(){
        UserEntity userEntity = LoginContextHolder.getLoginUser() ; // 登录用户信息
        UserDetails userDetailss = null ;
        String userNames = null;
        if(Objects.isNull(userEntity)){
            userDetailss = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userNames = userDetailss.getUsername();
        }else {
            userNames = userEntity.getUserName();
        }
        if (StringUtil.isNotEmpty(userNames)) {
            userEntity = userService.getUserByUserAccount(userNames);
        }else {
            userEntity = null;
        }
        return userEntity;
    }
}
