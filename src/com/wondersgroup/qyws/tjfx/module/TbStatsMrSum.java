package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 就诊统计表
 * @author Administrator
 *
 */
public class TbStatsMrSum implements Serializable {
	private static final long serialVersionUID = 1L;
	private long ID; //
	private String PXBID; //频次表ID
	private String YBH; //医保号
	private String SFZH; //身份证号
	private String RYLB; //人员类别
	private String XM; //姓名
	private String DWMC; //单位名称
	private Double JZFY; //就诊费用
	private Double PC; //频次
	private Date JSSJ; //结束时间
	private String JGID; //就诊机构ID
	private String QXDM; //区县代码
	private Date KSSJ; //开始时间
	private String YXBZ; //有效标志1：有效；2：无效
	private Date XGSJ; //修改时间

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getPXBID() {
		return PXBID;
	}

	public void setPXBID(String pXBID) {
		PXBID = pXBID;
	}

	public String getYBH() {
		return YBH;
	}

	public void setYBH(String yBH) {
		YBH = yBH;
	}

	public String getSFZH() {
		return SFZH;
	}

	public void setSFZH(String sFZH) {
		SFZH = sFZH;
	}

	public String getRYLB() {
		return RYLB;
	}

	public void setRYLB(String rYLB) {
		RYLB = rYLB;
	}

	public String getXM() {
		return XM;
	}

	public void setXM(String xM) {
		XM = xM;
	}

	public String getDWMC() {
		return DWMC;
	}

	public void setDWMC(String dWMC) {
		DWMC = dWMC;
	}

	public Double getJZFY() {
		return JZFY;
	}

	public void setJZFY(Double jZFY) {
		JZFY = jZFY;
	}

	public Double getPC() {
		return PC;
	}

	public void setPC(Double pC) {
		PC = pC;
	}

	public Date getJSSJ() {
		return JSSJ;
	}

	public void setJSSJ(Date jSSJ) {
		JSSJ = jSSJ;
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

	public Date getKSSJ() {
		return KSSJ;
	}

	public void setKSSJ(Date kSSJ) {
		KSSJ = kSSJ;
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
