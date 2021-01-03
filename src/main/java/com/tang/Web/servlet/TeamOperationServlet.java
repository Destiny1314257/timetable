package com.tang.Web.servlet;

import com.tang.model.ClassRoom;
import com.tang.model.Course;
import com.tang.model.PageBean;
import com.tang.model.Team;
import com.tang.service.ClassRoomService;
import com.tang.service.CourseService;
import com.tang.service.TeamService;
import com.tang.serviceImpl.ClassRoomServiceImpl;
import com.tang.serviceImpl.CourseServiceImpl;
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

@WebServlet(urlPatterns = "/TeamOperationServlet")
public class TeamOperationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置post提交时候，参数解码方式
        request.setCharacterEncoding("UTF-8");
        String method=request.getParameter("method");
        ServletContext servletContext = request.getServletContext();
        PageBean<Team> pageBeanTeam=(PageBean<Team>)servletContext.getAttribute("pageBeanTeam");
        //查询
        if("search".equals(method)){
            String teamId=request.getParameter("teamId");
            TeamService teamService=new TeamServiceImpl();
            //把所有课程信息查询出来
            List<Team> teamList= null;
            String sql="Select team_id teamId,team_name teamName,team_number teamNumber from team where team_id=?";
            try {
                teamList = teamService.getTeamListByPage(sql,Integer.valueOf(teamId));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            servletContext.setAttribute("teamList",teamList);
            request.getRequestDispatcher("manage/teamSearch.jsp").forward(request,response);
        }else if("delete".equals(method)){//删除
            String teamId=request.getParameter("teamId");
            TeamService teamService=new TeamServiceImpl();
            teamService.deleteTeam(teamId);
            for(int i=0;i<pageBeanTeam.getList().size();i++){
                Team team=pageBeanTeam.getList().get(i);
                if(team.getTeamId().equals(Integer.valueOf(teamId))){
                    pageBeanTeam.getList().remove(i);
                }
            }
            servletContext.setAttribute("pageBeanTeam",pageBeanTeam);
            request.getRequestDispatcher("manage/teammanage.jsp").forward(request,response);
        }else if("update".equals(method)){//更改
            Team team1=requestDateObj(request);
            TeamService teamService=new TeamServiceImpl();
            boolean flag=teamService.updateTeam(team1);
            for(int i=0;i<pageBeanTeam.getList().size();i++){
                Team team2=pageBeanTeam.getList().get(i);
                if(team2.getTeamId().equals(team1.getTeamId())){
                    pageBeanTeam.getList().set(i,team1);
                }
            }
            if(flag){
                servletContext.setAttribute("pageBeanTeam",pageBeanTeam);
                request.getRequestDispatcher("manage/teammanage.jsp").forward(request,response);
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
            String teamId=request.getParameter("teamId");
            TeamService teamService=new TeamServiceImpl();
            Team team=teamService.getTeamById(teamId);
            Team team1=requestDateObj(request);
            if(team!=null){
                PrintWriter writer = response.getWriter();
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                writer.write("<script>");
                writer.write("alert('failure！');");
                writer.write("</script>");
                writer.flush();
                writer.close();
            }else {
                teamService.InsertTeam(team1);
                pageBeanTeam.getList().add(team1);
                //跳转到首页
                servletContext.setAttribute("pageBeanTeam",pageBeanTeam);
                request.getRequestDispatcher("manage/teammanage.jsp").forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    //获得页面数据，封装成Course对象
    private Team requestDateObj(HttpServletRequest request) {
        //获取页面参数
        String teamId=request.getParameter("teamId");
        String teamName=request.getParameter("teamName");
        String teamNumber=request.getParameter("teamNumber");
        //获取前端输入Id的课程
        Team team=new Team(Integer.valueOf(teamId),teamName,Integer.valueOf(teamNumber));
        return team;
    }
}
