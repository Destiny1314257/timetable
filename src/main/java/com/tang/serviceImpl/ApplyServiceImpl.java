package com.tang.serviceImpl;

import com.tang.dao.ApplyDao;
import com.tang.daoImpl.ApplyDaoImpl;
import com.tang.model.Apply;
import com.tang.service.ApplyService;

import java.sql.SQLException;
import java.util.List;

public class ApplyServiceImpl implements ApplyService {
    @Override
    public boolean addApply(Apply apply) {
        ApplyDao dao=new ApplyDaoImpl();
        boolean flag=dao.addApply(apply);
        return flag;
    }

    @Override
    public List<Apply> getApplyList() throws SQLException {
        ApplyDao dao=new ApplyDaoImpl();
        List<Apply> applyList=dao.getApplyList();
        return applyList;
    }

    @Override
    public boolean updateApply(Integer applyId,int i) {
        ApplyDao dao=new ApplyDaoImpl();
        return dao.updateApply(applyId,i);
    }

    @Override
    public Apply getApplyByTandC(String className,String applyNewtime, Integer applyNewClassroom) {
        ApplyDao dao=new ApplyDaoImpl();
        Apply apply=dao.getApplyByTandC(className,applyNewtime,applyNewClassroom);
        return apply;
    }
}
