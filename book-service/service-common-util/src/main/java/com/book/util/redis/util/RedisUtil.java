package com.book.util.redis.util;
import com.alibaba.fastjson.JSON;
import com.book.util.redis.JedisClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

/**
 * Created by OWk on 2015/9/10.
 */
public class RedisUtil {


    public static void put(JedisClient jedisClient, String key, int second, String value){
        Jedis jedis = jedisClient.get();
        try {
            jedis.setex(key,second,value);
        }
        catch (Exception e){
        }
        finally {
            jedisClient.release(jedis);
        }
    }

    public static void add(JedisClient jedisClient, String key, String value){
        Jedis jedis = jedisClient.get();
        try {
            jedis.rpush(key, value);
        }
        catch (Exception e){
        }
        finally {
            jedisClient.release(jedis);
        }
    }

    public static void sadd(JedisClient jedisClient, String key, String value){
        Jedis jedis = jedisClient.get();
        try {
            jedis.sadd(key, value);
        }
        catch (Exception e){
        }
        finally {
            jedisClient.release(jedis);
        }
    }

    public static List<String> getArray(JedisClient jedisClient, String key){
        Jedis jedis = jedisClient.get();
        try {
            return jedis.lrange(key, 0, -1);
        }
        catch (Exception e){
        }
        finally {
            jedisClient.release(jedis);
        }
        return null;
    }




    public static void  removeArrayValue(JedisClient jedisClient, String key, Long startindex, Long endIndex){
        Jedis jedis = jedisClient.get();
        try {
            jedis.ltrim(key,startindex,endIndex);
        }
        catch (Exception e){
        }
        finally {
            jedisClient.release(jedis);
        }
    }

    /**
     * 根据key值移除于value相等的元素
     * @param jedisClient
     * @param key
     * @param value
     */
    public static void  removeArrayByValue(JedisClient jedisClient, String key, String value){
        Jedis jedis = jedisClient.get();
        try {
            jedis.lrem(key,0,value);
        }
        catch (Exception e){
        }
        finally {
            jedisClient.release(jedis);
        }
    }

    public static void  removeArrayFirstValue(JedisClient jedisClient, String key){
        Jedis jedis = jedisClient.get();
        try {
            jedis.ltrim(key,1,-1);
        }
        catch (Exception e){
        }
        finally {
            jedisClient.release(jedis);
        }
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

    public static String get(JedisPool jedisPool, String key){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.get(key);
        }
        catch (Exception e){
        }
        finally {
            jedisPool.returnResource(jedis);
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

    public static Long remove(JedisPool jedisPool, String key){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.del(key);
        }
        catch (Exception e){
        }
        finally {
            jedisPool.returnResource(jedis);
        }
        return null;
    }

    public static void incr (JedisClient jedisClient, String key){
        Jedis jedis = jedisClient.get();
        try {
            jedis.incr(key);
        }
        catch (Exception e){
        }
        finally {
            jedisClient.release(jedis);
        }
    }

    public static void decr(JedisClient jedisClient, String key){
        Jedis jedis = jedisClient.get();
        try {
            jedis.decr(key);
        }
        catch (Exception e){
        }
        finally {
            jedisClient.release(jedis);
        }
    }
    public static Long incrReturn (JedisClient jedisClient, String key){
        Jedis jedis = jedisClient.get();
        try {
            return jedis.incr(key);
        }
        catch (Exception e){
            return 0L;
        }
        finally {
            jedisClient.release(jedis);
        }
    }

    public static Long ttl(JedisClient jedisClient, String key){
        Jedis jedis = jedisClient.get();
        try {
            return jedis.ttl(key);
        }
        catch (Exception e){
        }
        finally {
            jedisClient.release(jedis);
        }
        return 0L;
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

    public static long putnx(JedisPool jedisPool,String key,String value){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.setnx(key,value);
        }
        catch (Exception e){
        }
        finally {
            jedisPool.returnResource(jedis);
        }
        return 0;
    }

    public static boolean acquire(JedisClient jedisClient,String key,Map<String,String> value){
        //将map值放入redis
        String redisValue = JSON.toJSONString(value);
        long rt = putnx(jedisClient,key,redisValue);
        if(rt == 1){
            return true;
        }
        //判断上一个value的时间戳  是否已经超时  如果超时则删除上一次的value  放入本次请求的值
        String currentValueStr = get(jedisClient,key);
        if (currentValueStr != null) {
            Map redisMap = JSON.parseObject(currentValueStr);
            if((System.currentTimeMillis() - Long.parseLong(redisMap.get("timestamp").toString())) / 1000 >= RedisKey.REDIS_TIME_OUT){
                remove(jedisClient,key);
                long result = putnx(jedisClient,key,redisValue);
                if(result == 1){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean acquire(JedisClient jedisClient,String key,Map<String,String> value,int redisTimeOut){
        //将map值放入redis
        String redisValue = JSON.toJSONString(value);

        long rt = putnx(jedisClient,key,redisValue);
        if(rt == 1){
            return true;
        }
        //判断上一个value的时间戳  是否已经超时(暂定超时时间120秒)  如果超时则删除上一次的value  放入本次请求的值
        String currentValueStr = get(jedisClient,key);
        if (currentValueStr != null) {
            Map redisMap = null;
            try {
                redisMap = JSON.parseObject(currentValueStr);
            } catch (Exception e) {
                remove(jedisClient,key);
                return true;
            }
            if((System.currentTimeMillis() - Long.parseLong(redisMap.get("timestamp").toString())) / 1000 >= redisTimeOut){
                remove(jedisClient,key);
                long result = putnx(jedisClient,key,redisValue);
                if(result == 1){
                    return true;
                }
            }
        }
        return false;
    }


    /**
     *
     * @param jedisPool
     * @param key
     * @param value
     * @param redisTimeOut
     * @return
     */
    public static boolean acquire(JedisPool jedisPool,String key,Map<String,String> value,int redisTimeOut){
        //将map值放入redis
        String redisValue = JSON.toJSONString(value);
        long rt = putnx(jedisPool,key,redisValue);
        if(rt == 1){
            return true;
        }
        //判断上一个value的时间戳  是否已经超时(暂定超时时间120秒)  如果超时则删除上一次的value  放入本次请求的值
        String currentValueStr = get(jedisPool,key);
        if (currentValueStr != null) {
            Map redisMap = JSON.parseObject(currentValueStr);
            if((System.currentTimeMillis() - Long.parseLong(redisMap.get("timestamp").toString())) / 1000 >= redisTimeOut){
                remove(jedisPool,key);
                long result = putnx(jedisPool,key,redisValue);
                if(result == 1){
                    return true;
                }
            }
        }
        return false;
    }

    public  static  Long expire(JedisClient jedisClient,String key,int redisTimeOut){
        Jedis jedis = jedisClient.get();
        try {
            return jedis.expire(key,redisTimeOut);
        }
        catch (Exception e){
        }
        finally {
            jedisClient.release(jedis);
        }
        return 0L;
    }
}
