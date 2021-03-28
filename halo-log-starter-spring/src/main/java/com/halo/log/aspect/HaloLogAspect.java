package com.halo.log.aspect;

import com.halo.log.annotation.HaloLog;
import com.halo.log.listener.event.LogEvent;
import com.halo.log.listener.info.LogInfo;
import com.halo.log.utils.LogContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;

/**
 * @author shoufeng
 */

@Aspect
@Slf4j
public class HaloLogAspect {

	@Pointcut("@annotation(com.halo.log.annotation.HaloLog)")
	public void haloLogPointcut() {
	}

	@Around("haloLogPointcut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		Object[] args = point.getArgs();
		String clazzName = point.getTarget().getClass().getName();
		String methodName = point.getSignature().getName();
		HaloLog haloLog = ((MethodSignature) point.getSignature()).getMethod().getAnnotation(HaloLog.class);
		LogInfo logInfo = new LogInfo();
		BeanUtils.copyProperties(LogContextUtils.getLogInfo(), logInfo);
		logInfo.setAction(haloLog.action());
		logInfo.setMethodName(clazzName + "#" + methodName);
		logInfo.setArgs(args);
		long startMills = System.currentTimeMillis();

		Object result;
		try {
			result = point.proceed();
			logInfo.setIsExecuteSuccess(true);
			logInfo.setResult(result);
			logInfo.setExecuteMills(System.currentTimeMillis() - startMills);
			LogContextUtils.publishEvent(new LogEvent(logInfo));
		} catch (Exception e) {
			logInfo.setIsExecuteSuccess(false);
			logInfo.setExecuteMills(System.currentTimeMillis() - startMills);
			logInfo.setErrMessage(e.toString());
			LogContextUtils.publishEvent(new LogEvent(logInfo));
			throw e;
		}

		return result;
	}

}
