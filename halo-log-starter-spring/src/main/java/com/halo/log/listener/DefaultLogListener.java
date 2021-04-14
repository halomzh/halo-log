package com.halo.log.listener;

import com.halo.log.listener.info.LogInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认日志处理器，打印日志
 *
 * @author shoufeng
 */

@Slf4j
public class DefaultLogListener extends AbstractLogListener {

	public DefaultLogListener() {
		super(logEvent -> {
			LogInfo logInfo = logEvent.getLogInfo();
			if (logInfo.getIsExecuteSuccess()) {
				log.info("接收到处理成功的日志事件: {}", logEvent);
			} else {
				log.error("接收到处理失败日志事件: {}", logEvent);
			}

		});
	}

}
