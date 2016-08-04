package com.wondersgroup.qyws.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.wondersgroup.qyws.tjfx.common.StringUtils;


public class SubString extends TagSupport{
	private static final long serialVersionUID = 1L;
	private String value = "";
	private int length = 10;
	private String append = "";
	private boolean filter = false;
	public boolean isFilter() {
		return filter;
	}
	public void setFilter(boolean filter) {
		this.filter = filter;
	}
	public String getAppend() {
		return append;
	}
	public void setAppend(String append) {
		this.append = append;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int doEndTag() throws JspException {
		if(this.isFilter()){
			value = StringUtils.filterString(this.value);
		}
		if(null != value && value.trim().length()>length){
			value = value.substring(0,length);
			value += append;
		}
		try {
			this.pageContext.getOut().print(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}
	
}