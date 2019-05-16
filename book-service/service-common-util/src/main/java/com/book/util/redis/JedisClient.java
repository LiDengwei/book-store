package com.book.util.redis;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClient {
    private static final Logger logger = Logger.getLogger(JedisClient.class);
    private Integer maxActive = 64;
    private Integer maxIdle = 32;
    private Integer maxWait = 30000;

    private final JedisPool jedisPool;

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public JedisClient(String address,String port){
        JedisPoolConfig config = new JedisPoolConfig();
        //config.setMaxActive(maxActive);
        config.setMaxTotal(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWait);
        //config.setMaxWait(maxWait);
        config.setTestOnBorrow(true);
        jedisPool = new JedisPool(config, address, Integer.parseInt(port));
    }

    //get a jedis client,but must return it.
    public Jedis get(){
        return jedisPool.getResource();
    }

    public void release(Jedis jedis){
        if(jedis != null && jedis.getDB() != 0){
            try {
                jedis.select(0);
            }catch(Exception ex){
            }
        }
        jedisPool.returnResource(jedis);
    }
}
