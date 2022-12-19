package com.tjoeun.springLifeCycle;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		
//		GenericXmlApplicationContext 클래스 객체를 생성할 때 생성자의 인수로 bean 설정 정보가 작성된
//		xml 파일을 넘겨주면 xml 파일에 설정된 내용대로 bean을 만들기 때문에 생성자가 실행되고 bean을 
//		생성하는 클래스에 InitializingBean 인터페이스를 구현해서 Override한 메소드 afterPropertiesSet()
//		가 자동으로 실행된다.
		/*
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Person person = ctx.getBean("person", Person.class);
		System.out.println(person.getName() + "님은 " + person.getAge() + "살 입니다.");
		System.out.println("------------------------------------------------------");
		
		Person person2 = ctx.getBean("person2", Person.class);
		System.out.println(person2.getName() + "님은 " + person2.getAge() + "살 입니다.");
		System.out.println("------------------------------------------------------");
		*/
		
//		DI 컨테이너를 만든다. => 빈 컨테이너 => 필요할 때 bean 정보를 채워서 사용한다.
//		GenericXmlApplicationContext 클래스 생성자로 bean 설정 정보를 넘기지 않고 만든 DI 컨테이너는
//		빈 컨테이너만 생성되고 bean 설정 정보는 들어있지 않다.
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		
//		load() 메소드로 xml 파일에서 정의한 bean 설정 정보를 DI 컨테이너에 넣어준다.
//		load() 메소드는 DI 컨테이너에 bean 설정 정보를 넣어주기만 하기 떄문에 아직 bean은 생성되지 
//		않은 상태이다. => 생성자가 실행되지 않은 상태이다.
		ctx.load("classpath:applicationCTX.xml"); // DI 컨테이너에 bean 설정 정보를 넣어준다.
	
//		load() 메소드를 사용해서 bean 설정 정보를 컨테이너에 넣어줄 경우 정상적인 처리(bean 생성)가
//		되게 하기 위해서 refresh() 메소드를 실행해야 한다.
//		refresh() 메소드가 실행되는 순간 load() 메소드로 DI 컨테이너에 넣어준 bean 설정 정보에 따른
//		bean이 생성되고 refresh() 메소드를 실행하지 않으면 getBean() 메소드에 의해서 bean을 얻어내는
//		순간 bean이 생성된다.
		ctx.refresh(); // bean 설정 정보에 따른 bean을 만든다.
		
		System.out.println("------------------------------------------------------");
		Person person = ctx.getBean("person", Person.class);
		System.out.println(person.getName() + "님은 " + person.getAge() + "살 입니다.");
		System.out.println("------------------------------------------------------");
		
		Person person2 = ctx.getBean("person2", Person.class);
		System.out.println(person2.getName() + "님은 " + person2.getAge() + "살 입니다.");
		System.out.println("------------------------------------------------------");
	
//		refresh() 메소드를 실행했더라도 close() 메소드를 실행하지 않으면 bean이 소멸될 때 destroy()
//		메소드는 실행되지 않는다.
//		close() 메소드를 실행했더라도 refresh()를 실행하지 않았으면 destroy() 메소드는 실행되지 않는다.
		ctx.close();
	}
}




















