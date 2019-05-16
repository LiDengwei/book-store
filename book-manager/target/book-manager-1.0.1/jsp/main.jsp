<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta name="robots" content="all" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link  type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/global.css" media="screen"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/composite.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<title>PI管理系统</title>

</head>
<body>
<jsp:include page="./header.jsp" flush="true"/>
<!--content begin-->
<div id="content" class="boxshadow">
 
<jsp:include page="./leftMenu.jsp" flush="true"/>  

  <div id="rightbox"> 
  	<a href="#" title="" class="lefthide"></a>
   
    <div class="formbox" style="height:500px; text-align:center;">
       	欢迎进入PI管理系统
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