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
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		MvcboardDAO mvcboardDAO = ctx.getBean("mvcboardDAO", MvcboardDAO.class);
		
		int pageSize = 10;
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) { }
		int totalCount = mvcboardDAO.selectCount();
		
		MvcboardList mvcboardList = ctx.getBean("mvcboardList", MvcboardList.class);
		mvcboardList.initMvcboardList(pageSize, totalCount, currentPage);
		
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", mvcboardList.getStartNo());
		hmap.put("endNo", mvcboardList.getEndNo());
		mvcboardList.setList(mvcboardDAO.selectList(hmap));
		System.out.println(mvcboardList);
		
		model.addAttribute("mvcboardList", mvcboardList);
	}

}











