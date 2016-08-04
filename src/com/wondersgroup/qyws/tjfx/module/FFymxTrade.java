package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 费用明细表
 * 
 * @author Administrator
 * 
 */
public class FFymxTrade implements Serializable {
	private static final long serialVersionUID = 1L;
	private String jylsh;// 交易流水号
	private String jzbh;// 就诊编号
	private String mxzdh;// 明细账单号
	private String xh;// 序号
	private String jyjslb;// 交易结算类别
	private String ybh;// 医保号
	private String zhh;// 账户号
	private String rylb;// 人员类别
	private String ryzlb;// 人员子类别
	private String dylb;// 待遇类别
	private String xzlb;// 险种类别(01=职工、02=统筹、03=城镇居民、04=城乡居民)
	private String cbxzqh;// 参保行政区划
	private String jyqhdm;// 就医行政区划
	private Double date_id;// 日期Id
	private Date mxfssj;// 明细发生时间
	private Date trade_time;// 交易时间
	private String takeout_prescrip_id;// 外配处方门急诊就诊编号
	private String wpddjg_id;// 外配处方医疗机构
	private String wpdr_id;// 外配处方医师
	private String ddjg_id;// 定点机构ID
	private String dr_id;// 医师ID
	private String mx_id;// 费用明细ID
	private String mx_mc;// 费用明细名称
	private String tym_mc;// 通用名名称
	private Double zlbl;// 自理比例
	private Double mcyl;// 每次用量
	private String yldw;// 用量单位
	private String yfbz;// 用法标准
	private String bzgg;// 包装规格
	private Double sl;// 数量
	private Double dj;// 单价
	private Double ypts;// 贴数
	private Double ypws;// 单贴味数
	private Double dtjg;// 单贴价格
	private Double fyze;// 总费用
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

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
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

	public Date getMxfssj() {
		return mxfssj;
	}

	public void setMxfssj(Date mxfssj) {
		this.mxfssj = mxfssj;
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

	public String getDdjg_id() {
		return ddjg_id;
	}

	public void setDdjg_id(String ddjgId) {
		ddjg_id = ddjgId;
	}

	public String getDr_id() {
		return dr_id;
	}

	public void setDr_id(String drId) {
		dr_id = drId;
	}

	public String getMx_id() {
		return mx_id;
	}

	public void setMx_id(String mxId) {
		mx_id = mxId;
	}

	public String getMx_mc() {
		return mx_mc;
	}

	public void setMx_mc(String mxMc) {
		mx_mc = mxMc;
	}

	public String getTym_mc() {
		return tym_mc;
	}

	public void setTym_mc(String tymMc) {
		tym_mc = tymMc;
	}

	public Double getZlbl() {
		return zlbl;
	}

	public void setZlbl(Double zlbl) {
		this.zlbl = zlbl;
	}

	public Double getMcyl() {
		return mcyl;
	}

	public void setMcyl(Double mcyl) {
		this.mcyl = mcyl;
	}

	public String getYldw() {
		return yldw;
	}

	public void setYldw(String yldw) {
		this.yldw = yldw;
	}

	public String getYfbz() {
		return yfbz;
	}

	public void setYfbz(String yfbz) {
		this.yfbz = yfbz;
	}

	public String getBzgg() {
		return bzgg;
	}

	public void setBzgg(String bzgg) {
		this.bzgg = bzgg;
	}

	public Double getSl() {
		return sl;
	}

	public void setSl(Double sl) {
		this.sl = sl;
	}

	public Double getDj() {
		return dj;
	}

	public void setDj(Double dj) {
		this.dj = dj;
	}

	public Double getYpts() {
		return ypts;
	}

	public void setYpts(Double ypts) {
		this.ypts = ypts;
	}

	public Double getYpws() {
		return ypws;
	}

	public void setYpws(Double ypws) {
		this.ypws = ypws;
	}

	public Double getDtjg() {
		return dtjg;
	}

	public void setDtjg(Double dtjg) {
		this.dtjg = dtjg;
	}

	public Double getFyze() {
		return fyze;
	}

	public void setFyze(Double fyze) {
		this.fyze = fyze;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

}
