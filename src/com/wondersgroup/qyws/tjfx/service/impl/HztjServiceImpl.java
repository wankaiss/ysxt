package com.wondersgroup.qyws.tjfx.service.impl;

import java.net.URLEncoder;
import java.nio.charset.CodingErrorAction;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.write.DateFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.wonders.pdcdc.common.pagination.PaginationRequest;
import com.wonders.pdcdc.common.pagination.PaginationResult;
import com.wondersgroup.qyws.tjfx.module.QueryCondition;
import com.wondersgroup.qyws.tjfx.module.TbJobSchedule;
import com.wondersgroup.qyws.tjfx.module.TbParaGrade;
import com.wondersgroup.qyws.tjfx.module.TbParaHeavy;
import com.wondersgroup.qyws.tjfx.module.TjYdLevel;
import com.wondersgroup.qyws.tjfx.service.HztjService;
import com.wondersgroup.qyws.tjfx.util.GlobalConstants;
import com.wondersgroup.util.papper.PageSet;
	
//汇总统计Service实现类
@Service("HztjServiceImplService")
@Transactional
public class HztjServiceImpl extends ServiceImpl implements HztjService{
	
	public PageSet getTestList(PageSet ps,Map map)
	{
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append("select 1 as ddje, 2 as psj,3 as rzje,4 as rkje,4 as wczfje,6 as thje,7 as ttt,2 as type,'201405' as month from dual ");

		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(ps,exeSQL.toString(),countSQL.toString());
		
	}

