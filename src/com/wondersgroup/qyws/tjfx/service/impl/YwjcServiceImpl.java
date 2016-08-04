package com.wondersgroup.qyws.tjfx.service.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import oracle.jdbc.internal.OracleTypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wondersgroup.qyws.task.dao.impl.DaoSupportImpl;
import com.wondersgroup.qyws.tjfx.service.YwjcService;
import com.wondersgroup.util.papper.PageSet;

//业务监测Service实现类

@Service("ywjcserviceimpl")
@Transactional
public class YwjcServiceImpl extends ServiceImpl implements YwjcService {


	@Autowired
	private DaoSupportImpl supportDao;
	/**
	 * 调用存储过程无输入参数
	 * @param procName 存储过程名
	 * @param jcdj 监测等级
	 * @param taskId 任务ID
	 */
	private void callProcDefultInputParams(String procName,String jcdj,String taskId) {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = SessionFactoryUtils.getDataSource(this.baseDao.getSessionFactory())
					.getConnection();
			cstmt = conn.prepareCall("{ call YSXT_TJJC."+procName+"(?,?,?,?) }");
			cstmt.setString(1, taskId);
			cstmt.setString(2, jcdj);
			cstmt.registerOutParameter(3, OracleTypes.NUMBER);
			cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
			cstmt.execute();
			//得到存储过程的输出参数值并打印出来
		    System.out.println (cstmt.getInt(3)); 
		    System.out.println (cstmt.getString(4)); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 调用存储过程有输入参数
	 * @param procName 存储过程名
	 * @param jcdj 监测等级
	 * @param month 阀值月数值
	 * @param jczbsz 检测指标设置值
	 * @param taskId 任务ID
	 */
	private void callProcInputParams(String procName,String jcdj,int month, int jczbsz,String taskId) {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = SessionFactoryUtils.getDataSource(this.baseDao.getSessionFactory())
					.getConnection();
			cstmt = conn.prepareCall("{ call YSXT_TJJC."+procName+"(?,?,?,?,?,?) }");
			cstmt.setString(1, taskId);
			cstmt.setString(2, jcdj);
			cstmt.setInt(3, month);
			cstmt.setDouble(4, jczbsz);
			cstmt.registerOutParameter(5, OracleTypes.NUMBER);
			cstmt.registerOutParameter(6, OracleTypes.VARCHAR);
			cstmt.execute();
			//得到存储过程的输出参数值并打印出来
			System.out.println (cstmt.getInt(5)); 
			System.out.println (cstmt.getString(6)); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 调用存储过程有输入参数
	 * @param procName 存储过程名
	 * @param jcdj 监测等级
	 * @param jczbsz 检测指标设置值
	 * @param taskId 任务ID
	 */
	private void callProcInputParams(String procName, String jcdj,int jczbsz,String taskId) {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = SessionFactoryUtils.getDataSource(this.baseDao.getSessionFactory())
					.getConnection();
			cstmt = conn.prepareCall("{ call YSXT_TJJC."+procName+"(?,?,?,?,?) }");
			cstmt.setString(1, taskId);
			cstmt.setString(2, jcdj);
			cstmt.setDouble(3, jczbsz);
			cstmt.registerOutParameter(4, OracleTypes.NUMBER);
			cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
			cstmt.execute();
			//得到存储过程的输出参数值并打印出来
			System.out.println (cstmt.getInt(4)); 
			System.out.println (cstmt.getString(5)); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 调用存储过程有输入参数
	 * @param procName 存储过程名
	 * @param jcdj 监测等级
	 * @param jczbsz 检测指标设置值
	 * @param taskId 任务ID
	 * @param per 比例值
	 */
	private void callProcInputParams(String procName, String jcdj,int jczbsz,String taskId,double per) {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = SessionFactoryUtils.getDataSource(this.baseDao.getSessionFactory())
					.getConnection();
			cstmt = conn.prepareCall("{ call YSXT_TJJC."+procName+"(?,?,?,?,?,?) }");
			cstmt.setString(1, taskId);
			cstmt.setString(2, jcdj);
			cstmt.setDouble(3, jczbsz);
			cstmt.setDouble(4, per);
			cstmt.registerOutParameter(5, OracleTypes.NUMBER);
			cstmt.registerOutParameter(6, OracleTypes.VARCHAR);
			cstmt.execute();
			//得到存储过程的输出参数值并打印出来
			System.out.println (cstmt.getInt(5)); 
			System.out.println (cstmt.getString(6)); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 医院未按服务性质和范围采购药品
	 */
	public void getYyjgfwxzCgyp(Map<String,String> params) {
		String procName = "SP_YWJC_CGJG_CGDJGYZGCGDJ";

		callProcInputParams(procName, "1", 1,"1");

	}
	/*
	 * @param ps
	 * @param map
	 * @return
	 * */
	public PageSet getCgdjjcDataPage(PageSet ps,Map map) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
		exeSQL.append("select 1,'test' YQMC from dual ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		return this.baseDao.queryBySql(ps,exeSQL.toString(),countSQL.toString());
	}
	

}

