package com.hqgml.service;

import com.hqgml.domain.ManagerUser_log;
import com.hqgml.domain.SurperUser_log;

public interface logService {

   public boolean manager_log(ManagerUser_log managerUser_log);

   public boolean surper_log(SurperUser_log surperUser_log);
}
