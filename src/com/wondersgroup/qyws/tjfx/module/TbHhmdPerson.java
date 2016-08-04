package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 参保人红黑名单
 * 
 * @author Administrator
 * 
 */
public class TbHhmdPerson implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long ID;
	private String YBH; // 医保号
	private String SFZHM; // 身份证号码
	private String XM; // 姓名
	private Integer NL; // 年龄
	private String QXDM; // 区县代码
	private String TCDM; // 统筹代码
	private String HHMDBZ; // 红黑名单标志(1：红名单，2：黑名单)
	private String BZ; // 备注
	private String YXBZ; // 有效标志 1：有效；2：无效
	private Date XGSJ; // 修改时间

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
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

	public String getHHMDBZ() {
		return HHMDBZ;
	}

	public void setHHMDBZ(String hHMDBZ) {
		HHMDBZ = hHMDBZ;
	}

	public String getBZ() {
		return BZ;
	}

	public void setBZ(String bZ) {
		BZ = bZ;
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
