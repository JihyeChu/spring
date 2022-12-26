package com.tjoeun.springAOP2_java;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import aopTest.AopTest;

public class MainClass {
	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Student student = ctx.getBean("student", Student.class);
		System.out.println(student);
		
		Worker worker = ctx.getBean("worker", Worker.class);
		System.out.println(worker);
		
		AopTest aopTest = ctx.getBean("aopTest", AopTest.class);
		
		System.out.println("----------------------------");
		student.getStudentInfo();
		System.out.println("----------------------------");
		worker.getWorkerInfo();
		System.out.println("----------------------------");
		aopTest.aopTest();
		System.out.println("----------------------------");
		student.summer();
		System.out.println("----------------------------");
	}
}
