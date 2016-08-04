package com.wondersgroup.qyws.tjfx.service;

import java.util.List;
import java.util.Map;

import com.wondersgroup.qyws.tjfx.module.QueryCondition;
import com.wondersgroup.qyws.tjfx.module.TbParaGrade;
import com.wondersgroup.qyws.tjfx.module.TbParaHeavy;
import com.wondersgroup.util.papper.PageSet;

//汇总统计Service
public interface HztjService extends Service{
	
	public PageSet getTestList(PageSet ps,Map map);

	/**
	 * 药店分析查询
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet hosAggregateAnalysis(PageSet pageSet, QueryCondition condition);

	/**
	 * 查询药店分析参数
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet queryYdfx(PageSet pageSet, QueryCondition condition);

	/**
	 * 保存
	 * @param pageSet
	 * @param condition
	 */
	public void saveParameter(QueryCondition condition);

	/**
	 * 权重修改
	 * @param condition
	 */
	public void updateParameter(QueryCondition condition);

	/**
	 * 权重删除
	 * @param condition
	 */
	public void deleteParameter(QueryCondition condition);

	/**
	 * 增长率详细设置
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet growSetting(PageSet pageSet, QueryCondition condition);

	/**
	 * 增长率设置权重值保存
	 * @param condition
	 */
	public void saveParameterLevelTwo(QueryCondition condition);

	/**
	 * 分数设置
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet scoreSetting(PageSet pageSet, QueryCondition condition);

	/**
	 * 分值保存
	 * @param condition
	 */
	public void saveParameterLevelThree(QueryCondition condition);

	/**
	 * 处方药非处方药权重设置
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet prescriptionSetting(PageSet pageSet, QueryCondition condition);

	/**
	 * 处方药非处方药权重设置保存
	 * @param condition
	 */
	public void saveParameterPrescription(QueryCondition condition);

	/**
	 * 当年账户历年账户权重设置
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet oldAndNewSetting(PageSet pageSet, QueryCondition condition);

	/**
	 * 当年账户历年账户权重设置保存
	 * @param condition
	 */
	public void saveParameterOldAndNew(QueryCondition condition);

	/**
	 * 费用额度设置
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet amountSetting(PageSet pageSet, QueryCondition condition);

	/**
	 * 费用额设置保存
	 * @param condition
	 */
	public void saveParameterAmount(QueryCondition condition);

	/**
	 * 单次非处方购药费用占比
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet singleMedPay(PageSet pageSet, QueryCondition condition);

	/**
	 * 单次非处方购药费用占比保存
	 * @param condition
	 */
	public void saveParameterSingleMed(QueryCondition condition);

	/**
	 * 药店分析详细查询
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet queryDetail(PageSet pageSet, QueryCondition condition);

	/**
	 * 药店综合分析分析报告
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet queryReport(PageSet pageSet, QueryCondition condition);

	public List queryBasicInfo(QueryCondition condition) throws Exception;

	/**
	 * 查询kc21,kb01信息
	 * @param condition
	 * @return
	 */
	public List queryKc21Kb01Info(QueryCondition condition) throws Exception ;

	/**
	 * 药店费用等级跨越详细设置分页查询
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet medLevelSetting(PageSet pageSet, QueryCondition condition);

	/**
	 * 药店费用等级详情设置
	 * @param condition
	 */
	public void saveMedLevel(QueryCondition condition);

	/**
	 * 费用范围修改
	 * @param condition
	 * @return
	 */
	public List queryTbParaGradeById(QueryCondition condition);

	/**
	 * 保存修改的费用范围
	 * @param condition
	 */
	public void saveCostRange(QueryCondition condition);

	/**
	 * 药店等级差值设置
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet levelScoreSetting(PageSet pageSet, QueryCondition condition);

	/**
	 * 药店等级差值设置分值保存
	 * @param condition
	 */
	public void saveLevelScore(QueryCondition condition);

	/**
	 * 参保人购药排名
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet cbrRanking(PageSet pageSet, QueryCondition condition);

	/**
	 * 药店刷卡频次统计生成计划
	 * @param condition
	 */
	public void generatePlan(QueryCondition condition);

	/**
	 * 药店刷卡频次统计计划查询
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet swipeFrequencyStatistics(PageSet pageSet,QueryCondition condition);

	/**
	 * 计划查询
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet queryGenerateDetail(PageSet pageSet, QueryCondition condition);

	/**
	 * 显示明细
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet queryPlanDetail(PageSet pageSet, QueryCondition condition);

	/**
	 * 就诊明细
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet queryJzmxDetail(PageSet pageSet, QueryCondition condition);

	/**
	 * 药店等级设置查询
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet queryMedLevel(PageSet pageSet, QueryCondition condition);

	public String queryAkb021(String akb020);

	/**
	 * 查询制定计划数量
	 * @param con
	 * @return
	 */
	public List queryGeneratePlan(QueryCondition condition) throws Exception;

