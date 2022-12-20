package com.tjoeun.springDI2_xml_setter;

import java.util.ArrayList;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		
//		MyInfo myInfo = new MyInfo();
//		myInfo.setName("홍길동");
//		myInfo.setHeight(158);
//		myInfo.setWeight(48);
//		ArrayList<String> hobbies = new ArrayList<String>();
//		hobbies.add("노래듣기");
//		hobbies.add("영화보기");
//		hobbies.add("잠자기");
//		myInfo.setHobbies(hobbies);
//		myInfo.setBmiCalculator(new BMICalculator());
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classPath:applicationCTX.xml");
		MyInfo myInfo = ctx.getBean("myInfo", MyInfo.class);
		
		System.out.println(myInfo);
		myInfo.getMyInfo();
		
		
	}
}



















