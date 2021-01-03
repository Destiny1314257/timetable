package com.tang.serviceImpl;

import com.tang.dao.ClassRoomDao;
import com.tang.dao.CourseDao;
import com.tang.dao.TeamDao;
import com.tang.daoImpl.ClassRoomDaoImpl;
import com.tang.daoImpl.CourseDaoImpl;
import com.tang.daoImpl.TeamDaoImpl;
import com.tang.model.ClassRoom;
import com.tang.model.Course;
import com.tang.service.ClassRoomService;

import java.sql.SQLException;
import java.util.List;

public class ClassRoomServiceImpl implements ClassRoomService {
    @Override
    public List<ClassRoom> findAll() throws SQLException {
        ClassRoomDao dao=new ClassRoomDaoImpl();
        List<ClassRoom> list=dao.findAll();
        return list;
    }

    @Override
    public Integer getClassroomCount(String sqlCount) {
        ClassRoomDao dao=new ClassRoomDaoImpl();
        Integer count=dao.getCount(sqlCount);
        return count;
    }

    @Override
    public List<ClassRoom> getClassRoomListByPage(String sql) throws SQLException {
        ClassRoomDao dao=new ClassRoomDaoImpl();
        List<ClassRoom> list=dao.getCourseListByPage(sql);
        return list;
    }

    @Override
    public void updateClassroom(Integer classroomId) {
        ClassRoomDao dao=new ClassRoomDaoImpl();
        dao.updateClassroom(classroomId);
    }

    @Override
    public ClassRoom getClassroomById(Integer classroomId) {
        ClassRoomDao dao=new ClassRoomDaoImpl();
        ClassRoom classroom=dao.getClassroomById(classroomId);
        return classroom;
    }

    @Override
    public void updateClassroom1(String courseTime, Integer classroomId) {
        ClassRoomDao dao=new ClassRoomDaoImpl();
        dao.updateClassroom1(courseTime,classroomId);
    }

    @Override
    public boolean InsertClassroom(ClassRoom classRoom) {
        ClassRoomDao dao=new ClassRoomDaoImpl();
        boolean flag=dao.InsertClassroom(classRoom);
        return flag;
    }

    @Override
    public List<ClassRoom> getClassRoomList(String sql, Integer classroomId) throws SQLException {
        ClassRoomDao dao=new ClassRoomDaoImpl();
        List<ClassRoom> list=dao.getClassRoomList(sql,classroomId);
        return list;
    }

    @Override
    public void deleteclassRoom(String classroomId) {
        ClassRoomDao dao=new ClassRoomDaoImpl();
        dao.deleteclassRoom(classroomId);
    }

    @Override
    public boolean updateClassroom2(ClassRoom classRoom) {
        ClassRoomDao dao=new ClassRoomDaoImpl();
        return dao.updateClassroom2(classRoom);
    }

    @Override
    public ClassRoom getClassroomByTandC(String className,String applyNewtime, Integer applyNewClassroom) {
        ClassRoomDao dao=new ClassRoomDaoImpl();
        ClassRoom classRoom=dao.getClassRoomByTandC(className,applyNewtime,applyNewClassroom);
        return classRoom;
    }
}
