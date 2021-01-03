package com.tang.daoImpl;

import com.tang.dao.UserDao;
import com.tang.model.Team;
import com.tang.model.User;
import com.tang.common.utils.DBUtils;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findAll() throws SQLException {
        String sql="select user_id userId,user_account userAccount,user_password userPassword,user_name userName,user_sex userSex,login_state loginState,team_id timeId from user where user_id=?";
        List<User> list =DBUtils.getList(User.class,sql,1);
        return  list;
    }

    @Override
    public Integer selectUserAccountCount(String account) {
        String sql="select count(*) from user where user_account=?";
        Integer count=DBUtils.getCount(sql,account);
        return count;
    }

    @Override
    public Boolean saveUser(User user) {
        String sql="insert into user(user_id,user_account,user_password,user_name,user_sex,login_state,team_id)"+
                "values(?,?,?,?,?,?,?)";
        return DBUtils.save(sql,user.getUserId(),user.getUserAccount(),user.getUserPassword(),user.getUserName(),user.getUserSex(),user.getLoginState(),user.getTeamId());
    }

    @Override
    public User getUserAccountAndPassword(String account, String password) {
        String sql="select user_id userId,user_account userAccount,user_password userPassword,user_name userName,user_sex userSex,login_state loginState,team_id teamId from user where user_account=? and user_password=?";
        User user=DBUtils.getSingleObj(User.class,sql,account,password);
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        String sql="update user set user_password=?,user_name=?,team_id=? where user_id=?";
        boolean flag=DBUtils.update(sql,user.getUserPassword(),user.getUserName(),user.getTeamId(),user.getUserId());
        return flag;
    }

    @Override
    public Integer getUserCount(String sqlCount, Integer state) {
        Integer count=DBUtils.getCount(sqlCount,state);
        return count;
    }

    @Override
    public List<User> getUserByState(String sql, Integer state) throws SQLException {
        List<User> userList= DBUtils.getList(User.class,sql,state);
        return  userList;
    }

    @Override
    public User getUser(String userId) {
        String sql="select user_id userId,user_account userAccount,user_password userPassword,user_name userName,user_sex userSex,login_state loginState,team_id teamId from user where user_id=?";
        User user=DBUtils.getSingleObj(User.class,sql,userId);
        return user;
    }

    @Override
    public List<User> getUserList(String sql, Integer userId) throws SQLException {
        List<User> userList= DBUtils.getList(User.class,sql,userId);
        return  userList;
    }

    @Override
    public void deleteUser(String userId) {
        String sql="delete from user where user_id=?";
        DBUtils.update(sql,Integer.valueOf(userId));
    }


}
