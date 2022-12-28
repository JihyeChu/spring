package com.tjoeun.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.tjoeun.dao.MvcboardDAO;
import com.tjoeun.vo.MvcboardVO;

public class IncrementService implements MvcboardService {

	@Override
	public void execute(MvcboardVO mvcboardVO) {

	}

	@Override
	public void execute(Model model) {
		System.out.println("IncrementService의 execute() 메소드 실행");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
//		Model 인터페이스 객체에 저장되어 넘어온 HttpServletRequest 인터페이스 객체에서 조회수를
//		증가시킬 글번호를 받는다. 
		int idx = Integer.parseInt(request.getParameter("idx"));
		
//		조회수를 증가시키는 메소드를 실행한다.
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		MvcboardDAO mvcboardDAO = ctx.getBean("mvcboardDAO", MvcboardDAO.class);
		mvcboardDAO.increment(idx);
	}

}



















