<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/temp/head_css.jsp"></c:import>
</head>
<body id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/temp/sidebar.jsp"></c:import>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/temp/topbar.jsp"></c:import>
				
				<div class="container-fluid">
					<h1 class="h3 mb-4 text-gray-800">Blank Page</h1>
					<c:if test="${not empty member}">
						<h3>로그인 상태</h3>
						<spring:message code="welcome.login" arguments="${member.username}-${member.birth}" argumentSeparator="-"></spring:message>
					</c:if> 

					<c:if test="${empty member}">                    
						<h3>비 로그인 상태</h3>
					</c:if>
                    
					<spring:message code="hi" text="code가 없을 때 출력하는 기본 메세지" var="m"></spring:message>
                    
					<div id="map" style="width:500px;height:400px;"></div>
                    
					${m}, ${m}
				</div>
			</div>
			<c:import url="/WEB-INF/views/temp/footer.jsp"></c:import>
		</div>
	</div>
	
	<c:import url="/WEB-INF/views/temp/footer_script.jsp"></c:import>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a51fbcc2036a3caba4c7de7ab3e51474"></script>
	<script type="text/javascript">
		const container = document.getElementById('map');
		
		// 💡 재할당되지 않는 옵션 객체와 지도 인스턴스는 const로 선언하는 것이 안전합니다.
		const options = { 
			center: new kakao.maps.LatLng(33.450701, 126.570667), 
			level: 3 
		};
	
		const map = new kakao.maps.Map(container, options); 
	</script>
</body>
</html>