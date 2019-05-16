package com.book.manager.controller.security;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.book.manager.api.service.AuthorityService;
import com.book.manager.api.service.ResourceService;
import com.book.manager.api.service.RoleService;
import com.book.manager.api.service.UserEntityService;
import com.book.manager.base.LoginContextHolder;
import com.book.manager.base.ParentController;
import com.book.manager.domain.security.ManagerLogEntity;
import com.book.manager.domain.security.ResourceEntity;
import com.book.manager.domain.security.RoleEntity;
import com.book.manager.domain.security.UserEntity;
import com.book.manager.util.MD5;
import com.book.manager.util.ResultMsg;
import com.book.util.GoEasyPublish;
import com.book.util.StringUtil;
import com.book.util.redis.JedisClient;
import com.book.util.redis.util.RedisUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 管理平台用户
 * Created by teela on 2017/3/24.
 */
@Controller
@RequestMapping(value = "/user",produces = "application/json;charset=utf-8")
public class UserControllerSecurity extends ParentController {
    private static Log logger = LogFactory.getLog(UserControllerSecurity.class) ;
    private static final String JSON = "application/json;charset=utf-8";

    @Autowired
    private UserEntityService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private JedisClient jedisClient ;

    /**
     * 进入首页
     * @return
     */
    @RequestMapping(value = "/index",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView index(){
        String goEasyOTP = GoEasyPublish.goEasyOTP(GoEasyPublish.SECRET_KEY) ;
//        UserEntity userEntity = LoginContextHolder.getLoginUser();
//        if(Objects.isNull(userEntity)){
//            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
//                    .getAuthentication()
//                    .getPrincipal();
//            if(!Objects.isNull(userDetails)){
//                userEntity = userService.getUserByUserAccount(userDetails.getUsername());
//                LoginContext context = new LoginContext(userEntity);
//                LoginContextHolder.set(context);
//            }
//        }
        return new ModelAndView("/view/index","otp",goEasyOTP);
    }

    /**
     * 切换语言
     * @param langrage CN:中文,EN:英文
     * @author daniel 2018/08/09
     * @return
     */
    @RequestMapping(value = "/language",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ResultMsg language(HttpServletRequest request , HttpServletResponse response ,String langrage){
        ResultMsg resultMsg = new ResultMsg();
        UserEntity userEntity = LoginContextHolder.getLoginUser() ; // 登录用户信息
        if(Objects.isNull(userEntity)){
            try {
                Object temp = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
                SecurityContext securityContext = (SecurityContext) temp;
                Authentication authentication = securityContext.getAuthentication();
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                userEntity = userService.getUserByUserAccount(userDetails.getUsername());
            }catch (Exception e){
                e.printStackTrace();
                resultMsg.setMsg("语言切换异常,e=" + e.getMessage());
                return resultMsg;
            }
        }
        UserEntity updateUser = new UserEntity();
        if(UserEntity.UserLangrageEnum.CN.getValue().equals(langrage)){
            updateUser.setUserLanguage(UserEntity.UserLangrageEnum.CN.getValue());
        }else if(UserEntity.UserLangrageEnum.EN.getValue().equals(langrage)){
            updateUser.setUserLanguage(UserEntity.UserLangrageEnum.EN.getValue());
        }else{
            resultMsg.setMsg("语言错误!");
            return resultMsg;
        }
        updateUser.setId(userEntity.getId());
        int effc = userService.updateByPrimaryKeySelective(updateUser);
        if(effc <= 0){
            resultMsg.setMsg("语言切换失败!");
        }else{
            resultMsg.success();
            resultMsg.setMsg("语言切换成功!");
        }
        return resultMsg;
    }


    /**
     * 获取该用户的所有权限
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = { "/menu" }, produces= { JSON })
    @ResponseBody
    public String getMenuContent(HttpServletRequest request , HttpServletResponse response ){
        UserEntity userEntity = LoginContextHolder.getLoginUser() ; // 登录用户信息
        List<ResourceEntity> listResource = null ;
        if(Objects.isNull(userEntity)){
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userEntity = userService.getUserByUserAccount(userDetails.getUsername());
            listResource = resourceService.getUserMenuByUserName(userDetails.getUsername()) ;
        }
        else{
            listResource = resourceService.getUserMenuByUserName(userEntity.getUserName()) ;
        }
        JSONArray jsonArray  = new JSONArray() ;
        // 查找用户拥有的资源
        List<ResourceEntity> fathers = new ArrayList<ResourceEntity>() ; // 一级菜单
        List<ResourceEntity> childrens = new ArrayList<>() ; // 二级菜单
        for(ResourceEntity resource : listResource){
            if(resource.getParentId().equals(0) && resource.getResourceLevel().equals(1) ){
                fathers.add(resource) ;
            }
            else if(resource.getResourceLevel().equals(2)){
                childrens.add(resource) ;
            }
        }
        String userLangrage = userEntity.getUserLanguage();
        if(StringUtil.isEmpty(userLangrage)){
            userLangrage = UserEntity.UserLangrageEnum.CN.getValue();
        }

        for(ResourceEntity fatherResource : fathers){
            String authDesc = fatherResource.getAuthorityEntity().getAuthorityDesc();
            JSONObject obj= JSONObject.parseObject(authDesc);
            JSONObject fatherObject = new JSONObject() ;
            fatherObject.put("id",fatherResource.getId()) ;
            fatherObject.put("text",obj.get(userLangrage)) ;
            fatherObject.put("url","/") ;
            JSONArray chidrenArray = new JSONArray() ;
            for(ResourceEntity chidrenResource : childrens){
                if(chidrenResource.getParentId().equals( fatherResource.getId())){
                    obj = JSONObject.parseObject(chidrenResource.getAuthorityEntity().getAuthorityDesc());
                    JSONObject childrenObject = new JSONObject() ;
                    childrenObject.put("id",chidrenResource.getId()) ;
                    childrenObject.put("url",chidrenResource.getResourceUrl()) ;
                    childrenObject.put("text",obj.get(userLangrage)) ;
                    chidrenArray.add(childrenObject) ;
                }
            }
            fatherObject.put("children",chidrenArray) ;
            jsonArray.add(fatherObject) ;
        }
        return toString(jsonArray) ;
    }

    /**
     * 查询用户列表
     * @return
     */
    @RequestMapping(value = "/preQueryUserList",method = {RequestMethod.GET})
    public ModelAndView preQueryUserList(){
        return new ModelAndView("/view/sysuser/userList");
    }


    /**
     * 查询用户列表
     * @param request
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/queryUserList",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String queryUserList(HttpServletRequest request, UserEntity userEntity, Integer pageIndex){
        if (pageIndex==null || pageIndex==0){
            pageIndex=1;
        }
        userEntity.setPage(pageIndex);
        List<UserEntity> entities=userService.selectByPage(userEntity);
        int totalRows=userService.countByPage(userEntity);
        userEntity.setTotalRows(totalRows);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("Rows",entities);
        jsonObject.put("Total", totalRows);
        return jsonObject.toJSONString();
    }

    /**
     * 新增用户
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/preCreateUser",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView preCreateUser(HttpServletRequest request, Integer id){
        UserEntity entity=null;
        if (id!=null && id>0){
            entity=userService.getById(id);
        }
        request.setAttribute("user", entity);;
        return new ModelAndView("/view/sysuser/createUser");
    }

    /**
     * 新增用户
     * @param request
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/createUser",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg createUser(HttpServletRequest request, UserEntity userEntity){
        ResultMsg resultMsg=new ResultMsg();
        if (StringUtil.isEmpty(userEntity.getUserName())){//用户名为空则与登录用户名一致
            userEntity.setUserName(userEntity.getUserAccount());
        }
        if (!userEntity.getPassword().equals(userEntity.getConfirmPassword())){
            resultMsg.fail();
            resultMsg.setMsg("密码与确认密码不一致");
            return resultMsg;
        }
        if (StringUtil.isEmpty(userEntity.getId()) || 0==userEntity.getId()) {//新增
            UserEntity temp=userService.getUserByUserAccount(userEntity.getUserAccount());
            if (temp!=null){
                resultMsg.fail();
                resultMsg.setMsg("登录用户名已存在");
                return resultMsg;
            }

//            userEntity.setStatus(UserEntity.StatusEnum.NO.getValue());
            userEntity.setIsSys(UserEntity.IsSysEnum.NO.getValue());
            userEntity.setPassword(MD5.GetMD5Code(userEntity.getPassword(), "UTF-8"));//密码md5加密

            Integer id =  userService.insert(userEntity);
            if (id != null && id > 0) {
                resultMsg.success();
                resultMsg.setData(userEntity);
                resultMsg.setMsg("新增成功");
                return resultMsg;
            }
            resultMsg.fail();
            resultMsg.setMsg("新增失败");
            return resultMsg;
        }else {//修改
            userEntity.setUserAccount(null);//登录用户名不可修改
            userEntity.setPassword(MD5.GetMD5Code(userEntity.getPassword(), "UTF-8"));//密码md5加密
            int rows=userService.updateByPrimaryKeySelective(userEntity);
            if (rows>0){
                resultMsg.success();
                resultMsg.setData(userEntity);
                resultMsg.setMsg("修改成功");
                return resultMsg;
            }
            resultMsg.fail();
            resultMsg.setMsg("修改失败");
            return resultMsg;
        }
    }

    /**
     * 删除用户
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteUser",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg deleteUser(HttpServletRequest request, Integer id){
        ResultMsg resultMsg=new ResultMsg();
        if (id==null || id<=0){
            resultMsg.fail();
            resultMsg.setMsg("参数失败");
            return resultMsg;
        }
        UserEntity temp=new UserEntity();
        temp.setId(id);
        int rows=userService.delete(temp);
        if (rows>0){
            resultMsg.success();
            resultMsg.setMsg("删除成功");
            return resultMsg;
        }
        resultMsg.fail();
        resultMsg.setMsg("删除失败");
        return resultMsg;
    }

    /**
     * 获取所有角色信息
     * @param request
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getAllRoles",method = {RequestMethod.GET})
    public String getAllRoles(HttpServletRequest request, Integer userId){
        if (userId==null || userId<=0){
            request.setAttribute("error","参数错误");
            return "/view/sysuser/giveRoleToUser";
        }
        UserEntity userEntity=userService.getById(userId);
        if (userEntity==null){
            request.setAttribute("error", "用户不存在");
            return "/view/sysuser/giveRoleToUser";
        }
        //用户拥有角色
        List<RoleEntity> rolesOfUser=roleService.getRolesByUserId(userEntity.getId());
        //所有角色
        List<RoleEntity> roleEntities=roleService.selectByPage(null);
        if (rolesOfUser!=null && rolesOfUser.size()>0){
            for (RoleEntity roleEntity : rolesOfUser) {
                for (RoleEntity entity : roleEntities) {
                    if (roleEntity.getId().equals(entity.getId())){
                        entity.setIsChecked(true);
                    }
                }
            }
        }
        request.setAttribute("user", userEntity);
        request.setAttribute("roles", roleEntities);
        return "/view/sysuser/giveRoleToUser";
    }

    /**
     * 给用户赋角色
     * @param request
     * @param userId
     * @return
     */
    @RequestMapping(value = "/giveRoleToUser",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg giveRoleToUser(HttpServletRequest request, Integer userId, String roleIds){
        ResultMsg resultMsg=new ResultMsg();
        if (userId==null || userId<=0){
            resultMsg.fail();
            resultMsg.setMsg("参数错误");
            return resultMsg;
        }
        if (roleIds==null || roleIds==""){
            resultMsg.fail();
            resultMsg.setMsg("未选择角色");
            return resultMsg;
        }
        UserEntity userEntity=userService.getById(userId);
        if (userEntity==null){
            resultMsg.fail();
            resultMsg.setMsg("用户不存在");
            return resultMsg;
        }
        //给用户赋角色
        Boolean result=roleService.giveRoleToUser(userEntity.getId(), roleIds);
        if (result!=null && result){
            resultMsg.success();
            resultMsg.setMsg("给用户赋角色成功");
            return resultMsg;
        }
        resultMsg.fail();
        resultMsg.setMsg("操作失败");
        return resultMsg;
    }

//    /**
//     * 用户登录失效
//     * @return
//     */
    @RequestMapping(value = "userOut",method = {RequestMethod.GET})
    @ResponseBody
    public ModelAndView userOut(){
        return new ModelAndView("/view/sysuser/userOut") ;
    }

    /**
     * 登录异常,读取 redis 错误信息
     */
    @RequestMapping(value = "/msg",method = {RequestMethod.POST},produces = JSON)
    @ResponseBody
    public JSONObject useMsg(){
        JSONObject jsonObject = new JSONObject() ;
        logger.info("jsonObject == " + jsonObject) ;
        try {
            String userName = RedisUtil.get(jedisClient,"userName") ;
            String msgKey = "MANAGER_" + userName + "_MSG" ; // 用户登录异常信息
            String value = RedisUtil.get(jedisClient,msgKey) ;
            jsonObject.put("msg",value) ;
        }catch (Exception e){
            logger.info("服务器异常:" + e.getMessage());
            e.printStackTrace();
        }
        logger.info("jsonObject == " + jsonObject);
        return jsonObject ;
    }

    @RequestMapping(value = "/preUpdatePassword",method = {RequestMethod.GET})
    public ModelAndView preUpdatePassword(){
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (userDetails==null){
                return new ModelAndView("/view/sysuser/updatePassword","msg","未登录");
            }
            UserEntity userEntity=userService.getUserByUserAccount(userDetails.getUsername());
            if (userEntity==null){
                return new ModelAndView("/view/sysuser/updatePassword","msg","用户不存在");
            }
            return new ModelAndView("/view/sysuser/updatePassword","user",userEntity);
        }catch (Exception e){
            return new ModelAndView("/view/sysuser/updatePassword","msg","登录信息有误");
        }
    }

    @RequestMapping(value = "/updatePassword",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg updatePassword(String password,String confirmPassword){
        ResultMsg resultMsg=new ResultMsg();
        try {
            if (StringUtil.isEmpty(password) || StringUtil.isEmpty(confirmPassword)){
                resultMsg.fail();
                resultMsg.setMsg("密码与确认密码不能为空");
                return resultMsg;
            }
            if (!password.equals(confirmPassword)){
                resultMsg.fail();
                resultMsg.setMsg("密码与确认密码不一致");
                return resultMsg;
            }

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (userDetails==null){
                resultMsg.fail();
                resultMsg.setMsg("未登录");
                return resultMsg;
            }
            UserEntity userEntity=userService.getUserByUserAccount(userDetails.getUsername());
            if (userEntity==null){
                resultMsg.fail();
                resultMsg.setMsg("用户不存在");
                return resultMsg;
            }
            boolean result=userService.updatePassword(userEntity.getUserAccount(), MD5.GetMD5Code(password, "UTF-8"));
            if (!result){
                resultMsg.fail();
                resultMsg.setMsg("密码修改失败");
                return resultMsg;
            }
        }catch (Exception e){
            resultMsg.fail();
            resultMsg.setMsg("登录信息有误");
            return resultMsg;
        }
        resultMsg.success();
        resultMsg.setMsg("密码修改成功");
        UserEntity userEntity = LoginContextHolder.getLoginUser();
        UserDetails userDetails = null ;
        if (Objects.isNull(userEntity)) {
            userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        super.addManagerLog(null,"用户密码修改(user.updatePassword),before:(pssword:" + userDetails.getPassword() + "),after:(newPassword:" + password + ")", ManagerLogEntity.LogTypeEnum.UPDATE.getValue());
        return resultMsg;
    }

    private String toString(JSONArray resultMsg){
        return com.alibaba.fastjson.JSON.toJSONString(resultMsg, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty);
    }
}
