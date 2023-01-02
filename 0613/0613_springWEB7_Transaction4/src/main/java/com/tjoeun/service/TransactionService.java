package com.tjoeun.service;

import com.tjoeun.vo.CardVO;

public interface TransactionService {

	public abstract void execute(CardVO cardVO);
	
}
