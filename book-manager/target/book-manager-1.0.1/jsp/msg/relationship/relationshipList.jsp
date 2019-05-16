<%--
  Created by IntelliJ IDEA.
  User: teela
  Date: 2016/11/1
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.wallet.common.util.PageUtil"%>
<%@page import="com.wallet.common.MessageCode"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/freTaglib.tld" prefix="fre"%>
<%PageUtil.noCache(request, response);%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="Content-Language" content="utf-8" />
  <meta http-equiv="Pragma" content="no-cache" />
  <meta name="robots" content="all" />
  <meta name="Author" content="Ivy" />
  <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
  <link  type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/global.css" media="screen"/>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/composite.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/DatePicker/WdatePicker.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
  <title><s:text name="g_queryRelationship"/></title>
  <script type="text/javascript">
    /**
     * 校验
     */
    function validate(){
      showError("error","","hidden");
      if(dateCompare('startDate','endDate') == false){
        showError('error','<s:text name="g_checkTime"/>','visible');
        return false;
      }
      return true;
    }
    //查询
    function searchNow(){
      if(validate() == false){
        return false;
      }
      if(document.forms[0].page)
      {
        document.forms[0].page.value = 1;
      }
      try{
        jscomLockScreenToWaitf('<s:text name="g_queryTip"/>');
      }catch(e){}
      document.forms[0].submit();
    }

  </script>
</head>
<body>
<jsp:include page="/jsp/header.jsp" flush="true"/>
<!--content begin-->
<div id="content" class="boxshadow">
  <!--nav begin-->
  <jsp:include page="/jsp/leftMenu.jsp?higKey=RelationshipList" flush="true"/>
  <!--nav end-->

  <div id="rightbox"> <a href="#" title="" class="lefthide"></a>
    <div class="breadcrumbs"><s:text name="g_position"/>：<s:text name="g_attachManager"/>&gt;<s:text name="g_queryRelationship"/> </div>
    <div class="formbox">
      <h3><span class="topradius fbshadow"><s:text name="g_queryRelationship"/></span></h3>
      <form name="formPage" action="RelationshipAction!queryRelationshipList.action" id="formPage" method="post">
        <%=PageUtil.getEncoderSessionIdHid(request)%>
        <div class="infobox divbg">
          <div>
            <label><s:text name="g_username"/> :</label>
            <input type="text" class="textstyle" id="relationship.username" name="relationship.username" value="<s:property value="relationship.username"/>" style="width: 150px;"/>&nbsp;

            <label><s:text name="g_otherUsername"/> :</label>
            <input type="text" class="textstyle" id="relationship.otherUsername" name="relationship.otherUsername" value="<s:property value="relationship.otherUsername"/>" style="width: 150px;"/>&nbsp;

            <label><s:text name="g_shipType"/>: </label>
            <select id="relationship.shipType" name="relationship.shipType">
              <option value="">
                <!--所有-->
                <s:text name="all" />
              </option>
              <s:iterator value="shipTypeMap" status="stuts1">
                <option value="<s:property value="key"/>"
                        <s:if test="relationship.shipType == key">selected</s:if>>
                  <s:property value="value" />
                </option>
              </s:iterator>
            </select>

            <label><s:text name="g_msgState"/>: </label>
            <select id="relationship.state" name="relationship.state">
              <option value="">
                <!--所有-->
                <s:text name="all" />
              </option>
              <s:iterator value="stateMap" status="stuts2">
                <option value="<s:property value="key"/>"
                        <s:if test="relationship.state == key">selected</s:if>>
                  <s:property value="value" />
                </option>
              </s:iterator>
            </select>
          </div>
          <p>
            <label><s:text name="g_createDate"/> :</label>
            <input type="text" name="relationship.startDate" onfocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'<%=com.wallet.common.Constant.DAY_FORMAT%>',maxDate:'#F{$dp.$D(\'endDate\')||\'2020-10-01\'}'})" value="<s:date name="relationship.startDate" format="%{@com.wallet.common.Constant@DAY_FORMAT}"/>" id="startDate" class="Wdate" readonly="readonly"/>
            &nbsp;<s:text name="g_to"/>&nbsp;
            <input type="text" name="relationship.endDate" onfocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'<%=com.wallet.common.Constant.DAY_FORMAT%>',minDate:'#F{$dp.$D(\'startDate\')}',maxDate:'2020-10-01'})"   value="<s:date name="relationship.endDate" format="%{@com.wallet.common.Constant@DAY_FORMAT}"/>" id="endDate" class="Wdate" readonly="readonly"/>
          </p>
          <a class="butblue radius" title="<s:text name="search"/>" href="#" onclick="searchNow();"><s:text name="search"/></a>
          <a class="butblue radius" title="<s:text name="res_clear"/>" href="#" onclick="cleanQryConditions();"><s:text name="res_clear"/></a>
          <%--<fre:authTag authCode="addRole">
            <a class="butblue radius" title="<s:text name="search"/>" href="RoleAction!preAddRole.action" ><s:text name="g_addRole"/></a>
          </fre:authTag>--%>
        </div>
        <div id="show" class="tableh">
          <table summary="">
            <thead>
            <tr>
              <th><s:text name="g_username"/></th>
              <th><s:text name="g_otherUsername"/></th>
              <th><s:text name="g_nickname"/></th>
              <th><s:text name="g_msgState"/></th>
              <th><s:text name="g_shipType"/></th>
              <th><s:text name="g_newMessageId"/></th>
              <th><s:text name="g_lastReadMessageId"/></th>
              <th><s:text name="g_createDate"/></th>
              <th>sessionId</th>
              <th><s:text name="g_operation"/></th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="relationshipList" status="stuts">
              <tr>
                <td><s:property value="username"/></td>
                <td><s:property value="otherUsername"/></td>
                <td><s:property value="nickName"/></td>
                <td><s:property value="stateStr"/></td>
                <td><s:property value="shipTypeStr"/></td>
                <td><s:property value="newMessageId"/></td>
                <td><s:property value="lastReadMessageId"/></td>
                <td><s:date name="createAt" format="%{@com.wallet.common.Constant@DAY_TIME_FORMAT}"/></td>
                <td><s:property value="sessionId"/></td>
                <td>
                    <fre:authTag authCode="updateRelationship">
                      <a href="RelationshipAction!queryRelationshipById.action?relationship.id=<s:property value="id"/>"><s:text name="g_editor"/></a>
                    </fre:authTag>
                </td>
              </tr>
            </s:iterator>
            </tbody>
          </table>
          <div align="right">
            <s:property value="pageMenu" escape="false"/>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>

<!--content end-->
<div id="footer">
  <p>&nbsp;</p>
</div>

<!--[if IE 6]>
<script type="text/javascript" src="../../js/resize_ie6.js"></script>
<![endif]-->
</body>
</html>



