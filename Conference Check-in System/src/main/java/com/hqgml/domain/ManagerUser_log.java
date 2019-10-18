package com.hqgml.domain;


import lombok.Data;

@Data
public class ManagerUser_log {
//    time参数设置为自增长，不需要在对象里面封账
    private Integer id;
    private Integer uid;
    private String ip;
    private String action;
    private String sql;
    private String time;
}
