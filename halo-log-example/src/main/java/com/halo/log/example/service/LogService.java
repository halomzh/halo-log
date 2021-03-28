package com.halo.log.example.service;

/**
 * @author shoufeng
 */

public interface LogService {

	/**
	 * 登录
	 *
	 * @param param 参数
	 * @return 结果
	 */
	String login(String param);

	/**
	 * 登出
	 *
	 * @return 结果
	 */
	String logout();

	/**
	 * 动作1: 无参
	 *
	 * @return 结果
	 */
	String actionOne();

	/**
	 * 动作2: 多参
	 *
	 * @return 结果
	 */
	String actionTwo(String param1, String param2, String param3);

	/**
	 * 动作3: 无参，无返回值
	 */
	void actionThree();

	/**
	 * 动作4: 多参，无返回值
	 */
	void actionFour(String param1, String param2);

	/**
	 * 动作5: 发生异常
	 */
	String actionFive(String param);
}
