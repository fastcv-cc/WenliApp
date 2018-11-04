package com.xiaohei.auser.wenliapp.Dao;

import com.xiaohei.auser.wenliapp.utils.XhLogUtil;
import com.xiaohei.auser.wenliapp.wenlientity.NewTeacher;
import com.xiaohei.auser.wenliapp.wenlientity.dbentity.DbTeacher;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by machenike on 2018/10/18.
 * 操作教师数据库
 */

public class TeacherDao {
    public static void saveTeacher(NewTeacher teacher){
        DbTeacher dbTeacher = new DbTeacher(1,teacher.getId(),teacher.getUsername(),teacher.getPassword(),teacher.getNickName(),
                teacher.getStatus(),teacher.getDepartmentId(),teacher.getDepartmentName());
        if(getTeacher() == null)
            dbTeacher.save();
        else
            dbTeacher.updateAll("id = ?","1");
        XhLogUtil.d("更新成功");

    }

    public static DbTeacher getTeacher(){
        List<DbTeacher> teachers = DataSupport.where("id = ?","1").find(DbTeacher.class);
        if (teachers.size() == 0)
            return null;
        else
            return teachers.get(0);
    }
}
