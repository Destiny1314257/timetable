package com.tang.dao;

import com.tang.model.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDao {
    public List<Course> findAll() throws SQLException;

    public Integer getCount(String sqlCount, Object[] param);

    public List<Course> getCourseListByPage(String sql,Integer teamId) throws SQLException;

    public List<Course> getCourseList(String sql) throws SQLException;

    public Course getCourseById(String courseId);

    public void deleteCourse(String courseId);

    public boolean updateCourse(Course course);

    public boolean InsertCourse(Course course);

    public Course getCourseByTandC(String applyNewtime, String applyNewClassroom);
}
