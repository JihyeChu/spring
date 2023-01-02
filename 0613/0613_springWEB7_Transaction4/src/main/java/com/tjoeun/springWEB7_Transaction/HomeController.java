package com.tjoeun.springWEB7_Transaction;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tjoeun.dao.TransactionDAO;
import com.tjoeun.service.TransactionService;
import com.tjoeun.vo.CardVO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
//	service 객체를 사용해서 dao에 접근하므로 주석으로 처리한다.
//	private TransactionDAO dao;
//	@Autowired
//	public void setDao(TransactionDAO dao) {
//		this.dao = dao;
//	}

//	sevice 객체를 생성하고 setter를 선언한다.
	private TransactionService service;
//	@Autowired 어노테이션을 붙여서 servlet-context.xml에서 정의한 TicketInsert 클래스의 bean을
//	자동으로 초기화시킨다.
	@Autowired
	public void setService(TransactionService service) {
		this.service = service;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/ticket")
	public String ticket(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 ticket() 메소드");
		return "ticket";
	}
	
	@RequestMapping("/ticketCard")
	public String ticketCard(HttpServletRequest request, Model model, CardVO cardVO) {
		System.out.println("컨트롤러의 ticketCard() 메소드");
		
//		DAO 클래스 butTicket() 메소드를 바로 실행하지 않고 service를 통해서 실행한다.
//		dao.buyTicket(cardVO);
		service.execute(cardVO);
		
//		커맨드 객체로 넘겨받은 내용을 뷰 페이지로 넘기기 위해 Model 인터페이스 객체에 저장한다.
		model.addAttribute("ticketInfo", cardVO);
		
		return "ticketEnd";
	}
}
















