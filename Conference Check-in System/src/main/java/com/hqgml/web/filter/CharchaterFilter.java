package com.hqgml.web.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CharchaterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        //request.setCharacterEncoding（）是设置从request中取得的值或从数据库中取出的值

        String method = req.getMethod();
        if (method.equalsIgnoreCase("post")) {
            req.setCharacterEncoding("utf-8");
        }
        //response.setContentType("text/html;charset=gb2312")是设置页面中为中文编码
        res.setContentType("text/html;charset=utf-8");

        //放行
        chain.doFilter(request, response);
        return;
    }

    @Override
    public void destroy() {

    }
}
