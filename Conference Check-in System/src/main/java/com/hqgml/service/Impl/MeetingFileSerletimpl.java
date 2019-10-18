package com.hqgml.service.Impl;

import com.hqgml.dao.MeetingFileDao;
import com.hqgml.dao.impl.MeetingFileDaoImpl;
import com.hqgml.domain.MeetingUers;
import com.hqgml.service.MeetingFileServlet;

public class MeetingFileSerletimpl implements MeetingFileServlet {
    MeetingFileDao md= new MeetingFileDaoImpl();
    @Override
    public boolean SetMeeting(MeetingUers mt) {
        int i=  md.SaveMeeting(mt);
        OtherActionService os=new OtherActionService();
        if (i>0){
            return true;
        }else {
            return false;
        }

    }
}
