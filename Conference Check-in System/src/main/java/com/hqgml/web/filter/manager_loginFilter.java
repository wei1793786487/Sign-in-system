package com.hqgml.web.filter;

import com.hqgml.domain.ManagerUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/managers/*"})
public class manager_loginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("wob");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        boolean IsLogun = true;
        HttpServletRequest hsr = (HttpServletRequest) request;
        HttpServletResponse hsp = (HttpServletResponse) response;
        String requestURI = hsr.getRequestURI();
        System.out.println("过滤器过滤的路径" + requestURI);
        if (requestURI.contains("index.jsp") || requestURI.contains("/user/login") || requestURI.contains("/css/") ||
                requestURI.contains("/img/") || requestURI.contains("/js/") || requestURI.contains("/plugins/") || requestURI.contains("/user/Verification") ||
                requestURI.contains("../index.jsp")
        ) {

            chain.doFilter(request, response);
        } else {
            Object manager = hsr.getSession().getAttribute("manager");
//            System.out.println("登录的管理员信息"+manager);
            if (manager == null) {
                IsLogun = false;
            }
        }
        if (IsLogun == true) {
            try {
                chain.doFilter(request, response);
            } catch (IOException e) {
                System.out.println("过滤异常");
            }
        } else if (IsLogun == false) {
            response.getWriter().write("<script>alert('普通管理员尚未登录');window.location.href='../index.jsp'; </script>");
        }

    }

    @Override
    public void destroy() {

    }
}
