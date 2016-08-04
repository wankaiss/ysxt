package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 就诊明细表
 * @author Administrator
 *
 */
public class TbStatsMrDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private long ID; //
	private String YBH; //医保号
	private String SFZH; //身份证号
	private String RYLB; //人员类别
	private String XM; //姓名
	private Date JZSJ; //就诊时间
	private String SJDM; //时间代码
	private String JZLX; //就诊类型
	private Double JZFY; //就诊费用
	private String ZJLSH; //就诊流水号
	private String RYZDDM; //入院诊断代码
	private String RYZDMC; //入院诊断名称
	private String YSID; //医生ID
	private Double DTJG; //单贴价格
	private Double DTWS; //单贴味数
	private String WPJGID; //外配机构ID
	private String WPYSID; //外配医生ID
	private String JGID; //就诊机构ID
	private String QXDM; //区县代码
	private String YXBZ; //有效标志1：有效；2：无效
	private Date XGSJ; //修改时间

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
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

	public Date getJZSJ() {
		return JZSJ;
	}

	public void setJZSJ(Date jZSJ) {
		JZSJ = jZSJ;
	}

	public String getSJDM() {
		return SJDM;
	}

	public void setSJDM(String sJDM) {
		SJDM = sJDM;
	}

	public String getJZLX() {
		return JZLX;
	}

	public void setJZLX(String jZLX) {
		JZLX = jZLX;
	}

	public Double getJZFY() {
		return JZFY;
	}

	public void setJZFY(Double jZFY) {
		JZFY = jZFY;
	}

	public String getZJLSH() {
		return ZJLSH;
	}

	public void setZJLSH(String zJLSH) {
		ZJLSH = zJLSH;
	}

	public String getRYZDDM() {
		return RYZDDM;
	}

	public void setRYZDDM(String rYZDDM) {
		RYZDDM = rYZDDM;
	}

	public String getRYZDMC() {
		return RYZDMC;
	}

	public void setRYZDMC(String rYZDMC) {
		RYZDMC = rYZDMC;
	}

	public String getYSID() {
		return YSID;
	}

	public void setYSID(String ySID) {
		YSID = ySID;
	}

	public Double getDTJG() {
		return DTJG;
	}

	public void setDTJG(Double dTJG) {
		DTJG = dTJG;
	}

	public Double getDTWS() {
		return DTWS;
	}

	public void setDTWS(Double dTWS) {
		DTWS = dTWS;
	}

	public String getWPJGID() {
		return WPJGID;
	}

	public void setWPJGID(String wPJGID) {
		WPJGID = wPJGID;
	}

	public String getWPYSID() {
		return WPYSID;
	}

	public void setWPYSID(String wPYSID) {
		WPYSID = wPYSID;
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
