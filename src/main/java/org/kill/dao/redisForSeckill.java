package org.kill.dao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Ellen on 2017/5/3.
 */
public class redisForSeckill {
    private JedisPool pool = new JedisPool("127.0.0.1", 6379);

    /**
     * 完成get操作
     */
    public String getValue(String key) {
        Jedis jedis = pool.getResource();
        String result = jedis.get(key);
        return result;
    }

    /**
     * 添加操作
     */
    public String set(String key, String value) {
        Jedis jedis = pool.getResource();
        String result = jedis.setex(key, 3600, value);
        return result;
    }
}
