package org.kill.dto;
//封装返回结果 json
public class SeckillResult<T> {
    private Boolean success;
    private T data;
    private String error;
    
    public SeckillResult(Boolean success, String error) {
        super();
        this.success = success;
        this.error = error;
    }
    public SeckillResult(Boolean success, T data) {
        super();
        this.success = success;
        this.data = data;
    }
    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    
    
}
