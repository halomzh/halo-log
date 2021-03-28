package com.halo.log.example.interceptor;

import com.halo.log.interceptor.AbstractLogInterceptor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shoufeng
 */

@Component("logInterceptor")
public class LogInterceptor extends AbstractLogInterceptor {

	@Override
	public String getCurrentUserName(HttpServletRequest httpServletRequest) {
		//因为是模拟请求，所以固定是用户张三发起的请求
		return "张三";
	}

	@Override
	public String getCurrentUserNo(HttpServletRequest httpServletRequest) {
		//因为是模拟请求，所以固定是用户张三发起的请求
		return "zhangsanno";
	}

}
