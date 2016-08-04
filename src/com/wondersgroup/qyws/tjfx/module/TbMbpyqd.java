package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 参保人慢病药品配药清单
 * 
 * @author Administrator
 * 
 */
public class TbMbpyqd implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ID;
	private Integer YBND; //医保年度
	private String SJDM; // 时间代码
	private String YBH; // 医保号
	private String SFZHM; // 身份证号码
	private String XM; // 姓名
	private Integer NL; // 年龄
	private String QXDM; // 区县代码
	private String TCDM; // 统筹代码
	private String RYLB; // 人员类别
	private String JZLX; // 就诊类型
	private long AAZ217; // 就诊编号
	private long AAZ213; // 明细ID
	private String AKE001; // 社保三大目录统一编码
	private String AKE002; // 社保三大目录名称,为便于对照和审批,医疗机构自主登记的目录名称与社保三大目录名称有差异的地方可以记录医疗机构的目录名称
	private String AKE003; // 三大目录类别
	private Date AKC221; // 处方明细时间
	private String YXBZ; // 有效标志 1：有效；2：无效
	private Date XGSJ; // 修改时间

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Integer getYBND() {
		return YBND;
	}

	public void setYBND(Integer yBND) {
		YBND = yBND;
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

	public long getAAZ217() {
		return AAZ217;
	}

	public void setAAZ217(long aAZ217) {
		AAZ217 = aAZ217;
	}

	public long getAAZ213() {
		return AAZ213;
	}

	public void setAAZ213(long aAZ213) {
		AAZ213 = aAZ213;
	}

	public String getAKE001() {
		return AKE001;
	}

	public void setAKE001(String aKE001) {
		AKE001 = aKE001;
	}

	public String getAKE002() {
		return AKE002;
	}

	public void setAKE002(String aKE002) {
		AKE002 = aKE002;
	}

	public String getAKE003() {
		return AKE003;
	}

	public void setAKE003(String aKE003) {
		AKE003 = aKE003;
	}

	public Date getAKC221() {
		return AKC221;
	}

	public void setAKC221(Date aKC221) {
		AKC221 = aKC221;
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
