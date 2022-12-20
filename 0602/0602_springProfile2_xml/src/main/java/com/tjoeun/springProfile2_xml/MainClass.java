package com.tjoeun.springProfile2_xml;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("실행할 작업 환경을 선택하세요(1 => dev, 2 => run): ");
		int info = scanner.nextInt();
		String config = "";
		switch(info) {
			case 1:
				config = "dev";
				break;
			case 2:
				config = "run";
				break;
		}
		
//		빈 컨테이너를 만든다.
//		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		
		
//		읽어올 bean에 profile을 넣어준다.
		ctx.getEnvironment().setActiveProfiles(config);
		
//		ctx.load("classpath:applicationCTX_dev.xml", "classpath:applicationCTX_run.xml");
//		GenericXmlApplicationContext 클래스로 xml 파일에서 설정한 bean 설정 정보를 컨테이너에 넣어주기
//		위해서 load() 메소드를 사용했었지만 AnnotationConfigApplicationContext 클래스로 java 파일에서
//		어노테이션을 붙여서 설정한 bean 설정 정보를 넣어주려면 register() 메소드를 사용해야 한다.
		ctx.register(ApplicationConfigDev.class, ApplicationConfigRun.class);
		ctx.refresh();
		
		ServerInfo serverInfo = ctx.getBean("serverInfo", ServerInfo.class);
		System.out.println("ipNumber: "+ serverInfo.getIpNumber());
		System.out.println("portNumber: "+ serverInfo.getPortNumber());
	}
}


















