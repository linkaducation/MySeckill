package org.kill.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.kill.dao.SecSuccessKill;
import org.kill.dao.SeckillDao;
import org.kill.dto.Exposer;
import org.kill.dto.SeckillExecution;
import org.kill.entity.Seckill;
import org.kill.entity.Successkill;
import org.kill.exception.RepeatException;
import org.kill.exception.SecKillException;
import org.kill.exception.SeckillCloseException;
import org.kill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;


public class SeckillServiceImpl implements SeckillService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SeckillDao SeckillDao;
    @Resource
    private SecSuccessKill SecSuccessKill;
    
    private final String slat = "bdsuai41d123nbwuidbsdsaewertg";
    
    @Override
    public Seckill getSeckillById(long id) {
        return SeckillDao.queryById(id);
    }

    @Override
    public List<Seckill> getSeckillList() {
        return SeckillDao.queryAll(0, 4);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = SeckillDao.queryById(seckillId);
        
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }
        
        Date startTime = seckill.getStart_time();
        Date endTime = seckill.getEnd_time();
        Date now = new Date();
        
        if (now.getTime() < startTime.getTime() ||now.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId,startTime.getTime(),now.getTime(),endTime.getTime());
        }
        
        String md5 = getMd5(seckillId);
        return new Exposer(true,md5,seckillId);
    }
    
    private String getMd5(Long seckillid){
        String base = seckillid+"/" +slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) {
        if (md5 == null || md5.equals(getMd5(seckillId))) {
            throw new SecKillException("seckill data rewrite");
        }
        
        Date now = new Date();
        
        
        try {
            int updatecount = SeckillDao.reduceNumber(seckillId, now);
            
            if (updatecount <= 0) {
                throw new SeckillCloseException("seckill is close");
            }else {
                //记录购买行为
                int insertCount = SecSuccessKill.insertSuccessKill(seckillId, userPhone);
                if (insertCount <= 0) {
                    throw new RepeatException("seckill repeated");
                }else {
                    Successkill successkill = SecSuccessKill.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, 1, "秒杀成功",successkill);
                }
                
            }
        }catch (SeckillCloseException el) {
            throw el;
        }catch (RepeatException e2) {
            throw e2;
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new SecKillException("seckill inner error" + e.getMessage());
        }
        
    }

}
