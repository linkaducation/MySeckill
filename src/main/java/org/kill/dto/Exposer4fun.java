package org.kill.dto;


public class Exposer4fun {
    //是否开启秒杀
    private final Boolean exposed;
    //加密
    private final String md5;
    private final long seckillId;
    //开启 时间  毫秒
    private final long start;
    //当前时间
    private final long now;
    //结束时间
    private final long end;
    
    public static class Builder{
        private Boolean exposed = false;
        private String md5 = null;
        private long seckillId = 0;
        private long start = 0;
        private long now = 0;
        private long end = 0;
        
        public Builder(){
        }
        public Builder exposed(Boolean exposed){
            this.exposed = exposed;
            return this;
        }
        public Builder md5(String md5){
            this.md5 = md5;
            return this;
        }
        public Builder seckillId(long seckillId){
            this.seckillId = seckillId;
            return this;
        }
        public Builder start(long start){
            this.start = start;
            return this;
        }
        public Builder now(long now){
            this.now = now;
            return this;
        }
        public Builder end(long end){
            this.end = end;
            return this;
        }
        
        public Exposer4fun builder(){
            return new Exposer4fun(this);
        }
    }
    
    public Exposer4fun(Builder builder) {
        // TODO 自动生成的构造函数存根
        exposed = builder.exposed;
        md5 = builder.md5;
        seckillId = builder.seckillId;
        start = builder.start;
        end = builder.end;
        now = builder.now;
    }
    public Boolean getExposed() {
        return exposed;
    }
    public String getMd5() {
        return md5;
    }
    public long getSeckillId() {
        return seckillId;
    }
    public long getStart() {
        return start;
    }
    public long getNow() {
        return now;
    }
    public long getEnd() {
        return end;
    }
    @Override
    public String toString() {
        return "Exposer [exposed=" + exposed + ", md5=" + md5 + ", seckillId=" + seckillId + ", start=" + start
                + ", now=" + now + ", end=" + end + "]";
    }
    
    
    
}   
