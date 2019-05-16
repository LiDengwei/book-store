<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.wallet.common.util.PageUtil"%>
<%PageUtil.noCache(request, response);%>

<head>
	<meta http-equiv="Content-Language" content="utf-8" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="expires"   content="0"/>
</head>

<table width="100%">
	<tr>
		<td align="center">
			Character error,the input can not include &lt;script or script&gt;
		</td>
	</tr>
	<tr>
		<td align="center">
			<input type="button" onclick="javascript:window.history.go(-1);" value="Back"/>
		</td>
	</tr>
</table>