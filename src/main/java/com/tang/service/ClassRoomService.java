package com.tang.service;

import com.tang.model.ClassRoom;

import java.sql.SQLException;
import java.util.List;

public interface ClassRoomService {
    public List<ClassRoom> findAll() throws SQLException;

    public Integer getClassroomCount(String sqlCount);

    public List<ClassRoom> getClassRoomListByPage(String sql) throws SQLException;

    public void updateClassroom(Integer classroomId);

    public ClassRoom getClassroomById(Integer classroomId);

    public void updateClassroom1(String courseTime, Integer classroomId);

    public boolean InsertClassroom(ClassRoom classRoom);

    public List<ClassRoom> getClassRoomList(String sql, Integer classroomId) throws SQLException;

    public void deleteclassRoom(String classroomId);

    public boolean updateClassroom2(ClassRoom classRoom);

    public ClassRoom getClassroomByTandC(String className,String applyNewtime, Integer applyNewClassroom);
}
