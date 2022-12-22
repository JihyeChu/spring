package com.tjoeun.springProperties1_Environment2;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class AdminConnection implements InitializingBean, EnvironmentAware, DisposableBean{

	private String adminId; 
	private String adminPw;
	private Environment env; // 스프링 컨테이너의 환경 설정 정보를 저장한다.
	
	public AdminConnection() {
		System.out.println("AdminConnection 클래스의 생성자 실행");
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPw() {
		return adminPw;
	}

	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}

	@Override
	public String toString() {
		return "AdminConnection [adminId=" + adminId + ", adminPw=" + adminPw + "]";
	}

//	InitializingBean 인터페이스를 구현하고 bean이 생성될 때 생성자가 실행되고 난 후 자동으로
//	실행되는 afterPropertiesSet() 메소드를 Override 한 다음에 properties 정보를 필드에 저장한다.
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("생성자가 실행되고 난 후 자동으로 afterPropertiesSet() 메소드 실행");
		adminId = env.getProperty("admin.id");
		adminPw = env.getProperty("admin.pw");
	}

//	EnvironmentAware 인터페이스를 구현하면 setEnvironment() 메소드를 Override 해서 사용할 수 있다.
//	setEnvironment() 메소드는 EnvironmentAware 인터페이스가 구현된 클래스의 bean이 
//	생성(refresh() 메소드가 실행)된 후 자동으로 실행된다.
//	setEnvironment() 메소드의 인수인 스프링 컨테이너의 환경 설정 정보를 기억하는 Environment 인터페이스 타입의
//	객체 environment에 스프링이 알아서 AdminConnection 클래스의 bean이 생성되는 순간
//	컨테이너의 환경 설정 정보를 넘겨준다. => properties 파일의 정보가 넘어온다.
	@Override
	public void setEnvironment(Environment environment) {
		System.out.println("AdminConnection 클래스의 bean이 생성된 후 자동으로 setEnvironment() 메소드 실행");
//		System.out.println("admin.id: " + environment.getProperty("admin.id"));
//		System.out.println("admin.pw: " + environment.getProperty("admin.pw"));
//		environment로 넘어온 환결 설정 정보를 AdminConnection 클래스에서 사용하기 위해 필드로 선언한
//		Environment 인터페이스 객체에 넣어준다.
		env = environment;
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("AdminConnection 클래스의 bean이 소멸된 후 자동으로 destroy() 메소드 실행");
	}
	
	
	
}


















