package com.tang.model;

//教室
public class ClassRoom {
    private Integer classroomId;
    private String classroomName;
    private String classroomType;
    private Integer classroomCapacity;
    private Integer classroomState;
    private String classroomTime;

    public ClassRoom() {
    }

    public ClassRoom(Integer classroomId, String classroomName, String classroomType, Integer classroomCapacity, Integer classroomState, String classroomTime) {
        this.classroomId = classroomId;
        this.classroomName = classroomName;
        this.classroomType = classroomType;
        this.classroomCapacity = classroomCapacity;
        this.classroomState = classroomState;
        this.classroomTime = classroomTime;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public String getClassroomType() {
        return classroomType;
    }

    public void setClassroomType(String classroomType) {
        this.classroomType = classroomType;
    }

    public Integer getClassroomCapacity() {
        return classroomCapacity;
    }

    public void setClassroomCapacity(Integer classroomCapacity) {
        this.classroomCapacity = classroomCapacity;
    }

    public Integer getClassroomState() {
        return classroomState;
    }

    public void setClassroomState(Integer classroomState) {
        this.classroomState = classroomState;
    }

    public String getClassroomTime() {
        return classroomTime;
    }

    public void setClassroomTime(String classroomTime) {
        this.classroomTime = classroomTime;
    }
}
