package com.hqgml.utlis;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlUtils  {
    public static String encode(String s,String enc) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode(s, enc);
        return encode;
    }
    public static String decode(String s,String enc) throws UnsupportedEncodingException {
        String decode = java.net.URLDecoder.decode(s, enc);
        return decode;
    }
    @Test
    public void demo0() throws UnsupportedEncodingException {
//        System.out.println(encode("哈哈","utf-8"));
        System.out.println(decode("%E6%B5%8B%E8%AF%95%E5%9B%BE%E7%89%87","utf-8"));
    }
}
