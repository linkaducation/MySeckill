package org.kill.dao;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.kill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * Created by Ellen on 2017/5/3.
 */
public class betterRedis {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private RuntimeSchema schema = RuntimeSchema.createFrom(Seckill.class);
    private JedisPool pool;

    public betterRedis(String host, int port) {
        pool = new JedisPool(host, port);
    }

    public Seckill getValue(long seckillId) {
        try (Jedis jedis = pool.getResource()) {
            String key = "seckill" + seckillId;
            byte[] value = jedis.get(key.getBytes());
            if (value != null) {
                Seckill seckill = (Seckill) schema.newMessage();
                ProtobufIOUtil.mergeFrom(value, seckill, schema);
                return seckill;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public String setValue(long seckillId, Seckill seckill) {
        String key = "seckill" + seckillId;
        byte[] value = ProtobufIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        try (Jedis jedis = pool.getResource()) {
            String result = jedis.setex(key.getBytes(), 3600, value);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
