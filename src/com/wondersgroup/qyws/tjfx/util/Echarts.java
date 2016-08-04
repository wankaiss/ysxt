package com.wondersgroup.qyws.tjfx.util;

import java.util.List;

public class Echarts {

	
	public String xAxis(String rotate,List<String> xAxisData){
		StringBuilder xAxis=new StringBuilder();
		xAxis.append("[{axisLabel : {show:true,interval: 'auto',rotate: "+rotate+",margin: 8},type:'category',data:[");
		for(int i=0;i<xAxisData.size();i++){
			Object object=xAxisData.get(i);
			if(object==null){
				object="未知";
			}
			xAxis.append("'"+object.toString()+"',");
		}
		xAxis.deleteCharAt(xAxis.lastIndexOf(","));
		xAxis.append("]");
		xAxis.append("}]");
		return xAxis.toString();
	}
	
	public String series(List<String> name,List<String> color,List<String> type,List<List<String>> series_data,boolean markPoint,boolean markLine){
		StringBuilder series=new StringBuilder();
		series.append("[");
		for(int i=0;i<name.size();i++){
			List<String> series_dataList=series_data.get(i);
			series.append("{name:'"+name.get(i).toString()+"',");
			if(color!=null){
				series.append("itemStyle:{normal: {color: '"+color.get(i).toString()+"'}},");
			}
			series.append("type:'"+type.get(i).toString()+"',");
			series.append("data:[");
			for(int j=0;j<series_dataList.size();j++){
				if(series_dataList.get(j)==null){
					series.append("0,");
				}else{
					series.append(series_dataList.get(j)+",");
				}
			}
			series.deleteCharAt(series.lastIndexOf(","));
			series.append("],");
			if(markPoint==true){
				series.append("markPoint : {data : [ {type : 'max', name: '最大值'},{type : 'min', name: '最小值'}]},");
			}
			if(markLine==true){
				series.append("markLine : {data : [ {type : 'average', name: '平均值'}]},");
			}
			series.deleteCharAt(series.lastIndexOf(","));
			series.append("},");
		}
		series.deleteCharAt(series.lastIndexOf(","));
		series.append("]");
		return series.toString();
	}
}
