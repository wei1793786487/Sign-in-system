package com.hqgml.web.servlte;


import com.hqgml.domain.ManagerUser;
import com.hqgml.domain.ResultInfo;
import com.hqgml.domain.SurperUser;
import com.hqgml.domain.jsonString;
import com.hqgml.service.Impl.OtherActionService;
import com.hqgml.service.Impl.UserServiceImpl;
import com.hqgml.service.UserService;
import com.hqgml.utlis.AddressUtils;
import com.hqgml.utlis.Getver;
import com.hqgml.utlis.LogUtlis;
import com.hqgml.utlis.Timeutils;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;


/**
 * 判断用户登录的方法
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    jsonString jsonString = new jsonString();
    UserService us = new UserServiceImpl();
    //保存时间的方法
    OtherActionService oac = new OtherActionService();
    HttpSession session = null;


    //验证码
    public void Verification(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //服务器通知浏览器不要缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");
        //session的获取必须在res之前 不然会报 Cannot create a session after the response has been committed 异常
        session = request.getSession();
        response.setContentType("image/png");
        String s = Getver.outputVerifyImage(115, 35, response.getOutputStream(), 4);
        //看看验证码是啥
        System.out.println(s);
        //存入结果
        session.setAttribute("res", s);
//      System.out.println(s);
    }

    //登录方法
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultInfo uf = new ResultInfo();//创建用来存放各种错误信息的对象

//        判断验证码
        String check = request.getParameter("check");
        session = request.getSession();
        String res = (String) session.getAttribute("res");
        session.removeAttribute("res");
        if (res == null || !res.equalsIgnoreCase(check)) {
            System.out.println("验证码不正确失败");
            uf.setFlag(false);
            uf.setErrorMsg("验证码输入错误");
            writeValue(uf, response);
            return;//验证码验证失败存入然后响应到页面
        }

        //判断异常信息
        if (request.getParameter("username") == "") {
            uf.setFlag(false);
            uf.setErrorMsg("用户名不能为空");
            writeValue(uf, response);
            return;
        }
        if (request.getParameter("password") == "") {
            uf.setFlag(false);
            uf.setErrorMsg("请填写密码");
            writeValue(uf, response);
            return;
        }
        if (request.getParameter("verifycode") == "") {
            uf.setFlag(false);
            uf.setErrorMsg("验证码不能为空");
            writeValue(uf, response);
            return;
        }


        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        System.out.println(username);
//        System.out.println(password);
        SurperUser surperUser = us.GetSurper(username, password);
        ManagerUser managerUser = us.GetManagerUser(username, password);
        if (surperUser != null) {
            //超管
            session.setAttribute("usrper_user", surperUser);
            uf.setFlag(true);
            uf.setUser(0);
            //更新最后登录的参数
            oac.Save("surperuser", "lasttime", Timeutils.Gettime(new Date()), "name", surperUser.getName());
            oac.Save("surperuser", "address", AddressUtils.GetAddress(request), "name", surperUser.getName());
            writeValue(uf, response);
            writeValueAsString(uf);
            return;
        } else if (managerUser != null) {
            //铺管

            session.setAttribute("manager", managerUser);
            System.out.println("添加的sess++++++" + session.getAttribute("manager"));
            uf.setFlag(true);
            uf.setUser(1);
            oac.Save("manageruser", "lasttime", Timeutils.Gettime(new Date()), "name", managerUser.getName());
            oac.Save("manageruser", "address", AddressUtils.GetAddress(request), "name", managerUser.getName());
            writeValue(uf, response);
            writeValueAsString("返回的json      " + uf);
            LogUtlis.setlog(session,request,"登录成功");
            return;
        } else {
            uf.setFlag(false);
            uf.setErrorMsg("账号或者密码错误");
            writeValue(uf, response);
        }

    }

    //修改密码
    public void chancepswd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("修改密码服务");
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        String newpassword2 = request.getParameter("newpassword2");
        if(newpassword==""||newpassword==null||newpassword2==null||newpassword2==""||!newpassword.equals(newpassword2)){
            jsonString.setInformation("两次密码不一致,或者新密码输入为空");
            writeValue(jsonString,response);
            return;
        }

        ManagerUser manager = (ManagerUser) session.getAttribute("manager");
        String name = manager.getName();

//        查询账号密码对不对
        SurperUser surperUser = us.GetSurper(name, oldpassword);
        ManagerUser managerUser = us.GetManagerUser(name, oldpassword);
        if (surperUser != null) {
          jsonString.setInformation("超级管理员不让改密码，滚蛋");
          writeValue(jsonString,response);
          return;
        } else if (managerUser != null) {
            //铺管
            //这里需要从session域里面获取对象 并且将新密码添加到session域里面的manger，并传过去
            manager.setPswd(newpassword);
            boolean updata = us.Updata(manager);
            if (updata){
                LogUtlis.setlog(session,request,"修改密码");
                jsonString.setInformation("密码修改成功");
                writeValue(jsonString,response);
                request.getSession().invalidate();//自爆所有session


            }else {
                LogUtlis.setlog(session,request,"但是失败了");
                jsonString.setInformation("密码修改失败,咱也不知道为啥,反正这个情况一般不会出现");
                writeValue(jsonString,response);

            }

        } else {
            jsonString.setInformation("密码输入错误");
            writeValue(jsonString,response);

        }

    }

    public void exits(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();//自爆所有session
        response.sendRedirect(request.getContextPath()+"../index.jsp");
    }

}
