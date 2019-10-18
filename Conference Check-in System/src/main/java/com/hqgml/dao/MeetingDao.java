package com.hqgml.dao;

import com.hqgml.domain.Meeting;
import com.hqgml.domain.MeetingUers;

import java.util.List;
import java.util.Map;

public interface MeetingDao {
    /**
     *
     * 添加的方法
     * @return
     */
    public int meeting_add(Meeting mt);

    /**
     * 查找总条数
     * @param parameterMap
     * @return
     */
    public int findTotalCount(Map<String, String[]> parameterMap);

    /***
     * 分页查询
     * @param start
     * @param rows
     * @param parameterMap
     * @return
     */
    public List<Meeting> findByPage(int start, int rows, Map<String, String[]> parameterMap);

    /**
     * 通过id找会议
     * @return
     */
     public  Meeting FindMeetingById( int id);

    /**
     * 通过会议id找会议人数列表
     * @return
     */
    List<MeetingUers> FindPeopleByUid(String mid);

    /**
     * 通过会议id寻找签到成功的人
     * @return
     */
    List<MeetingUers> findCheckByMid(String id);

    /**
     * 通过会议寻找未签到成功的人
     * @return
     */
    List<MeetingUers> findUnCheckByMid(String id);

    /**
     * 通过id删除
     * @param id
     * @return
     */
    int delectSelect(String id);
}
