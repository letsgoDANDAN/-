package cn.jmu.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.jmu.service.UserService;

public class test1 {

	private static ApplicationContext apc;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		apc = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		UserService userService=(UserService)apc.getBean("userServiceImpl");
		String name=userService.login("6", "6");
		System.out.println(name);
	}

}
