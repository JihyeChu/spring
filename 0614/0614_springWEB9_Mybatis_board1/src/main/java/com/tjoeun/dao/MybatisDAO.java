package com.tjoeun.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.tjoeun.vo.MvcboardVO;

//	mapper 연결에 사용하는 인터페이스
//	이 인터페이스의 풀 패키지 이름을 mvcboard.xml 파일의 namespace에 정확히 적어야 한다.
public interface MybatisDAO {
	
//	mapper로 사용되는 interface의 추상 메소드 형식은 resultType id(parameterType)와 같은 형식으로 만든다.
//	MybatisDAO 인터페이스의 추상 메소드 이름의 xml 파일의 sql 명령을 식별하는 id로 사용되고 추상 메소드의
//	인수로 전달된 데이터가 xml 파일의 sql 명령으로 전달된다.
	void insert(MvcboardVO mvcboardVO);
	int selectCount();
	ArrayList<MvcboardVO> selectList(HashMap<String, Integer> hmap);
	void increment(int idx);
	MvcboardVO selectByIdx(int idx);
	void update(MvcboardVO mvcboardVO);
	void delete(int idx);
	void incrementSeq(HashMap<String, Integer> hmap);
	void replyInsert(MvcboardVO mvcboardVO);
	
}
