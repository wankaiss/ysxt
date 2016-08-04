package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;

/**
 * 按医师、中药饮片汇总交易事实表(L2)
 * 
 * @author Administrator
 * 
 */
public class FZyypTradeDrZyypSum2 implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;// ID
	private String dr_id;// 医师ID
	private String jsny;// 结算年月
	private String xzlb;// 险种类别(01=职工、02=统筹、03=城镇居民、04=城乡居民)
	private String hosp_id;// 医疗机构ID
	private String yd_id;// 药店ID
	private String mx_id;// 中药饮片ID
	private Double sl;// 数量
	private Double fy;// 费用

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDr_id() {
		return dr_id;
	}

	public void setDr_id(String drId) {
		dr_id = drId;
	}

	public String getJsny() {
		return jsny;
	}

	public void setJsny(String jsny) {
		this.jsny = jsny;
	}

	public String getXzlb() {
		return xzlb;
	}

	public void setXzlb(String xzlb) {
		this.xzlb = xzlb;
	}

	public String getHosp_id() {
		return hosp_id;
	}

	public void setHosp_id(String hospId) {
		hosp_id = hospId;
	}

	public String getYd_id() {
		return yd_id;
	}

	public void setYd_id(String ydId) {
		yd_id = ydId;
	}

	public String getMx_id() {
		return mx_id;
	}

	public void setMx_id(String mxId) {
		mx_id = mxId;
	}

	public Double getSl() {
		return sl;
	}

	public void setSl(Double sl) {
		this.sl = sl;
	}

	public Double getFy() {
		return fy;
	}

	public void setFy(Double fy) {
		this.fy = fy;
	}

}
