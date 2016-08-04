package com.wondersgroup.qyws.tjfx.action.index;

import org.springframework.stereotype.Controller;

import com.wondersgroup.qyws.tjfx.common.BaseAction;
import com.wondersgroup.qyws.tjfx.common.PublicStatic;

@Controller()  
public class StructureAction  extends BaseAction{
	
	public String index2Content()
	{
		this.page = this.ROOR_PATH + "/index/content_index2.jsp";
		return this.SUCCESS;
	}
	
	public String indexContent()
	{
		this.page = this.ROOR_PATH + "/index/content.jsp";
		return this.SUCCESS;
	}
	
	public String indexContentGl()
	{
		this.page = this.ROOR_PATH + "/ywjc/indexGl.jsp";
		return this.SUCCESS;
	}
	
	public String indexTop()
	{
		this.page = this.ROOR_PATH + "/index/top.jsp";
		return this.SUCCESS;
	}
	
	public String indexGl()
	{
		this.page = this.ROOR_PATH + "/index/topGl.jsp";
		return this.SUCCESS;
	}

	public String indexInstantstatus()
	{
		this.page = this.ROOR_PATH + "/index/Instantstatus.jsp";
		return this.SUCCESS;
	}
	public String indexPurchase()
	{
		this.page = this.ROOR_PATH + "/index/Purchase.jsp";
		return this.SUCCESS;
	}
	
	public String hztjMain()
	{
		this.page = this.ROOR_PATH + "/hztj/page.jsp";
		return this.SUCCESS;
	}
	
	public String hztjLeftNav()
	{
		this.page = this.ROOR_PATH + "/hztj/leftNav.jsp";
		return this.SUCCESS;
	}
	
	public String hztjContent()
	{
		this.page = this.ROOR_PATH + "/hztj/hztjContent.jsp";
		return this.SUCCESS;
	}
	public String ywjcContent()
	{
		this.page = this.ROOR_PATH + "/ywjc/ywjccontent.jsp";
		return this.SUCCESS;
	}	
	public String ywjcLeftNav()
	{
		this.page = this.ROOR_PATH + "/ywjc/leftNav.jsp";
		return this.SUCCESS;
	}
}