	public  PaginationResult getTestList(Map map,PaginationRequest pr)
	{
		try{
			StringBuffer exeSQL=new StringBuffer();
			StringBuffer countSQL=new StringBuffer();
					
			exeSQL.append("select 1 as ddje, 2 as psj,3 as rzje,4 as rkje,4 as wczfje,6 as thje,7 as ttt,2 as type,'201405' as month from dual ");
	
			countSQL.append("select count(*) from (");
			countSQL.append(exeSQL.toString());
			countSQL.append(" )");
			
			List list = this.baseDao.getListBySql(pr.getStartRow(),pr.getPageSize(),exeSQL.toString());
			int pagesize = baseDao.getListSizeBySql(countSQL.toString());
			
			return new PaginationResult(pagesize, list, pr);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return new PaginationResult(0, new ArrayList(), pr);
	}

	/**
	 * 药店分析查询
	 */
	public PageSet hosAggregateAnalysis(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append(" select distinct b.akb021,b.akb020,b.aaa027,b.aka101, ");//,b.akb023
		exeSQL.append("(select sum(c.tjz) from TB_STATS_INSTITUTION_INDICATOR c where a.jgid=c.jgid and a.sjdm=c.sjdm and c.zbid='ZB000001' and c.lbid='LB000001') as cf,");
		exeSQL.append("(select sum(c.tjz) from TB_STATS_INSTITUTION_INDICATOR c where a.jgid=c.jgid and a.sjdm=c.sjdm and c.zbid='ZB000002' and c.lbid='LB000001') as cffy,");
		exeSQL.append("(select sum(c.tjz) from TB_STATS_INSTITUTION_INDICATOR c where a.jgid=c.jgid and a.sjdm=c.sjdm and c.zbid='ZB000003' and c.lbid='LB000001') as fcf,");
		exeSQL.append("(select sum(c.tjz) from TB_STATS_INSTITUTION_INDICATOR c where a.jgid=c.jgid and a.sjdm=c.sjdm and c.zbid='ZB000004' and c.lbid='LB000001') as fcffy ");
		exeSQL.append(",(select m.tjz from (select max(c.tjz) tjz,max(xgsj),jgid,sjdm from TB_STATS_INSTITUTION_INDICATOR c where c.zbid='ZB000015' and c.lbid<>'LB000001' ");
		if(StringUtils.isNotEmpty(condition.getType())){
			if(condition.getType().equals("2")){
				//按月份查询
				exeSQL.append(" and c.sjdm = '"+condition.getByMonth()+"'");
			}else{
				//按季度查询
				String qu = condition.getByQuarter();
				String year = qu.substring(0, 4);
				exeSQL.append(" and c.sjdm = '"+year+"S"+qu.substring(5, 6)+"'");
			}
		}
		exeSQL.append(" group by c.jgid,c.sjdm) m where a.jgid=m.jgid and a.sjdm=m.sjdm) as fs ");
		exeSQL.append(",dense_rank() over(order by (select m.tjz from (select max(c.tjz) tjz,max(xgsj),jgid,sjdm from TB_STATS_INSTITUTION_INDICATOR c where c.zbid='ZB000015' and c.lbid<>'LB000001' ");
		if(StringUtils.isNotEmpty(condition.getType())){
			if(condition.getType().equals("2")){
				//按月份查询
				exeSQL.append(" and c.sjdm = '"+condition.getByMonth()+"'");
			}else{
				//按季度查询
				String qu = condition.getByQuarter();
				String year = qu.substring(0, 4);
				exeSQL.append(" and c.sjdm = '"+year+"S"+qu.substring(5, 6)+"'");
			}
		}
		exeSQL.append(" group by c.jgid,c.sjdm) m where a.jgid=m.jgid and a.sjdm=m.sjdm) desc) as pm ");
		exeSQL.append(" from TB_STATS_INSTITUTION_INDICATOR a,kb01 b where a.jgid = b.akb020 and b.akb022='2' and yxbz = '1' ");
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQL.append(" and b.aaa027 = '"+condition.getAaa027()+"'");
		}
		if(StringUtils.isNotEmpty(condition.getType())){
			if(condition.getType().equals("2")){
				//按月份查询
				exeSQL.append(" and a.sjdm = '"+condition.getByMonth()+"'");
			}else{
				//按季度查询
				String qu = condition.getByQuarter();
				String year = qu.substring(0, 4);
				exeSQL.append(" and a.sjdm = '"+year+"S"+qu.substring(5, 6)+"'");
			}
		}

		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 查询药店分析参数
	 */
	public PageSet queryYdfx(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append("select id,qzm,qzdm,tcdm,qzz,gzbz from TB_PARA_HEAVY where qzjb = '"+GlobalConstants.DIC_QZJB_1+"' and yxbz = '"+GlobalConstants.DIC_YXBZ_1+"' ");
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQL.append(" and tcdm = '"+condition.getAaa027()+"' ");
		}
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		exeSQL.append(" order by id asc ");
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 保存
	 */
	public void saveParameter(QueryCondition condition) {
		//先将该统筹区下的所有一级节点数据变为无效
		String s = "update TB_PARA_HEAVY set yxbz='"+GlobalConstants.DIC_YXBZ_2+"' where tcdm = '"+condition.getTcdm()+"' and qzjb='"+GlobalConstants.DIC_QZJB_1+"' and SJQZID = '0' and yxbz='"+GlobalConstants.DIC_YXBZ_1+"' ";
		this.baseDao.executeUpdateBySql(s);
		String[] tempQzm = condition.getQzm().split(",");
		String[] tempQzz = condition.getQzz().split(",");
		String tempid = "";
		int m = 0;
		String gzbz = this.baseDao.getSequenceValue("SEQ_GZBZ");
		for(int i=0;i<tempQzm.length;i++){
			m = 1000*(i+1);
			TbParaHeavy tph = new TbParaHeavy();
			String id = this.baseDao.getSequenceValue("SEQ_PARA_HEAVY");
			if(tempQzm[i].trim().equals("药店费用等级跨越")){
				tempid = id;
			}
			tph.setID(Long.parseLong(id));
			tph.setQZM(tempQzm[i].trim());//权重名
			tph.setQZZ(Double.parseDouble(tempQzz[i]));//权重值
			tph.setQZDM(""+m);//权重代码
			tph.setTCDM(condition.getTcdm());//统筹代码
			tph.setQZJB(GlobalConstants.DIC_QZJB_1);//权重级别
			tph.setSJQZID("0");//上级权重ID
			tph.setYXBZ(GlobalConstants.DIC_YXBZ_1);//有效标志
			tph.setXGSJ(new Date());//修改时间
			tph.setGZBZ(Long.parseLong(gzbz));//规则标识
			this.baseDao.saveObject(tph);
		}
	}

	/**
	 * 权重修改
	 */
	public void updateParameter(QueryCondition condition) {
		//根据主键获取实体类数据
		TbParaHeavy tph = (TbParaHeavy)this.baseDao.getObjectById(TbParaHeavy.class, Long.parseLong(condition.getId()));
		tph.setQZZ(Double.parseDouble(condition.getQzz()));
		tph.setXGSJ(new Date());//修改时间
		this.baseDao.saveObject(tph);
	}

	/**
	 * 权重删除
	 */
	public void deleteParameter(QueryCondition condition) {
		//根据主键获取实体类数据
		TbParaHeavy tph = (TbParaHeavy)this.baseDao.getObjectById(TbParaHeavy.class, Long.parseLong(condition.getId()));
		this.baseDao.deleteEntity(tph);
	}

	/**
	 * 增长率详细设置
	 */
	public PageSet growSetting(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append(" select id,qzm,qzdm,qzjb,sjqzid,tcdm,qzz from tb_para_heavy where qzjb = '"+GlobalConstants.DIC_QZJB_2+"' and yxbz = '"+GlobalConstants.DIC_YXBZ_1+"'");
		if(StringUtils.isNotEmpty(condition.getTcdm())){
			exeSQL.append(" and tcdm = '"+condition.getTcdm()+"' ");
		}
		if(StringUtils.isNotEmpty(condition.getQzdm())){
			exeSQL.append(" and SJQZID = '"+condition.getQzdm()+"' ");
		}
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 增长率设置权重值保存
	 */
	public void saveParameterLevelTwo(QueryCondition condition) {
		//先将该统筹区下的所有二级节点数据变为无效
		String s = "update TB_PARA_HEAVY set yxbz='"+GlobalConstants.DIC_YXBZ_2+"' where tcdm = '"+condition.getTcdm()+"' and qzjb='"+GlobalConstants.DIC_QZJB_2+"' and SJQZID = '"+condition.getQzdm()+"' and yxbz='"+GlobalConstants.DIC_YXBZ_1+"' ";
		this.baseDao.executeUpdateBySql(s);
		String[] tempQzm = condition.getQzm().split(",");
		String[] tempQzz = condition.getQzz().split(",");
		int m = Integer.parseInt(condition.getQzdm());
		for(int i=0;i<tempQzm.length;i++){
			m++;
			TbParaHeavy tph = new TbParaHeavy();
			String id = this.baseDao.getSequenceValue("SEQ_PARA_HEAVY");
			tph.setID(Long.parseLong(id));
			tph.setQZM(tempQzm[i]);//权重名
			tph.setQZZ(Double.parseDouble(tempQzz[i]));//权重值
			tph.setQZDM(""+m);//权重代码
			tph.setTCDM(condition.getTcdm());//统筹代码
			tph.setQZJB(GlobalConstants.DIC_QZJB_2);//权重级别
			tph.setSJQZID(condition.getQzdm());//上级权重ID
			tph.setYXBZ(GlobalConstants.DIC_YXBZ_1);//有效标志
			tph.setXGSJ(new Date());//修改时间
			tph.setGZBZ(condition.getGzbz());//规则标识
			this.baseDao.saveObject(tph);
		}
	}

	/**
	 * 分数设置
	 */
	public PageSet scoreSetting(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append(" select id,qzm,qzdm,qzjb,sjqzid,tcdm,qzz from tb_para_heavy where qzjb = '"+GlobalConstants.DIC_QZJB_3+"' and yxbz = '"+GlobalConstants.DIC_YXBZ_1+"' ");
		if(StringUtils.isNotEmpty(condition.getQzdm())){
			exeSQL.append(" and SJQZID = '"+condition.getQzdm()+"' ");
		}
		if(StringUtils.isNotEmpty(condition.getTcdm())){
			exeSQL.append(" and tcdm = '"+condition.getTcdm()+"' ");
		}
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 分值保存
	 */
	public void saveParameterLevelThree(QueryCondition condition) {
		//先将该统筹区下的所有三级节点数据设为无效
		String s = "update TB_PARA_HEAVY set yxbz='"+GlobalConstants.DIC_YXBZ_2+"' where tcdm = '"+condition.getTcdm()+"' and qzjb='"+GlobalConstants.DIC_QZJB_3+"' and SJQZID = '"+condition.getQzdm()+"' and yxbz='"+GlobalConstants.DIC_YXBZ_1+"' ";
		this.baseDao.executeUpdateBySql(s);
		String[] tempQzm = condition.getQzm().split(",");
		String[] tempQzz = condition.getQzz().split(",");
		int m = Integer.parseInt(condition.getQzdm());
		int n = 1000;
		for(int i=0;i<tempQzm.length;i++){
			n++;
			TbParaHeavy tph = new TbParaHeavy();
			String id = this.baseDao.getSequenceValue("SEQ_PARA_HEAVY");
			tph.setID(Long.parseLong(id));
			tph.setQZM(tempQzm[i]);//权重名
			tph.setQZZ(Double.parseDouble(tempQzz[i]));//权重值
			tph.setQZDM(m+""+n);//权重代码
			tph.setTCDM(condition.getTcdm());//统筹代码
			tph.setQZJB(GlobalConstants.DIC_QZJB_3);//权重级别
			tph.setSJQZID(condition.getQzdm());//上级权重ID
			tph.setYXBZ(GlobalConstants.DIC_YXBZ_1);//有效标志
			tph.setXGSJ(new Date());//修改时间
			tph.setGZBZ(condition.getGzbz());//规则标识
			this.baseDao.saveObject(tph);
		}
	}

	/**
	 * 处方药非处方药权重设置
	 */
	public PageSet prescriptionSetting(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append(" select id,qzm,qzdm,qzjb,sjqzid,tcdm,qzz from tb_para_heavy where qzjb = '"+GlobalConstants.DIC_QZJB_4+"' and yxbz = '"+GlobalConstants.DIC_YXBZ_1+"' ");
		if(StringUtils.isNotEmpty(condition.getQzdm())){
			exeSQL.append(" and SJQZID = '"+condition.getQzdm()+"' ");
		}
		if(StringUtils.isNotEmpty(condition.getTcdm())){
			exeSQL.append(" and tcdm = '"+condition.getTcdm()+"' ");
		}
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 处方药非处方药权重设置保存
	 */
	public void saveParameterPrescription(QueryCondition condition) {
		//先将该统筹区下的所有三级节点数据设为无效
		String s = "update TB_PARA_HEAVY set yxbz='"+GlobalConstants.DIC_YXBZ_2+"' where tcdm = '"+condition.getTcdm()+"' and qzjb='"+GlobalConstants.DIC_QZJB_4+"' and SJQZID = '"+condition.getQzdm()+"' and yxbz='"+GlobalConstants.DIC_YXBZ_1+"' ";
		this.baseDao.executeUpdateBySql(s);
		String[] tempQzm = condition.getQzm().split(",");
		String[] tempQzz = condition.getQzz().split(",");
		int m = Integer.parseInt(condition.getQzdm());
		int n = 1000;
		for(int i=0;i<tempQzm.length;i++){
			n++;
			TbParaHeavy tph = new TbParaHeavy();
			String id = this.baseDao.getSequenceValue("SEQ_PARA_HEAVY");
			tph.setID(Long.parseLong(id));
			tph.setQZM(tempQzm[i]);//权重名
			tph.setQZZ(Double.parseDouble(tempQzz[i]));//权重值
			tph.setQZDM(m+""+n);//权重代码
			tph.setTCDM(condition.getTcdm());//统筹代码
			tph.setQZJB(GlobalConstants.DIC_QZJB_4);//权重级别
			tph.setSJQZID(condition.getQzdm());//上级权重ID
			tph.setYXBZ(GlobalConstants.DIC_YXBZ_1);//有效标志
			tph.setXGSJ(new Date());//修改时间
			tph.setGZBZ(condition.getGzbz());//规则标识
			this.baseDao.saveObject(tph);
		}
	}

	/**
	 * 当年账户历年账户权重设置
	 */
	public PageSet oldAndNewSetting(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append(" select id,qzm,qzdm,qzjb,sjqzid,tcdm,qzz from tb_para_heavy where qzjb = '"+GlobalConstants.DIC_QZJB_5+"' and yxbz = '"+GlobalConstants.DIC_YXBZ_1+"' ");
		if(StringUtils.isNotEmpty(condition.getQzdm())){
			exeSQL.append(" and SJQZID = '"+condition.getQzdm()+"' ");
		}
		if(StringUtils.isNotEmpty(condition.getTcdm())){
			exeSQL.append(" and tcdm = '"+condition.getTcdm()+"' ");
		}
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 当年账户历年账户权重设置保存
	 */
	public void saveParameterOldAndNew(QueryCondition condition) {
		//先将该统筹区下的当年账户历年账户权重数据设为无效
		String s = "update TB_PARA_HEAVY set yxbz='"+GlobalConstants.DIC_YXBZ_2+"' where tcdm = '"+condition.getTcdm()+"' and qzjb='"+GlobalConstants.DIC_QZJB_5+"' and SJQZID = '"+condition.getQzdm()+"' and yxbz='"+GlobalConstants.DIC_YXBZ_1+"' ";
		this.baseDao.executeUpdateBySql(s);
		String[] tempQzm = condition.getQzm().split(",");
		String[] tempQzz = condition.getQzz().split(",");
		int m = Integer.parseInt(condition.getQzdm());
		int n = 1000;
		for(int i=0;i<tempQzm.length;i++){
			n++;
			TbParaHeavy tph = new TbParaHeavy();
			String id = this.baseDao.getSequenceValue("SEQ_PARA_HEAVY");
			tph.setID(Long.parseLong(id));
			tph.setQZM(tempQzm[i]);//权重名
			tph.setQZZ(Double.parseDouble(tempQzz[i]));//权重值
			tph.setQZDM(m+""+n);//权重代码
			tph.setTCDM(condition.getTcdm());//统筹代码
			tph.setQZJB(GlobalConstants.DIC_QZJB_5);//权重级别
			tph.setSJQZID(condition.getQzdm());//上级权重ID
			tph.setYXBZ(GlobalConstants.DIC_YXBZ_1);//有效标志
			tph.setXGSJ(new Date());//修改时间
			tph.setGZBZ(condition.getGzbz());//规则标识
			this.baseDao.saveObject(tph);
		}
	}

	/**
	 * 费用额度设置
	 */
	public PageSet amountSetting(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append(" select id,qzm,qzdm,qzjb,sjqzid,tcdm,qzz from tb_para_heavy where qzjb = '"+GlobalConstants.DIC_QZJB_6+"' and yxbz = '"+GlobalConstants.DIC_YXBZ_1+"'");
		if(StringUtils.isNotEmpty(condition.getQzdm())){
			exeSQL.append(" and SJQZID = '"+condition.getQzdm()+"' ");
		}
		if(StringUtils.isNotEmpty(condition.getTcdm())){
			exeSQL.append(" and tcdm = '"+condition.getTcdm()+"' ");
		}
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 费用额设置保存
	 */
	public void saveParameterAmount(QueryCondition condition) {
		//先将该统筹区下的当年账户历年账户权重数据设为无效
		String s = "update TB_PARA_HEAVY set yxbz='"+GlobalConstants.DIC_YXBZ_2+"' where tcdm = '"+condition.getTcdm()+"' and qzjb='"+GlobalConstants.DIC_QZJB_6+"' and SJQZID = '"+condition.getQzdm()+"' and yxbz='"+GlobalConstants.DIC_YXBZ_1+"' ";
		this.baseDao.executeUpdateBySql(s);
		String[] tempQzm = condition.getQzm().split(",");
		String[] tempQzz = condition.getQzz().split(",");
		int m = Integer.parseInt(condition.getQzdm());
		int n = 1000;
		for(int i=0;i<tempQzm.length;i++){
			n++;
			TbParaHeavy tph = new TbParaHeavy();
			String id = this.baseDao.getSequenceValue("SEQ_PARA_HEAVY");
			tph.setID(Long.parseLong(id));
			tph.setQZM(tempQzm[i]);//权重名
			tph.setQZZ(Double.parseDouble(tempQzz[i]));//权重值
			tph.setQZDM(m+""+n);//权重代码
			tph.setTCDM(condition.getTcdm());//统筹代码
			tph.setQZJB(GlobalConstants.DIC_QZJB_6);//权重级别
			tph.setSJQZID(condition.getQzdm());//上级权重ID
			tph.setYXBZ(GlobalConstants.DIC_YXBZ_1);//有效标志
			tph.setXGSJ(new Date());//修改时间
			tph.setGZBZ(condition.getGzbz());//规则标识
			this.baseDao.saveObject(tph);
		}
	}

	/**
	 * 单次非处方购药费用占比
	 */
	public PageSet singleMedPay(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append(" select id,qzm,qzdm,qzjb,sjqzid,tcdm,qzz from tb_para_heavy where yxbz='"+GlobalConstants.DIC_YXBZ_1+"' ");
		if(StringUtils.isNotEmpty(condition.getQzdm())){
			exeSQL.append(" and SJQZID = '"+condition.getQzdm()+"' ");
		}
		if(StringUtils.isNotEmpty(condition.getTcdm())){
			exeSQL.append(" and tcdm = '"+condition.getTcdm()+"' ");
		}
		if(StringUtils.isNotEmpty(condition.getDivId())){
			if("1".equals(condition.getDivId())){
				exeSQL.append(" and qzjb = '"+GlobalConstants.DIC_QZJB_7+"' ");
			}else{
				exeSQL.append(" and qzjb = '"+GlobalConstants.DIC_QZJB_8+"' ");
			}
		}
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 单次非处方购药费用占比保存
	 */
	public void saveParameterSingleMed(QueryCondition condition) {
		String tempJb = "";
		if(condition.getDivId().equals("1")){
			tempJb = GlobalConstants.DIC_QZJB_7;
		}else{
			tempJb = GlobalConstants.DIC_QZJB_8;
		}
		//先将该统筹区下的当年账户历年账户权重数据设为无效
		String s = "update TB_PARA_HEAVY set yxbz='"+GlobalConstants.DIC_YXBZ_2+"' where tcdm = '"+condition.getTcdm()+"' and qzjb='"+tempJb+"' and SJQZID = '"+condition.getQzdm()+"' and yxbz='"+GlobalConstants.DIC_YXBZ_1+"' ";
		this.baseDao.executeUpdateBySql(s);
		String[] tempQzm = condition.getQzm().split(",");
		String[] tempQzz = condition.getQzz().split(",");
		int m = Integer.parseInt(condition.getQzdm());
		int n = 1000;
		for(int i=0;i<tempQzm.length;i++){
			n++;
			TbParaHeavy tph = new TbParaHeavy();
			String id = this.baseDao.getSequenceValue("SEQ_PARA_HEAVY");
			tph.setID(Long.parseLong(id));
			tph.setQZM(tempQzm[i]);//权重名
			tph.setQZZ(Double.parseDouble(tempQzz[i]));//权重值
			tph.setQZDM(m+""+n);//权重代码
			tph.setTCDM(condition.getTcdm());//统筹代码
			tph.setQZJB(tempJb);//权重级别
			tph.setSJQZID(condition.getQzdm());//上级权重ID
			tph.setYXBZ(GlobalConstants.DIC_YXBZ_1);//有效标志
			tph.setXGSJ(new Date());//修改时间
			tph.setGZBZ(condition.getGzbz());//规则标识
			this.baseDao.saveObject(tph);
		}
	}

	/**
	 * 药店分析详细查询
	 */
	public PageSet queryDetail(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append(" select distinct '"+condition.getStartDate()+"' as sjdm,");
		exeSQL.append("(select b.tjz from TB_STATS_INSTITUTION_INDICATOR b where a.jgid=b.jgid and a.sjdm=b.sjdm and b.zbid='ZB000001' and b.lbid='LB000001') as cf,");
		exeSQL.append("(select b.tjz from TB_STATS_INSTITUTION_INDICATOR b where a.jgid=b.jgid and a.sjdm=b.sjdm and b.zbid='ZB000002' and b.lbid='LB000001') as cffy,");
		exeSQL.append("(select b.tjz from TB_STATS_INSTITUTION_INDICATOR b where a.jgid=b.jgid and a.sjdm=b.sjdm and b.zbid='ZB000003' and b.lbid='LB000001') as fcf,");
		exeSQL.append("(select b.tjz from TB_STATS_INSTITUTION_INDICATOR b where a.jgid=b.jgid and a.sjdm=b.sjdm and b.zbid='ZB000004' and b.lbid='LB000001') as fcffy ");
		exeSQL.append(" from TB_STATS_INSTITUTION_INDICATOR a where trim(a.jgid) = '"+condition.getAkb020()+"' ");
		exeSQL.append(" and a.sjdm = '"+condition.getStartTime()+"'");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 药店综合分析分析报告
	 */
	public PageSet queryReport(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append("select * from ( ");
		exeSQL.append("select '处方药接诊总人次' as mc,");
		exeSQL.append("(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append(") as bqz,");                                                                                    
		exeSQL.append("(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append(") as sqz,");                                                                                    
		exeSQL.append("nvl((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -12), 'YYYYMM') ");
		}else{
			int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
			exeSQL.append(" and sjdm = '"+n+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("),0) as qntqz,");                                                                                  
		exeSQL.append("(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth().substring(0, 4)+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"' ");
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append(")/12 as sndpjz,");                                                                                 
		exeSQL.append("((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("),0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))*100 as tbzzl,");                                                                                  
		exeSQL.append("((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' "); 
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}       
		selectCondition(exeSQL,condition);       
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("),0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))*100 as hbzzl,");                                                                                  
		exeSQL.append("(((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' "); 
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}       
		selectCondition(exeSQL,condition);       
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("),0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))*100-((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' "); 
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}       
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		exeSQL.append("))/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		exeSQL.append("),0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		exeSQL.append("))*100) as tlydcz,");
		exeSQL.append("((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		selectCondition(exeSQL,condition);   
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}  
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		selectCondition(exeSQL,condition);                                                                             
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth().substring(0, 4)+"')/12 ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"')/4 ");
		}
		exeSQL.append(")/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		selectCondition(exeSQL,condition);                                                                             
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth().substring(0, 4)+"')/12 ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"')/4 ");
		}
		exeSQL.append(",0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000001' and LBID='LB000001' ");
		selectCondition(exeSQL,condition);                                                                             
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth().substring(0, 4)+"')/12 ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"')/4 ");
		}
		exeSQL.append(")*100 as zjqnbj ");
		exeSQL.append("from dual ");
		exeSQL.append("union all ");
		exeSQL.append("select '处方药费用' as mc,");
		exeSQL.append("(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append(") as bqz,");                                                                                    
		exeSQL.append("(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append(") as sqz,");                                                                                    
		exeSQL.append("nvl((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -12), 'YYYYMM') ");
		}else{
			int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
			exeSQL.append(" and sjdm = '"+n+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("),0) as qntqz,");                                                                                  
		exeSQL.append("(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth().substring(0, 4)+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"' ");
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append(")/12 as sndpjz,");                                                                                 
		exeSQL.append("((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("),0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))*100 as tbzzl,");                                                                                  
		exeSQL.append("((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' "); 
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}       
		selectCondition(exeSQL,condition);       
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("),0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))*100 as hbzzl,");                                                                                  
		exeSQL.append("(((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' "); 
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}       
		selectCondition(exeSQL,condition);       
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("),0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))*100-((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' "); 
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}       
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		exeSQL.append("))/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		exeSQL.append("),0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		exeSQL.append("))*100) as tlydcz,");
		exeSQL.append("((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		selectCondition(exeSQL,condition);   
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}  
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		selectCondition(exeSQL,condition);                                                                             
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth().substring(0, 4)+"')/12 ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"')/4 ");
		}
		exeSQL.append(")/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		selectCondition(exeSQL,condition);                                                                             
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth().substring(0, 4)+"')/12 ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"')/4 ");
		}
		exeSQL.append(",0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000002' and LBID='LB000001' ");
		selectCondition(exeSQL,condition);                                                                             
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth().substring(0, 4)+"')/12 ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"')/4 ");
		}
		exeSQL.append(")*100 as zjqnbj ");
		exeSQL.append("from dual ");
		exeSQL.append("union all ");
		exeSQL.append("select '非处方药接诊总人次' as mc,");
		exeSQL.append("(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append(") as bqz,");                                                                                    
		exeSQL.append("(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append(") as sqz,");                                                                                    
		exeSQL.append("nvl((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -12), 'YYYYMM') ");
		}else{
			int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
			exeSQL.append(" and sjdm = '"+n+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("),0) as qntqz,");                                                                                  
		exeSQL.append("(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth().substring(0, 4)+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"' ");
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append(")/12 as sndpjz,");                                                                                 
		exeSQL.append("((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("),0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))*100 as tbzzl,");                                                                                  
		exeSQL.append("((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' "); 
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}       
		selectCondition(exeSQL,condition);       
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("),0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))*100 as hbzzl,");                                                                                  
		exeSQL.append("(((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' "); 
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}       
		selectCondition(exeSQL,condition);       
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("),0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))*100-((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' "); 
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}       
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		exeSQL.append("))/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		exeSQL.append("),0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		exeSQL.append("))*100) as tlydcz,");
		exeSQL.append("((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		selectCondition(exeSQL,condition);   
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}  
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		selectCondition(exeSQL,condition);                                                                             
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth().substring(0, 4)+"')/12 ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"')/4 ");
		}
		exeSQL.append(")/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		selectCondition(exeSQL,condition);                                                                             
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth().substring(0, 4)+"')/12 ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"')/4 ");
		}
		exeSQL.append(",0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000003' and LBID='LB000001' ");
		selectCondition(exeSQL,condition);                                                                             
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth().substring(0, 4)+"')/12 ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"')/4 ");
		}
		exeSQL.append(")*100 as zjqnbj ");
		exeSQL.append("from dual ");
		exeSQL.append("union all ");
		exeSQL.append("select '非处方药费用' as mc,");
		exeSQL.append("(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append(") as bqz,");                                                                                    
		exeSQL.append("(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append(") as sqz,");                                                                                    
		exeSQL.append("nvl((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -12), 'YYYYMM') ");
		}else{
			int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
			exeSQL.append(" and sjdm = '"+n+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("),0) as qntqz,");                                                                                  
		exeSQL.append("(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth().substring(0, 4)+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"' ");
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append(")/12 as sndpjz,");                                                                                 
		exeSQL.append("((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("),0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))*100 as tbzzl,");                                                                                  
		exeSQL.append("((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' "); 
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}       
		selectCondition(exeSQL,condition);       
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("),0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))*100 as hbzzl,");                                                                                  
		exeSQL.append("(((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' "); 
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}       
		selectCondition(exeSQL,condition);       
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("),0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		selectCondition(exeSQL,condition);                                                                             
		exeSQL.append("))*100-((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' "); 
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}       
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		exeSQL.append("))/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		exeSQL.append("),0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		//时间代码
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = TO_CHAR(ADD_MONTHS(TO_DATE('"+condition.getByMonth()+"', 'YYYYMM'), -1), 'YYYYMM') ");
		}else{
			if(condition.getByQuarter().substring(5, 6).equals("1")){
				int n = Integer.parseInt(condition.getByQuarter().substring(0, 4))-1;
				exeSQL.append(" and sjdm = '"+n+"S"+4+"' ");
			}else{
				int m = Integer.parseInt(condition.getByQuarter().substring(5, 6))-1;
				exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+m+"' ");
			}
		}
		exeSQL.append("))*100) as tlydcz,");
		exeSQL.append("((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		selectCondition(exeSQL,condition);   
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth()+"' ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"S"+condition.getByQuarter().substring(5, 6)+"' ");
		}  
		exeSQL.append(")-(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		selectCondition(exeSQL,condition);                                                                             
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth().substring(0, 4)+"')/12 ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"')/4 ");
		}
		exeSQL.append(")/DECODE((select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		selectCondition(exeSQL,condition);                                                                             
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth().substring(0, 4)+"')/12 ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"')/4 ");
		}
		exeSQL.append(",0,1,(select sum(tjz) from TB_STATS_INSTITUTION_INDICATOR where ZBID = 'ZB000004' and LBID='LB000001' ");
		selectCondition(exeSQL,condition);                                                                             
		if(condition.getType().equals("2")){
			exeSQL.append(" and sjdm = '"+condition.getByMonth().substring(0, 4)+"')/12 ");
		}else{
			exeSQL.append(" and sjdm = '"+condition.getByQuarter().substring(0, 4)+"')/4 ");
		}
		exeSQL.append(")*100 as zjqnbj ");
		exeSQL.append("from dual ");
		exeSQL.append(") ");
		
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}
	
	public void selectCondition(StringBuffer exeSQL,QueryCondition condition){
		//机构ID
		if(StringUtils.isNotEmpty(condition.getAkb020())){
			exeSQL.append(" and jgid = '"+condition.getAkb020()+"' ");
		}
	}

	/**
	 * 查询TB_PARA_HEAVY权重和占比信息
	 */
	@SuppressWarnings("unchecked")
	public List queryBasicInfo(QueryCondition condition) throws Exception {
		StringBuffer exeSQL=new StringBuffer();
		exeSQL.append(" SELECT distinct ");
		exeSQL.append("(select b.qzz from TB_PARA_HEAVY b where a.tcdm = b.tcdm and b.qzm = '单次非处方购药当年账户支付费用额度' and b.qzjb = '6') as dnzh,");
		exeSQL.append("(select b.qzz from TB_PARA_HEAVY b where a.tcdm = b.tcdm and b.qzm = '单次非处方购药历年账户支付费用额度' and b.qzjb = '6') as lnzh,");
		exeSQL.append("(select count(*) from kc21 c,kc22 b where c.aaz217 = b.aaz217 and b.aka064 = '0' and to_char(c.akc194,'yyyy') = to_char(sysdate,'yyyy') ");
		exeSQL.append("and b.AKB065 < (select b.qzz from TB_PARA_HEAVY b where a.tcdm = b.tcdm and b.qzm = '单次非处方购药当年账户支付费用额度' and b.qzjb = '6'))/(");
		exeSQL.append("select (case when count(*)=0 then 1 else count(*) end) from kc21 c,kc22 b where c.aaz217 = b.aaz217 and b.aka064 = '0' and to_char(c.akc194,'yyyy') = to_char(sysdate,'yyyy'))*100 as dnzb,");
		exeSQL.append("(select count(*) from kc21 c,kc22 b where c.aaz217 = b.aaz217 and b.aka064 = '0' and to_char(c.akc194,'yyyy') < to_char(sysdate,'yyyy') ");
		exeSQL.append("and b.AKB065 < (select b.qzz from TB_PARA_HEAVY b where a.tcdm = b.tcdm and b.qzm = '单次非处方购药历年账户支付费用额度' and b.qzjb = '6'))/(");
		exeSQL.append("select (case when count(*)=0 then 1 else count(*) end) from kc21 c,kc22 b where c.aaz217 = b.aaz217 and b.aka064 = '0' and to_char(c.akc194,'yyyy') < to_char(sysdate,'yyyy'))*100 as lnzb ");
		exeSQL.append("FROM TB_PARA_HEAVY a where 1=1 ");
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQL.append(" and a.tcdm = '"+condition.getAaa027()+"'");
		}
		return this.baseDao.findListOfMapBySql(exeSQL.toString());
	}

	/**
	 * 查询kc21,kb01信息
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List queryKc21Kb01Info(QueryCondition condition) throws Exception {
		StringBuffer exeSQL=new StringBuffer();
		exeSQL.append("select aab013,aae006 from kb01 where 1 = 1 ");
		if(StringUtils.isNotEmpty(condition.getAkb020())){
			exeSQL.append(" and akb020 = '"+condition.getAkb020()+"'");
		}
		return this.baseDao.findListOfMapBySql(exeSQL.toString());
	}

	/**
	 * 药店费用等级跨越详细设置分页查询
	 */
	public PageSet medLevelSetting(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
		if(condition.getDivId().equals("1")){
			exeSQL.append("select distinct a.id as id,a.jbm as jbm,a.jbdm as jbdm,a.heavy_id,a.tcdm,a.xxz as xxz,a.xxbhdy as xxbhdy,a.xxwx as xxwx,a.sxz as sxz,a.sxbhdy as sxbhdy,a.sxwx as sxwx,a.jbfz,a.jbms ");
			exeSQL.append("from TB_PARA_GRADE a where 1=1 ");
		}else if(condition.getDivId().equals("2")){
			exeSQL.append("select distinct a.id as tid,a.jbm as tjbm,a.jbdm as jbdm,a.heavy_id,a.tcdm,a.xxz as txxz,a.xxbhdy as txxbhdy,a.xxwx as txxwx,a.sxz as tsxz,a.sxbhdy as tsxbhdy,a.sxwx as tsxwx,a.jbfz,a.jbms ");
			exeSQL.append("from TB_PARA_GRADE a where 1=1 ");
		}
		//统筹代码
		if(StringUtils.isNotEmpty(condition.getTcdm())){
			exeSQL.append(" and a.tcdm = '"+condition.getTcdm()+"' ");
		}
		//权重ID
		if(StringUtils.isNotEmpty(condition.getId())){
			exeSQL.append(" and a.heavy_id = '"+condition.getId()+"' ");
		}
		exeSQL.append(" and a.gzbz = "+condition.getGzbz()+" ");
		exeSQL.append(" and a.yxbz = '"+GlobalConstants.DIC_YXBZ_1+"' ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		exeSQL.append(" order by a.id asc ");
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 药店费用等级详情设置
	 */
	public void saveMedLevel(QueryCondition condition) {
		//先将该统筹区下的当年账户历年账户权重数据设为无效
		String s = "update TB_PARA_GRADE set yxbz='"+GlobalConstants.DIC_YXBZ_2+"' where heavy_id = '"+condition.getId()+"' and tcdm='"+condition.getTcdm()+"' and yxbz='"+GlobalConstants.DIC_YXBZ_1+"' ";
		this.baseDao.executeUpdateBySql(s);
		String[] templev = condition.getMedlev().split(",");
        String[] tempname = condition.getFcfName().split(",");
		String[] tempval = condition.getFcfValue().split(",");
		String[] tempcfname = condition.getCfName().split(",");
		String[] tempcfval = condition.getCfValue().split(",");
		List<TbParaGrade> tpgList = new ArrayList<TbParaGrade>();
		//非处方药
		for(int i=0;i<templev.length;i++){
			TbParaGrade tpg = new TbParaGrade();
			String id = this.baseDao.getSequenceValue("SEQ_PARA_GRADE");
			tpg.setID(Long.parseLong(id));
			tpg.setJBM(tempname[i]);//级别名
			tpg.setJBDM(templev[i]);//级别代码
			tpg.setHEAVY_ID(condition.getId());//权重ID
			tpg.setTCDM(condition.getTcdm());//统筹代码
			if(templev[i].equals("1")){
				tpg.setXXZ(0.0);//下限值
				tpg.setXXBHDY(GlobalConstants.DIC_XXBHDY_1);
				tpg.setXXWX(GlobalConstants.DIC_XXWX_1);
				tpg.setSXZ(Double.parseDouble(tempval[i]));//上限值
				tpg.setSXBHDY(GlobalConstants.DIC_SXBHDY_0);
				tpg.setSXWX(GlobalConstants.DIC_SXWX_0);
			}else if(templev[i].equals("4")){
				tpg.setXXZ(Double.parseDouble(tempval[i]));//下限值
				tpg.setXXBHDY(GlobalConstants.DIC_XXBHDY_1);
				tpg.setXXWX(GlobalConstants.DIC_XXWX_0);
				tpg.setSXZ(99999999.99);//上限值
				tpg.setSXBHDY(GlobalConstants.DIC_SXBHDY_0);
				tpg.setSXWX(GlobalConstants.DIC_SXWX_1);
			}else{
				String[] tt = tempval[i].split(";");
				tpg.setXXZ(Double.parseDouble(tt[0]));//下限值
				tpg.setXXBHDY(GlobalConstants.DIC_XXBHDY_1);
				tpg.setXXWX(GlobalConstants.DIC_XXWX_0);
				tpg.setSXZ(Double.parseDouble(tt[1]));//上限值
				tpg.setSXBHDY(GlobalConstants.DIC_SXBHDY_0);
				tpg.setSXWX(GlobalConstants.DIC_SXWX_0);
			}
			tpg.setJBFZ(0.0);//级别分值
			tpg.setJBMS(tempname[i]);//级别描述
			tpg.setYXBZ(GlobalConstants.DIC_YXBZ_1);//有效标志
			tpg.setXGSJ(new Date());//修改时间
			tpg.setGZBZ(condition.getGzbz());//规则标识
			tpgList.add(tpg);
		}
		//处方药
		for(int i=0;i<templev.length;i++){
			TbParaGrade tpg = new TbParaGrade();
			String id = this.baseDao.getSequenceValue("SEQ_PARA_GRADE");
			tpg.setID(Long.parseLong(id));
			tpg.setJBM(tempcfname[i]);//级别名
			tpg.setJBDM(templev[i]);//级别代码
			tpg.setHEAVY_ID(condition.getId());//权重ID
			tpg.setTCDM(condition.getTcdm());//统筹代码
			if(templev[i].equals("1")){
				tpg.setXXZ(0.0);//下限值
				tpg.setXXBHDY(GlobalConstants.DIC_XXBHDY_1);
				tpg.setXXWX(GlobalConstants.DIC_XXWX_0);
				tpg.setSXZ(Double.parseDouble(tempcfval[i]));//上限值
				tpg.setSXBHDY(GlobalConstants.DIC_SXBHDY_0);
				tpg.setSXWX(GlobalConstants.DIC_SXWX_0);
			}else if(templev[i].equals("4")){
				tpg.setXXZ(Double.parseDouble(tempcfval[i]));//下限值
				tpg.setXXBHDY(GlobalConstants.DIC_XXBHDY_1);
				tpg.setXXWX(GlobalConstants.DIC_XXWX_0);
				tpg.setSXZ(99999999.99);//上限值
				tpg.setSXBHDY(GlobalConstants.DIC_SXBHDY_0);
				tpg.setSXWX(GlobalConstants.DIC_SXWX_1);
			}else{
				String[] tt = tempcfval[i].split(";");
				tpg.setXXZ(Double.parseDouble(tt[0]));//下限值
				tpg.setXXBHDY(GlobalConstants.DIC_XXBHDY_1);
				tpg.setXXWX(GlobalConstants.DIC_XXWX_0);
				tpg.setSXZ(Double.parseDouble(tt[1]));//上限值
				tpg.setSXBHDY(GlobalConstants.DIC_SXBHDY_0);
				tpg.setSXWX(GlobalConstants.DIC_SXWX_0);
			}
			tpg.setJBFZ(0.0);//级别分值
			tpg.setJBMS(tempcfname[i]);//级别描述
			tpg.setYXBZ(GlobalConstants.DIC_YXBZ_1);//有效标志
			tpg.setXGSJ(new Date());//修改时间
			tpg.setGZBZ(condition.getGzbz());//规则标识
			tpgList.add(tpg);
		}
		for(TbParaGrade tpg : tpgList){
			String sql = "INSERT INTO TB_PARA_GRADE(ID, JBM, JBDM, HEAVY_ID, TCDM, XXZ, XXBHDY, XXWX, SXZ, SXBHDY, SXWX, JBFZ, JBMS, YXBZ, XGSJ, GZBZ) ";
			sql += "VALUES("+tpg.getID()+", '"+tpg.getJBM()+"', '"+tpg.getJBDM()+"', '"+tpg.getHEAVY_ID()+"', '"+tpg.getTCDM()+"', "+tpg.getXXZ()+", " +
					"'"+tpg.getXXBHDY()+"', '"+tpg.getXXWX()+"', "+tpg.getSXZ()+", '"+tpg.getSXBHDY()+"', '"+tpg.getSXWX()+"', "+tpg.getJBFZ()+", " +
					"'"+tpg.getJBMS()+"', '"+tpg.getYXBZ()+"', SYSDATE, "+tpg.getGZBZ()+")";
			this.baseDao.executeUpdateBySql(sql);
		}
	}

	/**
	 * 费用范围修改
	 */
	@SuppressWarnings("unchecked")
	public List queryTbParaGradeById(QueryCondition condition) {
		String sql = "select id,sxz,xxz,tcdm,jbdm,jbm from TB_PARA_GRADE where id in ("+condition.getId()+","+condition.getTid()+") and yxbz = '"+GlobalConstants.DIC_YXBZ_1+"'";
		return this.baseDao.getListBySql(sql);
	}

	/**
	 * 保存修改的费用范围
	 */
	public void saveCostRange(QueryCondition condition) {
		TbParaGrade tpg = (TbParaGrade)this.baseDao.getObjectById(TbParaGrade.class, Long.parseLong(condition.getId()));
		TbParaGrade ttpg = (TbParaGrade)this.baseDao.getObjectById(TbParaGrade.class, Long.parseLong(condition.getTid()));
		if(condition.getMedlev().equals("4")){
			String[] xxz = condition.getXxz().split(",");
			tpg.setXXZ(Double.parseDouble(xxz[0]));
			ttpg.setXXZ(Double.parseDouble(xxz[1]));
		}else if(condition.getMedlev().equals("1")){
			String[] sxz = condition.getSxz().split(",");
			tpg.setSXZ(Double.parseDouble(sxz[0]));
			ttpg.setSXZ(Double.parseDouble(sxz[1]));
		}else{
			String[] xxz = condition.getXxz().split(",");
			String[] sxz = condition.getSxz().split(",");
			tpg.setXXZ(Double.parseDouble(xxz[0]));
			ttpg.setXXZ(Double.parseDouble(xxz[1]));
			tpg.setSXZ(Double.parseDouble(sxz[0]));
			ttpg.setSXZ(Double.parseDouble(sxz[1]));
		}
		this.baseDao.saveObject(tpg);
		this.baseDao.saveObject(ttpg);
	}

	/**
	 * 药店等级差值设置
	 */
	public PageSet levelScoreSetting(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append(" select id,qzm,qzdm,qzjb,sjqzid,tcdm,qzz from tb_para_heavy where yxbz = '"+GlobalConstants.DIC_YXBZ_1+"' ");
		//权重级别
		if(StringUtils.isNotEmpty(condition.getDivId())){
			if("1".equals(condition.getDivId())){
				exeSQL.append(" and qzjb = '"+GlobalConstants.DIC_QZJB_9+"' ");
			}else{
				exeSQL.append(" and qzjb = '"+GlobalConstants.DIC_QZJB_10+"' ");
			}
		}
		//统筹代码
		if(StringUtils.isNotEmpty(condition.getTcdm())){
			exeSQL.append(" and tcdm = '"+condition.getTcdm()+"' ");
		}
		//级别代码
		if(StringUtils.isNotEmpty(condition.getJbdm())){
			exeSQL.append(" and sjqzid = '"+condition.getJbdm()+"' ");
		}
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 药店等级差值设置分值保存
	 */
	public void saveLevelScore(QueryCondition condition) {
		String tempJb = "";
		if(condition.getDivId().equals("1")){
			tempJb = GlobalConstants.DIC_QZJB_9;
		}else{
			tempJb = GlobalConstants.DIC_QZJB_10;
		}
		//先将该统筹区下的药店等级差值设置分值数据设为无效
		String s = "update TB_PARA_HEAVY set yxbz='"+GlobalConstants.DIC_YXBZ_2+"' where tcdm = '"+condition.getTcdm()+"' and qzjb='"+tempJb+"' and yxbz='"+GlobalConstants.DIC_YXBZ_1+"' and sjqzid = '"+condition.getJbdm()+"' ";
		this.baseDao.executeUpdateBySql(s);
		String[] tempQzm = condition.getQzm().split(",");
		String[] tempQzz = condition.getQzz().split(",");
		for(int i=0;i<tempQzm.length;i++){
			TbParaHeavy tph = new TbParaHeavy();
			String id = this.baseDao.getSequenceValue("SEQ_PARA_HEAVY");
			tph.setID(Long.parseLong(id));
			tph.setQZM(tempQzm[i]);//权重名
			tph.setQZZ(Double.parseDouble(tempQzz[i]));//权重值
			tph.setQZDM(""+i);//权重代码
			tph.setTCDM(condition.getTcdm());//统筹代码
			tph.setQZJB(tempJb);//权重级别
			tph.setSJQZID(condition.getJbdm());//上级权重ID
			tph.setYXBZ(GlobalConstants.DIC_YXBZ_1);//有效标志
			tph.setXGSJ(new Date());//修改时间
			tph.setGZBZ(condition.getGzbz());//规则标识
			this.baseDao.saveObject(tph);
		}
	}

	/**
	 * 参保人购药排名
	 */
	public PageSet cbrRanking(PageSet pageSet, QueryCondition condition) {
		String date1 = condition.getStartDate();
		String date2 = condition.getEndDate();
		String startDate = "";
		String endDate = "";
		if (StringUtils.isNotEmpty(condition.getType())) {
			if ("1".equals(condition.getType())) {
				startDate = date1.substring(0, 4) + "01";
				endDate = date1.substring(0, 4) + "12";
			}
			if ("2".equals(condition.getType())) {
				startDate = date1.substring(0, 4) + date1.substring(5, 7);
				endDate = date2.substring(0, 4) + date2.substring(5, 7);
			}
			if ("3".equals(condition.getType())) {
				String quarter = condition.getByQuarter();
				if (condition.getByQuarter().endsWith("1")) {
					startDate = quarter.substring(0, 4) + "01";
					endDate = quarter.substring(0, 4) + "03";
				}
				if (condition.getByQuarter().endsWith("2")) {
					startDate = quarter.substring(0, 4) + "04";
					endDate = quarter.substring(0, 4) + "06";
				}
				if (condition.getByQuarter().endsWith("3")) {
					startDate = quarter.substring(0, 4) + "07";
					endDate = quarter.substring(0, 4) + "09";
				}
				if (condition.getByQuarter().endsWith("4")) {
					startDate = quarter.substring(0, 4) + "10";
					endDate = quarter.substring(0, 4) + "12";
				}
			}
			if ("4".equals(condition.getType())) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
				Date date = new Date();
				String format = sdf.format(date);
				startDate = format.substring(0, 4) + "01";
				endDate = format;
			}
		}
		
		StringBuffer exeSQL=new StringBuffer("select * from(select t1.* from(select max(a.ybh) as ybh, max(b.xm) xm, " +
				"(select t2.name from (select ddjg_id,row_number() over(partition by ybh order by fyze desc) rk " +
				"from F_PHARM_PAY_YDCBR_2 where jsny >= '" + startDate + "' and jsny <= '" + endDate + "' and ybh = a.ybh) t1, " +
				"d_ddjg t2 where t1.rk = 1 and t1.ddjg_id = t2.ddjg_id) mc, " +
				"(select t2.admin_region_id from (select ddjg_id,row_number() over(partition by ybh order by fyze desc) rk " +
				"from F_PHARM_PAY_YDCBR_2 where jsny >= '" + startDate + "' and jsny <= '" + endDate + "' and ybh = a.ybh) t1, d_ddjg t2 " +
				"where t1.rk = 1 and t1.ddjg_id = t2.ddjg_id) xzqh, max(a.cbxzqh) cbxzqh, max(a.jyqhdm) jyqhdm, sum(a.fyze) fyze " +
				"from F_PHARM_PAY_YDCBR_2 a, d_person b where a.ybh = b.ybh  and a.jsny >= '" + startDate + "'  and a.jsny <= '" + endDate + "' ");
		StringBuffer countSQL=new StringBuffer();
		
		if (StringUtils.isNotEmpty(condition.getAaa027())) {
			exeSQL.append(" and a.jyqhdm = '" + condition.getAaa027() + "' ");
		}
		exeSQL.append(" group by a.ybh) t1 where 1 = 1  ");
		exeSQL.append(" order by t1.fyze desc) where rownum <= 100 ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" ) ");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 药店刷卡频次统计生成计划
	 */
	public void generatePlan(QueryCondition condition) {
		TbJobSchedule tjs = new TbJobSchedule();
		String id = this.baseDao.getSequenceValue("SEQ_JOB_SCHEDULE");
		tjs.setID(Long.parseLong(id));//主键
		tjs.setRWLX(GlobalConstants.DIC_RWLX_1);//任务类型-药店刷卡频次统计
		tjs.setPARA1(condition.getStartDate()); //参数一	 	日期开始时间
		tjs.setPARA2(condition.getEndDate());   //参数二	 	日期结束时间
		tjs.setPARA3(condition.getStartTime()); //参数三	 	时间开始时分
		tjs.setPARA4(condition.getEndTime());   //参数四  	时间结束时分
		tjs.setPARA5(condition.getAaa027());	//参数五		区县代码
		tjs.setPARA6(condition.getYdIds().trim());		//参数六		药店ID
		tjs.setPARA7(condition.getAka130());	//参数七		就诊类型
		tjs.setPARA8(String.valueOf(condition.getSjjg())); //参数八		时间间隔
//		tjs.setPARA8(condition.getSjjg()+condition.getSjlb()); //参数八		时间间隔
		tjs.setPARA9(condition.getPc());		//参数九 	频次
		tjs.setPARA10(String.valueOf(condition.getPmsl()));	   //参数十		排名数量
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			tjs.setYJZXSJ(sdf.parse(sdfdate.format(new Date())+" 20:00:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		tjs.setZXZT(GlobalConstants.DIC_ZXZT_0);//执行状态  未执行
		tjs.setYXBZ(GlobalConstants.DIC_YXBZ_1);//有效标志
		tjs.setXGSJ(new Date());//修改时间
		this.baseDao.saveObject(tjs);
	}

	/**
	 * 药店刷卡频次统计计划查询
	 */
	public PageSet swipeFrequencyStatistics(PageSet pageSet,QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append(" select a.*,(select b.akb021 from kb01 b where b.akb020 in (a.para6)) as akb021 "); 
		exeSQL.append(" from TB_JOB_SCHEDULE a where a.yxbz = '"+GlobalConstants.DIC_YXBZ_1+"' order by yxj asc");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 计划查询
	 */
	public PageSet queryGenerateDetail(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append(" select a.*,(select b.akb021 from kb01 b where a.jgid=b.akb020) as akb021 ");
		exeSQL.append("from TB_STATS_JG_SUM a where a.zxjhid = '"+condition.getId()+"' and a.yxbz = '"+GlobalConstants.DIC_YXBZ_1+"' order by kssj asc");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 显示明细
	 */
	public PageSet queryPlanDetail(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append(" select a.* ");
		exeSQL.append("from TB_STATS_MR_SUM a where a.PXBID = "+condition.getId()+" and a.yxbz = '"+GlobalConstants.DIC_YXBZ_1+"' order by kssj asc");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 就诊明细
	 */
	public PageSet queryJzmxDetail(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		exeSQL.append(" select distinct a.ybh,a.sfzh,a.rylb,a.xm,a.jzsj,a.jzlx,a.jzfy ");
		exeSQL.append("from TB_STATS_MR_DETAIL a where a.SFZH = '"+condition.getId()+"' and a.yxbz = '"+GlobalConstants.DIC_YXBZ_1+"' ");
		exeSQL.append("and jzsj between to_date('"+sdf.format(condition.getKssj())+"','yyyyMMdd hh24:mi:ss') and to_date('"+sdf.format(condition.getJssj())+"','yyyyMMdd hh24:mi:ss') ");
		exeSQL.append("and a.jzlx in ('031','032') order by jzsj asc");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 药店等级设置查询
	 */
	public PageSet queryMedLevel(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
//		exeSQL.append(" select a.* ");
//		exeSQL.append("from TB_STATS_MR_DETAIL a where a.SFZH = "+condition.getId()+" and a.yxbz = '"+GlobalConstants.DIC_YXBZ_1+"' order by jzsj asc");
//		countSQL.append("select count(*) from (");
//		countSQL.append(exeSQL.toString());
//		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	public String queryAkb021(String akb020) {
		String sql = "select akb021 from kb01 where akb020 = '"+akb020+"'";
		List list = this.baseDao.getListBySql(sql);
		return String.valueOf(list.get(0));
	}

	public void executePlan(QueryCondition condition) {
//		TbJobSchedule tjs = (TbJobSchedule)this.baseDao.getObjectById(TbJobSchedule.class, Long.parseLong(condition.getId()));
//		Connection conn = null;
//		CallableStatement cstmt = null;
//		try {
//			conn = SessionFactoryUtils.getDataSource(this.baseDao.getSessionFactory()).getConnection();
//			cstmt = conn.prepareCall("{ call PKG_TQSJ_NEW.PRC_SCHEDULING (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
//			cstmt.setString(1, condition.getId());//id
//			cstmt.setString(2, tjs.getRWLX());//任务类型
//			cstmt.setString(3, tjs.getPARA1());  // 参数1RWLX=01，日期开始时间          
//			cstmt.setString(4, tjs.getPARA2());  // 参数2RWLX=01，日期结束时间          
//			cstmt.setString(5, tjs.getPARA3());  // 参数3RWLX=01，时间开始时分          
//			cstmt.setString(6, tjs.getPARA4());  // 参数4RWLX=01，时间结束时分          
//			cstmt.setString(7, tjs.getPARA5());  // 参数5RWLX=01，区县代码            
//			cstmt.setString(8, tjs.getPARA6());  // 参数6RWLX=01，机构ID            
//			cstmt.setString(9, tjs.getPARA7());  // 参数7RWLX=01，就诊类型            
//			cstmt.setString(10, tjs.getPARA8()); // 参数8RWLX=01，时间间隔（分钟）        
//			cstmt.setString(11, tjs.getPARA9()); // 参数9RWLX=01，频次              
//			cstmt.setString(12, tjs.getPARA10()); // 参数10RWLX=01，排名数量          
//			cstmt.setString(13, tjs.getPARA11()); // 参数11                      
//			cstmt.setString(14, tjs.getPARA12()); // 参数12                      
//			cstmt.setString(15, GlobalConstants.DIC_ZXZT_1);//执行状态
//			cstmt.setString(16, "admin");//执行人ID
////			cstmt.registerOutParameter(5, OracleTypes.NUMBER);
////			cstmt.registerOutParameter(6, OracleTypes.VARCHAR);
//			cstmt.execute();
////			得到存储过程的输出参数值并打印出来
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//				tjs.setZXZT(GlobalConstants.DIC_ZXZT_1);//正在执行
//				this.baseDao.saveObject(tjs);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}

	/**
	 * 查询制定计划数量
	 * @throws Exception 
	 */
	public List queryGeneratePlan(QueryCondition condition) throws Exception {
		String sql = "select NVL(count(*),0) as zs from TB_JOB_SCHEDULE where zxzt='"+GlobalConstants.DIC_ZXZT_0+"' and PARA5='"+condition.getAaa027()+"' and YXBZ = '"+GlobalConstants.DIC_YXBZ_1+"'";
		return this.baseDao.findListOfMapBySql(sql);
	}

	/**
	 * 根据规则标识废除权重数据
	 */
	public void updateSet(QueryCondition condition) {
		String s = "update TB_PARA_HEAVY set yxbz='"+GlobalConstants.DIC_YXBZ_2+"' where  yxbz='"+GlobalConstants.DIC_YXBZ_1+"' and gzbz = "+condition.getGzbz()+"";
		String s1 = "update TB_PARA_GRADE set yxbz='"+GlobalConstants.DIC_YXBZ_2+"' where  yxbz='"+GlobalConstants.DIC_YXBZ_1+"' and gzbz = "+condition.getGzbz()+"";
		this.baseDao.executeUpdateBySql(s);
		this.baseDao.executeUpdateBySql(s1);
	}

	/**
	 * 单行新增权重保存
	 */
	public void saveSingleRow(QueryCondition condition) {
		TbParaHeavy tph = new TbParaHeavy();
		String id = this.baseDao.getSequenceValue("SEQ_PARA_HEAVY");
		tph.setID(Long.parseLong(id));
		tph.setQZM(condition.getQzm());//权重名
		tph.setQZZ(Double.parseDouble(condition.getQzz()));//权重值
		tph.setQZDM("4000");//权重代码
		tph.setTCDM(condition.getTcdm());//统筹代码
		tph.setQZJB("1");//权重级别
		tph.setSJQZID("0");//上级权重ID
		tph.setYXBZ(GlobalConstants.DIC_YXBZ_1);//有效标志
		tph.setXGSJ(new Date());//修改时间
		tph.setGZBZ(condition.getGzbz());//规则标识
		this.baseDao.saveObject(tph);
	}

	/**
	 * 历史参数查询
	 */
	public PageSet queryHistoryStatistics(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append(" select * from tb_para_heavy ");
		exeSQL.append(" where qzjb = '1' and sjqzid = '0' ");
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQL.append(" and tcdm = '"+condition.getAaa027()+"' ");
		}
		exeSQL.append(" and gzbz = '"+condition.getGzbz()+"' ");
		exeSQL.append(" order by gzbz asc ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 
	 */
	public PageSet viewGrowSetting(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append(" select * from tb_para_heavy ");
		exeSQL.append(" where 1=1 ");
		//统筹代码
		if(StringUtils.isNotEmpty(condition.getTcdm())){
			exeSQL.append(" and tcdm = '"+condition.getTcdm()+"' ");
		}
		//上级权重代码
		if(StringUtils.isNotEmpty(condition.getQzdm())){
			exeSQL.append(" and sjqzid = '"+condition.getQzdm()+"' ");
		}
		//权重级别
		if(StringUtils.isNotEmpty(condition.getQzjb())){
			exeSQL.append(" and qzjb = '"+condition.getQzjb()+"' ");
		}
		//规则标识
		exeSQL.append(" and gzbz = "+condition.getGzbz()+" ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	public PageSet viewMedLevelSetting(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
		if(condition.getDivId().equals("1")){
			exeSQL.append("select distinct a.id as id,a.jbm as jbm,a.jbdm as jbdm,a.heavy_id,a.tcdm,a.xxz as xxz,a.xxbhdy as xxbhdy,a.xxwx as xxwx,a.sxz as sxz,a.sxbhdy as sxbhdy,a.sxwx as sxwx,a.jbfz,a.jbms ");
			exeSQL.append("from TB_PARA_GRADE a where 1=1 ");
		}else if(condition.getDivId().equals("2")){
			exeSQL.append("select distinct a.id as tid,a.jbm as tjbm,a.jbdm as jbdm,a.heavy_id,a.tcdm,a.xxz as txxz,a.xxbhdy as txxbhdy,a.xxwx as txxwx,a.sxz as tsxz,a.sxbhdy as tsxbhdy,a.sxwx as tsxwx,a.jbfz,a.jbms ");
			exeSQL.append("from TB_PARA_GRADE a where 1=1 ");
		}
		//统筹代码
		if(StringUtils.isNotEmpty(condition.getTcdm())){
			exeSQL.append(" and a.tcdm = '"+condition.getTcdm()+"' ");
		}
		//权重ID
		if(StringUtils.isNotEmpty(condition.getId())){
			exeSQL.append(" and a.heavy_id = '"+condition.getId()+"' ");
		}
		//规则标识
		exeSQL.append(" and a.gzbz = "+condition.getGzbz()+" ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		exeSQL.append(" order by a.id asc ");
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 权重修改初始化页面 读取数据
	 */
	public PageSet changeWeigth(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
		exeSQL.append("select id,qzm,qzdm,tcdm,qzz,gzbz from TB_PARA_HEAVY where qzjb = '"+condition.getQzjb()+"' ");
		if(StringUtils.isNotEmpty(condition.getQzdm())){
			exeSQL.append(" and sjqzid = '"+condition.getQzdm()+"' ");
		}
		//规则标识
		exeSQL.append(" and gzbz = "+condition.getGzbz()+" ");
		exeSQL.append(" and yxbz = '"+GlobalConstants.DIC_YXBZ_1+"' ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		exeSQL.append(" order by id asc ");
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 权重值修改保存（根据GZBZ修改所有关联数据）
	 */
	public void saveParameterChangeWeigth(QueryCondition condition,List<TbParaHeavy> tphList,List<TbParaGrade> tpgList){
		//根据GZBZ将TB_PARA_HEAVY和tb_para_grade表的数据变为无效
		String s = "update TB_PARA_HEAVY set yxbz='"+GlobalConstants.DIC_YXBZ_2+"' where gzbz = '"+condition.getGzbz()+"' and yxbz='"+GlobalConstants.DIC_YXBZ_1+"' ";
		this.baseDao.executeUpdateBySql(s);
		String s1 = "update tb_para_grade set yxbz='"+GlobalConstants.DIC_YXBZ_2+"' where gzbz = '"+condition.getGzbz()+"' and yxbz='"+GlobalConstants.DIC_YXBZ_1+"' ";
		this.baseDao.executeUpdateBySql(s1);
		String[] tempQzm = condition.getQzm().split(",");
		String[] tempQzz = condition.getQzz().split(",");
		String tempHeavyId = "";
		String newGzbz = this.baseDao.getSequenceValue("SEQ_GZBZ");
		for(TbParaHeavy tph : tphList){
			TbParaHeavy tempTph = new TbParaHeavy();
			org.springframework.beans.BeanUtils.copyProperties(tph, tempTph);
			String id = this.baseDao.getSequenceValue("SEQ_PARA_HEAVY");
			tempTph.setID(Long.parseLong(id));
			if(condition.getQzjb().equals(tph.getQZJB())){
				for(int i=0;i<tempQzm.length;i++){
					if(tph.getQZM().equals(tempQzm[i].trim())){
						tempTph.setQZZ(Double.parseDouble(tempQzz[i]));//权重值
					}
				}	
			}
			if(tph.getQZM().equals("药店费用等级跨越")){
				tempHeavyId = id;
			}
			tempTph.setXGSJ(new Date());//修改时间
			tempTph.setGZBZ(Long.parseLong(newGzbz));//规则标识
			this.baseDao.saveObject(tempTph);
		}
		for(TbParaGrade tpg : tpgList){
			TbParaGrade tempTpg = new TbParaGrade();
			org.springframework.beans.BeanUtils.copyProperties(tpg, tempTpg);
			String id = this.baseDao.getSequenceValue("SEQ_PARA_GRADE");
			tempTpg.setID(Long.parseLong(id));
			tempTpg.setHEAVY_ID(tempHeavyId);
			tempTpg.setXGSJ(new Date());//修改时间
			tempTpg.setGZBZ(Long.parseLong(newGzbz));//规则标识
			this.baseDao.saveObject(tempTpg);
		}
	}

	/**
	 * 根据GZBZ查询TbParaGrade表下的所有有效数据
	 */
	public List<TbParaGrade> queryAllTbParaGradeByGzbz(long gzbz) {
		String sql = "select * from tb_para_grade where yxbz='"+GlobalConstants.DIC_YXBZ_1+"' and gzbz="+gzbz+" ";
		return baseDao.getListBySql(sql, TbParaGrade.class);
	}

	/**
	 * 根据GZBZ查询TbParaHeavy表下的所有有效数据
	 */
	public List<TbParaHeavy> queryAllTbParaHeavyByGzbz(long gzbz,String removeId) {
		String sql = "select * from TB_PARA_HEAVY where yxbz='"+GlobalConstants.DIC_YXBZ_1+"' and gzbz="+gzbz+" ";
		//去除当qzjb=1时的删除数据
		if(StringUtils.isNotEmpty(removeId)){
			String strid = "";
			String[] ids = removeId.split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			sql += " and id not in ("+strid+")";
		}
		return baseDao.getListBySql(sql, TbParaHeavy.class);
	}

	/**
	 * 药店费用等级费用范围修改保存
	 */
	public void saveUpdateCostRange(QueryCondition condition,List<TbParaHeavy> tphList,List<TbParaGrade> tpgList){
		//根据GZBZ将TB_PARA_HEAVY和tb_para_grade表的数据变为无效
		String s = "update TB_PARA_HEAVY set yxbz='"+GlobalConstants.DIC_YXBZ_2+"' where gzbz = '"+condition.getGzbz()+"' and yxbz='"+GlobalConstants.DIC_YXBZ_1+"' ";
		this.baseDao.executeUpdateBySql(s);
		String s1 = "update tb_para_grade set yxbz='"+GlobalConstants.DIC_YXBZ_2+"' where gzbz = '"+condition.getGzbz()+"' and yxbz='"+GlobalConstants.DIC_YXBZ_1+"' ";
		this.baseDao.executeUpdateBySql(s1);
		String[] templev = condition.getMedlev().split(",");
        String[] tempname = condition.getFcfName().split(",");
		String[] tempval = condition.getFcfValue().split(",");
		String[] tempcfname = condition.getCfName().split(",");
		String[] tempcfval = condition.getCfValue().split(",");
		String tempHeavyId = "";
		String newGzbz = this.baseDao.getSequenceValue("SEQ_GZBZ");
		for(TbParaHeavy tph : tphList){
			TbParaHeavy tempTph = new TbParaHeavy();
//			BeanUtils.copyProperties(tph, tempTph);
			org.springframework.beans.BeanUtils.copyProperties(tph, tempTph);
			String id = this.baseDao.getSequenceValue("SEQ_PARA_HEAVY");
			tempTph.setID(Long.parseLong(id));
			if(tph.getQZM().equals("药店费用等级跨越")){
				tempHeavyId = id;
			}
			tempTph.setXGSJ(new Date());//修改时间
			tempTph.setGZBZ(Long.parseLong(newGzbz));//规则标识
			this.baseDao.saveObject(tempTph);
		}
		for(TbParaGrade tpg : tpgList){
			TbParaGrade tempTpg = new TbParaGrade();
//			BeanUtils.copyProperties(tpg, tempTpg);
			org.springframework.beans.BeanUtils.copyProperties(tpg, tempTpg);
			String id = this.baseDao.getSequenceValue("SEQ_PARA_GRADE");
			tempTpg.setID(Long.parseLong(id));
			tempTpg.setHEAVY_ID(tempHeavyId);
			//非处方药
			for(int i=0;i<templev.length;i++){
				if(tempname[i].equals(tpg.getJBM())){
					if(templev[i].equals("1")){
						tempTpg.setXXZ(0.0);//下限值
						tempTpg.setSXZ(Double.parseDouble(tempval[i]));//上限值
					}else if(templev[i].equals("4")){
						tempTpg.setXXZ(Double.parseDouble(tempval[i]));//下限值
						tempTpg.setSXZ(99999999.99);//上限值
					}else{
						String[] tt = tempval[i].split(";");
						tempTpg.setXXZ(Double.parseDouble(tt[0]));//下限值
						tempTpg.setSXZ(Double.parseDouble(tt[1]));//上限值
					}
				}
			}
			//处方药
			for(int i=0;i<templev.length;i++){
				if(tempcfname[i].equals(tpg.getJBM())){
					if(templev[i].equals("1")){
						tempTpg.setXXZ(0.0);//下限值
						tempTpg.setSXZ(Double.parseDouble(tempcfval[i]));//上限值
					}else if(templev[i].equals("4")){
						tempTpg.setXXZ(Double.parseDouble(tempcfval[i]));//下限值
						tempTpg.setSXZ(99999999.99);//上限值
					}else{
						String[] tt = tempcfval[i].split(";");
						tempTpg.setXXZ(Double.parseDouble(tt[0]));//下限值
						tempTpg.setSXZ(Double.parseDouble(tt[1]));//上限值
					}
				}
			}
			tempTpg.setXGSJ(new Date());//修改时间
			tempTpg.setGZBZ(Long.parseLong(newGzbz));//规则标识
			this.baseDao.saveObject(tempTpg);
		}
	}

	/**
	 * 初始化修改页面（tab页）
	 */
	public PageSet updateScore(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append(" select id,qzm,qzdm,qzjb,sjqzid,tcdm,qzz from tb_para_heavy where yxbz='"+GlobalConstants.DIC_YXBZ_1+"' ");
		if(StringUtils.isNotEmpty(condition.getQzdm())){
			exeSQL.append(" and SJQZID = '"+condition.getQzdm()+"' ");
		}
		if(StringUtils.isNotEmpty(condition.getTcdm())){
			exeSQL.append(" and tcdm = '"+condition.getTcdm()+"' ");
		}
		if(StringUtils.isNotEmpty(condition.getQzjb())){
			exeSQL.append(" and qzjb = '"+condition.getQzjb()+"' ");
		}
		exeSQL.append(" and gzbz = '"+condition.getGzbz()+"' ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 分值保存
	 */
	public String saveUpdateScore(QueryCondition condition, List<TbParaHeavy> tphList, List<TbParaGrade> tpgList) {
		//根据GZBZ将TB_PARA_HEAVY和tb_para_grade表的数据变为无效
		String s = "update TB_PARA_HEAVY set yxbz='"+GlobalConstants.DIC_YXBZ_2+"' where gzbz = '"+condition.getGzbz()+"' and yxbz='"+GlobalConstants.DIC_YXBZ_1+"' ";
		this.baseDao.executeUpdateBySql(s);
		String s1 = "update tb_para_grade set yxbz='"+GlobalConstants.DIC_YXBZ_2+"' where gzbz = '"+condition.getGzbz()+"' and yxbz='"+GlobalConstants.DIC_YXBZ_1+"' ";
		this.baseDao.executeUpdateBySql(s1);
		String[] qzm = condition.getQzm().toString().split("-");
		String[] qzz = condition.getQzz().split("-");
		String[] tempQzm = qzm[0].split(",");
		String[] tempQzz = qzz[0].split(",");
		String[] tempQzm1 = qzm[1].split(",");
		String[] tempQzz1 = qzz[1].split(",");
		String tempHeavyId = "";
		String newGzbz = this.baseDao.getSequenceValue("SEQ_GZBZ");
		for(TbParaHeavy tph : tphList){
			TbParaHeavy tempTph = new TbParaHeavy();
			org.springframework.beans.BeanUtils.copyProperties(tph, tempTph);
			String id = this.baseDao.getSequenceValue("SEQ_PARA_HEAVY");
			tempTph.setID(Long.parseLong(id));
			if(condition.getQzjb().equals("8")||condition.getQzjb().equals("10")){
				if(tph.getQZJB().equals("7")||tph.getQZJB().equals("9")){
					for(int i=0;i<tempQzm.length;i++){
						if(tph.getQZM().equals(tempQzm[i].trim())){
							tempTph.setQZZ(Double.parseDouble(tempQzz[i]));//权重值
						}
					}
				}else{
					for(int i=0;i<tempQzm1.length;i++){
						if(tph.getQZM().equals(tempQzm1[i].trim())){
							tempTph.setQZZ(Double.parseDouble(tempQzz1[i]));//权重值
						}
					}
				}
			}
			if(tph.getQZM().equals("药店费用等级跨越")){
				tempHeavyId = id;
			}
			tempTph.setXGSJ(new Date());//修改时间
			tempTph.setGZBZ(Long.parseLong(newGzbz));//规则标识
			this.baseDao.saveObject(tempTph);
		}
		for(TbParaGrade tpg : tpgList){
			TbParaGrade tempTpg = new TbParaGrade();
			org.springframework.beans.BeanUtils.copyProperties(tpg, tempTpg);
			String id = this.baseDao.getSequenceValue("SEQ_PARA_GRADE");
			tempTpg.setID(Long.parseLong(id));
			tempTpg.setHEAVY_ID(tempHeavyId);
			tempTpg.setXGSJ(new Date());//修改时间
			tempTpg.setGZBZ(Long.parseLong(newGzbz));//规则标识
			this.baseDao.saveObject(tempTpg);
		}
		return newGzbz;
	}

	/**
	 * 上一年度药店等级设置（药店信息查询）
	 */
	public PageSet setYdLevel(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append(" select a.*,b.yddj,b.id from kb01 a left join TJ_YD_LEVEL b on a.akb020 = b.akb020 ");
		exeSQL.append("and a.aaa027 = b.aaa027 and b.yxbz = '1' and yddjnd = (to_char(sysdate,'yyyy')-1) where a.akb022 = '2' ");
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQL.append(" and a.aaa027 = '"+condition.getAaa027()+"' ");
		}
		if(StringUtils.isNotEmpty(condition.getAkb020())){
			exeSQL.append(" and a.akb020 = '"+condition.getAkb020()+"' ");
		}
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 药店等级保存或修改
	 */
	public void saveOrUpdYdLevel(QueryCondition queryCondition) {
		if(StringUtils.isNotEmpty(queryCondition.getId())){
			//修改原纪录为无效
			String s = "update TJ_YD_LEVEL set yxbz='"+GlobalConstants.DIC_YXBZ_2+"',xgsj=sysdate where id = '"+queryCondition.getId()+"' and yxbz='"+GlobalConstants.DIC_YXBZ_1+"' ";
			this.baseDao.executeUpdateBySql(s);
		}
		//新增一条新记录
		TjYdLevel tjl = new TjYdLevel();
		String id = this.baseDao.getSequenceValue("SEQ_TJ_YD_LEVEL_ID");
		tjl.setID(Long.parseLong(id));
		tjl.setAaa027(queryCondition.getAaa027());
		tjl.setAkb020(queryCondition.getAkb020());
		tjl.setAkb021(queryCondition.getAkb021());
		tjl.setYXBZ(GlobalConstants.DIC_YXBZ_1);
		tjl.setYddj(queryCondition.getYddj());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(new Date());
		tjl.setYDDJND(String.valueOf(Integer.parseInt(year) - 1));
		tjl.setXgsj(new Date());
//		this.baseDao.saveObject(tjl);
		String sql = "INSERT INTO TJ_YD_LEVEL(ID, AKB020, AAA027, AKB021, YDDJ, YXBZ, XGSJ, YDDJND) ";
		sql += "VALUES("+tjl.getID()+", '"+tjl.getAkb020()+"', '"+tjl.getAaa027()+"', '"+tjl.getAkb021()+"', '"+tjl.getYddj()+"', " +
				"'"+tjl.getYXBZ()+"', sysdate, '"+tjl.getYDDJND()+"')";
		this.baseDao.executeUpdateBySql(sql);
	}

	/**
	 * 药店分数查询
	 */
	public PageSet queryYdFs(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append(" select distinct b.akb021,b.akb020,b.aaa027,b.aka101,a.lbid,a.tjz ");
		exeSQL.append(" from (select tjz,jgid,lbid from TB_STATS_INSTITUTION_INDICATOR where zbid='ZB000015' ");
		if(StringUtils.isNotEmpty(condition.getType())){
			if(condition.getType().equals("2")){
				//按月份查询
				exeSQL.append(" and sjdm = '"+condition.getByMonth()+"'");
			}else{
				//按季度查询
				String qu = condition.getByQuarter();
				String year = qu.substring(0, 4);
				exeSQL.append(" and sjdm = '"+year+"S"+qu.substring(5, 6)+"'");
			}
		}
		exeSQL.append("  group by tjz,jgid,lbid) a,kb01 b where a.jgid = b.akb020 and b.akb022='2' ");
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQL.append(" and b.aaa027 = '"+condition.getAaa027()+"'");
		}
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	public PageSet queryAllQzGrade(PageSet pageSet1, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
		if(condition.getDivId().equals("1")){
			exeSQL.append("select distinct a.id as id,a.jbm as jbm,a.jbdm as jbdm,a.heavy_id,a.tcdm,a.xxz as xxz,a.xxbhdy as xxbhdy,a.xxwx as xxwx,a.sxz as sxz,a.sxbhdy as sxbhdy,a.sxwx as sxwx,a.jbfz,a.jbms ");
			exeSQL.append("from TB_PARA_GRADE a where 1=1 ");
		}else if(condition.getDivId().equals("2")){
			exeSQL.append("select distinct a.id as tid,a.jbm as tjbm,a.jbdm as jbdm,a.heavy_id,a.tcdm,a.xxz as txxz,a.xxbhdy as txxbhdy,a.xxwx as txxwx,a.sxz as tsxz,a.sxbhdy as tsxbhdy,a.sxwx as tsxwx,a.jbfz,a.jbms ");
			exeSQL.append("from TB_PARA_GRADE a where 1=1 ");
		}
		//统筹代码
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQL.append(" and tcdm = '"+condition.getAaa027()+"'");
		}
		exeSQL.append(" and a.yxbz = '"+GlobalConstants.DIC_YXBZ_1+"' ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		exeSQL.append(" order by a.id asc ");
		return this.baseDao.queryBySql(pageSet1,exeSQL.toString(),countSQL.toString());
	}

	public PageSet queryAllQzHeavy(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append(" select * from tb_para_heavy where yxbz = '1' and qzjb <> '11' ");
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQL.append(" and tcdm = '"+condition.getAaa027()+"'");
		}
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	public void resultSetToExcel(PageSet pageSet, PageSet pageSet1,Map aaa027Map, QueryCondition condition) {
		System.out.println("----resultSetToExcel-----------------");
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("application/vnd.ms-excel");
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(aaa027Map.get(condition.getAaa027())+"参数权重信息", "utf-8")+".xls");
			HSSFSheet sheet = workbook.createSheet();
			int iRow = 0;// 行下标
			workbook.setSheetName(0, "参数信息");
			HSSFRow row = sheet.createRow(iRow);
			if(pageSet.getCount()>0){
				HSSFCell cell = null;
				cell = row.createCell(0);
				// 设置字体样式
				HSSFFont font = workbook.createFont();
				font.setFontName(HSSFFont.FONT_ARIAL);// 字体
				font.setFontHeightInPoints((short) 15);// 字号
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
				// font.setColor(HSSFColor.BLUE.index);//颜色
				HSSFCellStyle cellStyle_1 = workbook.createCellStyle(); // 设置单元格样式
				cellStyle_1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle_1.setFont(font);
				cell.setCellStyle(cellStyle_1);
				cell.setCellValue(new HSSFRichTextString("参数权重"));// 设置文件名第一行展示
//				cell.setCellValue(new HSSFRichTextString(xlsName.substring(0,xlsName.length() - 4)));// 设置文件名第一行展示
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
				String[] top = {"参数名称","权重值"};
				iRow = iRow + 1;
				row = sheet.createRow(iRow);
				int nColumn = 2;
				for (int i = 1; i <= nColumn; i++) {
					sheet.setColumnWidth(i - 1, 20 * 256);// 设置单元格宽度：18字符
					cell = row.createCell((i - 1));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top[i - 1].toString()));// 给单元格赋值
				}
				iRow = iRow + 1;
				// 写入各条记录
				for(int i=0;i<pageSet.getCount();i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					if(String.valueOf(map.get("QZJB")).equals(GlobalConstants.DIC_QZJB_1)){
						obj[0] = map.get("QZM");
						obj[1] = map.get("QZZ") + "%";
						row = sheet.createRow(iRow);
						for (int k = 0; k < 2; k++) {
							cell = row.createCell(k);
							if (obj[k] != null) {
								cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
							} else {
								cell.setCellValue(new HSSFRichTextString(" "));
							}
							HSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
							cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
							cellStyle.setWrapText(true);
							cell.setCellStyle(cellStyle);
						}
						iRow = iRow + 1;
					}
				}
				
				HSSFCellStyle cellStyle_2 = workbook.createCellStyle(); // 设置单元格样式
				cellStyle_2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle_2.setFont(font);
				row = sheet.createRow(iRow+2);
				cell = row.createCell(0);
				cell.setCellStyle(cellStyle_2);
				cell.setCellValue(new HSSFRichTextString("增长率详细设置"));// 设置文件名第一行展示
//				cell.setCellValue(new HSSFRichTextString(xlsName.substring(0,xlsName.length() - 4)));// 设置文件名第一行展示
				sheet.addMergedRegion(new CellRangeAddress(iRow+2, iRow+2, 0, 1));
				String[] top1 = {"参数名称","权重值"};
				iRow = iRow + 3;
				row = sheet.createRow(iRow);
				for (int i = 1; i <= 2; i++) {
					sheet.setColumnWidth(i - 1, 20 * 256);// 设置单元格宽度：18字符
					cell = row.createCell((i - 1));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top1[i - 1].toString()));// 给单元格赋值
				}
				
				iRow = iRow + 1;
				// 写入各条记录
				for(int i=0;i<pageSet.getCount();i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					if(String.valueOf(map.get("QZJB")).equals(GlobalConstants.DIC_QZJB_2)){
						obj[0] = map.get("QZM");
						obj[1] = map.get("QZZ") + "%";
						row = sheet.createRow(iRow);
						for (int k = 0; k < 2; k++) {
							cell = row.createCell(k);
							if (obj[k] != null) {
								cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
							} else {
								cell.setCellValue(new HSSFRichTextString(" "));
							}
							HSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
							cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
							cellStyle.setWrapText(true);
							cell.setCellStyle(cellStyle);
						}
						iRow = iRow + 1;
					}
				}
				
				//-------------同比增长率分值
				HSSFCellStyle cellStyle_3 = workbook.createCellStyle(); // 设置单元格样式
				cellStyle_3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle_3.setFont(font);
				row = sheet.createRow(iRow+2);
				HSSFRow rowtmp = row;
				cell = row.createCell(0);
				cell.setCellStyle(cellStyle_3);
				cell.setCellValue(new HSSFRichTextString("同比增长率分值"));// 设置文件名第一行展示
//				cell.setCellValue(new HSSFRichTextString(xlsName.substring(0,xlsName.length() - 4)));// 设置文件名第一行展示
				sheet.addMergedRegion(new CellRangeAddress(iRow+2, iRow+2, 0, 1));
				String[] top2 = {"参数名称","分值"};
				iRow = iRow + 3;
				int tempRow1 = iRow;
				row = sheet.createRow(iRow);
				HSSFRow rowtmp1 = row;
				for (int i = 1; i <= 2; i++) {
					sheet.setColumnWidth(i - 1, 20 * 256);// 设置单元格宽度：18字符
					cell = row.createCell((i - 1));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top2[i - 1].toString()));// 给单元格赋值
				}
				
				iRow = iRow + 1;
				// 写入各条记录
				HSSFRow rowtmp2 = null;
				HSSFRow rowtmp3 = null;
				HSSFRow rowtmp4 = null;
				HSSFRow rowtmp5 = null;
				HSSFRow rowtmp6 = null;
				int j=0;
				for(int i=0;i<pageSet.getCount();i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					if(String.valueOf(map.get("QZJB")).equals(GlobalConstants.DIC_QZJB_3)&&String.valueOf(map.get("SJQZID")).equals("1001")){
						obj[0] = map.get("QZM");
						obj[1] = map.get("QZZ");
						row = sheet.createRow(iRow);
						if(j==0){
							rowtmp2 = row;
						}else if(j==1){
							rowtmp3 = row;
						}else if(j==2){
							rowtmp4 = row;
						}else if(j==3){
							rowtmp5 = row;
						}else if(j==4){
							rowtmp6 = row;
						}
						j++;
						for (int k = 0; k < 2; k++) {
							cell = row.createCell(k);
							if (obj[k] != null) {
								obj[k] = obj[k].toString().replace("&lt;", "<");
								obj[k] = obj[k].toString().replace("&gt;", ">");
								cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
							} else {
								cell.setCellValue(new HSSFRichTextString(" "));
							}
							HSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
							cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
							cellStyle.setWrapText(true);
							cell.setCellStyle(cellStyle);
						}
						iRow = iRow + 1;
					}
				}
				
				//-------------环比增长率分值
				HSSFCellStyle cellStyle_4 = workbook.createCellStyle(); // 设置单元格样式
				cellStyle_4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle_4.setFont(font);
				cell = rowtmp.createCell(3);
				cell.setCellStyle(cellStyle_4);
				cell.setCellValue(new HSSFRichTextString("环比增长率分值"));// 设置文件名第一行展示
				sheet.addMergedRegion(new CellRangeAddress(tempRow1-1, tempRow1-1, 3, 4));
				String[] top3 = {"参数名称","分值"};
				for (int i = 1; i <= 2; i++) {
					sheet.setColumnWidth(i + 2, 20 * 256);// 设置单元格宽度：18字符
					cell = rowtmp1.createCell((i + 2));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top3[i - 1].toString()));// 给单元格赋值
				}
				
				// 写入各条记录
				int m=0;
				for(int i=0;i<pageSet.getCount();i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					if(String.valueOf(map.get("QZJB")).equals(GlobalConstants.DIC_QZJB_3)&&String.valueOf(map.get("SJQZID")).equals("1002")){
						obj[0] = map.get("QZM");
						obj[1] = map.get("QZZ");
						HSSFRow tmp = null;
						if(m==0){
							tmp = rowtmp2;
						}else if(m==1){
							tmp = rowtmp3;
						}else if(m==2){
							tmp = rowtmp4;
						}else if(m==3){
							tmp = rowtmp5;
						}else if(m==4){
							tmp = rowtmp6;
						}
						m++;
						for (int k = 0; k < 2; k++) {
							cell = tmp.createCell(k+3);
							if (obj[k] != null) {
								obj[k] = obj[k].toString().replace("&lt;", "<");
								obj[k] = obj[k].toString().replace("&gt;", ">");
								cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
							} else {
								cell.setCellValue(new HSSFRichTextString(" "));
							}
							HSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
							cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
							cellStyle.setWrapText(true);
							cell.setCellStyle(cellStyle);
						}
					}
				}
				
				//-------------与同类药店平均增长率差值分值
				HSSFCellStyle cellStyle_5 = workbook.createCellStyle(); // 设置单元格样式
				cellStyle_5.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle_5.setFont(font);
				cell = rowtmp.createCell(6);
				cell.setCellStyle(cellStyle_5);
				cell.setCellValue(new HSSFRichTextString("与同类药店平均增长率差值分值"));// 设置文件名第一行展示
				sheet.addMergedRegion(new CellRangeAddress(tempRow1-1, tempRow1-1, 6, 7));
				String[] top4 = {"参数名称","分值"};
				for (int i = 1; i <= 2; i++) {
					sheet.setColumnWidth(i + 5, 20 * 256);// 设置单元格宽度：18字符
					cell = rowtmp1.createCell((i + 5));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top4[i - 1].toString()));// 给单元格赋值
				}
				
				// 写入各条记录
				m=0;
				for(int i=0;i<pageSet.getCount();i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					if(String.valueOf(map.get("QZJB")).equals(GlobalConstants.DIC_QZJB_3)&&String.valueOf(map.get("SJQZID")).equals("1003")){
						obj[0] = map.get("QZM");
						obj[1] = map.get("QZZ");
						HSSFRow tmp = null;
						if(m==0){
							tmp = rowtmp2;
						}else if(m==1){
							tmp = rowtmp3;
						}else if(m==2){
							tmp = rowtmp4;
						}else if(m==3){
							tmp = rowtmp5;
						}else if(m==4){
							tmp = rowtmp6;
						}
						m++;
						for (int k = 0; k < 2; k++) {
							cell = tmp.createCell(k+6);
							if (obj[k] != null) {
								obj[k] = obj[k].toString().replace("&lt;", "<");
								obj[k] = obj[k].toString().replace("&gt;", ">");
								cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
							} else {
								cell.setCellValue(new HSSFRichTextString(" "));
							}
							HSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
							cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
							cellStyle.setWrapText(true);
							cell.setCellStyle(cellStyle);
						}
					}
				}
				
				//-------------与自己去年平均值比较增长率分值
				HSSFCellStyle cellStyle_6 = workbook.createCellStyle(); // 设置单元格样式
				cellStyle_6.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle_6.setFont(font);
				cell = rowtmp.createCell(9);
				cell.setCellStyle(cellStyle_6);
				cell.setCellValue(new HSSFRichTextString("与自己去年平均值比较增长率分值"));// 设置文件名第一行展示
				sheet.addMergedRegion(new CellRangeAddress(tempRow1-1, tempRow1-1, 9, 10));
				String[] top5 = {"参数名称","分值"};
				for (int i = 1; i <= 2; i++) {
					sheet.setColumnWidth(i + 8, 20 * 256);// 设置单元格宽度：18字符
					cell = rowtmp1.createCell((i + 8));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top5[i - 1].toString()));// 给单元格赋值
				}
				
				// 写入各条记录
				m=0;
				for(int i=0;i<pageSet.getCount();i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					if(String.valueOf(map.get("QZJB")).equals(GlobalConstants.DIC_QZJB_3)&&String.valueOf(map.get("SJQZID")).equals("1004")){
						obj[0] = map.get("QZM");
						obj[1] = map.get("QZZ");
						HSSFRow tmp = null;
						if(m==0){
							tmp = rowtmp2;
						}else if(m==1){
							tmp = rowtmp3;
						}else if(m==2){
							tmp = rowtmp4;
						}else if(m==3){
							tmp = rowtmp5;
						}else if(m==4){
							tmp = rowtmp6;
						}
						m++;
						for (int k = 0; k < 2; k++) {
							cell = tmp.createCell(k+9);
							if (obj[k] != null) {
								obj[k] = obj[k].toString().replace("&lt;", "<");
								obj[k] = obj[k].toString().replace("&gt;", ">");
								cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
							} else {
								cell.setCellValue(new HSSFRichTextString(" "));
							}
							HSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
							cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
							cellStyle.setWrapText(true);
							cell.setCellStyle(cellStyle);
						}
					}
				}
				
				//药店费用等级跨越详细设置pageSet1
				HSSFCellStyle cellStyle_7 = workbook.createCellStyle(); // 设置单元格样式
				cellStyle_7.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle_7.setFont(font);
				row = sheet.createRow(iRow+2);
				cell = row.createCell(0);
				cell.setCellStyle(cellStyle_7);
				cell.setCellValue(new HSSFRichTextString("药店费用等级跨越详细设置"));// 设置文件名第一行展示
//				cell.setCellValue(new HSSFRichTextString(xlsName.substring(0,xlsName.length() - 4)));// 设置文件名第一行展示
				sheet.addMergedRegion(new CellRangeAddress(iRow+2, iRow+2, 0, 4));
				String[] top6 = {"等级","非处方药分类","非处方药费用","处方药分类","处方药费用"};
				iRow = iRow + 3;
				row = sheet.createRow(iRow);
				for (int i = 1; i <= 5; i++) {
					sheet.setColumnWidth(i - 1, 20 * 256);// 设置单元格宽度：18字符
					cell = row.createCell((i - 1));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top6[i - 1].toString()));// 给单元格赋值
				}
				
				iRow = iRow + 1;
				// 写入各条记录
				for(int i=0;i<pageSet1.getCount()/2;i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet1.getData().get(i);
					obj[0] = map.get("JBDM");
					obj[1] = map.get("JBM");
					if(String.valueOf(map.get("JBDM")).equals("4")){
						obj[2] = ">=" + map.get("XXZ") + "万";
					}else if(String.valueOf(map.get("JBDM")).equals("3")||String.valueOf(map.get("JBDM")).equals("2")){
						obj[2] = ">=" + map.get("XXZ") + "万" + "and" + "<" + map.get("SXZ") + "万";
					}else if(String.valueOf(map.get("JBDM")).equals("1")){
						obj[2] = "<" + map.get("SXZ") + "万";
					}
					obj[3] = map.get("TJBM");
					if(String.valueOf(map.get("JBDM")).equals("4")){
						obj[4] = ">=" + map.get("TXXZ") + "万";
					}else if(String.valueOf(map.get("JBDM")).equals("3")||String.valueOf(map.get("JBDM")).equals("2")){
						obj[4] = ">=" + map.get("TXXZ") + "万" + "and" + "<" + map.get("TSXZ") + "万";
					}else if(String.valueOf(map.get("JBDM")).equals("1")){
						obj[4] = "<" + map.get("TSXZ") + "万";
					}
					row = sheet.createRow(iRow);
					for (int k = 0; k < 5; k++) {
						cell = row.createCell(k);
						if (obj[k] != null) {
							cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
						} else {
							cell.setCellValue(new HSSFRichTextString(" "));
						}
						HSSFCellStyle cellStyle = workbook.createCellStyle();
						cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
						cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
						cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
						cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
						cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
						cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
						cellStyle.setWrapText(true);
						cell.setCellStyle(cellStyle);
					}
					iRow = iRow + 1;
				}
				
				//药店费用等级跨越详细设置pageSet1
				HSSFCellStyle cellStyle_8 = workbook.createCellStyle(); // 设置单元格样式
				cellStyle_8.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle_8.setFont(font);
				row = sheet.createRow(iRow+2);
				cell = row.createCell(0);
				cell.setCellStyle(cellStyle_8);
				cell.setCellValue(new HSSFRichTextString("药店费用等级跨越等级差值分值（处方药）"));// 设置文件名第一行展示
//				cell.setCellValue(new HSSFRichTextString(xlsName.substring(0,xlsName.length() - 4)));// 设置文件名第一行展示
				sheet.addMergedRegion(new CellRangeAddress(iRow+2, iRow+2, 0, 10));
				String[] top7 = {"等级差值（等级4）","分值"};
				iRow = iRow + 3;
				row = sheet.createRow(iRow);
				rowtmp1 = row;
				for (int i = 1; i <= 2; i++) {
					sheet.setColumnWidth(i - 1, 20 * 256);// 设置单元格宽度：18字符
					cell = row.createCell((i - 1));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top7[i - 1].toString()));// 给单元格赋值
				}
				
				iRow = iRow + 1;
				// 写入各条记录
				rowtmp2 = null;
				rowtmp3 = null;
				rowtmp4 = null;
				rowtmp5 = null;
				rowtmp6 = null;
				j = 0;
				for(int i=0;i<pageSet.getCount();i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					if(String.valueOf(map.get("QZJB")).equals(GlobalConstants.DIC_QZJB_9)&&String.valueOf(map.get("SJQZID")).equals("4")){
						obj[0] = map.get("QZM");
						obj[1] = map.get("QZZ");
						row = sheet.createRow(iRow);
						if(j==0){
							rowtmp2 = row;
						}else if(j==1){
							rowtmp3 = row;
						}else if(j==2){
							rowtmp4 = row;
						}else if(j==3){
							rowtmp5 = row;
						}else if(j==4){
							rowtmp6 = row;
						}
						j++;
						for (int k = 0; k < 2; k++) {
							cell = row.createCell(k);
							if (obj[k] != null) {
								obj[k] = obj[k].toString().replace("&lt;", "<");
								obj[k] = obj[k].toString().replace("&gt;", ">");
								cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
							} else {
								cell.setCellValue(new HSSFRichTextString(" "));
							}
							HSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
							cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
							cellStyle.setWrapText(true);
							cell.setCellStyle(cellStyle);
						}
						iRow = iRow + 1;
					}
				}
				
				String[] top8 = {"等级差值（等级3）","分值"};
				for (int i = 1; i <= 2; i++) {
					sheet.setColumnWidth(i + 2, 20 * 256);// 设置单元格宽度：18字符
					cell = rowtmp1.createCell((i + 2));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top8[i - 1].toString()));// 给单元格赋值
				}
				// 写入各条记录
				m=0;
				for(int i=0;i<pageSet.getCount();i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					if(String.valueOf(map.get("QZJB")).equals(GlobalConstants.DIC_QZJB_9)&&String.valueOf(map.get("SJQZID")).equals("3")){
						obj[0] = map.get("QZM");
						obj[1] = map.get("QZZ");
						HSSFRow tmp = null;
						if(m==0){
							tmp = rowtmp2;
						}else if(m==1){
							tmp = rowtmp3;
						}else if(m==2){
							tmp = rowtmp4;
						}else if(m==3){
							tmp = rowtmp5;
						}else if(m==4){
							tmp = rowtmp6;
						}
						m++;
						for (int k = 0; k < 2; k++) {
							cell = tmp.createCell(k+3);
							if (obj[k] != null) {
								obj[k] = obj[k].toString().replace("&lt;", "<");
								obj[k] = obj[k].toString().replace("&gt;", ">");
								cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
							} else {
								cell.setCellValue(new HSSFRichTextString(" "));
							}
							HSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
							cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
							cellStyle.setWrapText(true);
							cell.setCellStyle(cellStyle);
						}
					}
				}
				
				String[] top9 = {"等级差值（等级2）","分值"};
				for (int i = 1; i <= 2; i++) {
					sheet.setColumnWidth(i + 5, 20 * 256);// 设置单元格宽度：18字符
					cell = rowtmp1.createCell((i + 5));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top9[i - 1].toString()));// 给单元格赋值
				}
				// 写入各条记录
				m=0;
				for(int i=0;i<pageSet.getCount();i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					if(String.valueOf(map.get("QZJB")).equals(GlobalConstants.DIC_QZJB_9)&&String.valueOf(map.get("SJQZID")).equals("2")){
						obj[0] = map.get("QZM");
						obj[1] = map.get("QZZ");
						HSSFRow tmp = null;
						if(m==0){
							tmp = rowtmp2;
						}else if(m==1){
							tmp = rowtmp3;
						}else if(m==2){
							tmp = rowtmp4;
						}else if(m==3){
							tmp = rowtmp5;
						}else if(m==4){
							tmp = rowtmp6;
						}
						m++;
						for (int k = 0; k < 2; k++) {
							cell = tmp.createCell(k+6);
							if (obj[k] != null) {
								obj[k] = obj[k].toString().replace("&lt;", "<");
								obj[k] = obj[k].toString().replace("&gt;", ">");
								cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
							} else {
								cell.setCellValue(new HSSFRichTextString(" "));
							}
							HSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
							cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
							cellStyle.setWrapText(true);
							cell.setCellStyle(cellStyle);
						}
					}
				}
				
				String[] top10 = {"等级差值（等级1）","分值"};
				for (int i = 1; i <= 2; i++) {
					sheet.setColumnWidth(i + 8, 20 * 256);// 设置单元格宽度：18字符
					cell = rowtmp1.createCell((i + 8));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top10[i - 1].toString()));// 给单元格赋值
				}
				// 写入各条记录
				m=0;
				for(int i=0;i<pageSet.getCount();i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					if(String.valueOf(map.get("QZJB")).equals(GlobalConstants.DIC_QZJB_9)&&String.valueOf(map.get("SJQZID")).equals("1")){
						obj[0] = map.get("QZM");
						obj[1] = map.get("QZZ");
						HSSFRow tmp = null;
						if(m==0){
							tmp = rowtmp2;
						}else if(m==1){
							tmp = rowtmp3;
						}else if(m==2){
							tmp = rowtmp4;
						}else if(m==3){
							tmp = rowtmp5;
						}else if(m==4){
							tmp = rowtmp6;
						}
						m++;
						for (int k = 0; k < 2; k++) {
							cell = tmp.createCell(k+9);
							if (obj[k] != null) {
								obj[k] = obj[k].toString().replace("&lt;", "<");
								obj[k] = obj[k].toString().replace("&gt;", ">");
								cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
							} else {
								cell.setCellValue(new HSSFRichTextString(" "));
							}
							HSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
							cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
							cellStyle.setWrapText(true);
							cell.setCellStyle(cellStyle);
						}
					}
				}
				
				HSSFCellStyle cellStyle_9 = workbook.createCellStyle(); // 设置单元格样式
				cellStyle_9.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle_9.setFont(font);
				row = sheet.createRow(iRow+2);
				cell = row.createCell(0);
				cell.setCellStyle(cellStyle_9);
				cell.setCellValue(new HSSFRichTextString("药店费用等级跨越等级差值分值（非处方药）"));// 设置文件名第一行展示
