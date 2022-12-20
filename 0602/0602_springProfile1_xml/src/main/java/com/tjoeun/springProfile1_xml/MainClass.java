package com.tjoeun.springProfile1_xml;

import java.util.Scanner;

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
		
//		beab을 설정하는 xml 파일에 profile 속성이 지정된 경우 아래와 같이 bean을 생성하면 안된다.
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX_dev.xml", "classpath:applicationCTX_run.xml");
		
//		profile이 설정된 xml 파일의 bean을 읽기 위해서는 스프링 컨테이너를 먼저 만든 후 읽어올 bean의
//		profile을 지정한 다음 스프링 컨테이너에 해당 profile이 지정된 bean을 load 시켜야 한다.
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		
//		읽어올 bean에 profile을 넣어준다.
//		getEnvironment() 메소드로 스프링 컨테이너의 환경 설정 정보를 읽어와서 setActiveProfiles() 메소드로
//		읽어올 profile 속성으로 지정한 값을 넣어준다.
		ctx.getEnvironment().setActiveProfiles(config);
		
		ctx.load("classpath:applicationCTX_dev.xml", "classpath:applicationCTX_run.xml");
		ctx.refresh();
		
		ServerInfo serverInfo = ctx.getBean("serverInfo", ServerInfo.class);
		System.out.println("ipNumber: "+ serverInfo.getIpNumber());
		System.out.println("portNumber: "+ serverInfo.getPortNumber());
	}
}


















