package com.wondersgroup.qyws.tjfx.common;

public class BaseStatic {
	
	//项目标识 （登录时通过其确定当前运行是哪个应用，根据权限获得该用户拥有的资源）
	public static final String PROJECT_SIGN ="PT_ZHGL";
	public static String threegrade;
	public static String maxloginfail;
	
	public static  boolean getThreeGrade(){
		
		if("1".equals(threegrade)){
			return true;
		}else{
			return false; 
		}
		
	}
	public String getThreegrade() {
		return threegrade;
	}
	public void setThreegrade(String threegrade) {
		this.threegrade = threegrade;
	}
	public  String getMaxloginfail() {
		return maxloginfail;
	}
	public  void setMaxloginfail(String maxloginfail) {
		this.maxloginfail = maxloginfail;
	}
	
	

	
	
	
	
}
