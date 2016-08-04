package com.wondersgroup.qyws.tag;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

public class BaseTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	protected void print(String value){
		if(null == value || value.trim().length()==0){
			return;
		}
		try {
			this.pageContext.getOut().write(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}