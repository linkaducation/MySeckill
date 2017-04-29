package org.kill.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kill.dto.Exposer;
import org.kill.dto.SeckillExecution;
import org.kill.entity.Seckill;
import org.kill.exception.RepeatException;
import org.kill.exception.SecKillException;
import org.kill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {
    private Logger loger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService SeckillService;
    @Test
    public void testGetSeckillById() {
        Seckill result = SeckillService.getSeckillById(1000);
        loger.info("result={}",result);
    }

    @Test
    public void testGetSeckillList() {
        List<Seckill> list = SeckillService.getSeckillList();
        loger.info("list={}",list);
    }

    @Test
    public void testeckill() {
        Exposer exposer = SeckillService.exportSeckillUrl(1001);
        loger.info("exposer={}",exposer);
        if (exposer.getExposed()) {
            try {
                SeckillExecution executeSeckill = SeckillService.executeSeckill(1001, 15521158749L, exposer.getMd5());
                loger.info("executeSeckill={}",executeSeckill);
            } catch (RepeatException e) {
                loger.error(e.getMessage());
            } catch (SecKillException e) {
                loger.error(e.getMessage());
            }
        }else {
            loger.warn("exposer={}",exposer);
        }
    }

}
