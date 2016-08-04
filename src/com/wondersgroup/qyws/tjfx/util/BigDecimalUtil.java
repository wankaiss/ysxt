package com.wondersgroup.qyws.tjfx.util;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

public class BigDecimalUtil {
	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
		return v1.add(v2);
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
		return v1.subtract(v2);
	}
	
	public static BigDecimal sub(BigDecimal v1, int v2) {
		return sub(v1,new BigDecimal(v2));
	}
	
	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @param scale
	 *            表示需要精确到小数点以后几位。
	 * @return 两个参数的差
	 */
	public static BigDecimal sub(BigDecimal v1, BigDecimal v2,int scale) {
		return round(sub(v1,v2),scale);
	}

	public static BigDecimal sub(BigDecimal v1, int v2,int scale) {
		return round(sub(v1,v2),scale);
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
		return v1.multiply(v2);
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static BigDecimal div(BigDecimal v1, BigDecimal v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		if(v1==null||v2==null||v2.intValue()==0){
			return new BigDecimal(0);
		}
		return v1.divide(v2, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static BigDecimal round(BigDecimal v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v.toString());
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP);
	}
	
	public static void main(String args[]){
		Logger log=Logger.getLogger(BigDecimalUtil.class);

		// 1.测试 div
		BigDecimal big1=new BigDecimal("2000.999999988889");
		BigDecimal big2=new BigDecimal("50.8");
		log.debug(div(big1,big2,4));
		
		// 2.测试sub
		log.debug(sub(big1,big2,2).toString());
		log.debug(sub(big1,big2).toEngineeringString());
		log.debug(sub(big1,big2).toPlainString());
	}
}
