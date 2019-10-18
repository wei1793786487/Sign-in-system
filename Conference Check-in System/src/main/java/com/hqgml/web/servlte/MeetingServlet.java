package com.hqgml.web.servlte;

import com.hqgml.domain.*;
import com.hqgml.utlis.*;
import com.hqgml.service.Impl.MeetingServiceImpl;
import com.hqgml.service.MeetingService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.soap.MTOM;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
//我tdm写的是些啥 我也不知道 哈哈哈哈

/**
 * 会议操作方法
 */

@WebServlet("/Meeting/*")
public class MeetingServlet extends BaseServlet {
    private MeetingService ms = new MeetingServiceImpl();
   private jsonString jsonString = new jsonString();//json信息

    /**
     * 添加用户的方法
     *
     * @param req
     * @param resp
     */
    public void addmeeting(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
//       异常的判断
        Map<String, String[]> parameterMap = req.getParameterMap();
        Set<String> strings = parameterMap.keySet();
        String meeting_phone = req.getParameter("meeting_phone");
        Meeting mt = new Meeting();
        try {
            BeanUtils.populate(mt, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            jsonString.setInformation("添加异常");
            writeValue(jsonString, resp);

        } catch (InvocationTargetException e) {
            e.printStackTrace();
            jsonString.setInformation("添加异常");
            writeValue(jsonString, resp);

        }
        ManagerUser  manager = (ManagerUser) req.getSession().getAttribute("manager");
        //该地方如果用户没登录就会报异常 但是是没有这样情况的因为过滤器保证，如果没登录的话 会禁止访问
        Integer id = manager.getId();
        //因为这里数据库是int类型 参数获取之后的是string 没有办法通过beanutils进行封装，
        mt.setAdd_id(id);
        //添加会议唯一标识符 用来创建人员库的id唯一表示
        mt.setUuid(UuidUtil.getUuid());
        TencentString tencentString = TencentUtils.CreateGroup(mt.getUuid(), mt.getUuid());
        //返回值为空说明报了异常，那么就是有错误的
        if (tencentString.getError() != null) {
            jsonString.setInformation(tencentString.getError());
            writeValue(jsonString, resp);
        } else {
            //不然就是成功了呗
            boolean b = ms.add_meeting(mt);
            if (b) {
                LogUtlis.setlog(session,req,"添加会议");
                jsonString.setInformation("添加成功");
                writeValue(jsonString, resp);
                return;
            } else {
                LogUtlis.setlog(session,req,"添加会议,但是失败了");
                jsonString.setInformation("添加失败");
                writeValue(jsonString, resp);
            }

        }


    }

    public void meetinglist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPage = req.getParameter("currentPage");//当前页码
//      String rows =request.getParameter("rows");//每页的条数
        String rows = "10";// 每页显示10条写死
        //获取所有的参数，参数用来联想查询的
        Map<String, String[]> parameterMap = req.getParameterMap();
        PageBean<Meeting> meetingPageBean = ms.FindMeetingByPage(currentPage, rows, parameterMap);
        writeValue(meetingPageBean, resp);

    }

    public void findmeetingbyid(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String id = req.getParameter("id");
        Meeting mt = ms.FindMeetingById(id);
        if (mt == null) {
            jsonString.setInformation("会议不存在");
            writeValue(jsonString, resp);
            return;
        } else {
            writeValue(mt, resp);
            return;
        }

    }

    public void personlist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String mid = req.getParameter("mid");
        if (mid == null || mid.equals("")) {
            jsonString.setInformation("该会议非法");
            writeValue(jsonString, resp);
            return;
        }
        Meeting meeting = ms.FindMeetingById(mid);
        if (meeting == null) {
            jsonString.setInformation("该会议不存在");
            writeValue(jsonString, resp);
            return;
        }
        List<MeetingUers> meetingUers = ms.FindPeoleByuid(mid);
        writeValue(meetingUers, resp);
    }

    public void findCheckByMid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mid = req.getParameter("mid");
        List<MeetingUers> meetingUers = ms.findCheckByMid(mid);
        writeValue(meetingUers, resp);
    }

    public void findUnCheckByMid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mid = req.getParameter("mid");
        List<MeetingUers> meetingUers = ms.findUnCheckByMid(mid);
        writeValue(meetingUers, resp);
    }

    public void findCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取会议传递的id的参数
        String id = req.getParameter("id");
        List<MeetingUers> checkByMid = ms.findCheckByMid(id);
        List<MeetingUers> unCheckByMid = ms.findUnCheckByMid(id);
        Map map = new HashMap();
        map.put("checkByMid", checkByMid);
        map.put("uncheckByMid", unCheckByMid);
        writeValue(map, resp);
    }

    public void delectMeeting(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String[] ids = req.getParameterValues("ids");
        boolean IsDelect= ms.delecySelect(ids);
        if (IsDelect){
            LogUtlis.setlog(session,req,"添加会议");
            resp.getWriter().write("<script>alert('删除成功');window.location.href=document.referrer;</script>");
        }else {
            LogUtlis.setlog(session,req,"添加会议，但是失败了");
            resp.getWriter().write("<script>alert('删除失败');window.location.href=document.referrer;</script>");
        }
    }

}
