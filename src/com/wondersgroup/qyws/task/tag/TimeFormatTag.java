package com.wondersgroup.qyws.task.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.time.DateFormatUtils;

public class TimeFormatTag extends TagSupport{

	private static final long serialVersionUID = 1L;
	
	private String value;
	private String format;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	@Override
	public int doEndTag() throws JspException{
		if(null != this.value && this.value.trim().length()>0){
			try{
				this.pageContext.getOut().print(DateFormatUtils.format(Long.parseLong(this.value),this.format));
			}catch(Exception e){}
		}
		return super.doEndTag();
	}
	
}