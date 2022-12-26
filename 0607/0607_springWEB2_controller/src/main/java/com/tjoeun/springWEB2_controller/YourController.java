package com.tjoeun.springWEB2_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//	컨트롤러 클래스에 @RequestMapping 어노테이션을 붙여주면 컨트롤러에 붙여준 @RequestMapping 
//	어노테이션과 메소드에 붙여준 @RequestMapping 어노테이션의 요청을 합쳐서 요청해야 뷰 페이지를
//	제대로 찾아갈 수 있다.
@RequestMapping("/board")
public class YourController {

	@RequestMapping("/contentView")
	public String contentView() {
		System.out.println("YourController 클래스의 contentView() 메소드 실행");
		return "board/contentView";
	}
	
}
