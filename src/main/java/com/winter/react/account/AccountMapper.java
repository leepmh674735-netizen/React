package com.winter.react.account;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
	
	public int create(AcccountDTO accountDTO)throws Exception;

}