package com.wondersgroup.qyws.tjfx.service;

import java.util.List;

import com.wondersgroup.qyws.tjfx.module.QueryCondition;
import com.wondersgroup.util.papper.PageSet;



public interface YdfxService extends Service{

	void resultSetToExcelDoctor(List<Object[]> rs, String[] top, String string,
			String string2);

	PageSet cbrRanking(PageSet pageSet, QueryCondition condition);

	PageSet ydTotalStatistics(PageSet pageSet, QueryCondition condition);

	String resultSetToExcelYdTotalStatistics(List<Object[]> rs, String[] top,
			String string, String string2);

	PageSet ydMonthMove(PageSet pageSet, QueryCondition condition);

	void exportExcelYdMonthMove(List<Object[]> rs, String[] top, String string,
			String string2);

	void exportExcelYdCostStatistics(List<Object[]> rs, String[] top,
			String string, String string2);

	PageSet ydCostStatistics(PageSet pageSet, QueryCondition condition);

	void exportExcelYdCostRanking(List<Object[]> rs, String[] top,
			String string, String string2);

	PageSet ydCostRanking(PageSet pageSet, QueryCondition condition);

	PageSet ydMonthCostRanking(PageSet pageSet, QueryCondition condition);

	void exportExcelYdMonthCostRanking(List<Object[]> rs, String[] top,
			String string, String string2);

	PageSet docWpcfRanking(PageSet pageSet, QueryCondition condition);

	void exportExcelDocWpcfRanking(List<Object[]> rs, String[] top,
			String string, String string2);

	PageSet docYdWpcfRanking(PageSet pageSet, QueryCondition condition);

	void exportExcelDocYdWpcfRanking(List<Object[]> rs, String[] top,
			String string, String string2);

	PageSet docWpcfDetail(PageSet pageSet, QueryCondition condition);

	PageSet hosAggregateAnalysis(PageSet pageSet, QueryCondition condition);



}
