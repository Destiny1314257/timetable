package com.tang.Web.servlet;

import com.tang.model.Course;
import com.tang.model.PageBean;
import com.tang.model.User;
import com.tang.service.CourseService;
import com.tang.service.TeamService;
import com.tang.service.UserService;
import com.tang.serviceImpl.CourseServiceImpl;
import com.tang.serviceImpl.TeamServiceImpl;
import com.tang.serviceImpl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/TeacherManageServlet")
public class TeacherManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //1.每页多少行 pageSize
        String pageSizeStr=request.getParameter("pageSize");
        Integer pageSize=null;//每页多少行
        if(pageSizeStr!=null&&pageSizeStr.length()>0){
            pageSize=Integer.valueOf(pageSizeStr);
        }else {
            pageSize=10;//默认值
        }
        //2.当前是第几页 currentPage
        String currentPageStr=request.getParameter("currentPage");
        Integer currentPage=null;//当前查询第几页
        if(currentPageStr!=null&&currentPageStr.length()>0){
            currentPage=Integer.valueOf(currentPageStr);
        }else {
            currentPage=1;//默认值
        }
        //3.一共有多少行数据 totalRows
        Integer totalRows=0;
        UserService userService=new UserServiceImpl();
        String sqlCount="select count(*) from user where login_state=?";
        totalRows=userService.getUserCount(sqlCount,2);

        //5.起始行 startRow
        Integer startRow=(currentPage-1)*pageSize;

        StringBuffer sqlRow=new StringBuffer("Select user_id userId,user_account userAccount,user_name userName,user_sex userSex from user where login_state=?");
        sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);
        //把所有老师信息查询出来
        List<User> userList= null;
        try {
            userList = userService.getUserByState(sqlRow.toString(),2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PageBean<User> pageBean=new PageBean<>(currentPage,pageSize,totalRows,userList);
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("pageBeanUser",pageBean);
        request.setAttribute("pageBeanUser",pageBean);
        request.getRequestDispatcher("manage/teachermanage.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
