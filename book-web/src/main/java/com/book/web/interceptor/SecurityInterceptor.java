package com.book.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.book.common.Constant;
import com.book.exceptions.APIException;
import com.book.util.*;
import com.book.util.redis.JedisClient;
import com.book.web.context.LoginContext;
import com.book.web.context.LoginContextHolder;
import com.book.web.webUtil.Config;
import com.book.web.webUtil.ResourceMessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Map;
import java.util.regex.Pattern;

//@Component
public class SecurityInterceptor implements HandlerInterceptor {

    private static Log logger = LogFactory.getLog(SecurityInterceptor.class);
    private static org.slf4j.Logger filterLogger = LoggerFactory.getLogger("filterLogger");
    private static final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    //防止sql注入正则表达式
    private static String sqlReg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
            + "(\\b(select|update|union|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";


    private static String sqlRegNew = "(\\b(select|update|union|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
    private static Pattern sqlPattern = Pattern.compile(sqlRegNew, Pattern.CASE_INSENSITIVE);

    @Autowired
    private JedisClient jedisClient;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //htttps重定向
//        if(!"https".equals(httpServletRequest.getHeader("x-forwarded-proto")) && StringUtils.isNoneBlank(HttpsHost)
//                && HttpsHost.equals(httpServletRequest.getHeader("host"))){
//            logger.info("Redirect to HTTPS...");
//            httpServletResponse.sendRedirect(httpServletRequest.getRequestURL().toString().replace("http", "https"));
//            return false;
//        }

        if ("1".equals(Config.getInstance().getProperty("crossdomain.open"))) {
            String origin = httpServletRequest.getHeader("Origin");
            origin = origin == null ? "*" : origin;
            httpServletResponse.setHeader("Access-Control-Allow-Origin", origin);
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,User-Agent,Accept,Accept-Language,Accept-Encoding,Origin,Cookie,Connection,book-language,Pragma,Cache-Control,Content-Length");
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
        }

        startTime.set(System.currentTimeMillis());
        String requestUri = httpServletRequest.getRequestURI();
        if (requestUri.endsWith("js") || requestUri.endsWith("css") || requestUri.endsWith("html") || requestUri.endsWith("map") ||
                requestUri.contains("verifySSOToken") || requestUri.contains("ex_rates")) {
            return true;
        }

        LoginContext context = new LoginContext();
        String ip = RealIpUtils.getClientIP(httpServletRequest);
        context.setIp(ip);
        LoginContextHolder.set(context);

        Map<String, String[]> paramMap = httpServletRequest.getParameterMap();
        String paramStr = null;
        if (paramMap != null) {
            paramStr = JSON.toJSONString(paramMap);
            //防止sql注入
            if(!isSqlValid(paramStr)){
                logger.error("请求参数sql校验错误，参数信息："+paramStr);
                throw new APIException(APIException.ErrorCode.PARAM_ERROR, ResourceMessageUtil.getMessage("canshuyouwu_mwe"));
            }

            paramStr = paramStr.replaceAll("token\"\\:\\[\"[^\"]*\"", "token\":[\"****\"");
            paramStr = paramStr.replaceAll("password\"\\:\\[\"[^\"]*\"", "password\":[\"****\"");
            paramStr = paramStr.replaceAll("tradePwd\"\\:\\[\"[^\"]]*\"", "tradePwd\":[\"****\"");
            paramStr = paramStr.replaceAll("password\"\\:\\[\"[^\"]]*\"", "password\":[\"****\"");
            paramStr = paramStr.replaceAll("pay_pwd\"\\:\\[\"[^\"]]*\"", "pay_pwd\":[\"****\"");
            paramStr = paramStr.replaceAll("newPassword\"\\:\\[\"[^\"]]*\"", "newPassword\":[\"****\"");
        }
        String radomNumber = RandomUtil.randomeNumStr(6);
        String language = httpServletRequest.getHeader("book-language");
        String method = httpServletRequest.getMethod();
        filterLogger.info(radomNumber + ",Http请求Url:" + httpServletRequest.getRequestURL() + " Method:" + httpServletRequest.getMethod()
                + " ,requestUri:" + requestUri + " ,language:" + language + "\nParam:" + paramStr);
        if (StringUtil.isEmpty(language)) {
            language = Constant.CN;//默认中文
        } else {
            if (language.indexOf("zh") >= 0 || language.indexOf("cn") >= 0) {
                language = Constant.CN;
            } else {
                language = Constant.EN;
            }
        }
        context.setLanguage(language);//设置语言

