<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.wallet.common.Constant"%>
<%@page import="com.wallet.common.util.PageUtil"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="/WEB-INF/freTaglib.tld" prefix="fre"%>

		<div id="leftbox" class="leftshadow leftradius">
			<div id="menu">
				<fre:authTag authCode="AuthManager">
						<h2><a href="#" title="权限管理"><span>权限管理</span></a></h2>
						 <ul>
						 <fre:authTag authCode="MenuManagerList">
						 	<li><%/*菜单管理*/%><a href="MenuAndAuthAction!queryMenuList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="MenuManagerList" title="菜单管理 "><span> 菜单管理</span></a></li>
						</fre:authTag>
						<fre:authTag authCode="RoleManagerList">
							<li><%/*角色管理*/%><a href="RoleAction!queryRoleList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="RoleManagerList" title="角色管理 "><span> 角色管理</span></a></li>
						</fre:authTag>
						 </ul>
			   </fre:authTag>
			   	<fre:authTag authCode="TradeManager">
			   		<h2><a href="#" title="交易管理"><span>交易管理</span></a></h2>
			   		<ul>
			   			<fre:authTag authCode="UserManagerList">
			   				<li><%/*用户管理*/%><a href="UserAction!preQueryUserList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="UserManagerList" title="用户管理 "><span>用户管理</span></a></li>
			   			</fre:authTag>
			   			<fre:authTag authCode="UserAccountLogManagerList">
			   				<li><%/*用户资金帐变管理*/%><a href="UserAction!preQueryUserAccountLogList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="UserAccountLogManagerList" title="用户资金帐变管理 "><span>用户资金帐变管理</span></a></li>
			   			</fre:authTag>
			   			<fre:authTag authCode="DepositManagerList">
			   				<li><%/*充值管理*/%><a href="DepositAction!preQueryDepositList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="DepositManagerList" title="充值管理 "><span>充值管理</span></a></li>
			   			</fre:authTag>
			   			<fre:authTag authCode="EntrustManagerList">
			   				<li><%/*委托管理*/%><a href="TradeAction!preQueryEntrustList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="EntrustManagerList" title="委托管理 "><span>委托管理</span></a></li>
			   			</fre:authTag>
			   			<fre:authTag authCode="TradeRecordManagerList">
			   				<li><%/*交易记录管理*/%><a href="TradeAction!preQueryTradeRecordList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="TradeRecordManagerList" title="交易记录管理 "><span>交易记录管理</span></a></li>
			   			</fre:authTag>
			   			<fre:authTag authCode="TransactionManagerList">
			   				<li><%/*转帐记录管理*/%><a href="TradeAction!preQueryTransactionList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="TransactionManagerList" title="转帐记录管理 "><span>转帐记录管理</span></a></li>
			   			</fre:authTag>
						<fre:authTag authCode="efficiencyList">
							<li><%/*道具卡记录*/%><a href="PropsAction!intoQueryPropsList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="efficiencyList" title="道具卡记录管理 "><span>道具卡记录</span></a></li>
						</fre:authTag>
						<fre:authTag authCode="propsRecordList">
							<li><%/*道具卡使用记录*/%><a href="PropsAction!intoQueryPropRecordList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="propsRecordList" title="道具卡使用记录"><span>道具卡使用记录</span></a></li>
						</fre:authTag>
                        <fre:authTag authCode="feedback">
                            <li><%/*反馈信息*/%><a href="feedback!index.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="feedback" title="反馈信息"><span>反馈信息</span></a></li>
                        </fre:authTag>
						<fre:authTag authCode="notice">
							<li><%/*推送通知*/%><a href="NoticeAction!preNoticeList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="notice" title="推送通知"><span>推送通知</span></a></li>
						</fre:authTag>
			   		</ul>
			   	</fre:authTag>

				<fre:authTag authCode="recordManager">
					<h2><a href="#" title="日志管理"><span>日志管理</span></a></h2>
					<ul>
						<fre:authTag authCode="userLogList">
							<li><%/*用户日志记录*/%><a href="UserAction!intoQueryUserLogList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="userLogList" title="用户日志记录"><span>用户日志记录</span></a></li>
						</fre:authTag>
						<fre:authTag authCode="smsList">
							<li><%/*短信发送记录*/%><a href="UserAction!intoQuerySmsRecordList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="smsList" title="短信发送记录"><span>短信发送记录</span></a></li>
						</fre:authTag>
						<fre:authTag authCode="device">
							<li><%/*客户端登录记录*/%><a href="UserAction!preQueryDeviceList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="device" title="客户端登录记录"><span>客户端登录记录</span></a></li>
						</fre:authTag>
					</ul>
				</fre:authTag>

				<fre:authTag authCode="SystemManager">
					<h2><a href="#" title="系统管理"><span>系统管理</span></a></h2>
						<ul>
							<fre:authTag authCode="SysUserInfoList">
								<li><%/*管理员管理*/%><a href="SystemUserInfoAction!priSystemUserInfoList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="SysUserInfoList" title="管理员管理 "><span>管理员管理</span></a></li>
							</fre:authTag>
							<fre:authTag authCode="UpdateManagerPassWord">
								<li><%/*修改密码*/%><a href="SystemUserInfoAction!updatePwdJump.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="UpdateManagerPassWord" title="修改密码 "><span>修改密码</span></a></li>
							</fre:authTag>							
							<fre:authTag authCode="ManagerLogList">
								<li><%/*管理员日志 查询 */%><a href="SystemLogAction!enterManagerLogView.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="ManagerLogList" title="管理员日志 查询 "><span>管理员日志查询</span></a></li>
							</fre:authTag>							
							<fre:authTag authCode="SysConfigManager">
								<li><%/*系统参数查询*/%><a href="SysConfigAction!findSysConfigList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="SysConfigManager" title="系统参数查询"><span>系统参数查询</span></a></li>
							</fre:authTag>
							<fre:authTag authCode="NoticeList">
								<li><%/*公告查询*/%><a href="NoticeAction!findNoticeList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="NoticeList" title="公告查询"><span>公告查询</span></a></li>
							</fre:authTag>
						</ul>
				</fre:authTag>
				<fre:authTag authCode="ReportManager">
					<h2><a href="#" title="报表管理"><span>报表管理</span></a></h2>
					<ul>
						<fre:authTag authCode="StatisticsManagerList">
							<li><%/*"登录注册统计"*/%><a href="ReportAction!queryStatisticsList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="StatisticsManagerList" title="统计管理"><span>登录注册统计</span></a></li>
							<li><%/*"登录注册统计"*/%><a href="ReportAction!intoQueryStatisticsChart.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="StatisticsChart" title="统计管理"><span>登录注册统计图</span></a></li>
						</fre:authTag>
					    <fre:authTag authCode="FinancialManage">
							<li><%/*"财务统计"*/%><a href="ReportAction!queryFinancialStatistic.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="queryFinancialStatistic" title="财务统计"><span>财务统计</span></a></li>
						</fre:authTag>

					</ul>
				</fre:authTag>
				<fre:authTag authCode="AttachManager">
					<h2><a href="#" title="附加管理"><span>附加管理</span></a></h2>
					<ul>
						<fre:authTag authCode="MessageList">
							<li><%/*"会话消息"*/%><a href="MessageAction!queryMessageList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="MessageList" title="会话消息"><span>会话消息</span></a></li>
						</fre:authTag>
						<fre:authTag authCode="RelationshipList">
							<li><%/*"好友关系列表"*/%><a href="RelationshipAction!queryRelationshipList.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="RelationshipList" title="好友关系列表"><span>好友关系列表</span></a></li>
						</fre:authTag>

					</ul>
				</fre:authTag>
				<fre:authTag authCode="PaikeManager">
					<h2><a href="#" title="派克管理"><span>派克管理</span></a></h2>
					<ul>
						<fre:authTag authCode="checkUserList">
							<li><%/*"检查用户信息"*/%><a href="PaikeUserAction!checkUserDispatch.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="checkUserList" title="检查用户信息"><span>检查用户信息</span></a></li>
						</fre:authTag>
						<fre:authTag authCode="updateAccount">
							<li><%/*"更新用户信息"*/%><a href="PaikeUserAction!updateAccountDispatch.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="updateAccount" title="更新用户信息"><span>更新用户信息</span></a></li>
						</fre:authTag>
						<fre:authTag authCode="registerAccount">
							<li><%/*"向派克注册用户"*/%><a href="PaikeUserAction!registerAccountDispatch.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="registerAccount" title="向派克注册用户"><span>向派克注册用户</span></a></li>
						</fre:authTag>
						<fre:authTag authCode="getDepositRecord">
							<li><%/*"查询订单"*/%><a href="PaikeUserAction!getDepositRecordDispatch.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="getDepositRecord" title="查询订单"><span>查询订单</span></a></li>
						</fre:authTag>
						<fre:authTag authCode="UserLogout">
							<li><%/*"注销用户"*/%><a href="PaikeUserAction!preLogoutUser.action?<%=PageUtil.getEncoderSessionIdParam(request)%>" id="UserLogout" title="注销用户"><span>注销用户</span></a></li>
						</fre:authTag>

					</ul>
				</fre:authTag>
				
			</div>
		</div>
		<script language="javascript">
				var higObj = document.getElementById('<%=request.getParameter("higKey")%>');
				if(higObj){
					try{
						higObj.setAttribute("class","hig");						
					}catch(e){}
					try{
						higObj.setAttribute("className","hig");
					}catch(e){}
				}
				try{
					$(document).ready(function(){
							$(".hig").parent().parent().show();			
						
					});
				}catch(e){}
		</script>
