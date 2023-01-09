package com.tjoeun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.tjoeun.springWEB6_DBCPTemplate_board.Constant;
import com.tjoeun.vo.MvcboardVO;

public class MvcboardDAO {

//	DBCP Template 설정
//	DAO 클래스에서 DBCP Template을 사용하기 위해서 JdbcTemplate 클래스 타입의 객체를 선언한다.	
	private JdbcTemplate template;
	
//	기본 생성자에서 DAO 클래스의 객체(bean)이 생성되는 순간 servlet-context.xml 파일에서 
//	생성되어 컨트롤러가 전달받아 Constant 클래스의 JdbcTemplate클래스 타입의 객체에 저장된 
//	bean으로 초기화시킨다.
	public MvcboardDAO() {
		template = Constant.template;
	}
	
	
//	여기부터 ===========================================================
//	DataSource dataSource;
	
//	public MvcboardDAO() {
//		template = Constant.template;
//		try {
//			Context context = new InitialContext();
//			dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/oracle");
//		} catch (NamingException e) {
//			e.printStackTrace();
//			System.out.println("연결실패!!!");
//		}
//	}
//	여기까지 DBCP 방식을 사용하는 객체를 초기화시키는 부분이므로 DBCP Template으로
//	코드 변환이 완전히 처리된 후 모두 주석으로 바꿔준다.
//	=====================================================================
	
//	InsertService 클래스에서 호출되는 테이블에 저장할 메인글 데이터가 저장된 객체를 넘겨받고
//	insert sql 명령을 실행하는 메소드
//	insert, delete, update sql 명령을 실행하는 메소드의 인수로 넘어오는 데이터는 중간에 값이
//	변경되면 안되기 때문에 DBCP Template에서는 insert, delete, update sql 명령을 실행하는
//	메소드의 인수를 선언할 때 반드시 final을 붙여서 인수로 넘어온 데이터를 수정할 수 없도록
//	선언해야 한다.
	public void insert(final MvcboardVO mvcboardVO) {
		System.out.println("MvcboardDAO의 insert() 메소드 실행");
		
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "insert into mvcboard (idx, name, subject, content, gup, lev, seq) " +
					"values (mvcboard_idx_seq.nextval, ?, ?, ?, mvcboard_idx_seq.currval, 0, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mvcboardVO.getName());
			pstmt.setString(2, mvcboardVO.getSubject());
			pstmt.setString(3, mvcboardVO.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		*/
		
		String sql = "insert into mvcboard (idx, name, subject, content, gup, lev, seq) " +
				"values (mvcboard_idx_seq.nextval, ?, ?, ?, mvcboard_idx_seq.currval, 0, 0)";
//		update() 메소드의 2번째 인수로 PreparedStatementSetter 인터페이스 객체를 익명으로 구현해서 
//		sql 명령의 "?"를 채운다.
		template.update(sql, new PreparedStatementSetter() {
//			PreparedStatementSetter 인터페이스 객체를 익명으로 구현하면 setValues() 추상 메소드가
//			자동으로 Override되고 여기서 "?"를 채운다.
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, mvcboardVO.getName());
				ps.setString(2, mvcboardVO.getSubject());
				ps.setString(3, mvcboardVO.getContent());
			}
		});
		
	}

