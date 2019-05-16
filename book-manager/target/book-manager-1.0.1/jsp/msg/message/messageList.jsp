<%--
  Created by IntelliJ IDEA.
  User: teela
  Date: 2016/10/31
  Time: 15:06
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
  <title><s:text name="g_queryMessage"/></title>
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
  <jsp:include page="/jsp/leftMenu.jsp?higKey=MessageList" flush="true"/>
  <!--nav end-->

  <div id="rightbox"> <a href="#" title="" class="lefthide"></a>
    <div class="breadcrumbs"><s:text name="g_position"/>：<s:text name="g_attachManager"/>&gt;<s:text name="g_queryMessage"/> </div>
    <div class="formbox">
      <h3><span class="topradius fbshadow"><s:text name="g_queryMessage"/></span></h3>
      <form name="formPage" action="MessageAction!queryMessageList.action" id="formPage" method="post">
        <%=PageUtil.getEncoderSessionIdHid(request)%>
        <div class="infobox divbg">
          <div>
            <label><s:text name="g_sendUsername"/> :</label>
            <input type="text" class="textstyle" id="messageMeta.username" name="messageMeta.username" value="<s:property value="messageMeta.username"/>" style="width: 150px;"/>&nbsp;

            <label><s:text name="g_receiveUsername"/> :</label>
            <input type="text" class="textstyle" id="messageMeta.receiveUsername" name="messageMeta.receiveUsername" value="<s:property value="messageMeta.receiveUsername"/>" style="width: 150px;"/>&nbsp;

            <label><s:text name="g_msgType"/>: </label>
            <select id="messageMeta.messageType" name="messageMeta.messageType">
              <option value="">
                <!--所有-->
                <s:text name="all" />
              </option>
              <s:iterator value="messageTypeMap" status="stuts">
                <option value="<s:property value="key"/>"
                        <s:if test="messageMeta.messageType == key">selected</s:if>>
                  <s:property value="value" />
                </option>
              </s:iterator>
            </select>

            <label><s:text name="g_msgState"/>: </label>
            <select id="messageMeta.state" name="messageMeta.state">
              <option value="">
                <!--所有-->
                <s:text name="all" />
              </option>
              <s:iterator value="stateMap" status="stuts">
                <option value="<s:property value="key"/>"
                        <s:if test="messageMeta.state == key">selected</s:if>>
                  <s:property value="value" />
                </option>
              </s:iterator>
            </select>
          </div>
          <p>
            <label><s:text name="g_createDate"/> :</label>
            <input type="text" name="messageMeta.startDate" onfocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'<%=com.wallet.common.Constant.DAY_FORMAT%>',maxDate:'#F{$dp.$D(\'endDate\')||\'2020-10-01\'}'})" value="<s:date name="messageMeta.startDate" format="%{@com.wallet.common.Constant@DAY_FORMAT}"/>" id="startDate" class="Wdate" readonly="readonly"/>
            &nbsp;<s:text name="g_to"/>&nbsp;
            <input type="text" name="messageMeta.endDate" onfocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'<%=com.wallet.common.Constant.DAY_FORMAT%>',minDate:'#F{$dp.$D(\'startDate\')}',maxDate:'2020-10-01'})"   value="<s:date name="messageMeta.endDate" format="%{@com.wallet.common.Constant@DAY_FORMAT}"/>" id="endDate" class="Wdate" readonly="readonly"/>
          </p>
          <a class="butblue radius" title="<s:text name="search"/>" href="#" onclick="searchNow();"><s:text name="search"/></a>
          <a class="butblue radius" title="<s:text name="res_clear"/>" href="#" onclick="cleanQryConditions();"><s:text name="res_clear"/></a>
          <fre:authTag authCode="addMessage">
            <a class="butblue radius" title="<s:text name="g_addMessage"/>" href="MessageAction!preAddMessage.action" ><s:text name="g_addMessage"/></a>
          </fre:authTag>
        </div>
        <div id="show" class="tableh">
          <table summary="">
            <thead>
            <tr>
              <th><s:text name="g_sendUsername"/></th>
              <th><s:text name="g_receiveUsername"/></th>
              <th><s:text name="g_sessionId"/></th>
              <th><s:text name="g_bussinessId"/></th>
              <th><s:text name="g_message"/></th>
              <th><s:text name="g_msgType"/></th>
              <th><s:text name="g_msgState"/></th>
              <th><s:text name="g_createDate"/></th>
              <th><s:text name="g_operation"/></th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="messageList" status="stuts">
              <tr>
                <td><s:property value="username"/></td>
                <td><s:property value="receiveUsername"/></td>
                <td><s:property value="sessionId"/></td>
                <td><s:property value="businessId"/></td>
                <td>
                  <p title="<s:property value="message"/>" style="max-width: 200px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
                    <s:property value="message"/>
                  </p>
                </td>
                <td><s:property value="messageTypeStr"/></td>
                <td><s:property value="stateStr"/></td>
                <td><s:date name="%{new java.util.Date(createAt)}" format="%{@com.wallet.common.Constant@DAY_TIME_FORMAT}"/></td>
                <td>
                  <fre:authTag authCode="updateMessage">
                    <a href="MessageAction!queryMessageById.action?messageMeta.id=<s:property value="id"/>"><s:text name="g_editor"/></a>
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


