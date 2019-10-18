package com.hqgml.web.servlte;


import com.hqgml.domain.ManagerUser;
import com.hqgml.domain.ManagerUser_log;
import com.hqgml.domain.Meeting;
import com.hqgml.domain.PageBean;
import com.hqgml.service.Impl.LogFindServletImpl;
import com.hqgml.service.LogFindServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/log/*")
public class LogServlet extends BaseServlet {
    LogFindServlet logFindServlet = new LogFindServletImpl();

    public void manager_log(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("我被访问了");
        ManagerUser manager = (ManagerUser) req.getSession().getAttribute("manager");
        String currentPage = req.getParameter("currentPage");//当前页码
//      String rows =request.getParameter("rows");//每页的条数
        String rows = "10";// 每页显示10条写死
        //获取所有的参数，参数用来联想查询的
        Map<String, String[]> parameterMap = req.getParameterMap();
        PageBean<ManagerUser_log> managerUser_logPageBean = logFindServlet.FindlogByPage(currentPage, rows, parameterMap);
        writeValue(managerUser_logPageBean, resp);



    }
}
