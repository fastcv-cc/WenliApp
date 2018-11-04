package com.xiaohei.auser.wenliapp.Dao;

import com.xiaohei.auser.wenliapp.utils.XhLogUtil;
import com.xiaohei.auser.wenliapp.wenlientity.dbentity.DbWeeksText;
import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by machenike on 2018/10/18.
 */

public class WeekTextDao {

    public static DbWeeksText getWeekText(String roomId){
        List<DbWeeksText> dbWeeksTexts = DataSupport.where("roomId = ?",roomId).find(DbWeeksText.class);
        if (dbWeeksTexts.size() == 0)
            return null;
        else
            return dbWeeksTexts.get(0);
    }

    public static void saveWeekText(DbWeeksText weeksText,String roomId){
        if(getWeekText(roomId) == null)
            weeksText.save();
        else
            weeksText.updateAll("roomId = ?",roomId);
        XhLogUtil.d("更新成功");

    }
}
