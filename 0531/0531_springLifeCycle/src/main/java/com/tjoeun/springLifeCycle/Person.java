package com.tjoeun.springLifeCycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Person implements InitializingBean, DisposableBean {

	private String name;
	private int age;
	
	public Person() { 
		System.out.println("기본 생성자");
	}
	
	public Person(String name, int age) {
		System.out.println("name, age를 인수로 넘겨받아 초기화시키는 생성자");
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

//	InitializingBean 인터페이스를 구현하면 afterPropertiesSet() 메소드를 반드시 Override 해서
//	사용해야 하고 afterPropertiesSet() 메소드는 bean이 생성(생성자가 실행)된 후 자동으로 실행되는 메소드이다.
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiseSet() 메소드 실행");
	}

//	DisposableBean 인터페이스를 구현하면 destroy() 메소드를 반드시 Override 해서 사용해야 하고
//	destroy() 메소드는 bean이 소멸(DI 컨테이너에서 close() 메소드가 실행)된 후 자동으로 실행한 내용을 구현한다.
	@Override
	public void destroy() throws Exception {
		System.out.println("destroy() 메소드 실행");
	}
	
	
}














