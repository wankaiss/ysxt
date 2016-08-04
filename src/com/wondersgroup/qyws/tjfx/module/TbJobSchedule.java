package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 执行计划表
 * 
 * @author Administrator
 * 
 */
public class TbJobSchedule implements Serializable {
	private static final long serialVersionUID = 1L;
	private long ID;
	private String RWLX; // 任务类型01：药店刷卡频次统计
	private String PARA1; // 参数1RWLX=01，日期开始时间
	private String PARA2; // 参数2RWLX=01，日期结束时间
	private String PARA3; // 参数3RWLX=01，时间开始时分
	private String PARA4; // 参数4RWLX=01，时间结束时分
	private String PARA5; // 参数5RWLX=01，区县代码
	private String PARA6; // 参数6RWLX=01，机构ID
	private String PARA7; // 参数7RWLX=01，就诊类型
	private String PARA8; // 参数8RWLX=01，时间间隔（分钟）
	private String PARA9; // 参数9RWLX=01，频次
	private String PARA10; // 参数10RWLX=01，排名数量
	private String PARA11; // 参数11
	private String PARA12; // 参数12
	private String ZXZT; // 执行状态0：未执行；1：正常执行；2：执行完成；3：取消
	private String ZXRID; // 执行人ID
	private Date YJZXSJ; // 预计执行时间
	private Double YXJ; // 优先级
	private Date ZXKSSJ; // 执行开始时间
	private Date ZXJSSJ; // 执行结束时间
	private String YXBZ; // 有效标志1：有效；2：无效
	private Date XGSJ; // 修改时间

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getRWLX() {
		return RWLX;
	}

	public void setRWLX(String rWLX) {
		RWLX = rWLX;
	}

	public String getPARA1() {
		return PARA1;
	}

	public void setPARA1(String pARA1) {
		PARA1 = pARA1;
	}

	public String getPARA2() {
		return PARA2;
	}

	public void setPARA2(String pARA2) {
		PARA2 = pARA2;
	}

	public String getPARA3() {
		return PARA3;
	}

	public void setPARA3(String pARA3) {
		PARA3 = pARA3;
	}

	public String getPARA4() {
		return PARA4;
	}

	public void setPARA4(String pARA4) {
		PARA4 = pARA4;
	}

	public String getPARA5() {
		return PARA5;
	}

	public void setPARA5(String pARA5) {
		PARA5 = pARA5;
	}

	public String getPARA6() {
		return PARA6;
	}

	public void setPARA6(String pARA6) {
		PARA6 = pARA6;
	}

	public String getPARA7() {
		return PARA7;
	}

	public void setPARA7(String pARA7) {
		PARA7 = pARA7;
	}

	public String getPARA8() {
		return PARA8;
	}

	public void setPARA8(String pARA8) {
		PARA8 = pARA8;
	}

	public String getPARA9() {
		return PARA9;
	}

	public void setPARA9(String pARA9) {
		PARA9 = pARA9;
	}

	public String getPARA10() {
		return PARA10;
	}

	public void setPARA10(String pARA10) {
		PARA10 = pARA10;
	}

	public String getPARA11() {
		return PARA11;
	}

	public void setPARA11(String pARA11) {
		PARA11 = pARA11;
	}

	public String getPARA12() {
		return PARA12;
	}

	public void setPARA12(String pARA12) {
		PARA12 = pARA12;
	}

	public String getZXZT() {
		return ZXZT;
	}

	public void setZXZT(String zXZT) {
		ZXZT = zXZT;
	}

	public String getZXRID() {
		return ZXRID;
	}

	public void setZXRID(String zXRID) {
		ZXRID = zXRID;
	}

	public Date getYJZXSJ() {
		return YJZXSJ;
	}

	public void setYJZXSJ(Date yJZXSJ) {
		YJZXSJ = yJZXSJ;
	}

	public Double getYXJ() {
		return YXJ;
	}

	public void setYXJ(Double yXJ) {
		YXJ = yXJ;
	}

	public Date getZXKSSJ() {
		return ZXKSSJ;
	}

	public void setZXKSSJ(Date zXKSSJ) {
		ZXKSSJ = zXKSSJ;
	}

	public Date getZXJSSJ() {
		return ZXJSSJ;
	}

	public void setZXJSSJ(Date zXJSSJ) {
		ZXJSSJ = zXJSSJ;
	}

	public String getYXBZ() {
		return YXBZ;
	}

	public void setYXBZ(String yXBZ) {
		YXBZ = yXBZ;
	}

	public Date getXGSJ() {
		return XGSJ;
	}

	public void setXGSJ(Date xGSJ) {
		XGSJ = xGSJ;
	}

}
