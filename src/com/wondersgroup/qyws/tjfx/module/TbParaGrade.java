package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 药店综合分析参数级别表
 * 
 * @author Administrator
 * 
 */
public class TbParaGrade implements Serializable {
	private static final long serialVersionUID = 1L;
	private long ID;
	private String JBM; // 级别名
	private String JBDM; // 级别代码
	private String HEAVY_ID; // 权重ID
	private String TCDM; // 统筹代码
	private Double XXZ; // 下限值
	private String XXBHDY; // 下限包含等于1：包含；0：不包含
	private String XXWX; // 下限无限1：无限；0：非无限
	private Double SXZ; // 上限值
	private String SXBHDY; // 上限包含等于1：包含；0：不包含
	private String SXWX; // 上限无限1：无限；0：非无限
	private Double JBFZ; // 级别分值
	private String JBMS; // 级别描述
	private String YXBZ;// 有效标志1：有效；2：无效
	private Date XGSJ; // 修改时间
	private long GZBZ; //规则标识

	public long getGZBZ() {
		return GZBZ;
	}

	public void setGZBZ(long gZBZ) {
		GZBZ = gZBZ;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getJBM() {
		return JBM;
	}

	public void setJBM(String jBM) {
		JBM = jBM;
	}

	public String getJBDM() {
		return JBDM;
	}

	public void setJBDM(String jBDM) {
		JBDM = jBDM;
	}

	public String getHEAVY_ID() {
		return HEAVY_ID;
	}

	public void setHEAVY_ID(String hEAVYID) {
		HEAVY_ID = hEAVYID;
	}

	public String getTCDM() {
		return TCDM;
	}

	public void setTCDM(String tCDM) {
		TCDM = tCDM;
	}

	public Double getXXZ() {
		return XXZ;
	}

	public void setXXZ(Double xXZ) {
		XXZ = xXZ;
	}

	public String getXXBHDY() {
		return XXBHDY;
	}

	public void setXXBHDY(String xXBHDY) {
		XXBHDY = xXBHDY;
	}

	public String getXXWX() {
		return XXWX;
	}

	public void setXXWX(String xXWX) {
		XXWX = xXWX;
	}

	public Double getSXZ() {
		return SXZ;
	}

	public void setSXZ(Double sXZ) {
		SXZ = sXZ;
	}

	public String getSXBHDY() {
		return SXBHDY;
	}

	public void setSXBHDY(String sXBHDY) {
		SXBHDY = sXBHDY;
	}

	public String getSXWX() {
		return SXWX;
	}

	public void setSXWX(String sXWX) {
		SXWX = sXWX;
	}

	public Double getJBFZ() {
		return JBFZ;
	}

	public void setJBFZ(Double jBFZ) {
		JBFZ = jBFZ;
	}

	public String getJBMS() {
		return JBMS;
	}

	public void setJBMS(String jBMS) {
		JBMS = jBMS;
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
