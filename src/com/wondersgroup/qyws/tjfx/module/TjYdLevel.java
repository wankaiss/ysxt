package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 药店等级
 * 
 * @author Administrator
 * 
 */
public class TjYdLevel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long ID;
	private String akb020; // 药店编码
	private String akb021; // 药店名称
	private String aaa027; // 统筹编码
	private String yddj; // 药店等级
	private String YXBZ; // 有效标志 1：有效；2：无效
	private String YDDJND; // 药店等级年度
	private Date xgsj; // 修改时间

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getAkb020() {
		return akb020;
	}

	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}

	public String getAkb021() {
		return akb021;
	}

	public void setAkb021(String akb021) {
		this.akb021 = akb021;
	}

	public String getAaa027() {
		return aaa027;
	}

	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}

	public String getYddj() {
		return yddj;
	}

	public void setYddj(String yddj) {
		this.yddj = yddj;
	}

	public String getYXBZ() {
		return YXBZ;
	}

	public void setYXBZ(String yXBZ) {
		YXBZ = yXBZ;
	}

	public String getYDDJND() {
		return YDDJND;
	}

	public void setYDDJND(String yDDJND) {
		YDDJND = yDDJND;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

}
