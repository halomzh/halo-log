package com.halo.log.config.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author shoufeng
 */

@Data
@ConfigurationProperties(prefix = HaloLogProperties.PREFIX)
@NoArgsConstructor
@AllArgsConstructor
public class HaloLogProperties {

	public static final String PREFIX = "halo.log";

	/**
	 * 是否开启
	 */
	private boolean enable = false;

}
