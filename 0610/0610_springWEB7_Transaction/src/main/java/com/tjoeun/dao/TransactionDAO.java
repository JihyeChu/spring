package com.tjoeun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.tjoeun.vo.CardVO;

public class TransactionDAO {

//	DBCP Template 객체
	private JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

//	CardVO 클래스 객체로 넘어온 내용을 각각 card, ticket 테이블에 저장하는 메소드
	public void buyTicket(final CardVO cardVO) {
		System.out.println("TransactionDAO의 buyTicket() 메소드 실행");
		System.out.println("consumerId: " + cardVO.getConsumerId());
		System.out.println("amount: " + cardVO.getAmount());
		
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
	
	
	
}

















