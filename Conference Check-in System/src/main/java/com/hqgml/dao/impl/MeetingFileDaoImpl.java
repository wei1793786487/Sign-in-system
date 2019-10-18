package com.hqgml.dao.impl;

import com.hqgml.dao.MeetingFileDao;
import com.hqgml.domain.MeetingUers;
import com.hqgml.service.MeetingFileServlet;
import com.hqgml.utlis.JDBCutils;
import jdk.nashorn.internal.scripts.JD;
import org.springframework.jdbc.core.JdbcTemplate;

public class MeetingFileDaoImpl implements MeetingFileDao {
    JdbcTemplate jdbcTemplate= new JdbcTemplate(JDBCutils.getDataSource());
    @Override
    public int SaveMeeting(MeetingUers mt) {
        String sql="insert into meetinguers(mid,photo_name,person_name) value(?,?,?) ";
        int update = jdbcTemplate.update(sql, mt.getMid(), mt.getPhoto_name(), mt.getPerson_name());
        return update;
    }
}
