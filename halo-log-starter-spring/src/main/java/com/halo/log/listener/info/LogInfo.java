package com.halo.log.listener.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shoufeng
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogInfo implements Serializable {

	/**
	 * 应用名称
	 */
	private String appName;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 账号
	 */
	private String userNo;

	/**
	 * 动作
	 */
	private String action;

	/**
	 * 方法名称
	 */
	private String methodName;

	/**
	 * 请求时间
	 */
	private Date requestDate;

	/**
	 * 入参
	 */
	private Object[] args;

	/**
	 * 出参
	 */
	private Object result;

	/**
	 * 是否执行成功
	 */
	private Boolean isExecuteSuccess;

	/**
	 * 异常
	 */
	private String errMessage;

	/**
	 * 执行时长(毫秒)
	 */
	private Long executeMills;

	/**
	 * IP地址
	 */
	private String ip;

	/**
	 * 地址（通过ip拿到地址）
	 */
	private String region;

}
