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
	                    <form action="./create" method="post">
	                      <input type="hidden" name="productNum" value="${param.productNum}">
						  
						  <div class="form-group">
						    <label for="password">Password</label>
						    <input type="password" name="accountPw" class="form-control" id="password">
						  </div>	
						  
						  <div class="form-group">
						    <label for="passwordCheck">Password Check</label>
						    <input type="password" name="accountPwCheck" class="form-control" id="passwordCheck">
						  </div>						 
						   
						  <div class="form-group">
						    <label for="contents">Contents</label>
						    <textarea name="boardContents" class="form-control" id="contents"></textarea>
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
</body>
</html>