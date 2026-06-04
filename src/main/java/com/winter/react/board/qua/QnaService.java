package com.winter.react.board.qua;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.react.board.BoardDTO;
import com.winter.react.board.BoardService;
import com.winter.react.board.FileDTO;
import com.winter.react.util.Pager;

@Service
public class QnaService implements BoardService {

	@Autowired
	private QnaMapper quaMapper;

	@Autowired
	private FileManager fileManager;

	@Value("${app.board.qna}")
	private String name;

	@Override
	public List<BoardDTO> list(Pager pager) throws Exception {
		pager.makePageNum(quaMapper.getCount(pager));
		pager.makeStartNum();
		return quaMapper.list(pager);
	}

	@Override
	public BoardDTO detail(BoardDTO boardDTO) throws Exception {
		return quaMapper.detail(boardDTO);
	}

	@Override
	public int create(BoardDTO boardDTO, MultipartFile[] attach) throws Exception {
		int result = quaMapper.create(boardDTO);

		if (attach == null) {
			return result;
		}

		for (MultipartFile f : attach) {
			if (f.isEmpty()) {
				continue;
			}

			String fileName = fileManager.fileSave(name, f);
			QnaFileDTO qnaFileDTO = new QnaFileDTO();
			qnaFileDTO.setBoardNum(boardDTO.getBoardNum());
			qnaFileDTO.setOriName(f.getOriginalFilename());
			qnaFileDTO.setFileName(fileName);

			result = quaMapper.createFile(qnaFileDTO);
		}

		return result;
	}

	@Override
	public int update(BoardDTO boardDTO, MultipartFile[] attach) throws Exception {
		return quaMapper.update(boardDTO);
	}

	@Override
	public int delete(BoardDTO boardDTO) throws Exception {
		boardDTO = quaMapper.detail(boardDTO);

		if (boardDTO.getList() != null) {
			for (FileDTO fileDTO : boardDTO.getList()) {
				fileManager.fileDelete(name, fileDTO);
			}
		}

		return quaMapper.delete(boardDTO);
	}

	@Override
	public FileDTO fileDetail(FileDTO fileDTO) throws Exception {
		return quaMapper.fileDetail(fileDTO);
	}
}