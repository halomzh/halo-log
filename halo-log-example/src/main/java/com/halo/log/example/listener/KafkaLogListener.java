package com.halo.log.example.listener;

import com.halo.log.handler.LogHandler;
import com.halo.log.listener.AbstractLogListener;
import com.halo.log.listener.event.LogEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author shoufeng
 */

@Slf4j
@Component
public class KafkaLogListener extends AbstractLogListener {

	public KafkaLogListener() {
		super(new LogHandler() {
			@Override
			public void accept(LogEvent logEvent) {
				//模拟向kafka指定topic投递日志
				log.info("kafka日志处理器接收到日志: LogEvent[{}]", logEvent);
			}
		});
	}

}
