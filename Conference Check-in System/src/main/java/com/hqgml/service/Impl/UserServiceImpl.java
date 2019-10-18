package com.hqgml.service.Impl;

import com.hqgml.dao.*;
import com.hqgml.dao.impl.UserDaoImpl;
import com.hqgml.domain.ManagerUser;
import com.hqgml.domain.MeetingUers;
import com.hqgml.domain.SurperUser;
import com.hqgml.service.UserService;

public class UserServiceImpl implements UserService {
    UesrDao ud = new UserDaoImpl();


    @Override
    public ManagerUser GetManagerUser(String username, String password) {
        ManagerUser managerUser = ud.GetManagerUser(username, password);
        return managerUser;
    }

    @Override
    public SurperUser GetSurper(String username, String password) {
        SurperUser surperUser = ud.GetSurper(username, password);
        return surperUser;
    }

    @Override
    public void SaveUser(ManagerUser mu) {

    }

    @Override
    public boolean Updata(ManagerUser mu) {
        int i = ud.Updata_manager(mu);
        if (i>0){
            return true;
        }else {
            return false;
        }

    }


}
