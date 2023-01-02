package com.tjoeun.service;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.tjoeun.dao.TransactionDAO;
import com.tjoeun.vo.CardVO;

public class TicketInesrt implements TransactionService {

	private TransactionDAO dao;
	public void setDao(TransactionDAO dao) {
		this.dao = dao;
	}
	
//	외부 TransactionTemplate
	private TransactionTemplate transactionTemplate2;
	public void setTransactionTemplate2(TransactionTemplate transactionTemplate2) {
		this.transactionTemplate2 = transactionTemplate2;
	}

	
	@Override
	public void execute(final CardVO cardVO) {
		
//		외부 트랜잭션이 있을 때와 없을 때를 구분해서 실행시켜 보자
//		servlet-context.xml 파일의 <beans:property name="propagationBehavior" value="0" />의 value 속성에
//		지정한 값에 따라 트랜잭션 처리가 달라진다. 
//		외부 트랜잭션(transactionTemplate2), 내부 트랜잭션(transactionTemplate1) 모두 0이면 트랜잭션이
//		모두 적용된다. => 외부, 내부 모두 에러가 발생되면 rollback 된다. 
//		SUPPORTS: 외부 트랜잭션이 있으면 외부 트랜잭션에 합류하고 외부 트랜잭션이 없으면 트랜잭션을 생성하지 않는다.
//		외부 트랜잭션이 있고(0), 내부 트랜잭션이 1이면 모두 적용된다.
//		외부 트랜잭션이 없고, 내부 트랜잭션이 1이면 모두 트랜잭션이 적용되지 않는다.
//		MANDATORY: 외부 트랜잭션에 무조건 합류한다. 만약 외부 트랜잭션이 없으면 예외를 발생시킨다.
//		외부 트랜잭션이 있고, 내부 트랜잭션이 2이면 모두 트랜잭션이 적용된다.
//		외부 트랜잭션이 없고, 내부 트랜잭션이 2이면 IllegalTransactionStateException 예외가 발생된다.
//		3, 4, 5는 각각 0, 1, 2에 반대이다.
		
//		외부 트랜잭션이 없을 경우
//		cardVO.setAmount("1"); // 정상 실행
//		dao.buyTicket(cardVO);
//		cardVO.setAmount("5"); // 오휴 발생
//		dao.buyTicket(cardVO);
		
//		외부 트랜잭션이 있을 경우 => TransactionTemplate 코딩
		try {
			transactionTemplate2.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {

//					ticket.jsp의 입력과 상관없이 트랜잭션을 실행한다.
					cardVO.setAmount("1"); // 정상 실행
					dao.buyTicket(cardVO);
					cardVO.setAmount("5"); // 오류 발생
					dao.buyTicket(cardVO);
					
				}
			});
		}catch (Exception e) {
			e.printStackTrace();
		}

		
	}

}

















