package org.kill.dao;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kill.entity.Seckill;
import org.kill.entity.Successkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-dao.xml"})
public class SuccessKillDaoTest {
    @Autowired
    private SecSuccessKill secSuccessKill;
    
    @Test
    public void testSuccessKill(){
        int result = secSuccessKill.insertSuccessKill(1000, 58749);
        System.out.println(result);
    }
    
    @Test
    public void testQueryAll(){
        Successkill result = secSuccessKill.queryByIdWithSeckill(1000, 58749);
        System.out.println(result.getSeckill());
    }
    
}
