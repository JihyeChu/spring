package com.tjoeun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.tjoeun.vo.CardVO;

public class TransactionDAO {

//	DBCP Template 객체
	private JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

//	PlatformTransactionManager 객체를 선언하고 servlet-content.xml 파일에서 bean을 설정한다.
	private PlatformTransactionManager transactionManager;
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}


	//	CardVO 클래스 객체로 넘어온 내용을 각각 card, ticket 테이블에 저장하는 메소드
	public void buyTicket(final CardVO cardVO) {
		System.out.println("TransactionDAO의 buyTicket() 메소드 실행");
		System.out.println("consumerId: " + cardVO.getConsumerId());
		System.out.println("amount: " + cardVO.getAmount());
		
//		트랜잭션 상태를 처리할 객체를 생성하고 PlatformTransactionManager 객체를 사용해 초기화시킨다.
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		
//		try ~ catch로 트랜잭션을 처리한다.
		try {
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					String sql = "insert into card (consumerId, amount) values (?, ?)";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, cardVO.getConsumerId());
					pstmt.setString(2, cardVO.getAmount());
					return pstmt;
				}
			});
			
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					String sql = "insert into ticket (consumerId, amount) values (?, ?)";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, cardVO.getConsumerId());
					pstmt.setString(2, cardVO.getAmount());
					return pstmt;
				}
			});
			
//			정상 처리되면 트랜잭션을 commit 시킨다.
			transactionManager.commit(status);
			
		}catch (Exception e) {
			e.printStackTrace();
	
//			정상 처리가 되지 않으면 트랜잭션을 rollback 시킨다.
			transactionManager.rollback(status);
			
		}
		
		
	}
	
	
	
}

















