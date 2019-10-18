package com.hqgml.dao.impl;

import com.hqgml.dao.LogListServlet;
import com.hqgml.domain.ManagerUser_log;
import com.hqgml.domain.Meeting;
import com.hqgml.domain.PageBean;
import com.hqgml.utlis.JDBCutils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LogListServletImpl implements LogListServlet {
    JdbcTemplate jdbcTemplate= new JdbcTemplate(JDBCutils.getDataSource());
    @Override
    public List<ManagerUser_log> FindMLogById( String uid) {
        String sql="select *from manageruser_log where uid =?";
        List<ManagerUser_log> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ManagerUser_log>(ManagerUser_log.class),uid);
        return query;
    }

    @Override
    public int findTotalCount(Map<String, String[]> parameterMap) {
        //查询总记录数
        //如果没有条件查询的参数的话也不会报异常
        String sql = "select count(*) from manageruser_log where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        //遍历map
        Set<String> strings = parameterMap.keySet();//返回这个map的键值
        List<Object> ls = new ArrayList<Object>();
        for (String string : strings) {
            if ("currentPage".equals(string) || "rows".equals(string)) {
                continue;
            }
            String value = parameterMap.get(string)[0];//有的请求是有很多个 比如多选框 而这个确定就是只有一个数值
            //如果有值就拼接
            if (value != null && !"".equals(value)) {
                sb.append(" and " + string + " like ?");//需要加空格 不然会出现拼接问题
                //拼接完成就会把值放进来
                ls.add("%" + value + "%");
            }

        }
        //拼接完成之后的sql是StringBuilder的tostrinng方法
        sql = sb.toString();
        int integer = jdbcTemplate.queryForObject(sql, Integer.class, ls.toArray());//接受的参数就可变变参数 可变参数的本质就是数值
        return integer;
    }

    @Override
    public  List<ManagerUser_log> findByPage(int start, int rows, Map<String, String[]> parameterMap) {
        String sql = "select * from manageruser_log where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        //遍历map
        Set<String> strings = parameterMap.keySet();//返回这个map的键值
        List<Object> ls = new ArrayList<Object>();
        for (String string : strings) {
            if ("currentPage".equals(string) || "rows".equals(string)) {
                continue;
            }
            String value = parameterMap.get(string)[0];//有的请求是有很多个 比如多选框 而这个确定就是只有一个数值
            //如果有值就拼接
            if (value != null && !"".equals(value)) {
                sb.append(" and " + string + " like ?");//需要加空格 不然会出现拼接问题
                //拼接完成就会把值放进来
                ls.add("%" + value + "%");
            }

        }
        //添加分页查询
        sb.append(" limit ?,? ");
        //下面的查询语句是给的list集合 所以要把start, rows一起添加过去
        ls.add(start);
        ls.add(rows);
        //拼接完成之后的sql是StringBuilder的tostrinng方法
        sql = sb.toString();
        List<ManagerUser_log> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ManagerUser_log>(ManagerUser_log.class), ls.toArray());
        return query;
    }
}
