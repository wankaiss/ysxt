package com.wondersgroup.qyws.tjfx.action.index;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wondersgroup.qyws.tjfx.common.BaseAction;
import com.wondersgroup.qyws.tjfx.util.CommonJdbcDaoUtils;
import com.wondersgroup.qyws.tjfx.util.SessionConstants;

@SuppressWarnings("serial")
@Controller()  
@Scope("prototype")
public class IndexAction extends BaseAction {
	
	protected String login_user;
	protected String login_pass;
	private HttpSession session;
	
	public String index() throws Exception{
		return SUCCESS;		
	}
	
	@SuppressWarnings("static-access")
	public String gotoMain()
	{
		this.page = this.ROOR_PATH + "/index/index.jsp";
		return this.SUCCESS;
	}
	
	public String logout(){
		this.getRequest().getSession().removeAttribute("login_user");
		this.page = this.ROOR_PATH + "/index/logout.jsp";
		return this.SUCCESS;
	}
	
	public String getPercentOfCgjeAjaxData(){
		
		JSONObject map=new JSONObject();
		map.put("zfyje", "12");
		map.put("jyje", "9");
		map.put("djyje", "12");
		map.put("kjyje", "6");
		map.put("zcyje", "11");
		map.put("totalCgje", "15");		
		this.ajaxJson(map.toString());
		
		return null;
	}
	
