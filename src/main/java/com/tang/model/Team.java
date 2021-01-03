package com.tang.model;

//班级
public class Team {
    private Integer teamId;
    private String teamName;
    private Integer teamNumber;

    public Team() {
    }

    public Team(Integer teamId, String teamName, Integer teamNumber) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamNumber = teamNumber;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(Integer teamNumber) {
        this.teamNumber = teamNumber;
    }
}
