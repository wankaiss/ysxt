package com.wondersgroup.qyws.tjfx.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wondersgroup.qyws.tjfx.module.QueryCondition;
import com.wondersgroup.qyws.tjfx.util.DateUtils;
import com.wondersgroup.util.papper.PageSet;

/**
 * Action - 基类
 */
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 6718838822334455667L;

	
	public static final String VIEW = "view";
	public static final String LIST = "list";
	public static final String STATUS = "status";
	public static final String WARN = "warn";
	public static final String SUCCESS = "success";
	public static final String CHAIN = "chain"; 
	public static final String REDIRECT = "redirect";
	public static final String OBJECT = "type_object";
	public static final String ERROR = "error";
	public static final String MESSAGE = "message";
	public static final String CONTENT = "content";
	
	
	protected String socketAddress;
	protected String socketListenPort;
	
	protected String ROOR_PATH = "/WEB-INF/web";
	protected String page;
	protected Object object;
	protected PageSet pageSet;
	
	protected String nf;
	protected String yf;
	protected String jd;
	
	protected String startRow;//开始于哪行
	protected String endRow;//一页显示多少行
	protected String status;//翻页状态
	protected String jumpTo;//跳转到哪页
	protected String indexType;//对应哪个分类

	protected String yljgdm;
	protected String ksdm;
	protected String doctorid;
	protected String startDate;//开始时间
	protected String endDate;
	protected String kfys;//开方医生
	protected String cyh;//处方号
	
	protected String yylb;//医院类别
	protected String jzlx;//就诊类型
	protected String selectDate; //选择的时间
	protected String selectType; //选择的类型
	
	protected String strSj;//用于在table页显示时间
	
	
	public String showSumbit;
	
	protected String id;
	protected String[] ids;
	protected String logInfo;// 日志记录信息
	protected String redirectionUrl;// 操作提示后的跳转URL,为null则返回前一页
	
	protected QueryCondition condition;//查询条件
	
	protected PageSet pageSet1;
	protected PageSet pageSet2;
	
	public PageSet getPageSet2() {
		return pageSet2;
	}

	public void setPageSet2(PageSet pageSet2) {
		this.pageSet2 = pageSet2;
	}

	public PageSet getPageSet1() {
		return pageSet1;
	}

	public void setPageSet1(PageSet pageSet1) {
		this.pageSet1 = pageSet1;
	}

	public String getSocketListenPort() {
		return socketListenPort;
	}

	public void setSocketListenPort(String socketListenPort) {
		this.socketListenPort = socketListenPort;
	}

	public String getSocketAddress() {
		return socketAddress;
	}

	public void setSocketAddress(String socketAddress) {
		this.socketAddress = socketAddress;
	}

	public PageSet getPageSet() {
		return pageSet;
	}

	public void setPageSet(PageSet pageSet) {
		this.pageSet = pageSet;
	}

	public String input() {
		return null;
	}
	
	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getROOR_PATH() {
		return ROOR_PATH;
	}
	public void setROOR_PATH(String rOOR_PATH) {
		ROOR_PATH = rOOR_PATH;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	// 获取Attribute
	public Object getAttribute(String name) {
		return ServletActionContext.getRequest().getAttribute(name);
	}

	// 设置Attribute
	public void setAttribute(String name, Object value) {
		ServletActionContext.getRequest().setAttribute(name, value);
	}

	// 获取Parameter
	public String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	// 获取Parameter数组
	public String[] getParameterValues(String name) {
		return getRequest().getParameterValues(name);
	}

	// 获取Session
	public Object getSession(String name) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session.get(name);
	}

	// 获取Session
	public Map<String, Object> getSession() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session;
	}

	// 设置Session
	public void setSession(String name, Object value) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.put(name, value);
	}

	// 获取Request
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	// 获取Response
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	// 获取Application
	public ServletContext getApplication() {
		return ServletActionContext.getServletContext();
	}
	
	
	//获取项目发布路径
	public String getRealyPath(String path) {
		return getApplication().getRealPath(path);
	}
	
	
	//输入javascript 的 alert str 打印信息
	public String outJavascriptAlertString(String str){
		PrintWriter out;
		try {
			getResponse().setCharacterEncoding("GBK");
			out = getResponse().getWriter();
			out.write("<script language=javascript>alert('"+str+"');</script>");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//返回上一页
	public void backToHistoryLocation(){
		PrintWriter out;
		try {
			getResponse().setCharacterEncoding("GBK");
			out = getResponse().getWriter();
			out.write("<script language=javascript>window.history.back();</script>");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// AJAX输出，返回null
	public String ajax(String content, String type) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// AJAX输出文本，返回null
	public String ajaxText(String text) {
		return ajax(text, "text/plain");
	}

	// AJAX输出HTML，返回null
	public String ajaxHtml(String html) {
		return ajax(html, "text/html");
	}

	// AJAX输出XML，返回null
	public String ajaxXml(String xml) {
		return ajax(xml, "text/xml");
	}

	// 根据字符串输出JSON，返回null
	public String ajaxJson(String jsonString) {
		return ajax(jsonString, "text/json");
	}
	
	// 根据Map输出JSON，返回null
	public String ajaxJson(Map<?, ?> jsonMap) {
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		return ajax(jsonObject.toString(), "text/json");
	}
	public String ajaxJson(List jsonList){
		String string=JSONArray.fromObject(jsonList).toString();
		return ajax(string, "text/json");
	}
	// 输出JSON警告消息，返回null
	public String ajaxJsonWarnMessage(String message) {
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(STATUS, WARN);
		jsonMap.put(MESSAGE, message);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		return ajax(jsonObject.toString(), "text/json");
	}
	
	// 输出JSON成功消息，返回null
	public String ajaxJsonSuccessMessage(String message) {
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(STATUS, SUCCESS);
		jsonMap.put(MESSAGE, message);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		return ajax(jsonObject.toString(), "text/json");
	}
	
	// 输出JSON错误消息，返回null
	public String ajaxJsonErrorMessage(String message) {
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(STATUS, ERROR);
		jsonMap.put(MESSAGE, message);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		return ajax(jsonObject.toString(), "text/json");
	}
	
	// 设置页面不缓存
	public void setResponseNoCache() {
		getResponse().setHeader("progma", "no-cache");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setHeader("Cache-Control", "no-store");
		getResponse().setDateHeader("Expires", 0);
	}
	
	//初始化时间参数
	protected String[] initParame(){
		String startDate = "";
		String endDate = "";
		if (StringUtils.isNotBlank(selectDate) && StringUtils.isNotBlank(selectType)){
			if ("year".equals(selectType)){
				startDate = selectDate;
				endDate = selectDate;
			} else if ("month".equals(selectType)){
				startDate = selectDate;
				endDate = selectDate;
			} else if ("week".equals(selectType)){
				String start = selectDate.substring(0, 4);
				String end= selectDate.substring(4);
				if ("01".equals(end)){
					startDate = start.concat("01");
					endDate = start.concat("03");
				} else if ("02".equals(end)){
					startDate = start.concat("04");
					endDate = start.concat("06");
				} else if ("03".equals(end)){
					startDate = start.concat("07");
					endDate = start.concat("09");
				} else if ("04".equals(end)){
					startDate = start.concat("10");
					endDate = start.concat("12");
				}
			}
		} else {
			selectType = "month";
			startDate = DateUtils.getAfterMonthDate(DateUtils.convertToString(new Date(), "yyyyMM"), -1);
			endDate = DateUtils.getAfterMonthDate(DateUtils.convertToString(new Date(), "yyyyMM"), -1);
		}
		return new String[]{selectType, startDate, endDate};
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	public String getRedirectionUrl() {
		return redirectionUrl;
	}

	public void setRedirectionUrl(String redirectionUrl) {
		this.redirectionUrl = redirectionUrl;
	}
	
	 /************get和set方法**************************/

	public String getNf() {
		return nf;
	}
	
	public void setNf(String nf) {
		this.nf = nf;
	}
	
	public String getYf() {
		return yf;
	}
	
	public void setYf(String yf) {
		this.yf = yf;
	}
	
	public String getJd() {
		return jd;
	}
	
	public void setJd(String jd) {
		this.jd = jd;
	}
	
	public String getYljgdm() {
		return yljgdm;
	}
	
	public void setYljgdm(String yljgdm) {
		this.yljgdm = yljgdm;
	}
	
	public String getKsdm() {
		return ksdm;
	}
	
	public void setKsdm(String ksdm) {
		this.ksdm = ksdm;
	}
	
	public String getDoctorid() {
		return doctorid;
	}
	
	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}

	public String getStrSj() {
		return strSj;
	}

	public void setStrSj(String strSj) {
		this.strSj = strSj;
	}

	public String getYylb() {
		return yylb;
	}

	public void setYylb(String yylb) {
		this.yylb = yylb;
	}

	public String getSelectDate() {
		return selectDate;
	}

	public void setSelectDate(String selectDate) {
		this.selectDate = selectDate;
	}

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	public String getJzlx() {
		return jzlx;
	}

	public void setJzlx(String jzlx) {
		this.jzlx = jzlx;
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
	public String getKfys() {
		return kfys;
	}

	public void setKfys(String kfys) {
		this.kfys = kfys;
	}

	public String getCyh() {
		return cyh;
	}

	public void setCyh(String cyh) {
		this.cyh = cyh;
	}

	public String getStartRow() {
		return startRow;
	}

	public void setStartRow(String startRow) {
		this.startRow = startRow;
	}

	public String getEndRow() {
		return endRow;
	}

	public void setEndRow(String endRow) {
		this.endRow = endRow;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJumpTo() {
		return jumpTo;
	}

	public void setJumpTo(String jumpTo) {
		this.jumpTo = jumpTo;
	}

	public String getIndexType() {
		return indexType;
	}

	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}

	public String getShowSumbit() {
		return showSumbit;
	}

	public void setShowSumbit(String showSumbit) {
		this.showSumbit = showSumbit;
	}

	public QueryCondition getCondition() {
		return condition;
	}

	public void setCondition(QueryCondition condition) {
		this.condition = condition;
	}
	
}