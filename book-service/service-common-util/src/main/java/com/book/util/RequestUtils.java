package com.book.util;

import com.alibaba.fastjson.JSON;
import com.book.util.redis.JedisClient;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * Restlet Filter Utils
 *
 * Created by jfb
 * @since  2015
 */
public class RequestUtils {
    private static final Log log = LogFactory.getLog(RequestUtils.class);


    public static boolean isMobileAppRequest(HttpServletRequest request){
        String ua = request.getHeader("User-Agent");
        if(ua.contains("yi-qianbao")){
            return true;
        }
        return false;
    }

    public static boolean acquire(JedisClient jedisClient, String key, Map<String,String> value) {
        //将map值放入redis
        String redisValue = JSON.toJSONString(value);
        long rt = putnx(jedisClient, key, redisValue);
        if (rt == 1) {
            return true;
        }
        //判断上一个value的时间戳  是否已经超时  如果超时则删除上一次的value  放入本次请求的值
        String currentValueStr = get(jedisClient, key);
        if (currentValueStr != null) {
            Map redisMap = JSON.parseObject(currentValueStr);
            if ((System.currentTimeMillis() - Long.parseLong(redisMap.get("timestamp").toString())) / 1000 >= 20) {
                remove(jedisClient, key);
                long result = putnx(jedisClient, key, redisValue);
                if (result == 1) {
                    return true;
                }
            }
        }
        return false;
    }


    public static String get(JedisClient jedisClient, String key){
        Jedis jedis = jedisClient.get();
        try {
            return jedis.get(key);
        }
        catch (Exception e){
        }
        finally {
            jedisClient.release(jedis);
        }
        return null;
    }

    public static Long remove(JedisClient jedisClient, String key){
        Jedis jedis = jedisClient.get();
        try {
            return jedis.del(key);
        }
        catch (Exception e){
        }
        finally {
            jedisClient.release(jedis);
        }
        return null;
    }

    public static long putnx(JedisClient jedisClient,String key,String value){
        Jedis jedis = jedisClient.get();
        try {
            return jedis.setnx(key,value);
        }
        catch (Exception e){
        }
        finally {
            jedisClient.release(jedis);
        }
        return 0;
    }

    public static String getDeviceId(HttpServletRequest request){
        String deviceId = request.getHeader("deviceId");
        if(StringUtils.isEmpty(deviceId)){
            deviceId = "DefaultDevice";
        }
        return deviceId;
    }


    public static String getCookieValue(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookieName.equalsIgnoreCase(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static boolean isAuthExclude(HttpServletRequest request) {
        String excludes = XMLConfig.getInstance().getAuthExcludes();
        String[] ex = excludes.split(",");
        boolean filter = false;
        for (String s : ex) {
            if (request.getRequestURI().indexOf(s) != -1) {
                filter = true;
                break;
            }
        }
        return filter;
    }
    public static boolean isExcludeRateLimit(HttpServletRequest request) {
        XMLConfig config = XMLConfig.getInstance();
        String ext = config.getRateLimitExcludes();
        String uri = request.getRequestURI();

        boolean find = false;
        if (ext != null)
            for (String s : ext.split(",")) {
                if (uri.indexOf(s) != -1) {
                    find = true;
                    break;
                }
            }
        return find;
    }
    public static boolean isIncludeUnlock(HttpServletRequest request) {
        XMLConfig config = XMLConfig.getInstance();
        String ext = config.getUnlockLimits();
        String uri = request.getRequestURI();

        boolean find = false;
        if (ext != null)
            for (String s : ext.split(",")) {
                if (uri.indexOf(s) != -1) {
                    find = true;
                    break;
                }
            }
        return find;
    }


}
