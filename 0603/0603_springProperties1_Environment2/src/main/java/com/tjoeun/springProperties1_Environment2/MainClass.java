package com.tjoeun.springProperties1_Environment2;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

public class MainClass {
	public static void main(String[] args) {
		
//		스프링 컨테이너를 만든다.
		ConfigurableApplicationContext ctx = new GenericXmlApplicationContext();
//		스프링 컨테이너의 환경 설정 정보를 읽어온다.
		ConfigurableEnvironment env = ctx.getEnvironment();
//		스프링 환경 설정 정보 중에서 properties 정보만 읽어온다.
		MutablePropertySources mutablePropertySources = env.getPropertySources();
		
		try {
//			스프링 환경 설정 정보의 properties에 admin.properties 파일의 정보를 추가한다.
			mutablePropertySources.addLast(new ResourcePropertySource("classpath:admin.properties"));
			System.out.println("admin.id: " + env.getProperty("admin.id"));
			System.out.println("admin.pw: " + env.getProperty("admin.pw"));
			System.out.println("properties 파일의 내용을 읽어온다.");
		} catch (IOException e) {
			System.out.println("admin.properties 파일을 읽어올 수 없습니다.");
			e.printStackTrace();
		}
		System.out.println("-----------------------------------------------");
		
//		아래와 같이 스프링 컨테이너를 다시 만들면 admin.properties 파일의 properties 정보가 존재하지 않는다.
//		AbstractApplicationContext gCtx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		AdminConnection adminConnection = gCtx.getBean("adminConnection", AdminConnection.class);

//		스프링 컨테이너를 다시 만들어 환경 설정 정보를 얻어오면 addlast() 메소드를 사용해 추가한
//		properties 정보가 없기 때문에 ConfigurableApplicationCOntext 인터페이스 타입 객체의 환경 설정 정보를
//		그대로 사용하기 위해 xml 파일에서 bean 설정 정보를 읽어내는 GenericXmlApplicationContext 클래스
//		타입으로 형변환 시켜 사용한다.
		
		GenericXmlApplicationContext gCtx = (GenericXmlApplicationContext) ctx;
		gCtx.load("classpath:applicationCTX.xml");
		gCtx.refresh();
		
		AdminConnection adminConnection = gCtx.getBean("adminConnection", AdminConnection.class);
		System.out.println("adminId: " + adminConnection.getAdminId()); // null
		System.out.println("adminPw: " + adminConnection.getAdminPw()); // null
		System.out.println("-----------------------------------------------");
		
		/*
		System.out.println("AdminConnection 클래스의 bean이 생성된 후 properties 파일의 정보를 넣어준다.");
		adminConnection.setAdminId(env.getProperty("admin.id"));
		adminConnection.setAdminPw(env.getProperty("admin.pw"));
		System.out.println("admin.id: " + env.getProperty("admin.id")); // asdfg
		System.out.println("admin.pw: " + env.getProperty("admin.pw")); // 12345
		*/
		
		gCtx.close();
		
	}
}


























