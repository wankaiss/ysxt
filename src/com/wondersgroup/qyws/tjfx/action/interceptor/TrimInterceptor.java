package com.wondersgroup.qyws.tjfx.action.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 拦截器 - 去除页面参数字符串两端的空格
 */

@SuppressWarnings("serial")
public class TrimInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> parameters = invocation.getInvocationContext().getParameters();
		for (String key : parameters.keySet()) {
			Object value = parameters.get(key);
			if (value instanceof String[]) {
				String[] valueArray = (String[]) value;
				for (int i = 0; i < valueArray.length; i++) {
					valueArray[i] = valueArray[i].trim();
				}
				parameters.put(key, valueArray);
			}
		}
		return invocation.invoke();
	}

}
