package com.wondersgroup.qyws.tjfx.module;

import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("unchecked")
public class QueryCondition {
	private String aaa027;//统筹区编码
	private String akb020;//药店编码
	private Integer year;//统计年度
	private String yearMonth;//年月
	private String quarter;//季度
	private String byYear;
	private String byMonth;
	private String byQuarter;
	private String type;
	private String startDate;
	private String endDate;
	private String hosIds; //医院IDS
	private String ydIds; //药店IDS
	private String hosNames;
	private String ydNames;
	private String akb021;//医疗机构名称
	private String akb022;//机构类别
	private String aka130;//就诊类型
	private String akc021;//人员类别
	private String docId;//医师编号
	private String docName;//医师姓名
	private String aaz263;// 医护人员编号
	private String aac003;// 姓名
	private String tcdm; //统筹代码
	private String qzm;//权重名
	private String qzz;//权重值
	private String id;//id
	private String qzdm;//权重代码
	private String divId;//页签
	private String aaz217;
	private String fs;//得分
	private String pm;//排名
	private Double dnzh;//非处方药当年账户
	private Double lnzh;//非处方药历年账户
	private Double dnzb;//非处方药当年账户占比
	private Double lnzb;//非处方药历年账户占比
	private String aab013;//法人代表
	private String aae006;//地址
	private Integer pmsl;//排名数量
	private String medlev;//药店等级
	private String fcfName;//非处方药分类名称
	private String fcfValue;//非处方药费用
	private String cfName;//处方药分类名称
	private String cfValue;//处方药费用
	private String tid;//处方药ID
	private String sxz;//上限值（非处方）
	private String xxz;//下限值（非处方）
	private String tsxz;//上限值（处方）
	private String txxz;//下限值（处方）
	private String jbdm;//级别代码
	private String qzjb;//权重级别
	private String ysxm;//医生姓名
	private String aac001;//
	private String aaz370;
	private String akc050;
	private String akc194;
	private Double zfy;
	private String byZrYear;//自然年度
	private String byYbYear;//医保年度
	private String px;//排序方式
	private String orderColumn;//排序字段
	private String startTime;//开始时间（时分秒）
	private String endTime;//结束时间（时分秒）
	private Integer sjjg;//时间间隔
	private String sjlb;//时间类别
	private String pc;//频次
	private String pjcs;//平均次数
	private String pjfy;//平均费用
	private String sign;//标记
	private String rc_pjfy;//日常-平均费用
	private String rc_pjcs;//日常-平均次数
	private String rc_fyze;//日常-费用总额
	private String rc_cszs;//日常-次数总数
	
	private ArrayList data1;
	private ArrayList data2;
	private ArrayList data3;
	private ArrayList data4;
	
	private String colorList;//颜色名单
	private String aac002;//社会保障号码
	private String aac004;//性别
	private String aka130Names;//就诊类型
	private long gzbz; //规则标识（各区县某一套规则权重得分的统一标识）
	private String hhmdbz;//红黑名单标识（1红名单2黑名单）
	private String bz;//备注
	private String nl;//年龄
	private Date kssj;
	private Date jssj;
	private String yddj;//药店等级
	private Double dtjgStart; //单贴价格开始区间
	private Double dtjgEnd; //单贴价格结束区间
	private Double wsStart; //味数开始区间
	private Double wsEnd; //味数结束区间
	private String tym;//通用名
	private String zgzs;//资格证书编码
	private String wpddjg_id;//外配医疗机构ID
	private String wpysxm;//外配医师姓名
	private String jylsh;//就医流水号
	private String mxzdh;//明细账单号
	private String yplx;//药品类型
	private String jyaaa027;
	
	private String valid_flag;
	
	public String getYplx() {
		return yplx;
	}

	public void setYplx(String yplx) {
		this.yplx = yplx;
	}

	public String getJylsh() {
		return jylsh;
	}

	public void setJylsh(String jylsh) {
		this.jylsh = jylsh;
	}

	public String getMxzdh() {
		return mxzdh;
	}

	public void setMxzdh(String mxzdh) {
		this.mxzdh = mxzdh;
	}

	public String getWpddjg_id() {
		return wpddjg_id;
	}

	public void setWpddjg_id(String wpddjgId) {
		wpddjg_id = wpddjgId;
	}

	public String getWpysxm() {
		return wpysxm;
	}

	public void setWpysxm(String wpysxm) {
		this.wpysxm = wpysxm;
	}

	public String getZgzs() {
		return zgzs;
	}

	public void setZgzs(String zgzs) {
		this.zgzs = zgzs;
	}

	public String getTym() {
		return tym;
	}

	public void setTym(String tym) {
		this.tym = tym;
	}

	public Double getDtjgStart() {
		return dtjgStart;
	}

	public void setDtjgStart(Double dtjgStart) {
		this.dtjgStart = dtjgStart;
	}

	public Double getDtjgEnd() {
		return dtjgEnd;
	}

	public void setDtjgEnd(Double dtjgEnd) {
		this.dtjgEnd = dtjgEnd;
	}

