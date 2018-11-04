package com.xiaohei.auser.wenliapp.Dao;

import com.xiaohei.auser.wenliapp.utils.XhLogUtil;
import com.xiaohei.auser.wenliapp.wenlientity.NewStudent;
import com.xiaohei.auser.wenliapp.wenlientity.dbentity.DbStudent;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by machenike on 2018/10/17.
 * 用于数据装换
 */

public class StudentDao {

    public static void saveStudent(NewStudent student){
        DbStudent dbStudent = new DbStudent(1,student.getId(),student.getStatus(),student.getCardId(),student.getName(),
                student.getPassword(),student.getDepartmentId(),student.getDepartmentName(),student.getClassId(),student.getClassName(),
                student.getRoomId(),student.getRoomName(),student.getBuildName());
        if(getStudent() == null)
            dbStudent.save();
        else
        dbStudent.updateAll("id = ?","1");
        XhLogUtil.d("更新成功");
    }

    public static void saveStudent(DbStudent student){
        student.save();
    }

    public static DbStudent getStudent(){
        List<DbStudent> students = DataSupport.where("id = ?","1").find(DbStudent.class);
        if (students.size() == 0)
            return null;
        else
        return students.get(0);
    }

}
