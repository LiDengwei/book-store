package com.book.manager.controller.security;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.book.manager.api.service.ResourceService;
import com.book.manager.domain.security.ResourceEntity;
import com.book.manager.util.ResultMsg;
import com.book.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by teela on 2017/3/31.
 */
@Controller
@RequestMapping(value = "/resource",produces = "application/json;charset=utf-8")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    /**
     * 查询资源信息
     * @return
     */
    @RequestMapping(value = "preQueryResourceList",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView preQueryResourceList(){
        return new ModelAndView("/view/sysuser/resourceList");
    }


    /**
     * 查询资源信息
     * @param resourceEntity
     * @return
     */
    @RequestMapping(value = "queryResourceList",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String resourceList(HttpServletRequest request, ResourceEntity resourceEntity, Integer pageIndex){
        if (pageIndex==null || pageIndex==0){
            pageIndex=1;
        }
        resourceEntity.setPage(pageIndex);
        List<ResourceEntity> entities=resourceService.selectByPage(resourceEntity);
        int totalRows=resourceService.countByPage(resourceEntity);
        resourceEntity.setTotalRows(totalRows);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("Rows",entities);
        jsonObject.put("Total",totalRows);
        return jsonObject.toJSONString();
    }

    /**
     * 进入新增资源页面
     * @param resourceId
     * @return
     */
    @RequestMapping(value = "preCreateResource",method = {RequestMethod.GET,RequestMethod.POST})
    public String preCreateResource(HttpServletRequest request, Integer resourceId){
        ResourceEntity entity=null;
        if (resourceId!=null && resourceId>0){
            entity=resourceService.getById(resourceId);
        }
        request.setAttribute("resource",entity);
        return "/view/sysuser/createResource";
//        return new ModelAndView("/view/createResource","resource",entity);
    }

    /**
     * 新增资源
     * @param resourceEntity
     * @return
     */
    @RequestMapping(value = "createResource",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg createResource(ResourceEntity resourceEntity){
        ResultMsg resultMsg=new ResultMsg();
        if (StringUtil.isEmpty(resourceEntity.getId()) || 0==resourceEntity.getId()) {//新增
            ResourceEntity temp=resourceService.getByUrl(resourceEntity.getResourceUrl());
            if (temp!=null && resourceEntity.getParentId()!=0){
                resultMsg.fail();
                resultMsg.setMsg("资源URL已存在");
                return resultMsg;
            }
            resourceEntity.setEnabled(ResourceEntity.EnabledEnum.YES.getValue());
            resourceEntity.setIsSys(ResourceEntity.IsSysEnum.NO.getValue());
            resourceEntity.setCreateDate(new Date());
            Integer id = resourceService.insert(resourceEntity);
            if (id != null && id > 0) {
                resultMsg.success();
                resultMsg.setData(resourceEntity);
                resultMsg.setMsg("新增成功");
                return resultMsg;
            }
            resultMsg.fail();
            resultMsg.setMsg("新增失败");
            return resultMsg;
        }else {//修改
            ResourceEntity temp=resourceService.getByUrl(resourceEntity.getResourceUrl());
            String a = "/" ;
            if (temp==null){
                boolean T = false;
            }else {
                boolean T= (temp.getResourceUrl().equals(a));
                if (temp!=null && !temp.getId().equals(resourceEntity.getId())&& !T){
                    resultMsg.fail();
                    resultMsg.setMsg("资源URL已存在");
                    return resultMsg;
                }
            }
            int rows=resourceService.updateByPrimaryKeySelective(resourceEntity);
            if (rows>0){
                resultMsg.success();
                resultMsg.setData(resourceEntity);
                resultMsg.setMsg("修改成功");
                return resultMsg;
            }
            resultMsg.fail();
            resultMsg.setMsg("修改失败");
            return resultMsg;
        }
    }

    /**
     * 删除资源
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteResources",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg deleteResources(Integer id){
        ResultMsg resultMsg=new ResultMsg();
        if (id==null || id<=0){
            resultMsg.fail();
            resultMsg.setMsg("参数错误");
            return resultMsg;
        }

        ResourceEntity temp=new ResourceEntity();
        temp.setId(id);
        int rows=resourceService.delete(temp);
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
     * 获取所以一级和二级资源
     * @return
     */
    @RequestMapping(value = "/getAllMenu" , method = {RequestMethod.POST})
    @ResponseBody
    public JSONObject getAllMenu(@RequestParam(required = false) Integer parentId , @RequestParam(required = false) Integer resourceLevel){
        JSONObject jsonObject = new JSONObject() ;
        JSONArray menu = new JSONArray() ;  // 一级权限名称
        JSONArray secondary = new JSONArray() ; // 二级权限名称
        List<ResourceEntity> list = null ;
        if(parentId == null){
             list  = resourceService.getSubMenu(0) ;
            for(ResourceEntity resourceEntity : list){
                if(resourceEntity.getResourceLevel() == 1){
                    menu.add(resourceEntity) ;
                }
                else{
                    continue;
                }
            }
            jsonObject.put("menu" , menu) ;
        }
        else if(parentId!=null && resourceLevel == null){ // 查找父目录下的所有子目录
            list = resourceService.getSubMenu(parentId) ;
            for(ResourceEntity resourceEntity : list){
                secondary.add(resourceEntity) ;
            }
            jsonObject.put("second",secondary) ;
        }
        else if(resourceLevel != null){
            ResourceEntity resourceEntity = new ResourceEntity() ;
            resourceEntity = resourceService.getResourceFather(parentId) ;
            jsonObject.put("resource",resourceEntity) ;
        }
        return jsonObject ;
    }
}
