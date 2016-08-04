package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 参保人药店购药累计表
 * 
 * @author Administrator
 * 
 */
public class TbStatsDrug implements Serializable {
	private static final long serialVersionUID = 1L;
	private long ID; //
	private String SJDM; // 时间代码
	private String YBH; // 医保号
	private String XM; // 姓名
	private String QXDM; // 区县代码
	private String TCDM; // 统筹代码
	private String TBBZ; // 特病标志
	private String TBMC; // 特病名称
	private String RYLB; // 人员类别
	private Double CFYFY; // 处方药费用
	private Double FCFYFY; // 非处方药费用
	private Double ZFY; // 总费用
	private String JGID; // 本次费用最高机构ID
	private Double JDZ; // 本次费用最高机构的值
	private String YXBZ; // 有效标志1：有效；2：无效
	private Date XGSJ; // 修改时间

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getSJDM() {
		return SJDM;
	}

	public void setSJDM(String sJDM) {
		SJDM = sJDM;
	}

	public String getYBH() {
		return YBH;
	}

	public void setYBH(String yBH) {
		YBH = yBH;
	}

	public String getXM() {
		return XM;
	}

	public void setXM(String xM) {
		XM = xM;
	}

	public String getQXDM() {
		return QXDM;
	}

	public void setQXDM(String qXDM) {
		QXDM = qXDM;
	}

	public String getTCDM() {
		return TCDM;
	}

	public void setTCDM(String tCDM) {
		TCDM = tCDM;
	}

	public String getTBBZ() {
		return TBBZ;
	}

	public void setTBBZ(String tBBZ) {
		TBBZ = tBBZ;
	}

	public String getTBMC() {
		return TBMC;
	}

	public void setTBMC(String tBMC) {
		TBMC = tBMC;
	}

	public String getRYLB() {
		return RYLB;
	}

	public void setRYLB(String rYLB) {
		RYLB = rYLB;
	}

	public Double getCFYFY() {
		return CFYFY;
	}

	public void setCFYFY(Double cFYFY) {
		CFYFY = cFYFY;
	}

	public Double getFCFYFY() {
		return FCFYFY;
	}

	public void setFCFYFY(Double fCFYFY) {
		FCFYFY = fCFYFY;
	}

	public Double getZFY() {
		return ZFY;
	}

	public void setZFY(Double zFY) {
		ZFY = zFY;
	}

	public String getJGID() {
		return JGID;
	}

	public void setJGID(String jGID) {
		JGID = jGID;
	}

	public Double getJDZ() {
		return JDZ;
	}

	public void setJDZ(Double jDZ) {
		JDZ = jDZ;
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
