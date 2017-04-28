package org.kill.dao;

import org.apache.ibatis.annotations.Param;
import org.kill.entity.Successkill;

public interface SecSuccessKill{
    /**
     * 根据秒杀对象id和对象name，成功秒杀，插入成功秒杀数据
     * @param seckill
     * @param userPhone
     * @return
     */
    int insertSuccessKill(@Param("seckill") long seckill,@Param("userPhone") long userPhone);
    
    /**
     * 根据秒杀对象id查询秒杀成功的订单，并返回秒杀成功的商品
     * @param secKillId
     * @return
     */
    Successkill queryByIdWithSeckill(@Param("secKillId") long secKillId,@Param("userPhone") long userPhone);
}
