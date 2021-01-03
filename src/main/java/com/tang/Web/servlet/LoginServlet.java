package com.tang.Web.servlet;


import com.tang.dao.UserDao;
import com.tang.model.Apply;
import com.tang.model.User;
import com.tang.service.ApplyService;
import com.tang.service.UserService;
import com.tang.serviceImpl.ApplyServiceImpl;
import com.tang.serviceImpl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //获取前台提交的email和密码
        String account=request.getParameter("account");
        String password=request.getParameter("password");

        //根据email和密码查询申请人

        UserService userService=new UserServiceImpl();
        User user=userService.getUserAccountAndPassword(account,password);
        //判断applicant==null,返回登录页面，不为空进入主页面
        if(user!=null){
            //将登陆用户保存到session
            request.getSession().setAttribute("SESSION_USER",user);

            //用户名和密码输入正确，判断用户是管理员,学生还是老师
            //根据登录状态进行判断 0 管理员 1 学生 2 老师
            Integer loginState=user.getLoginState();
            ApplyService applyService=new ApplyServiceImpl();
            List<Apply> applyList=null;
            try {
                applyList=applyService.getApplyList();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            StringBuffer stringBuffer=new StringBuffer();
            for(int i=0;i<applyList.size();i++){
                Apply apply=applyList.get(i);
                if(apply.getApplyState().equals(1)){
                    String str="编号为"+apply.getCourseId()+"的课程从教室编号为"+apply.getApplyOldclassroom()+"调到教室编号为"+apply.getApplyNewclassroom()+"时间从"+apply.getApplyOldtime()+"调到"+apply.getApplyNewtime();
                    stringBuffer.append(str);
                }else if(apply.getApplyState().equals(2)){
                    String str="编号为"+apply.getCourseId()+"的课程从教室编号为"+apply.getApplyOldclassroom()+"调到教室编号为"+apply.getApplyNewclassroom()+"时间从"+apply.getApplyOldtime()+"调到"+apply.getApplyNewtime()+"调课失败，失败原因：教室已被占用";
                    stringBuffer.append(str);
                }
            }
            String str=stringBuffer.toString();
            ServletContext servletContext = request.getServletContext();
            servletContext.setAttribute("str",str);
            if(loginState==0){
                response.sendRedirect("/ManagerServlet");
            }else if(loginState==1){
                response.sendRedirect("/StudentServlet");
            }else if(loginState==2){
                response.sendRedirect("/TeacherServlet");
            }
        }else{
            PrintWriter writer = response.getWriter();
            writer.write("<script>");
            writer.write("alert('用户名或密码错误！');");
            writer.write("window.location.href='index.jsp'");
            writer.write("</script>");
            writer.flush();
            writer.close();
        }
    }
}
