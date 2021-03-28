package com.halo.log.example;

import com.halo.log.example.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shoufeng
 */

@SpringBootApplication
@RestController
@RequestMapping("/example")
@Slf4j
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Autowired
	private LogService logService;

	@GetMapping("/get/1")
	public void get1() {
		logService.login("aaaaaa");
	}

	@GetMapping("/get/2")
	public void get2() {
		logService.logout();
	}

	@GetMapping("/get/3")
	public void get3() {
		logService.actionOne();
	}

	@GetMapping("/get/4")
	public void get4() {
		logService.actionTwo("1", "2", "3");
	}

	@GetMapping("/get/5")
	public void get5() {
		logService.actionThree();
	}

	@GetMapping("/get/6")
	public void get6() {
		logService.actionFour("a", "b");
	}

	@GetMapping("/get/7")
	public void get7() {
		logService.actionFive("xxxxx");
	}

}
