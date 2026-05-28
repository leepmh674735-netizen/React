<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Page</title>
	<c:import url="/WEB-INF/views/temp/head_css.jsp"></c:import>
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.css" rel="stylesheet">
</head>
<body id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/temp/sidebar.jsp"></c:import>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/temp/topbar.jsp"></c:import>
				
                <div class="container-fluid">
                    <h1 class="h3 mb-4 text-gray-800">Create Page</h1>
                    
                    <div>
	                    <form action="./reply" method="post" enctype="multipart/form-data">
	                      <input type="hidden" name="boardNum" value="${qnaDTO.boardNum}">	
						  
						  <div class="form-group">
						    <label for="title">제목</label>
						    <input type="text" name="boardTitle" class="form-control" id="title">
						  </div>
						  
						  <div class="form-group">
						    <label for="writer">작성자</label>
						    <input type="text" name="boardWriter" class="form-control" id="writer">
						  </div>						 
						  
						  <div class="form-group">
						    <label for="contents">내용</label>
						    <textarea rows="12" cols="" name="boardContents" class="form-control" id="contents"></textarea>
						  </div>
						  
						  <div class="form-group">
							<button type="button" id="add">File Add</button>
						  </div>

						  <div class="form-group" id="result" data-file-size="0">
						  	<label>첨부파일</label>
						  </div>
						  				  					  
						  <button type="submit" class="btn btn-primary">Submit</button>
						</form>
                    </div>
                </div>
			</div>
			<c:import url="/WEB-INF/views/temp/footer.jsp"></c:import>
		</div>
	</div>
	
	<c:import url="/WEB-INF/views/temp/footer_script.jsp"></c:import>
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.js"></script>
	<script>
      $('#contents').summernote({
        placeholder: '내용을 구체적으로 작성해 주세요.',
        tabsize: 2,
        height: 200
      });
    </script>
	<script src="/js/board/form.js"></script>
</body>
</html>