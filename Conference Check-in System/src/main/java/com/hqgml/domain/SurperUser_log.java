package com.hqgml.domain;


import lombok.Data;

@Data
public class SurperUser_log {

    private Integer id;
    private Integer uid;
    private String ip;
    private String action;
    private String sql;
    private  String time;
}
