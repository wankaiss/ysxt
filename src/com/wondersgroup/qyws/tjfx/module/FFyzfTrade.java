package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 费用支付事实表(L1)
 * 
 * @author Administrator
 * 
 */
public class FFyzfTrade implements Serializable {
	private static final long serialVersionUID = 1L;
	private String jylsh;// 交易流水号
	private String jzbh;// 就诊编号
	private String mxzdh;// 明细账单号
	private String jyjslb;// 交易结算类别
	private String ybh;// 医保号
	private String zhh;// 账户号
	private String rylb;// 人员类别
	private String ryzlb;// 人员子类别
	private String dylb;// 待遇类别
	private String xzlb;// 险种类别(01=职工、02=统筹、03=城镇居民、04=城乡居民)
	private String cbxzqh;// 参保行政区划
	private String jyqhdm;// 就医区划代码
	private Double date_id;// 日期Id
	private Date trade_time;// 交易时间
	private String takeout_prescrip_id;// 外配处方门急诊就诊编号
	private String wpddjg_id;// 外配处方医疗机构
	private String wpdr_id;// 外配处方医师
	private String zd_group_id;// 诊断组号
	private String ddjg_id;// 定点机构ID
	private String ddjgdj;// 定点机构等级
	private String dr_id;// 医师Id
	private String dept_id;// 科室ID
	private Double fyze;// 费用总额
	private Double xjazfs;// XJA支付数
	private Double xjbzfs;// XJB支付数
	private Double xjczfs;// XJC支付数
	private Double xjdzfs;// XJD支付数
	private Double dnzhzfs;// 当年账户支付数
	private Double lnzhzfs;// 历年账户支付数
	private Double xja_lnzh;// XJA历年账户
	private Double xjb_lnzh;// XJB历年账户
	private Double xjc_lnzh;// XJC历年账户
	private Double xjd_lnzh;// XJD历年账户
	private Double tczfs;// 统筹支付数
	private Double jzzfs;// 救助支付数
	private Double gbzfs;// 公补支付数
	private Double fjzfs;// 附加支付数
	private Integer ypts;// 贴数
	private Integer ypws;// 单贴味数
	private Double dtjg;// 单贴价格
	private Double ypfy;// 中药饮片费用
	private String zt;// 状态

	public String getJylsh() {
		return jylsh;
	}

	public void setJylsh(String jylsh) {
		this.jylsh = jylsh;
	}

	public String getJzbh() {
		return jzbh;
	}

	public void setJzbh(String jzbh) {
		this.jzbh = jzbh;
	}

	public String getMxzdh() {
		return mxzdh;
	}

	public void setMxzdh(String mxzdh) {
		this.mxzdh = mxzdh;
	}

	public String getJyjslb() {
		return jyjslb;
	}

	public void setJyjslb(String jyjslb) {
		this.jyjslb = jyjslb;
	}

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

	public String getDylb() {
		return dylb;
	}

	public void setDylb(String dylb) {
		this.dylb = dylb;
	}

	public String getXzlb() {
		return xzlb;
	}

	public void setXzlb(String xzlb) {
		this.xzlb = xzlb;
	}

	public String getCbxzqh() {
		return cbxzqh;
	}

	public void setCbxzqh(String cbxzqh) {
		this.cbxzqh = cbxzqh;
	}

	public String getJyqhdm() {
		return jyqhdm;
	}

	public void setJyqhdm(String jyqhdm) {
		this.jyqhdm = jyqhdm;
	}

	public Double getDate_id() {
		return date_id;
	}

	public void setDate_id(Double dateId) {
		date_id = dateId;
	}

	public Date getTrade_time() {
		return trade_time;
	}

	public void setTrade_time(Date tradeTime) {
		trade_time = tradeTime;
	}

	public String getTakeout_prescrip_id() {
		return takeout_prescrip_id;
	}

	public void setTakeout_prescrip_id(String takeoutPrescripId) {
		takeout_prescrip_id = takeoutPrescripId;
	}

	public String getWpddjg_id() {
		return wpddjg_id;
	}

