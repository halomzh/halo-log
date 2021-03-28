package com.halo.log.utils;

import com.halo.log.listener.event.LogEvent;
import com.halo.log.listener.info.LogInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.NamedInheritableThreadLocal;

/**
 * @author shoufeng
 */

public class LogContextUtils implements ApplicationContextAware {

	private static ThreadLocal<LogInfo> logInfoThreadLocal = new NamedInheritableThreadLocal<>("log_info_thread_local");

	public static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		LogContextUtils.applicationContext = applicationContext;
	}

	public static <T> T getBean(Class<T> tClass) {
		return applicationContext.getBean(tClass);
	}

	public static <T> T getBean(Class<T> tClass, String beanName) {
		return applicationContext.getBean(beanName, tClass);
	}

	public static void publishEvent(LogEvent event) {
		applicationContext.publishEvent(event);
	}

	public static LogInfo getLogInfo() {
		LogInfo logInfo = logInfoThreadLocal.get();
		if (ObjectUtils.isEmpty(logInfo)) {
			logInfo = new LogInfo();
			logInfoThreadLocal.set(logInfo);
		}

		return logInfo;
	}

	public static void setLogInfo(LogInfo logInfo) {
		logInfoThreadLocal.set(logInfo);
	}

	public static void clear() {
		logInfoThreadLocal.remove();
	}

}
