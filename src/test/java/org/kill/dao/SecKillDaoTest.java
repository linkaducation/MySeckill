package org.kill.dao;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kill.dto.Exposer4fun;
import org.kill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-dao.xml"})
public class SecKillDaoTest {
    @Resource
    private SeckillDao seckilldao ;
    
    @Test
    public void testqueryById(){
        long id = 1000;
        Seckill seckill = seckilldao.queryById(id);
        System.out.println(seckill);
        System.out.println(seckill.getName());
    }
    
    @Test
    public void testQueryAll(){
        int offset = 0;
        int limit = 3;
        List<Seckill> list = seckilldao.queryAll(offset, limit);
        for (Seckill seckill : list) {
            System.out.println(seckill.getName());
        }
    }
    
    @Test
    public void testReduceNumber(){
        int i = seckilldao.reduceNumber(1001L, new Date());
        System.out.println(i);
    }
    
    @Test
    public void testExpser(){
        Exposer4fun exposer = new Exposer4fun.Builder().exposed(false).md5("123456").start(123456).end(789).now(2017).seckillId(1000).builder();
        System.out.println(exposer);
        
    }
}
