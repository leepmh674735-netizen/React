package com.winter.react.board.qua;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import com.winter.react.account.AcccountService;
import com.winter.react.board.BoardDTO;

@Controller
@RequestMapping("/qua/*")
public class QnaController {

	private final AcccountService acccountService;
	private final QnaService qnaService;

	@Value("${app.board.qua}")
	private String name;

	public QnaController(AcccountService acccountService, QnaService qnaService) {
		this.acccountService = acccountService;
		this.qnaService = qnaService;
	}

	@ModelAttribute("name")
	public String getName() {
		return this.name;
	}

	@GetMapping("list")
	public String list(Pager pager, Model model) throws Exception {
		List<BoardDTO> ar = qnaService.list(pager);
		model.addAttribute("list", ar);
		return "board/list";
	}

	@GetMapping("detail")
	public String detail(QnaDTO qnaDTO, Model model) throws Exception {
		BoardDTO boardDTO = qnaService.detail(qnaDTO);
		model.addAttribute("dto", boardDTO);
		return "board/detail";
	}

	@GetMapping("create")
	public String create() throws Exception {
		return "board/create";
	}

	@PostMapping("create")
	public String create(QnaDTO qnaDTO, @RequestParam("attach") MultipartFile[] attach) throws Exception {
		int result = qnaService.create(qnaDTO, attach);
		return "redirect:./list";
	}

	@GetMapping("update")
	public String update(QnaDTO qnaDTO, Model model) throws Exception {
		BoardDTO boardDTO = qnaService.detail(qnaDTO);
		model.addAttribute("dto", boardDTO);
		return "board/update";
	}

	@PostMapping("update")
	public String update(QnaDTO qnaDTO, @RequestParam("attach") MultipartFile[] attach) throws Exception {
		int result = qnaService.update(qnaDTO, attach);
		return "redirect:./list";
	}

	@PostMapping("delete")
	public String delete(QnaDTO qnaDTO) throws Exception {
		int result = qnaService.delete(qnaDTO);
		return "redirect:./list";
	}

	@GetMapping("down")
	public String fileDown(FileDTO fileDTO, Model model) throws Exception {
		fileDTO = qnaService.fileDetail(fileDTO);
		model.addAttribute("fileDTO", fileDTO);
		return "fileDownView";
	}
}