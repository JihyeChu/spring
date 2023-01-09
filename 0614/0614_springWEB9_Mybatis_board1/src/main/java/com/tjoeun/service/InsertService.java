package com.tjoeun.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.tjoeun.dao.MvcboardDAO;
import com.tjoeun.vo.MvcboardVO;

public class InsertService implements MvcboardService {

	@Override
	public void execute(MvcboardVO mvcboardVO) {
		System.out.println("InsertService의 execute() 메소드 실행 - VO 사용");

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		MvcboardDAO mvcboardDAO = ctx.getBean("mvcboardDAO", MvcboardDAO.class);
		mvcboardDAO.insert(mvcboardVO);
	}

	@Override
	public void execute(Model model) {
		System.out.println("InsertService의 execute() 메소드 실행 - Model 사용");
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		MvcboardVO mvcboardVO = ctx.getBean("mvcboardVO", MvcboardVO.class);
		mvcboardVO.setName(name);
		mvcboardVO.setSubject(subject);
		mvcboardVO.setContent(content);

		MvcboardDAO mvcboardDAO = ctx.getBean("mvcboardDAO", MvcboardDAO.class);
		mvcboardDAO.insert(mvcboardVO);
	}

}