//				cell.setCellValue(new HSSFRichTextString(xlsName.substring(0,xlsName.length() - 4)));// 设置文件名第一行展示
				sheet.addMergedRegion(new CellRangeAddress(iRow+2, iRow+2, 0, 10));
				iRow = iRow + 3;
				row = sheet.createRow(iRow);
				rowtmp1 = row;
				for (int i = 1; i <= 2; i++) {
					sheet.setColumnWidth(i - 1, 20 * 256);// 设置单元格宽度：18字符
					cell = row.createCell((i - 1));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top7[i - 1].toString()));// 给单元格赋值
				}
				
				iRow = iRow + 1;
				// 写入各条记录
				rowtmp2 = null;
				rowtmp3 = null;
				rowtmp4 = null;
				rowtmp5 = null;
				rowtmp6 = null;
				j = 0;
				for(int i=0;i<pageSet.getCount();i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					if(String.valueOf(map.get("QZJB")).equals(GlobalConstants.DIC_QZJB_10)&&String.valueOf(map.get("SJQZID")).equals("4")){
						obj[0] = map.get("QZM");
						obj[1] = map.get("QZZ");
						row = sheet.createRow(iRow);
						if(j==0){
							rowtmp2 = row;
						}else if(j==1){
							rowtmp3 = row;
						}else if(j==2){
							rowtmp4 = row;
						}else if(j==3){
							rowtmp5 = row;
						}else if(j==4){
							rowtmp6 = row;
						}
						j++;
						for (int k = 0; k < 2; k++) {
							cell = row.createCell(k);
							if (obj[k] != null) {
								obj[k] = obj[k].toString().replace("&lt;", "<");
								obj[k] = obj[k].toString().replace("&gt;", ">");
								cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
							} else {
								cell.setCellValue(new HSSFRichTextString(" "));
							}
							HSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
							cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
							cellStyle.setWrapText(true);
							cell.setCellStyle(cellStyle);
						}
						iRow = iRow + 1;
					}
				}
				
				for (int i = 1; i <= 2; i++) {
					sheet.setColumnWidth(i + 2, 20 * 256);// 设置单元格宽度：18字符
					cell = rowtmp1.createCell((i + 2));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top8[i - 1].toString()));// 给单元格赋值
				}
				// 写入各条记录
				m=0;
				for(int i=0;i<pageSet.getCount();i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					if(String.valueOf(map.get("QZJB")).equals(GlobalConstants.DIC_QZJB_10)&&String.valueOf(map.get("SJQZID")).equals("3")){
						obj[0] = map.get("QZM");
						obj[1] = map.get("QZZ");
						HSSFRow tmp = null;
						if(m==0){
							tmp = rowtmp2;
						}else if(m==1){
							tmp = rowtmp3;
						}else if(m==2){
							tmp = rowtmp4;
						}else if(m==3){
							tmp = rowtmp5;
						}else if(m==4){
							tmp = rowtmp6;
						}
						m++;
						for (int k = 0; k < 2; k++) {
							cell = tmp.createCell(k+3);
							if (obj[k] != null) {
								obj[k] = obj[k].toString().replace("&lt;", "<");
								obj[k] = obj[k].toString().replace("&gt;", ">");
								cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
							} else {
								cell.setCellValue(new HSSFRichTextString(" "));
							}
							HSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
							cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
							cellStyle.setWrapText(true);
							cell.setCellStyle(cellStyle);
						}
					}
				}
				
				for (int i = 1; i <= 2; i++) {
					sheet.setColumnWidth(i + 5, 20 * 256);// 设置单元格宽度：18字符
					cell = rowtmp1.createCell((i + 5));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top9[i - 1].toString()));// 给单元格赋值
				}
				// 写入各条记录
				m=0;
				for(int i=0;i<pageSet.getCount();i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					if(String.valueOf(map.get("QZJB")).equals(GlobalConstants.DIC_QZJB_10)&&String.valueOf(map.get("SJQZID")).equals("2")){
						obj[0] = map.get("QZM");
						obj[1] = map.get("QZZ");
						HSSFRow tmp = null;
						if(m==0){
							tmp = rowtmp2;
						}else if(m==1){
							tmp = rowtmp3;
						}else if(m==2){
							tmp = rowtmp4;
						}else if(m==3){
							tmp = rowtmp5;
						}else if(m==4){
							tmp = rowtmp6;
						}
						m++;
						for (int k = 0; k < 2; k++) {
							cell = tmp.createCell(k+6);
							if (obj[k] != null) {
								obj[k] = obj[k].toString().replace("&lt;", "<");
								obj[k] = obj[k].toString().replace("&gt;", ">");
								cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
							} else {
								cell.setCellValue(new HSSFRichTextString(" "));
							}
							HSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
							cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
							cellStyle.setWrapText(true);
							cell.setCellStyle(cellStyle);
						}
					}
				}
				
				for (int i = 1; i <= 2; i++) {
					sheet.setColumnWidth(i + 8, 20 * 256);// 设置单元格宽度：18字符
					cell = rowtmp1.createCell((i + 8));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top10[i - 1].toString()));// 给单元格赋值
				}
				// 写入各条记录
				m=0;
				for(int i=0;i<pageSet.getCount();i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					if(String.valueOf(map.get("QZJB")).equals(GlobalConstants.DIC_QZJB_10)&&String.valueOf(map.get("SJQZID")).equals("1")){
						obj[0] = map.get("QZM");
						obj[1] = map.get("QZZ");
						HSSFRow tmp = null;
						if(m==0){
							tmp = rowtmp2;
						}else if(m==1){
							tmp = rowtmp3;
						}else if(m==2){
							tmp = rowtmp4;
						}else if(m==3){
							tmp = rowtmp5;
						}else if(m==4){
							tmp = rowtmp6;
						}
						m++;
						for (int k = 0; k < 2; k++) {
							cell = tmp.createCell(k+9);
							if (obj[k] != null) {
								obj[k] = obj[k].toString().replace("&lt;", "<");
								obj[k] = obj[k].toString().replace("&gt;", ">");
								cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
							} else {
								cell.setCellValue(new HSSFRichTextString(" "));
							}
							HSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
							cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
							cellStyle.setWrapText(true);
							cell.setCellStyle(cellStyle);
						}
					}
				}
				
				//药店费用等级跨越 处方药非处方药权重设置 
				HSSFCellStyle cellStyle_10 = workbook.createCellStyle(); // 设置单元格样式
				cellStyle_10.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle_10.setFont(font);
				row = sheet.createRow(iRow+2);
				cell = row.createCell(0);
				cell.setCellStyle(cellStyle_10);
				cell.setCellValue(new HSSFRichTextString("药店费用等级跨越 处方药非处方药权重设置"));// 设置文件名第一行展示
