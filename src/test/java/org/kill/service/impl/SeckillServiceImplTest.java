package org.kill.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kill.dto.Exposer;
import org.kill.dto.SeckillExecution;
import org.kill.entity.Seckill;
import org.kill.exception.SecKillException;
import org.kill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {

    @Autowired
    private SeckillService SeckillService;
    @Test
    public void testGetSeckillById() {
        Seckill result = SeckillService.getSeckillById(1000);
        System.out.println(result.getName());
    }

    @Test
    public void testGetSeckillList() {
        List<Seckill> list = SeckillService.getSeckillList();
        for (Seckill seckill : list) {
            System.out.println(seckill.getName()+"\t"+seckill.getNumber());
        }
    }

    @Test
    public void testExportSeckillUrl() {
        Exposer exposer = SeckillService.exportSeckillUrl(1001);
        if (exposer.getExposed()) {
            try {
                SeckillExecution executeSeckill = SeckillService.executeSeckill(1001, 15521158749L, exposer.getMd5());
                System.out.println(executeSeckill.getSeckillId() + "\t" +executeSeckill.getState() + 
                        "\t" +executeSeckill.getStateInfo());
            } catch (SecKillException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("秒杀未开启");
        }
    }

    @Test
    public void testExecuteSeckill() {
       
    }

}
