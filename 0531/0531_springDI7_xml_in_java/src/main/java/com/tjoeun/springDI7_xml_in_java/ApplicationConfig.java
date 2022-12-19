package com.tjoeun.springDI7_xml_in_java;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration 
public class ApplicationConfig {
	
	@Bean
	public Student student2() {
		/*
		Student student = new Student();
		student.setName("홍길동");
		student.setAge(20);
		ArrayList<String> hobbies = new ArrayList<String>();
		hobbies.add("잠자기");
		hobbies.add("먹기");
		hobbies.add("놀기");
		student.setHobbies(hobbies);
		*/
		
		ArrayList<String> hobbies = new ArrayList<String>();
		hobbies.add("잠자기");
		hobbies.add("먹기");
		hobbies.add("놀기");
		Student student = new Student("임꺽정", 20, hobbies);
		
		
		student.setHeight(190);
		student.setWeight(70);
		return student; 
	}
}


















