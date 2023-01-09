package com.tjoeun.springWEB6_DBCPTemplate_board;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
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

import com.tjoeun.dao.MybatisDAO;
import com.tjoeun.service.MvcboardService;
import com.tjoeun.service.ReplyService;
import com.tjoeun.vo.MvcboardList;
import com.tjoeun.vo.MvcboardVO;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

//	여기부터
	private JdbcTemplate template;

	@Autowired // @Autowired을 붙여서 선언한 메소드는 프로젝트가 시작될 때 자동으로 실행된다.
	public void setTemplate(JdbcTemplate template) {
		System.out.println("안녕");
		this.template = template;

		Constant.template = this.template;
	}
//	여기까지 mybatis로 변환이 완료되면 주석으로 처리한다.	

//	servlet-context.xml 파일에서 생성한 mybatis bean(sqlSession)을 사용하기 위해서 SqlSession
//	인터페이스 타입의 객체를 선언한다.
//	servlet-context.xml 파일에서 생성된 mybatis bean을 자동으로 SqlSession 인터페이스 타입의
//	객체에 넣어주도록 하기 위해서 @Autowired 어노테이션을 붙여준다.
	@Autowired
	private SqlSession sqlSession;

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

	@RequestMapping("/insertOK")
	public String insertOK(HttpServletRequest request, Model model, MvcboardVO mvcboardVO) {
		System.out.println("컨트롤러의 insertOK() 메소드 실행 - 커맨드 객체 사용");

//		model.addAttribute("request", request);

//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcboardService service = ctx.getBean("insert", InsertService.class);
//		service.execute(model);

//		System.out.println(mvcboardVO);
//		mapper로 사용할 interface 객체에 mybatis mapper를 얻어와서 넣어준다.
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);

//		메인글을 저장하는 insert sql 명령을 실행한다.
		mapper.insert(mvcboardVO);

		return "redirect:list";
	}

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 list() 메소드 실행");

//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcboardService service = ctx.getBean("select", SelectService.class);
//		service.execute(model);

//		mapper를 얻어온다.
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);

//		1페이지에 표시할 글의 개수, 브라우저에 표시할 페이지 번호, 전체 글의 개수를 지정한다.
		int pageSize = 10;
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
		}
		int totalCount = mapper.selectCount();

//		1페이지 분량의 글과 페이징 작업에 사용할 변수를 초기화시킨다.
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		MvcboardList mvcboardList = ctx.getBean("mvcboardList", MvcboardList.class);
		mvcboardList.initMvcboardList(pageSize, totalCount, currentPage);

//		1페이지 분량의 글 목록을 얻어온다.
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", mvcboardList.getStartNo());
		hmap.put("endNo", mvcboardList.getEndNo());
		mvcboardList.setList(mapper.selectList(hmap));

//		list.jsp로 넘겨줄 데이터를 Model 인터페이스 객체에 넣어준다.
		model.addAttribute("mvcboardList", mvcboardList);

		return "list";
	}

	@RequestMapping("/increment")
	public String increment(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 increment() 메소드 실행");

//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcboardService service = ctx.getBean("increment", IncrementService.class);
//		service.execute(model);

//		mapper를 얻어온다.
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);

//		조회수를 증가시킬 글번호를 받는다.
		int idx = Integer.parseInt(request.getParameter("idx"));

//		조회수를 증가시키는 메소드를 실행한다.
		mapper.increment(idx);

		model.addAttribute("idx", request.getParameter("idx"));
		model.addAttribute("currentPage", request.getParameter("currentPage"));

		return "redirect:contentView";
	}

	@RequestMapping("/contentView")
	public String contentView(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 contentView() 메소드 실행");

//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcboardService service = ctx.getBean("contentView", ContentViewService.class);
//		service.execute(model);

//		mapper를 얻어온다.
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);

//		조회수를 증가시킨(화면에 표시할) 글번호를 받는다. 
		int idx = Integer.parseInt(request.getParameter("idx"));

//		글 1건을 얻어와서 MvcboardVO 객체에 저장한다.
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		MvcboardVO mvcboardVO = ctx.getBean("mvcboardVO", MvcboardVO.class);
		mvcboardVO = mapper.selectByIdx(idx);