        Cookie[] cookies = httpServletRequest.getCookies();
        boolean isUnlock = true;
        boolean exclude = RequestUtils.isAuthExclude(httpServletRequest);
        filterLogger.info(radomNumber + "-" + requestUri + ">>,URI过滤=====" + exclude);
//        if (exclude) {
//            if ((requestUri.contains("sendEmailCode"))) {
//                if (!"{}".equals(paramStr)) {
//
//                    return true;
//                }
//            } else {
//                return true;
//            }
//        }
//        if ((requestUri.contains("sendSmsCode"))) {
//            if (paramStr.indexOf("register") >= 0) {
//                return true;
//            }
//        }
//        if ((requestUri.contains("sendEmailCode"))) {
//            if (paramStr.indexOf("\"type\":[\"2\"]") < 0) {
//                return true;
//            }
//        }
        boolean isMobileLogin = true;
        //User user = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                filterLogger.info(radomNumber + "-" + requestUri + ">>,开始循环cookies,name=" + c.getName() + ",value=" + c.getValue());
                if (c.getName().equals("book")) {
                    //uid|random|salt_enc
                    String cookieString = c.getValue();
                    filterLogger.info(radomNumber + "-" + requestUri + ">>,cookies=====" + c.getName() + "========" + cookieString);
                    if (StringUtils.isEmpty(cookieString)) {
//                        if (requestUri.indexOf("sendEmailCode") >= 0) {
//                            return true;
//                        }
                    }
                    String uidStr = isCookieTimeOut(httpServletRequest, httpServletResponse, cookieString);
                    String clientType = httpServletRequest.getHeader("clientType");
                    filterLogger.info(radomNumber + "-" + requestUri + ">>,获取到cookies=====" + JSON.toJSONString(uidStr));
                    if (uidStr == null) {
                        throw new APIException(APIException.ErrorCode.NOT_LOGGEDIN, "Not Logged in.");
                    }
                    String[] param = uidStr.split("_");
                    if (param.length < 2) {
                        throw new APIException(APIException.ErrorCode.NOT_LOGGEDIN, ResourceMessageUtil.getMessage("PLEASE_RE_LOGIN"));
                    }
//                    String accessToken = param[1];
//                    String userId = param[0];
//                    Long uid = NumberUtils.createLong(userId);
//                    String repeatCheckKey = "";
//                    //1.检查是否重复请求
//
//                    try {
//                        user = userServiceApi.selectByPrimaryKey(uid);
//                        if (user != null) {
//                            logger.info("ssssssssss clientType=" + clientType + "address=" + user.getAddress() + "username=" + user.getUserName());
//                            if (StringUtil.isNotEmpty(clientType)) {
//                                if ("2".equals(clientType)) {
//                                    String token = RedisUtil.get(jedisClient, InitConst.APP_TOKEN_PREFIX + "_" + user.getId());
////                                    logger.info("2 old token=" + token + "address=" + user.getAddress() + "username=" + user.getUserName());
////                                    logger.info("2 new accessToken=" + accessToken + "address=" + user.getAddress() + "username=" + user.getUserName());
//                                    if (!accessToken.equals(token)) {
//                                        filterLogger.info(radomNumber + "-" + requestUri + ">>,uid = " + uid + "<-><-> 请求用户:" + user.getUserName() + ",cookies中的token与redis不匹配，不允许操作");
////                                        RedisUtil.put(jedisClient, InitConst.APP_TOKEN_PREFIX + "_" + user.getId(), InitConst.APP_COOKIE_TIMEOUT, token);
//                                        throw new APIException(APIException.ErrorCode.NOT_LOGGEDIN, ResourceMessageUtil.getMessage("niyizaibiechudenglu_baa") + "," + ResourceMessageUtil.getMessage("PLEASE_RE_LOGIN"));
//                                    }
//                                    //2.检查请求token是否有效
//                                    if (StringUtil.isEmpty(accessToken) || !accessToken.equals(user.getLoginToken())) {
//                                        filterLogger.info(radomNumber + "-" + requestUri + ">>,uid = " + uid + "<-><-> 查询到了用户:" + user.getUserName() + ",token不匹配 accessToken = " + accessToken + ",数据库中为:" + user.getLoginToken());
//                                        throw new APIException(APIException.ErrorCode.NOT_LOGGEDIN, ResourceMessageUtil.getMessage("niyizaibiechudenglu_baa") + "," + ResourceMessageUtil.getMessage("PLEASE_RE_LOGIN"));
//                                    }
//                                } else {
//                                    Device deviceParam = new Device();
//                                    deviceParam.setAddress(user.getAddress());
//                                    deviceParam.setDeviceToken(user.getLoginToken());
//                                    Device device = deviceServiceApi.selectByTokenAndType(deviceParam);
////                                    logger.info("3 old user.getLoginToken()=" + user.getLoginToken() + "address=" + user.getAddress() + "username=" + user.getUserName());
////                                    logger.info("3 new accessToken=" + accessToken + "address=" + user.getAddress() + "username=" + user.getUserName());
//                                    if (StringUtil.isNotEmpty(device) && Short.parseShort("2") == device.getClientType()) {
//                                        filterLogger.info(radomNumber + "-" + requestUri + ">>,uid = " + uid + "<-><-> 请求用户:" + user.getUserName() + ",当前设备已更换为安卓，不允许操作");
////                                        RedisUtil.put(jedisClient, InitConst.APP_TOKEN_PREFIX + "_" + user.getId(), InitConst.APP_COOKIE_TIMEOUT, accessToken);
//                                        throw new APIException(APIException.ErrorCode.NOT_LOGGEDIN, ResourceMessageUtil.getMessage("niyizaibiechudenglu_baa") + "," + ResourceMessageUtil.getMessage("PLEASE_RE_LOGIN"));
//                                    }
//                                }
//                            }
//                            //3.检查账户是否被冻结
//                            if (User.StatusEnum.LOCKED.getValue() == user.getStatus()) {
//                                filterLogger.info(radomNumber + "-" + requestUri + ">>,uid = " + uid + "<-><-> 请求用户:" + user.getUserName() + ",状态为冻结，不允许操作" + requestUri);
//                                throw new APIException(APIException.ErrorCode.NOT_LOGGEDIN, ResourceMessageUtil.getMessage("user_unlock"));
//                            }
//                            context.setUser(user);
//                            LoginContextHolder.set(context);
//                            //check if unlock
//                            if (RequestUtils.isIncludeUnlock(httpServletRequest)) {
//                                if (!isUnlock(ip, uid)) {
//                                    isUnlock = false;
//                                }
//                            }
//                        }
//                    } catch (APIException ex) {
//                        StackTraceElement ste = ex.getStackTrace()[0];
//                        filterLogger.error(radomNumber + "-" + requestUri + ">>,异常信息：" + ex.getMessage() + "异常类：" + ste.getClassName() + "，异常方法：" + ste.getMethodName() + "异常行数：" + ste.getLineNumber());
//                        throw ex;
//                    }
                }
            }
        } else {
            filterLogger.info(radomNumber + "-" + requestUri + ">>,cookies为空");
        }

        if (!isUnlock) {
            throw new APIException(APIException.ErrorCode.ACCOUNT_LOCK, "account is locking");
        }
