package com.tang.daoImpl;

import com.tang.common.utils.DBUtils;
import com.tang.dao.CourseDao;
import com.tang.model.Course;

import java.sql.SQLException;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    @Override
    public List<Course> findAll() throws SQLException {
        String sql="select course_id courseId,course_name courseName,course_hour courseHour,course_time courseTime,team_id teamId,classroom_id classroomId,teacher_id teacherId from course where course_id=?";
        List<Course> list = DBUtils.getList(Course.class,sql,1);
        return  list;
    }

    @Override
    public Integer getCount(String sqlCount, Object[] param) {
        Integer count=DBUtils.getCount(sqlCount,param);
        return count;
    }

    @Override
    public List<Course> getCourseListByPage(String sql,Integer timeId) throws SQLException {
        List<Course> courseList= DBUtils.getList(Course.class,sql,timeId);
        return  courseList;
    }

    @Override
    public List<Course> getCourseList(String sql) throws SQLException {
        List<Course> courseList= DBUtils.getList(Course.class,sql);
        return  courseList;
    }

    @Override
    public Course getCourseById(String courseId) {
        String sql="select course_id courseId,course_name courseName,course_hour courseHour,course_time courseTime,user_id userId,team_id teamId,classroom_id classroomId from course where course_id=?";
        Course course=DBUtils.getSingleObj(Course.class,sql,courseId);
        return course;
    }

    @Override
    public void deleteCourse(String courseId) {
        String sql="delete from course where course_id=?";
        DBUtils.update(sql,courseId);
    }

    @Override
    public boolean updateCourse(Course course) {
        String sql="update course set course_name=?,course_hour=?,course_time=?,user_id=?,team_id=?,classroom_id=? where course_id=?";
        return DBUtils.update(sql,course.getCourseName(),course.getCourseHour(),course.getCourseTime(),course.getUserId(),course.getTeamId(),course.getClassroomId(),course.getCourseId());
    }

    @Override
    public boolean InsertCourse(Course course) {
        String sql="insert into course(course_id,course_name,course_hour,course_time,user_id,team_id,classroom_id)"+
                "values(?,?,?,?,?,?,?)";
        return DBUtils.save(sql,course.getCourseId(),course.getCourseName(),course.getCourseHour(),course.getCourseTime(),course.getUserId(),course.getTeamId(),course.getClassroomId());
    }

    @Override
    public Course getCourseByTandC(String applyNewtime, String applyNewClassroom) {
        String sql="select course_id courseId,course_name courseName,course_hour courseHour,course_time courseTime,user_id userId,team_id teamId,classroom_id classroomId from course where course_time=? and classroom_id=?";
        Course course=DBUtils.getSingleObj(Course.class,sql,applyNewtime,Integer.valueOf(applyNewClassroom));
        return course;
    }
}
