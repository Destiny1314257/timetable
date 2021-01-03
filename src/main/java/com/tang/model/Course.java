package com.tang.model;

//课程
public class Course {
    private Integer courseId;
    private String courseName;
    private String courseHour;
    private String courseTime;
    private Integer userId;
    private Integer teamId;
    private Integer classroomId;

    public Course() {
    }

    public Course(Integer courseId, String courseName, String courseHour, String courseTime, Integer userId, Integer teamId, Integer classroomId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseHour = courseHour;
        this.courseTime = courseTime;
        this.userId = userId;
        this.teamId = teamId;
        this.classroomId = classroomId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseHour() {
        return courseHour;
    }

    public void setCourseHour(String courseHour) {
        this.courseHour = courseHour;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }
}
