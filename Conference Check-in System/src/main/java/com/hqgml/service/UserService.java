package com.hqgml.service;


import com.hqgml.domain.ManagerUser;
import com.hqgml.domain.MeetingUers;
import com.hqgml.domain.SurperUser;

public interface UserService {
    /**
     * 查询管理员用户的方法
     *
     * @return
     */
    public ManagerUser GetManagerUser(String username,String password);

    /**
     * 查询超级管理员方法
     *
     * @return
     */
    public SurperUser GetSurper(String username, String password);

    /**
     *  保存注册信息
     */
    public void  SaveUser(ManagerUser mu);

    /**
     *  更新用户信息
     */

    public boolean Updata(ManagerUser mu);
}