//	SelectService 클래스에서 호출되는 테이블에 저장된 전체 글의 개수를 얻어오는 seelct sql 명령을
//	실행하는 메소드
	public int selectCount() {
		System.out.println("MvcboardDAO의 selectCount() 메소드 실행");
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0; // 테이블에 저장된 전체 글의 개수를 저장해서 리턴시킬 변수를 선언한다.
		
		try {
			conn = dataSource.getConnection();
			String sql = "select count(*) from mvcboard";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
//			테이블에 저장된 전체 글의 개수는 null이 나올리 없으므로 조건 비교는 필요없다.
			rs.next();
			result = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		
		return result;
		*/
		
		String sql = "select count(*) from mvcboard";
//		update(): 테이블의 내용이 갱신되는 sql 명령 => insert, delete, update
//		query(): 테이블의 내용이 갱신되지 않는 sql 명령 => select => 실행 결과가 여러건일 경우 사용한다.
//		queryForObject(): 테이블의 내용이 갱신되지 않는 sql 명령 => select => 실행 결과가 1건일 때 사용한다.
//		queryForInt(): 테이블의 내용이 갱신되지 않는 sql 명령 => select => 실행 결과가 정수일 경우 사용한다.
		return template.queryForInt(sql);
	}

//	SelectService 클래스에서 호출되는 브라우저에 표시할 1페이지 분량의 시작 인덱스, 끝 인덱스가 저장된
//	HashMap 객체를 넘겨받고 테이블에서 1페이지 분량의 글 목록을 얻어오는 select sql 명령을 실행하는 메소드
	public ArrayList<MvcboardVO> selectList(HashMap<String, Integer> hmap) {
		System.out.println("MvcboardDAO의 selectList() 메소드 실행");
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MvcboardVO> list = null; // 1페이지 분량의 글 목록을 저장해 리턴시킬 ArrayList 객체를 선언한다.
		
		try {
			conn = dataSource.getConnection();
			String sql = "select * from (" + 
			             "    select rownum rnum, AA.* from (" +
			             "        select * from mvcboard order by gup desc, seq asc" +
			             "    ) AA where rownum <= ?" +
			             ") where rnum >= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, hmap.get("endNo"));
			pstmt.setInt(2, hmap.get("startNo"));
			rs = pstmt.executeQuery();
			
//			ResultSet 객체에 저장된 1페이지 분량의 글 목록을 저장할 ArrayList 객체를 생성한다.
			list = new ArrayList<MvcboardVO>();
//			ResultSet 객체에 저장된 1페이지 분량의 글 목록을 ArrayList에 저장해서 리턴시키기 위해
//			얻어온 글의 개수만큼 반복하며 저장한다.
			while (rs.next()) {
				AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
				MvcboardVO mvcboardVO = ctx.getBean("mvcboardVO", MvcboardVO.class);
				mvcboardVO.setIdx(rs.getInt("idx"));
				mvcboardVO.setName(rs.getString("name"));
				mvcboardVO.setSubject(rs.getString("subject"));
				mvcboardVO.setContent(rs.getString("content"));
				mvcboardVO.setGup(rs.getInt("gup"));
				mvcboardVO.setLev(rs.getInt("lev"));
				mvcboardVO.setSeq(rs.getInt("seq"));
				mvcboardVO.setHit(rs.getInt("hit"));
				mvcboardVO.setWriteDate(rs.getTimestamp("writeDate"));
				list.add(mvcboardVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		
		return list;
		 */
		
//		DBCP Template을 사용하는 경우 select sql 명령에만 "?"를 사용할 수 없다. => "?" 자리에 데이터가
//		저장된 변수를 직접 사용해야 한다.
		String sql = "select * from (" + 
	             "    select rownum rnum, AA.* from (" +
	             "        select * from mvcboard order by gup desc, seq asc" +
	             "    ) AA where rownum <= " + hmap.get("endNo") + 
	             ") where rnum >= " + hmap.get("startNo");
		
//		select sql 명령 실행 결과를 BeanpropertyRowMapper 클래스 생성자의 인수로 MvcboardVO 클래스를
//		넘겨서 sql 명령 실행 결과를 저장시켜 리턴한다.
//		query() 메소드의 실행 결과가 List 인터페이스 타입으로 리턴되기 때문에 메소드의 리턴 타입인
//		ArrayList<MvcboardVO>로 형변환이 필요하다.
//		데이터베이스 테이블의 필드 이름과 BeanPropertyRowMapper 클래스 생성자의 인수로 전달되는 클래스의
//		필드 이름이 반드시 같아야 정상적으로 처리된다.
		return (ArrayList<MvcboardVO>) template.query(sql, new BeanPropertyRowMapper(MvcboardVO.class));
	}

//	IncrementService 클래스에서 호출되는 조회수를 증가시킬 글번호를 넘겨받고 조회수를 증가시키는
//	update sql 명령을 실행하는 메소드
	public void increment(final int idx) {
		System.out.println("MvcboardDAO의 increment() 메소드 실행");
		
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "update mvcboard set hit = hit + 1 where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		*/
		
		/*
//		"?"가 많을 때 사용하면 편리하다.
		String sql = "update mvcboard set hit = hit + 1 where idx = ?";
		
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, idx);
			}
		});
		*/
		
//		"?"가 많지 않을 때 사용하면 편리하다.
		String sql = "update mvcboard set hit = hit + 1 where idx = " + idx;
		template.update(sql);
		
	}

//	ContentViewService 클래스에서 호출되는 조회수를 증가시킨 글번호를 넘겨받고 조회수를 증가시킨
//	글 1건을 얻어오는 select sql 명령을 실행하는 메소드
	public MvcboardVO selectByIdx(int idx) {
		System.out.println("MvcboardDAO의 selectByIdx() 메소드 실행");
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MvcboardVO mvcboardVO = null; // 조회수를 증가시킨 글 1건을 얻어와 저장시켜 리턴할 객체를 선언한다.
		
		try {
			conn = dataSource.getConnection();
			String sql = "select * from mvcboard where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
//			ResultSet 객체에 저장된 글 1건을 리턴시키기 위해서 MvcboardVO 클래스 객체에 저장한다.
			if(rs.next()) {
				AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
				mvcboardVO = ctx.getBean("mvcboardVO", MvcboardVO.class);
				mvcboardVO.setIdx(rs.getInt("idx"));
				mvcboardVO.setName(rs.getString("name"));
				mvcboardVO.setSubject(rs.getString("subject"));
				mvcboardVO.setContent(rs.getString("content"));
				mvcboardVO.setGup(rs.getInt("gup"));
				mvcboardVO.setLev(rs.getInt("lev"));
				mvcboardVO.setSeq(rs.getInt("seq"));
				mvcboardVO.setHit(rs.getInt("hit"));
				mvcboardVO.setWriteDate(rs.getTimestamp("writeDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		
		return mvcboardVO;
		*/
		
		String sql = "select * from mvcboard where idx =" + idx;
		return template.queryForObject(sql, new BeanPropertyRowMapper(MvcboardVO.class));
		
	}

//	UpdateService 클래스에서 호출되는 수정할 글번호와 데이터를 넘겨받고 글 1건을 수정하는
//	update sql 명령을 실행하는 메소드
	public void update(final int idx, final String subject, final String content) {
		System.out.println("MvcboardDAO의 update(int idx, String subject, String content) 메소드 실행");
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "update mvcboard set subject=?, content=? where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setInt(3, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		*/
		/*
		String sql = "update mvcboard set subject=?, content=? where idx=?";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, subject);
				ps.setString(2, content);
				ps.setInt(3, idx);
			}
		});
		*/
		// 문자열은 양쪽에 작은 따옴표 붙여줌
//		String sql = "update mvcboard set subject='" + subject + "', content='" + content + "'where idx=" + idx;
		String sql = String.format("update mvcboard set subject = '%s', content='%s' where idx = %d", subject, content, idx);
		template.update(sql);
		
	}

//	UpdateService 클래스에서 호출되는 수정할 글번호와 데이터가 저장된 객체를 넘겨받고 글 1건을
//	수정하는 update sql 명령을 실행하는 메소드
	public void update(final MvcboardVO mvcboardVO) {
		System.out.println("MvcboardDAO의 update(MvcboardVO mvcboardVO) 메소드 실행");
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "update mvcboard set subject=?, content=? where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mvcboardVO.getSubject());
			pstmt.setString(2, mvcboardVO.getContent());
			pstmt.setInt(3, mvcboardVO.getIdx());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		*/
		String sql = "update mvcboard set subject=?, content=? where idx=?";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, mvcboardVO.getSubject());
				ps.setString(2, mvcboardVO.getContent());
				ps.setInt(3, mvcboardVO.getIdx());
			}
		});
	}

//	DeleteService 클래스에서 호출되는 삭제할 글번호를 넘겨받고 글 1건을 삭제하는 delete sql 명령을
//	실행하는 메소드
	public void delete(int idx) {
		System.out.println("MvcboardDAO의 delete() 메소드 실행");
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "delete from mvcboard where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		*/
		
		String sql = "delete from mvcboard where idx =" + idx;
		template.update(sql);
		
	}

//	ReplyService 클래스에서 호출되는 글그룹과 글이 출력되는 순서가 저장된 HashMAp 객체를 넘겨받고
//	조건에 만족하는 레코드의 seq를 1증가시키는 update sql 명령을 실행하는 메소드
	public void replyIncrement(final HashMap<String, Integer> hmap) {
		System.out.println("MvcboardDAO의 replyIncrement() 메소드 실행");
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "update mvcboard set seq = seq + 1 where gup = ? and seq >= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, hmap.get("gup"));
			pstmt.setInt(2, hmap.get("seq"));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		*/
		
		String sql = "update mvcboard set seq = seq + 1 where gup = ? and seq >= ?";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, hmap.get("gup"));
				ps.setInt(2, hmap.get("seq"));
			}
		});
	}

