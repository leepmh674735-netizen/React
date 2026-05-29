package com.winter.react.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcccountService {
	
	@Autowired
	private AccountMapper accountMapper;
	
	
	public int create(AccountDTO accountDTO)throws Exception{
		long time = System.currentTimeMillis();
		
		accountDTO.setAcccountNum(String.valueOf(time));
		
		int result = acccountMapper.create(accoountDTO);
	}

}
