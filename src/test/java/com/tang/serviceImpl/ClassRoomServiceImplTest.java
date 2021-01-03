package com.tang.serviceImpl;

import com.tang.model.ClassRoom;
import com.tang.service.ClassRoomService;

import java.sql.SQLException;
import java.util.List;

public class ClassRoomServiceImplTest {
    @org.junit.Test
    public  void test() throws SQLException {
        ClassRoomService classRoomService=new ClassRoomServiceImpl();
        List<ClassRoom> list=classRoomService.findAll();
        for(ClassRoom classRoom:list){
            System.out.println(classRoom.getClassroomName());
        }
    }
}
