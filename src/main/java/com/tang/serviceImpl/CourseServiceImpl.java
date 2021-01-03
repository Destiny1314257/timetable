package com.tang.serviceImpl;

import com.tang.dao.CourseDao;
import com.tang.daoImpl.CourseDaoImpl;
import com.tang.model.Course;
import com.tang.service.CourseService;

import java.sql.SQLException;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    @Override
    public List<Course> findAll() throws SQLException {
        CourseDao dao=new CourseDaoImpl();
        List<Course> list=dao.findAll();
        return list;
    }

    @Override
    public Integer getCourseCount(String sqlCount,Object...param) {
        CourseDao dao=new CourseDaoImpl();
        Integer count=dao.getCount(sqlCount,param);
        return count;
    }

    @Override
    public List<Course> getCourseListByPage(String sql,Integer teamId) throws SQLException {
        CourseDao dao=new CourseDaoImpl();
        List<Course> list=dao.getCourseListByPage(sql,teamId);
        return list;
    }

    @Override
    public List<Course> getCourseList(String sql) throws SQLException {
        CourseDao dao=new CourseDaoImpl();
        List<Course> list=dao.getCourseList(sql);
        return list;
    }

    @Override
    public Course getCourseById(String courseId) {
        CourseDao dao=new CourseDaoImpl();
        Course course=dao.getCourseById(courseId);
        return course;
    }

    @Override
    public void deleteCourse(String courseId) {
        CourseDao dao=new CourseDaoImpl();
        dao.deleteCourse(courseId);
    }

    @Override
    public boolean updateCourse(Course course) {
        CourseDao dao=new CourseDaoImpl();
        boolean flag=dao.updateCourse(course);
        return flag;
    }

    @Override
    public boolean InsertCourse(Course course) {
        CourseDao dao=new CourseDaoImpl();
        boolean flag=dao.InsertCourse(course);
        return flag;
    }

    @Override
    public Course getCourseByTandC(String applyNewtime, String applyNewClassroom) {
        CourseDao dao=new CourseDaoImpl();
        Course course=dao.getCourseByTandC(applyNewtime,applyNewClassroom);
        return course;
    }
}