//				cell.setCellValue(new HSSFRichTextString(xlsName.substring(0,xlsName.length() - 4)));// 设置文件名第一行展示
				sheet.addMergedRegion(new CellRangeAddress(iRow+2, iRow+2, 0, 1));
				String[] top11 = {"参数名称","权重值"};
				iRow = iRow + 3;
				row = sheet.createRow(iRow);
				for (int i = 1; i <= 2; i++) {
					sheet.setColumnWidth(i - 1, 20 * 256);// 设置单元格宽度：18字符
					cell = row.createCell((i - 1));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top11[i - 1].toString()));// 给单元格赋值
				}
				
				iRow = iRow + 1;
				// 写入各条记录
				for(int i=0;i<pageSet.getCount();i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					if(String.valueOf(map.get("QZJB")).equals(GlobalConstants.DIC_QZJB_4)){
						obj[0] = map.get("QZM");
						obj[1] = map.get("QZZ") + "%";
						row = sheet.createRow(iRow);
						for (int k = 0; k < 2; k++) {
							cell = row.createCell(k);
							if (obj[k] != null) {
								cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
							} else {
								cell.setCellValue(new HSSFRichTextString(" "));
							}
							HSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
							cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
							cellStyle.setWrapText(true);
							cell.setCellStyle(cellStyle);
						}
						iRow = iRow + 1;
					}
				}
				
				HSSFCellStyle cellStyle_11 = workbook.createCellStyle(); // 设置单元格样式
				cellStyle_11.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle_11.setFont(font);
				row = sheet.createRow(iRow+2);
				cell = row.createCell(0);
				cell.setCellStyle(cellStyle_11);
				cell.setCellValue(new HSSFRichTextString("单次非处方购药费用占比"));// 设置文件名第一行展示
