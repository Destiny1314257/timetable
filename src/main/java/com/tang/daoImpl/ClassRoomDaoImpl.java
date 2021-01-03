package com.tang.daoImpl;

import com.tang.common.utils.DBUtils;
import com.tang.dao.ClassRoomDao;
import com.tang.model.ClassRoom;
import com.tang.model.Course;

import java.sql.SQLException;
import java.util.List;

public class ClassRoomDaoImpl implements ClassRoomDao {

    @Override
    public List<ClassRoom> findAll() throws SQLException {
        String sql="select classroom_id classroomId,classroom_name classroomName,classroom_type classroomType,classroom_capacity classroomCapacity,classroom_state classroomState,classroom_time classroomTime from classroom";
        List<ClassRoom> list = DBUtils.getList(ClassRoom.class,sql);
        return  list;
    }

    @Override
    public Integer getCount(String sqlCount) {
        Integer count=DBUtils.getCount(sqlCount);
        return count;
    }

    @Override
    public List<ClassRoom> getCourseListByPage(String sql) throws SQLException {
        List<ClassRoom> classRoomList= DBUtils.getList(ClassRoom.class,sql);
        return  classRoomList;
    }

    @Override
    public void updateClassroom(Integer classroomId) {
        String sql="update classroom set classroom_state=?,classroom_time=? where classroom_id=?";
        DBUtils.update(sql,0,null,classroomId);
    }

    @Override
    public ClassRoom getClassroomById(Integer classroomId) {
        String sql="select classroom_id classroomId,classroom_name classroomName,classroom_type classroomType,classroom_capacity classroomCapacity,classroom_state classroomState,classroom_time classroomTime from classroom where classroom_id=?";
        ClassRoom classRoom=DBUtils.getSingleObj(ClassRoom.class,sql,classroomId);
        return classRoom;

    }

    @Override
    public void updateClassroom1(String courseTime, Integer classroomId) {
        String sql="update classroom set classroom_state=?,classroom_time=? where classroom_id=?";
        DBUtils.update(sql,1,courseTime,classroomId);
    }

    @Override
    public boolean InsertClassroom(ClassRoom classRoom) {
        String sql="insert into classroom(classroom_id,classroom_name,classroom_type,classroom_capacity,classroom_state,classroom_time)"+
                "values(?,?,?,?,?,?)";
        return DBUtils.save(sql,classRoom.getClassroomId(),classRoom.getClassroomName(),classRoom.getClassroomType(),classRoom.getClassroomCapacity(),classRoom.getClassroomState(),classRoom.getClassroomTime());
    }

    @Override
    public List<ClassRoom> getClassRoomList(String sql, Integer classroomId) throws SQLException {
        List<ClassRoom> classRoomList= DBUtils.getList(ClassRoom.class,sql,classroomId);
        return  classRoomList;
    }

    @Override
    public void deleteclassRoom(String classroomId) {
        String sql="delete from team where classroom_id=?";
        DBUtils.update(sql,Integer.valueOf(classroomId));
    }

    @Override
    public boolean updateClassroom2(ClassRoom classRoom) {
        String sql="update classroom set classroom_name=?,classroom_type=?,classroom_capacity=?,classroom_state=?,classroom_time=? where classroom_id=?";
        return DBUtils.update(sql,classRoom.getClassroomName(),classRoom.getClassroomType(),classRoom.getClassroomCapacity(),classRoom.getClassroomState(),classRoom.getClassroomTime(),classRoom.getClassroomId());
    }

    @Override
    public ClassRoom getClassRoomByTandC(String className,String applyNewtime, Integer applyNewClassroom) {
        String sql="select classroom_id classroomId,classroom_name classroomName,classroom_type classroomType,classroom_capacity classroomCapacity,classroom_state classroomState,classroom_time classroomTime from classroom where classroom_name=? and classroom_id=? and classroom_time=?";
        ClassRoom classRoom=DBUtils.getSingleObj(ClassRoom.class,sql,className,applyNewClassroom,applyNewtime);
        return classRoom;
    }
}
