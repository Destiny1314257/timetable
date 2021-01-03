package com.tang.daoImpl;

import com.tang.common.utils.DBUtils;
import com.tang.dao.ApplyDao;
import com.tang.model.Apply;

import java.sql.SQLException;
import java.util.List;

public class ApplyDaoImpl implements ApplyDao {
    @Override
    public boolean addApply(Apply apply) {
        String sql="insert into apply(apply_id,apply_oldtime,apply_oldclassroom,apply_newtime,apply_newclassroom,apply_state,course_id,classroom_name)"+
                "values(?,?,?,?,?,?,?)";
        return DBUtils.save(sql,apply.getApplyId(),apply.getApplyOldtime(),apply.getApplyOldclassroom(),apply.getApplyNewtime(),apply.getApplyNewclassroom(),apply.getApplyState(),apply.getCourseId(),apply.getClassroomName());
    }

    @Override
    public List<Apply> getApplyList() throws SQLException {
        String sql="select apply_id applyId,apply_oldtime applyOldtime,apply_oldclassroom applyOldclassroom,apply_newtime applyNewtime,apply_newclassroom applyNewclassroom,apply_state applyState,course_id courseId,classroom_name classroomName from apply";
        List<Apply> applyList=DBUtils.getList(Apply.class,sql);
        return applyList;
    }

    @Override
    public boolean updateApply(Integer applyId,int i) {
        String sql="update apply set apply_state=? where apply_id=?";
        return DBUtils.update(sql,i,applyId);
    }

    @Override
    public Apply getApplyByTandC(String className,String applyNewtime, Integer applyNewClassroom) {
        String sql="select apply_id applyId,apply_oldtime applyOldtime,apply_oldclassroom applyOldclassroom,apply_newtime applyNewtime,apply_newclassroom applyNewclassroom,apply_state applyState,course_id courseId,classroom_name classroomName from apply";
        return null;
    }
}
