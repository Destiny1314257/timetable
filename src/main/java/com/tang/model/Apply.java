package com.tang.model;

public class Apply {
    private Integer applyId;
    private String applyOldtime;
    private Integer applyOldclassroom;
    private String applyNewtime;
    private Integer applyNewclassroom;
    private Integer applyState;
    private Integer courseId;
    private String classroomName;

    public Apply() {
    }

    public Apply(Integer applyId, String applyOldtime, Integer applyOldclassroom, String applyNewtime, Integer applyNewclassroom, Integer applyState, Integer courseId, String classroomName) {
        this.applyId = applyId;
        this.applyOldtime = applyOldtime;
        this.applyOldclassroom = applyOldclassroom;
        this.applyNewtime = applyNewtime;
        this.applyNewclassroom = applyNewclassroom;
        this.applyState = applyState;
        this.courseId = courseId;
        this.classroomName = classroomName;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public String getApplyOldtime() {
        return applyOldtime;
    }

    public void setApplyOldtime(String applyOldtime) {
        this.applyOldtime = applyOldtime;
    }

    public Integer getApplyOldclassroom() {
        return applyOldclassroom;
    }

    public void setApplyOldclassroom(Integer applyOldclassroom) {
        this.applyOldclassroom = applyOldclassroom;
    }

    public String getApplyNewtime() {
        return applyNewtime;
    }

    public void setApplyNewtime(String applyNewtime) {
        this.applyNewtime = applyNewtime;
    }

    public Integer getApplyNewclassroom() {
        return applyNewclassroom;
    }

    public void setApplyNewclassroom(Integer applyNewclassroom) {
        this.applyNewclassroom = applyNewclassroom;
    }

    public Integer getApplyState() {
        return applyState;
    }

    public void setApplyState(Integer applyState) {
        this.applyState = applyState;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }
}
