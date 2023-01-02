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
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.tjoeun.vo.CardVO;

public class TransactionDAO {

	private JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

//	내부 TransactionTemplate => transactionTemplate1로 수정한다.
	private TransactionTemplate transactionTemplate1;
	public void setTransactionTemplate1(TransactionTemplate transactionTemplate1) {
		this.transactionTemplate1 = transactionTemplate1;
	}

	public void buyTicket(final CardVO cardVO) {
		System.out.println("TransactionDAO의 buyTicket() 메소드 실행");
		System.out.println("consumerId: " + cardVO.getConsumerId());
		System.out.println("amount: " + cardVO.getAmount());
	
//		내부 트랜잭션
		try {
			
			transactionTemplate1.execute(new TransactionCallbackWithoutResult() {
				
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					
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
					
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
}

















