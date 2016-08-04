package com.wondersgroup.qyws.tjfx.service;

import java.util.List;

import com.wondersgroup.qyws.tjfx.module.QueryCondition;
import com.wondersgroup.util.papper.PageSet;

public interface CbrService extends Service{

	/**
	 * 平均就诊费用和就诊次数查询
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	PageSet averageCostQuery(PageSet pageSet, QueryCondition condition);

	/**
	 * 异常就医人员筛选
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	PageSet queryChooseExceptionPerson(PageSet pageSet, QueryCondition condition);

	/**
	 * 根据区县查询日常参数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	List chooseExceptionPerson(QueryCondition condition) throws Exception;

	/**
	 * 参保人日常查询条件保存
	 * @param condition
	 */
	void saveRcQueryCondition(QueryCondition condition);

	/**
	 * 待查人员查询
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	PageSet queryPerson(PageSet pageSet, QueryCondition condition);

	/**
	 * 个人分析报告
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	PageSet queryPersonReport(PageSet pageSet, QueryCondition condition);

	/**
	 * 批量加入黑/红名单
	 * @param queryCondition
	 */
	void beathAddList(QueryCondition queryCondition);

	/**
	 * 就诊信息查询
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	PageSet queryJzxx(PageSet pageSet, QueryCondition condition);

	/**
	 * 费用明细查询
	 * @param pageSet
	 * @param condition
	 * @return
	 */
	PageSet queryFymx(PageSet pageSet, QueryCondition condition);

}
