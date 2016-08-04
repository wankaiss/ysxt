package com.wondersgroup.qyws.tjfx.common;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class PublicFunction {
	
	public static boolean ifJsonArray(String inStr)
	{
		try{
			JSONArray jsonA=JSONArray.fromObject(inStr); 
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public static String doNull(Object obj)
	{
		if(null==obj)
		{
			return "";
		}
		else
		{
			return obj.toString().trim();
		}
	}
}
