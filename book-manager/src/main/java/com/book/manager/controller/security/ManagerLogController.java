package com.book.manager.controller.security;

import com.alibaba.fastjson.JSONObject;
import com.book.manager.api.service.ManagerLogService;
import com.book.manager.base.ParentController;
import com.book.manager.domain.security.ManagerLogEntity;
import com.book.util.redis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

/**
 * Created by teela on 2018/5/12.
 */
@Controller
@RequestMapping(value = "/managerLog",produces = "application/json;charset=utf-8")
public class ManagerLogController extends ParentController {
    @Autowired
    private ManagerLogService managerLogService;
    @Autowired
    private JedisClient jedisClient ;

    /**
     *
     * @return
     */
    @RequestMapping(value = "preQueryManagerLogList",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView preQueryManagerLogList(){
        return new ModelAndView("/view/system/queryManagerLogList");
    }

    /**
     *
     * @param managerLogEntity
     * @return
     */
    @RequestMapping(value = "queryManagerLogList",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String queryManagerLogList(HttpServletRequest request, ManagerLogEntity managerLogEntity, Integer pageIndex){
        if (pageIndex==null || pageIndex==0){
            pageIndex=1;
        }
        managerLogEntity.setPage(pageIndex);
        List<ManagerLogEntity> entities=managerLogService.selectByPage(managerLogEntity);
        int totalRows=managerLogService.countByPage(managerLogEntity);
        managerLogEntity.setTotalRows(totalRows);
        JSONObject jsonObject=new JSONObject();
        Iterator iterator = entities.iterator() ;
        while (iterator.hasNext()){
            ManagerLogEntity managerLogEntity1 = (ManagerLogEntity) iterator.next();
            managerLogEntity1.setCreateAtStr(changeDate(managerLogEntity1.getCreateAt()));
            managerLogEntity1.setUpdateAtStr(changeDate(managerLogEntity1.getUpdateAt()));
        }
        jsonObject.put("Rows", entities);
        jsonObject.put("Total",totalRows);
        return jsonObject.toJSONString();
    }


}
