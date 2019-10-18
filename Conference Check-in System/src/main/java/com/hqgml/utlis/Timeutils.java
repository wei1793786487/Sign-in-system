package com.hqgml.utlis;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timeutils {
    //获取格式化字符串
    public static  String Gettime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatdate = sdf.format(date);
        return formatdate;
    }

    @Test
    public void  demo(){
        String gettime = Gettime(new Date());
        System.out.println(gettime);
    }
}

