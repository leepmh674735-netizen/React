package com.winter.react.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardService {
	
	public List<BoardDTO> list(Pager pager)throws Exception;
	
	public BoardDTO detail(BoardDTO boardDTO) throws Exception;

	//C - create 
	public int create(BoardDTO boardDTO, MultipartFile [] attach) throws Exception;

     
	 //U - update
	public int update(BoardDTO boardDTO, MultipartFile [] attach)throws Exception;
	
	//D -delete
	public int delete(BoardDTO boardDTO)throws Exception;
	
}
