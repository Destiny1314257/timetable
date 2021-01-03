package com.tang.Web.servlet;

import com.tang.model.User;
import com.tang.service.UserService;
import com.tang.serviceImpl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        //获取请求参数
        //String type=request.getParameter("type");
        //获取前台提交的页面参数
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        String name=request.getParameter("name");
        String sex=request.getParameter("sex");
        String profession=request.getParameter("profession");
        String team=request.getParameter("team");
        //获取请求参数
        String type=request.getParameter("type");
        PrintWriter writer = response.getWriter();
        UserService userService=new UserServiceImpl();
        if("accountAjaxValidate".equals(type)) {
            //判断是否有相同的账号
            Integer count = userService.selectUserAccountCount(account);
            String reg="{\"reg\":\"账号已被注册！\"}";
            if (count > 0) {
                //数据库中存在相同的账号
                //通过response对象给客户端一个错误提示
                writer.print(reg);
            } else {
                writer.print("{\"reg\":\"账号可以使用！\"}");
            }
        }else{
            if(profession.equals("student")) {//学生
                if (team.equals("")) {
                    team = "1";
                }
                //插入到数据库中，对数据进行封装，自己封装成一个对象
                User user=new User(null,account,password,name,sex,1,Integer.parseInt(team));
                //flag是否注册成功
                boolean flag=userService.saveUser(user);
                if(flag){

                    //注册成功就跳转到登录页面 重定向
                    response.sendRedirect("index.jsp");
                }else {
                    //注册失败就返回注册页面 请求转发
                    RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                    dispatcher.forward(request,response);
                }
            }else if(profession.equals("teacher")){//老师
                //插入到数据库中，对数据进行封装，自己封装成一个对象
                User user=new User(null,account,password,name,sex,2,null);
                //flag是否注册成功
                boolean flag=userService.saveUser(user);
                if(flag){

                    //注册成功就跳转到登录页面 重定向
                    response.sendRedirect("index.jsp");
                }else {
                    //注册失败就返回注册页面 请求转发
                    RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                    dispatcher.forward(request,response);
                }
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
