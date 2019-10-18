package com.hqgml.service;

import com.hqgml.domain.ManagerUser;
import com.hqgml.domain.Meeting;
import com.hqgml.domain.MeetingUers;
import com.hqgml.domain.PageBean;

import java.util.List;
import java.util.Map;

public interface MeetingService {
    /**
     * 添加方法
     * @return 返回是否添加成功
     */
    public boolean add_meeting(Meeting meeting);

    /**
     * 分页查询与迷糊查询
     * @param currentPage
     * @param rows
     * @param parameterMap
     */
    public PageBean<Meeting> FindMeetingByPage(String currentPage, String rows, Map<String, String[]> parameterMap);

    /**
     * 通过id找到一个会议
     * @return
     */
   public  Meeting FindMeetingById( String id);

    /**
     * 通过会议的id查询所有的属于这个会议的数据
     * @param mid
     * @return
     */
    public List<MeetingUers> FindPeoleByuid(String mid);

    /**
     * 查找成功签到的人的列表
     * @return
     */
    List<MeetingUers> findCheckByMid(String id);

    /**
     * 查找未签到成功的人的列表
     * @return
     */
    List<MeetingUers> findUnCheckByMid(String id);


    boolean delecySelect(String[] ids);

//    boolean updateMeeting(String )
}
