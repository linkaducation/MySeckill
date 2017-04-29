package org.kill.dto;

import org.kill.dao.SecSuccessKill;
import org.kill.entity.Successkill;

public class SeckillExecution {
    private long seckillId;

    //秒杀执行结果的状态
    private int state;

    //状态的明文标识
    private String stateInfo;

    //当秒杀成功时，需要传递秒杀成功的对象回去
    private Successkill successkill;
    
    

    public SeckillExecution(long seckillId, int state, String stateInfo) {
        super();
        this.seckillId = seckillId;
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public SeckillExecution(long seckillId, int state, String stateInfo, Successkill successkill) {
        super();
        this.seckillId = seckillId;
        this.state = state;
        this.stateInfo = stateInfo;
        this.successkill = successkill;
    }

    public SeckillExecution() {
        super();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public Successkill getSuccesskill() {
        return successkill;
    }

    public void setSuccesskill(Successkill successkill) {
        this.successkill = successkill;
    }

   

   
}
