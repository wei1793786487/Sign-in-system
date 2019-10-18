package com.hqgml.dao.impl;

import com.hqgml.domain.MeetingUers;
import com.hqgml.utlis.JDBCutils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 用不到
 */
public class OtherActionDao  {

    JdbcTemplate jdbcTemplate=  new JdbcTemplate(JDBCutils.getDataSource());
    /**
     *
     *
     *
     * @param table 表名
     * @param id  标识符
     * @param table_Column 列名
     * @param parameter  要更新的数据
     * @param idparameter 标识符列名
     * @return
     */
    public  int Save(String table,String table_Column,String parameter,String id, String  idparameter ){

        //        UPDATE `super` SET `last_ip` = '255.255.255.0' WHERE `id` = '0' ;
        String sql =" update "+table+" set "+table_Column+" =? where "+id+"=?";
        int update = jdbcTemplate.update(sql,parameter,idparameter);

        return update;
    };

    public String Isexist(String Name) {
        String sql="select * from where photo_name=?";
        try {
            String s = jdbcTemplate.queryForObject(sql, String.class, Name);
            return s;
        } catch (DataAccessException e) {
            return null;
        }
    }

}
