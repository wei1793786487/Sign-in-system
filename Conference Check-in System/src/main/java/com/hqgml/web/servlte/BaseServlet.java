package com.hqgml.web.servlte;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {
    //post请求与get请求在本质上是没有区别的 只是post需要设置编码
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URi = req.getRequestURI();
        System.out.println(URi);
        String methonname = URi.substring(URi.lastIndexOf("/") + 1);
        System.out.println("访问的路径" + methonname);
        try {
            //获取方法的参数
            Method method = this.getClass().getMethod(methonname, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);//执行 执行的实际对象还有传递的参数
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 快捷返回客户端的json方法
     *
     * @param obj
     * @param response
     * @throws IOException
     */
    public void writeValue(Object obj, HttpServletResponse response) throws IOException {
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
    public String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
