package com.hqgml.domain;

import lombok.Data;



@Data
public class Meeting {
    private Integer id;
    private Integer add_id;//添加人id
    private String metting_address;
    private String meeting_name;
    private String meeting_phone;
    private String meeting_number;
    private String times;
    private String uuid;
}
