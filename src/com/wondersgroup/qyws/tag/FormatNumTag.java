package com.wondersgroup.qyws.tag;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

public class FormatNumTag extends TagSupport{ 
	private static final long serialVersionUID = 1L;
	private String value = ""; //数字
	private int length = 2;//保留小数点位数
	private String append = "";//扩展后缀
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
		if(null != value && StringUtils.isNotBlank(value)){
			try {
				value = formate(value);
				if (value.endsWith(".00") || value.endsWith(".0")) {
					String[] temstr = value.split("\\.");
					value = temstr[0];
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			value = formate("0.00");
		}
		try {
			this.pageContext.getOut().print(value+append);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}
	
	private String formate(String value){		
		BigDecimal tempNum = new BigDecimal(value).setScale(length, RoundingMode.HALF_UP);
		return tempNum.toPlainString();
	}	
	
}
