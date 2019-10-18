package com.hqgml.dao;


import com.hqgml.domain.ManagerUser_log;
import com.hqgml.domain.PageBean;

import java.util.List;
import java.util.Map;

public interface LogListServlet {

    /**
     * 通过id寻找log日志
     */
    public List<ManagerUser_log> FindMLogById(String uid);

    int findTotalCount(Map<String, String[]> parameterMap);

    List<ManagerUser_log> findByPage(int start, int rows, Map<String, String[]> parameterMap);
}
