package com.hqgml.utlis;



import java.lang.management.BufferPoolMXBean;

public class StringUtils {
    /*
    UUID 生成随机的字符串
    生成唯一的文件名：
    * */
    public static String getUUIDFileName(String filename) {
        //由于UUID随机生成字符串，可能有我们不需要的字符例如"-"等
        //将文件名的前面部分进行截取：xx.jpg  ----> .jpg
        int idx = filename.lastIndexOf(".");//获取" . "的位置
        String extention = filename.substring(idx);//得到文件的扩展名
        String uuidfilename = UuidUtil.getUuid() + extention;
        return uuidfilename;
    }

    public static String GetPrefix(String filename) {
        int idx = filename.lastIndexOf(".");//获取" . "的位置
        String extention = filename.substring(0, idx);//获取文件的前缀名
        System.out.println(extention);
        return extention;
    }

    public static String getSuffix(String filename){
        int idx = filename.lastIndexOf(".");//获取" . "的位置
        String extention = filename.substring(idx+1);//得到文件的扩展名
        return extention;
    }
    public static String ReplaceString(String number,String replace,String chancereplace){
        String newnumber=number.replace(replace,chancereplace);
        return newnumber;
    }

}
