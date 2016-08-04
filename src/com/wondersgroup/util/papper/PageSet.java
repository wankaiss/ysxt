package com.wondersgroup.util.papper;

import java.util.List;

public class PageSet {
	public PageSet(){}
	public PageSet(int limit,int start){
		if(limit<=0){
			limit = 10;
		}
		this.limit = limit;
		this.start = start;
	}
	private int limit;
	private int start;
	private int count;
	private List<?> data;
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		if(limit<=0){
			limit = 20;
		}
		this.limit = limit;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}