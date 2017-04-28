package org.kill.service;

import java.util.List;

import org.kill.dto.Exposer;
import org.kill.dto.SeckillExecution;
import org.kill.entity.Seckill;

public interface SeckillService {
    
    /**
     * 根据id查询商品
     */
    Seckill getSeckillById(long id);
    
    /**
     * 返回所有的商品查询
     */
    List<Seckill> getSeckillList();
    
    //查看是否可以进行秒杀，可以的话输出秒杀接口，否则输出开始时间结束时间
    Exposer exportSeckillUrl(long seckillId);
    
    //查看是否秒杀成功
    SeckillExecution executeSeckill(long seckillId,long userPhone,String md5); 
           
    
}   
