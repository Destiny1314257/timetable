package com.tang.service;

import com.tang.model.Apply;

import java.sql.SQLException;
import java.util.List;

public interface ApplyService {
    public boolean addApply(Apply apply);

    public List<Apply> getApplyList() throws SQLException;

    public boolean updateApply(Integer applyId,int i);

    public Apply getApplyByTandC(String className,String applyNewtime, Integer applyNewClassroom);
}
