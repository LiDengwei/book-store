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

  <title><s:text name="g_addRole"/></title>
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
      if(isEmpty('messageMeta.sessionId')) {
        showError('error','<s:text name="g_sessionIdNull"/>','visible');
        return false;
      }
      if(isEmpty('messageMeta.username')) {
        showError('error','<s:text name="g_usernameNull"/>','visible');
        return false;
      }
      if(isEmpty('messageMeta.message')) {
        showError('error','<s:text name="g_messageNull"/>','visible');
        return false;
      }
    }

    function addRole(){
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
    <div class="breadcrumbs"><s:text name="g_position"/>：<s:text name="g_attachManager"/> &gt; <s:text name="g_queryMessage"/> &gt; <s:text name="g_addMessage"/></div>
    <div class="formbox">
      <form action="MessageAction!addMessage.action" id="form1" method="post">
        <%=PageUtil.getEncoderSessionIdHid(request)%>
        <input type="hidden" id="messageMeta.id" name="messageMeta.id" value="<s:property value="messageMeta.id"/>"/>
        <div class="formbox margintop">
          <h3><span class="topradius fbshadow"><s:text name="g_addMessage"/> </span></h3>
          <table style="width: 500px;height: 100px;margin-top: 20px;">
            <tr>
              <td><s:text name="g_sessionId"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.sessionId" name="messageMeta.sessionId" value="<s:property value="messageMeta.sessionId"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_bussinessId"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.businessId" name="messageMeta.businessId" value="<s:property value="messageMeta.businessId"/>"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_sendUsername"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.username" name="messageMeta.username" value="<s:property value="messageMeta.username"/>"/>
              </td>
            </tr>
            <%--<tr>
              <td><s:text name="g_sendMsgAddress"/>：</td>
              <td>
                <input style="width: 180px;" type="text" id="messageMeta.address" name="messageMeta.address" value="<s:property value="messageMeta.address"/>"/>
              </td>
            </tr>--%>
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
                <select id="messageMeta.messageType" name="messageMeta.messageType">
                  <s:iterator value="messageTypeMap" status="stuts">
                    <option value="<s:property value="key"/>"
                            <s:if test="messageMeta.messageType == key">selected</s:if>>
                      <s:property value="value" />
                    </option>
                  </s:iterator>
                </select>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_msgState"/>：</td>
              <td>
                <select id="messageMeta.state" name="messageMeta.state">
                  <s:iterator value="stateMap" status="stuts">
                    <option value="<s:property value="key"/>"
                            <s:if test="messageMeta.state == key">selected</s:if>>
                      <s:property value="value" />
                    </option>
                  </s:iterator>
                </select>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_messageFlag"/>：</td>
              <td>
                <select id="messageMeta.messageFlag" name="messageMeta.messageFlag">
                  <s:iterator value="messageFlagMap" status="stuts">
                    <option value="<s:property value="key"/>"
                            <s:if test="messageMeta.messageFlag == key">selected</s:if>>
                      <s:property value="value" />
                    </option>
                  </s:iterator>
                </select>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_createDate"/>：</td>
              <td>
                <s:date name="%{new java.util.Date(messageMeta.createAt)}" format="%{@com.wallet.common.Constant@DAY_TIME_FORMAT}"/>
              </td>
            </tr>
            <tr>
              <td><s:text name="g_updateDate"/>：</td>
              <td>
                <s:date name="%{new java.util.Date(messageMeta.updateAt)}" format="%{@com.wallet.common.Constant@DAY_TIME_FORMAT}"/>
                <%--<input style="width: 180px;" type="text" id="messageMeta.updateAt" value="<s:property value="messageMeta.updateAt"/>" readonly/>--%>
              </td>
            </tr>
          </table>
        </div>
        <p id="p.error" class="error" style="visibility: hidden"></p>
        <div class="buttonbox">
          <a href="#" onclick="addRole();" id="btnConfirm" title="<s:text name='g_add'/>" class="but"><span><s:text name="g_add"/></span></a>
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