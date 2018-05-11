package com.xiaohei.auser.wenliapp.dao;


import com.xiaohei.auser.wenliapp.entity.WeeksText;
import com.xiaohei.auser.wenliapp.entity.vo.StudentsVo;
import com.xiaohei.auser.wenliapp.entity.vo.TeachersVo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class OperateDB {

	public StudentsVo getStudent(SQLiteDatabase db, int studentid){
		Cursor c=db.rawQuery("select * from students where student_id = '"+studentid+"'", null);
		StudentsVo complete = null;
		if(c!=null){
			while(c.moveToNext()){
				complete =new StudentsVo();
				complete.setStudentId(c.getInt(c.getColumnIndex("student_id")));
				complete.setStudentCardId(c.getString(c.getColumnIndex("student_card_id")));
				complete.setStudentName(c.getString(c.getColumnIndex("student_name")));
				complete.setStudentPassword(c.getString(c.getColumnIndex("student_password")));
				complete.setDepartmentId(c.getInt(c.getColumnIndex("department_id")));
				complete.setDepartmentName(c.getString(c.getColumnIndex("department_name")));
				complete.setClassId(c.getInt(c.getColumnIndex("class_id")));
				complete.setClassName(c.getString(c.getColumnIndex("class_name")));
				complete.setBuildName(c.getString(c.getColumnIndex("builds_name")));
				complete.setRoomId(c.getInt(c.getColumnIndex("room_id")));
				complete.setRoomName(c.getString(c.getColumnIndex("room_name")));
		}
		}
		c.close();
		db.close();
		return complete;
	}

	public String getStudentName(SQLiteDatabase db,int studentid){
		Cursor c=db.rawQuery("select * from students where student_id = '"+studentid+"'", null);
		String name = null;
		if(c!=null){
			while(c.moveToNext()){
				name = c.getString(c.getColumnIndex("student_name"));
			}
		}
		c.close();
		db.close();
		return name;
	}

	public int getStudentRoomId(SQLiteDatabase db,int studentid){
		Cursor c=db.rawQuery("select * from students where student_id = '"+studentid+"'", null);
		int roomid = -1;
		if(c!=null){
			while(c.moveToNext()){
				roomid = c.getInt(c.getColumnIndex("room_id"));
			}
		}
		c.close();
		db.close();
		return roomid;
	}
	
//	public List<String> getDraftNames(SQLiteDatabase db,int roomid){
//		list_name.clear();
//		Cursor c=db.rawQuery("select * from room where roomid = '"+roomid+"'", null);
//		if(c!=null){
//			while(c.moveToNext()){
//				list_name.add(c.getString(c.getColumnIndex("draftname")));
//		}
//		}
//		c.close();
//		db.close();
//		return list_name;
//	}

//	public Map<String,String> getNumberPassword(SQLiteDatabase db){
//		map=new HashMap<String, String>();
//		Cursor c=db.rawQuery("select * from user", null);
//		if(c!=null){
//		while(c.moveToNext()){
//			map.put(c.getString(c.getColumnIndex("number")), c.getString(c.getColumnIndex("password")));
//		}
//		}
//		c.close();
//		db.close();
//		return map;
//	}

//	public String getusername(SQLiteDatabase db,String usernumber){
//		String username = null;
//		Cursor c=db.rawQuery("select * from user", null);
//		if(c!=null){
//		while(c.moveToNext()){
////			map.put(c.getString(c.getColumnIndex("number")), c.getString(c.getColumnIndex("password")));
//			if(c.getString(c.getColumnIndex("number")).equals(usernumber)){
//				username=c.getString(c.getColumnIndex("username"));
//				break;
//			}
//		}
//		}
//		c.close();
//		db.close();
//		return username;
//	}


	public void addStudent(StudentsVo complete,SQLiteDatabase db){
		ContentValues values=new ContentValues();
		values.put("student_id", complete.getStudentId());
		values.put("student_card_id", complete.getStudentCardId());
		values.put("student_name", complete.getStudentName());
		values.put("student_password", complete.getStudentPassword());
		values.put("department_id", complete.getDepartmentId());
		values.put("department_name", complete.getDepartmentName());
		values.put("class_id", complete.getClassId());
		values.put("class_name", complete.getClassName());
		values.put("builds_name",complete.getBuildName());
		values.put("room_id", complete.getRoomId());
		values.put("room_name", complete.getRoomName());
		db.insert("students", null, values);
		values.clear();
		db.close();
	}

//	public String getPhone(SQLiteDatabase db){
//		String phone=null;
//		Cursor c=db.rawQuery("select * from number", null);
//		if(c.moveToLast()){
//			phone=null;
//			phone=c.getString(c.getColumnIndex("phone"));
//		}
//		c.close();
//		db.close();
//		return phone;
//	}

//	//��ע����Ϣ���ڳ�Ա����
//	public void putPhone(String phone,String country,SQLiteDatabase db){
//		ContentValues values=new ContentValues();
//		values.put("phone", phone);
//		values.put("country", country);
//		db.insert("number", null, values);
//		values.clear();
//		db.close();
//	}

//	public List<Book> getBookInfo(SQLiteDatabase db){
//		List<Book> listbook=new ArrayList<Book>();
//		Cursor c=db.rawQuery("select * from book", null);
//		if(c!=null){
//			while(c.moveToNext()){
//				Book book=new Book();
//				book.setBooknumber(c.getString(c.getColumnIndex("booknumber")));
//				book.setBookname(c.getString(c.getColumnIndex("bookname")));
//				book.setBookauthor(c.getString(c.getColumnIndex("boolauthor")));
//				book.setBooklabel(c.getString(c.getColumnIndex("boollabel")));
//				listbook.add(book);
//			}
//		}
//		c.close();
//		db.close();
//		return listbook;
//	}

//	public void putBookInfo(SQLiteDatabase db,String Booknumber,String Bookname,String Bookauthor,String Booklabel){
//		ContentValues values=new ContentValues();
//		values.put("booknumber", Booknumber);
//		values.put("bookname", Bookname);
//		values.put("boolauthor",Bookauthor);
//		values.put("boollabel", Booklabel);
//		db.insert("book", null, values);
//		values.clear();
//		db.close();
//	}

	public void updataStudent(SQLiteDatabase db,StudentsVo complete){
		ContentValues values=new ContentValues();
		values.put("student_id", complete.getStudentId());
		values.put("student_name", complete.getStudentName());
		values.put("student_password", complete.getStudentPassword());
		values.put("department_id", complete.getDepartmentId());
		values.put("department_name", complete.getDepartmentName());
		values.put("class_id", complete.getClassId());
		values.put("class_name", complete.getClassName());
		values.put("builds_name",complete.getBuildName());
		values.put("room_id", complete.getRoomId());
		values.put("room_name", complete.getRoomName());
		db.update("students", values, "student_card_id=?", new String[]{complete.getStudentCardId()});
		values.clear();
		db.close();
	}

	public void deleteStudent(SQLiteDatabase db,int studentid){
		String sql="DELETE FROM students WHERE student_id='"+studentid+"'";
		db.execSQL(sql);
		db.close();
	}
	
	public void deleteWeek(SQLiteDatabase db,int roomid){
		String sql="DELETE FROM weeks WHERE roomid='"+roomid+"'";
		db.execSQL(sql);
		db.close();
	}

	public WeeksText getWeek(SQLiteDatabase db, int roomid){
		Cursor c=db.rawQuery("select * from weeks where roomid = '"+roomid+"'", null);
		WeeksText weeksText = null;
		if(c!=null){
			while(c.moveToNext()){
				weeksText =new WeeksText();
				weeksText.setStudy((byte) c.getInt(c.getColumnIndex("study")));
				weeksText.setHealth((byte) c.getInt(c.getColumnIndex("health")));
				weeksText.setMood((byte) c.getInt(c.getColumnIndex("mood")));
				weeksText.setConsume((byte) c.getInt(c.getColumnIndex("consume")));
				weeksText.setReturnHome((byte) c.getInt(c.getColumnIndex("return_home")));
				weeksText.setSleepCondition((byte) c.getInt(c.getColumnIndex("sleep_condition")));
				weeksText.setLoveLose((byte) c.getInt(c.getColumnIndex("love_lose")));
				weeksText.setConditionText(c.getString(c.getColumnIndex("condition_text")));
			}
		}
		c.close();
		db.close();
		return weeksText;
	}

	public void updataWeek(SQLiteDatabase db,WeeksText weeksText){
		ContentValues values=new ContentValues();
		values.put("study", weeksText.getStudy());
		values.put("health", weeksText.getHealth());
		values.put("return_home", weeksText.getReturnHome());
		values.put("sleep_condition", weeksText.getSleepCondition());
		values.put("mood", weeksText.getMood());
		values.put("consume", weeksText.getConsume());
		values.put("love_lose",weeksText.getLoveLose());
		values.put("condition_text", weeksText.getConditionText());
		db.update("weeks", values, "roomid=?", new String[]{weeksText.getRoomId()+""});
		values.clear();
		db.close();
	}

	public void addWeek(WeeksText weeksText,SQLiteDatabase db){
		ContentValues values=new ContentValues();
		values.put("roomid", weeksText.getRoomId());
		values.put("study", weeksText.getStudy());
		values.put("health", weeksText.getHealth());
		values.put("return_home", weeksText.getReturnHome());
		values.put("sleep_condition", weeksText.getSleepCondition());
		values.put("mood", weeksText.getMood());
		values.put("consume", weeksText.getConsume());
		values.put("love_lose",weeksText.getLoveLose());
		values.put("condition_text", weeksText.getConditionText());
		db.insert("weeks", null, values);
		values.clear();
		db.close();
	}

	public TeachersVo getTeacher(SQLiteDatabase db, int teacherid){
		Cursor c=db.rawQuery("select * from teachers where teacher_id = '"+teacherid+"'", null);
		TeachersVo teachersVo = null;
		if(c!=null){
			while(c.moveToNext()){
                teachersVo =new TeachersVo();
                teachersVo.setTeacherId(c.getInt(c.getColumnIndex("teacher_id")));
                teachersVo.setTeacherCardId(c.getString(c.getColumnIndex("teacher_cardid")));
                teachersVo.setTeacherName(c.getString(c.getColumnIndex("teacher_name")));
                teachersVo.setDepartmentName(c.getString(c.getColumnIndex("department_name")));
			}
		}
		c.close();
		db.close();
		return teachersVo;
	}

	public String getTeacherCardId(SQLiteDatabase db, int teacherid){
		Cursor c=db.rawQuery("select * from teachers where teacher_id = '"+teacherid+"'", null);
		String teachercardid = null;
		if(c!=null){
			while(c.moveToNext()){
				teachercardid = c.getString(c.getColumnIndex("teacher_cardid"));
			}
		}
		c.close();
		db.close();
		return teachercardid;
	}

	public String getTeacherName(SQLiteDatabase db, int teacherid){
		Cursor c=db.rawQuery("select * from teachers where teacher_id = '"+teacherid+"'", null);
		String teacherName= null;
		if(c!=null){
			while(c.moveToNext()){
				teacherName = c.getString(c.getColumnIndex("teacher_name"));
			}
		}
		c.close();
		db.close();
		return teacherName;
	}

	public void addTeacher(TeachersVo teachers,SQLiteDatabase db){
		ContentValues values=new ContentValues();
		values.put("teacher_id", teachers.getTeacherId());
		values.put("teacher_cardid", teachers.getTeacherCardId());
		values.put("teacher_name", teachers.getTeacherName());
		values.put("department_name", teachers.getDepartmentName());
		db.insert("teachers", null, values);
		values.clear();
		db.close();
	}

	public void deleteTeacher(SQLiteDatabase db,int teacherid){
		String sql="DELETE FROM teachers WHERE teacher_id='"+teacherid+"'";
		db.execSQL(sql);
		db.close();
	}
}
