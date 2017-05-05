package org.kill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Ellen on 2017/5/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class betterRedisTest {
    @Autowired
    private betterRedis betterRedis;
    @Test
    public void getValue() throws Exception {
        Seckill name = betterRedis.getValue(1001);
        System.out.println(name);
    }

    @Test
    public void setValue() throws Exception {
        Seckill seckill = new Seckill();
        seckill.setName("钟明");
        seckill.setNumber(100);
        String result = betterRedis.setValue(1001,seckill);
        System.out.println(result);
    }

}