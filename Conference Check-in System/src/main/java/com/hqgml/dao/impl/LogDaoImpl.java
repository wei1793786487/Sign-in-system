package com.hqgml.dao.impl;

import com.hqgml.dao.LogDao;
import com.hqgml.domain.ManagerUser_log;
import com.hqgml.domain.SurperUser_log;
import com.hqgml.utlis.JDBCutils;
import org.springframework.jdbc.core.JdbcTemplate;

public class LogDaoImpl implements LogDao {
    JdbcTemplate jdbcTemplate= new JdbcTemplate(JDBCutils.getDataSource());
    @Override
    public int Manager_log(ManagerUser_log managerUser_log) {
        String sql="insert into manageruser_log(uid,ip,action,time) value(?,?,?,?)";
        int update = jdbcTemplate.update(sql, managerUser_log.getUid(), managerUser_log.getIp(), managerUser_log.getAction(), managerUser_log.getTime());
        return update;
    }

    @Override
    public int SuPer_log(SurperUser_log surperUser_log) {
        return 0;
    }
}
