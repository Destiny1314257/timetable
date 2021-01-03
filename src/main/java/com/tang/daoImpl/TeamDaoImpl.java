package com.tang.daoImpl;

import com.tang.common.utils.DBUtils;
import com.tang.dao.TeamDao;
import com.tang.model.Course;
import com.tang.model.Team;

import java.sql.SQLException;
import java.util.List;

public class TeamDaoImpl implements TeamDao {
    @Override
    public List<Team> findAll() throws SQLException {
        String sql="select team_id teamId,team_name teamName,team_number teamNumber from team where team_id=?";
        List<Team> list = DBUtils.getList(Team.class,sql,1);
        return  list;
    }

    @Override
    public Integer getTeamCount(String sqlCount) {
        Integer count=DBUtils.getCount(sqlCount);
        return count;
    }

    @Override
    public List<Team> getTeamList(String sql) throws SQLException {
        List<Team> courseList= DBUtils.getList(Team.class,sql);
        return  courseList;
    }

    @Override
    public List<Team> getTeamListByPage(String sql, Integer teamId) throws SQLException {
        List<Team> courseList= DBUtils.getList(Team.class,sql,teamId);
        return  courseList;
    }

    @Override
    public Team getTeamById(String teamId) {
        String sql="Select team_id teamId,team_name teamName,team_number teamNumber from team where team_id=?";
        Team team=DBUtils.getSingleObj(Team.class,sql,teamId);
        return team;
    }

    @Override
    public void deleteTeam(String teamId) {
        String sql="delete from team where team_id=?";
        DBUtils.update(sql,Integer.valueOf(teamId));
    }

    @Override
    public boolean updateTeam(Team team) {
        String sql="update team set team_name=?,team_number=? where team_id=?";
        return DBUtils.update(sql,team.getTeamName(),team.getTeamNumber(),team.getTeamId());
    }

    @Override
    public boolean InsertTeam(Team team) {
        String sql="insert into team(team_id,team_name,team_number)"+
                "values(?,?,?)";
        return DBUtils.save(sql,team.getTeamId(),team.getTeamName(),team.getTeamNumber());
    }
}
