package com.tjoeun.springDI8_java_in_xml;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//	java 파일에서 xml 파일의 bean 설정 정보를 읽어오려면 @ImportResource 어노테이션으로
//	읽어들일 xml 파일을 java 파일에 포함시키면 된다.
@ImportResource("classpath:applicationCTX.xml")

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


