	public Double getWsStart() {
		return wsStart;
	}

	public void setWsStart(Double wsStart) {
		this.wsStart = wsStart;
	}

	public Double getWsEnd() {
		return wsEnd;
	}

	public void setWsEnd(Double wsEnd) {
		this.wsEnd = wsEnd;
	}

	public String getYddj() {
		return yddj;
	}

	public void setYddj(String yddj) {
		this.yddj = yddj;
	}

	public Date getKssj() {
		return kssj;
	}

	public void setKssj(Date kssj) {
		this.kssj = kssj;
	}

	public Date getJssj() {
		return jssj;
	}

	public void setJssj(Date jssj) {
		this.jssj = jssj;
	}

	public String getNl() {
		return nl;
	}

	public void setNl(String nl) {
		this.nl = nl;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getHhmdbz() {
		return hhmdbz;
	}

	public void setHhmdbz(String hhmdbz) {
		this.hhmdbz = hhmdbz;
	}

	public long getGzbz() {
		return gzbz;
	}

	public void setGzbz(long gzbz) {
		this.gzbz = gzbz;
	}

	public String getAka130Names() {
		return aka130Names;
	}

	public void setAka130Names(String aka130Names) {
		this.aka130Names = aka130Names;
	}

	public String getAac002() {
		return aac002;
	}

	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}

	public String getAac004() {
		return aac004;
	}

	public void setAac004(String aac004) {
		this.aac004 = aac004;
	}

	public String getColorList() {
		return colorList;
	}

	public void setColorList(String colorList) {
		this.colorList = colorList;
	}

	public String getJbdm() {
		return jbdm;
	}

	public void setJbdm(String jbdm) {
		this.jbdm = jbdm;
	}

	public ArrayList getData1() {
		return data1;
	}

	public void setData1(ArrayList data1) {
		this.data1 = data1;
	}

	public ArrayList getData2() {
		return data2;
	}

	public void setData2(ArrayList data2) {
		this.data2 = data2;
	}

	public ArrayList getData3() {
		return data3;
	}

	public void setData3(ArrayList data3) {
		this.data3 = data3;
	}

	public ArrayList getData4() {
		return data4;
	}

