package com.tjoeun.springWEB6_DBCPTemplate_board;

import org.springframework.jdbc.core.JdbcTemplate;

public class Constant {

//	JdbcTemplate 클래스 객체를 public static으로 선언하는 이유는 public으로 선언해야
//	현재 클래스 외부에서 자유롭게 접근할 수 있고 static으로 선언해야 클래스의 객체를
//	선언하지 않고 클래스 이름에 "."을 찍어서 접근할 수 있기 때문이다.
//	컨트롤러에서 프로젝트가 실행될 때 초기화된 JdbcTemplate 객체를 MvcboardDAO 클래스에서
//	사용할 수 있도록 하기 위해서이다. 
	public static JdbcTemplate template;
	
}
