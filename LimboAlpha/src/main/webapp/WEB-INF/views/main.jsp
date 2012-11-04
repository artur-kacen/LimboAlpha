<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf8">

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
<link href="${pageContext.request.contextPath}/css/menu.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" type="text/css" />
<title><spring:message code="label.title" /></title>

<script>
	$(function() {
		$(".datepicker").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	});
	function onSubmitRepair(){
		var amount = document.forms["repair"]["paymentAmount"].value;
		amount = amount.replace(",", "");
		amount = amount.repalce(".", "");
		document.forms["repair"]["paymentAmount"].value = amount;
		alert(document.forms["repair"]["paymentAmount"].value);
	}
</script>
</head>
<body>


	<div align="right">
		<a href="<c:url value="/logout" />"> <spring:message
				code="label.logout" />
		</a> <br /> <span style="float: right"> <a href="?lang=ru">ru</a>
			| <a href="?lang=lv">lv</a>
		</span>
	</div>

	<br />
	<!--  	<div align="center">
		<ul id="css3menu1" class="topmenu">
			<li class="topmenu"><a href="#"
				style="height: 18px; line-height: 18px;"><span><spring:message
							code="label.header_repair" /></span></a>
				<ul>
					<li><a href="<c:url value="/repair/add" />"> <spring:message
								code="label.repair_new" /></a></li>
					<li><a href="<c:url value="/repair/list" />"><spring:message
								code="label.repair_list" /></a></li>
				</ul></li>
			<li class="topmenu"><a href="#"
				style="height: 18px; line-height: 18px;"><span><spring:message
							code="label.header_shop" /></span></a></li>
			<sec:authorize ifAllGranted="ROLE_ADMIN">
				<li class="topmenu"><a href="<c:url value="/user" />"
					style="height: 18px; line-height: 18px;"><spring:message
							code="label.header_users" /></a></li>
			</sec:authorize>
			<sec:authorize ifAllGranted="ROLE_SUPERADMIN">
				<li class="topmenu"><a href="<c:url value="/clients" />"
					style="height: 18px; line-height: 18px;"><spring:message
							code="label.header_clients" /></a></li>
			</sec:authorize>
		</ul>
	</div>-->

	<nav id="menu-wrap">
	<ul id="menu">
		<li><a href="<c:url value="/repair" />"><spring:message	code="label.header_repair" /></a>
			<ul>
				<li><a href="<c:url value="/repair/add" />"> <spring:message code="label.repair_new" /></a></li>
				<li><a href="<c:url value="/repair/list" />"><spring:message code="label.repair_list" /></a>
					<ul>
						<li><a href="<c:url value="/repair/list/all" />"><spring:message code="label.repair_all" /></a></li>
						<li><a href="<c:url value="/repair/list/repaired" />"><spring:message code="label.repair_repaired" /></a></li>
						<li><a href="<c:url value="/repair/list/returned" />"><spring:message code="label.repair_returned" /></a></li>
						<sec:authorize ifAllGranted="ROLE_ADMIN">
							<li><a href="<c:url value="/repair/list/deleted" />"><spring:message code="label.repair_deleted" /></a></li>
						</sec:authorize>
					</ul>
				</li>
			</ul>
		</li>
		<li><a href="#"><spring:message code="label.header_shop" /></a></li>			
		<sec:authorize ifAllGranted="ROLE_ADMIN">
			<li><a href="<c:url value="/user" />"><spring:message code="label.header_users" /></a>
			<ul>
				<li><a href="<c:url value="/user/add" />"> <spring:message code="label.user_new" /></a></li>
				<li><a href="<c:url value="/user/list" />"><spring:message code="label.user_list" /></a>
			</ul></li>
		</sec:authorize>
		<sec:authorize ifAllGranted="ROLE_SUPERADMIN">
			<li><a href="<c:url value="/clients" />"><spring:message code="label.header_clients" /></a></li>
		</sec:authorize>
		<li><a href="">Contact</a></li>
	</ul>
	</nav>