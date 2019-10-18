package com.hqgml.utlis;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;


import java.io.*;

public class Base64Utils {
    public static String GetImageStr(String imgFile)
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组

        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("数组转换出现问题");
        }

        //对字节数组Base64编码
//        BASE64Encoder encoder = new BASE64Encoder();
//        return encoder.encode(data);//返回Base64编码过的字节数组字符串
        String s = Base64.encodeBase64String(data);
        return s;
    }


//    public static boolean GenerateImage(String base64str,String savepath)
//    {   //对字节数组字符串进行Base64解码并生成图片
////        if (base64str == null) //图像数据为空
////            return false;
////        // System.out.println("开始解码");
//////        BASE64Decoder decoder = new BASE64Decoder();
////        try
////        {
////            //Base64解码
////            byte[] b = decoder.decodeBuffer(base64str);
////            //  System.out.println("解码完成");
////            for(int i=0;i<b.length;++i)
////            {
////                if(b[i]<0)
////                {//调整异常数据
////                    b[i]+=256;
////                }
////            }
////            // System.out.println("开始生成图片");
////            //生成jpeg图片
////            OutputStream out = new FileOutputStream(savepath);
////            out.write(b);
////            out.flush();
////            out.close();
////            return true;
////        }
////        catch (Exception e)
////        {
////            return false;
////        }
//    }

 @Test
    public void deombase64(){
     String url = "F:/360MoveData/Users/Administrator/Desktop/psb.jpg";
     String s = GetImageStr(url);
     System.out.println(s);
 }
}
