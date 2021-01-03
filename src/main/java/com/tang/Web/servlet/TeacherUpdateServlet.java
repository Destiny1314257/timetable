package com.tang.Web.servlet;

import com.tang.model.User;
import com.tang.service.UserService;
import com.tang.serviceImpl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/TeacherUpdateServlet")
public class TeacherUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置post提交时候，参数解码方式
        request.setCharacterEncoding("UTF-8");
        User user=requestDateObj(request);
        UserService userService=new UserServiceImpl();
        boolean flag=userService.updateUser(user);
        if(flag){
            //将登陆用户保存到session
            request.getSession().setAttribute("SESSION_USER",user);
            ServletContext servletContext = request.getServletContext();
            servletContext.setAttribute("user",user);
            //跳转到首页
            request.getRequestDispatcher("/teacher/teacherInformation.jsp").forward(request,response);
        }else{
            PrintWriter writer = response.getWriter();
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            writer.write("<script>");
            writer.write("alert('failure！');");
            writer.write("</script>");
            writer.flush();
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    //获得页面数据，封装成User对象
    private User requestDateObj(HttpServletRequest request) {
        //获取页面参数
        String password=request.getParameter("password");
        String name=request.getParameter("name");
        String teamId=request.getParameter("teamId");

        //从session中取出登录用户的id
        User user=(User)request.getSession().getAttribute("SESSION_USER");
        if(password.equals("")){
            password=user.getUserPassword();
        }
        User user1=new User(user.getUserId(),user.getUserAccount(),password,name,user.getUserSex(),user.getLoginState(),Integer.valueOf(teamId));
        return user1;
    }
}
