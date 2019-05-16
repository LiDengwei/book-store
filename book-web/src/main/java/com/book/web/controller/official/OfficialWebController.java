//package com.book.web.controller.official;
//
//
//import com.alibaba.fastjson.JSONObject;
//import com.offical.common.ResultMsg;
//import com.offical.page.PageBean;
//import com.offical.service.api.official.OfficialWebApi;
//import com.offical.service.model.official.OfficialWeb;
//import com.offical.util.StringUtil;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Objects;
//
///**
// * 官网公告
// * Created by Nyx on 2018/3/10.
// */
//@RestController
//@Api(value = "官网公告API", description = "官网公告相关接口")
//@RequestMapping(value = "/official", produces = "application/json;charset=UTF-8")
//public class OfficialWebController{
//
//    private static org.slf4j.Logger radarLoginLogger = LoggerFactory.getLogger("RadarLoginLogger");
//
//    @Autowired
//    private OfficialWebApi officialWebApi;
//
//    /**
//     * 官网公告列表查询(多参,通过实体封装来查询)
//     * @return
//     * https://web.exploitsmart.com/official/preQueryOfficialWeb
//     * 接口说明：查询公告列表
//     * 请求方式：post和get都可以
//     * 参数：OfficialWeb，pageIndex，pageSize
//     */
//    @ApiOperation(value="查询公告列表")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "officialWebJson", value = "{\"id\":\"11\",\"title\":\"单反\",\"terraceName\":\"BE\",\"status\":\"1\",\"userName\":\"admin\",\"terrace\":\"4\",\"language\":\"2\"}", required = true,paramType = "query",dataType = "String"),
//            @ApiImplicitParam(name = "pageIndex", value = "页码", required = false,paramType = "query",dataType = "Integer"),
//            @ApiImplicitParam(name = "pageSize", value = "每页显示数", required = false,paramType = "query",dataType = "Integer")
//    })
//    @RequestMapping(value = { "/preQueryOfficialWeb" } ,method = RequestMethod.POST)
//    public ResultMsg preQueryOfficialWeb(String officialWebJson, HttpServletRequest request, Integer pageIndex, Integer pageSize){
//        ResultMsg resultMsg = new ResultMsg();
//        resultMsg.fail();
//
//        //Json字符串转为Java对象
//        JSONObject jsonObject = JSONObject.parseObject(officialWebJson);
//        OfficialWeb officialWeb = JSONObject.toJavaObject(jsonObject,OfficialWeb.class);
//
//        //校验
//        if(Objects.isNull(officialWeb)){
//            resultMsg.setMsg("没有访问信息");
//            return resultMsg;
//        }
//        if(officialWeb.getTerrace() == null){
//            resultMsg.setMsg("没有访问平台编号");
//            return resultMsg;
//        }
//        if(officialWeb.getLanguage() == null){
//            resultMsg.setMsg("没有访问语言");
//            return resultMsg;
//        }
//
//        if(pageIndex == null || pageIndex < 0){
//            pageIndex = 1 ;
//        }
//        if(pageSize == null || pageSize < 0){
//            pageSize = 20 ;
//        }
//
//        PageBean pageBean=new PageBean();
//        pageBean.setPageNo(pageIndex);
//        pageBean.setPageSize(pageSize);
//        officialWeb.setStatus(OfficialWeb.StatusEnum.EMPLOY.getValue());//只查询状态可使用的
//        pageBean.setData(officialWeb);
//
//        pageBean = officialWebApi.selectTitleByPageBeanSlave(pageBean);
//        if(pageBean != null && pageBean.getResults()!=null && pageBean.getResults().size() > 0){
//            resultMsg.setData(pageBean);
//            resultMsg.success();
//            return resultMsg;
//        }
//        resultMsg.setMsg("没有数据");
//        return resultMsg;
//    }
//
//    /**
//     * 官网公告列表查询(少参,通过传递几个参数来查询)
//     * @param terraceId  平台ID  2.Discovery，3.Q网，4.PK
//     * @param language  语言 1.中文 2.英文
//     * @param pageIndex 页码
//     * @param pageSize 页数
//     *
//     * https://web.exploitsmart.com/official/getofficials
//     *
//     * 接口说明：查询公告列表
//     * 请求方式：post和get都可以
//     * 参数：
//     * String        terraceId，平台ID  2.Discovery，3.Q网，4.PK
//     * String        language ，语言 1.中文 2.英文
//     * Integer       pageIndex，页码
//     * Integer       pageSize，页数
//     * @return
//     * @auther danny 2018/07/20
//     */
//    @ApiOperation(value="官网公告列表查询")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "terraceId", value = "平台ID ", required = true,paramType = "query",dataType = "Integer"),
//            @ApiImplicitParam(name = "language", value = "语言", required = true,paramType = "query",dataType = "String"),
//            @ApiImplicitParam(name = "pageIndex", value = "页码", required = false,paramType = "query",dataType = "Integer"),
//            @ApiImplicitParam(name = "pageSize", value = "每页显示数", required = false,paramType = "query",dataType = "Integer")
//    })
//    @RequestMapping(value = { "/getofficials" } ,method = RequestMethod.POST)
//    public ResultMsg getofficials(HttpServletRequest request,String terraceId, String language , Integer pageIndex, Integer pageSize){
//        ResultMsg resultMsg = new ResultMsg();
//
//        radarLoginLogger.info("-----getofficials   terraceId = " + terraceId + ", language = " + language + ",pageIndex = " + pageIndex + ",pageSize = " + pageSize);
//        resultMsg.fail();
//        //校验
//        if(StringUtil.isEmpty(terraceId)){
//            resultMsg.setMsg("缺少平台ID");
//            return resultMsg;
//        }
//        if(StringUtil.isEmpty(language)){
//            resultMsg.setMsg("没有访问语言");
//            return resultMsg;
//        }
//
//        if(pageIndex == null || pageIndex < 0){
//            pageIndex = 1 ;
//        }
//        if(pageSize == null || pageSize < 0){
//            pageSize = 20 ;
//        }
//
//        try {
//            PageBean pageBean=new PageBean();
//            pageBean.setPageNo(pageIndex);
//            pageBean.setPageSize(pageSize);
//            OfficialWeb officialWeb = new OfficialWeb();
//            officialWeb.setTerrace(Integer.parseInt(terraceId));
//            officialWeb.setLanguage(Integer.parseInt(language));
//            officialWeb.setStatus(OfficialWeb.StatusEnum.EMPLOY.getValue());//只查询状态可使用的
//            pageBean.setData(officialWeb);
//            OfficialWeb officialWeb1 = (OfficialWeb) pageBean.getData();
//            radarLoginLogger.info("-----getofficials   ------------" + officialWeb1.getLanguage() + "," + officialWeb1.getTerrace() + "," + officialWeb1.getStatus());
//            pageBean = officialWebApi.selectTitleByPageBeanSlave(pageBean);
//            if(pageBean != null && pageBean.getResults()!=null && pageBean.getResults().size() > 0){
//                pageBean.setData(null);
//                resultMsg.setData(pageBean);
//                resultMsg.success();
//                return resultMsg;
//            }
//            resultMsg.success();
//            resultMsg.setMsg("没有数据");
//            return resultMsg;
//        }catch (Exception e){
//            resultMsg.setMsg("数据查询异常");
//            return resultMsg;
//        }
//    }
//
//    /**
//     * 官网公告根据id查询单个公告详情
//     * @return
//     *
//     * https://web.exploitsmart.com/official/queryOfficialWeb
//     * 接口说明：根据id查询单个公告详情
//     * 请求方式：post和get都可以
//     * 参数：主键id
//     */
//    @ApiOperation(value="根据id查询单个公告详情")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "ID ", required = true,paramType = "query",dataType = "Long"),
//    })
//    @RequestMapping(value = { "/queryOfficialWeb" } ,method = RequestMethod.POST)
//    public ResultMsg queryOfficialWeb(HttpServletRequest request, Long id){
//        ResultMsg resultMsg = new ResultMsg();
//        resultMsg.fail();
//
//        //获取客户端ip
////        String ip = RealIpUtils.getClientIP(request);
////        //获取允许访问ip
////        String IP_OPEN_GIFT_DEBRIS = "";
////        SysConfig sysconfig = sysConfigtServiceApi.getByConfigKeySlave("IP_OPEN_GIFT_DEBRIS");
////        if (sysconfig != null && !StringUtil.isEmpty(sysconfig.getConfigValue())) {
////            IP_OPEN_GIFT_DEBRIS = sysconfig.getConfigValue();
////        }
////        if(StringUtil.isEmpty(IP_OPEN_GIFT_DEBRIS)){
////            resultMsg.setMsg("IP限制不能访问1");
////            return resultMsg;
////        }
////        //判断请求ip是否被允许访问
////        String [] ips = IP_OPEN_GIFT_DEBRIS.split(",");
////        boolean checkResult = false;
////        for (int i = 0; i < ips.length; i++) {
////            String tmp = ips[i];
////            if(tmp.equals(ip)){
////                checkResult = true;
////                break;
////            }
////        }
////        if(!checkResult){
////            resultMsg.setMsg("IP限制不能访问2");
////            return resultMsg;
////        }
//        //校验
//        if(null == id){
//            resultMsg.setMsg("没有访问信息");
//            return resultMsg;
//        }
//
//        OfficialWeb officialWeb = officialWebApi.selectByPrimaryKeySlave(id);
//        if(officialWeb != null){
//            resultMsg.setData(officialWeb);
//            resultMsg.success();
//            return resultMsg;
//        }
//        resultMsg.setMsg("没有数据");
//        return resultMsg;
//    }
//}
