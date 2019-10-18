package com.hqgml.domain;

import lombok.Data;

@Data
public class ManagerUser {
    private Integer id;
    private String name;
    private String sex;
    private Integer idcard;
    private Integer phone;
    private String pswd;
    private String address;
    private String lasttime;
}
