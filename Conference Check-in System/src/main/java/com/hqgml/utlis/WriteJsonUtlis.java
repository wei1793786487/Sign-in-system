package com.hqgml.utlis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WriteJsonUtlis {
    /**
     * 快捷返回客户端的json方法
     *
     * @param obj
     * @param response
     * @throws IOException
     */
    public static void writeValue(Object obj, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
//        有了过滤器,此句也是有用
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), obj);
    }

    /**
     * 返回一个json对象的快捷方法
     *
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public static String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
