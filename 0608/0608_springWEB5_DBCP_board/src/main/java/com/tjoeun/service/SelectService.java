package com.tjoeun.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.tjoeun.dao.MvcboardDAO;
import com.tjoeun.vo.MvcboardList;
import com.tjoeun.vo.MvcboardVO;

public class SelectService implements MvcboardService {

	@Override
	public void execute(MvcboardVO mvcboardVO) { }

	@Override
	public void execute(Model model) {
		System.out.println("SelectService의 execute() 메소드 실행");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
//		MvcboardDAO 클래스의 bean을 얻어온다.
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		MvcboardDAO mvcboardDAO = ctx.getBean("mvcboardDAO", MvcboardDAO.class);
		
//		브라우저에 출력할 글의 개수를 정한다.
		int pageSize = 10;
//		HttpServletRequest 인터페이스 객체에 저장되서 넘어오는 화면에 표시할 페이지 번호를 받는다.
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) { }
//		테이블에 저장된 전체 글의 개수를 얻어오는 메소드를 실행한다.
		int totalCount = mvcboardDAO.selectCount();
//		System.out.println(totalCount);
		
//		1페이지 분량의 글과 페이지 작업에 사용할 8개의 변수를 기억하는 MvcboardList 클래스의 
//		bean을 얻어온다.
		MvcboardList mvcboardList = ctx.getBean("mvcboardList", MvcboardList.class);
//		jsp로 작업할 때 처럼 생성자를 사용해서 초기화가 불가능하므로 MvcboardList 클래스의 bean을
//		얻어온 후 8개의 변수를 초기화시키는 메소드를 실행한다.
		mvcboardList.initMvcboardList(pageSize, totalCount, currentPage);
		
//		MvcboardList 클래스의 1페이지 분량의 글 목록을 기억하는 ArrayList에 1페이지 분량의 글을
//		테이블에서 얻어와서 넣어준다.
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", mvcboardList.getStartNo());
		hmap.put("endNo", mvcboardList.getEndNo());
		mvcboardList.setList(mvcboardDAO.selectList(hmap));
		System.out.println(mvcboardList);
		
//		list.jsp로 넘겨줄 데이터를 Model 인터페이스 객체에 넣어준다.
		model.addAttribute("mvcboardList", mvcboardList);
	}

}











