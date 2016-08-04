package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 参保人就诊统计表
 * @author Administrator
 * 
 */
public class TbStatsPerson implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ID;
	private String SJDM; // 时间代码
	private String YBH; // 医保号
	private String SFZHM; // 身份证号码
	private String XM; // 姓名
	private Integer NL; // 年龄
	private String QXDM; // 区县代码
	private String TCDM; // 统筹代码
	private String RYLB; // 人员类别
	private String JZLX; // 就诊类型
	private Integer JZCS; // 就诊次数
	private Double ZFY; // 总费用 (处方药费用+非处方药费用)
	private Double LNZHZFFY; // 历年账户支付费用
	private String TBBZ; // 特病标志
	private String TBMC; // 特病名称
	private String YXBZ; // 有效标志 1：有效；2：无效
	private Date XGSJ; // 修改时间

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
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

	public String getSFZHM() {
		return SFZHM;
	}

	public void setSFZHM(String sFZHM) {
		SFZHM = sFZHM;
	}

	public String getXM() {
		return XM;
	}

	public void setXM(String xM) {
		XM = xM;
	}

	public Integer getNL() {
		return NL;
	}

	public void setNL(Integer nL) {
		NL = nL;
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

	public Integer getJZCS() {
		return JZCS;
	}

	public void setJZCS(Integer jZCS) {
		JZCS = jZCS;
	}

	public Double getZFY() {
		return ZFY;
	}

	public void setZFY(Double zFY) {
		ZFY = zFY;
	}

	public Double getLNZHZFFY() {
		return LNZHZFFY;
	}

	public void setLNZHZFFY(Double lNZHZFFY) {
		LNZHZFFY = lNZHZFFY;
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
