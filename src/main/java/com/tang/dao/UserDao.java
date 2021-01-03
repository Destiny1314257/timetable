package com.tang.dao;

import com.tang.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public List<User> findAll() throws SQLException;

    public Integer selectUserAccountCount(String account);

    public Boolean saveUser(User user);

    public User getUserAccountAndPassword(String account, String password);

    public boolean updateUser(User user);

    public Integer getUserCount(String sqlCount, Integer state);

    public List<User> getUserByState(String sql, Integer state) throws SQLException;

    public User getUser(String userId);

    public List<User> getUserList(String sql, Integer userId) throws SQLException;

    public void deleteUser(String userId);
}
