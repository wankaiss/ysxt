package com.wondersgroup.qyws.tjfx.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页帮助类
 * @author Administrator
 *
 */
public class PagingUtil {
	
	/**
	 * 获取分页的参数,包括当前页,每页条数,起始条数
	 * @param request 请求对象,此对象获取的参数都在asynPage.js中获取
	 * @return
	 */
	public static Integer[] getPagingParameter(HttpServletRequest request){
		Integer[] args = new Integer[10];
		//当前页
		Integer currPageNum = Integer.parseInt(request.getParameter("currPageNum"));
		//每页条数limit的max
		Integer pageRowSize = Integer.parseInt(request.getParameter("pageRowSize"));
		//此页起始条数limit的first
		Integer firstResult = Integer.parseInt(request.getParameter("firstResult"));
		
		args[0] = currPageNum;
		args[1] = pageRowSize;
		args[2] = firstResult;
		return args;
	}
}
