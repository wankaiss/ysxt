package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 药店综合分析参数权重表
 * 
 * @author Administrator
 * 
 */
public class TbParaHeavy implements Serializable {
	private static final long serialVersionUID = 1L;
	private long ID;
	private String QZM; // 权重名
	private String QZDM; // 权重代码
	private String QZJB; // 权重级别1：根节点；2：子节点；以此类推
	private String SJQZID; // 上级权重ID
	private String TCDM; // 统筹代码
	private Double QZZ; // 权重值
	private String YXBZ; // 有效标志1：有效；2：无效
	private Date XGSJ; // 修改时间
	private long GZBZ; //规则标识

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getQZM() {
		return QZM;
	}

	public void setQZM(String qZM) {
		QZM = qZM;
	}

	public String getQZDM() {
		return QZDM;
	}

	public void setQZDM(String qZDM) {
		QZDM = qZDM;
	}

	public String getQZJB() {
		return QZJB;
	}

	public void setQZJB(String qZJB) {
		QZJB = qZJB;
	}

	public String getSJQZID() {
		return SJQZID;
	}

	public void setSJQZID(String sJQZID) {
		SJQZID = sJQZID;
	}

	public String getTCDM() {
		return TCDM;
	}

	public void setTCDM(String tCDM) {
		TCDM = tCDM;
	}

	public Double getQZZ() {
		return QZZ;
	}

	public void setQZZ(Double qZZ) {
		QZZ = qZZ;
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

	public long getGZBZ() {
		return GZBZ;
	}

	public void setGZBZ(long gZBZ) {
		GZBZ = gZBZ;
	}

}
