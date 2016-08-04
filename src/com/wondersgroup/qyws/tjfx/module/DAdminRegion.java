package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;

/**
 * 行政区域维度表
 * 
 * @author Administrator
 * 
 */
public class DAdminRegion implements Serializable {
	private static final long serialVersionUID = 1L;
	private String admin_region_id;// 行政区域ID
	private String district_name;// 区县名称
	private String valid_flag;// 有效标志

	public String getAdmin_region_id() {
		return admin_region_id;
	}

	public void setAdmin_region_id(String adminRegionId) {
		admin_region_id = adminRegionId;
	}

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String districtName) {
		district_name = districtName;
	}

	public String getValid_flag() {
		return valid_flag;
	}

	public void setValid_flag(String validFlag) {
		valid_flag = validFlag;
	}

}
