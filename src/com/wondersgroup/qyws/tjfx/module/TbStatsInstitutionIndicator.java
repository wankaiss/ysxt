package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 药店综合分析指标统计表
 * @author Administrator
 *
 */
public class TbStatsInstitutionIndicator implements Serializable {
	private static final long serialVersionUID = 1L;
	private long ID; //
	private String JGID; //机构ID
	private String SJDM; //时间代码
	private String ZBID; //指标ID
	private String LBID; //类别ID
	private Double TJZ; //统计值
	private String YXBZ; //有效标志1：有效；2：无效
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
