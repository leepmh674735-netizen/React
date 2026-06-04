package com.winter.react.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

	@Autowired
	private CartMapper cartMapper;

	public int delelte(List<CartDTO> ar) throws Exception {
		return cartMapper.delete(ar);
	}

	public List<ProductDTO> list(MemberDTO memberDTO) throws Exception {
		return cartMapper.list(memberDTO);

	}

	public int create(CartDTO cartDTO) throws Exception {
		return cartMapper.create(cartDTO);
	}

}
