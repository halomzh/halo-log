package com.halo.log.listener.event;

import com.halo.log.listener.info.LogInfo;
import org.springframework.context.ApplicationEvent;

/**
 * @author shoufeng
 */

public class LogEvent extends ApplicationEvent {

	/**
	 * Create a new {@code ApplicationEvent}.
	 *
	 * @param source the object on which the event initially occurred or with
	 *               which the event is associated (never {@code null})
	 */
	public LogEvent(LogInfo source) {
		super(source);
	}

	public LogInfo getLogInfo() {
		return (LogInfo) getSource();
	}

}
