package com.halo.log.interceptor;

import com.halo.log.listener.info.LogInfo;
import com.halo.log.utils.AddressUtils;
import com.halo.log.utils.IpUtils;
import com.halo.log.utils.LogContextUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author shoufeng
 */

public abstract class AbstractLogInterceptor implements HandlerInterceptor {

	@Value("${spring.application.name}")
	private String appName;

	public abstract String getCurrentUserName(HttpServletRequest httpServletRequest);

	public abstract String getCurrentUserNo(HttpServletRequest httpServletRequest);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String ip = IpUtils.getIp(request);
		String region = AddressUtils.getRegionByIp(ip);
		String currentUserName = getCurrentUserName(request);
		String currentUserNo = getCurrentUserNo(request);
		LogInfo logInfo = LogContextUtils.getLogInfo();
		logInfo.setAppName(appName);
		logInfo.setIp(ip);
		logInfo.setRegion(region);
		logInfo.setUserName(currentUserName);
		logInfo.setUserNo(currentUserNo);
		logInfo.setRequestDate(new Date());
		LogContextUtils.setLogInfo(logInfo);

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		LogContextUtils.clear();
	}

}
