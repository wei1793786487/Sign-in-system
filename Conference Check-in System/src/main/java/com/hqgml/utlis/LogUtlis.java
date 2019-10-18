package com.hqgml.utlis;

import com.hqgml.domain.ManagerUser;
import com.hqgml.domain.ManagerUser_log;
import com.hqgml.service.Impl.logServiceImpl;
import com.hqgml.service.logService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class LogUtlis {


    public static  void setlog(HttpSession session, HttpServletRequest request,String action){
        logService logService= new logServiceImpl();
        //日志操作
        ManagerUser_log managerUser_log= new ManagerUser_log();
        ManagerUser manager = (ManagerUser) session.getAttribute("manager");
        managerUser_log.setUid(manager.getId());
        managerUser_log.setIp(IpUtil.getIpAddress(request));
        managerUser_log.setTime(Timeutils.Gettime(new Date()));
        managerUser_log.setAction(action);
        //日志操作完成
        logService.manager_log(managerUser_log);
    }

}
