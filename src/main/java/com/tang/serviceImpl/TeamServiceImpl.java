package com.tang.serviceImpl;

import com.tang.dao.CourseDao;
import com.tang.dao.TeamDao;
import com.tang.daoImpl.CourseDaoImpl;
import com.tang.daoImpl.TeamDaoImpl;
import com.tang.model.Course;
import com.tang.model.Team;
import com.tang.service.TeamService;

import java.sql.SQLException;
import java.util.List;

public class TeamServiceImpl implements TeamService {
    @Override
    public List<Team> findAll() throws SQLException {
        TeamDao dao=new TeamDaoImpl();
        List<Team> list=dao.findAll();
        return list;
    }

    @Override
    public Integer getTeamCount(String sqlCount) {
        TeamDao dao=new TeamDaoImpl();
        Integer count=dao.getTeamCount(sqlCount);
        return count;
    }

    @Override
    public List<Team> getTeamList(String sql) throws SQLException {
        TeamDao dao=new TeamDaoImpl();
        List<Team> list=dao.getTeamList(sql);
        return list;
    }

    @Override
    public List<Team> getTeamListByPage(String sql, Integer teamId) throws SQLException {
        TeamDao dao=new TeamDaoImpl();
        List<Team> list=dao.getTeamListByPage(sql,teamId);
        return list;
    }

    @Override
    public Team getTeamById(String teamId) {
        TeamDao dao=new TeamDaoImpl();
        Team team=dao.getTeamById(teamId);
        return team;
    }

    @Override
    public void deleteTeam(String teamId) {
        TeamDao dao=new TeamDaoImpl();
        dao.deleteTeam(teamId);
    }

    @Override
    public boolean updateTeam(Team team) {
        TeamDao dao=new TeamDaoImpl();
        boolean flag=dao.updateTeam(team);
        return flag;
    }

    @Override
    public boolean InsertTeam(Team team) {
        TeamDao dao=new TeamDaoImpl();
        boolean flag=dao.InsertTeam(team);
        return flag;
    }
}
