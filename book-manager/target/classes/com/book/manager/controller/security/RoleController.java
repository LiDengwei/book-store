package com.book.manager.controller.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.book.manager.api.service.AuthorityService;
import com.book.manager.api.service.ResourceService;
import com.book.manager.api.service.RoleService;
import com.book.manager.domain.security.AuthorityEntity;
import com.book.manager.domain.security.ResourceEntity;
import com.book.manager.domain.security.RoleAuthorityEntity;
import com.book.manager.domain.security.RoleEntity;
import com.book.manager.security.impl.CustomInvocationSecurityMetadataSourceImpl;
import com.book.manager.util.ResultMsg;
import com.book.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by teela on 2017/3/27.
 */
@Controller
@RequestMapping(value = "/role",produces = "application/json;charset=utf-8")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private ResourceService resourceService ;
    @Autowired
    private CustomInvocationSecurityMetadataSourceImpl customInvocationSecurityMetadataSource ;

    @RequestMapping(value = "/preQueryRoleList",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView preQueryRoleList(HttpServletRequest request, RoleEntity roleEntity){
        return new ModelAndView("/view/sysuser/roleList");
    }

    @RequestMapping(value = "/queryRoleList",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String queryRoleList(HttpServletRequest request, RoleEntity roleEntity){
        List<RoleEntity> entities=roleService.selectByPage(roleEntity);
        int totalRows=roleService.countByPage(roleEntity);
        roleEntity.setTotalRows(totalRows);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("Rows",entities);
        jsonObject.put("Total", totalRows);
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/preCreateRole",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView preCreateRole(HttpServletRequest request, Integer id){
        RoleEntity roleEntity=null;
        if (id!=null && id>0){
            roleEntity=roleService.getById(id);
        }
        request.setAttribute("role", roleEntity);
        return new ModelAndView("/view/sysuser/createRole");
    }

    @RequestMapping(value = "/createRole",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg addRole(HttpServletRequest request, RoleEntity roleEntity){
        ResultMsg resultMsg=new ResultMsg();
        if (StringUtil.isEmpty(roleEntity.getId()) || 0==roleEntity.getId()){//新增
            RoleEntity temp=roleService.getByRoleName(roleEntity.getRoleName());
            if (temp!=null){
                resultMsg.fail();
                resultMsg.setMsg("角色名称已存在");
                return resultMsg;
            }

//            roleEntity.setEnabled(RoleEntity.EnabledEnum.YES.getValue());
//            roleEntity.setIsSys(RoleEntity.IsSysEnum.NO.getValue());
            Integer id=roleService.insert(roleEntity);
            if (id!=null && id>0){
                resultMsg.success();
                resultMsg.setData(roleEntity);
                resultMsg.setMsg("新增成功");
                return resultMsg;
            }
            resultMsg.fail();
            resultMsg.setMsg("新增失败");
            return resultMsg;
        }else {//修改
            RoleEntity temp=roleService.getByRoleName(roleEntity.getRoleName());
            if (temp!=null && !roleEntity.getId().equals(temp.getId())){
                resultMsg.fail();
                resultMsg.setMsg("角色名称已存在");
                return resultMsg;
            }

            int rows=roleService.updateByPrimaryKeySelective(roleEntity);
            if (rows>0){
                resultMsg.success();
                resultMsg.setData(roleEntity);
                resultMsg.setMsg("修改成功");
                return resultMsg;
            }
            resultMsg.fail();
            resultMsg.setMsg("修改失败");
            return resultMsg;
        }
    }

    @RequestMapping(value = "/deleteRole",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg deleteRole(Integer id){
        ResultMsg resultMsg=new ResultMsg();
        if (id==null || id<=0){
            resultMsg.fail();
            resultMsg.setMsg("参数失败");
            return resultMsg;
        }
        RoleEntity temp=new RoleEntity();
        temp.setId(id);
        int rows=roleService.delete(temp);
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
     * 获取所有权限
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/getAllAuths",method = {RequestMethod.POST})
    @ResponseBody
    public JSONObject getAllAuths(Integer roleId){
        JSONObject jsonObject = new JSONObject() ;
        JSONArray jsonArray = new JSONArray() ;
        List<AuthorityEntity> authsOfRole=null; // 获取该用户所拥有的权限
        if (roleId!=null && roleId>0){
            //角色所拥有的权限
            authsOfRole=authorityService.getAuthoritiesByRoleId(roleId,1);
        }else {
            throw new NullPointerException() ;
        }

        List<Integer> userFather = new ArrayList<>() ; // 用户一级菜单
        List<Integer> userChildre = new ArrayList<>() ; // 用户二级菜单
        List<Integer> userGrandChild = new ArrayList<>() ; // 用户三级菜单
        Set<Integer> userGrand = new HashSet<>() ;
        for(AuthorityEntity authorityEntity : authsOfRole){
            if(authorityEntity.getResourceLevel().equals(1)||authorityEntity.getResourceLevel().equals(0)){
                userFather.add(authorityEntity.getId()) ; // 一级权限 id
            }
            if(authorityEntity.getResourceLevel().equals(2)){
                userChildre.add(authorityEntity.getId()) ; // 二级权限 id
            }
            if(authorityEntity.getResourceLevel().equals(20)){ // 三级权限 id
                userGrand.add(authorityEntity.getId()) ;
            }
        }

        for(Integer id : userGrand){
            userGrandChild.add(id) ;
        }

        // 所有权限及权限对应的资源
        List<ResourceEntity> adminResource = resourceService.getAllAuthorityAndResource() ;
        List<ResourceEntity> fathers = new ArrayList<>() ; // 一级权限对象
        List<ResourceEntity> childres = new ArrayList<>() ; // 二级权限对象
        List<ResourceEntity> grandChild = new ArrayList<>() ; // 三级权限对象 权限id 不重复
        for(ResourceEntity resourceEntity : adminResource){
            if(resourceEntity.getResourceLevel().equals(1)||resourceEntity.getResourceLevel().equals(0)){
                fathers.add(resourceEntity) ;
            }
            if(resourceEntity.getResourceLevel().equals(2)){
                childres.add(resourceEntity) ;
            }
            if(resourceEntity.getResourceLevel().equals(20)){
                if(grandChild.size()>0){
                    boolean flag = true ;
                    for(int i=0;i<grandChild.size();i++){
                        if(grandChild.get(i).getAuthorityId().equals(resourceEntity.getAuthorityId())){
                            flag = false ;
                        }
                    }
                    if(flag){
                        grandChild.add(resourceEntity) ;
                    }
                }
                else{
                    grandChild.add(resourceEntity) ;
                }
            }
        }

        // tree 返回字符串
        for(ResourceEntity father : fathers){
            JSONObject fatherObject = new JSONObject() ;
            JSONArray childrenArray = new JSONArray() ;
            if(father.getResourceLevel().equals(1)){
                fatherObject.put("text",father.getAuthorityDesc()) ;
            }
            else{
                fatherObject.put("text",father.getAuthorityDesc()+" *** ") ;
            }
            fatherObject.put("isexpand",false) ;
            fatherObject.put("roleId",father.getAuthorityId()) ;
            // 判断用户是否拥有此一级权限
            if(contain(father.getAuthorityId(),userFather)){
                fatherObject.put("ischecked" , true) ;
            }
            else{
                fatherObject.put("ischecked",false) ;
            }
            if(father.getResourceLevel().equals(1)){
                for(ResourceEntity children : childres){
                    JSONArray grandArray = new JSONArray() ;
                    if(children.getParentId().equals(father.getId())){
                        JSONObject childrenObject = new JSONObject() ;
                        childrenObject.put("url",children.getResourceUrl()) ;
                        childrenObject.put("text",children.getAuthorityDesc()) ;
                        childrenObject.put("roleId",children.getAuthorityId()) ;
                        if(contain(children.getAuthorityId(),userChildre)){ // 用户拥有该二级菜单权限
                            childrenObject.put("ischecked",true) ;
                        }
                        else{
                            childrenObject.put("ischecked",false) ;
                        }
                        for(ResourceEntity grand : grandChild){
                            if(grand.getParentId().equals(children.getId())){
                                JSONObject grandObject = new JSONObject() ;
                                grandObject.put("url",grand.getResourceUrl()) ;
                                grandObject.put("text",grand.getAuthorityDesc()) ;
                                grandObject.put("roleId",grand.getAuthorityId()) ;
                                if(contain(grand.getAuthorityId(),userGrandChild)){
                                    grandObject.put("ischecked",true) ;
                                }else{
                                    grandObject.put("ischecked",false) ;
                                }
                                grandArray.add(grandObject) ;
                            }
                        }
                        childrenObject.put("children",grandArray) ;
                        childrenArray.add(childrenObject) ;
                    }
                }
            }
            fatherObject.put("children" ,childrenArray) ;
            jsonArray.add(fatherObject) ;
        }
        jsonObject.put("userFather" , userFather) ;
        jsonObject.put("userChildren" , userChildre) ;
        jsonObject.put("userGrandChild",userGrandChild) ;
        jsonObject.put("result",jsonArray) ;
        return jsonObject ;

    }

    /**
     * 赋权限给角色
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/giveAuthToRole",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg giveAuthTorole(String resultData ,String userFather , String userChildren ,String userGrandChild,Integer roleId){
        ResultMsg resultMsg = new ResultMsg() ;
        JSONArray resultArray = JSON.parseArray(resultData) ; // 将json字符串转化成json数组
        List<Integer> fatherList = JSONObject.parseArray(userFather,Integer.class) ; // 角色原来拥有的一级权限
        List<Integer> childrenList = JSONObject.parseArray(userChildren,Integer.class) ; // 角色原来有用的二级权限
        List<Integer> grandChildList = JSONObject.parseArray(userGrandChild,Integer.class) ; // 角色原来有用的三级权限

        List<Integer> fatherRole = new ArrayList<>() ; // 修改后的一级权限
        List<Integer> childrenRole = new ArrayList<>() ; // 修改后用户拥有的二级权限
        List<Integer> grandChildRole = new ArrayList<>() ; // 修改后用户拥有的三级权限

        for(int i=0;i<resultArray.size();i++){
            JSONObject jsonObject = resultArray.getJSONObject(i) ;
            if(jsonObject.getBoolean("ischecked")){ // 拥拥有该一级权限
                fatherRole.add(jsonObject.getInteger("roleId")) ;
            }
            JSONArray jsonArray = jsonObject.getJSONArray("children") ;
            for(int j=0;j<jsonArray.size();j++){
                JSONObject childrenObject = jsonArray.getJSONObject(j) ;
                if(childrenObject.getBoolean("ischecked")){ // 用户有用的二级权限
                    childrenRole.add(childrenObject.getInteger("roleId")) ;
                }
                JSONArray childrenArray = childrenObject.getJSONArray("children") ;
                for(int k=0;k<childrenArray.size();k++){
                    JSONObject grandObject = childrenArray.getJSONObject(k) ;
                    if(grandObject.getBoolean("ischecked")){
                        grandChildRole.add(grandObject.getInteger("roleId")) ;
                    }
                }
            }
        }
        List<Integer> closeRole = new ArrayList<>() ; // 角色被取消的权限
        List<Integer> openRole = new ArrayList<>() ; // 角色新添加的权限
        List<AuthorityEntity> authsOfRole = authorityService.getAuthoritiesByRoleId(roleId , null) ;
        if(authsOfRole.size()>0){ // 判断角色是否为新角色
            for(int i=0;i<fatherList.size();i++){
                if(!contain(fatherList.get(i),fatherRole)){  // 修改过后，取消该一级权限
                    closeRole.add(fatherList.get(i)) ;
                }
            }
            for(int j=0 ; j<childrenList.size();j++){
                if(!contain(childrenList.get(j),childrenRole)){
                    closeRole.add(childrenList.get(j)) ; // 修改后，取消该二级权限
                }
            }
            for(int n=0;n<grandChildList.size();n++){ // 修改后,取消的三级权限
                if(!contain(grandChildList.get(n),grandChildRole)){
                    closeRole.add(grandChildList.get(n)) ; // 修改后，取消该三级权限
                }
            }
            for(int k=0;k<fatherRole.size();k++){
                if(!contain(fatherRole.get(k),fatherList)){
                    openRole.add(fatherRole.get(k)) ; // 添加的一级权限
                }
            }
            for(int m=0 ; m<childrenRole.size();m++){
                if(!contain(childrenRole.get(m),childrenList)){
                    openRole.add(childrenRole.get(m)) ; // 添加的二级权限
                }
            }
            for(int l=0;l<grandChildRole.size();l++){ // 添加的三级权限
                if(!contain(grandChildRole.get(l),grandChildList)){
                    openRole.add(grandChildRole.get(l)) ; // 添加的二级权限
                }
            }
            if(closeRole.size()>0){ // 取消了角色的权限
                for(int i=0;i<closeRole.size();i++){
                    Integer result = authorityService.updateRoleAuthority(roleId,closeRole.get(i),false) ;
                    if(result == null){
                        resultMsg.fail();
                        resultMsg.setMsg("更新失败");
                        return resultMsg ;
                    }
                }
            }
            if(openRole.size()>0){ // 新添加的角色权限
                // 查找该角色是否拥有该权限
                List<RoleAuthorityEntity> addRole = new ArrayList<>() ;
                for(int i=0;i<openRole.size();i++){
                    RoleAuthorityEntity result = authorityService.getRoleAuthority(roleId,openRole.get(i)) ;
                    if(result!=null && !result.isIssy()){ // 角色拥有该权限
                        Integer upresult = authorityService.updateRoleAuthority(roleId , openRole.get(i),true) ; // 更新权限
                        if(upresult == null){
                            resultMsg.fail();
                            resultMsg.setMsg("更新失败");
                            return resultMsg ;
                        }
                    }
                    else if(result == null){ // 角色没有该权限
                        RoleAuthorityEntity roleAuthorityEntity = new RoleAuthorityEntity() ;
                        roleAuthorityEntity.setRoleId(roleId);
                        roleAuthorityEntity.setAuthorityId(openRole.get(i));
                        roleAuthorityEntity.setIssy(true);
                        roleAuthorityEntity.setCreateDate(new Date());
                        addRole.add(roleAuthorityEntity) ;
                    }
                }
                if(addRole.size()>0){
                    Integer result = authorityService.batchInsertRoleAuthority(addRole) ;
                    if(result==null){
                        resultMsg.fail();
                        resultMsg.setMsg("更新失败");
                        return resultMsg ;
                    }
                }
            }
            resultMsg.success();
            resultMsg.setMsg("更新成功");
            customInvocationSecurityMetadataSource.init();
            return resultMsg ;
        }
        else{ // 新添加的角色,给每个新添加的角色赋予公共权限
            if(fatherRole.size()>0){
                List<AuthorityEntity> addAuthority = authorityService.getAuthorityPublic() ;
                for(AuthorityEntity authorityEntity : addAuthority){
                    fatherRole.add(authorityEntity.getId()) ; // 给新角色添加公共权限
                }
                for(Integer children : childrenRole){ // 将一级权限和二级权限放到一个集合里面批量插入
                    fatherRole.add(children) ;
                }
                for(Integer grandChildren : grandChildRole){ // 将三级权限放到集合里面批量插入
                    fatherRole.add(grandChildren) ;
                }
                // 拥有权限集合对象
                List<RoleAuthorityEntity> roleAuthorityEntities = new ArrayList<>() ;
                for(Integer authoirty : fatherRole){
                    RoleAuthorityEntity roleAuthorityEntitie = new RoleAuthorityEntity() ;
                    roleAuthorityEntitie.setAuthorityId(authoirty);
                    roleAuthorityEntitie.setIssy(true);
                    roleAuthorityEntitie.setRoleId(roleId);
                    roleAuthorityEntitie.setCreateDate(new Date());
                    roleAuthorityEntities.add(roleAuthorityEntitie) ; // 新加权限集合
                }
                Integer result = authorityService.batchInsertRoleAuthority(roleAuthorityEntities) ;
                if(result == null){
                    resultMsg.fail();
                    resultMsg.setMsg("更新失败");
                    return resultMsg;
                }
            }
            customInvocationSecurityMetadataSource.init();
            return resultMsg ;
        }
    }


    // 判断元素是否属于集合
    public boolean contain(Integer roleId , List<Integer> list){
        for(int i=0;i<list.size();i++){
            if(roleId.equals(list.get(i))){
                return true ;
            }
        }
        return false ;
    }
    private String toString(JSONArray resultMsg){
        return JSON.toJSONString(resultMsg, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty);
    }
    public boolean contain(Integer roleId,List<ResourceEntity> grandRepetion,List<Integer> list){
        for(ResourceEntity resourceEntity : grandRepetion){
            if(resourceEntity.getAuthorityId().equals(roleId)){
                for(Integer id : list){
                    if(id.equals(resourceEntity.getId())){
                        return true ;
                    }
                }
            }
        }
        return false ;
    }

}
