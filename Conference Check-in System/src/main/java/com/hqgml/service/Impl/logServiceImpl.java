package com.hqgml.service.Impl;

import com.hqgml.dao.LogDao;
import com.hqgml.dao.impl.LogDaoImpl;
import com.hqgml.domain.ManagerUser_log;
import com.hqgml.domain.SurperUser_log;
import com.hqgml.service.logService;

public class logServiceImpl implements logService {
    LogDao logDao = new LogDaoImpl();

    @Override
    public boolean manager_log(ManagerUser_log managerUser_log) {
        int i = logDao.Manager_log(managerUser_log);
        if (i > 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean surper_log(SurperUser_log surperUser_log) {
        return false;
    }
}
