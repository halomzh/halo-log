package com.halo.log.listener;

import com.halo.log.handler.LogHandler;
import com.halo.log.listener.event.LogEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认日志处理器，打印日志
 *
 * @author shoufeng
 */

@Slf4j
public class DefaultLogListener extends AbstractLogListener {

	public DefaultLogListener() {
		super(new LogHandler() {
			@Override
			public void accept(LogEvent logEvent) {
				log.info("接收到日志事件: {}", logEvent);
			}
		});
	}

}
