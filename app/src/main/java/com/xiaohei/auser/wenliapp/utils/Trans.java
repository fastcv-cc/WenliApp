package com.xiaohei.auser.wenliapp.utils;

public class Trans {

	public static String getStudy(int i){
		if(i==1){
			return "很好";
		}else if(i==2){
			return "一般";
		}else{
			return "很差";
		}
	}
	
	public static String getHeath(int i){
		if(i==1){
			return "很好";
		}else if(i==2){
			return "一般";
		}else{
			return "很差";
		}
	}
	
	public static String getReturn(int i){
		if(i==1){
			return "没有";
		}else if(i==2){
			return "很少";
		}else{
			return "很多";
		}
	}
	
	public static String getSleep(int i){
		if(i==1){
			return "很好";
		}else if(i==2){
			return "一般";
		}else{
			return "很差";
		}
	}
	
	public static String getMood(int i){
		if(i==1){
			return "很好";
		}else if(i==2){
			return "一般";
		}else{
			return "很差";
		}
	}
	
	public static String getConsume(int i){
		if(i==1){
			return "没有";
		}else if(i==2){
			return "很少";
		}else{
			return "很多";
		}
	}
	
	public static String getLoveLose(int i){
		if(i==1){
			return "有且情绪低落";
		}else if(i==2){
			return "有且情绪正常";
		}else{
			return "没有";
		}
	}
}
