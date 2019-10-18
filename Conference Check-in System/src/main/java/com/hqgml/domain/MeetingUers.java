package com.hqgml.domain;

import lombok.Data;

@Data
public class MeetingUers {
    private Integer id ;
    private String mid;//会议的id
    private String person_name;//真实姓名
    private String photo_name;
    private String IsCheck;
}
