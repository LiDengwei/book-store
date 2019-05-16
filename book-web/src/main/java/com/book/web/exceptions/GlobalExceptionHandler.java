package com.book.web.exceptions;

import com.alibaba.fastjson.JSON;
import com.book.common.ResultMsg;
import com.book.exceptions.APIException;
import com.book.web.webUtil.ResourceMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 */

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value=APIException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String jsonErrorHandler(HttpServletRequest req, APIException e) throws Exception{
		ResultMsg resultMsg = new ResultMsg();
		logger.error("request error: url:{}",req.getRequestURI(),e);
		String exMessage = e.getMessage();
		exMessage = getMessage(exMessage);//替换成国际化后的语言
		resultMsg.fail();
		resultMsg.setMsg(e.message);
		resultMsg.setResultCode(e.code);
		return JSON.toJSONString(resultMsg);
	}

	/**
	 * Handle other exception
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String handleException(HttpServletRequest req,Exception ex) {
		ResultMsg resultMsg = new ResultMsg();
		logger.error("request error: url:{}",req.getRequestURI(),ex);
		resultMsg.fail();
		resultMsg.setMsg(ResourceMessageUtil.getMessage("system_busy_sdfsu"));
		resultMsg.setResultCode(APIException.ErrorCode.INTERNAL_ERROR.name());
		ex.printStackTrace();
		return JSON.toJSONString(resultMsg);
	}
	public String getMessage(String key){
		return ResourceMessageUtil.getMessage(key);
	}

}