	public void setData4(ArrayList data4) {
		this.data4 = data4;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getRc_pjfy() {
		return rc_pjfy;
	}

	public void setRc_pjfy(String rcPjfy) {
		rc_pjfy = rcPjfy;
	}

	public String getRc_pjcs() {
		return rc_pjcs;
	}

	public void setRc_pjcs(String rcPjcs) {
		rc_pjcs = rcPjcs;
	}

	public String getRc_fyze() {
		return rc_fyze;
	}

	public void setRc_fyze(String rcFyze) {
		rc_fyze = rcFyze;
	}

	public String getRc_cszs() {
		return rc_cszs;
	}

	public void setRc_cszs(String rcCszs) {
		rc_cszs = rcCszs;
	}

	public String getPjcs() {
		return pjcs;
	}

	public void setPjcs(String pjcs) {
		this.pjcs = pjcs;
	}

	public String getPjfy() {
		return pjfy;
	}

	public void setPjfy(String pjfy) {
		this.pjfy = pjfy;
	}

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public Integer getSjjg() {
		return sjjg;
	}

	public void setSjjg(Integer sjjg) {
		this.sjjg = sjjg;
	}

	public String getSjlb() {
		return sjlb;
	}

	public void setSjlb(String sjlb) {
		this.sjlb = sjlb;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getPx() {
		return px;
	}

	public void setPx(String px) {
		this.px = px;
	}

	public String getByZrYear() {
		return byZrYear;
	}

	public void setByZrYear(String byZrYear) {
		this.byZrYear = byZrYear;
	}

	public String getByYbYear() {
		return byYbYear;
	}

	public void setByYbYear(String byYbYear) {
		this.byYbYear = byYbYear;
	}

	public String getYsxm() {
		return ysxm;
	}

	public void setYsxm(String ysxm) {
		this.ysxm = ysxm;
	}

	public String getAac001() {
		return aac001;
	}

	public void setAac001(String aac001) {
		this.aac001 = aac001;
	}

	public String getAaz370() {
		return aaz370;
	}

	public void setAaz370(String aaz370) {
		this.aaz370 = aaz370;
	}

	public String getAkc050() {
		return akc050;
	}

	public void setAkc050(String akc050) {
		this.akc050 = akc050;
	}

	public String getAkc194() {
		return akc194;
	}

	public void setAkc194(String akc194) {
		this.akc194 = akc194;
	}

	public Double getZfy() {
		return zfy;
	}

	public void setZfy(Double zfy) {
		this.zfy = zfy;
	}

	public String getQzjb() {
		return qzjb;
	}

	public void setQzjb(String qzjb) {
		this.qzjb = qzjb;
	}

	public String getSxz() {
		return sxz;
	}

	public void setSxz(String sxz) {
		this.sxz = sxz;
	}

	public String getXxz() {
		return xxz;
	}

	public void setXxz(String xxz) {
		this.xxz = xxz;
	}

	public String getTsxz() {
		return tsxz;
	}

	public void setTsxz(String tsxz) {
		this.tsxz = tsxz;
	}

	public String getTxxz() {
		return txxz;
	}

	public void setTxxz(String txxz) {
		this.txxz = txxz;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getMedlev() {
		return medlev;
	}

	public void setMedlev(String medlev) {
		this.medlev = medlev;
	}

	public String getFcfName() {
		return fcfName;
	}

	public void setFcfName(String fcfName) {
		this.fcfName = fcfName;
	}

	public String getFcfValue() {
		return fcfValue;
	}

	public void setFcfValue(String fcfValue) {
		this.fcfValue = fcfValue;
	}

	public String getCfName() {
		return cfName;
	}

	public void setCfName(String cfName) {
		this.cfName = cfName;
	}

	public String getCfValue() {
		return cfValue;
	}

	public void setCfValue(String cfValue) {
		this.cfValue = cfValue;
	}

	public Integer getPmsl() {
		return pmsl;
	}

	public void setPmsl(Integer pmsl) {
		this.pmsl = pmsl;
	}

	public String getAab013() {
		return aab013;
	}

	public void setAab013(String aab013) {
		this.aab013 = aab013;
	}

	public String getAae006() {
		return aae006;
	}

	public void setAae006(String aae006) {
		this.aae006 = aae006;
	}

	public Double getDnzb() {
		return dnzb;
	}

	public void setDnzb(Double dnzb) {
		this.dnzb = dnzb;
	}

	public Double getLnzb() {
		return lnzb;
	}

	public void setLnzb(Double lnzb) {
		this.lnzb = lnzb;
	}

	public Double getDnzh() {
		return dnzh;
	}

	public void setDnzh(Double dnzh) {
		this.dnzh = dnzh;
	}

	public Double getLnzh() {
		return lnzh;
	}

	public void setLnzh(Double lnzh) {
		this.lnzh = lnzh;
	}

	public String getFs() {
		return fs;
	}

	public void setFs(String fs) {
		this.fs = fs;
	}

	public String getPm() {
		return pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	public String getAaz217() {
		return aaz217;
	}

	public void setAaz217(String aaz217) {
		this.aaz217 = aaz217;
	}

	public String getDivId() {
		return divId;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	public String getQzdm() {
		return qzdm;
	}

	public void setQzdm(String qzdm) {
		this.qzdm = qzdm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQzm() {
		return qzm;
	}

	public void setQzm(String qzm) {
		this.qzm = qzm;
	}

	public String getQzz() {
		return qzz;
	}

	public void setQzz(String qzz) {
		this.qzz = qzz;
	}

	public String getTcdm() {
		return tcdm;
	}

	public void setTcdm(String tcdm) {
		this.tcdm = tcdm;
	}

	public String getAaz263() {
		return aaz263;
	}

	public void setAaz263(String aaz263) {
		this.aaz263 = aaz263;
	}

	public String getAac003() {
		return aac003;
	}

	public void setAac003(String aac003) {
		this.aac003 = aac003;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getAkc021() {
		return akc021;
	}

	public void setAkc021(String akc021) {
		this.akc021 = akc021;
	}

	public String getAka130() {
		return aka130;
	}

	public void setAka130(String aka130) {
		this.aka130 = aka130;
	}

	public String getAkb021() {
		return akb021;
	}

	public void setAkb021(String akb021) {
		this.akb021 = akb021;
	}

	public String getAkb022() {
		return akb022;
	}

	public void setAkb022(String akb022) {
		this.akb022 = akb022;
	}

	public String getHosNames() {
		return hosNames;
	}

	public void setHosNames(String hosNames) {
		this.hosNames = hosNames;
	}

	public String getYdNames() {
		return ydNames;
	}

	public void setYdNames(String ydNames) {
		this.ydNames = ydNames;
	}

	public String getHosIds() {
		return hosIds;
	}

	public void setHosIds(String hosIds) {
		this.hosIds = hosIds;
	}

	public String getYdIds() {
		return ydIds;
	}

	public void setYdIds(String ydIds) {
		this.ydIds = ydIds;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getByMonth() {
		return byMonth;
	}

	public void setByMonth(String byMonth) {
		this.byMonth = byMonth;
	}

	public String getByQuarter() {
		return byQuarter;
	}

	public void setByQuarter(String byQuarter) {
		this.byQuarter = byQuarter;
	}

	public String getByYear() {
		return byYear;
	}

	public void setByYear(String byYear) {
		this.byYear = byYear;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getAkb020() {
		return akb020;
	}

	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getAaa027() {
		return aaa027;
	}

	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}

	public String getValid_flag() {
		return valid_flag;
	}

	public void setValid_flag(String valid_flag) {
		this.valid_flag = valid_flag;
	}

	public String getJyaaa027() {
		return jyaaa027;
	}

	public void setJyaaa027(String jyaaa027) {
		this.jyaaa027 = jyaaa027;
	}

	
}
