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
import com.tjoeun.vo.CardVO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
//	TransactionDAO 클래스 객체를 선언한다. 
	private TransactionDAO dao;

//	@Autowired 어노테이션으로 servlet-context.xml 파일에서 프로젝트가 실행될 때 생성된
//	JdbcTemplate 객체를 참조해서 만든 TransactionDAO 클래스의 bean을 넣어준다.
	@Autowired
	public void setDao(TransactionDAO dao) {
		this.dao = dao;
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
//		System.out.println("consumerId: " + request.getParameter("consumerId"));
//		System.out.println("amount: " + request.getParameter("amount"));
		System.out.println("consumerId: " + cardVO.getConsumerId());
		System.out.println("amount: " + cardVO.getAmount());
		
//		DAO 클래스의 card, ticket 테이블에 insert sql 명령을 실행하는 메소드를 실행한다.
		dao.buyTicket(cardVO);
		
		
//		커맨드 객체로 넘겨받은 내용을 뷰 페이지로 넘기기 위해 Model 인터페이스 객체에 저장한다.
		model.addAttribute("ticketInfo", cardVO);
		
		return "ticketEnd";
	}
}
















