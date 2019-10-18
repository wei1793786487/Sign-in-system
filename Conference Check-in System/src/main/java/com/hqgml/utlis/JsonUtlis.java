package com.hqgml.utlis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonUtlis {

  //解析百度的返回的json获取物理地址
    public  static String getAddress(String Json) {
        JSONObject city = JSON.parseObject(Json);
        String address = city.getJSONObject("content").getString("address");
        return address;
    }
    //查看jsonl里面有没有错误信息

    /**
     *
     * @param json json的参数
     * @return 返回是否是josn具有错误
     */
    public static boolean  IsEerror(String json){

        JSONObject jsonObject = JSON.parseObject(json);
        JSONObject error = jsonObject.getJSONObject("Response").getJSONObject("Error");
        if (error==null){
            return false;
        }else {
            String message = error.getJSONObject("Message").toString();
            System.out.println("腾讯云调用的时候成功返回的信息是"+message);
            return true;
        }
    }
    /**
     *
     * @param json 腾讯返回的json对象
     * @return
     */
   public static  boolean IsUser(String json){
       boolean b = IsEerror(json);
       if (!b){
           JSONObject jsonObject = JSON.parseObject(json);
           String faceNum = jsonObject.getJSONObject("FaceNum").toString();
           Integer integer = Integer.valueOf(faceNum);
           if (integer>0){
               return true;
           }else {
               return false;
           }
       }
       return false;
   }
}

