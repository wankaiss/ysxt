package com.wondersgroup.util.spring;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtils implements ApplicationContextAware{
	private static ApplicationContext applicationContext;
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextUtils.applicationContext = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	
	public static Object getBean(String name){
		if(applicationContext.containsBean(name)){
			return applicationContext.getBean(name);
		}
		return null;
	}
	public static Object getBean(Class<?> clazz){
		Map<?,?> m = applicationContext.getBeansOfType(clazz);
		if(null == m || m.isEmpty()){
			return null;
		}
		Iterator<?> iter = m.keySet().iterator();
		if(iter.hasNext()){
			String key = iter.next().toString();
			return m.get(key);
		}
		return null;
	}
}