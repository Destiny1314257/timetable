package com.tang.serviceImpl;

import com.tang.dao.TeamDao;
import com.tang.dao.UserDao;
import com.tang.daoImpl.TeamDaoImpl;
import com.tang.daoImpl.UserDaoImpl;
import com.tang.model.Team;
import com.tang.model.User;
import com.tang.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public List<User> findAll() throws SQLException {
        UserDao dao=new UserDaoImpl();
        List<User> list=dao.findAll();
        return list;
    }

    @Override
    public Integer selectUserAccountCount(String account) {
        UserDao dao=new UserDaoImpl();
        Integer count=dao.selectUserAccountCount(account);
        return  count;
    }

    @Override
    public boolean saveUser(User user) {
        UserDao dao=new UserDaoImpl();
        boolean flag=dao.saveUser(user);
        return flag;
    }

    @Override
    public User getUserAccountAndPassword(String account, String password) {
        UserDao dao=new UserDaoImpl();
        User user=dao.getUserAccountAndPassword(account,password);
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        UserDao dao=new UserDaoImpl();
        boolean flag=dao.updateUser(user);
        return flag;
    }

    @Override
    public Integer getUserCount(String sqlCount, Integer state) {
        UserDao dao=new UserDaoImpl();
        Integer count=dao.getUserCount(sqlCount,state);
        return count;
    }

    @Override
    public List<User> getUserByState(String sql, Integer state) throws SQLException {
        UserDao dao=new UserDaoImpl();
        List<User> list=dao.getUserByState(sql,state);
        return list;
    }

    @Override
    public User getUser(String userId) {
        UserDao dao=new UserDaoImpl();
        User user=dao.getUser(userId);
        return user;
    }

    @Override
    public List<User> getUserList(String sql, Integer userId) throws SQLException {
        UserDao dao=new UserDaoImpl();
        List<User> list=dao.getUserList(sql,userId);
        return list;
    }

    @Override
    public void deleteUser(String userId) {
        UserDao dao=new UserDaoImpl();
        dao.deleteUser(userId);
    }


}
