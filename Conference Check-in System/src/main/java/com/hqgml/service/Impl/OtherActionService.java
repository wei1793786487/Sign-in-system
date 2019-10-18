package com.hqgml.service.Impl;

import com.hqgml.dao.impl.OtherActionDao;
import com.hqgml.domain.MeetingUers;

public class OtherActionService {
    OtherActionDao dao = new OtherActionDao();

    /**
     * @param table        表名
     * @param table_Column 列名
     * @param parameter    更细参数
     * @param id           标识
     * @param idparameter  标识参数
     * @return 更新是否成功
     */
    public boolean Save(String table, String table_Column, String parameter, String id, String idparameter) {

        boolean IsSuccess;

        int save = dao.Save(table, table_Column, parameter, id, idparameter);
        if (save > 0) {
            IsSuccess = true;
        } else {
            IsSuccess = false;
        }

        return IsSuccess;
    }

    /**
     * 判断文件名是否存在
     *
     * @param name
     * @return 返回是否存在
     */
    public boolean Isexist(String name) {

        String isexist = dao.Isexist(name);
        if (isexist != null) {
            return true;
        } else {
            return false;
        }

    }

}
