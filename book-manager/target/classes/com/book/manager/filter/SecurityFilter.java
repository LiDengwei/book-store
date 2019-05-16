package com.book.manager.filter;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


public class SecurityFilter implements Filter {

	private static final Log log = LogFactory.getLog(SecurityFilter.class);
	
	private static final String CHARACTER_REDIRECT_URL = "/jsp/error/character_error.jsp";

	private static final String ILLEGAL_REDIRECT_URL = "/jsp/error/illegal_error.jsp";
	
	//不需验证地址
	private static final String EXCLUDE_FILTER_URI = "";

	public void init(FilterConfig arg0) throws ServletException {
	}	
	public void destroy() {
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String strURI = request.getRequestURI();
		strURI = strURI.substring(strURI.lastIndexOf("/") + 1);

		
//		if (!"".equals(strURI) && EXCLUDE_FILTER_URI.indexOf(strURI) == -1) {
//			// 校验是否跨站点伪造请求
//			final String ernSessionId = request.getParameter(Constant.BTCOIN_SESSID);
//			final String encoderSessionId = PageUtil.getEncoderSessionId(request);
//			if (!(encoderSessionId.equals(ernSessionId) || (encoderSessionId + "#").equals(ernSessionId))) {
//				log.error("illegal url,ernSessionId=" + ernSessionId + ",encoderSessionId=" + encoderSessionId + ",requestURL=" + request.getRequestURL());
//				request.setAttribute(Constant.ILLEGAL_URL, request.getRequestURL());
//				request.getRequestDispatcher(ILLEGAL_REDIRECT_URL).forward(request, response);
//				return;
//			}
//		}
		// 校验是否存储非法Script
		arg0.setCharacterEncoding("UTF-8");
		response.addHeader("P3P", "CP=CAO PSA OUR");
		Enumeration enum1 = request.getParameterNames();
		String attributeName = null;
		String name = null;
		boolean flag = false;
		while (enum1 != null && enum1.hasMoreElements()) {
			attributeName = (String) enum1.nextElement();
			name = request.getParameter(attributeName);
			if(name != null){
				String name2 = name;
				if (name.indexOf(" ") >= 0) {
					name2 = name.replace(" ", "");
				}
				if (name2 != null) {
					if (name2.toLowerCase().indexOf("<script") != -1 || name2.toLowerCase().indexOf("script>") != -1) {
						flag = true;
						log.error("parameter error,valuer=" + name + ",requestURL=" + request.getRequestURL());
						break;
					}
				}
			}
			attributeName = null;
			name = null;
		}
		if (flag) {
			request.getRequestDispatcher(CHARACTER_REDIRECT_URL).forward(request, response);
			return;
		}
		arg2.doFilter(arg0, arg1);
	}

}
