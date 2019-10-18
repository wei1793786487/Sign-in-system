package com.hqgml.service.Impl;

import com.hqgml.dao.MeetingDao;
import com.hqgml.dao.impl.MeetingDaoimpl;
import com.hqgml.domain.Meeting;
import com.hqgml.domain.MeetingUers;
import com.hqgml.domain.PageBean;
import com.hqgml.service.MeetingService;

import java.util.List;
import java.util.Map;

public class MeetingServiceImpl implements MeetingService {
    MeetingDao md = new MeetingDaoimpl();
    OtherActionService otherActionService = new OtherActionService();

    @Override
    public boolean add_meeting(Meeting meeting) {
        int i = md.meeting_add(meeting);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param _currentPage 当前页面
     * @param _rows        一个页面显示多少行数
     * @param parameterMap 条件查询发送过来的参数
     * @return 查询出来的user的集合
     */
    @Override
    public PageBean<Meeting> FindMeetingByPage(String _currentPage, String _rows, Map<String, String[]> parameterMap) {

        //防止空指针异常
        if (_currentPage == null) {
            _currentPage = "1";
        }
        if (_rows == null) {
            _rows = "5";
        }
        //转换
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        PageBean<Meeting> pb = new PageBean<Meeting>();
        //查询总记录数
        int totalcount = md.findTotalCount(parameterMap);
        pb.setTotalcont(totalcount);
        //计算总页码
        int totalPage = totalcount % rows == 0 ? totalcount / rows : totalcount / rows + 1;
        pb.setTotal(totalPage);
        //防止越界点击
        if (currentPage < 1) {
            currentPage = 1;
        }
        if (rows < 1) {
            rows = 1;
        }
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        if (rows > totalcount) {
            rows = totalcount;
        }
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //调用dao来查询数据
        //需要查询开始页面的索引
        int start = (currentPage - 1) * rows;
        List<Meeting> ls = md.findByPage(start, rows, parameterMap);
        pb.setList(ls);
        return pb;
    }

    @Override
    public Meeting FindMeetingById(String _id) {
        int id = Integer.parseInt(_id);
        Meeting mt = md.FindMeetingById(id);
        return mt;
    }

    @Override
    public List<MeetingUers> FindPeoleByuid(String mid) {
        List<MeetingUers> meetingUers = md.FindPeopleByUid(mid);
        //查询为之后将mid对应的会议的人数加1
//        将list集合的大小转换为string
        String size = "" + meetingUers.size();

        boolean save = otherActionService.Save("meeting", "meeting_number", size, "id", mid);
        System.out.println(save);
        return meetingUers;
    }

    @Override
    public List<MeetingUers> findCheckByMid(String id) {
        List<MeetingUers> meetingUers = md.findCheckByMid(id);
        return meetingUers;
    }

    @Override
    public List<MeetingUers> findUnCheckByMid(String id) {
        List<MeetingUers> meetingUers = md.findUnCheckByMid(id);
        return meetingUers;

    }

    @Override
    public boolean delecySelect(String[] ids) {
        boolean delect = false;
        for (String id : ids) {
            int isDelect = md.delectSelect(id);
            if (isDelect > 0) {
                delect = true;
            }
        }
        return delect;
    }
}

