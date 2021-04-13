package com.halo.log.config;

import com.halo.log.aspect.HaloLogAspect;
import com.halo.log.config.properties.HaloLogProperties;
import com.halo.log.interceptor.AbstractLogInterceptor;
import com.halo.log.listener.AbstractLogListener;
import com.halo.log.listener.DefaultLogListener;
import com.halo.log.utils.LogContextUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;

/**
 * @author shoufeng
 */

@Configuration
@EnableConfigurationProperties(value = {
		HaloLogProperties.class
})
@ConditionalOnProperty(prefix = HaloLogProperties.PREFIX, name = "enable", havingValue = "true")
@Slf4j
@Data
public class HaloLogConfig implements ApplicationRunner, WebMvcConfigurer, ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Bean
	public LogContextUtils logContextUtils() {
		return new LogContextUtils();
	}

	@Bean
	@DependsOn(value = {"logContextUtils"})
	public HaloLogAspect haloLogAspect() {

		return new HaloLogAspect();
	}

	@Bean
	public DefaultLogListener defaultLogListener(){
		return new DefaultLogListener();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		HandlerInterceptor logInterceptor = applicationContext.getBean(AbstractLogInterceptor.class);
		if (ObjectUtils.isEmpty(logInterceptor)) {
			throw new RuntimeException("启动日志服务失败: 容器内至有且仅能存在一个AbstractLogInterceptor的子类");
		}
		registry.addInterceptor(logInterceptor)
				.addPathPatterns("/**");
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Map<String, AbstractLogListener> beanNameLogListenerMap = applicationContext.getBeansOfType(AbstractLogListener.class);
		if (beanNameLogListenerMap.size() < 1) {
			throw new RuntimeException("启动日志服务失败: 容器内至少存在一个AbstractLogListener的子类");
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
