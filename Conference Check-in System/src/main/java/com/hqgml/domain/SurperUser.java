package com.hqgml.domain;

import lombok.Data;

@Data
public class SurperUser {
    private Integer id;
    private String name;
    private String pswd;
    private String address;
    private String lasttime;
}