//				cell.setCellValue(new HSSFRichTextString(xlsName.substring(0,xlsName.length() - 4)));// 设置文件名第一行展示
				sheet.addMergedRegion(new CellRangeAddress(iRow+2, iRow+2, 0, 4));
				iRow = iRow + 3;
				row = sheet.createRow(iRow);
				rowtmp1 = row;
				String[] top12 = {"增长率（当年账户购药）","分值"};
				for (int i = 1; i <= 2; i++) {
					sheet.setColumnWidth(i - 1, 20 * 256);// 设置单元格宽度：18字符
					cell = row.createCell((i - 1));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top12[i - 1].toString()));// 给单元格赋值
				}
				
				iRow = iRow + 1;
				// 写入各条记录
				rowtmp2 = null;
				rowtmp3 = null;
				rowtmp4 = null;
				rowtmp5 = null;
				rowtmp6 = null;
				HSSFRow rowtmp7 = null;
				j = 0;
				for(int i=0;i<pageSet.getCount();i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					if(String.valueOf(map.get("QZJB")).equals(GlobalConstants.DIC_QZJB_7)){
						obj[0] = map.get("QZM");
						obj[1] = map.get("QZZ");
						row = sheet.createRow(iRow);
						if(j==0){
							rowtmp2 = row;
						}else if(j==1){
							rowtmp3 = row;
						}else if(j==2){
							rowtmp4 = row;
						}else if(j==3){
							rowtmp5 = row;
						}else if(j==4){
							rowtmp6 = row;
						}else if(j==5){
							rowtmp7 = row;
						}
						j++;
						for (int k = 0; k < 2; k++) {
							cell = row.createCell(k);
							if (obj[k] != null) {
								obj[k] = obj[k].toString().replace("&lt;", "<");
								obj[k] = obj[k].toString().replace("&gt;", ">");
								cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
							} else {
								cell.setCellValue(new HSSFRichTextString(" "));
							}
							HSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
							cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
							cellStyle.setWrapText(true);
							cell.setCellStyle(cellStyle);
						}
						iRow = iRow + 1;
					}
				}
				String[] top13 = {"增长率（历年账户购药）","分值"};
				for (int i = 1; i <= 2; i++) {
					sheet.setColumnWidth(i + 2, 20 * 256);// 设置单元格宽度：18字符
					cell = rowtmp1.createCell((i + 2));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top13[i - 1].toString()));// 给单元格赋值
				}
				// 写入各条记录
				m=0;
				for(int i=0;i<pageSet.getCount();i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					if(String.valueOf(map.get("QZJB")).equals(GlobalConstants.DIC_QZJB_8)){
						obj[0] = map.get("QZM");
						obj[1] = map.get("QZZ");
						HSSFRow tmp = null;
						if(m==0){
							tmp = rowtmp2;
						}else if(m==1){
							tmp = rowtmp3;
						}else if(m==2){
							tmp = rowtmp4;
						}else if(m==3){
							tmp = rowtmp5;
						}else if(m==4){
							tmp = rowtmp6;
						}else if(m==5){
							tmp = rowtmp7;
						}
						m++;
						for (int k = 0; k < 2; k++) {
							cell = tmp.createCell(k+3);
							if (obj[k] != null) {
								obj[k] = obj[k].toString().replace("&lt;", "<");
								obj[k] = obj[k].toString().replace("&gt;", ">");
								cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
							} else {
								cell.setCellValue(new HSSFRichTextString(" "));
							}
							HSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
							cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
							cellStyle.setWrapText(true);
							cell.setCellStyle(cellStyle);
						}
					}
				}
				
				HSSFCellStyle cellStyle_12 = workbook.createCellStyle(); // 设置单元格样式
				cellStyle_12.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle_12.setFont(font);
				row = sheet.createRow(iRow+2);
				cell = row.createCell(0);
				cell.setCellStyle(cellStyle_12);
				cell.setCellValue(new HSSFRichTextString("费用额设置"));// 设置文件名第一行展示
				sheet.addMergedRegion(new CellRangeAddress(iRow+2, iRow+2, 0, 1));
				String[] top14 = {"参数名称","费用"};
				iRow = iRow + 3;
				row = sheet.createRow(iRow);
				for (int i = 1; i <= nColumn; i++) {
					sheet.setColumnWidth(i - 1, 20 * 256);// 设置单元格宽度：18字符
					cell = row.createCell((i - 1));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top14[i - 1].toString()));// 给单元格赋值
				}
				iRow = iRow + 1;
				// 写入各条记录
				for(int i=0;i<pageSet.getCount();i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					if(String.valueOf(map.get("QZJB")).equals(GlobalConstants.DIC_QZJB_6)){
						obj[0] = map.get("QZM");
						obj[1] = map.get("QZZ") + "元";
						row = sheet.createRow(iRow);
						for (int k = 0; k < 2; k++) {
							cell = row.createCell(k);
							if (obj[k] != null) {
								cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
							} else {
								cell.setCellValue(new HSSFRichTextString(" "));
							}
							HSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
							cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
							cellStyle.setWrapText(true);
							cell.setCellStyle(cellStyle);
						}
						iRow = iRow + 1;
					}
				}
				
				
				HSSFCellStyle cellStyle_13 = workbook.createCellStyle(); // 设置单元格样式
				cellStyle_13.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle_13.setFont(font);
				row = sheet.createRow(iRow+2);
				cell = row.createCell(0);
				cell.setCellStyle(cellStyle_13);
				cell.setCellValue(new HSSFRichTextString("当年账户历年账户权重设置"));// 设置文件名第一行展示
				sheet.addMergedRegion(new CellRangeAddress(iRow+2, iRow+2, 0, 1));
				String[] top15 = {"参数名称","权重"};
				iRow = iRow + 3;
				row = sheet.createRow(iRow);
				for (int i = 1; i <= nColumn; i++) {
					sheet.setColumnWidth(i - 1, 20 * 256);// 设置单元格宽度：18字符
					cell = row.createCell((i - 1));
					HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(top15[i - 1].toString()));// 给单元格赋值
				}
				iRow = iRow + 1;
				// 写入各条记录
				for(int i=0;i<pageSet.getCount();i++){
					Object[] obj = new Object[10];
					Map map=new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					if(String.valueOf(map.get("QZJB")).equals(GlobalConstants.DIC_QZJB_5)){
						obj[0] = map.get("QZM");
						obj[1] = map.get("QZZ") + "%";
						row = sheet.createRow(iRow);
						for (int k = 0; k < 2; k++) {
							cell = row.createCell(k);
							if (obj[k] != null) {
								cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
							} else {
								cell.setCellValue(new HSSFRichTextString(" "));
							}
							HSSFCellStyle cellStyle = workbook.createCellStyle();
							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
							cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
							cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
							cellStyle.setWrapText(true);
							cell.setCellStyle(cellStyle);
						}
						iRow = iRow + 1;
					}
				}
			}
			ServletOutputStream fOut = response.getOutputStream();
			
			System.out.println("--------------endend-------------");
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (Exception e) {
		}
	}

	/**
	 * 药店药品使用排名（按药店查询）
	 */
	public PageSet ydYpsyRankingYd(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append(" select * from tb_para_heavy where yxbz = '1' and qzjb <> '11' ");
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQL.append(" and tcdm = '"+condition.getAaa027()+"'");
		}
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	public PageSet pharmacyMaintenance(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQl = new StringBuffer();
		StringBuffer countSQL = new StringBuffer();
		
		exeSQl.append("select a.qssj as starttime, a.ddjg_id as ydids, a.name as akb021, a.valid_flag, a.zipcode from d_ddjg a ");
		
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQl.append(" where a.zipcode = '"+condition.getAaa027()+"'");
		}
		
		countSQL.append(" select count(*) from (");
		countSQL.append(exeSQl.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet, exeSQl.toString(), countSQL.toString());
	}

	public PageSet ydTotalStatistics(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQl = new StringBuffer();
		StringBuffer countSQL = new StringBuffer();
		exeSQl.append("select * from(select a.yd_id, a.fcfrc, a.fcffy, a.cfrc, a.cffy, a.fyze, " +
				"b.name, b.admin_region_id as aaa027, (a.fcfrc + a.cfrc) as zrc " +
				"from f_pharm_pay_yd_2 a, d_ddjg b where b.ddjg_id = a.yd_id ");
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQl.append(" and b.admin_region_id = '"+condition.getAaa027()+"'");
		}
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			String date = condition.getStartDate();
			String startDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny >= '"+startDate+"'");
		}
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			String date = condition.getEndDate();
			String endDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny <= '"+endDate+"'");
		}
		if(StringUtils.isNotEmpty(condition.getYdIds())){
			exeSQl.append(" and a.yd_id = '"+condition.getYdIds()+"'");
		}
		exeSQl.append(" order by a.fyze desc) where rownum <= 100 "); 
		countSQL.append(" select count(*) from (");
		countSQL.append(exeSQl.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet, exeSQl.toString(), countSQL.toString());
	}

	public PageSet ydMonthMove(PageSet pageSet, QueryCondition condition) {
		
		StringBuffer exeSQl = new StringBuffer();
		StringBuffer countSQL = new StringBuffer();
		exeSQl.append("select a.yd_id, a.jsny, b.name, b.admin_region_id as aaa027, " +
				"round(a.cfrc/(add_months(trunc(to_date(a.jsny,'YYYYMM'),'MM'),1)-trunc(to_date(a.jsny,'YYYYMM'),'MM')),0) as pjcfrc, " +
				"round(a.cffy/(add_months(trunc(to_date(a.jsny,'YYYYMM'),'MM'),1)-trunc(to_date(a.jsny,'YYYYMM'),'MM')),4) as pjcffy, " +
				"round(a.fcffy/(add_months(trunc(to_date(a.jsny,'YYYYMM'),'MM'),1)-trunc(to_date(a.jsny,'YYYYMM'),'MM')),4) as pjfcffy, " +
				"round(a.fcfrc/(add_months(trunc(to_date(a.jsny,'YYYYMM'),'MM'),1)-trunc(to_date(a.jsny,'YYYYMM'),'MM')),0) as pjfcfrc " +
				"from F_PHARM_PAY_YD_2 a, d_ddjg b where b.ddjg_id = a.yd_id ");
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQl.append(" and b.admin_region_id = '"+condition.getAaa027()+"'");
		}
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			String date = condition.getStartDate();
			String startDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny >= '"+startDate+"'");
		}
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			String date = condition.getEndDate();
			String endDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny <= '"+endDate+"'");
		}
		if(StringUtils.isNotEmpty(condition.getYdIds())){
			exeSQl.append(" and a.yd_id = '"+condition.getYdIds()+"'");
		}
		exeSQl.append(" and rownum <= 100 ");
		countSQL.append(" select count(*) from (");
		countSQL.append(exeSQl.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet, exeSQl.toString(), countSQL.toString());
	}

	public PageSet ydCostStatistics(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQl = new StringBuffer();
		StringBuffer countSQL = new StringBuffer();
		exeSQl.append("select * from (select a.yd_id,  max(b.name) as ydName, max(b.admin_region_id) as aaa027, sum(a.fcfrc) as fcfrc, sum(c.fcfrc) as tfcfrc," +
				"sum(a.fcffy) as fcffy, round(sum(c.fcffy)/sum(a.fyze), 4)*100 as fyzb, round(sum(c.fcfrc)/sum(a.fcfrc),4)*100 as trczb, " +
				"round(decode((sum(a.fcffy)/decode(sum(a.fcfrc), 0,1, sum(a.fcfrc))),sum(a.fcffy), 0, sum(a.fcffy)/" +
				"decode(sum(a.fcfrc), 0, 1, sum(a.fcfrc))),4) as fcfcjfy, sum(a.cfrc) as cfrc, sum(a.cffy) as cffy, " +
				"round(decode((sum(a.cffy)/decode(sum(a.cfrc), 0,-1, sum(a.cfrc))),-1, 0, sum(a.cffy)/" +
				"decode(sum(a.cfrc), 0, -1, sum(a.cfrc))),4) as cfpjrc from F_PHARM_PAY_YD_2 a, d_ddjg b, F_PHARM_PAY_YD_3 c where b.ddjg_id = a.yd_id  and a.yd_id = c.yd_id ");
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQl.append(" and b.admin_region_id = '"+condition.getAaa027()+"'");
		}
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			String date = condition.getStartDate();
			String startDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny >= '"+startDate+"'");
		}
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			String date = condition.getEndDate();
			String endDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny <= '"+endDate+"'");
		}
		if(StringUtils.isNotEmpty(condition.getYdIds())){
			exeSQl.append(" and a.yd_id = '"+condition.getYdIds()+"'");
		}
		if (StringUtils.isNotEmpty(condition.getFcfValue())) {
			exeSQl.append(" and c.dnzhdc = '" + condition.getFcfValue() + "' ");
			
		}
		exeSQl.append("group by a.yd_id) where rownum <= 100 ");
		countSQL.append(" select count(*) from (");
		countSQL.append(exeSQl.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet, exeSQl.toString(), countSQL.toString());
	}

	public PageSet ydCostRanking(PageSet pageSet, QueryCondition condition) {
		
		String date1 = condition.getStartDate();
		String startDate = date1.substring(0, 4) + date1.substring(5, 7);
		String date2 = condition.getEndDate();
		String endDate = date2.substring(0, 4) + date2.substring(5, 7);
		
		StringBuffer exeSQl = new StringBuffer();
		StringBuffer countSQL = new StringBuffer();
		exeSQl.append("select * from (select * from(select max(a.ybh) as ybh, max(b.xm) xm, max(a.xzlb) as xzlb, " +
				"(select t2.name from (select ddjg_id,row_number() over(partition by ybh order by fyze desc) rk " +
				"from F_PHARM_PAY_YDCBR_2 where jsny >= '" + startDate + "' and jsny <= '" + endDate +"' and ybh = a.ybh) t1, d_ddjg t2 " +
				"where t1.rk = 1 and t1.ddjg_id = t2.ddjg_id) mc, " +
				"max(a.cbxzqh) cbxzqh, max(a.jyqhdm) jyqhdm, sum(a.fyze) fyze, max(a.ddjg_id) as ddjg_id," +
				"sum(a.cffy) cffy, sum(a.fcffy) fcffy from F_PHARM_PAY_YDCBR_2 a, " +
				"d_person b where a.ybh = b.ybh ");
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			
			exeSQl.append(" and a.jsny >= '" + startDate + "' ");
		}
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			
			exeSQl.append(" and a.jsny <= '" + endDate + "' ");
		}
		exeSQl.append(" group by a.ybh) t1 where 1 = 1 ");
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQl.append(" and t1.cbxzqh = '"+condition.getAaa027()+"'");
		}
		if(StringUtils.isNotEmpty(condition.getJyaaa027())){
			exeSQl.append(" and t1.jyqhdm = '"+condition.getJyaaa027()+"'");
		}
		if (StringUtils.isNotEmpty(condition.getPm())) {
			exeSQl.append(" and rownum <= '" + condition.getPm() + "'" );
		}
		exeSQl.append(" order by t1.fyze desc) where rownum <= 100 ");
		countSQL.append(" select count(*) from (");
		countSQL.append(exeSQl.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet, exeSQl.toString(), countSQL.toString());
	}

	public PageSet ydMonthCostRanking(PageSet pageSet, QueryCondition condition) {
		
		
		String startDate = "";
		String start = "";
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			startDate = condition.getStartDate();
			start = startDate.substring(0, 4) + startDate.substring(5, 7);
		}
		String endDate = "";
		String end = "";
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			endDate = condition.getEndDate();
			end = endDate.substring(0, 4) + endDate.substring(5, 7);
		}
		StringBuffer exeSQl = new StringBuffer();
		StringBuffer countSQL = new StringBuffer();
		exeSQl.append("select * from (select * from (select max(a.ybh) as ybh, max(b.xm) xm, max(a.xzlb) as xzlb, max(a.cbxzqh) cbxzqh, max(a.jyqhdm) jyqhdm, " +
				"sum(a.fyze) fyze, sum(a.fyze)/(months_between(add_months(to_date('" + end + "','yyyyMM'),1)," +
				"to_date('" + start + "', 'yyyyMM'))) as ypjfy from F_PHARM_PAY_YDCBR_2 a, d_person b " +
				"where a.ybh = b.ybh ");
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			exeSQl.append(" and a.jsny >= '" + start + "' ");
		}
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			exeSQl.append(" and a.jsny <= '" + end + "' ");
		}
		exeSQl.append(" group by a.ybh) t1 where 1 = 1 ");
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQl.append(" and t1.cbxzqh = '"+condition.getAaa027()+"'");
		}
		if(StringUtils.isNotEmpty(condition.getJyaaa027())){
			exeSQl.append(" and t1.jyqhdm = '"+condition.getJyaaa027()+"'");
		}
		if(StringUtils.isNotEmpty(condition.getPjfy())){
			exeSQl.append(" and t1.ypjfy >= '"+condition.getPjfy()+"'");
		}
		exeSQl.append(" order by t1.ypjfy desc) where rownum <= 100 ");
		countSQL.append(" select count(*) from (");
		countSQL.append(exeSQl.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet, exeSQl.toString(), countSQL.toString());
	}

	public PageSet docYdWpcfRanking(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQl = new StringBuffer();
		StringBuffer countSQL = new StringBuffer();
		exeSQl.append("select * from (select * from (select max(a.ddjg_id) as ddjg_id, max(b.name) as name, " +
				"c.xm, sum(a.wpcfcs) as wpcfcs, sum(a.wpcffy) as wpcffy " +
				"from F_PHARM_PAY_DOC_2 a, d_ddjg b, d_doctor c where a.ddjg_id = b.ddjg_id and a.dr_id = c.dr_id ");
		if (StringUtils.isNotEmpty(condition.getYdIds())) {
		exeSQl.append(" and a.ddjg_id = '" + condition.getYdIds() + "' ");
			
		}
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			String date = condition.getStartDate();
			String startDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny >= '" + startDate + "' ");
		}
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			String date = condition.getEndDate();
			String endDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny <= '" + endDate + "' ");
		}
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQl.append(" and b.admin_region_id = '"+condition.getAaa027()+"'");
		}
		exeSQl.append(" group by c.xm) t1 where 1 = 1 ");
		
		exeSQl.append(" order by t1.wpcffy desc) where rownum <= 100 ");
		countSQL.append(" select count(*) from (");
		countSQL.append(exeSQl.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet, exeSQl.toString(), countSQL.toString());
	}

	public PageSet docWpcfRanking(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQl = new StringBuffer();
		StringBuffer countSQL = new StringBuffer();
		exeSQl.append("select * from (select * from (select max(a.ddjg_id) as ddjg_id, max(b.name) as name, " +
				"max(a.dr_id) as docId, c.xm, sum(a.wpcfcs) as wpcfcs, sum(a.wpcffy) as wpcffy " +
				"from F_PHARM_PAY_DOC_2 a, d_ddjg b, d_doctor c where a.ddjg_id = b.ddjg_id and a.dr_id = c.dr_id ");
		
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			String date = condition.getStartDate();
			String startDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny >= '" + startDate + "' ");
		}
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			String date = condition.getEndDate();
			String endDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny <= '" + endDate + "' ");
		}
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQl.append(" and b.admin_region_id = '"+condition.getAaa027()+"'");
		}
		exeSQl.append(" group by c.xm) t1 where 1 = 1 ");
		
		if(StringUtils.isNotEmpty(condition.getPm())){
			exeSQl.append(" and rownum <= " + condition.getPm());
		}
		exeSQl.append(" order by t1.wpcffy desc) where rownum <= 100 ");
		countSQL.append(" select count(*) from (");
		countSQL.append(exeSQl.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet, exeSQl.toString(), countSQL.toString());
	}
}
