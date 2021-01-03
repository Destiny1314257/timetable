package com.tang.Web.servlet;

import com.tang.model.ClassRoom;
import com.tang.model.PageBean;
import com.tang.model.User;
import com.tang.service.ClassRoomService;
import com.tang.service.UserService;
import com.tang.serviceImpl.ClassRoomServiceImpl;
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

@WebServlet(urlPatterns = "/TeacherOperationServlet")
public class TeacherOperationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//设置post提交时候，参数解码方式
        request.setCharacterEncoding("UTF-8");
        String method=request.getParameter("method");
        ServletContext servletContext = request.getServletContext();
        PageBean<User> pageBeanUser=(PageBean<User>)servletContext.getAttribute("pageBeanUser");
        //查询
        if("search".equals(method)){
            String userId=request.getParameter("userId");
            UserService userService=new UserServiceImpl();
            //把教师信息查询出来
            List<User> userList= null;
            String sql="select user_id userId,user_account userAccount,user_password userPassword,user_name userName,user_sex userSex,login_state loginState,team_id teamId from user where user_id=?";
            try {
                userList = userService.getUserList(sql,Integer.valueOf(userId));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            servletContext.setAttribute("userList",userList);
            request.getRequestDispatcher("manage/teacherSearch.jsp").forward(request,response);
        }else if("delete".equals(method)){//删除
            String userId=request.getParameter("userId");
            UserService userService=new UserServiceImpl();
            userService.deleteUser(userId);
            for(int i=0;i<pageBeanUser.getList().size();i++){
                User user=pageBeanUser.getList().get(i);
                if(user.getUserId().equals(Integer.valueOf(userId))){
                    pageBeanUser.getList().remove(i);
                }
            }
            servletContext.setAttribute("pageBeanUser",pageBeanUser);
            request.getRequestDispatcher("manage/teachermanage.jsp").forward(request,response);
        }else if("add".equals(method)){//增加
            String userId=request.getParameter("userId");
            User user=requestDateObj(request);
            UserService userService=new UserServiceImpl();
            User user1=userService.getUser(userId);
            if(user1!=null){
                PrintWriter writer = response.getWriter();
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                writer.write("<script>");
                writer.write("alert('failure！');");
                writer.write("</script>");
                writer.flush();
                writer.close();
            }else {
                userService.saveUser(user);
                pageBeanUser.getList().add(user);
                //跳转到首页
                servletContext.setAttribute("pageBeanUser",pageBeanUser);
                request.getRequestDispatcher("manage/teachermanage.jsp").forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    //获得页面数据，封装成Course对象
    private User requestDateObj(HttpServletRequest request) {
        //获取页面参数
        String userId=request.getParameter("userId");
        String userAccount=request.getParameter("userAccount");
        String userPassword=request.getParameter("userPassword");
        String userName=request.getParameter("userName");
        String userSex=request.getParameter("userSex");
        //获取用户
        User user=new User(Integer.valueOf(userId),userAccount,userPassword,userName,userSex,2,null);
        return user;
    }
}
