<%--
  Created by IntelliJ IDEA.
  User: teela
  Date: 2016/11/1
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.wallet.common.util.PageUtil"%>
<%@page import="com.wallet.common.MessageCode"%>
<%@page import="com.wallet.common.util.ConfigUtil"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%PageUtil.noCache(request, response);%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="Content-Language" content="utf-8" />
  <meta http-equiv="Pragma" content="no-cache" />
  <meta name="robots" content="all" />
  <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

  <title><s:text name="g_updateMessage"/></title>
  <link  type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/global.css" media="screen"/>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/composite.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/inputtext.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.colorbox-min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/popup.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>

  <script type="text/javascript" >
    function goback(){
      window.location.href="MessageAction!queryMessageList.action";
    }
    function validate(){
      showError("error","","hidden");
      if(isEmpty('menu.menuName')) {
        showError('error','<s:text name="g_menuNameNull"/>','visible');
        return false;
      }
      if(isEmpty("menu.menuCode")){
        showError('error','<s:text name="g_menuCodeNull"/>','visible');
        return false;
      }

    }

    function updateMenu(){
      if(validate() == false){
        return false;
      }
      document.getElementById("form1").submit();
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
  <div id="rightbox">
    <a href="#" title="" class="lefthide"></a>
    <div class="breadcrumbs"><s:text name="g_position"/>：<s:text name="g_attachManager"/> &gt; <s:text name="g_queryMessage"/> &gt; <s:text name="g_updateMessage"/></div>
    <div class="formbox">
      <form action="" id="form1" method="post">
        <input type="hidden" name="messageMeta.id" value="<s:property value="messageMeta.id"/>"/>
        <%--<input type="hidden" name="operaterType" value="1"/>--%>
        <%=PageUtil.getEncoderSessionIdHid(request)%>
        <div class="formbox margintop">
          <h3><span class="topradius fbshadow"><s:text name="g_updateMessage"/> </span></h3>
          <table style="width: 500px;height: 100px;margin-top: 20px;">
            <tr>
              <td><s:text name="g_sessionId"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.sessionId" name="messageMeta.sessionId" value="<s:property value="messageMeta.sessionId"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_sendUsername"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.username" name="messageMeta.username" value="<s:property value="messageMeta.username"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_sendMsgAddress"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.address" name="messageMeta.address" value="<s:property value="messageMeta.address"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_receiveUsername"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.receiveUsername" name="messageMeta.receiveUsername" value="<s:property value="messageMeta.receiveUsername"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_receiveAddress"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.receiveAddress" name="messageMeta.receiveAddress" value="<s:property value="messageMeta.receiveAddress"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_message"/><s:text name="g_mineOrOther"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.message" name="messageMeta.message" value="<s:property value="messageMeta.message"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_message"/><s:text name="g_other"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.messageV2" name="messageMeta.messageV2" value="<s:property value="messageMeta.messageV2"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_data"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.data" name="messageMeta.data" value="<s:property value="messageMeta.data"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_imgUrl"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.imgUrl" name="messageMeta.imgUrl" value="<s:property value="messageMeta.imgUrl"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_url"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.url" name="messageMeta.url" value="<s:property value="messageMeta.url"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_msgType"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.messageTypeStr" name="messageMeta.messageTypeStr" value="<s:property value="messageMeta.messageTypeStr"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_msgState"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.stateStr" name="messageMeta.stateStr" value="<s:property value="messageMeta.stateStr"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_bussinessId"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.businessId" name="messageMeta.businessId" value="<s:property value="messageMeta.businessId"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_messageFlag"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.messageFlagStr" name="messageMeta.messageFlagStr" value="<s:property value="messageMeta.messageFlagStr"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_clientFlag"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.clientFlag" name="messageMeta.clientFlag" value="<s:property value="messageMeta.clientFlag"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_checkFlag"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.checkFlag" name="messageMeta.checkFlag" value="<s:property value="messageMeta.checkFlag"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_backup1"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.backup1" name="messageMeta.backup1" value="<s:property value="messageMeta.backup1"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_backup2"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.backup2" name="messageMeta.backup2" value="<s:property value="messageMeta.backup2"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_createDate"/>：</td>
              <td>
                <s:date name="%{new java.util.Date(messageMeta.createAt)}" format="%{@com.wallet.common.Constant@DAY_TIME_FORMAT}"/>
                <%--<input style="width: 180px;" type="text" id="messageMeta.createAt" name="messageMeta.createAt" value="<s:property value="messageMeta.createAt"/>"/>--%>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_updateDate"/>：</td>
              <td>
                <s:date name="%{new java.util.Date(messageMeta.updateAt)}" format="%{@com.wallet.common.Constant@DAY_TIME_FORMAT}"/>
                <%--<input style="width: 180px;" type="text" id="messageMeta.updateAt" name="messageMeta.updateAt" value="<s:property value="messageMeta.updateAt"/>"/>--%>
              </td>
            </tr>
          </table>
        </div>
        <p id="p.error" class="error" style="visibility: hidden"></p>
        <div class="buttonbox">
          <%--<a href="#" onclick="updateMenu();" id="btnConfirm" title="<s:text name='g_update'/>" class="but"><span><s:text name="g_update"/></span></a>--%>
          <a href="#" onclick="goback();" title="<s:text name='g_back'/>" class="but"><span><s:text name="g_back"/></span></a>
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
<script language="javascript">
  var showMsg="${message}";
  if(showMsg!=null && showMsg!="")
  {
    alert(showMsg);
  }
</script>
</body>
</html>