	public void setWpddjg_id(String wpddjgId) {
		wpddjg_id = wpddjgId;
	}

	public String getWpdr_id() {
		return wpdr_id;
	}

	public void setWpdr_id(String wpdrId) {
		wpdr_id = wpdrId;
	}

	public String getZd_group_id() {
		return zd_group_id;
	}

	public void setZd_group_id(String zdGroupId) {
		zd_group_id = zdGroupId;
	}

	public String getDdjg_id() {
		return ddjg_id;
	}

	public void setDdjg_id(String ddjgId) {
		ddjg_id = ddjgId;
	}

	public String getDdjgdj() {
		return ddjgdj;
	}

	public void setDdjgdj(String ddjgdj) {
		this.ddjgdj = ddjgdj;
	}

	public String getDr_id() {
		return dr_id;
	}

	public void setDr_id(String drId) {
		dr_id = drId;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String deptId) {
		dept_id = deptId;
	}

	public Double getFyze() {
		return fyze;
	}

	public void setFyze(Double fyze) {
		this.fyze = fyze;
	}

	public Double getXjazfs() {
		return xjazfs;
	}

	public void setXjazfs(Double xjazfs) {
		this.xjazfs = xjazfs;
	}

	public Double getXjbzfs() {
		return xjbzfs;
	}

	public void setXjbzfs(Double xjbzfs) {
		this.xjbzfs = xjbzfs;
	}

	public Double getXjczfs() {
		return xjczfs;
	}

	public void setXjczfs(Double xjczfs) {
		this.xjczfs = xjczfs;
	}

	public Double getXjdzfs() {
		return xjdzfs;
	}

	public void setXjdzfs(Double xjdzfs) {
		this.xjdzfs = xjdzfs;
	}

	public Double getDnzhzfs() {
		return dnzhzfs;
	}

	public void setDnzhzfs(Double dnzhzfs) {
		this.dnzhzfs = dnzhzfs;
	}

	public Double getLnzhzfs() {
		return lnzhzfs;
	}

	public void setLnzhzfs(Double lnzhzfs) {
		this.lnzhzfs = lnzhzfs;
	}

	public Double getXja_lnzh() {
		return xja_lnzh;
	}

	public void setXja_lnzh(Double xjaLnzh) {
		xja_lnzh = xjaLnzh;
	}

	public Double getXjb_lnzh() {
		return xjb_lnzh;
	}

	public void setXjb_lnzh(Double xjbLnzh) {
		xjb_lnzh = xjbLnzh;
	}

	public Double getXjc_lnzh() {
		return xjc_lnzh;
	}

	public void setXjc_lnzh(Double xjcLnzh) {
		xjc_lnzh = xjcLnzh;
	}

	public Double getXjd_lnzh() {
		return xjd_lnzh;
	}

	public void setXjd_lnzh(Double xjdLnzh) {
		xjd_lnzh = xjdLnzh;
	}

	public Double getTczfs() {
		return tczfs;
	}

	public void setTczfs(Double tczfs) {
		this.tczfs = tczfs;
	}

	public Double getJzzfs() {
		return jzzfs;
	}

	public void setJzzfs(Double jzzfs) {
		this.jzzfs = jzzfs;
	}

	public Double getGbzfs() {
		return gbzfs;
	}

	public void setGbzfs(Double gbzfs) {
		this.gbzfs = gbzfs;
	}

	public Double getFjzfs() {
		return fjzfs;
	}

	public void setFjzfs(Double fjzfs) {
		this.fjzfs = fjzfs;
	}

	public Integer getYpts() {
		return ypts;
	}

	public void setYpts(Integer ypts) {
		this.ypts = ypts;
	}

	public Integer getYpws() {
		return ypws;
	}

	public void setYpws(Integer ypws) {
		this.ypws = ypws;
	}

	public Double getDtjg() {
		return dtjg;
	}

	public void setDtjg(Double dtjg) {
		this.dtjg = dtjg;
	}

	public Double getYpfy() {
		return ypfy;
	}

	public void setYpfy(Double ypfy) {
		this.ypfy = ypfy;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

}
