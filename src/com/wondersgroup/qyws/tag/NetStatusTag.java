package com.wondersgroup.qyws.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

public class NetStatusTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	private String type = "";
	private String value = ""; //数字
	private String append = "";//扩展后缀
	public String getAppend() {
		return append;
	}
	public void setAppend(String append) {
		this.append = append;
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
				if (type.equalsIgnoreCase("devicetype")) {
					String[] tempstr = StringUtils.split(value, ".");
					value = tempstr[tempstr.length-1];
				}
				int flag = Integer.valueOf(value);
				if (type.equalsIgnoreCase("devicetype")) {
					switch (flag) {
					case 3: 
						value = "hrDeviceProcessor";
						break;
					case 4:
						value = "hrDeviceNetwork";
						break;
					case 5:
						value = "hrDevicePrinter";
						break;
					case 6:
						value = "hrDeviceDiskStorage";
						break;
					default:
						value = "unknown";
						break;
					}
				}
				if (type.equalsIgnoreCase("devicestatus")) {
					switch (flag) {
					case 1: 
						value = "unknown";
						break;
					case 2:
						value = "running";
						break;
					case 3:
						value = "warning";
						break;
					case 4:
						value = "testing";
						break;
					case 5:
						value = "down";
						break;
					}
				}
				
				if (type.equalsIgnoreCase("routtype")) {
					switch (flag) {
					case 1:
						value = "other";
						break;
					case 2:
						value = "invalid";
						break;
					case 3:
						value = "direct";
						break;
					case 4:
						value = "indirect";
						break;
					}
				}
				if(type.equalsIgnoreCase("ipmactype")){
					switch (flag) {
					case 1:
						value = "other";
						break;
					case 2:
						value = "invalid";
						break;
					case 3:
						value = "dynamic";
						break;
					case 4:
						value = "static";
						break;
					}
				}
				if (type.equalsIgnoreCase("routproto")) {
					switch (flag) {
					case 1:
						value = "other";
						break;
					case 2:
						value = "local";
						break;
					case 3:
						value = "netmgmt";
						break;
					case 4:
						value = "icmp";
						break;
					case 5:
						value = "egp";
						break;
					case 6:
						value = "ggp";
						break;
					case 7:
						value = "hello";
						break;
					case 8:
						value = "rip";
						break;
					case 9:
						value = "is-is";
						break;
					case 10:
						value = "es-is";
						break;
					case 11:
						value = "ciscoIgrp";
						break;
					case 12:
						value = "bbnSpfIgp";
						break;
					case 13:
						value = "ospf";
						break;
					case 14:
						value = "bgp";
						break;
					}
				}
				if (type.equalsIgnoreCase("tcpstatus")) {
					switch (flag) {
					case 1:
						value = "closed";
						break;
					case 2:
						value = "listen";
						break;
					case 3:
						value = "synSent";
						break;
					case 4:
						value = "synReceived";
						break;
					case 5:
						value = "established";
						break;
					case 6:
						value = "finWait1";
						break;
					case 7:
						value = "finWait2";
						break;
					case 8:
						value = "closeWait";
						break;
					case 9:
						value = "lastAck";
						break;
					case 10:
						value = "closing";
						break;
					case 11:
						value = "timeWait";
						break;
					case 12:
						value = "deleteTCB";
						break;
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			value = "unknown";
		}
		try {
			this.pageContext.getOut().print(value+append);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
		
}
