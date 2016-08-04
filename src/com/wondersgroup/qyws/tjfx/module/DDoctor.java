package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 医师信息表
 * 
 * @author Administrator
 * 
 */
public class DDoctor implements Serializable {
	private static final long serialVersionUID = 1L;
	private String dr_id;// 医师Id
	private String xm;// 姓名
	private String hosp_id;// 医疗机构Id
	private String ysdm;// 医师代码
	private String zjlx;// 证件类型
	private String zjhm;// 证件号码
	private String zgzs;// 资格证书编码
	private String zyzs;// 执业证书编码
	private String zch;// 职称
	private String zyfw;// 执业范围
	private String zt;// 状态
	private Date qssj;// 起始时间
	private Date jssj;// 结束时间

	public String getDr_id() {
		return dr_id;
	}

	public void setDr_id(String drId) {
		dr_id = drId;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getHosp_id() {
		return hosp_id;
	}

	public void setHosp_id(String hospId) {
		hosp_id = hospId;
	}

	public String getYsdm() {
		return ysdm;
	}

	public void setYsdm(String ysdm) {
		this.ysdm = ysdm;
	}

	public String getZjlx() {
		return zjlx;
	}

	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}

	public String getZjhm() {
		return zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	public String getZgzs() {
		return zgzs;
	}

	public void setZgzs(String zgzs) {
		this.zgzs = zgzs;
	}

	public String getZyzs() {
		return zyzs;
	}

	public void setZyzs(String zyzs) {
		this.zyzs = zyzs;
	}

	public String getZch() {
		return zch;
	}

	public void setZch(String zch) {
		this.zch = zch;
	}

	public String getZyfw() {
		return zyfw;
	}

	public void setZyfw(String zyfw) {
		this.zyfw = zyfw;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public Date getQssj() {
		return qssj;
	}

	public void setQssj(Date qssj) {
		this.qssj = qssj;
	}

	public Date getJssj() {
		return jssj;
	}

	public void setJssj(Date jssj) {
		this.jssj = jssj;
	}

}