//        if (user == null && !exclude) {
//            filterLogger.info(radomNumber + "-" + requestUri + ">>,最终登录结果，Not Logged in.");
//            throw new APIException(APIException.ErrorCode.NOT_LOGGEDIN, "Not Logged in." + radomNumber);
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        //To change body of implemented methods use File | Settings | File Templates.
        String origin = httpServletRequest.getHeader("Origin");
        origin = origin == null ? "*" : origin;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", origin);
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        httpServletResponse.setHeader("Access-Control-Max-Age", "60"); //有效期
//        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,User-Agent,Accept,Accept-Language,Accept-Encoding,Origin,Cookie,Connection,book-language,Pragma,Cache-Control,Content-Length");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
    }

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        User user = LoginContextHolder.getContext().getUser();
//        //清除防止重复提交的redis
//        if (!Objects.isNull(user)) {
//            String requestUri = httpServletRequest.getRequestURI();
//            String repeatCheckKey = requestUri + ":" + user.getUserName();
//            String currentValueStr = RedisUtil.get(jedisClient, repeatCheckKey);
//            if (currentValueStr != null) {
//                //清除本次的redis缓存
//                RedisUtil.remove(jedisClient, repeatCheckKey);
//            }
//        }


//        LoginContextHolder.getContext().addLog("response end:", RealIpUtils.getClientIP(httpServletRequest)
//                + "         -        " + getInetAddress()
//                + "         -        " + httpServletRequest.getMethod()
//                + "         -        " + httpServletRequest.getRequestURI()
//                + "         -        " + (System.currentTimeMillis() - startTime.get()) + "ms"
//                + "         -        " + httpServletRequest.getQueryString()
//                + "         -        " + httpServletRequest.getHeader("UserRender-Agent"));
        httpServletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
        //增加no-cache的header
        if (httpServletRequest.getMethod().equalsIgnoreCase("GET")) {
            httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
            httpServletResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
            httpServletResponse.setDateHeader("Expires", 0); // Proxies.
        }

        LoginContextHolder.writeLog();
        LoginContextHolder.remove();
    }

    /**
     * @return
     */
    public static String getInetAddress() {
        String sb = "";
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = en.nextElement();
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()
                            && inetAddress.isSiteLocalAddress()) {
                        sb = (inetAddress.getHostAddress().toString());
                    }
                }
            }
        } catch (SocketException e) {
            logger.error(e.getMessage(), e);
        }
        return sb;
    }

    private boolean isUnlock(String ip, long userId) {
        String key = "seed_unlock" + ip + userId;
        boolean ret = false;
        Jedis jedis = jedisClient.get();
        try {
            ret = jedis.exists(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return ret;

    }

    private boolean checkAppSession(HttpServletRequest request, HttpServletResponse response, Long userId) {
//        if(RequestUtils.isMobileAppRequest(request)){
//            return true;
//        }

        String ip = RealIpUtils.getClientIP(request);
        String key = InitConst.APP_COOKIE_PREFIX + ip + "_" + userId;
        Jedis jedis = jedisClient.get();
        try {
            String value = jedis.get(key);
            LoginContextHolder.addLog("SecurityFilter:checkAppSessionDebug:userId=" + userId + ",key=" + key + ",value=" + value + ",uri=" + request.getRequestURI() + ",ip=" + ip);
            if (value == null) {
                removeCookie(request, response);
                return false;
            } else {
                jedis.setex(key, InitConst.APP_COOKIE_TIMEOUT, userId + "");
            }
        } catch (Exception e) {

        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return true;
    }

    private void removeCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase("user_info_secret")) {
                String cookieString = cookie.getValue();
                if (StringUtils.isNotEmpty(cookieString)) {
                    Jedis jedis = jedisClient.get();
                    try {
                        jedis.del(cookieString);
                    } catch (Exception e) {

                    } finally {
                        if (null != jedis) {
                            jedis.close();
                        }
                    }
                }
            }
        }

        String[] hosts = Config.getInstance().getProperty("cookie.host").split(",");
        for (String host : hosts) {
            Cookie cookie = new Cookie("user_info_secret", null); // Not necessary, but saves bandwidth.
            cookie.setPath("/");
            cookie.setMaxAge(0); // Don't set to -1 or it will become a session cookie!
            cookie.setDomain("." + host);
            response.addCookie(cookie);
            cookie = new Cookie("user_info_secret", null); // Not necessary, but saves bandwidth.
            cookie.setPath("/");
            cookie.setMaxAge(0); // Don't set to -1 or it will become a session cookie!
            response.addCookie(cookie);
        }
    }

    private String isCookieTimeOut(HttpServletRequest request, HttpServletResponse response, String cookieString) {
        Jedis jedis = jedisClient.get();
        try {
            String value = jedis.get(cookieString);
            if (value == null) {
                removeCookie(request, response);
            } else {
                jedis.setex(cookieString, InitConst.APP_COOKIE_TIMEOUT, value);
            }
            return value;
        } catch (Exception e) {

        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return null;
    }

    private boolean isSqlValid(String str)
    {
        if (sqlPattern.matcher(str).find())
        {
            logger.error("未能通过过滤器：str=" + str);
            return false;
        }
        return true;
    }

}
