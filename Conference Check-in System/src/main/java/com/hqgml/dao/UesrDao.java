package com.hqgml.dao;

import com.hqgml.domain.ManagerUser;
import com.hqgml.domain.SurperUser;

/**
 * 用户登录持久层操作
 */
public interface UesrDao {
    /**
     * 查询管理员用户的方法
     *
     * @return
     */
    public ManagerUser GetManagerUser( String user,String password);

    /**
     * 查询超级管理员方法
     *
     * @return
     */
    public SurperUser GetSurper(String user, String password);

    /**
     *  保存注册信息
     */
    public void  SaveUser_manager(ManagerUser mu) ;

    /**
     *  更新用户信息
     */

    public int Updata_manager(ManagerUser mu);

}
