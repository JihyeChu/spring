package com.tjoeun.springWEB6_DBCPTemplate_board;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tjoeun.service.ContentViewService;
import com.tjoeun.service.DeleteService;
import com.tjoeun.service.IncrementService;
import com.tjoeun.service.InsertService;
import com.tjoeun.service.MvcboardService;
import com.tjoeun.service.ReplyService;
import com.tjoeun.service.SelectService;
import com.tjoeun.service.UpdateService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
//	JEBCTemplate을 사용하려면 servlet-context.xml 파일에서 프로젝트가 시작될 때 DriverManagerDataSource
//	클래스의 bean(데이터베이스 연결 정보)을 참조해서 생성한 JdbcTemplate 클래스의 bean을 컨트롤러에서
//	JdbcTemplate 클래스 타입의 객체를 생성하고 넣어줘야 한다. 
	private JdbcTemplate template;
	
	
//	JdbcTemplate 클래스 타입 객체의 getter & setter를 만든다. => 사실....getter는 안만들어도 된다.
	public JdbcTemplate getTemplate() {
		return template;
	}
	
//	프로젝트가 시작되고 servlet-context.xml 파일이 읽혀진 다음 JdbcTemplate 클래스 객체의 setter가
//	자동으로 실행되게 하기 위해서 @Autowired 어노테이션을 붙여준다.	
//	@Autowired 어노테이션이 붙어있는 setter의 인수 template으로 스프링이 servlet-context.xml 파일에서 
//	생성한 JdbcTemplate 클래스의 bean을 자동으로 전달하고 전달받은 bean으로 JdbcTemplate 클래스 객체를
//	초기화시킨다. 
	@Autowired // @Autowired을 붙여서 선언한 메소드는 프로젝트가 시작될 때 자동으로 실행된다.
	public void setTemplate(JdbcTemplate template) {
		System.out.println("안녕");
		this.template = template;
		
//		여기까지 정상적으로 실행되면 컨트롤러에서 JdbcTemplate를 사용할 수 있다.
//		데이터베이스 연결을 주로 DAO 클래스에서 사용하므로 컨트롤러 이외의 클래스에서
//		JdbcTemplate를 사용할 수 있게 하기 위해서 적당한 이름의 패키지(base-package)에
//		적당한 이름의 클래스(Constant) 만들고 적당한 이름으로 작성한 클래스의 정적
//		필드(public static JdbcTemplate template)에 servlet-context.xml 파일에서 생성된
//		JdbcTemplate 클래스의 bean을 넣어준다.
		Constant.template = this.template;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("컨트롤러의 home() 메소드 실행");
		return "redirect:list";
	}
	
	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 insert() 메소드 실행");
		return "insert";
	}
	
	
//	글 입력 폼에 입력된 내용을 테이블에 저장하는 메소드 => Model 인터페이스 객체 사용
	@RequestMapping("/insertOK")
	public String insertOK(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 insertOK() 메소드 실행 - Model 인터페이스 객체 사용");
	
//		insert.jsp에서 입력한 데이터가 저장된 HttpServletRequest 인터페이스 객체를 Model 인터페이스
//		객체에 저장한다.
		model.addAttribute("request", request);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		MvcboardService service = ctx.getBean("insert", InsertService.class);
		service.execute(model);

		return "redirect:list";
	}
		
//	브라우저에 출력할 1페이지 분량의 글 목록을 얻어오고 1페이지 분량의 글 목록을 출력하는 페이지를
//	호출하는 메소드
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 list() 메소드 실행");
		
		model.addAttribute("request", request);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		MvcboardService service = ctx.getBean("select", SelectService.class);
		service.execute(model);
		
		return "list";
	}
	
	@RequestMapping("/increment")
	public String increment(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 increment() 메소드 실행");
		
		model.addAttribute("request", request);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		MvcboardService service = ctx.getBean("increment", IncrementService.class);
		service.execute(model);
		
		model.addAttribute("idx", request.getParameter("idx"));
		model.addAttribute("currentPage", request.getParameter("currentPage"));
		
		return "redirect:contentView";
	}
	
	@RequestMapping("/contentView")
	public String contentView(HttpServletRequest request, Model model){
		System.out.println("컨트롤러의 contentView() 메소드 실행");
		
		model.addAttribute("request", request);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		MvcboardService service = ctx.getBean("contentView", ContentViewService.class);
		service.execute(model);
		
		return "contentView";
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 update() 메소드 실행");

		model.addAttribute("request", request);

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		MvcboardService service = ctx.getBean("update", UpdateService.class);
		service.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 update() 메소드 실행");
		
		model.addAttribute("request", request);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		MvcboardService service = ctx.getBean("delete", DeleteService.class);
		service.execute(model);
		
		return "redirect:list";
	}

	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 reply() 메소드 실행");
		
		model.addAttribute("request", request);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		MvcboardService service = ctx.getBean("contentView", ContentViewService.class);
		service.execute(model);
		
		return "reply";
	}
	
	@RequestMapping("/replyInsert")
	public String replyInsert(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 replyInsert() 메소드 실행");
		
		model.addAttribute("request", request);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		MvcboardService service = ctx.getBean("reply", ReplyService.class);
		service.execute(model);
		
		return "redirect:list";
	}
}

















