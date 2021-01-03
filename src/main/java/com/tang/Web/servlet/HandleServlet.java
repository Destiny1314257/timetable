package com.tang.Web.servlet;

import com.tang.model.Apply;
import com.tang.model.ClassRoom;
import com.tang.model.Course;
import com.tang.model.PageBean;
import com.tang.service.ApplyService;
import com.tang.service.ClassRoomService;
import com.tang.service.CourseService;
import com.tang.serviceImpl.ApplyServiceImpl;
import com.tang.serviceImpl.ClassRoomServiceImpl;
import com.tang.serviceImpl.CourseServiceImpl;

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

@WebServlet(urlPatterns = "/HandleServlet")
public class HandleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplyService applyService=new ApplyServiceImpl();
        CourseService courseService=new CourseServiceImpl();
        ClassRoomService classRoomService=new ClassRoomServiceImpl();
        List<Apply> applyList=null;
        ServletContext servletContext = request.getServletContext();
        PageBean<ClassRoom> pageBeanClassroom=(PageBean<ClassRoom>)servletContext.getAttribute("pageBeanClassroom");
        try {
            applyList=applyService.getApplyList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for(int i=0;i<applyList.size();i++){
            Apply apply=applyList.get(i);
            if(apply.getApplyState().equals(0)){

                ClassRoom classRoom=classRoomService.getClassroomByTandC(apply.getClassroomName(),apply.getApplyNewtime(),apply.getApplyNewclassroom());
                if(classRoom!=null){
                    applyService.updateApply(apply.getApplyId(),2);
                }else{
                    applyService.updateApply(apply.getApplyId(),1);
                    Course course=courseService.getCourseById(Integer.toString(apply.getCourseId()));
                    classRoomService.updateClassroom(apply.getApplyOldclassroom());
                    course.setCourseTime(apply.getApplyNewtime());
                    course.setClassroomId(apply.getApplyNewclassroom());
                    courseService.updateCourse(course);
                    ClassRoom classRoom1=classRoomService.getClassroomById(course.getClassroomId());
                    classRoom1.setClassroomTime(apply.getApplyNewtime());
                    classRoom1.setClassroomName(apply.getClassroomName());
                    classRoom1.setClassroomState(1);
                    classRoomService.InsertClassroom(classRoom1);
                    pageBeanClassroom.getList().add(classRoom1);
                }
            }
        }
        servletContext.setAttribute("pageBeanClassroom",pageBeanClassroom);
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        writer.write("<script>");
        writer.write("alert('success！');");
        writer.write("</script>");
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
