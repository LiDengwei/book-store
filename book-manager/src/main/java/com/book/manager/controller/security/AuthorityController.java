package com.book.manager.controller.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.book.manager.api.service.AuthorityService;
import com.book.manager.api.service.ResourceService;
import com.book.manager.domain.security.AuthorityEntity;
import com.book.manager.domain.security.ResourceEntity;
import com.book.manager.domain.security.ResourceTreeEntity;
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
 * Created by teela on 2017/3/31.
 */
@Controller
@RequestMapping(value = "/auth",produces = "application/json;charset=utf-8")
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private ResourceService resourceService;

    /**
     * 查询权限列表信息
     * @return
     */
    @RequestMapping(value = "preQueryAuthList",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView preAuthList(){
        return new ModelAndView("/view/sysuser/authList");
    }

    /**
     * 查询权限列表信息
     * @param authorityEntity
     * @return
     */
    @RequestMapping(value = "queryAuthList",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String authList(AuthorityEntity authorityEntity, Integer pageIndex){
        if (pageIndex==null || pageIndex==0){
            pageIndex=1;
        }
        authorityEntity.setPage(pageIndex);
        List<AuthorityEntity> entities=authorityService.selectByPage(authorityEntity);
        int totalRows=authorityService.countByPage(authorityEntity);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("Rows",entities);
        jsonObject.put("Total", totalRows);
        return jsonObject.toJSONString();
    }

    /**
     * 进入新增权限页面
     * @param id
     * @return
     */
    @RequestMapping(value = "preCreateAuth",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView preCreateAuth(HttpServletRequest request, Integer id){
        AuthorityEntity entity=null;
        if (id!=null && id>0){
            entity=authorityService.getById(id);
        }
        request.setAttribute("auth", entity);
        return new ModelAndView("/view/sysuser/createAuth");
    }

    /**
     * 新增权限
     * @param authorityEntity
     * @return
     */
    @RequestMapping(value = "createAuth",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg createAuth(AuthorityEntity authorityEntity){
        ResultMsg resultMsg=new ResultMsg();
        if (StringUtil.isEmpty(authorityEntity.getId()) || 0==authorityEntity.getId()){//新增
            AuthorityEntity temp=authorityService.getByAuthName(authorityEntity.getAuthorityName());
            if (temp!=null){
                resultMsg.fail();
                resultMsg.setMsg("权限名称已存在");
                return resultMsg;
            }

            authorityEntity.setCreateDate(new Date());
            Integer id=authorityService.insert(authorityEntity);
            if (id!=null && id>0){
                resultMsg.success();
                resultMsg.setData(authorityEntity);
                resultMsg.setMsg("新增成功");
                return resultMsg;
            }
            resultMsg.fail();
            resultMsg.setMsg("新增失败");
            return resultMsg;
        }else {//修改
            AuthorityEntity temp=authorityService.getByAuthName(authorityEntity.getAuthorityName());
            if (temp!=null && !authorityEntity.getId().equals(temp.getId())){
                resultMsg.fail();
                resultMsg.setMsg("权限名称已存在");
                return resultMsg;
            }

            int rows=authorityService.updateByPrimaryKeySelective(authorityEntity);
            if (rows>0){
                resultMsg.success();
                resultMsg.setData(authorityEntity);
                resultMsg.setMsg("修改成功");
                return resultMsg;
            }
            resultMsg.fail();
            resultMsg.setMsg("修改失败");
            return resultMsg;
        }
    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteAuth",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg deleteAuth(Integer id){
        ResultMsg resultMsg=new ResultMsg();
        if (id==null || id<=0){
            resultMsg.fail();
            resultMsg.setMsg("参数错误");
            return resultMsg;
        }
        AuthorityEntity temp=new AuthorityEntity();
        temp.setId(id);
        int rows=authorityService.delete(temp);
        if (rows > 0) {
            resultMsg.success();
            resultMsg.setMsg("删除成功");
            return resultMsg;
        }
        resultMsg.fail();
        resultMsg.setMsg("删除失败");
        return resultMsg;
    }

    /**
     * 查询所有资源并分组
     * @param request
     * @param authId
     * @return
     */
    @RequestMapping(value = "getResourceMap",method = {RequestMethod.GET})
    @ResponseBody
    public JSONObject getAllResources(HttpServletRequest request, Integer authId){
        JSONObject json = new JSONObject() ;
//        权限所拥有资源
        List<ResourceEntity> resourcesOfAuth=resourceService.getResourceByAuthId(authId);
        List<Integer> authoList = new ArrayList<>() ; // 权限资源集合
        if(resourcesOfAuth.size()>0 && resourcesOfAuth.get(0)!=null){
            for(ResourceEntity resourceEntity : resourcesOfAuth){
                authoList.add(resourceEntity.getId()) ;
            }
        }
        json.put("authorityResource", authoList);
        //所有资源信息
        List<ResourceTreeEntity> allResourceTreeEntity = resourceService.getAllResourceTreeEntity();
        //判断是否被选中 和 是否在tree中展开
        for(ResourceTreeEntity entity : allResourceTreeEntity){
            if(contain(entity.getId(),resourcesOfAuth)){
                entity.setIschecked(true);
                entity.setIsExpand(true);
                //1级资源在tree中展开
                if(new Integer(2).equals(entity.getResourceLevel())){
                    for(ResourceTreeEntity entity1 : allResourceTreeEntity){
                        if(new Integer(1).equals(entity1.getResourceLevel()) && entity.getPid().equals(entity1.getId())){
                            entity1.setIsExpand(true);
                            break;
                        }
                    }
                }
            }
            else{
                entity.setIschecked(false);
            }
        }
        json.put("array", allResourceTreeEntity);
        return json;
    }

    /**
     * 给权限赋资源
     * @param treeData
     * @param authId
     * @return
     */
    @RequestMapping(value = "giveResourcesToAuth",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg giveResourcesToAuth(String treeData , Integer authId ) {
        ResultMsg resultMsg = new ResultMsg();
        //解析树JSON 为ResourceTreeEntity集
        //所有资源的父id
        Set<Integer> resourcePid = new HashSet<>();
        List<ResourceTreeEntity> resourceTreeEntity = new ArrayList<>();
        JSONArray jsonArray = JSON.parseArray(treeData);
        for (int i=0; i<jsonArray.size(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONObject data = jsonObject.getJSONObject("data");
            ResourceTreeEntity entity = new ResourceTreeEntity() ;
            entity.setId(data.getInteger("id"));
            entity.setPid(data.getInteger("pid"));
            entity.setText(data.getString("text"));
            entity.setResourceLevel(data.getInteger("resourceLevel"));
            entity.setIschecked(true);
            resourceTreeEntity.add(entity);
            if(new Integer(20).equals(data.getInteger("resourceLevel"))){//添加所有最下级资源的父资源
                resourcePid.add(entity.getPid());
            }

        }
        //添加2级资源权限
        for(ResourceTreeEntity entity : resourceTreeEntity){
            Iterator<Integer> iterator = resourcePid.iterator();
            while(iterator.hasNext()){
                Integer next = iterator.next();
                if(entity.getId().equals(next)){
                    iterator.remove();
                }
            }
        }
        for(Integer pid : resourcePid){
            ResourceTreeEntity bossEntity = new ResourceTreeEntity() ;
            bossEntity.setId(pid);
            bossEntity.setResourceLevel(2);
            bossEntity.setIschecked(true);
            resourceTreeEntity.add(bossEntity);
        }
        //给权限赋资源
        boolean flag = authorityService.giveResourcesToAuth(resourceTreeEntity,authId);
        if(flag){

            CustomInvocationSecurityMetadataSourceImpl customInvocationSecurityMetadataSource = new CustomInvocationSecurityMetadataSourceImpl(authorityService,resourceService);
            customInvocationSecurityMetadataSource.init();
            resultMsg.success();
            resultMsg.setMsg("给权限赋资源成功");
            return resultMsg;
        }
        resultMsg.fail();
        resultMsg.setMsg("给权限赋资源失败");
        return resultMsg ;
    }

    private String toString(JSONArray resultMsg){
        return JSON.toJSONString(resultMsg, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty);
    }

    private boolean contain(int resource , List<ResourceEntity> list){
        if(list.size() > 0 && list.get(0)!=null){
            for(int i=0;i<list.size();i++){
                if(resource == list.get(i).getId()){
                    return true ;
                }
            }
        }
        return false ;
    }

    private boolean compare(int resource , List<Integer> list){
        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                if(resource == list.get(i)){
                    return true ;
                }
            }
        }
        return false ;
    }


    /**
     * 重新加载权限 Map
     */
    @RequestMapping(value = "/restartMap",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg restartMap(){
        ResultMsg resultMsg = new ResultMsg() ;
        try{
            CustomInvocationSecurityMetadataSourceImpl customInvocationSecurityMetadataSource = new CustomInvocationSecurityMetadataSourceImpl(authorityService,resourceService);
            customInvocationSecurityMetadataSource.init();
            resultMsg.setMsg("重新加载资源成功!");
            resultMsg.success();
        }catch (Exception e){
            resultMsg.fail();
            resultMsg.setMsg("重新加载资源失败!");
            e.printStackTrace();
        }
        return resultMsg ;
    }

}
