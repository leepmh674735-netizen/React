package com.winter.react.board.notice;

import java.util.List;

// 1. 잘못된 외부 라이브러리 임포트 제거
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.winter.react.board.BoardDTO;

@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeService implements BoardService {

	@Autowired
	private NoticeMapper noticeMapper;

	// 기존 코드에서 누락된 fileManager 주입 추가 (또는 fileMapper 사용 확인 필요)
	// 아래 로직에서 fileManger를 사용하므로 올바른 클래스명과 변수명으로 주입받아야 합니다.
	@Autowired
	private FileManager fileManager; 

	@Value("${app.board.notice}")
	private String name;

	@Override
	public List<BoardDTO> list(Pager pager) throws Exception {
		pager.makePageNum(noticeMapper.getCount(pager));
		pager.makeStartNum();

		return noticeMapper.list(pager);
	}

	@Override
	public BoardDTO detail(BoardDTO boardDTO) throws Exception {
		return noticeMapper.detail(boardDTO);
	}

	@Override
	public int create(BoardDTO boardDTO, MultipartFile[] attach) throws Exception {
		int result = noticeMapper.create(boardDTO);

		// 2. result result; 오타 -> return result; 로 수정
		if (attach == null) {
			return result; 
		}

		// 3. MultparFile -> MultipartFile 오타 수정
		for (MultipartFile f : attach) { 
			if (f.isEmpty()) {
				continue;
			}

			// 4. fileManger -> fileManager 오타 수정
			String fileName = fileManager.fileSave(name, f); 
			NoticeFileDTO noticeFileDTO = new NoticeFileDTO();
			noticeFileDTO.setBoardNum(boardDTO.getBoardNum());
			noticeFileDTO.setFileName(fileName);

			result = noticeMapper.createFile(noticeFileDTO);
		}

		return result; // 5. 문법 오류 수정
	}

	@Override
	public int update(BoardDTO boardDTO, MultipartFile[] attach) throws Exception {
		int result = noticeMapper.update(boardDTO);
		return result; // 6. result result; -> return result; 수정
	}
 
	@Override
	public int delete(BoardDTO boardDTO) throws Exception {
		boardDTO = noticeMapper.detail(boardDTO);
		
		// 7. fileDTO -> FileDTO 클래스명 대문자 수정 및 boardDTO 내부 리스트 변수명 체크
		// (기본적으로 BoardDTO 내부의 파일 리스트 타입이 FileDTO 계열이어야 합니다)
		if (boardDTO.getList() != null) {
			for (FileDTO fileDTO : boardDTO.getList()) {
				fileManager.fileDelete(name, fileDTO);
			}
		}
		
		// 8. resulot -> result 오타 수정 및 리턴문 적용
		int result = noticeMapper.delete(boardDTO);
		return result; 
	} // 9. 괄호가 닫히면서 delete 메서드 정상 종료

	// 10. 기존에 delete 메서드 내부에 갇혀있던 오버라이드 메서드를 밖으로 탈출시킴
	@Override
	public FileDTO fileDetail(FileDTO fileDTO) throws Exception {
		return noticeMapper.fileDetail(fileDTO);
	}
}