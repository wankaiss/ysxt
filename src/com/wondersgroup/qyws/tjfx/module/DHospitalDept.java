package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 医疗机构信息表
 * 
 * @author Administrator
 * 
 */
public class DHospitalDept implements Serializable {
	private static final long serialVersionUID = 1L;
	private String dept_id;// 机构ID
	private String hosp_id;// 医疗机构Id
	private String ksdm;// 科室代码
	private String ksmc;// 科室名称
	private String zt;// 状态
	private Date qssj;// 起始时间
	private Date jssj;// 结束时间

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String deptId) {
		dept_id = deptId;
	}

	public String getHosp_id() {
		return hosp_id;
	}

	public void setHosp_id(String hospId) {
		hosp_id = hospId;
	}

	public String getKsdm() {
		return ksdm;
	}

	public void setKsdm(String ksdm) {
		this.ksdm = ksdm;
	}

	public String getKsmc() {
		return ksmc;
	}

	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
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
