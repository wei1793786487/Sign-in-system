package com.hqgml.service;

import com.hqgml.domain.ManagerUser_log;

import com.hqgml.domain.PageBean;

import java.util.List;
import java.util.Map;

public interface LogFindServlet {

    public List<ManagerUser_log> findList( String uid);

    PageBean<ManagerUser_log> FindlogByPage(String currentPage, String rows, Map<String, String[]> parameterMap);
}
