package com.tang.Web.servlet;

import com.tang.model.ClassRoom;
import com.tang.model.Course;
import com.tang.model.PageBean;
import com.tang.model.User;
import com.tang.service.ClassRoomService;
import com.tang.service.CourseService;
import com.tang.serviceImpl.ClassRoomServiceImpl;
import com.tang.serviceImpl.CourseServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/ManagerServlet")
public class ManagerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        CourseService courseService=new CourseServiceImpl();
        String sqlCount="select count(*) from course";
        totalRows=courseService.getCourseCount(sqlCount);

        //5.起始行 startRow
        Integer startRow=(currentPage-1)*pageSize;

        StringBuffer sqlRow=new StringBuffer("Select course_id courseId,course_name courseName,course_hour courseHour,course_time courseTime,user_id userId,team_id teamId,classroom_id classroomId from course");
        sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);
        //把所有课程信息查询出来
        List<Course> courseList= null;
        try {
            courseList = courseService.getCourseList(sqlRow.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PageBean<Course> pageBean=new PageBean<>(currentPage,pageSize,totalRows,courseList);
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("pageBean",pageBean);
        request.setAttribute("pageBean",pageBean);
        request.getRequestDispatcher("manage/manage.jsp").forward(request,response);
    }
}
