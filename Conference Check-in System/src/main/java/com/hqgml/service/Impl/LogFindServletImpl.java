package com.hqgml.service.Impl;

import com.hqgml.dao.LogListServlet;
import com.hqgml.dao.impl.LogListServletImpl;
import com.hqgml.domain.ManagerUser_log;
import com.hqgml.domain.Meeting;
import com.hqgml.domain.PageBean;
import com.hqgml.service.LogFindServlet;

import java.util.List;
import java.util.Map;

public class LogFindServletImpl implements LogFindServlet {
    LogListServlet logListServlet= new LogListServletImpl();
    @Override
    public List<ManagerUser_log> findList(String uid) {
        List<ManagerUser_log> managerUser_logs = logListServlet.FindMLogById(uid);
        return managerUser_logs;
    }

    @Override
    public PageBean<ManagerUser_log> FindlogByPage(String _currentPage, String _rows, Map<String, String[]> parameterMap) {
        //防止空指针异常
        if (_currentPage == null) {
            _currentPage = "1";
        }
        if (_rows == null) {
            _rows = "5";
        }
        //转换
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        PageBean<ManagerUser_log> pb = new PageBean<ManagerUser_log>();
        //查询总记录数
        int totalcount = logListServlet.findTotalCount(parameterMap);
        pb.setTotalcont(totalcount);
        //计算总页码
        int totalPage = totalcount % rows == 0 ? totalcount / rows : totalcount / rows + 1;
        pb.setTotal(totalPage);
        //防止越界点击
        if (currentPage < 1) {
            currentPage = 1;
        }
        if (rows < 1) {
            rows = 1;
        }
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        if (rows > totalcount) {
            rows = totalcount;
        }
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //调用dao来查询数据
        //需要查询开始页面的索引
        int start = (currentPage - 1) * rows;
        List<ManagerUser_log> byPage = logListServlet.findByPage(start, rows, parameterMap);
        pb.setList(byPage);
        return pb;
    }
}
