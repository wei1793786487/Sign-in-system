package com.hqgml.web.servlte;


import com.hqgml.domain.Meeting;
import com.hqgml.domain.TencentString;
import com.hqgml.domain.jsonString;
import com.hqgml.service.Impl.MeetingServiceImpl;
import com.hqgml.service.Impl.OtherActionService;
import com.hqgml.service.MeetingService;
import com.hqgml.utlis.StringUtils;
import com.hqgml.utlis.TencentUtils;
import com.hqgml.utlis.UrlUtils;
import com.hqgml.utlis.WriteJsonUtlis;
import com.tencentcloudapi.iai.v20180301.models.Candidate;
import com.tencentcloudapi.iai.v20180301.models.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@SuppressWarnings("all")
@WebServlet("/video")
public class VideoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MeetingService meetingService = new MeetingServiceImpl();
        OtherActionService otherActionService= new OtherActionService();
        jsonString jsonString = new jsonString();
        String personName = null;
        Float score = null;
        String person_id=null;
        float Recognition = (float) 80.00;//大于这个比例就算识别成功
        System.out.println("我被访问了");
        String image = request.getParameter("image");
        Map<String, String[]> parameterMap = request.getParameterMap();
        String mid = request.getParameter("mid");//需要判断这个mid不为空
        //通过id找打是哪个会议 再找到是哪个会议组
        if (image != null && mid != null && !mid.equals("")) {
            Meeting meeting = meetingService.FindMeetingById(mid);
//        System.out.println(image);
            //将找到会议的唯一标识uuid放入寻找
            TencentString tencentString = TencentUtils.SearchFaces(meeting.getUuid(), image);
            //得到结果集
            Result[] result = tencentString.getResult();
            //遍历结果集,得到对比度，与名字
            for (Result result1 : result) {
                Candidate[] candidates = result1.getCandidates();
                for (Candidate candidate : candidates) {
                    personName = candidate.getPersonName();
                    score = candidate.getScore();
                    person_id=candidate.getPersonId();
//                    //存入,存屁 有啥用
//                    tencentString.setPersonName(personName);
//                    tencentString.setScore(score);
                }
            }
            if (personName != null || score != null) {
                if (score > Recognition) {
                    if (personName==null){
                        //如果没有存入，，名字 那么就显示空字符串
                        personName="";
                    }
                    //人脸识别确认
                    //根据personid修改数据库是否签到成功
                    //将_替换为。获取表里对应的phpto_name；
                    String photo_name = StringUtils.ReplaceString(person_id, "_", ".");
                    otherActionService.Save("meetinguers","IsCheck","1","photo_name",photo_name);
                    String rpersonName= UrlUtils.decode(personName,"utf-8");
                    jsonString.setInformation("签到成功," + rpersonName + "");
                    WriteJsonUtlis.writeValue(jsonString, response);
                    return;
                } else {
                    jsonString.setInformation("签到失败,请摆正姿势重试");
                    WriteJsonUtlis.writeValue(jsonString, response);
                    return;
                }

            } else {
                jsonString.setInformation("服务器内部发生异常");
                WriteJsonUtlis.writeValue(jsonString, response);
                return;
            }


        } else {
            jsonString.setInformation("参数异常,请刷新重试");
            WriteJsonUtlis.writeValue(jsonString, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
