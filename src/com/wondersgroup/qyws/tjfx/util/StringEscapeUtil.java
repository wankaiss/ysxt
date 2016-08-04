package com.wondersgroup.qyws.tjfx.util;

public class StringEscapeUtil {
	
	public static String ecscapeString(String str){
		
		 String[] chartArray={"<","<="," >",">=","&"};
		 String[] replaceArray={"&lt;","&lt;=","&gt;","&gt;=","&amp;"};
			   for(int i=0;i<replaceArray.length;i++){
				   str=str.replaceAll(replaceArray[i],chartArray[i]);
			   }
		  return str;
	}

}
