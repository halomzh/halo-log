package com.halo.log.example.service.impl;

import com.halo.log.annotation.HaloLog;
import com.halo.log.example.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author shoufeng
 */

@Slf4j
@Component
public class LogServiceImpl implements LogService {

	@HaloLog(action = "登录动作")
	@Override
	public String login(String param) {

		return "响应登录: param[ " + param + "]";
	}

	@HaloLog(action = "登出动作")
	@Override
	public String logout() {

		return "响应登出";
	}

	@HaloLog(action = "动作一")
	@Override
	public String actionOne() {
		return "响应动作1";
	}

	@HaloLog(action = "动作二")
	@Override
	public String actionTwo(String param1, String param2, String param3) {
		return "响应动作2: " + param1 + "," + param2 + "," + param3;
	}

	@HaloLog(action = "动作三")
	@Override
	public void actionThree() {

	}

	@HaloLog(action = "动作四")
	@Override
	public void actionFour(String param1, String param2) {

	}

	@HaloLog(action = "动作五")
	@Override
	public String actionFive(String param) {
		throw new RuntimeException("模拟发生异常情况");
	}

}
