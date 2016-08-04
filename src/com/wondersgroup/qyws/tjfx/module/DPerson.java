package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;

/**
 * 参保人维度表
 * 
 * @author Administrator
 * 
 */
public class DPerson implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ybh;// 医保号
	private String zhh;//账户号
	private String xm;// 姓名
	private String xb;// 性别
	private String zjlx;//证件类型  
	private String sfzh;// 身份证号
	private Integer csrq;// 出生日期
	private String mz;// 民族
	private String yb;// 邮编
	private String dz;// 地址
	private String lxdh;// 联系电话
	private String dwdm;//单位代码  
	private String rylb;// 人员类别
	private String ryzlb;// 人员子类别
	private String xzlb;//险种类别(01=职工、02=统筹、03=城镇居民、04=城乡居民)  
	private String xzqhdm;//行政区划代码
	private String zhzt;//账户状态
	private String tbbz;// 特病标识

	public String getYbh() {
		return ybh;
	}

	public void setYbh(String ybh) {
		this.ybh = ybh;
	}

	public String getZhh() {
		return zhh;
	}

	public void setZhh(String zhh) {
		this.zhh = zhh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getZjlx() {
		return zjlx;
	}

	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public Integer getCsrq() {
		return csrq;
	}

	public void setCsrq(Integer csrq) {
		this.csrq = csrq;
	}

	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getYb() {
		return yb;
	}

	public void setYb(String yb) {
		this.yb = yb;
	}

	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getDwdm() {
		return dwdm;
	}

	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}

	public String getRylb() {
		return rylb;
	}

	public void setRylb(String rylb) {
		this.rylb = rylb;
	}

	public String getRyzlb() {
		return ryzlb;
	}

	public void setRyzlb(String ryzlb) {
		this.ryzlb = ryzlb;
	}

	public String getXzlb() {
		return xzlb;
	}

	public void setXzlb(String xzlb) {
		this.xzlb = xzlb;
	}

	public String getXzqhdm() {
		return xzqhdm;
	}

	public void setXzqhdm(String xzqhdm) {
		this.xzqhdm = xzqhdm;
	}

	public String getZhzt() {
		return zhzt;
	}

	public void setZhzt(String zhzt) {
		this.zhzt = zhzt;
	}

	public String getTbbz() {
		return tbbz;
	}

	public void setTbbz(String tbbz) {
		this.tbbz = tbbz;
	}

}
