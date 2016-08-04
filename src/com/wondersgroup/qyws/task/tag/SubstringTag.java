package com.wondersgroup.qyws.task.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class SubstringTag extends TagSupport{
	
	private static final long serialVersionUID = 1L;
	private String value;
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getAppend() {
		return append;
	}

	public void setAppend(String append) {
		this.append = append;
	}

	private int length;
	private String append;
	
	@Override
	public int doEndTag() throws JspException {
		String temp = this.value;
		if(null != this.value && this.value.trim().length() > this.length){
			temp = this.value.substring(0,this.length) + this.append;
		}
		try {
			this.pageContext.getOut().print(temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}
	
}