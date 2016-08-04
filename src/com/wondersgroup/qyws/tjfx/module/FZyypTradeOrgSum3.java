package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;

/**
 * 中药饮片交易按机构汇总事实表(L3)
 * 
 * @author Administrator
 * 
 */
public class FZyypTradeOrgSum3 implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;// Id
	private String hosp_id;// 医疗机构ID
	private String ddjgdj;//定点机构等级
	private String yd_id;// 药店ID
	private String jsny;// 结算年月
	private String xzlb;// 险种类别(01=职工、02=统筹、03=城镇居民、04=城乡居民)
	private Double fy;// 总费用
	private Double fy_p;// 上期总费用
	private Long ts;// 贴数
	private Long ts_p;// 上期贴数
	private Long rc;// 就诊人次
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

	public Double getFy() {
		return fy;
	}

	public void setFy(Double fy) {
		this.fy = fy;
	}

	public Double getFy_p() {
		return fy_p;
	}

	public void setFy_p(Double fyP) {
		fy_p = fyP;
	}

	public Long getTs() {
		return ts;
	}

	public void setTs(Long ts) {
		this.ts = ts;
	}

	public Long getTs_p() {
		return ts_p;
	}

	public void setTs_p(Long tsP) {
		ts_p = tsP;
	}

	public Long getRc() {
		return rc;
	}

	public void setRc(Long rc) {
		this.rc = rc;
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
