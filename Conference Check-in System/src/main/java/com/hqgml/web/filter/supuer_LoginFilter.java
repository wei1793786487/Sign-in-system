package com.hqgml.web.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/surper/*"})
public class supuer_LoginFilter implements Filter {
    //傻逼你在这里定义个鸡毛 人家又不创建
//    boolean IsLogun = false;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

//        System.out.println("过滤器出生了");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        转化内携带htttp协议的servlet请求
        //创建是否过滤的表示
        boolean IsLogun = true;
        HttpServletRequest hsr = (HttpServletRequest) request;
        HttpServletResponse hsp = (HttpServletResponse) response;
        String requestURI = hsr.getRequestURI();
        System.out.println("过滤器过滤的路径" + requestURI);
        //写路径的时候不要加* 你个傻逼
        if (requestURI.contains("index.jsp") || requestURI.contains("/user/login") || requestURI.contains("/css/") ||
                requestURI.contains("/img/") || requestURI.contains("/js/") || requestURI.contains("/plugins/") || requestURI.contains("/user/Verification") ||
                requestURI.contains("../index.jsp")
        ) {

            chain.doFilter(request, response);
        } else {
//           获取超管的session
            Object usrper_user = hsr.getSession().getAttribute("usrper_user");
            if (usrper_user == null) {
                IsLogun = false;
            }
        }
        //这里要写双等于  单等于是赋值
        if (IsLogun == true) {
            chain.doFilter(request, response);
        } else if (IsLogun == false) {
            response.getWriter().write("<script>alert('超级管理员尚未登录');window.location.href='../index.jsp'; </script>");
        }

    }

    @Override
    public void destroy() {

    }
}
