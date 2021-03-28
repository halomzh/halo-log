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
public class ElasticSearchLogListener extends AbstractLogListener {

	public ElasticSearchLogListener() {
		super(new LogHandler() {
			@Override
			public void accept(LogEvent logEvent) {
				//模拟向ElasticSearch投递日志
				log.info("elasticSearch日志处理器接收到日志: LogEvent[{}]", logEvent);
			}
		});
	}

}
