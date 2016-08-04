package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;

/**
 * 中药饮片交易按医师汇总事实表(L2)
 * 
 * @author Administrator
 * 
 */
public class FZyypTradeDrSum2 implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;//ID
	private String dr_id;// 医师ID
	private String jsny;// 结算年月
	private String xzlb;//险种类别(01=职工、02=统筹、03=城镇居民、04=城乡居民)
	private String hosp_id;// 定点机构ID
	private String ddjgdj;//定点机构等级
	private String yd_id;// 外配药店ID
	private Double tb_fy;// 特病人员-总费用
	private Double tb_fy_p;// 特病人员-上期总费用
	private Long tb_ts;// 特病人员-总帖数
	private Long tb_ts_p;// 特病人员-上期总帖数
	private Long tb_rc;// 特病人员-就诊人次
	private Double ftb_fy;// 非特病人员-总费用
	private Double ftb_fy_p;// 非特病人员-上期总费用
	private Long ftb_ts;// 非特病人员-总帖数
	private Long ftb_ts_p;// 非特病人员-上期总帖数
	private Long ftb_rc;// 非特病人员-就诊人次

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

	public String getDdjgdj() {
		return ddjgdj;
	}

	public void setDdjgdj(String ddjgdj) {
		this.ddjgdj = ddjgdj;
	}

	public String getYd_id() {
		return yd_id;
	}

	public void setYd_id(String ydId) {
		yd_id = ydId;
	}

	public Double getTb_fy() {
		return tb_fy;
	}

	public void setTb_fy(Double tbFy) {
		tb_fy = tbFy;
	}

	public Double getTb_fy_p() {
		return tb_fy_p;
	}

	public void setTb_fy_p(Double tbFyP) {
		tb_fy_p = tbFyP;
	}

	public Long getTb_ts() {
		return tb_ts;
	}

	public void setTb_ts(Long tbTs) {
		tb_ts = tbTs;
	}

	public Long getTb_ts_p() {
		return tb_ts_p;
	}

	public void setTb_ts_p(Long tbTsP) {
		tb_ts_p = tbTsP;
	}

	public Long getTb_rc() {
		return tb_rc;
	}

	public void setTb_rc(Long tbRc) {
		tb_rc = tbRc;
	}

	public Double getFtb_fy() {
		return ftb_fy;
	}

	public void setFtb_fy(Double ftbFy) {
		ftb_fy = ftbFy;
	}

	public Double getFtb_fy_p() {
		return ftb_fy_p;
	}

	public void setFtb_fy_p(Double ftbFyP) {
		ftb_fy_p = ftbFyP;
	}

	public Long getFtb_ts() {
		return ftb_ts;
	}

	public void setFtb_ts(Long ftbTs) {
		ftb_ts = ftbTs;
	}

	public Long getFtb_ts_p() {
		return ftb_ts_p;
	}

	public void setFtb_ts_p(Long ftbTsP) {
		ftb_ts_p = ftbTsP;
	}

	public Long getFtb_rc() {
		return ftb_rc;
	}

	public void setFtb_rc(Long ftbRc) {
		ftb_rc = ftbRc;
	}

}