	public String loginCheck() {
		//用户认证
		login_user = this.getRequest().getParameter("login_user");
		login_pass = this.getRequest().getParameter("login_pass");
		//delete session in function logout(Remember!!)
		this.session = this.getRequest().getSession();
		if (session.equals(null) || session.equals("")) {
			session.setAttribute("login_user", login_user);
		}else {
			session.removeAttribute("login_user");
			session.setAttribute("login_user", login_user);
			
		}
		
		System.out.println(this.login_user + ":" + this.login_pass);
		
	
		try {
			System.out.println("verifying permission!");
			this.ajaxText("yes");
		} catch (Exception ex) {
			this.ajaxText("no");
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String getChartData() throws Exception{
		
		try{
			  String rs="[[{},{\"series\":\"[{name:'医保范围挂网',stack: 'stack',data:[1000,1000,1000,1000,1000,1000]},{name:'自费范围挂网',stack: 'stack',data:[1000,1000,1000,1000,1000,1000]},{name:'医保范围招标已落标',stack: 'stack',data:[1000,1000,1000,1000,1000,1000]},{name:'其他',stack: 'stack',data:[1000,1000,1000,1000,1000,1000]},{name:'医保范围招标',stack: 'stack',data:[1000,1000,1000,1000,1000,1000]},{name:'量价挂钩集中采购',stack: 'stack',data:[1000,1000,1000,1000,1000,1000]},{name:'不允许采购',stack: 'stack',data:[1000,1000,1000,1000,1000,1000]},{name:'医保范围未招标',stack: 'stack',data:[1000,1000,1000,1000,1000,1000]}]\"}],[{\"seriesData\":\"[['医保范围挂网',1000],['自费范围挂网',1000],['医保范围招标已落标',1000],['其他',1000],['医保范围招标',1000],['量价挂钩集中采购',1000],['不允许采购',1000],['医保范围未招标',1000]]\"}],[{\"Xcategories\":\"['2100013','2100014','2100015']\"},{\"series\":\"[{name:'订单金额',data:[1000,1000,1000]},{name:'配送金额',data:[1000,1000,1000]},{name:'入账金额',data:[1000,1000,1000]},{name:'入库金额',data:[1000,1000,1000]},{name:'完成支付金额',data:[1000,1000,1000]},{name:'退货金额',data:[1000,1000,1000]}]\"}]]";
			  return ajax(rs, "text/json");
		 }
		 catch(Exception ex){
			 ex.printStackTrace();
		 }
		 return null;	
	}
	
	public String loginByAjax() {
		String jsonData=null;
		String loginname = this.getParameter("loginname");
		String userid = this.getParameter("userid");
		String realname = this.getParameter("realname");
		String orgid = this.getParameter("orgid");
		String orgname = this.getParameter("orgname");
		String xzqhid = this.getParameter("xzqhid");
		String check = this.getParameter("check");
		String type = this.getParameter("type");
		String dwybh = this.getParameter("dwybh");
		String dwdm = this.getParameter("dwdm");
		String uncheck="";
		getSession().put(SessionConstants.WSSIP_OPERATOR_ID,userid);
		getSession().put(SessionConstants.WSSIP_OPERATOR_LOGINNAME,loginname);
		getSession().put(SessionConstants.WSSIP_OPERATOR_NAME,realname);
		getSession().put(SessionConstants.WSSIP_OPERATOR_ORGANNAME,orgname);
		getSession().put(SessionConstants.WSSIP_OPERATOR_SECURITYAGENT,xzqhid);
		try {
			uncheck=encodeToMD5(userid + loginname + realname + orgid + orgname + xzqhid+type+dwybh+dwdm);
		} catch (Exception e1) {
			jsonData=e1.toString();
		}
		if (StringUtils.isNotEmpty(dwdm)) {
			String newdwdm=dwdm.trim();
			for (int i = 0; i < 16-dwdm.length(); i++) {
				newdwdm=newdwdm+" ";
			}
			dwdm="'"+newdwdm+"'";
		}
		if ("1".equals(type)) {
//			String result=getLoginInfo(xzqhid,userid);
//			getSession().put(SessionConstants.WSSIP_OPERATOR_ORGANCODE, result);
		} else if ("2".equals(type)) {
			getSession().put(SessionConstants.WSSIP_OPERATOR_ORGANCODE, dwdm);
		}
		System.out.println("dwdm.length="+dwdm.length());
		System.out.println("loginname="+loginname+",userid="+userid+",realname="+realname+",orgid="+orgid+",orgname="+orgname+",xzqhid="+xzqhid+",type="+type+",check="+check+",dwybh="+dwybh+",dwdm="+dwdm);
		System.out.println(uncheck);
		if (check!=null) {
			if (check.equals(uncheck)) {
				jsonData= "success";
//				newLogin(userid,loginname,realname,orgid,orgname,xzqhid);
			}else {
				jsonData= "false";
			}
		}
		System.out.println(jsonData);
		try {
			returnAjax(jsonData);
			System.out.println(jsonData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private String getLoginInfo(String xzqhid, String userid) {
		String res="";
		try {
			String sql=" select yboltp.PK_ZNSH.Func_CheckSh('"+xzqhid+"', '"+userid+"') res from dual  ";
			Map<String, Object>  result = CommonJdbcDaoUtils.queryForMap(sql);
			JSONObject json =new JSONObject();
			if (result!=null) {
				json.accumulateAll(result);
				JSONObject json1=json.getJSONObject("RES");
				String flag=json1.getString("success");
				if ("true".equals(flag)) {
					JSONArray array=json1.getJSONArray("result");
					for (int i = 0; i < array.size(); i++) {
						JSONObject obj=(JSONObject) array.get(i);
						res=res+"'"+obj.get("yydm")+"',";
					}
					res=res.substring(0, res.length()-1);
				}else {
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			res="'000000'";
		}
		return res;
	}
	
	
	private static String encodeToMD5(String s) throws Exception{
		if (s == null)
		return null;
	    String digstr = "";
		java.security.MessageDigest MD = java.security.MessageDigest.getInstance("MD5");

		byte[] oldbyte = new byte[s.length()];
		for (int i = 0; i < s.length(); i++) {
	        oldbyte[i] = (byte) s.charAt(i);
	    }
	    MD.update(oldbyte);
		byte[] newbyte = MD.digest(oldbyte);
		for (int i = 0; i < newbyte.length; i++) {
	        digstr = digstr + newbyte[i];
	    }
		return digstr;
	}
	
	private void returnAjax(String msg) throws Exception{
		HttpServletResponse response = this.getResponse();
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(msg.getBytes());
		outputStream.flush();
	}
}
