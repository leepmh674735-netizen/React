<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

	<a class="sidebar-brand d-flex align-items-center justify-content-center" href="/">
		<div class="sidebar-brand-icon rotate-n-15">
			<i class="fas fa-laugh-wink"></i>
		</div>
		<div class="sidebar-brand-text mx-3">
			<spring:message code="sidebar.brand" text="SB Admin 2" />
		</div>
	</a>

	<hr class="sidebar-divider my-0">

	<li class="nav-item">
		<a class="nav-link" href="/"> 
			<i class="fas fa-fw fa-tachometer-alt"></i> 
			<span><spring:message code="sidebar.dashboard" text="Dashboard" /></span>
		</a>
	</li>

	<hr class="sidebar-divider">

	<div class="sidebar-heading">
		<spring:message code="sidebar.heading.interface" text="Interface" />
	</div>

	<li class="nav-item">
		<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo"> 
			<i class="fas fa-fw fa-cog"></i> 
			<span><spring:message code="sidebar.community" text="Community" /></span>
		</a>
		<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<a class="collapse-item" href="/notice/list"><spring:message code="sidebar.notice" text="공지사항" /></a> 
				<a class="collapse-item" href="/product/list"><spring:message code="sidebar.product" text="상품목록" /></a>
			</div>
		</div>
	</li>

	<li class="nav-item">
		<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities"> 
			<i class="fas fa-fw fa-wrench"></i> 
			<span><spring:message code="sidebar.utilities" text="Utilities" /></span>
		</a>
		<div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header"><spring:message code="sidebar.utilities.custom" text="Custom Utilities:" /></h6>
				<a class="collapse-item" href="utilities-color.html"><spring:message code="sidebar.utilities.colors" text="Colors" /></a> 
				<a class="collapse-item" href="utilities-border.html"><spring:message code="sidebar.utilities.borders" text="Borders" /></a> 
				<a class="collapse-item" href="utilities-animation.html"><spring:message code="sidebar.utilities.animations" text="Animations" /></a>
				<a class="collapse-item" href="utilities-other.html"><spring:message code="sidebar.utilities.other" text="Other" /></a>
			</div>
		</div>
	</li>

	<hr class="sidebar-divider">

	<div class="sidebar-heading">
		<spring:message code="sidebar.heading.addons" text="Addons" />
	</div>

	<li class="nav-item active">
		<a class="nav-link" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages"> 
			<i class="fas fa-fw fa-folder"></i> 
			<span><spring:message code="sidebar.pages" text="Pages" /></span>
		</a>
		<div id="collapsePages" class="collapse show" aria-labelledby="headingPages" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header"><spring:message code="sidebar.pages.login" text="Login Screens:" /></h6>
				<a class="collapse-item" href="login.html"><spring:message code="sidebar.pages.login.btn" text="Login" /></a> 
				<a class="collapse-item" href="register.html"><spring:message code="sidebar.pages.register"