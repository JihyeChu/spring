package com.tjoeun.springDI7_xml_in_java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		
//		xml 파일에서 설정한 bean 설정 정보를 읽어오려면 아래의 방법을 사용한다.
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		getBean("id", bean을 생성한 클래스 이름.class)
		
//		java 파일에서 설정한 bean 설정 정보를 읽어오려면 아래의 방법을 사용한다.
//		new AnnotationConfigApplicationContext(bean을 설정한 클래스 이름 class)
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//		getBean("메소드 이름", 메소드의 리턴타입.class);
		
		System.out.println("xml 파일에서 설정한 bean을 얻어온다.");
		Student student = ctx.getBean("student", Student.class);
		System.out.println(student);
		System.out.println("이름: " + student.getName());
		System.out.println("나이: " + student.getAge());
		System.out.println("취미: " + student.getHobbies());
		System.out.println("신장: " + student.getHeight());
		System.out.println("체중: " + student.getWeight());
		System.out.println("-------------------------------------");
		
		System.out.println("java 파일에서 설정한 bean을 얻어온다.");
		Student student2 = ctx.getBean("student2", Student.class);
		System.out.println(student2);
		System.out.println("이름: " + student2.getName());
		System.out.println("나이: " + student2.getAge());
		System.out.println("취미: " + student2.getHobbies());
		System.out.println("신장: " + student2.getHeight());
		System.out.println("체중: " + student2.getWeight());
		
	}
}




















