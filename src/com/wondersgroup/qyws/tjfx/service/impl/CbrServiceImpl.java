package com.wondersgroup.qyws.tjfx.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wondersgroup.qyws.tjfx.module.Kc21;
import com.wondersgroup.qyws.tjfx.module.QueryCondition;
import com.wondersgroup.qyws.tjfx.module.TbHhmdPerson;
import com.wondersgroup.qyws.tjfx.module.TbParaGrade;
import com.wondersgroup.qyws.tjfx.module.TbParaHeavy;
import com.wondersgroup.qyws.tjfx.service.CbrService;
import com.wondersgroup.qyws.tjfx.util.GlobalConstants;
import com.wondersgroup.util.papper.PageSet;

@Service("CbrServiceImplService")
@Transactional
public class CbrServiceImpl extends ServiceImpl implements CbrService {

	/**
	 * 平均就诊费用和就诊次数查询
	 */
	public PageSet averageCostQuery(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
		exeSQL.append("select jzlx,sum(zfy)/decode(sum(jzcs),0,1,sum(jzcs)) pjfy,sum(jzcs)/count(*) pjcs from (");
		exeSQL.append("select jzlx,sum(zfy) zfy,jzcs,sfzhm ");
		exeSQL.append("from tb_stats_person where 1 = 1 ");
		//开始时间
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			exeSQL.append(" and sjdm >= '"+condition.getStartDate().substring(0, 4)+condition.getStartDate().substring(5, 7)+"'");
		}
		//结束时间
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			exeSQL.append(" and sjdm <= '"+condition.getEndDate().substring(0, 4)+condition.getEndDate().substring(5, 7)+"'");
		}
		//统筹区
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQL.append(" and tcdm = '"+condition.getAaa027()+"' ");
		}
		//人员类别
		if(StringUtils.isNotEmpty(condition.getAkc021())){
			exeSQL.append(" and rylb in (select aaa102 from aa10 where aaa100='RYLB' and aaa105='"+condition.getAkc021()+"') ");
		}
		exeSQL.append(" group by jzlx,jzcs,sfzhm) ");
		exeSQL.append(" group by jzlx ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 异常就医人员筛选
	 */
	public PageSet queryChooseExceptionPerson(PageSet pageSet,QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
			
		exeSQL.append("select * from (");
		exeSQL.append("select t.ybh,t.sfzhm,t.xm,t.nl,t.tcdm,t.LNZHZFFY, ");
		exeSQL.append("sum(t.zfy)/sum(t.jzcs) pjfy,sum(t.jzcs)/count(*) pjcs, ");
		exeSQL.append("sum(t.zfy) fyze, sum(jzcs) jzcs from ( ");
		exeSQL.append("select a.YBH, a.SFZHM, a.XM, a.NL, a.TCDM, nvl(sum(a.zfy),0) as zfy,a.jzcs,a.LNZHZFFY ");
		exeSQL.append("from TB_STATS_PERSON a where 1=1 ");
		//开始时间结束时间
		if(StringUtils.isNotEmpty(condition.getYearMonth())){
			String[] tt = condition.getYearMonth().split("~");
			exeSQL.append(" and a.sjdm >= '"+tt[0].substring(0, 4)+tt[0].substring(5, 7)+"'");
			exeSQL.append(" and a.sjdm <= '"+tt[1].substring(0, 4)+tt[1].substring(5, 7)+"'");
		}
		//统筹区
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQL.append(" and a.tcdm = '"+condition.getAaa027()+"' ");
		}
		//人员类别
		if(StringUtils.isNotEmpty(condition.getAkc021())){
			exeSQL.append(" and a.rylb = '"+condition.getAkc021()+"' ");
		}
		//就诊类别
		if(StringUtils.isNotEmpty(condition.getAka130())){
			exeSQL.append(" and a.jzlx = '"+condition.getAka130()+"' ");
		}
		exeSQL.append("group by  a.YBH, a.SFZHM, a.XM, a.NL, a.TCDM,a.jzcs,a.LNZHZFFY ) t ");
		exeSQL.append("group by t.ybh,t.sfzhm,t.xm,t.nl,t.tcdm,t.LNZHZFFY ) m where 1=1 ");
		//就医人员平均就诊费用大于等于该类型所有就医人员平均就诊费用
		if(StringUtils.isNotEmpty(condition.getRc_pjfy())){
			exeSQL.append(" and m.pjfy >= "+Double.parseDouble(condition.getRc_pjfy())*Double.parseDouble(condition.getPjfy())+"");
		}
		//就医人员平均就诊次数大于等于该类型所有就医人员平均就诊次数
		if(StringUtils.isNotEmpty(condition.getRc_pjcs())){
			exeSQL.append(" and m.pjcs >= "+Double.parseDouble(condition.getRc_pjcs())*Double.parseDouble(condition.getPjcs())+"");
		}
		//就医人员就诊费用总额大于等于
		if(StringUtils.isNotEmpty(condition.getRc_fyze())){
			exeSQL.append(" and m.fyze >= "+condition.getRc_fyze()+"");
		}
		//就医人员就诊次数大于等于
		if(StringUtils.isNotEmpty(condition.getRc_cszs())){
			exeSQL.append(" and m.jzcs >= "+condition.getRc_cszs()+"");
		}
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 根据区县查询日常参数
	 * @throws Exception 
	 */
	public List chooseExceptionPerson(QueryCondition condition) throws Exception {
		String sql = "select qzm,qzdm,sjqzid,qzz,id from TB_PARA_HEAVY where yxbz='"+GlobalConstants.DIC_YXBZ_1+"' and tcdm='"+condition.getAaa027()+"' and qzjb = '"+GlobalConstants.DIC_QZJB_11+"'";
		return this.baseDao.findListOfMapBySql(sql);
	}

	/**
	 * 参保人日常查询条件保存
	 */
	public void saveRcQueryCondition(QueryCondition condition) {
		//先将该统筹区下的所有日常查询条件变为无效
		String s = "update TB_PARA_HEAVY set yxbz='"+GlobalConstants.DIC_YXBZ_2+"' where tcdm = '"+condition.getAaa027()+"' and qzjb='"+GlobalConstants.DIC_QZJB_11+"' and yxbz='"+GlobalConstants.DIC_YXBZ_1+"' ";
		this.baseDao.executeUpdateBySql(s);
		TbParaHeavy tph = new TbParaHeavy();
		String id = this.baseDao.getSequenceValue("SEQ_PARA_HEAVY");
		tph.setID(Long.parseLong(id));
		tph.setQZM(condition.getRc_pjfy());//日常-平均费用
		tph.setQZDM(condition.getRc_pjcs());//日常-平均次数
		tph.setSJQZID(condition.getRc_fyze());//日常-费用总额
		tph.setQZZ(Double.parseDouble(condition.getRc_cszs()));//日常-次数总数
		tph.setTCDM(condition.getAaa027());//统筹代码
		tph.setQZJB(GlobalConstants.DIC_QZJB_11);//权重级别
		tph.setYXBZ(GlobalConstants.DIC_YXBZ_1);//有效标志
		tph.setXGSJ(new Date());//修改时间
		this.baseDao.saveObject(tph);
	}

	/**
	 * 待查人员查询
	 */
	public PageSet queryPerson(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
		exeSQL.append("select * from (");	
		exeSQL.append("select distinct a.ybh,a.xm,a.nl,a.sfzhm,a.xgsj,");
		exeSQL.append("nvl(sum(b.jzcs),0)/decode(count(*),0,1,count(*)) pjcs,");
		exeSQL.append("nvl(sum(b.zfy),0) zfy,nvl(sum(b.jzcs),0) jzcs,b.LNZHZFFY,nvl(sum(b.zfy),0) / decode(nvl(sum(b.jzcs),0),0,1,nvl(sum(b.jzcs),0)) as pjjzfy,");
		exeSQL.append("(select c.HHMDBZ from TB_HHMD_PERSON c where a.sfzhm = c.sfzhm and a.ybh = c.ybh) as hhmdbz,");
		exeSQL.append("(select c.bz from TB_HHMD_PERSON c where a.sfzhm = c.sfzhm and a.ybh = c.ybh and c.yxbz = '1') as bz ");
		exeSQL.append("from TB_MBPYQD a,");
		exeSQL.append("(select d.ybh,d.sfzhm,d.yxbz,d.jzcs,d.LNZHZFFY,d.sjdm,sum(d.zfy) zfy from TB_STATS_PERSON d where 1=1 ");
		if(StringUtils.isNotEmpty(condition.getSign())&&condition.getSign().equals("all")){
			if(condition.getHhmdbz().equals("1")){
				//加入红名单时查询出来的数据不能是红名单或黑名单成员
				exeSQL.append(" and d.hhmdbz not in ('"+GlobalConstants.DIC_HHMDBZ_1+"','"+GlobalConstants.DIC_HHMDBZ_2+"') ");
			}else if(condition.getHhmdbz().equals("2")){
				//加入黑名单是查询出来的数据不能是黑名单成员
				exeSQL.append(" and d.hhmdbz <> '"+GlobalConstants.DIC_HHMDBZ_2+"' ");
			}
		}
		exeSQL.append("group by d.ybh,d.sfzhm,d.yxbz,d.jzcs,d.LNZHZFFY,d.sjdm) b ");
		exeSQL.append("where a.ybh = b.ybh and a.sfzhm = b.sfzhm and a.yxbz = '1' and b.yxbz = '1' ");
//		exeSQL.append("and a.ybnd = to_char(sysdate,'yyyy')");
		//开始时间结束时间
		if(StringUtils.isNotEmpty(condition.getYearMonth())){
			String[] tt = condition.getYearMonth().split("~");
			exeSQL.append(" and b.sjdm >= '"+tt[0].substring(0, 4)+tt[0].substring(5, 7)+"'");
			exeSQL.append(" and b.sjdm <= '"+tt[1].substring(0, 4)+tt[1].substring(5, 7)+"'");
		}
		//统筹区
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQL.append(" and a.tcdm = '"+condition.getAaa027()+"' ");
		}
		//人员类别
		if(StringUtils.isNotEmpty(condition.getAkc021())){
			exeSQL.append(" and a.rylb = '"+condition.getAkc021()+"' ");
		}
		//就诊类别
		if(StringUtils.isNotEmpty(condition.getAka130())){
			exeSQL.append(" and a.jzlx = '"+condition.getAka130()+"' ");
		}
		exeSQL.append("group by a.ybh,a.xm,a.nl,a.sfzhm,a.xgsj,b.LNZHZFFY) m where 1=1 ");
		//就医人员平均就诊费用大于等于该类型所有就医人员平均就诊费用
		if(StringUtils.isNotEmpty(condition.getRc_pjfy())){
			exeSQL.append(" and m.pjjzfy >= "+Double.parseDouble(condition.getRc_pjfy())*Double.parseDouble(condition.getPjfy())+"");
		}
		//就医人员平均就诊次数大于等于该类型所有就医人员平均就诊次数
		if(StringUtils.isNotEmpty(condition.getRc_pjcs())){
			exeSQL.append(" and m.pjcs >= "+Double.parseDouble(condition.getRc_pjcs())*Double.parseDouble(condition.getPjcs())+"");
		}
		//就医人员就诊费用总额大于等于
		if(StringUtils.isNotEmpty(condition.getRc_fyze())){
			exeSQL.append(" and m.zfy >= "+condition.getRc_fyze()+"");
		}
		//就医人员就诊次数大于等于
		if(StringUtils.isNotEmpty(condition.getRc_cszs())){
			exeSQL.append(" and m.jzcs >= "+condition.getRc_cszs()+"");
		}
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 个人分析报告
	 */
	public PageSet queryPersonReport(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
			
		exeSQL.append("select distinct a.aac001,a.aac003,a.aac004,TRUNC(MONTHS_BETWEEN(SYSDATE,TO_DATE(AAC006,'YYYYMMDD'))/12,0) nl,a.aac002,a.aaz217 ");
		exeSQL.append(" from kc21 a where a.aac002 = '"+condition.getAac002()+"' ");
		//开始时间结束时间
		if(StringUtils.isNotEmpty(condition.getYearMonth())){
			String[] tt = condition.getYearMonth().split("~");
			exeSQL.append(" and to_char(a.akc194,'yyyyMM') >= '"+tt[0].substring(0, 4)+tt[0].substring(5, 7)+"'");
			exeSQL.append(" and to_char(a.akc194,'yyyyMM') <= '"+tt[1].substring(0, 4)+tt[1].substring(5, 7)+"'");
		}
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 批量加入黑/红名单
	 */
	public void beathAddList(QueryCondition queryCondition) {
		TbHhmdPerson thp = new TbHhmdPerson();
		String[] aac002s = queryCondition.getAac002().split(",");
		for(int i=0;i<aac002s.length;i++){
			String id = this.baseDao.getSequenceValue("SEQ_TB_HHMDPERSON_ID");
			String sql = "select aac001,aac003,TRUNC(MONTHS_BETWEEN(SYSDATE,TO_DATE(AAC006,'YYYYMMDD'))/12,0) aac006 from kc21 where aac002 = '"+aac002s[i]+"' and rownum < 2 ";
			List list =  baseDao.getListBySql(sql);
			Object[] obj = (Object[])list.get(0);
			thp.setID(Long.parseLong(id));
			thp.setNL(Integer.parseInt(String.valueOf(obj[2])));//年龄
			thp.setQXDM("");
			thp.setSFZHM(aac002s[i]);//身份证号码
			thp.setTCDM(queryCondition.getAaa027());//统筹区
			thp.setXM(String.valueOf(obj[1]));//姓名
			thp.setYBH(String.valueOf(obj[0]));//医保号
			thp.setHHMDBZ(queryCondition.getHhmdbz());//红黑名单标识
			thp.setBZ(queryCondition.getBz());//备注
			thp.setYXBZ(GlobalConstants.DIC_YXBZ_1);//有效标识
			thp.setXGSJ(new Date());//修改时间
//			this.baseDao.saveObject(thp);
			String insertSql = "insert into TB_HHMD_PERSON (id,nl,qxdm,sfzhm,tcdm,xm,ybh,hhmdbz,bz,yxbz,xgsj) ";
				insertSql += "values("+thp.getID()+","+thp.getNL()+",'','"+thp.getSFZHM()+"','"+thp.getTCDM()+"'";
				insertSql += ",'"+thp.getXM()+"','"+thp.getYBH()+"','"+thp.getHHMDBZ()+"','"+thp.getBZ()+"'";
				insertSql += ",'1',sysdate)";
			this.baseDao.executeUpdateBySql(insertSql);
		}
	}

	/**
	 * 就诊信息查询
	 */
	public PageSet queryJzxx(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
			
		exeSQL.append("select a.akb021,a.aka130,a.aka120,a.akc194,a.aae030,a.aae031,a.aaz217, ");
		exeSQL.append("(select b.aac003 from kf05 b where to_char(a.aaz263)=b.aaz263 and a.akb020=b.akb020) ysxm, ");
		exeSQL.append("(select sum(c.akc264) from kc24 c where a.aaz217 = c.aaz217) zfy from kc21 a where a.aaz217 = '"+condition.getAaz217()+"' ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 费用明细查询
	 */
	public PageSet queryFymx(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
			
		exeSQL.append("select * from kc22 a where a.aaz217 = '"+condition.getAaz217()+"' ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}
}
