package com.tang.Web.servlet;

import com.tang.model.*;
import com.tang.service.ClassRoomService;
import com.tang.service.CourseService;
import com.tang.service.UserService;
import com.tang.serviceImpl.ClassRoomServiceImpl;
import com.tang.serviceImpl.CourseServiceImpl;
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

@WebServlet(urlPatterns = "/CourseManageServlet")
public class CourseManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置post提交时候，参数解码方式
        request.setCharacterEncoding("UTF-8");
        String method=request.getParameter("method");
        ServletContext servletContext = request.getServletContext();
        PageBean<Course> pageBean=(PageBean<Course>)servletContext.getAttribute("pageBean");
        PageBean<ClassRoom> pageBean2=(PageBean<ClassRoom>)servletContext.getAttribute("pageBean2");
        //查询
        if("search".equals(method)){
            String courseId=request.getParameter("courseId");
            CourseService courseService=new CourseServiceImpl();
            //把所有课程信息查询出来
            List<Course> courseList= null;
            String sql="select course_id courseId,course_name courseName,course_hour courseHour,course_time courseTime,user_id userId,team_id teamId,classroom_id classroomId from course where course_id=?";
            try {
                courseList = courseService.getCourseListByPage(sql,Integer.valueOf(courseId));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            servletContext.setAttribute("courseList",courseList);
            request.getRequestDispatcher("manage/coursesearch.jsp").forward(request,response);
        }else if("delete".equals(method)){//删除
            String courseId=request.getParameter("courseId");
            CourseService courseService=new CourseServiceImpl();
            Course course=courseService.getCourseById(courseId);
            courseService.deleteCourse(courseId);
            for(int i=0;i<pageBean.getList().size();i++){
                Course course1=pageBean.getList().get(i);
                if(course1.getCourseId().equals(Integer.valueOf(courseId))){
                    pageBean.getList().remove(i);
                }
            }
            ClassRoomService classRoomService=new ClassRoomServiceImpl();
            classRoomService.updateClassroom(course.getClassroomId());
            servletContext.setAttribute("pageBean",pageBean);
            request.getRequestDispatcher("manage/coursemanage.jsp").forward(request,response);
        }else if("update".equals(method)){//更改
            ClassRoom classRoom=requestDateObj2(request);
            Course course=requestDateObj(request);
            CourseService courseService=new CourseServiceImpl();
            boolean flag=courseService.updateCourse(course);
            for(int i=0;i<pageBean.getList().size();i++){
                Course course1=pageBean.getList().get(i);
                if(course1.getCourseId().equals(course.getCourseId())){
                    pageBean.getList().set(i,course);
                }
            }
            if(flag){
                ClassRoomService classRoomService=new ClassRoomServiceImpl();
                if(classRoom.getClassroomId().equals(course.getClassroomId())){
                    classRoomService.updateClassroom1(course.getCourseTime(),course.getClassroomId());
                }else {
                    classRoomService.updateClassroom(classRoom.getClassroomId());
                    classRoomService.updateClassroom1(course.getCourseTime(),course.getClassroomId());
                }
                //跳转到首页
                servletContext.setAttribute("pageBean",pageBean);
                request.getRequestDispatcher("manage/coursemanage.jsp").forward(request,response);
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
        }else if("add".equals(method)){//增加
            String courseId=request.getParameter("courseId");
            CourseService courseService=new CourseServiceImpl();
            Course course=courseService.getCourseById(courseId);
            Course course1=requestDateObj(request);
            if(course!=null){
                PrintWriter writer = response.getWriter();
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                writer.write("<script>");
                writer.write("alert('failure！');");
                writer.write("</script>");
                writer.flush();
                writer.close();
            }else {
                boolean flag1=true;
                ClassRoomService classRoomService=new ClassRoomServiceImpl();
                ClassRoom classRoom=classRoomService.getClassroomById(course1.getClassroomId());
                if(classRoom!=null&&classRoom.getClassroomState().equals(1)){
                    flag1=false;
                }
                if(flag1){
                    boolean flag=courseService.InsertCourse(course1);
                    pageBean.getList().add(course1);
                    if(classRoom!=null){
                        classRoomService.updateClassroom1(course1.getCourseTime(),course1.getClassroomId());
                    }else {
                        ClassRoom classRoom1=requestDateObj3(request);
                        classRoomService.InsertClassroom(classRoom1);
                    }
                    //跳转到首页
                    servletContext.setAttribute("pageBean",pageBean);
                    request.getRequestDispatcher("manage/coursemanage.jsp").forward(request,response);
                }else {
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
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    //获得页面数据，封装成Course对象
    private Course requestDateObj(HttpServletRequest request) {
        //获取页面参数
        String courseId=request.getParameter("courseId");
        String courseName=request.getParameter("courseName");
        String courseHour=request.getParameter("courseHour");
        String courseTime=request.getParameter("courseTime");
        String userId=request.getParameter("userId");
        String teamId=request.getParameter("teamId");
        String classroomId=request.getParameter("classroomId");

        //获取前端输入Id的课程
        CourseService courseService=new CourseServiceImpl();
        Course course=courseService.getCourseById(courseId);
        if(courseName.equals("")){
            courseName=course.getCourseName();
        }
        if(courseHour.equals("")){
            courseHour=course.getCourseHour();
        }
        if(courseTime.equals("")){
            courseTime=course.getCourseTime();
        }
        if(userId.equals("")){
            userId=Integer.toString(course.getUserId());
        }
        if(teamId.equals("")){
            teamId=Integer.toString(course.getTeamId());
        }
        if(classroomId.equals("")){
            classroomId=Integer.toString(course.getClassroomId());
        }
        Course course1=new Course(Integer.valueOf(courseId),courseName,courseHour,courseTime,Integer.valueOf(userId),Integer.valueOf(teamId),Integer.valueOf(classroomId));
        return course1;
    }
    //获得页面数据，封装成Course对象
    private ClassRoom requestDateObj2(HttpServletRequest request) {
        //获取页面参数
        String courseId=request.getParameter("courseId");

        //获取前端输入Id的课程
        CourseService courseService=new CourseServiceImpl();
        Course course=courseService.getCourseById(courseId);
        ClassRoomService classRoomService=new ClassRoomServiceImpl();
        ClassRoom classRoom=classRoomService.getClassroomById(course.getClassroomId());
        return classRoom;
    }
    private ClassRoom requestDateObj3(HttpServletRequest request) {
        //获取页面参数
        String courseTime=request.getParameter("courseTime");
        String classroomId=request.getParameter("classroomId");
        String classroomName=request.getParameter("classroomName");
        String classroomType=request.getParameter("classroomType");
        String classroomCapacity=request.getParameter("classroomCapacity");
        ClassRoom classRoom=new ClassRoom(Integer.valueOf(classroomId),classroomName,classroomType,Integer.valueOf(classroomCapacity),1,courseTime);
        return classRoom;
    }
}
