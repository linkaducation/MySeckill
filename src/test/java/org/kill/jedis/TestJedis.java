package org.kill.jedis;

import org.junit.Test;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ellen on 2017/5/3.
 */
public class TestJedis {
    @Test
    public void jedisTest() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("grade", "97");
        String grade = jedis.get("grade");
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
    }

    @Test
    public void jedisPoolTest() {
        JedisPool pool = new JedisPool("127.0.0.1", 6379);
        Jedis jedis = pool.getResource();
        jedis.set("grade", "97");
        String grade = jedis.get("grade");
        String name = jedis.get("name");
        System.out.println(name);
        System.out.println(grade);
        jedis.close();
    }

    @Test
    public void jedisPoolCofxigTest() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(20);
        List<JedisShardInfo> shardedJedis = new ArrayList<>();

        shardedJedis.add(new JedisShardInfo("127.0.0.1", 6379));

        try (ShardedJedisPool pool = new ShardedJedisPool(poolConfig, shardedJedis); ShardedJedis jedis = pool.getResource()) {
            String name = jedis.get("name");
            System.out.println(name);
        }
    }
    @Test
    public void testNull(){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String value = jedis.set("kkk","kkk");
        System.out.println(value);
        jedis.close();
    }
}
