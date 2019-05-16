<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.wallet.common.util.PageUtil"%>
<%@page import="com.wallet.system.dto.TManagerInfo"%>
<%@ taglib uri="/WEB-INF/freTaglib.tld" prefix="fre"%>

<%
	HttpSession Session = request.getSession();
  TManagerInfo manager = (TManagerInfo)Session.getAttribute("Manager");
%>
<div id="header">
	<%--<img src="<%=request.getContextPath()%>/images/logo.png" width="80px" height="50px">--%>
	<div>
		欢迎您，<span style="color:red;"><%=manager.getLoginName() %></span>
		<p>
			<span>
				<a href="SysLoginAction!logout.action" class="fshadow exit" title="安全退出">安全退出</a>
				
			</span>
		</p>
	</div>
</div>

<div style="display:none;">
	<div id="confirm2">
		<h4><s:text name="res_confirm_info"/></h4>
		<p>&nbsp;</p>
		<ul>
			<li id="msgLi">Are you sure to delete the user ?</li>
		</ul>
		<div class="buttonbox">
			<a id="confirmHref" href="#" onclick="confirmDone();" title="<s:text name="confirm"/>" class="butblue radius example2"><s:text name="confirm"/></a>
		</div>
	</div>
</div>