<%--
  Created by IntelliJ IDEA.
  User: teela
  Date: 2016/11/1
  Time: 18:13
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

  <title><s:text name="g_updateRelationship"/></title>
  <link  type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/global.css" media="screen"/>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/composite.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/inputtext.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.colorbox-min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/popup.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>

  <script type="text/javascript" >
    function goback(){
      window.location.href="RelationshipAction!queryRelationshipList.action";
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
  <jsp:include page="/jsp/leftMenu.jsp?higKey=RelationshipList" flush="true"/>
  <!--nav end-->
  <div id="rightbox">
    <a href="#" title="" class="lefthide"></a>
    <div class="breadcrumbs"><s:text name="g_position"/>：<s:text name="g_attachManager"/> &gt; <s:text name="g_queryRelationship"/> &gt; <s:text name="g_updateRelationship"/></div>
    <div class="formbox">
      <form action="" id="form1" method="post">
        <input type="hidden" name="relationship.id" value="<s:property value="relationship.id"/>"/>
        <%--<input type="hidden" name="operaterType" value="1"/>--%>
        <%=PageUtil.getEncoderSessionIdHid(request)%>
        <div class="formbox margintop">
          <h3><span class="topradius fbshadow"><s:text name="g_updateRelationship"/> </span></h3>
          <table style="width: 500px;height: 100px;margin-top: 20px;">
            <tr>
              <td><s:text name="g_username"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="relationship.username" name="relationship.username" value="<s:property value="relationship.username"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_userAddress"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="relationship.address" name="relationship.address" value="<s:property value="relationship.address"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_otherUsername"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="relationship.otherUsername" name="relationship.otherUsername" value="<s:property value="relationship.otherUsername"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_otherAddress"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="relationship.otherAddress" name="relationship.otherAddress" value="<s:property value="relationship.otherAddress"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_nickname"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="relationship.nickName" name="relationship.nickName" value="<s:property value="relationship.nickName"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_msgState"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="relationship.stateStr" name="relationship.stateStr" value="<s:property value="relationship.stateStr"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_shipType"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="relationship.shipTypeStr" name="relationship.shipTypeStr" value="<s:property value="relationship.shipTypeStr"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_data"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="relationship.data" name="relationship.data" value="<s:property value="relationship.data"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_unReadCount"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="relationship.unReadCount" name="relationship.unReadCount" value="<s:property value="relationship.unReadCount"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_sessionIdShip"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="relationship.sessionId" name="relationship.sessionId" value="<s:property value="relationship.sessionId"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_lastReadMessageId"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="relationship.lastReadMessageId" name="relationship.lastReadMessageId" value="<s:property value="relationship.lastReadMessageId"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_newMessageId"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="relationship.newMessageId" name="relationship.newMessageId" value="<s:property value="relationship.newMessageId"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_rev1"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="relationship.rev1" name="relationship.rev1" value="<s:property value="relationship.rev1"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_createDate"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="relationship.createAt" name="relationship.createAt" value="<s:property value="relationship.createAt"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_updateDate"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="relationship.updateAt" name="relationship.updateAt" value="<s:property value="relationship.updateAt"/>"/>
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

