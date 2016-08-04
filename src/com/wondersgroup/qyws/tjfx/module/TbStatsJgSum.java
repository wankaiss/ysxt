package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 药店刷卡频次统计表
 * 
 * @author Administrator
 * 
 */
public class TbStatsJgSum implements Serializable {
	private static final long serialVersionUID = 1L;
	private long ID; //
	private String ZXJHID; // 执行计划ID
	private String JGID; // 就诊机构ID
	private Double JZFY; // 就诊费用
	private Double JZRC; // 就诊人次
	private String JZLX; // 就诊类型
	private Date KSSJ; // 开始时间
	private Date JSSJ; // 结束时间
	private String QXDM; // 区县代码
	private String YXBZ; // 有效标志1：有效；2：无效
	private Date XGSJ; // 修改时间

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getZXJHID() {
		return ZXJHID;
	}

	public void setZXJHID(String zXJHID) {
		ZXJHID = zXJHID;
	}

	public String getJGID() {
		return JGID;
	}

	public void setJGID(String jGID) {
		JGID = jGID;
	}

	public Double getJZFY() {
		return JZFY;
	}

	public void setJZFY(Double jZFY) {
		JZFY = jZFY;
	}

	public Double getJZRC() {
		return JZRC;
	}

	public void setJZRC(Double jZRC) {
		JZRC = jZRC;
	}

	public String getJZLX() {
		return JZLX;
	}

	public void setJZLX(String jZLX) {
		JZLX = jZLX;
	}

	public Date getKSSJ() {
		return KSSJ;
	}

	public void setKSSJ(Date kSSJ) {
		KSSJ = kSSJ;
	}

	public Date getJSSJ() {
		return JSSJ;
	}

	public void setJSSJ(Date jSSJ) {
		JSSJ = jSSJ;
	}

	public String getQXDM() {
		return QXDM;
	}

	public void setQXDM(String qXDM) {
		QXDM = qXDM;
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
