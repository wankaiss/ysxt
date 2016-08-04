package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 医生中药饮片统计表
 * @author Administrator
 *
 */
public class TbStatsDoctorDrug implements Serializable {
	private static final long serialVersionUID = 1L;
	private long ID;
	private String JGID; //机构ID
	private String QXDM; //区县代码
	private String YSID; //医生ID
	private String RYLB; //人员类别
	private String JZLX; //就诊类型
	private String SJDM; //时间代码
	private String ZBID; //指标ID
	private String LBID; //类别ID
	private Double TJZ; //统计值
	private String YXBZ; //有效标志
	private Date XGSJ; //修改时间

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getJGID() {
		return JGID;
	}

	public void setJGID(String jGID) {
		JGID = jGID;
	}

	public String getQXDM() {
		return QXDM;
	}

	public void setQXDM(String qXDM) {
		QXDM = qXDM;
	}

	public String getYSID() {
		return YSID;
	}

	public void setYSID(String ySID) {
		YSID = ySID;
	}

	public String getRYLB() {
		return RYLB;
	}

	public void setRYLB(String rYLB) {
		RYLB = rYLB;
	}

	public String getJZLX() {
		return JZLX;
	}

	public void setJZLX(String jZLX) {
		JZLX = jZLX;
	}

	public String getSJDM() {
		return SJDM;
	}

	public void setSJDM(String sJDM) {
		SJDM = sJDM;
	}

	public String getZBID() {
		return ZBID;
	}

	public void setZBID(String zBID) {
		ZBID = zBID;
	}

	public String getLBID() {
		return LBID;
	}

	public void setLBID(String lBID) {
		LBID = lBID;
	}

	public Double getTJZ() {
		return TJZ;
	}

	public void setTJZ(Double tJZ) {
		TJZ = tJZ;
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
