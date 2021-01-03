package com.tang.daoImpl;

import com.tang.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImplTest {
    @org.junit.Test
    public  void test() throws SQLException {
        UserDaoImpl adminDao=new UserDaoImpl();
        List<User> list=adminDao.findAll();
        for(User admin:list){
            System.out.println(admin.getUserName());
        }

    }
}