	/**
	 * 根据规则标识废除权重数据
	 * @param condition
	 */
	public void updateSet(QueryCondition condition);

	/**
	 * 单行新增权重保存
	 * @param condition
	 */
	public void saveSingleRow(QueryCondition condition);

	/**
	 * 历史参数查询
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet queryHistoryStatistics(PageSet pageSet, QueryCondition condition);

	/**
	 * 
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet viewGrowSetting(PageSet pageSet, QueryCondition condition);

	public PageSet viewMedLevelSetting(PageSet pageSet, QueryCondition condition);

	/**
	 * 权重修改初始化页面 读取数据
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet changeWeigth(PageSet pageSet, QueryCondition condition);

	/**
	 * 权重值修改保存（根据GZBZ修改所有关联数据）
	 * @param condition
	 */
	public void saveParameterChangeWeigth(QueryCondition condition,List<TbParaHeavy> tphList,List<TbParaGrade> tpgList);

	/**
	 * 根据GZBZ查询TbParaHeavy表下的所有有效数据
	 * @param parseLong
	 * @return
	 */
	public List<TbParaHeavy> queryAllTbParaHeavyByGzbz(long gzbz,String removeId);

	/**
	 * 根据GZBZ查询TbParaGrade表下的所有有效数据
	 * @param parseLong
	 * @return
	 */
	public List<TbParaGrade> queryAllTbParaGradeByGzbz(long gzbz);

	/**
	 * 药店费用等级费用范围修改保存
	 * @param condition
	 */
	public void saveUpdateCostRange(QueryCondition condition,List<TbParaHeavy> tphList,List<TbParaGrade> tpgList);

	/**
	 * 初始化修改页面（tab页）
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet updateScore(PageSet pageSet, QueryCondition condition);

	/**
	 * 分值保存
	 * @param condition
	 * @param tphList
	 * @param tpgList
	 */
	public String saveUpdateScore(QueryCondition condition, List<TbParaHeavy> tphList, List<TbParaGrade> tpgList);

	/**
	 * 上一年度药店等级设置（药店信息查询）
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet setYdLevel(PageSet pageSet, QueryCondition condition);

	/**
	 * 药店等级保存或修改
	 * @param queryCondition
	 */
	public void saveOrUpdYdLevel(QueryCondition queryCondition);

	/**
	 * 药店分数查询
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet queryYdFs(PageSet pageSet, QueryCondition condition);

	public PageSet queryAllQzHeavy(PageSet pageSet, QueryCondition condition);

	public PageSet queryAllQzGrade(PageSet pageSet1, QueryCondition condition);

	public void resultSetToExcel(PageSet pageSet, PageSet pageSet1,Map aaa027Map, QueryCondition condition);

	/**
	 * 药店药品使用排名（按药店查询）
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	public PageSet ydYpsyRankingYd(PageSet pageSet, QueryCondition condition);

	/**
	 * 药房与维护
	 * @param pageSet
	 * @param condition
	 * @return
	 * @author Gerald
	 */
	public PageSet pharmacyMaintenance(PageSet pageSet, QueryCondition condition);

	/**
	 * 药店总数据统计
	 * @param pageSet
	 * @param condition
	 * @author Gerald
	 * @return
	 */
	public PageSet ydTotalStatistics(PageSet pageSet, QueryCondition condition);

	/**
	 * 费用移动平均
	 * @param pageSet
	 * @param condition
	 * @author Gerald
	 * @return
	 */
	public PageSet ydMonthMove(PageSet pageSet, QueryCondition condition);

	/**
	 * 药店费用统计
	 * @param pageSet
	 * @param condition
	 * @author Gerald
	 * @return
	 */
	public PageSet ydCostStatistics(PageSet pageSet, QueryCondition condition);

	/**
	 * 分离要素排名
	 * @param pageSet
	 * @param condition
	 * @author Gerald
	 * @return
	 */
	public PageSet ydCostRanking(PageSet pageSet, QueryCondition condition);

	/**
	 * 药店月平均费用
	 * @param pageSet
	 * @param condition
	 * @author Gerald
	 * @return
	 */
	public PageSet ydMonthCostRanking(PageSet pageSet, QueryCondition condition);

	/**
	 * 医生开外配处方次数、费用排名（按药店查询）
	 * @param pageSet
	 * @param condition
	 * @author Gerald
	 * @return
	 */
	public PageSet docYdWpcfRanking(PageSet pageSet, QueryCondition condition);

	/**
	 * 医生开外配处方次数、费用排名（总的排名）
	 * @param pageSet
	 * @param condition
	 * @author Gerald
	 * @return
	 */
	public PageSet docWpcfRanking(PageSet pageSet, QueryCondition condition);
}
