package com.tjoeun.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.tjoeun.dao.MvcboardDAO;
import com.tjoeun.vo.MvcboardVO;

public class UpdateService implements MvcboardService {

	@Override
	public void execute(MvcboardVO mvcboardVO) {}

	@Override
	public void execute(Model model) {
		System.out.println("Update의 execute() 메소드 실행");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
//		Model 인터페이스 객체에 저장되어 넘어온 HttpServletRequest 인터페이스 객체에서 조회수를
//		증가시킨 글번호를 받는다. 
		int idx = Integer.parseInt(request.getParameter("idx"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		MvcboardDAO mvcboardDAO = ctx.getBean("mvcboardDAO", MvcboardDAO.class);

//		넘겨받은 데이터로 글 1건을 수정하는 메소드를 실행한다.
		mvcboardDAO.update(idx, subject, content);
		
//		넘겨받은 데이터를 MvcboardVO 클래스 bean에 저장하고 글 1건을 수정한다.
		MvcboardVO mvcboardVO = ctx.getBean("mvcboardVO", MvcboardVO.class);
		mvcboardVO.setIdx(idx);
		mvcboardVO.setSubject(subject);
		mvcboardVO.setContent(content);
		mvcboardDAO.update(mvcboardVO);
		
//		글 수정 작업 후 돌아갈 페이지 번호를 Model 인터페이스 객체에 저장한다.
		model.addAttribute("currentPage", request.getParameter("currentPage"));
	}

}






















