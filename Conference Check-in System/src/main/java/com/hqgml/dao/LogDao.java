package com.hqgml.dao;

import com.hqgml.domain.ManagerUser_log;
import com.hqgml.domain.SurperUser_log;

public interface LogDao {
    /**
     * 管理员的日志的添加
     * @return
     */
    public int Manager_log(ManagerUser_log managerUser_log);

    /**
     * 超管的日志的添加
     * @return
     */
    public int SuPer_log(SurperUser_log surperUser_log);

}
