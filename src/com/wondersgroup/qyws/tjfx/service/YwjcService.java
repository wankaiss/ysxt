package com.wondersgroup.qyws.tjfx.service;

import java.util.Map;

import com.wondersgroup.util.papper.PageSet;


//业务监测Service
public interface YwjcService extends Service{
	
	//采购单价波动监测
	public PageSet getCgdjjcDataPage(PageSet ps,Map map);
}
