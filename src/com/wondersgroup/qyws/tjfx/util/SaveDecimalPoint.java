package com.wondersgroup.qyws.tjfx.util;

import java.math.BigDecimal;

//保留几位小数点
public class SaveDecimalPoint {
	
	//保留小数位数
	public static Double saveDecimalPoint(String decimal){
		
		BigDecimal bd = new BigDecimal(decimal).setScale(2,BigDecimal.ROUND_HALF_UP);     
		
		return bd.doubleValue();
	}

}
