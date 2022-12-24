package com.tjoeun.springAOP1_xml;

import java.text.SimpleDateFormat;

import org.aspectj.lang.ProceedingJoinPoint;

//	공통 기능 메소드를 모아놓은 클래스
public class LogAop {
	
//	before
	public void before() {
		System.out.println("LogAop 클래스의 before() 메소드가 실행됨");
	}

//	after-returning
	public void afterReturning() {
		System.out.println("LogAop 클래스의 afterReturning() 메소드가 실행됨");
	}
	
//	after-throwing
	public void afterThrowing() {
		System.out.println("LogAop 클래스의 afterThrowing() 메소드가 실행됨");
	}
	
//	after
	public void after() {
		System.out.println("LogAop 클래스의 after() 메소드가 실행됨");
	}
	
//	around
//	1. around AOP 메소드는 핵심 기능이 실행되고 난 후 리턴되는 데이터 타입을 예측할 수 없으므로
//	   모든 데이터 타입을 포함하는 자바의 최상위 클래스인 Object로 지정한다.
//	2. around AOP 메소드의 인수로 실행할 핵심 기능(메소드)이 넘어온다.
//		=> 반드시 인수로 ProceedingJoinPoint 인터페이스 타입의 객체를 사용한다.
//		=> ProceedingJoinPoint 인터페이스가 자동 완성되지 않으면 pom.xml 파일의
//		   <scope>runtime</scope>를 삭제하거나 주석으로 처리한다.
//	3. around AOP 메소드는 try ~ finally 형태로 실행되면 catch는 throw Throwable로 대체된다.
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		
//		핵심 기능이 실행되기 전 실행할 내용을 코딩한다.
		System.out.println("LogAop 클래스의 around() 메소드가 실행됨 - 핵심 기능 실행 전");
//		핵심 기능이 실행되기 전 시간을 저장한다. => 시작 시간
		long start = System.currentTimeMillis(); // 13자리 정수 => long 
		
		try {
			System.out.println("LogAop 클래스의 around() 메소드가 실행됨 - 핵심 기능 실행 중");
//			ProceedingJoinPoint 인터페이스 객체로 넘어온 핵심 기능을 실행하고 결과를 Object 객체에 저장한다.
			Thread.sleep(2000);
			Object object = joinPoint.proceed();
			return object;
		}finally {
//			핵심 기능이 실행되고 난 후 실행할 내용을 코딩한다.
			System.out.println("LogAop 클래스의 around() 메소드가 실행됨 - 핵심 기능 실행 후");
//			핵심 기능이 실행되고 난 후 시간을 저장한다. => 종료 시간
			long end = System.currentTimeMillis();
			
//			실행 시간을 계산한다. => 종료 시간 - 실행 시간
			System.out.println("핵심 기능이 실행되는데 경과된 시간: " + (end - start) / 1000. + "초");
		
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
			System.out.println("핵심 기능이 실행되는데 경과된 시간: " + sdf.format(end - start - 32400000));
		}
		
	}
}

































