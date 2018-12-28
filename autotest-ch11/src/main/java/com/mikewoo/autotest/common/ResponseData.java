package com.mikewoo.autotest.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Eric Gui
 * @date 2018/12/25
 */
@Data
public class ResponseData implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static ResponseData build(Integer status, String msg, Object data) {
        return new ResponseData(status, msg, data);
    }

    public static ResponseData ok(Object data) {
        return new ResponseData(data);
    }

    public static ResponseData ok() {
        return new ResponseData(null);
    }

    public ResponseData() {

    }

    public static ResponseData build(Integer status, String msg) {
        return new ResponseData(status, msg, null);
    }

    public ResponseData(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResponseData(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
