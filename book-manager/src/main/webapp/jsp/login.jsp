<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.wallet.common.util.PageUtil"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%PageUtil.noCache(request,response);%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0"/>
<meta name="robots" content="all" />
<meta name="Author" content="Ivy" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link  type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/global.css" media="screen"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/inputtext.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<title>管理后台</title>
	<script type="text/javascript">
	    /**
	    * 获取验证码图片
	    */
		function reloadImg()
		{
			document.getElementById("vcodeImg").src="<%=request.getContextPath()%>/SysValidateCodeServlet.servlet?pp=" + Math.random();
		}
		/**
		* 校验和提交登录
		*/
		function doSubmit()
		{
			clearValidateMsg();
			if(isEmpty("tmanager.loginName"))
			{
				showError("tmanager.loginName","请输入你的登录名称","visible");
				return false;
			}
			if(isEmpty("tmanager.loginPwd"))
			{
				showError("tmanager.loginPwd","请输入你的登录密码","visible");
				return false;
			}
			if(isEmpty("tmanager.validateCode"))
			{
				showError("tmanager.validateCode","请输入正确的验证码","visible");
				return false;
			}
			document.forms[0].submit();
		}
	
		/**
		* 清空校验显示的信息
		*/
		function clearValidateMsg(){
			if(document.getElementById("errorMsg") != null)
			{
				document.getElementById("errorMsg").innerHTML="";
			}
			showError("tmanager.loginName","","hidden");
			showError("tmanager.loginPwd","","hidden");
			showError("tmanager.validateCode","","hidden");
		}
		
		/**
		* 清空
		*/
		function clearInput()
		{
			document.getElementById("tmanager.loginName").value = '';
			document.getElementById("tmanager.loginPwd").value = '';
			document.getElementById("tmanager.validateCode").value = '';
		}
		/**
		* 响应回车
		*/
		function keySub(e){
		   var msie = (document.all) ? true : false;
		   var keycode;
		   if(!msie)keycode=e.which;
		   	else keycode=event.keyCode;
		   if(keycode == 13){
		    	document.getElementById('btnLogin').click();
		   }
		} 	
		function resetPassWord(){
			window.location.href="SystemUserInfoAction!preForgetPassword.action";
		}
	</script>
</head>
<body>
<form method="post" action="SysLoginAction!login.action" id="form1">
	<%=PageUtil.getEncoderSessionIdHid(request)%>
	<%
		java.util.Random r=new java.util.Random();
		int pp = r.nextInt();
	%>
	<div id="login" class="shadow radius">
		<div style="width: 332px; height: 281px; float:left;">
		<img src="<%=request.getContextPath()%>/images/loginbg.png" /></div>
		<dl>
		     <%if(request.getAttribute("message")!=null){ %>
				<dt id="errorMsg">
              		<font color="red">
              		<%=request.getAttribute("message").toString()%>
              		</font>
				</dt>
	         <%}%>	
			<dt>用户名:</dt>
			<dd><input type="text" style="width:250px;" name="tmanager.loginName" id="tmanager.loginName" class="textlo" /><span id="span.tmanager.loginName" class="error" style="visibility: hidden"></span></dd>
			<dt>密码:</dt>
			<dd><input type="password" style="width:250px;" name="tmanager.loginPwd" id="tmanager.loginPwd" class="textlo"/><span id="span.tmanager.loginPwd" class="error" style="visibility: hidden"></span></dd>
			<dt>验证码:</dt>
			<dd><input type="text" onkeydown="keySub(event)" id="tmanager.validateCode" name="tmanager.validateCode" class="textshort" maxlength="4" style="width:100px;"/> 
			   <label style="float: inherit">                       
			  <img src="<%=request.getContextPath()%>/SysValidateCodeServlet.servlet" 
                    alt="Validation code" name="vcodeImg" id="vcodeImg" align="middle"
					style="cursor:pointer;margin-bottom:4px;" title="Click to change another one"
					onclick="javascript:reloadImg();"/></label>
				<span id="span.tmanager.validateCode" class="error" style="visibility: hidden"></span>
			</dd>
			<dd>
				<div class="buttonbox">
					<a id ="btnLogin" href="#" onclick="doSubmit();" title="登录" class="butblue radius">登录</a>
					<a href="#" onclick="clearInput();" title="Reset">重置</a>&nbsp;&nbsp;&nbsp;
				</div>
			</dd>
		</dl>
	</div>
	<!--content end-->
	<div id="footer">
		<p>&nbsp;</p>
	</div>
</form>
</body>
</html>