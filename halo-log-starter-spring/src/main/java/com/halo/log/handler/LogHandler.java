package com.halo.log.handler;

import com.halo.log.listener.event.LogEvent;

import java.util.function.Consumer;

/**
 * @author shoufeng
 */

public interface LogHandler extends Consumer<LogEvent> {
}
