package com.tjoeun.springDI3_xml_constructor;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Student student = ctx.getBean("student", Student.class);
		System.out.println(student);
		
		StudentInfo studentInfo = ctx.getBean("studentInfo", StudentInfo.class);
		System.out.println(studentInfo);
		studentInfo.getStudentInfo();
	}
	
}
