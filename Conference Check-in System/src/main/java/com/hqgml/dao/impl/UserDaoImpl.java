package com.hqgml.dao.impl;

import com.hqgml.dao.UesrDao;
import com.hqgml.domain.ManagerUser;
import com.hqgml.domain.SurperUser;
import com.hqgml.utlis.JDBCutils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UesrDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCutils.getDataSource());

    @Override
    public ManagerUser GetManagerUser(String username, String password) {
        String sql = "select *from manageruser where name=? and pswd=?";
        try {
            ManagerUser managerUser = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<ManagerUser>(ManagerUser.class), username, password);
            return managerUser;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public SurperUser GetSurper(String user, String password) {
        String sql = "select *from surperuser where name =? and pswd=?";
        try {
            SurperUser surperUser = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<SurperUser>(SurperUser.class), user, password);
            return surperUser;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void SaveUser_manager(ManagerUser mu) {

    }

    @Override
    public int Updata_manager(ManagerUser mu) {
       String sql ="update manageruser set name=?,sex=?,idcard=?,phone=?,pswd=?,address=?,lasttime=? where name=? ";
        int update = jdbcTemplate.update(sql, mu.getName(),mu.getSex(),mu.getIdcard(),mu.getPhone(),mu.getPswd(),mu.getAddress(),mu.getLasttime(),mu.getName());
        return update;
    }


}