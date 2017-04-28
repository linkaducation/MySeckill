package org.kill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kill.entity.Seckill;

public interface SeckillDao {
    /**
     * 秒杀成功，减少库存
     * @param seckillId
     * @param createTime
     * @return
     */
    int reduceNumber(@Param("seckillId") Long seckillId,@Param("createTime") Date createTime);
    
    /**
     * 根据秒杀ID查询秒杀对象
     * @param seckillId
     * @return
     */
    Seckill queryById(Long seckillId);
    
    /**
     * 根据偏移量查询多有的秒杀对象
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset")int offset,@Param("limit")int limit);
}   
