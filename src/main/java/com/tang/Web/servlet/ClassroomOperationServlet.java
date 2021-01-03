package com.tang.Web.servlet;

import com.tang.model.ClassRoom;
import com.tang.model.PageBean;
import com.tang.model.Team;
import com.tang.service.ClassRoomService;
import com.tang.service.TeamService;
import com.tang.serviceImpl.ClassRoomServiceImpl;
import com.tang.serviceImpl.TeamServiceImpl;

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

@WebServlet(urlPatterns = "/ClassroomOperationServlet")
public class ClassroomOperationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置post提交时候，参数解码方式
        request.setCharacterEncoding("UTF-8");
        String method=request.getParameter("method");
        ServletContext servletContext = request.getServletContext();
        PageBean<ClassRoom> pageBeanClassroom=(PageBean<ClassRoom>)servletContext.getAttribute("pageBeanClassroom");
        //查询
        if("search".equals(method)){
            String classroomId=request.getParameter("classroomId");
            ClassRoomService classRoomService=new ClassRoomServiceImpl();
            //把所有课程信息查询出来
            List<ClassRoom> classRoomList= null;
            String sql="select classroom_id classroomId,classroom_name classroomName,classroom_type classroomType,classroom_capacity classroomCapacity,classroom_state classroomState,classroom_time classroomTime from classroom where classroom_id=?";
            try {
                classRoomList = classRoomService.getClassRoomList(sql,Integer.valueOf(classroomId));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            servletContext.setAttribute("classRoomList",classRoomList);
            request.getRequestDispatcher("manage/classroomSearch.jsp").forward(request,response);
        }else if("delete".equals(method)){//删除
            String classroomId=request.getParameter("classroomId");
            ClassRoomService classRoomService=new ClassRoomServiceImpl();
            classRoomService.deleteclassRoom(classroomId);
            for(int i=0;i<pageBeanClassroom.getList().size();i++){
                ClassRoom classRoom=pageBeanClassroom.getList().get(i);
                if(classRoom.getClassroomId().equals(Integer.valueOf(classroomId))){
                    pageBeanClassroom.getList().remove(i);
                }
            }
            servletContext.setAttribute("pageBeanClassroom",pageBeanClassroom);
            request.getRequestDispatcher("manage/classroommanage.jsp").forward(request,response);
        }else if("update".equals(method)){//更改
            ClassRoom classRoom1=requestDateObj(request);
            ClassRoomService classRoomService=new ClassRoomServiceImpl();
            boolean flag=classRoomService.updateClassroom2(classRoom1);
            for(int i=0;i<pageBeanClassroom.getList().size();i++){
                ClassRoom classRoom2=pageBeanClassroom.getList().get(i);
                if(classRoom2.getClassroomId().equals(classRoom1.getClassroomId())){
                    pageBeanClassroom.getList().set(i,classRoom1);
                }
            }
            if(flag){
                servletContext.setAttribute("pageBeanClassroom",pageBeanClassroom);
                request.getRequestDispatcher("manage/classroommanage.jsp").forward(request,response);
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
            String classroomId=request.getParameter("classroomId");
            ClassRoom classRoom=requestDateObj(request);
            ClassRoomService classRoomService=new ClassRoomServiceImpl();
            ClassRoom classRoom1=classRoomService.getClassroomById(Integer.valueOf(classroomId));
            if(classRoom1!=null){
                PrintWriter writer = response.getWriter();
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                writer.write("<script>");
                writer.write("alert('failure！');");
                writer.write("</script>");
                writer.flush();
                writer.close();
            }else {
                classRoomService.InsertClassroom(classRoom);
                pageBeanClassroom.getList().add(classRoom);
                //跳转到首页
                servletContext.setAttribute("pageBeanClassroom",pageBeanClassroom);
                request.getRequestDispatcher("manage/classroommanage.jsp").forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    //获得页面数据，封装成Course对象
    private ClassRoom requestDateObj(HttpServletRequest request) {
        //获取页面参数
        String classroomId=request.getParameter("classroomId");
        String classroomName=request.getParameter("classroomName");
        String classroomType=request.getParameter("classroomType");
        String classroomCapacity=request.getParameter("classroomCapacity");
        String classroomState=request.getParameter("classroomState");
        String classroomTime=request.getParameter("classroomTime");
        //获取前端输入Id的课程
        ClassRoom classRoom=new ClassRoom(Integer.valueOf(classroomId),classroomName,classroomType,Integer.valueOf(classroomCapacity),Integer.valueOf(classroomState),classroomTime);
        return classRoom;
    }
}
