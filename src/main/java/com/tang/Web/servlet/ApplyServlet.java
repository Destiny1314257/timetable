package com.tang.Web.servlet;

import com.tang.model.Apply;
import com.tang.model.Course;
import com.tang.service.ApplyService;
import com.tang.service.CourseService;
import com.tang.serviceImpl.ApplyServiceImpl;
import com.tang.serviceImpl.CourseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/ApplyServlet")
public class ApplyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String courseId=request.getParameter("courseId");
        String newTime=request.getParameter("newTime");
        String newClassroom=request.getParameter("newClassroom");
        String newClassroomName=request.getParameter("newClassroomName");
        CourseService courseService=new CourseServiceImpl();
        Course course=courseService.getCourseById(courseId);
        Apply apply=new Apply(null,course.getCourseTime(),course.getClassroomId(),newTime,Integer.valueOf(newClassroom),0,Integer.valueOf(courseId),newClassroomName);
        ApplyService applyService=new ApplyServiceImpl();
        boolean flag=applyService.addApply(apply);
        if(flag){
            request.getRequestDispatcher("/teacher/teacherlist.jsp").forward(request,response);
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
}
