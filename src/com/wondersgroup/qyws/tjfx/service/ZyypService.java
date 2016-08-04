package com.wondersgroup.qyws.tjfx.service;

import java.util.List;

import com.wondersgroup.qyws.tjfx.module.QueryCondition;
import com.wondersgroup.util.papper.PageSet;


public interface ZyypService extends Service{

	/**
	 * 查询医疗机构信息
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	PageSet queryHosInfo(PageSet pageSet, QueryCondition condition);

	/**
	 * 中药饮片总量统计
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	PageSet totalStatistics(PageSet pageSet, QueryCondition condition);

	/**
	 * 导出Excel
	 * @param rs
	 * @param top
	 * @param string
	 * @param string2
	 */
	void resultSetToExcel(List<Object[]> rs, String[] top, String string, String string2);

	/**
	 * 医师选择
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	PageSet queryDoctorInfo(PageSet pageSet, QueryCondition condition);

	/**
	 * 单个医生的中药饮片费用及贴均价格查询
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	PageSet querySingleDoctor(PageSet pageSet, QueryCondition condition);

	/**
	 * 单个医师导出Excel
	 * @param rs
	 * @param top
	 * @param string
	 * @param string2
	 */
	void resultSetToExcelDoctor(List<Object[]> rs, String[] top, String string, String string2);

	/**
	 * 单贴价格与味数查询逐级下帖
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	PageSet singleCostQuery(PageSet pageSet, QueryCondition condition);

	/**
	 * 单贴价格与味数查询显示明细
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	PageSet singleCostQueryDetail(PageSet pageSet, QueryCondition condition);

	/**
	 * 查看费用明细
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	PageSet queryCostDetail(PageSet pageSet, QueryCondition condition);

	/**
	 * 医疗机构及药店中药饮片分人员总量统计
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	PageSet personTotalStatistics(PageSet pageSet, QueryCondition condition);

	/**
	 * 医疗机构及药店特定中药饮片统计数据查询
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	PageSet totalStatisticsQuery(PageSet pageSet, QueryCondition condition);

	/**
	 * 医生特定中药饮片统计数据查询
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	PageSet doctorTotalStatisticsQuery(PageSet pageSet, QueryCondition condition);

	/**
	 * 单个医生人均总费用统计
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	PageSet singleDoctorStatisticsQuery(PageSet pageSet,QueryCondition condition);

	/**
	 * 医疗机构及药店中药饮片分人员总量统计导出EXCEL
	 * @param rs
	 * @param top
	 * @param string
	 * @param string2
	 */
	void resultSetToExcelPersonStatics(List<Object[]> rs, String[] top, String string, String string2);

}
