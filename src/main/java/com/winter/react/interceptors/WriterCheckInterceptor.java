package com.winter.react.interceptors;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WriterCheckInterceptor implements HandlerInterceptor{
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
//		if(request.getMethod().toUpperCase().equals("POST")) {
//			return;
//		}
		
		HttpSession session = request.getSession();
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		//modelAndview
		Map<String, Object> model = modelAndView.getModel();
		BoardDTO boardDTO=(BoardDTO)model.get("dto");
		log.info("{}", model);
		boolean flag = memberDTO.getUsername().equals(boardDTO.getBoardWriter());
		if(!flag) {
			modelAndView.setViewName("commons/result");
			modelAndView.addObject("result", "작성자만 가능");
			modelAndView.addObject("url", "./list");
		}
	} 

}
}
