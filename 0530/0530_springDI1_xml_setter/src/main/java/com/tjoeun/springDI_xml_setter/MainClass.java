package com.tjoeun.springDI_xml_setter;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		
		/*
//		자바에서 사용하던 방식
		MyCalculator myCalculator = new MyCalculator();
		myCalculator.setFirstNum(8);
		myCalculator.setSecondNum(5);
		
//		Calculator calculator = new Calculator();
//		myCalculator.setCalculator(calculator); // 의존성 주입
		myCalculator.setCalculator(new Calculator());
		*/
		
//		xml 파일에서 설정한 bean 설정 정보를 읽어들인다.
//		class의 bean을 설정하는 xml 파일 만들기: src/main/resources에서 우클릭 => New =>
//		Spring Bean Configuration File => xml 파일명 입력 => Finish 버튼 클릭
	
//		xml 파일에서 설정한 bean을 얻어온다.
//		applicationCTX.xml이 위치한 경로 src/main/resources를 classpath라 부른다.
		String configLocation = "classpath:applicationCTX.xml";
		
//		GenericXmlApplicationContext 클래스 생성자의 인수로 bean을 설정하는 xml 파일의 경로와 이름을 넘겨주면
//		xml 파일의 내용을 읽어서 java 객체로 변환한 후 부모 클래스인 AbstractApplicationContext 클래스 객체에 저장한다.
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
//		getBean() 메소드로 AbstractApplicationContext 클래스 타입의 객체 ctx에 저장된 bean을 얻어온다.
//		getBean("얻어올 bean의 id", bean을 생성할 클래스 이름.class)
		MyCalculator myCalculator = ctx.getBean("myCalculator", MyCalculator.class);		
		System.out.println(myCalculator);
		
		myCalculator.add();
		myCalculator.sub();
		myCalculator.mul();
		myCalculator.div();
	}
}



















