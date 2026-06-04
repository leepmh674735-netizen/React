package com.winter.react.pager;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Pager {
	
	private String search="";
	
	private String kind;
	
	private Long page;
	
	private Long perPage;
	
	private Long startNum;
	
	private boolean pre=true;

	private boolean next=true;
	
	private Long start;
	
	private Long end;

	public Long getPage() {
		if(page==null||page<1) {
			this.page=1L;
		}
		return this.page;
		
	}
	
	public Long getPerPage() {
		if(perPage == null || perPage<1) {
			this.perPage=5L;
		}
		return this.perPage;
	}
	
	public void makePageNum(Long totalCount) {
		//1. 총 페이지 수 구하기
		Long totalPage=(long)(Math.ceil((double)totalCount/this.getPerPage()));
		
		//2. 총 블럭 수 구하기
		Long perBlock=5L;//한블럭당 출력한 번호의 갯수
		
		Long totalBlock = totalPage/perBlock;
		if(totalPage%5 != 0) {
			totalBlock++;
		}
		
		//3. 페이지 번호로 현재 블럭 번호 구하기
		Long curBlock = this.getPage()/perBlock;
		if(this.page%perBlock != 0) {
			curBlock++;
		}
		
		//4. 현재블럭번호로 시작번호와 끝번호 구하기
		start = (curBlock-1)*perBlock+1;
		end = curBlock*perBlock;
		
		//5. 현재블럭번호가 총블럭과 같다면
		if(curBlock==totalBlock) {
			end=totalPage;
			next=false;
		}
		
		//6. 블럭 유무
		if(curBlock<2) {
			pre=false;
		}
		
		
	}
	
	public void makeStartNum() {
		//1, 0: 2, 5
		this.startNum = (this.getPage()-1)*this.getPerPage();
	}
	
	

}