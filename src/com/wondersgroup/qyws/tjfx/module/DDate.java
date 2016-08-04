package com.wondersgroup.qyws.tjfx.module;

import java.io.Serializable;

/**
 * 日期维度表
 * 
 * @author Administrator
 * 
 */
public class DDate implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long date_id;// 日期ID
	private String date_des;// 日期描述
	private String date;// 日编号
	private String week;// 周编号
	private String month;// 月编号
	private String season;// 季编号
	private String day;// 星期
	private String year;// 自然年度
	private String ybnd_zg;// 医保年度（职工）
	private String ybnd_jm;// 医保年度（居民）

	public Long getDate_id() {
		return date_id;
	}

	public void setDate_id(Long dateId) {
		date_id = dateId;
	}

	public String getDate_des() {
		return date_des;
	}

	public void setDate_des(String dateDes) {
		date_des = dateDes;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getYbnd_zg() {
		return ybnd_zg;
	}

	public void setYbnd_zg(String ybndZg) {
		ybnd_zg = ybndZg;
	}

	public String getYbnd_jm() {
		return ybnd_jm;
	}

	public void setYbnd_jm(String ybndJm) {
		ybnd_jm = ybndJm;
	}

}