//	ReplyService 클래스에서 호출되는 답글이 저장된 객체를 넘겨받고 답글을 저장하는 insert sql 명령을
//	실행하는 메소드
	public void replyInsert(final MvcboardVO mvcboardVO) {
		System.out.println("MvcboardDAO의 replyInsert() 메소드 실행");
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "insert into mvcboard (idx, name, subject, content, gup, lev, seq) values (mvcboard_idx_seq.nextval, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mvcboardVO.getName());
			pstmt.setString(2, mvcboardVO.getSubject());
			pstmt.setString(3, mvcboardVO.getContent());
			pstmt.setInt(4, mvcboardVO.getGup());
			pstmt.setInt(5, mvcboardVO.getLev());
			pstmt.setInt(6, mvcboardVO.getSeq());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		*/
		
		String sql = "insert into mvcboard (idx, name, subject, content, gup, lev, seq) values (mvcboard_idx_seq.nextval, ?, ?, ?, ?, ?, ?)";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, mvcboardVO.getName());
				ps.setString(2, mvcboardVO.getSubject());
				ps.setString(3, mvcboardVO.getContent());
				ps.setInt(4, mvcboardVO.getGup());
				ps.setInt(5, mvcboardVO.getLev());
				ps.setInt(6, mvcboardVO.getSeq());
			}
		});
		
		
	}



}



































