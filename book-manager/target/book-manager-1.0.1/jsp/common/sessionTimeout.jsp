<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.wallet.common.util.PageUtil"%>
<%@page import="com.wallet.common.util.BtCoinUtil"%>
<%PageUtil.noCache(request,response);%>

<html>
<head>
	<title>timeout</title>
	<script type="text/javascript">
	var totalTime = 5; 
	var isOpenedWindow = false;
	window.onload = function()
	{
		document.getElementById("totalTime").innerHTML = totalTime;
		setTimeout(goBackLoginPage,totalTime*1000);
		setTimeout(setTotalTime,1000)
	}
	
	function goBackLoginPage()
	{
	    //居于安全性将参数加密
		var requestSystem = '<%= BtCoinUtil.getMD5Str(request.getParameter("timeOutSystem"),"a") %>';
		if(window.parent.opener == null)
		{
			var topWin = getTopWin(window);
			topWin.location.href = "<%=PageUtil.URL_SERVICE(request)+ "/jsp/login.jsp"%>";
		}
		else
		{
			window.parent.close();
			var topWin = getTopWin(window);
			closeAllWin(window);
			if(	isOpenedWindow )
			{
				window.top.close();
			}
			topWin.location.href = "<%=PageUtil.URL_SERVICE(request)+ "/jsp/login.jsp"%>";
		}
	}
	
	function setTotalTime()
	{
		totalTime = totalTime - 1;
		if( totalTime<0 )
		{
			return;
		}
		document.getElementById("totalTime").innerHTML = totalTime;
		setTimeout(setTotalTime,1000);
	}
	
	function getTopWin(win)
	{
		if(win.top.opener != undefined && win.top.opener != null)
		{
			return getTopWin(win.top.opener);
		}
		else if(win.top.dialogArguments != undefined && win.top.dialogArguments != null)
		{
			return getTopWin(win.top.dialogArguments);
		}
		else
		{
			return win.top;
		}
	}
	
	function closeAllWin(win)
	{
		if(win.top.opener != undefined && win.top.opener != null)
		{
			isOpenedWindow = true;
			closeAllWin(win.top.opener);
			win.top.opener.close();
		}
		else if(win.top.dialogArguments != undefined && win.top.dialogArguments != null)
		{
			isOpenedWindow = true;
			closeAllWin(win.top.dialogArguments);
			win.top.dialogArguments.close();
		}
	}
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="bian_edit_left">
  <tr> 
    <td width="100%" height="80" align="center" valign="bottom">
    	&nbsp;&nbsp;你未登录系统或者超时，请 <a href="#" onclick="goBackLoginPage()">重新登录</font></a>
    	系统还有<font color="blue"><span id="totalTime">5</span></font>秒自动跳转到登录页
    </td>
 </tr>
</table>
</body>
</html>