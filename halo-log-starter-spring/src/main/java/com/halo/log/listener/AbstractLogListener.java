package com.halo.log.listener;

import com.halo.log.handler.LogHandler;
import com.halo.log.listener.event.LogEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;

/**
 * @author shoufeng
 */

@AllArgsConstructor
public abstract class AbstractLogListener implements ApplicationListener<LogEvent> {

	private LogHandler logHandler;

	@Override
	public void onApplicationEvent(LogEvent event) {
		logHandler.accept(event);
	}

}
