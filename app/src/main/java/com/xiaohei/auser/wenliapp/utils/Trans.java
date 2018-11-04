package com.xiaohei.auser.wenliapp.utils;

public class Trans {

	/**
	 *  数字对应的状态
	 */
	public static final int ZERO_NUM = 0;
	public static final String NONE_STATUS = "没有";
	public static final int ONE_NUM = 1;
	public static final String GOOD_STATUS = "很好";
	public static final int TWO_NUM = 2;
	public static final String COMMON_STATUS = "一般";
	public static final int THREE_NUM = 3;
	public static final String BAD_STATUS = "很差";
	public static final int FOUR_NUM = 4;
	public static final String LESS_STATUS = "很少";
	public static final int FIVE_NUM = 5;
	public static final String MORE_STATUS = "很多";
	public static final int SIX_NUM = 6;
	public static final String DOWN_STATUS = "有且情绪低落";
	public static final int SEVEN_NUM = 7;
	public static final String NORMAL_STATUS = "有且情绪正常";

	/**
	 *  获取状态名称
	 * @return
	 */
	public static String getStatusName(Integer status) {
		switch (status) {
			case ZERO_NUM: return NONE_STATUS;
			case ONE_NUM: return GOOD_STATUS;
			case TWO_NUM: return COMMON_STATUS;
			case THREE_NUM: return BAD_STATUS;
			case FOUR_NUM: return LESS_STATUS;
			case FIVE_NUM: return MORE_STATUS;
			case SIX_NUM: return DOWN_STATUS;
			case SEVEN_NUM: return NORMAL_STATUS;
			default:return NONE_STATUS;
		}
	}



}
