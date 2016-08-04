package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 定点机构维度表
 * 
 * @author Administrator
 * 
 */
public class DDdjg implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ddjg_id;// 定点机构ID
	private String admin_region_id;// 行政区域ID
	private String name;// 医疗机构名称
	private String abbr;// 简称
	private String hosp_type;// 医疗机构类型
	private String hosp_class;// 医院等级
	private String addr;// 地址
	private String zipcode;// 邮编
	private String telephone;// 联系电话
	private String contact_psn;// 联系人
	private String valid_flag;// 有效标志
	private Date qssj;// 起始时间
	private Date jssj;// 结束时间

	public String getDdjg_id() {
		return ddjg_id;
	}

	public void setDdjg_id(String ddjgId) {
		ddjg_id = ddjgId;
	}

	public String getAdmin_region_id() {
		return admin_region_id;
	}

	public void setAdmin_region_id(String adminRegionId) {
		admin_region_id = adminRegionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getHosp_type() {
		return hosp_type;
	}

	public void setHosp_type(String hospType) {
		hosp_type = hospType;
	}

	public String getHosp_class() {
		return hosp_class;
	}

	public void setHosp_class(String hospClass) {
		hosp_class = hospClass;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getContact_psn() {
		return contact_psn;
	}

	public void setContact_psn(String contactPsn) {
		contact_psn = contactPsn;
	}

	public String getValid_flag() {
		return valid_flag;
	}

	public void setValid_flag(String validFlag) {
		valid_flag = validFlag;
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
