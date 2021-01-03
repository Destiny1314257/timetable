package com.tang.model;

//管理员
public class User {
    private Integer userId;
    private String userAccount;
    private String userPassword;
    private String userName;
    private String userSex;
    private Integer loginState;
    private Integer teamId;

    public User() {
    }

    public User(Integer userId, String userAccount, String userPassword, String userName, String userSex, Integer loginState, Integer teamId) {
        this.userId = userId;
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userSex = userSex;
        this.loginState = loginState;
        this.teamId = teamId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Integer getLoginState() {
        return loginState;
    }

    public void setLoginState(Integer loginState) {
        this.loginState = loginState;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
