package com.winter.react.cart;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper {
	
	public int delete(List<CartDTO> ar)throws Exception;
	
	public int create(CartDTO cartDTO)throws Exception;
	
	public List<ProductDTO> list (MemberDTO memberDTO)throws Exception;
	
}

}
