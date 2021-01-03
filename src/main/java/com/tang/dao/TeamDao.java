package com.tang.dao;

import com.tang.model.Team;

import java.sql.SQLException;
import java.util.List;

public interface TeamDao {
    public List<Team> findAll() throws SQLException;

    public Integer getTeamCount(String sqlCount);

    public List<Team> getTeamList(String sql) throws SQLException;

    public List<Team> getTeamListByPage(String sql, Integer teamId) throws SQLException;

    public Team getTeamById(String teamId);

    public void deleteTeam(String teamId);

    public boolean updateTeam(Team team);

    public boolean InsertTeam(Team team);
}
