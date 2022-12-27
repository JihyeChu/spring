package com.tjoeun.service;

import org.springframework.ui.Model;

import com.tjoeun.vo.MvcboardVO;

public interface MvcboardService {

	public abstract void execute(MvcboardVO mvcboardVO);
	public abstract void execute(Model model);
	
}