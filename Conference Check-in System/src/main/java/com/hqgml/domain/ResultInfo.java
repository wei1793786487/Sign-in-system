package com.hqgml.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 用于封装后端返回前端数据对象
 */
@Data
public class ResultInfo implements Serializable {
    private boolean flag;//后端返回结果正常为true，发生异常返回false
    private Object data;//后端返回结果数据对象
    private String errorMsg;//发生异常的错误消息
    private Integer user;
}
