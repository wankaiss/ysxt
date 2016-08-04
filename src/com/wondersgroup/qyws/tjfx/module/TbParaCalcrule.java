package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 药店综合分析计算规则表
 * @author Administrator
 *
 */
public class TbParaCalcrule implements Serializable {
	private static final long serialVersionUID = 1L;
	private long ID;
	private String GZDM; //规则代码
	private String GZMC; //规则名称
	private String KSSJ; //开始时间
	private String JSSJ; //结束时间
	private String GZDY; //规则定义
	private String JSDY; //计算定义
	private String GZMS; //规则描述
	private String YXBZ; //有效标志1：有效；2：无效
	private Date XGSJ; //修改时间

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getGZDM() {
		return GZDM;
	}

	public void setGZDM(String gZDM) {
		GZDM = gZDM;
	}

	public String getGZMC() {
		return GZMC;
	}

	public void setGZMC(String gZMC) {
		GZMC = gZMC;
	}

	public String getKSSJ() {
		return KSSJ;
	}

	public void setKSSJ(String kSSJ) {
		KSSJ = kSSJ;
	}

	public String getJSSJ() {
		return JSSJ;
	}

	public void setJSSJ(String jSSJ) {
		JSSJ = jSSJ;
	}

	public String getGZDY() {
		return GZDY;
	}

	public void setGZDY(String gZDY) {
		GZDY = gZDY;
	}

	public String getJSDY() {
		return JSDY;
	}

	public void setJSDY(String jSDY) {
		JSDY = jSDY;
	}

	public String getGZMS() {
		return GZMS;
	}

	public void setGZMS(String gZMS) {
		GZMS = gZMS;
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
