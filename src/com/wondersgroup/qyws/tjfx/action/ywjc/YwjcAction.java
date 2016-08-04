package com.wondersgroup.qyws.tjfx.action.ywjc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wondersgroup.qyws.task.service.TaskService;
import com.wondersgroup.qyws.tjfx.common.BaseAction;
import com.wondersgroup.qyws.tjfx.common.PublicStatic;
import com.wondersgroup.qyws.tjfx.service.YwjcService;
import com.wondersgroup.util.papper.PageSet;

@SuppressWarnings("serial")
@Controller()  
@Scope("prototype")
public class YwjcAction extends BaseAction {
	@Autowired
	private YwjcService ywjcService;
	
	@Autowired
	private TaskService taskService;
	
	public String left(){
		this.page = this.ROOR_PATH + "/ywjc/left.jsp";
		return this.SUCCESS;
	}
	
	//TODO 采购单价监测
	public String gotoCgdjjc(){
		this.page = this.ROOR_PATH + "/ywjc/cgdjjcIndex.jsp";
		return this.SUCCESS;
	}
	public String cgdjjc(){
		
		Map map=new HashMap<String,String>();
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		this.pageSet = ywjcService.getCgdjjcDataPage(pageSet,map);
		this.page = this.ROOR_PATH + "/ywjc/cgdjjc.jsp";
		return this.SUCCESS;
	}
	
	public String gotoYwjcIndex(){
		this.page = this.ROOR_PATH + "/ywjc/index.jsp";
		return this.SUCCESS;
	}

}