//		브라우저에 출력할 글, 작업 후 돌아갈 페이지 번호, 줄바꿈에 사용할 "\r\n"을 Model 인터페이스
//		객체에 저장한다.
		model.addAttribute("vo", mvcboardVO);
		model.addAttribute("currentPage", request.getParameter("currentPage"));
		model.addAttribute("enter", "\r\n");

		return "contentView";
	}

	@RequestMapping("/update")
	public String update(HttpServletRequest request, Model model, MvcboardVO mvcboardVO) {
		System.out.println("컨트롤러의 update() 메소드 실행");

//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcboardService service = ctx.getBean("update", UpdateService.class);
//		service.execute(model);

//		mapper를 얻어온다.
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);

//		글을 수정하는 메소드를 실행한다.
		mapper.update(mvcboardVO);

//		글 수정 작업 후 돌아갈 페이지 번호를 Model 인터페이스 객체에 저장한다.
		model.addAttribute("currentPage", request.getParameter("currentPage"));

		return "redirect:list";
	}

	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 update() 메소드 실행");

//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcboardService service = ctx.getBean("delete", DeleteService.class);
//		service.execute(model);

//		mapper를 얻어온다.
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);

//		삭제할 글번호를 받는다.
		int idx = Integer.parseInt(request.getParameter("idx"));

//		글을 삭제하는 메소드를 실행한다.
		mapper.delete(idx);

//		글 삭제 작업 후 돌아갈 페이지 번호를 Model 인터페이스 객체에 저장한다.
		model.addAttribute("currentPage", request.getParameter("currentPage"));

		return "redirect:list";
	}

	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("컨트롤러의 reply() 메소드 실행");

//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcboardService service = ctx.getBean("contentView", ContentViewService.class);
//		service.execute(model);

//		mapper를 얻어온다.
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
//		답글을 입력할 글번호를 받는다.
		int idx = Integer.parseInt(request.getParameter("idx"));
//		글 1건을 얻어와서 MvcboardVO 객체에 저장한다.
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		MvcboardVO mvcboardVO = ctx.getBean("mvcboardVO", MvcboardVO.class);
		mvcboardVO = mapper.selectByIdx(idx);

//		브라우저에 출력할 글, 작업 후 돌아갈 페이지 번호, 줄바꿈에 사용할 "\r\n"을 Model 인터페이스
//		객체에 저장한다.
		model.addAttribute("vo", mvcboardVO);
		model.addAttribute("currentPage", request.getParameter("currentPate"));
		model.addAttribute("enter", "\r\n");
		
		return "reply";
	}

	@RequestMapping("/replyInsert")
	public String replyInsert(HttpServletRequest request, Model model, MvcboardVO mvcboardVO) {
		System.out.println("컨트롤러의 replyInsert() 메소드 실행");

//		model.addAttribute("request", request);
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
//		MvcboardService service = ctx.getBean("reply", ReplyService.class);
//		service.execute(model);
		
//		mapper를 얻어온다.
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);

//		커맨드 객체를 사용하기 때문에 별도의 처리를 하지 않아도 답변할 원본 글번호(idx), 글그룹(gup), 글레벨(lev),
//		같은 글 그룹에서 글 출력 순서(seq), 답글 작성자 이름(name), 답글 제목(subject), 답글 내용(content)을
//		받을 수 있다.
//		커맨드 객체를 이용해서 답글 내용을 받았으면 lev와 seq는 1씩 증가 시켜야 한다.
		mvcboardVO.setLev(mvcboardVO.getLev() + 1);
		mvcboardVO.setSeq(mvcboardVO.getSeq() + 1);
		
//		답글이 삽입될 위치를 확보하기 위해 조건에 만족하는 seq를 1씩 증가시키는 메소드를 실행한다.
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("gup", mvcboardVO.getGup());
		hmap.put("seq", mvcboardVO.getSeq());
		mapper.incrementSeq(hmap);
//		답글을 저장하는 메소드를 실행한다.
		mapper.replyInsert(mvcboardVO);
		
//		답글 저장 후 돌아갈 페이지 번호를 Model 인터페이스 객체에 저장한다.
		model.addAttribute("currentPage", request.getParameter("currentPage"));
		
		return "redirect:list";
	}
}
