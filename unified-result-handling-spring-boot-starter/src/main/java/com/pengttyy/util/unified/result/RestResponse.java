package com.pengttyy.util.unified.result;

import java.io.Serializable;

/**
 * Rest 统一返回结果
 * @author kai.peng
 */
public class RestResponse implements Serializable {
    private boolean success;
    private String msg;
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    private void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    private void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    private void setData(Object data) {
        this.data = data;
    }

    public static RestResponse success(Object data,String msg){
        return getInstance(data, msg,true);
    }

    public static RestResponse success(Object data){
        return RestResponse.success(data,"ok");
    }

    public static RestResponse success(){
        return RestResponse.success(null,"ok");
    }

    public static RestResponse failure(String msg){
        return getInstance(null,msg,false);
    }

    private static RestResponse getInstance(Object data, String msg,boolean success) {
        RestResponse restResponse = new RestResponse();
        restResponse.setSuccess(success);
        restResponse.setData(data);
        restResponse.setMsg(msg);
        return restResponse;
    }
}
